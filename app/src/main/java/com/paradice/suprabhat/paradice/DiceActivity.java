package com.paradice.suprabhat.paradice;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_layout);

        final TextView textView = (TextView) findViewById(R.id.textView);

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.dice_constraint_layout);

        constraintLayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText(String.valueOf(APIClient.getRandomNumber(6)));
                    }
                }
        );
    }
}
