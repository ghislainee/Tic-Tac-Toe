package com.example.tictactoe.src.TicTacToe;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacModel {
    private List<TicTacToe.Observer<TicTacModel, String>> observers;
    private ArrayList<Integer> playerMoves = new ArrayList<>();
    private ArrayList<Integer> cpuMoves = new ArrayList<>();
    private String[][] board = new String[3][3];
    private boolean isGameOver = false;
    private int player = 1;

    public TicTacModel(){
        observers = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void select(int spot){
        if (playerMoves.contains(spot) || cpuMoves.contains(spot))
            announce("Illegal move");
        else if (!isGameOver)
            putPiece(spot, "X");  // PLACE THE PLAYER X ON BOARD

        Random rand = new Random();
        int cpuPlayer = rand.nextInt(9) + 1;
        while (playerMoves.contains(cpuPlayer) || cpuMoves.contains(cpuPlayer)){
            if (playerMoves.size() + cpuMoves.size() >= 9){
                isGameOver = true;
                announce("GAME OVER");
                break;
            }

            cpuPlayer = rand.nextInt(9) + 1;
        }
        if (!isGameOver){
            putPiece(cpuPlayer, "O"); // CPU MOVE
            announce("Next move!");
            checkWinner();
        }
        else
            announce("GAME OVER!");

    }

    private void putPiece(int spot, String piece){
        if (playerMoves.contains(spot) || cpuMoves.contains(spot)){
        }
        else {
            switch (spot) {
                case 1:
                    board[0][0] = piece;
                    break;
                case 2:
                    board[0][1] = piece;
                    break;
                case 3:
                    board[0][2] = piece;
                    break;
                case 4:
                    board[1][0] = piece;
                    break;
                case 5:
                    board[1][1] = piece;
                    break;
                case 6:
                    board[1][2] = piece;
                    break;
                case 7:
                    board[2][0] = piece;
                    break;
                case 8:
                    board[2][1] = piece;
                    break;
                case 9:
                    board[2][2] = piece;
                    break;
            }
            if (piece.equals("X"))
                playerMoves.add(spot);
            else if (piece.equals("O"))
                cpuMoves.add(spot);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkWinner(){
        List<Integer> row1 = List.of(1, 2, 3);
        List<Integer> row2 = List.of(4, 5, 6);
        List<Integer> row3 = List.of(7, 8, 9);

        List<Integer> col1 = List.of(1, 4, 7);
        List<Integer> col2 = List.of(2, 5, 8);
        List<Integer> col3 = List.of(3, 6, 9);

        List<Integer> cross1 = List.of(1, 5, 9);
        List<Integer> cross2 = List.of(3, 5, 7);

        List<List<Integer>> winnerMoves = List.of(row1, row2, row3, col1, col2, col3, cross1, cross2);
        for (List list: winnerMoves){
            if (playerMoves.containsAll(list)){
                isGameOver = true;
                announce("PLAYER WINS!");
                break;
            }
            else if (cpuMoves.containsAll(list)){
                isGameOver = true;
                announce("CPU WINS!");
                break;
            }
            else if (playerMoves.size() + cpuMoves.size() == 9){
                isGameOver = true;
                announce("ITS A TIE!");
                break;
            }
        }
    }

    public void reset(){
        board = new String[3][3];
        playerMoves = new ArrayList<>();
        cpuMoves = new ArrayList<>();
        isGameOver = false;
        announce("NEW GAME!");
    }

    public String[][] getBoard() {
        return board;
    }

    public void addObserver( TicTacToe.Observer< TicTacModel, String > obs ) {
        this.observers.add( obs );
    }

    private void announce(String arg ) {
        for ( TicTacToe.Observer<TicTacModel, String> obs : this.observers ) {
            obs.update( this, arg );
        }
    }

}


