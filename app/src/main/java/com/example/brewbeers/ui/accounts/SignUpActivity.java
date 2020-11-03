package com.example.brewbeers.ui.accounts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brewbeers.R;
import com.example.brewbeers.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = SignUpActivity.class.getSimpleName();

    @BindView(R.id.loginTextView) TextView mLoginText;
    @BindView(R.id.createUserButton) Button mCreateUserButton;
    @BindView(R.id.nameEditText) EditText mNameEditText;
    @BindView(R.id.emailEditText) EditText mEmailEditText;
    @BindView(R.id.passwordEditText) EditText mPasswordEditText;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        //View clickListeners
        mLoginText.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);
        //
        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();
    }

    @Override
    public void onClick(View v) {
        if (v == mLoginText) {
            Intent login = new Intent(SignUpActivity.this, LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
            finish();
        }
        if (v == mCreateUserButton) {
            createNewUser();
        }
    }

    public void createNewUser(){
        final String name = mNameEditText.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();
        mName = mNameEditText.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);

        if (!validEmail || !validName || !validPassword) return;
        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgressDialog.dismiss();

                if (task.isSuccessful()){
                    Log.d(TAG, "Authentication successful");
                    Toast.makeText(SignUpActivity.this,"Account Success", Toast.LENGTH_SHORT).show();
                    createFirebaseUserProfile(task.getResult().getUser());
                }else {
                    Toast.makeText(SignUpActivity.this,"Failed.A Similar Account Exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if(!isGoodEmail){
            mEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name){
        if(name.equals("")){
            mNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword){
        if(password.length() < 6){
            mPasswordEditText.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)){
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void createFirebaseUserProfile(final FirebaseUser user){
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG+" Display Name", user.getDisplayName());
                        }
                    }
                });
    }

    public void createAuthProgressDialog(){
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Checking");
        mAuthProgressDialog.setMessage("Checking Info...Just a Sec");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}