package chat.hack.hackchat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import chat.hack.hackchat.chat.hack.hackchat.ads.HackChatInterstitialAd;
import chat.hack.hackchat.chat.hack.hackchat.ads.HackChatInterstitialAdvertable;

public class MainActivity extends Activity implements HackChatInterstitialAdvertable {
    public static final String prefsFile = "chatroomNicknameFile";
    public static final String KEY_CHATROOM = "lastChatroom";

    private EditText etChatroom;
    private Button bEnter;

    private HackChatInterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        interstitialAd = new HackChatInterstitialAd(this, getResources().getString(R.string.homeScreenAdUniId));

        setContentView(R.layout.activity_main);

        etChatroom = (EditText) findViewById(R.id.etChatroom);
        bEnter = (Button) findViewById(R.id.bEnter);

        Uri data = getIntent().getData();

        // "if" is executed when app is launched from a link; otherwise else is executed
        if (getIntent() != null && data != null) {
            etChatroom.setText(extractReferredChatroomName(data));
        } else {
            etChatroom.setText(fetchLastChatroomName());
        }

        bEnter.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // Do nothing if there is no internet
                if (hasInternet() == false) {
                    Toast.makeText(MainActivity.this, "No internet connection!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Do nothing if input chatroom is empty
                if (etChatroom.getText().toString().trim().equals("")) {
                    return;
                }

                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    startChatActivity();
                }
            }
        });
    }

    /**
     * Sets the chatroom name that was last used. Or, blank if
     * no last chatroom name is found.
     */
    private String fetchLastChatroomName() {
        SharedPreferences prefs = getSharedPreferences(prefsFile, MODE_PRIVATE);
        return prefs.getString(KEY_CHATROOM, "");
    }

    /**
     * Returns the chatroom name from the referred URI data provided.
     */
    private String extractReferredChatroomName(Uri data) {
        String url = data.getEncodedSchemeSpecificPart();
        if (url.indexOf('?') != -1) {
            return url.substring(url.lastIndexOf('?') + 1);
        }
        return "";
    }

    @Override
    public void onInterstitialAdClosed() {
        startChatActivity();
    }

    /**
     * Starts the chat activity.
     */
    private void startChatActivity() {
        Intent i = new Intent(getBaseContext(), Chat.class);
        i.putExtra(KEY_CHATROOM, etChatroom.getText().toString().trim());
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        interstitialAd.requestInterstitialAd();
    }

    /**
     * Checks whether internet is available on the device.
     */
    private boolean hasInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
