package com.quartzo.androidjokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "extraJoke";
    private TextView jokeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeTextView = (TextView) findViewById(R.id.tv_joke);

        if(!getIntent().getStringExtra(EXTRA_JOKE).isEmpty()){

            jokeTextView.setText(getIntent().getStringExtra(EXTRA_JOKE));

        }

    }
}
