package com.callproject.zak_dev.callproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class calling extends AppCompatActivity {
    public MediaPlayer player;

    private InterstitialAd myInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        //backgroundMusic
        player  = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
        //backgroundMusicEnd
    }

    public void acceptCallEvent(View view){
        Intent acceptCall = new Intent(this,dialing.class);
        startActivity(acceptCall);
        player.stop();
        finish();

    }

    public void denyCall(View view){
        //interstitialAdLoader
        myInterstitialAd = new InterstitialAd(this);
        myInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        myInterstitialAd.loadAd(new AdRequest.Builder().build());
        //interstitialAdLoaderEnd
        player.stop();
        myInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int errorCode){
                finish();
            }

            @Override
            public void onAdLoaded(){
                myInterstitialAd.show();

            }
            @Override
            public void onAdClosed() {
                finish();
            }
        });



    }

}
