package com.example.brewbeers.ui.accounts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brewbeers.R;
import com.example.brewbeers.ui.MainActivity;
import com.example.brewbeers.ui.lists.OtherList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.registerTextView2) TextView goSignUp;
    @BindView(R.id.loginButton2) Button loginButton;
    @BindView(R.id.emailEditText2) EditText emailEditText;
    @BindView(R.id.passwordEditText2) EditText passwordEditText;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        //View clickListeners
        goSignUp.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        //
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Intent intent = new Intent(LoginActivity.this, OtherList.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
        createAuthProgressDialog();
    }

    @Override
    public void onClick(View v) {
        if(v == goSignUp){
            Intent signUp = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(signUp);
            finish();
        }
        if (v == loginButton){
            loginWithPassword();
        }
    }

    private void createAuthProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Checking");
        progressDialog.setMessage("Checking Info...Just a Sec");
        progressDialog.setCancelable(false);
    }

    private void loginWithPassword(){
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if(email.equals("")){
            emailEditText.setError("Please Enter Your Email");
            return;
        }
        if(password.equals("")){
            passwordEditText.setError("Password cannot be blank");
            return;
        }
        progressDialog.show();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                Log.d(TAG+" Success Login", "signInWithEmail:onComplete:" + task.isSuccessful());
                if(!task.isSuccessful()){
                    Log.w(TAG, "signInWithEmail", task.getException());
                    Toast.makeText(LoginActivity.this, "Login Faiure, Check your email and/or password and try again or create an account. ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }

}