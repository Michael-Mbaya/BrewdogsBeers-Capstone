package com.example.brewbeers.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brewbeers.R;
import com.example.brewbeers.ui.accounts.LoginActivity;
import com.example.brewbeers.ui.lists.BeersActivity;
import com.example.brewbeers.ui.lists.OtherList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.nextActButton) Button mButtonMain;
    @BindView(R.id.editText) EditText mEditTextMain;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                }else {
                    getSupportActionBar().setTitle(R.string.app_name);
                }
            }
        };
        //View clickListeners
        mButtonMain.setOnClickListener(this);
        //

    }

    //clicklisteners actions onClick
    @Override
    public void onClick(View v) {
        if (v == mButtonMain) {

            if (mEditTextMain.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
                mEditTextMain.setError("Search Text is Required");
                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
            }else {
                String myNameIs = mEditTextMain.getText().toString();
//                Intent intent = new Intent(MainActivity.this, BeersActivity.class);
                Intent other = new Intent(MainActivity.this, OtherList.class);
                //pass data with intent extras
//                intent.putExtra("myName", myNameIs);
                other.putExtra("myName", myNameIs);
                //go to search activity
//                startActivity(intent);
                startActivity(other);
                Toast.makeText(MainActivity.this, "Welcome " + myNameIs+"!", Toast.LENGTH_LONG).show();
            }

        }
    }
    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //on log-out option select
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //firebase logout
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        //return to login after log out of session
        Intent logout = new Intent(MainActivity.this, LoginActivity.class);
        logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logout);
        finish();
    }
    //AuthStateListener start
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    //AuthStateListener end
    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

}