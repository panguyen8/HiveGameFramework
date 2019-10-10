package com.example.hivegamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    final EditText theText = findViewById(R.id.EditText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                //each method requires a String to be appended to the editText object
                theText.append("randomString");

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
        });

    }

    public Boolean check(HiveGameState stateOne, HiveGameState stateTwo) {
        if(stateOne.toString() == stateTwo.toString()) {
            return true;
        }
        return false;
    }
}
