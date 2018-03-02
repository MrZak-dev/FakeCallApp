package com.callproject.zak_dev.callproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class dialing extends AppCompatActivity {
    private InterstitialAd myInterstitialAd2;
    public Chronometer myChrono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialing);
        myChrono = (Chronometer) findViewById(R.id.chronometer2);
        myChrono.start();

    }
    public void quitCallEvent(View view) {
        //interstitialAdLoader
        myInterstitialAd2 = new InterstitialAd(this);
        myInterstitialAd2.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        myInterstitialAd2.loadAd(new AdRequest.Builder().build());
        //interstitialAdLoaderEnd

        //displayInterstitialAfterClosing
        myInterstitialAd2.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int errorCode){
                finish();
            }
            @Override
            public void onAdLoaded(){
                myInterstitialAd2.show();
            }
            @Override
            public void onAdClosed(){
                finish();
            }

        });
        //displayInterstitialAfterClosing/>
    }
}
