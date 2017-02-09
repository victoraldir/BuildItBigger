package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.quartzo.androidjokelibrary.JokeActivity;
import com.udacity.gradle.builditbigger.FetchJokeAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, FetchJokeAsyncTask.OnJokeAsyncTaskListener {

    private Button mBtnShowJoke;
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        mBtnShowJoke = (Button) root.findViewById(R.id.btn_joke);
        mBtnShowJoke.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {

        if(mProgressBar.getVisibility() == View.INVISIBLE)
            new FetchJokeAsyncTask(this).execute(getActivity());

        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onJokeReceived(String joke) {

        mProgressBar.setVisibility(View.INVISIBLE);

        if(!joke.isEmpty()) {

            Intent it = new Intent(getActivity(), JokeActivity.class);
            it.putExtra(JokeActivity.EXTRA_JOKE, joke);
            startActivity(it);
        }else{
            Toast.makeText(getActivity(),getString(R.string.error),Toast.LENGTH_SHORT).show();
        }


    }
}
