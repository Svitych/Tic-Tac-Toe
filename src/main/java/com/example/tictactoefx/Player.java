package com.example.tictactoefx;

import javafx.stage.Stage;

import java.io.*;

public class Player {

    public String name;
    public String sign;

    public int score;
    public static Player player1 = new Player("Гравець 1", "Х");
    public static Player player2 = new Player("Гравець 2", "O");

    public Player(){}
    public Player(String name, String sign){
        this.name = name;
        this.sign = sign;
    }

    public static String[]  ReadFileSetting() {

        String[] Arr = {"Гравець 1", "Гравець 2"};

        try {
            BufferedReader br = new BufferedReader(new FileReader("Setting.ini"));
            Arr[0] = br.readLine();
            Arr[1] = br.readLine();

        }catch (IOException e){}

        return Arr;
    }

    public static void WriteFileSetting(String Player1, String Player2){

        //String property = "java.io.tmpdir";
        //String tempDir = System.getProperty(property);
        //System.out.println("OS temporary directory is " + tempDir);

        try {
            File file = new File("Setting.ini");

            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter pw = new PrintWriter(file);
            pw.println(Player1);
            pw.println(Player2);
            pw.close();

        }catch(Exception e){}

    }

}
