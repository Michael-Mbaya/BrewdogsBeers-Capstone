package com.example.brewbeers.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brewbeers.Constants;
import com.example.brewbeers.R;
import com.example.brewbeers.models.BeersModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerDetail extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference databaseReference;
    private BeersModel beersModel;

    @BindView(R.id.beerSave)Button mSave;
    @BindView(R.id.beerImageDetail) ImageView imageDetail;
    @BindView(R.id.nameDetail) TextView nameDetail;
    @BindView(R.id.descriptionDetail) TextView descriptionDetail;
    @BindView(R.id.abvDetail) TextView abvDetail;
    @BindView(R.id.brewerTipsDetail) TextView tipsDetail;
    @BindView(R.id.firstBrewedDetail) TextView firstBrewedDetail;
    @BindView(R.id.tagDetail) TextView tagDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        databaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_BEER);
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        ButterKnife.bind(this);
        //
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        String tag = intent.getStringExtra("tag");
        String firstBrewed = intent.getStringExtra("firstBrewed");
        String abv = intent.getStringExtra("abv");
        String describe = intent.getStringExtra("description");
        String tips = intent.getStringExtra("tips");
        //
        Picasso.get().load(imageUrl).into(imageDetail);
        nameDetail.setText(name);
        descriptionDetail.setText(describe);
        abvDetail.setText(abv);
        tipsDetail.setText(tips);
        firstBrewedDetail.setText(firstBrewed);
        tagDetail.setText(tag);
        //
        mSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSave){

//            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
