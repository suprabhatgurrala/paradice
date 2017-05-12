package com.paradice.suprabhat.paradice;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class DiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_layout);

        final TextView diceTextView = (TextView) findViewById(R.id.diceTextView);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressVal, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.dice_constraint_layout);

        constraintLayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        diceTextView.setText(APIClient.getMultipleDiceRolls(seekBar.getProgress() + 1));
                        diceTextView.setTextSize(COMPLEX_UNIT_SP, 96);
                    }
                }
        );
    }
}
