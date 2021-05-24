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

package com.github.reloadedd.correctanaddress.entities;

import org.apache.commons.text.StringEscapeUtils;


/**
 * A simple class representing an address.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class AddressEntity {
    private final String countryName;
    private final String stateName;
    private final String cityName;

    public AddressEntity(String countryName, String stateName, String cityName) {
        this.countryName = countryName;
        this.stateName = stateName;
        this.cityName = cityName;
    }

    private String capitalizeFirstLetter(String what) {
        String firstLetter = what.substring(0, 1);
        String remainingLetters = what.substring(1);

        /* change the first letter to uppercase */
        firstLetter = firstLetter.toUpperCase();

        return firstLetter + remainingLetters;
    }

    public String constructAddress() {
        return StringEscapeUtils.escapeHtml4(String.format("%s, %s, %s", this.capitalizeFirstLetter(this.countryName),
                this.capitalizeFirstLetter(this.stateName),
                this.capitalizeFirstLetter(this.cityName)));
    }
}
