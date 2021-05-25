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

package com.github.reloadedd.entities;


/**
 * Entity class that describe a particular item from the JSON returned by the HERE Maps API.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class HereMapsAddressEntity {
    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getResultType() {
        return resultType;
    }

    public String getLocalityType() {
        return localityType;
    }

    public Address getAddress() {
        return address;
    }

    public Position getPosition() {
        return position;
    }

    public MapView getMapView() {
        return mapView;
    }

    public Scoring getScoring() {
        return scoring;
    }

    private String title;
    private String id;
    private String resultType;
    private String localityType;
    private Address address;
    private Position position;
    private MapView mapView;
    private Scoring scoring;

    public static class Address {
        public String getLabel() {
            return label;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public String getStateCode() {
            return stateCode;
        }

        public String getState() {
            return state;
        }

        public String getCounty() {
            return county;
        }

        public String getCity() {
            return city;
        }

        public String getPostalCode() {
            return postalCode;
        }

        private String label;
        private String countryCode;
        private String countryName;
        private String stateCode;
        private String state;
        private String county;
        private String city;
        private String postalCode;
    }

    public static class Position {
        private double lat;         /* Latitude */
        private double lng;         /* Longitude */

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }

    public static class MapView {
        private double west;
        private double south;
        private double east;
        private double north;

        public double getWest() {
            return west;
        }

        public double getSouth() {
            return south;
        }

        public double getEast() {
            return east;
        }

        public double getNorth() {
            return north;
        }
    }

    public static class Scoring {
        private double queryScore;
        private FieldScore fieldScore;

        public double getQueryScore() {
            return queryScore;
        }

        public FieldScore getFieldScore() {
            return fieldScore;
        }

        public class FieldScore {
            private int city;

            public int getCity() {
                return city;
            }
        }
    }

    public String constructAddress() {
        return String.format("%s %s %s %s [Latitude: %g, Longitude: %g]",
            this.address.countryCode == null? "" : this.address.countryCode + ",",
            this.address.countryName == null? "" : this.address.countryName + ",",
            this.address.state == null? "" : this.address.state + ",",
            this.address.city == null? "" : this.address.city,
            this.position.lat,
            this.position.lng
        );
    }

    public String getGoogleMapsAddress() {
        return String.format("%s %s %s %s",
            this.address.countryCode == null? "" : this.address.countryCode + ",",
            this.address.countryName == null? "" : this.address.countryName + ",",
            this.address.state == null? "" : this.address.state + ",",
            this.address.city == null? "" : this.address.city
        );
    }
}
