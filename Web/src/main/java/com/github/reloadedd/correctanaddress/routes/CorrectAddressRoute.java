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

import com.github.reloadedd.correctanaddress.entities.AddressEntity;
import com.github.reloadedd.correctanaddress.entities.GoogleMapsQueryEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Handler class for the route that handles correcting the address and giving back the results page to the user.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
@Controller
@RequestMapping("/correct-this-address")
public class CorrectAddressRoute {
    @PostMapping
    public String getCorrectAddress(@RequestParam String country, @RequestParam String state,
                                    @RequestParam String city, Model model) {
        AddressEntity addressEntity = new AddressEntity(country, state, city);
        model.addAttribute("address", addressEntity.constructAddress());
        model.addAttribute("query",
                new GoogleMapsQueryEntity(addressEntity).getGoogleMapsURL());

        return "results";
    }
}
