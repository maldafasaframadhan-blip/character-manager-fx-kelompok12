
package com.unpri.charactermanager;

import com.unpri.charactermanager.controller.CharacterController;
import com.unpri.charactermanager.model.CharacterManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CharacterManagerApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
                "/com/unpri/charactermanager/view/CharacterView.fxml"
            )
        );

        Parent root = loader.load();

        CharacterController controller = loader.getController();
        controller.setManager(new CharacterManager());

        Scene scene = new Scene(root);

        stage.setTitle("Character Manager FX - Kelompok 12");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(450);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
