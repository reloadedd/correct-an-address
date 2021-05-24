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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


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

    public void correctAddress(ActionEvent actionEvent) {
        if (!this.validateUserInputFromFields()) {
            return;
        }

        System.out.println("country is" + country.getText());
        System.out.println("state is" + state.getText());
        System.out.println("city is" + city.getText());
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
