package com.example.hivegamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText theText;

    // Below citation by Samuel Nguyen
    /**
     * External Citation
     * Date: October 10, 2019
     * Problem: I needed to access an element at an index in the bugList ArrayList.
     * Resource: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
     * Solution: I used the get() function.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theText = (EditText) findViewById(R.id.EditText);

        //initialize HiveGameState
        HiveGameState hiveGameState = new HiveGameState();

        //initialize the the Run Test button
        Button runTestButton = findViewById(R.id.runTestButton);

        runTestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //clears the text once clicked
                theText.setText("");

                //the first instance of the game created
                HiveGameState firstInstance = new HiveGameState();

                //second instance created by deep copy from first instance
                HiveGameState secondInstance = new HiveGameState(firstInstance);

                //call all methods of firstInstance
                //each method also requires a String to be appended to the editText object
                firstInstance.placePiece(0, firstInstance.bugList.get(0), 50, 50);
                theText.append("Player 0 moved " + firstInstance.bugList.get(0)
                        + " to position [50][50]\n");
                firstInstance.movePiece(0, firstInstance.bugList.get(0), 50, 50, 60, 60);
                theText.append("Player 0 moved " + firstInstance.bugList.get(0) +
                        " to position [60][60] from [50][50]\n");
                firstInstance.undo(0);
                theText.append("Moves undone by player 0.\n");
                firstInstance.zoom(1);
                theText.append("Game was zoomed in by player 1.\n");
                int turn = firstInstance.getTurn();
                theText.append("Got the turn, result is " + turn +".\n");
                firstInstance.setTurn(0);
                theText.append("Set the turn to player 0's turn.\n");
                firstInstance.quit(1);
                theText.append("Game quit by player 1.\n");


                //the third instance of game created
                HiveGameState thirdInstance = new HiveGameState();

                //fourth instance deep copy made from the third instance
                HiveGameState fourthInstance = new HiveGameState(thirdInstance);

                //append second and fourth state strings to theText if both are equal
                if (check(secondInstance, fourthInstance)) {
                    theText.append(secondInstance.toString());
                    theText.append(fourthInstance.toString());
                }
            }
        }
        );

    }

    public Boolean check(HiveGameState stateOne, HiveGameState stateTwo) {
        if(stateOne.toString().equals(stateTwo.toString())) {
            return true;
        }
        return false;
    }
}
