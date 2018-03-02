package com.callproject.zak_dev.callproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    /*public void theCall() {
        callBtn = (ImageButton) findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCall = new Intent(MainActivity.this, calling.class);
                startActivity(startCall);
            }
        });
    }*/
    public RadioGroup rGroup;
    public int afterTime;
    public String waitingTime;
    private AdView myAdBanner;
    private InterstitialAd myInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //BannerAdLoader
        myAdBanner = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        myAdBanner.loadAd(adRequest);
        //BannerAdLoaderEnd








        //RadioBtn
        rGroup = (RadioGroup) findViewById(R.id.rBtnGroup);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.rBtnNow){
                    afterTime = 0;
                }
                else if (checkedId == R.id.rBtnOne){
                    afterTime = 15000;
                    waitingTime = "Wait 15 Second";
                }
                else if (checkedId == R.id.rBtnFive){
                    afterTime = 60000;
                    waitingTime = "Wait 1 minute";
                }

                else if (checkedId == R.id.rBtnThirty){
                    afterTime = 1800000;
                    waitingTime = "Wait 30 minutes";
                }
                else if (checkedId == R.id.rBtnHour){
                    afterTime = 3600000;
                    waitingTime = "Wait 1 hour";
                }
            }
        });

    }

    public void callBtnEvent(View view){

        final Intent callEvent = new Intent(this,calling.class);
        final Handler handler = new Handler();
        //interstitialAdLoader
        myInterstitialAd = new InterstitialAd(this);
        myInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        myInterstitialAd.loadAd(new AdRequest.Builder().build());
        //interstitialAdLoaderEnd


        myInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int errorCode){
                if (afterTime != 0){
                    Toast.makeText(getApplicationContext(),waitingTime,Toast.LENGTH_SHORT).show();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(callEvent);
                    }
                }, afterTime);
            }
            @Override
            public void onAdLoaded(){
                myInterstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                if (afterTime != 0){
                    Toast.makeText(getApplicationContext(),waitingTime,Toast.LENGTH_SHORT).show();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(callEvent);
                    }
                }, afterTime);
            }


        });




    }


}
