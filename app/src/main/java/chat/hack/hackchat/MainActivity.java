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

public class MainActivity extends Activity {
    EditText etChatroom;
    Button bEnter;
    public static final String prefsFile = "chatroomNicknameFile";
    public static final String KEY_CHATROOM = "lastChatroom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etChatroom = (EditText) findViewById(R.id.etChatroom);

        Uri data = getIntent().getData();

        // "if" is executed when app is launched from a link; otherwise else is executed
        if (getIntent() != null && data != null) {
            String url = data.getEncodedSchemeSpecificPart();
            if(url.indexOf('?') != - 1) {
                String chatroom = url.substring(url.lastIndexOf('?') + 1);
                etChatroom.setText(chatroom);
            }
        } else {
            SharedPreferences prefs = getSharedPreferences(prefsFile, MODE_PRIVATE);
            String lastChatroom = prefs.getString(KEY_CHATROOM, "");

            if (lastChatroom.length() > 0) {
                etChatroom.setText(lastChatroom);
            }
        }

        etChatroom = (EditText) findViewById(R.id.etChatroom);
        bEnter = (Button) findViewById(R.id.bEnter);

        bEnter.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (hasInternet() == false) {
                    Toast.makeText(MainActivity.this, "No internet connection!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etChatroom.getText().toString().trim().equals("")) {
                    return;
                }

                Intent i = new Intent(getBaseContext(), Chat.class);
                i.putExtra(KEY_CHATROOM, etChatroom.getText().toString().trim());
                startActivity(i);
            }
        });
    }

    private boolean hasInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
