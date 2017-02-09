package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.quartzo.androidjokelibrary.JokeActivity;
import com.udacity.gradle.builditbigger.FetchJokeAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, FetchJokeAsyncTask.OnJokeAsyncTaskListener {

    private InterstitialAd mInterstitialAd;
    private Button mBtnShowJoke;
    private ProgressBar mProgressBar;

    private String jokeReceived;
    private boolean flagAdIsOpen = false;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        mBtnShowJoke = (Button) root.findViewById(R.id.btn_joke);
        mBtnShowJoke.setOnClickListener(this);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        AdManager adManager = new AdManager();

        mInterstitialAd.setAdListener(adManager);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAdRequest();
    }

    @Override
    public void onClick(View view) {

        showInterstitialAd();

        if(!mProgressBar.isActivated())
            new FetchJokeAsyncTask(this).execute(getActivity());

        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void showInterstitialAd(){
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

    @Override
    public void onJokeReceived(String joke) {

        jokeReceived = joke;
        mProgressBar.setVisibility(View.INVISIBLE);

        launchJokeActivity();

    }

    private void launchJokeActivity(){

        if(!flagAdIsOpen && !mProgressBar.isActivated()){

            if(!jokeReceived.isEmpty()) {

                Intent it = new Intent(getActivity(), JokeActivity.class);
                it.putExtra(JokeActivity.EXTRA_JOKE, jokeReceived);
                startActivity(it);

            }else{

                Toast.makeText(getActivity(),getString(R.string.error),Toast.LENGTH_SHORT).show();

            }

        }

    }

    private void loadAdRequest(){
        if(!mInterstitialAd.isLoading()) {
            AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
            mInterstitialAd.loadAd(request);
        }
    }

    public class AdManager extends AdListener{

        @Override
        public void onAdOpened() {
            super.onAdOpened();
            flagAdIsOpen = true;
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            flagAdIsOpen = false;
            launchJokeActivity();

        }
    }
}
