package com.example.myapplicationabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    static String a;
    public void buttonClick(View view){
        Button button = findViewById(R.id.button2);
        TextView txt  =findViewById(R.id.Nametext);
        if(txt.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Your Name!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Well "+txt.getText().toString()+"......You're Dumb!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ActualQuiz.class);
            intent.putExtra("UserName",txt.getText().toString());
            intent.putExtra("QuesNo",seekBar.getProgress());
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            final WindowInsetsController insetsController = getWindow().getInsetsController();
//            if (insetsController != null) {
//                insetsController.hide(WindowInsets.Type.statusBars());
//            }
//        } else {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        }
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.Difficulty, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        a =spinner.getSelectedItem().toString().toLowerCase();
        final TextView NoOfQues = findViewById(R.id.NumberOfQuestion);
        seekBar= findViewById(R.id.seekBar);
        NoOfQues.setText("Number Of Questions:- "+ seekBar.getProgress());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                NoOfQues.setText("Number Of Questions:- "+ i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}