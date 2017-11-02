package com.aystub.muhbeers.application;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aystub.muhbeers.screens.home.HomeActivity;
import com.aystub.muhbeers.screens.welcome.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Aystub on 11/1/17.
 */

public class MuhBeersRoutingActivity extends Activity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendUserToCorrectScreen();
        finish();
    }


    private void sendUserToCorrectScreen() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null) {
            startActivity(WelcomeActivity.createIntent(this));
        } else {
            startActivity(HomeActivity.createIntent(this));
        }
    }
}
