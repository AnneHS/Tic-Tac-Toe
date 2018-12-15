package com.example.anneh.tictactoe;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Game game;
    TileState state;

    // Initialize game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
    }

    // Save instance state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Game", game);
    }

    // Restore layout (game progress) when activity is reloaded
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("Game");

            // Restore Text for each button, one button at a time
            int counter = 0;
            for(int row=0; row<3; row++)
                for (int col = 0; col < 3; col++) {

                    counter += 1;

                    // Get reference to button
                    Button btn = null;
                    switch(counter) {
                        case 1:         btn = findViewById(R.id.one);
                                        break;
                        case 2:         btn = findViewById(R.id.two);
                                        break;
                        case 3:         btn = findViewById(R.id.three);
                                        break;
                        case 4:         btn = findViewById(R.id.four);
                                        break;
                        case 5:         btn = findViewById(R.id.five);
                                        break;
                        case 6:         btn = findViewById(R.id.six);
                                        break;
                        case 7:         btn = findViewById(R.id.seven);
                                        break;
                        case 8:         btn = findViewById(R.id.eight);
                                        break;
                        case 9:         btn = findViewById(R.id.nine);
                                        break;
                    }

                    // Set text to correspond with saved TileState
                    TileState saved = game.save(row, col);
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

        // Get reference to clicked button
        Button btn = findViewById(view.getId());

        // Get corresponding TileState
        switch(view.getId()) {
            case R.id.one:          state = game.choose(0, 0);
                                    break;
            case R.id.two:          state = game.choose(0, 1);
                                    break;
            case R.id.three:        state = game.choose(0, 2);
                                    break;
            case R.id.four:         state = game.choose(1, 0);
                                    break;
            case R.id.five:         state = game.choose(1, 1);
                                    break;
            case R.id.six:          state = game.choose(1, 2);
                                    break;
            case R.id.seven:        state = game.choose(2, 0);
                                    break;
            case R.id.eight:        state = game.choose(2, 1);
                                    break;
            case R.id.nine:         state = game.choose(2, 2);
                                    break;
        }

        // Set button text
        switch(state) {
            case CROSS:     btn.setText("X");
                            break;
            case CIRCLE:    btn.setText("0");
                            break;
            case INVALID:   Toast.makeText(getApplicationContext(), "Invalid move", Toast.LENGTH_SHORT).show();
                            break;
        }

        GameState game_state = game.won();

        // Display toast when game has ended
        Toast toast;
        switch(game_state) {
            case PLAYER_ONE:    toast = Toast.makeText(getApplicationContext(), "P1 won", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                break;
            case PLAYER_TWO:    toast = Toast.makeText(getApplicationContext(), "P2 won", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                break;
            case DRAW:          toast = Toast.makeText(getApplicationContext(), "DRAW", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                break;
        }
    }

    // Reset game
    public void resetClicked(View view) {
        game = new Game();
        setContentView(R.layout.activity_main);
    }
}