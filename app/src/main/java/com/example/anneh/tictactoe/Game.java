package com.example.anneh.tictactoe;

import java.io.Serializable;
import android.util.Log;

public class Game implements Serializable {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    // To keep track of who's turn it is and whether the game has ended
    private Boolean playerOneTurn;
    private Boolean gameOver;

    // Create a new Game where all TileStates are initialized to BLANK
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++) {
            for(int j=0; j<BOARD_SIZE; j++) {
                board[i][j] = TileState.BLANK;
            }
        }

        playerOneTurn = true;
        gameOver = false;
    }

    // Choose new TileState for clicked button based on who's turn it is
    public TileState choose(int row, int column) {

        if (board[row][column] == TileState.BLANK && gameOver != true) {
            if (playerOneTurn == true) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return board[row][column]; // OF OOK TILESTATE?
            }
            else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;

                return board[row][column];
            }
        }
        else {
            return TileState.INVALID;
        }
    }

    public TileState save(int row, int col) {
        return board[row][col];
    }


    // Checks whether game has been won and by whom
    public GameState won() {

        TileState cross = TileState.CROSS;
        TileState circle = TileState.CIRCLE;

        // Checks whether one of the rows or columns is XXX or 000
        for (int i = 0; i < BOARD_SIZE; i++) {

            // ROWS
            if (board[i][0] == cross && board[i][1] == cross && board[i][2] == cross) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            else if (board[i][0] == circle && board[i][1] == circle && board[i][2] == circle) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }

            // COLUMNS
            if (board[0][i] == cross && board[1][i] == cross && board[2][i] == cross) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            else if (board[0][i] == circle && board[1][i] == circle && board[2][i] == circle) {
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        }

        // DIAGONAL
        if (board[0][0] == cross && board[1][1] == cross && board[2][2] == cross) {
            gameOver = true;
            return GameState.PLAYER_ONE;
        }
        else if (board[0][0] == circle && board[1][1] == circle && board[2][2] == circle) {
            gameOver = true;
            return GameState.PLAYER_TWO;
        }
        if (board[0][2] == cross && board[1][1] == cross && board[2][0] == cross) {
            gameOver = true;
            return GameState.PLAYER_ONE;
        }
        else if (board[0][2] == circle && board[1][1] == circle && board[2][0] == circle) {
            gameOver = true;
            return GameState.PLAYER_TWO;
        }

        // Checks whether there are blank tiles available
        boolean blank_tile = false;
        for(int i=0; i<BOARD_SIZE; i++) {
            for(int j=0; j<BOARD_SIZE; j++) {
                if (board[i][j] == TileState.BLANK){
                     blank_tile = true;
                }
            }
        }

        // Return draw if no further moves possible
        if (blank_tile == false) {

            gameOver = true;
            return GameState.DRAW;
        }
        else {
            return GameState.IN_PROGRESS;
        }
    }
}
