package chat.hack.hackchat.chat.hack.hackchat.ads;

import android.app.Activity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Helper class to display interstitial ads across the project.
 *
 * Created by rudra on 31/12/16.
 */

public class HackChatInterstitialAd {
    private InterstitialAd interstitialAd;

    private HackChatInterstitialAdvertable advertable;
    private String adUnitId;

    public HackChatInterstitialAd(HackChatInterstitialAdvertable advertable, String adUnitId) {
        this.advertable = advertable;
        this.adUnitId = adUnitId;

        try {
            setUpInterstitialAd();
        } catch (Exception e) {
            // Welcome to the refactoring hell.
        }
    }

    /**
     * Sets up the interstitial ad object.
     */
    private void setUpInterstitialAd() throws Exception {
        if (!(advertable instanceof  Activity)) {
            throw new Exception("Only Activity class can implement HackChatInterstitialAdvertable.");
        }
        interstitialAd = new InterstitialAd((Activity) advertable);
        interstitialAd.setAdUnitId(adUnitId);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                advertable.onInterstitialAdClosed();
            }
        });

        requestInterstitialAd();
    }

    /**
     * Requests a new interstitial ad and loads to same for displaying.
     */
    public void requestInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }

    /**
     * Returns whether the ad has been loaded.
     */
    public boolean isLoaded() {
        return interstitialAd.isLoaded();
    }

    /**
     * Displays the ad.
     */
    public void show() {
        interstitialAd.show();
    }
}
