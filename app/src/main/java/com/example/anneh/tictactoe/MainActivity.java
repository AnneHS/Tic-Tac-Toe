package com.example.anneh.tictactoe;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // add variable game
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize game
        game = new Game();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Game", game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("Game");

            // counter to keep track of restored buttons
            int counter = 0;

            Button btn;

            // Restore Text for each button, one button at a time
            for(int row=0; row<3; row++)
                for (int col = 0; col < 3; col++) {

                    counter += 1;

                    /* Get corresponding button */
                    if (counter == 1) {
                        btn = findViewById(R.id.one);
                    } else if (counter == 2) {
                        btn = findViewById(R.id.two);
                    } else if (counter == 3) {
                        btn = findViewById(R.id.three);
                    } else if (counter == 4) {
                        btn = findViewById(R.id.four);
                    } else if (counter == 5) {
                        btn = findViewById(R.id.five);
                    } else if (counter == 6) {
                        btn = findViewById(R.id.six);
                    } else if (counter == 7) {
                        btn = findViewById(R.id.seven);
                    } else if (counter == 8) {
                        btn = findViewById(R.id.eight);
                    } else { // if r.id.9
                        btn = findViewById(R.id.nine);
                    }

                    // Get saved Tilestate for current button
                    TileState saved = game.save(row, col);

                    // Restore
                    switch (saved) {
                        case CROSS:     btn.setText("X");
                                        break;
                        case CIRCLE:    btn.setText("0");
                                        break;
                        case BLANK:     btn.setText("");
                                        break;
                    }
                }
        }
    }

    public void tileClicked(View view) {

        Button btn;
        int row;
        int col;

        // translate button ID into coordinates
        int id = view.getId();

        TileState state;
        if (id == R.id.one) {
            btn = findViewById(R.id.one); // of met (Button) ????
            state = game.choose(0, 0);
        } else if (id == R.id.two) {
            btn = findViewById(R.id.two);
            state = game.choose(0, 1);
        } else if (id == R.id.three) {
            btn = findViewById(R.id.three);
            state = game.choose(0, 2);
        } else if (id == R.id.four) {
            btn = findViewById(R.id.four);
            state = game.choose(1, 0);
        } else if (id == R.id.five) {
            btn = findViewById(R.id.five);
            state = game.choose(1, 1);
        } else if (id == R.id.six) {
            btn = findViewById(R.id.six);
            state = game.choose(1, 2);
        } else if (id == R.id.seven) {
            btn = findViewById(R.id.seven);
            state = game.choose(2, 0);
        } else if (id == R.id.eight) {
            btn = findViewById(R.id.eight);
            state = game.choose(2, 1);
        } else { // if r.id.9
            btn = findViewById(R.id.nine);
            state = game.choose(2, 2);
        }

        switch(state) {
            case CROSS:     btn.setText("X");
                            break;
            case CIRCLE:    btn.setText("0");
                            break;
            case INVALID:   Toast.makeText(getApplicationContext(), "Invalid move", Toast.LENGTH_SHORT).show();
                            break;
        }

        GameState game_state = game.won();

        switch(game_state) {
            case PLAYER_ONE:    Toast.makeText(getApplicationContext(), "P1 won", Toast.LENGTH_SHORT).show();
                                break;
            case PLAYER_TWO:    Toast.makeText(getApplicationContext(), "P2 won", Toast.LENGTH_SHORT).show();
                                break;
            case DRAW:          Toast.makeText(getApplicationContext(), "DRAW", Toast.LENGTH_SHORT).show();
                                break;
        }
    }

    // Reset game
    public void resetClicked(View view) {
        game = new Game();
        setContentView(R.layout.activity_main);
    }
}



/*
Button selected_button = (Button) findViewById(R.id.id);
For (int row = 0; row < 3; row++) {
    for (int col = 0; col < 3; col++) {
        if Grid[row][col] == selectedbutton;
            TileState state = game.choose(row, col);
*/