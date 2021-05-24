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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;


/**
 * Starting point of the JavaFX Application.
 *
 * @author  Ionuț Roșca
 * @version 0.1.0
 */
public class Main extends Application {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final String TITLE = "? Correct an Address";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome_stage.fxml")));
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
