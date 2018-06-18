package com.interdisciplinar.mainaccount.agoravaiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends Activity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private TextView entre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextCEmail);
        password = findViewById(R.id.editTextCPass);
        entre = findViewById(R.id.textViewEntre);

        entre.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                callActivity(MainActivity.class);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void signUp(View v) {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w("ERRORCAD", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccountActivity.this, "Falha na autenticação.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(CreateAccountActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }

    public void updateUI(FirebaseUser currentUser){
        if(currentUser != null)
            callActivity(DashboardActivity.class);
    }
}
