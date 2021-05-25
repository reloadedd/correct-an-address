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


import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing tests for the <code>HereMapsApiAuthenticator</code> class.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class IndexRouteTests {
    private RestTemplate restTemplate;

    @Before
    public void initialization() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    @Test
    public void testGetMethodOnIndexPage() {
        String firstUrl = "https://reloadedd.me:5443/";
        String secondUrl = "https://reloadedd.me:5443/index";
        String thirdUrl = "https://reloadedd.me:5443/index.html";
        String fourthUrl = "https://reloadedd.me:5443/this-is-not-index";

        /* Test the first URL which should work */
        ResponseEntity<String> firstResponse = this.restTemplate.getForEntity(firstUrl, String.class);
        assertEquals(firstResponse.getStatusCode(), HttpStatus.OK);

        /* Test the second URL which should work */
        ResponseEntity<String> secondResponse = this.restTemplate.getForEntity(secondUrl, String.class);
        assertEquals(secondResponse.getStatusCode(), HttpStatus.OK);

        /* Test the third URL which should work */
        ResponseEntity<String> thirdResponse = this.restTemplate.getForEntity(thirdUrl, String.class);
        assertEquals(thirdResponse.getStatusCode(), HttpStatus.OK);

        /* Test the fourth URL which shouldn't work */
        ResponseEntity<String> fourthResponse = null;

        try {
             fourthResponse = this.restTemplate.getForEntity(fourthUrl, String.class);
        } catch (HttpClientErrorException ignored) {}

        assertNull(fourthResponse);
    }
}
