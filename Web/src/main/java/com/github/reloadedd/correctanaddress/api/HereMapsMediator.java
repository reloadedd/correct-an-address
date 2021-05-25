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
import com.github.reloadedd.correctanaddress.exceptions.NoTokenReceivedException;
import com.github.reloadedd.correctanaddress.heremaps.HereMapsApiAuthenticator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * Class that facilitates the communication between the project's API and the HERE Maps' API.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class HereMapsMediator {
    private String hereMapsToken;
    private final RestTemplate restTemplate;
    private final HereMapsApiAuthenticator hereMapsGateway;
    private static final String HERE_MAPS_GEOCODE_URL = "https://geocode.search.hereapi.com/v1/geocode";

    public HereMapsMediator() throws NoTokenReceivedException {
        this.restTemplate = new RestTemplateBuilder().build();
        this.hereMapsGateway = new HereMapsApiAuthenticator();

        /* We get the token for the first time */
        this.renewToken();
    }

    public void renewToken() throws NoTokenReceivedException {
        this.hereMapsToken = this.hereMapsGateway.getAccessToken();
        if (this.hereMapsToken == null) {
            throw new NoTokenReceivedException();
        }
    }

    public String locate(AddressEntity address) {
        String url = String.format("%s?q=%s&lang=%s", HERE_MAPS_GEOCODE_URL, String.format("country=%s;state=%s;city=%s",
                address.getCountryName(), address.getStateName(), address.getCityName()), address.getLanguage());

        /* Set the token for the HERE Maps API */
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer\n" + this.hereMapsToken);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class
        );

        return response.getBody();
    }
}
