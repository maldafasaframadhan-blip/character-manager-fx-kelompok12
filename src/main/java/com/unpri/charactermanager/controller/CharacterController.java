
package com.unpri.charactermanager.controller;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CharacterController {

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<CharacterType> typeChoiceBox;

    @FXML
    private TextField levelField;

    @FXML
    private Button saveButton;

    @FXML
    private Button resetButton;

    @FXML
    private VBox characterCardArea;

    @FXML
    private Label statusBar;

    @FXML
    private Label averageLevelLabel;

    private CharacterManager manager;

    public void setManager(CharacterManager manager) {
        this.manager = manager;
        refreshCharacters();
    }

    @FXML
    public void initialize() {
        typeChoiceBox.getItems().setAll(CharacterType.values());
        typeChoiceBox.setValue(CharacterType.WARRIOR);
    }

    @FXML
    private void handleSave() {

        if (manager == null) {
            statusBar.setText("Manager belum diinisialisasi.");
            return;
        }

        String name = nameField.getText().trim();
        CharacterType type = typeChoiceBox.getValue();
        String levelText = levelField.getText().trim();

        if (name.isEmpty()) {
            statusBar.setText("Nama karakter tidak boleh kosong.");
            return;
        }

        if (type == null) {
            statusBar.setText("Pilih tipe karakter.");
            return;
        }

        if (levelText.isEmpty()) {
            statusBar.setText("Level harus diisi.");
            return;
        }

        try {
            int level = Integer.parseInt(levelText);

            if (manager.isNameTaken(name)) {
                statusBar.setText("Nama karakter sudah digunakan.");
                return;
            }

            Character character = new Character(name, type, level);
            manager.addCharacter(character);

            statusBar.setText("Karakter " + name + " berhasil ditambahkan.");

            resetForm();
            refreshCharacters();

        } catch (NumberFormatException e) {
            statusBar.setText("Level harus berupa angka.");
        } catch (IllegalArgumentException e) {
            statusBar.setText(e.getMessage());
        }
    }

    @FXML
    private void handleReset() {
        resetForm();
        statusBar.setText("Form berhasil direset.");
    }

    private void resetForm() {
        nameField.clear();
        typeChoiceBox.setValue(CharacterType.WARRIOR);
        levelField.clear();
    }

    private void refreshCharacters() {

        if (manager == null) {
            return;
        }

        characterCardArea.getChildren().clear();

        for (Character character : manager.getAllCharacters()) {

            Label characterLabel = new Label(
                character.getName()
                + " | " + character.getType()
                + " | Level " + character.getLevel()
                + " | HP " + character.getHitPoints()
                + " | Attack " + character.getAttack()
                + " | Mana " + character.getMana()
            );

            characterLabel.setStyle(
                "-fx-text-fill: white;"
                + "-fx-background-color: #263449;"
                + "-fx-padding: 15;"
                + "-fx-background-radius: 8;"
                + "-fx-font-size: 14px;"
            );

            characterLabel.setMaxWidth(Double.MAX_VALUE);

            characterCardArea.getChildren().add(characterLabel);
        }

        averageLevelLabel.setText(
            String.format(
                "Rata-rata level: %.1f",
                manager.getAverageLevel()
            )
        );
    }
}
