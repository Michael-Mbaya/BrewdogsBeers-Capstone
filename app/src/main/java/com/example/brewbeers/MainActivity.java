package com.example.brewbeers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.nextActButton) Button mButtonMain;
    @BindView(R.id.editText) EditText mEditTextMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButtonMain.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mButtonMain) {

            if (mEditTextMain.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
                mEditTextMain.setError("Search Text is Required");
                Toast.makeText(MainActivity.this, "Text is Required", Toast.LENGTH_LONG).show();
            }else {
                String myNameIs = mEditTextMain.getText().toString();
                Intent intent = new Intent(MainActivity.this, BeersActivity.class);
                //pass data with intent extras
                intent.putExtra("myName", myNameIs);
                //go to search activity
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Welcome " + myNameIs+"!", Toast.LENGTH_LONG).show();
            }

        }
    }

}