package com.example.myapplicationabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class finish extends AppCompatActivity {
    public void main(View view){
        Intent intent = new Intent(this,ActualQuiz.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        TextView score = findViewById(R.id.textView9);
        score.setText(ActualQuiz.idk);
    }
}