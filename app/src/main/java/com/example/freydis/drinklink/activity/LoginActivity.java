package com.example.freydis.drinklink.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.freydis.drinklink.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        loginButton.setReadPermissions("user_friends");

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
                if (loginResult.getAccessToken() != null) {
                    profile = Profile.getCurrentProfile();
                    authSuccess(profile);
                }
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

    private void authSuccess(Profile profile) {
        if (profile != null) {
            String user_id = profile.getId();
            String firstname = profile.getFirstName();
            String lastname = profile.getLastName();

            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            main.putExtra("user_id", user_id);
            main.putExtra("firstname", firstname);
            main.putExtra("lastname", lastname);
            main.putExtra("imageUrl", profile.getProfilePictureUri(200, 200).toString());

            new LoginAsyncTask().execute(user_id, firstname, lastname);

            startActivity(main);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        authSuccess(profile);
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
