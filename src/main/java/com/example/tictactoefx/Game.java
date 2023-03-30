package com.example.tictactoefx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

public class Game {

    public CurrentGame game;
    @FXML
    private AnchorPane paneGame;
    @FXML
    private Label StepText;
    @FXML
    private Text ScorePlayerText1;
    @FXML
    private Text ScorePlayerText2;

    @FXML
   private Text GameText;
    @FXML
    private Button butonKey;

    private Player currentPlayerThis;

    @FXML
    protected void onGameButtonClick(ActionEvent actionEvent) {
        // actionEvent вертає ссилку на обєкт. Можна зробити через Nodeа або перетворити в обєкт Button
        // це буде теж саме
        // final Node source = (Node) actionEvent.getSource();
        // String id = source.getId();


       // Button butonKey = (Button) actionEvent.getSource();

//        paneGame = (AnchorPane) butonKey.getParent();
//        Object Children = paneGame.getChildren().get(10);
         // Button Children2 = (Button)  paneGame.getChildren().get(1);



//        Object Children3 = paneGame.getChildren().get(2);

        //paneGame.getChildren().

        if (game != null && game.step < 10){

            Button btn=(Button) actionEvent.getSource();
            String id = btn.getId();
            char row = id.charAt(3);
            char col = id.charAt(4);

            int row_int = Character.digit(row, 10) - 1;
            int col_int = Character.digit(col, 10) - 1;

            game.arrTic[row_int][col_int] = game.currentPlayer;
            btn.setText(game.currentPlayer.sign);

            boolean result = game.payoutСheck(game.currentPlayer, game.arrTic);

            if (result == true){
                GameText.setText("Вітаю!!!!! Виграв гравець: ");
                game.currentPlayer.score += 1;
                GameText.setFill(Color.RED);
                StepText.setText(game.currentPlayer.name);
                ScorePlayerText1.setText(Player.player1.name + ": " + Player.player1.score);
                ScorePlayerText2.setText(Player.player2.name + ": " + Player.player2.score);
                currentPlayerThis = game.currentPlayer;
                Updateform();
                game = null;
                return;
            }

            if (game.currentPlayer == Player.player1){
                game.currentPlayer = Player.player2;
            }else if (game.currentPlayer == Player.player2){
                game.currentPlayer = Player.player1;
            }

            Updateform();

            currentPlayerThis = game.currentPlayer;

            if (game.step == 9){
                GameText.setText("Ігру заваршено, виграла дружба");
                GameText.setFill(Color.RED);
                StepText.setText("");
                game = null;
                Updateform();
                return;
            }

            game.step += 1;
        }

    }


    @FXML
    protected void onPlayTwo(){

        game = new CurrentGame();
        Player.player1.score = 0;
        Player.player2.score = 0;
        ScorePlayerText1.setText(Player.player1.name + ": " + Player.player1.score);
        ScorePlayerText2.setText(Player.player2.name + ": " + Player.player2.score);
        UpdateDefaulform();
        Updateform();

    }

    @FXML
    protected void onPlayContinue() {

        game = new CurrentGame();
        game.currentPlayer = currentPlayerThis;
        Updateform();
        UpdateDefaulform();

    }

    @FXML
    public void Updateform(){

        StepText.setText(game.currentPlayer.name);
        ScorePlayerText1.setText(Player.player1.name + ": " + Player.player1.score);
        ScorePlayerText2.setText(Player.player2.name + ": " + Player.player2.score);

    }

    @FXML
    public void UpdateDefaulform(){

        GameText.setText("Хід гравця: ");
        GameText.setFill(Color.BLACK);

        List ListForm = paneGame.getChildren();

        ListIterator listIterator = ListForm.listIterator();

        while(listIterator.hasNext()){
            Object el = listIterator.next();

            Class classEl = el.getClass();
            Class emptyClassButton = new Button().getClass();

            if (emptyClassButton == classEl){

                String id = ((Button) el).getId();

                if (id != null) {
                    String key = id.substring(0, 3);

                    if ((key.equals("key"))) {
                        ((Button) el).setText("");
                    }
                }
            }
        }
    }

    @FXML
    protected void onPressSetting() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("setting.fxml"));

        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

    @FXML
    protected void onExit() throws IOException {

        System.exit(0);

    }

    @FXML
    public void onInfo(ActionEvent actionEvent){

        Stage stage = (Stage) paneGame.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.INFORMATION;

        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.setTitle("Інформація про ігру");
        alert.getDialogPane().setHeaderText(null);
        alert.getDialogPane().setContentText("Хрестики - нулики\nІгру розробив Світич Володимир");
        alert.showAndWait();

    }

    @FXML
    void initialize() {

        Player.player1.name = Player.ReadFileSetting()[0];
        Player.player1.sign = "X";
        Player.player2.name = Player.ReadFileSetting()[1];
        Player.player2.sign = "O";

        ScorePlayerText1.setText(Player.player1.name + ": " + Player.player1.score);
        ScorePlayerText2.setText(Player.player2.name + ": " + Player.player2.score);

    }

}