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
 *
 */

package com.github.reloadedd;

import com.github.reloadedd.entities.HereMapsAddressEntity;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


/**
 * Main controller for the JavaFX Application.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class Controller {
    @FXML public Button correctMeButton;
    @FXML public TextField country;
    @FXML public TextField state;
    @FXML public TextField city;
    @FXML public Button exitButton;
    private static final String DEFAULT_LANGUAGE = "en-US";
    private static final String CORRECT_ADDRESS_API_URL = "https://reloadedd.me:5443/api/correct-this-address";

    public void correctAddress(ActionEvent actionEvent) {
        if (!this.validateUserInputFromFields()) {
            return;
        }

        final RestTemplate restTemplate = new RestTemplateBuilder().build();
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.put("country", Collections.singletonList(country.getText()));
        map.put("state", Collections.singletonList(state.getText()));
        map.put("city", Collections.singletonList(city.getText()));
        map.put("language", Collections.singletonList(DEFAULT_LANGUAGE));

        /* Set Content-Type header to JSON */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        HereMapsAddressEntity response = restTemplate.postForObject(CORRECT_ADDRESS_API_URL, request,
                HereMapsAddressEntity.class);

        assert response != null;

        /* Set up an alert message informing the user about the new corrected address */
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeaderText("Thinking...Aaand done!");
        alert.setContentText("Our super intelligent systems found and corrected your address to:\n\t" +
                response.constructAddress());
        alert.show();
    }

    private boolean validateUserInputFromFields() {
        if (country.getText().isEmpty() || state.getText().isEmpty() || city.getText().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Oops...");
            errorAlert.setContentText("All fields must be completed.");
            errorAlert.showAndWait();

            return false;
        }

        return true;
    }

    public void exitButtonPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}
