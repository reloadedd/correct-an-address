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

import java.util.Collections;
import org.junit.jupiter.api.Test;
import com.github.reloadedd.correctanaddress.entities.HereMapsAddressEntity;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Class containing tests for the <code>RestAPI</code> class.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class RestAPITests {
    private static final String CORRECT_ADDRESS_API_URL = "https://reloadedd.me:5443/api/correct-this-address";

    @Test
    public void testCorrectAddressFunctionalityOfApi() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.put("country", Collections.singletonList("Romania"));
        map.put("state", Collections.singletonList("Iasi"));
        map.put("city", Collections.singletonList("Iasi"));
        map.put("language", Collections.singletonList("en-US"));

        /* Set Content-Type header to JSON */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        HereMapsAddressEntity response = restTemplate.postForObject(CORRECT_ADDRESS_API_URL, request,
                HereMapsAddressEntity.class);

        assertNotNull(response);
    }

    @Test
    public void testValidityOfTheResponse() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

        map.put("country", Collections.singletonList("Romania"));
        map.put("state", Collections.singletonList("Iasi"));
        map.put("city", Collections.singletonList("Iasi"));
        map.put("language", Collections.singletonList("en-US"));

        /* Set Content-Type header to JSON */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        HereMapsAddressEntity response = restTemplate.postForObject(CORRECT_ADDRESS_API_URL, request,
                HereMapsAddressEntity.class);

        assertEquals(response.getAddress().getCountryCode(), "ROU");
        assertEquals(response.getAddress().getCountryName(), "Romania");
        assertEquals(response.getAddress().getCity(), "Iasi");
    }
}
