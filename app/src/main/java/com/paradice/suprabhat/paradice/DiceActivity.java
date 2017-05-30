package com.paradice.suprabhat.paradice;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class DiceActivity extends AppCompatActivity {
    final String TAG = "DiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Roller.INSTANCE.populateCache();
        setContentView(R.layout.dice_layout);

        final TextView diceTextView = (TextView) findViewById(R.id.diceTextView);
        final TextView seekBarMinTextView = (TextView) findViewById(R.id.seekBarMinLabel);
        final TextView seekBarMaxTextView = (TextView) findViewById(R.id.seekBarMaxLabel);

        final DiscreteSeekBar seekBar = (DiscreteSeekBar) findViewById(R.id.seekBar);

        seekBarMinTextView.setText(String.valueOf(seekBar.getMin()));
        seekBarMaxTextView.setText(String.valueOf(seekBar.getMax()));

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.dice_constraint_layout);

        constraintLayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        diceTextView.setText(Roller.INSTANCE.getMultipleDiceRolls(seekBar.getProgress()));
                        diceTextView.setTextSize(COMPLEX_UNIT_SP, 96);
                        Log.d(TAG, "Requested roll.");
                    }
                }
        );
    }
}
