package com.example.tictactoefx;

public class CurrentGame {

    public  Player[][] arrTic = new Player[3][3];
    public Player currentPlayer;
    public int step;

    public CurrentGame(){

        currentPlayer = Player.player1;
        step = 1;

    }

    public static boolean payoutСheck(Player player, Player[][] arrTic){

        boolean returnValue = false;

        for (int i = 0; i < 3; i++) {

            if (arrTic[i][0] == player && arrTic[i][1] == player && arrTic[i][2] == player) {
                returnValue = true;
                break;
            }
        }

        for (int i = 0; i < 3; i++) {

            if (arrTic[0][i] == player && arrTic[1][i] == player && arrTic[2][i] == player) {
                returnValue = true;
                break;
            }
        }

        if (arrTic[0][0] == player && arrTic[1][1] == player && arrTic[2][2] == player) {
            returnValue = true;
        }

        if (arrTic[2][0] == player && arrTic[1][1] == player && arrTic[0][2] == player) {
            returnValue = true;
        }

        return returnValue;
    }

}

