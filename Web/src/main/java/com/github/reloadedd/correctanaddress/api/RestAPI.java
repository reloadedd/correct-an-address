/*
 *     "? Correct An Address" is a tool that will correct a partially correct address.
 *     Copyright (C) 2021  Ionuț Roșca <ionut.rosca@info.uaic.ro>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.reloadedd.correctanaddress.api;

import com.github.reloadedd.correctanaddress.entities.AddressEntity;
import com.github.reloadedd.correctanaddress.entities.HereMapsAddressEntity;
import com.github.reloadedd.correctanaddress.exceptions.NoTokenReceivedException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * This class implements the REST API of the project.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class RestAPI {
    @PostMapping(value = "/correct-this-address", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HereMapsAddressEntity> correctAddressApiRoute(@RequestParam String country,
                                                                        @RequestParam String state,
                                                                        @RequestParam String city,
                                                                        @RequestParam String language)
            throws NoTokenReceivedException {
        Gson gson = new Gson();
        HereMapsMediator mediator = new HereMapsMediator();

        AddressEntity addressEntity = new AddressEntity(country, state, city, language);
        String locateAddressJsonResponse = mediator.locate(addressEntity);

        /* Parse the whole JSON and extract the contents of the "items" which will contain a list of
         * minimum one element of found locations.
         */
        JsonElement jsonElement = JsonParser.parseString(locateAddressJsonResponse);
        JsonElement root = jsonElement.getAsJsonObject().get("items");
        Type listType = new TypeToken<List<HereMapsAddressEntity>>() {}.getType();

        JsonElement items = gson.toJsonTree(root);
        List<HereMapsAddressEntity> list = gson.fromJson(items, listType);

        return new ResponseEntity<>(this.getFittestIndividualAddress(list), HttpStatus.OK);
    }

    private HereMapsAddressEntity getFittestIndividualAddress(List<HereMapsAddressEntity> addresses) {
        double maximumFitness = 0.0;
        HereMapsAddressEntity fittestAddress = null;

        for (HereMapsAddressEntity entity: addresses) {
            double currentFitness = entity.getScoring().getQueryScore();
            if (currentFitness > maximumFitness) {
                maximumFitness = currentFitness;
                fittestAddress = entity;
            }
        }

        return fittestAddress;
    }
}
