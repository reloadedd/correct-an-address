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


/**
 * Entity class responsible with the generation of a valid Google Maps url.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class GoogleMapsQueryEntity {
    private static final String GOOGLE_MAPS_URL = "https://www.google.com/maps/embed/v1/place";
    private static final String API_KEY = "AIzaSyBGZuTWkfZ_hjNmMT-b70Z2ka6BAZCYkCM";
    private static final int ZOOM_LEVEL = 15;
    private final AddressEntity addressEntity;

    public GoogleMapsQueryEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public String getGoogleMapsURL() {
        return String.format("%s?key=%s&q=%s&zoom=%d", GOOGLE_MAPS_URL, API_KEY, addressEntity.constructAddress(),
                ZOOM_LEVEL);
    }
}
