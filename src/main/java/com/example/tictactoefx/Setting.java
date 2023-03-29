package com.example.tictactoefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;

public class Setting {

    @FXML
    private TextField setPlayer1;
    @FXML
    private TextField setPlayer2;

    @FXML
    Button exit;

    @FXML
    public void onSettingExit(ActionEvent actionEvent) {

        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();

    }


    @FXML
    void initialize() {

        setPlayer1.setText( Player.player1.name);
        setPlayer2.setText( Player.player2.name);

    }

    @FXML
    public void onSaveSetting(ActionEvent actionEvent) {

        Player.WriteFileSetting(setPlayer1.getText(),setPlayer2.getText());

        Player.player1.name = setPlayer1.getText();
        Player.player2.name = setPlayer2.getText();

        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();

    }
}
