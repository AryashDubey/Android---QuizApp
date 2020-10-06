package com.example.myapplicationabc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class ActualQuiz extends AppCompatActivity {
    SeekBar seek;
    ArrayList<JSONObject> JSON;
    ArrayList<JSONArray> JSONIncorrect;
    ArrayList<String> JSONQuestions;
    ArrayList<String> JSONCorrect;
    Boolean isemp = true;
    Button ch1;
    TextView ques;
    int score;
    static String idk;
    TextView Scoree;
    TextView Progress;
    Button ch2;
    Button ch3;
    Button ch4;
    Button Sumbit;
    Random random = new Random();
    int a;
    int j;
    int quesNumber;
    public void Next(int i) throws JSONException {
        a =random.nextInt(4);
        ques.setText(JSONQuestions.get(i));
        switch (a){
            case 0:
                ch1.setText(JSONCorrect.get(i));
                ch2.setText(JSONIncorrect.get(i).get(0).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                ch3.setText(JSONIncorrect.get(i).get(1).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                ch4.setText(JSONIncorrect.get(i).get(2).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                break;
            case 1:
                ch2.setText(JSONCorrect.get(i));
                ch1.setText(JSONIncorrect.get(i).get(0).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                ch3.setText(JSONIncorrect.get(i).get(1).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                ch4.setText(JSONIncorrect.get(i).get(2).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                break;
            case 2:
                ch3.setText(JSONCorrect.get(i));
                ch2.setText(JSONIncorrect.get(i).get(0).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                ch1.setText(JSONIncorrect.get(i).get(1).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                ch4.setText(JSONIncorrect.get(i).get(2).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                break;
            case 3:
                ch4.setText(JSONCorrect.get(i));
                ch2.setText(JSONIncorrect.get(i).get(0).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                ch3.setText(JSONIncorrect.get(i).get(1).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                ch1.setText(JSONIncorrect.get(i).get(2).toString().replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                break;
        }
    }
    public void check(View view){

    }
    public void reset(){
        ch1.setBackgroundColor(Color.WHITE);
        ch1.setTextColor(Color.parseColor("#6200EE"));
        ch2.setBackgroundColor(Color.WHITE);
        ch2.setTextColor(Color.parseColor("#6200EE"));
        ch3.setBackgroundColor(Color.WHITE);
        ch3.setTextColor(Color.parseColor("#6200EE"));
        ch4.setBackgroundColor(Color.WHITE);
        ch4.setTextColor(Color.parseColor("#6200EE"));
        ch1.setTag("false");
        ch2.setTag("false");
        ch3.setTag("false");
        ch4.setTag("false");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_quiz);
        ques = findViewById(R.id.QuesTextView);
        final Intent intent = getIntent();
        score=0;
        Scoree=findViewById(R.id.textView5);
        ch1 = findViewById(R.id.Choice1);
        ch2 = findViewById(R.id.Choice2);
        ch3 = findViewById(R.id.Choice3);
        ch4 = findViewById(R.id.Choice4);
        seek= findViewById(R.id.seekBar2);
        seek.setEnabled(false);
        Sumbit = findViewById(R.id.Sumbit);
        Progress = findViewById(R.id.textView4);
        JSON=new ArrayList<>();
        JSONCorrect=new ArrayList<>();
        JSONIncorrect= new ArrayList<>();
        JSONQuestions=new ArrayList<>();
        quesNumber=intent.getIntExtra("QuesNo",10);
        seek.setMax(quesNumber);
        j= 0;

        reset();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://opentdb.com/api.php?amount="+quesNumber+"&difficulty="+MainActivity.a+"&type=multiple", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray;
                    jsonArray=response.getJSONArray("results");
                    Log.i("Yayyyyy","Yooooo"+jsonArray.toString());
                    for (int i =0;i<quesNumber;i++){
                        if (jsonArray.getJSONObject(i)!= null){
                            JSON.add(jsonArray.getJSONObject(i));
                            JSONQuestions.add(JSON.get(i).getString("question").replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                            JSONCorrect.add(JSON.get(i).getString("correct_answer").replace("&#039;","").replace("&quot;","").replace("&amp;","&").replace("&rsquo;","'"));
                            JSONIncorrect.add(JSON.get(i).getJSONArray("incorrect_answers"));
                            Log.i("!!!!!!!!",JSONQuestions.get(i));
                            Log.i("!!!!!!!!",JSONCorrect.get(i));
                            Log.i("!!!!!!!!",JSONIncorrect.get(i).toString());

                        }
                    }
                    try {
                        Next(j);
                        Scoree.setText("Score:- "+score +"/"+j);
                        Progress.setText(j+"/"+(quesNumber));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Sumbit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isemp=true;
                            if(j<=quesNumber) {
                                try {
                                    if(ch1.getTag()=="true"){
                                        if(ch1.getText()==JSONCorrect.get(j)){
                                            Toast.makeText(ActualQuiz.this, "Correct", Toast.LENGTH_SHORT).show();
                                            score++;
                                        }else{
                                            Toast.makeText(ActualQuiz.this, "No The Correct Answer Was :- "+JSONCorrect.get(j), Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(ch2.getTag()=="true"){
                                        if(ch2.getText()==JSONCorrect.get(j)){
                                            Toast.makeText(ActualQuiz.this, "Correct", Toast.LENGTH_SHORT).show();
                                            score++;
                                        }else{
                                            Toast.makeText(ActualQuiz.this, "No The Correct Answer Was :- "+JSONCorrect.get(j), Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(ch3.getTag()=="true"){
                                        if(ch3.getText()==JSONCorrect.get(j)){
                                            Toast.makeText(ActualQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
                                            score++;
                                        }else{
                                            Toast.makeText(ActualQuiz.this, "No The Correct Answer Was :- "+JSONCorrect.get(j), Toast.LENGTH_SHORT).show();
                                        }
                                    }else if(ch4.getTag()=="true"){
                                        if(ch4.getText()==JSONCorrect.get(j)){
                                            Toast.makeText(ActualQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
                                            score++;
                                        }else{
                                            Toast.makeText(ActualQuiz.this, "No The Correct Answer Was :- "+JSONCorrect.get(j), Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(ActualQuiz.this, "Please Select An Option", Toast.LENGTH_SHORT).show();
                                        isemp=false;
                                    }
                                    if(isemp){
                                    j++;
                                    if(j>=quesNumber){
                                        Intent intent1 = new Intent(getApplicationContext(),finish.class);
                                        idk=("Score:- "+score +"/"+j);
                                        intent.putExtra("Score",idk);
                                        Scoree.setText(idk);
                                        Progress.setText(j+"/"+(quesNumber));
                                        startActivity(intent1);
                                        finish();
                                    }else{
                                        idk=("Score:- "+score +"/"+j);
                                        Scoree.setText(idk);
                                        Progress.setText(j+"/"+(quesNumber));
                                        Next(j);
                                        seek.setProgress(seek.getProgress()+1);
                                        reset();}
                                    }



                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    Log.i("Yayyyyy","Yooooo"+JSON.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("NOOO","Yooooo");

            }
        });
        requestQueue.add(jsonObjectRequest);
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                ch1.setTextColor(Color.WHITE);
                ch1.setBackgroundColor(Color.parseColor("#6200ee"));
                ch1.setTag("true");
            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                ch2.setTextColor(Color.WHITE);
                ch2.setBackgroundColor(Color.parseColor("#6200ee"));
                ch2.setTag("true");
            }
        });
        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                ch3.setTextColor(Color.WHITE);
                ch3.setBackgroundColor(Color.parseColor("#6200ee"));
                ch3.setTag("true");
            }
        });
        ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                ch4.setTextColor(Color.WHITE);
                ch4.setBackgroundColor(Color.parseColor("#6200ee"));
                ch4.setTag("true");
            }
        });
    }
}