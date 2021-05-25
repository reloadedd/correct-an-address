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

package com.github.reloadedd.correctanaddress.routes;

import java.util.Collections;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.client.RestTemplateBuilder;
import com.github.reloadedd.correctanaddress.entities.GoogleMapsQueryEntity;
import com.github.reloadedd.correctanaddress.entities.HereMapsAddressEntity;


/**
 * Handler class for the route that handles correcting the address and giving back the results page to the user.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
@Controller
@RequestMapping("/correct-this-address")
public class CorrectAddressRoute {
    private static final String CORRECT_ADDRESS_API_URL = "https://reloadedd.me:5443/api/correct-this-address";

    @PostMapping
    public String getCorrectAddress(@RequestParam String country, @RequestParam String state,
                                    @RequestParam String city, @RequestParam String language, Model model) {
        final RestTemplate restTemplate = new RestTemplateBuilder().build();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.put("country", Collections.singletonList(country));
        map.put("state", Collections.singletonList(state));
        map.put("city", Collections.singletonList(city));
        map.put("language", Collections.singletonList(language));

        /* Set Content-Type header to JSON */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        HereMapsAddressEntity response = restTemplate.postForObject(CORRECT_ADDRESS_API_URL, request,
                HereMapsAddressEntity.class);

        assert response != null;
        model.addAttribute("address", response.constructAddress());
        model.addAttribute("query",
                new GoogleMapsQueryEntity(response.getGoogleMapsAddress()).getGoogleMapsURL());

        return "results";
    }
}
