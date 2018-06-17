package com.interdisciplinar.mainaccount.agoravaiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends Activity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();

    }

    public void logout(View v){
        mAuth.signOut();
        callActivity(MainActivity.class);
    }

    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(DashboardActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }
}
