package com.example.freydis.drinklink.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.freydis.drinklink.R;
import com.example.freydis.drinklink.control.LoginAsyncTask;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Handles signing in and authenticating an account.
 * Connects to the Facebook SDK to fetch profile data.
 */

public class LoginActivity extends AppCompatActivity {

    // callbackManager encapsulates the callback that is called on login completion
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Profile profile;

    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.freydis.drinklink", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        attemptLogin();
    }

    private void attemptLogin() {
        callbackManager = CallbackManager.Factory.create();
        FacebookCallback<LoginResult> callback = getFacebookCallback();
        loginButton.registerCallback(callbackManager, callback);
    }

    private FacebookCallback<LoginResult> getFacebookCallback() {
        return new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {

                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            getFacebookData(object);
                        }
                    });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancelled Log-In to Facebook", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error while Logging-In to Facebook: "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();
        String id = object.optString("id");

        try {
            URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=200");
            bundle.putString("profile_pic", profile_pic.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        bundle.putString("user_id", id);
        if (object.has("first_name"))
            bundle.putString("first_name", object.optString("first_name"));
        if (object.has("last_name"))
            bundle.putString("last_name", object.optString("last_name"));
        if (object.has("email"))
            bundle.putString("email", object.optString("email"));
        if (object.has("birthday"))
            bundle.putString("birthday", object.optString("birthday"));

        authSuccess(bundle);
    }

    private void authSuccess(Bundle bundle) {

        String first_name = bundle.getString("first_name");
        String last_name = bundle.getString("last_name");
        String profile_pic = bundle.getString("profile_pic");
        String user_id = bundle.getString("user_id");
        String user_email = bundle.getString("email");
        String user_bday = bundle.getString("birthday");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("first_name", first_name);
        editor.putString("last_name", last_name);
        editor.putString("profile_pic", profile_pic);
        editor.putString("user_id", user_id);
        editor.putString("user_email", user_email);
        editor.putString("user_bday", user_bday);
        editor.commit();

        new LoginAsyncTask().execute(user_id, first_name, last_name);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token != null) {
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(main);
            finish();
        } else {
            attemptLogin();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);
    }
}
