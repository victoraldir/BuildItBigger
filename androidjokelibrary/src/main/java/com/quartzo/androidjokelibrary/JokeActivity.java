package com.quartzo.androidjokelibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "extraJoke";
    private TextView jokeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jokeTextView = (TextView) findViewById(R.id.tv_joke);

        if(!getIntent().getStringExtra(EXTRA_JOKE).isEmpty()){
            jokeTextView.setText(getIntent().getStringExtra(EXTRA_JOKE));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;

    }

}
