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

package com.github.reloadedd.correctanaddress.here;

import com.here.account.oauth2.HereAccessTokenProvider;

public class HereAuthentication {
    /**
     * A simple method that builds a HereAccessTokenProvider,
     * gets one Access Token,
     * and if successful outputs the first few characters of the valid token.
     */
    public void doGetAccessToken() {
        try {
            // use your provided System properties, ~/.here/credentials.ini, or credentials.properties file
            HereAccessTokenProvider accessTokens = HereAccessTokenProvider.builder().build();

            // call accessTokens.getAccessToken(); every time one is needed, it will always be fresh
            String accessToken = accessTokens.getAccessToken();
            // use accessToken on a request...

            useAccessToken(accessToken);
        } catch (Exception e) {
            trouble(e);
        }

    }

    protected void useAccessToken(String accessToken) {
        System.out.println("got HERE Access Token: " + accessToken);
    }

    protected void trouble(Exception e) {
        System.err.println("trouble " + e);
        e.printStackTrace();
        exit(1);
    }

    protected void exit(int status) {
        System.exit(status);
    }
}
