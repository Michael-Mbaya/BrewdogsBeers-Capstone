package com.example.brewbeers.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brewbeers.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerDetail extends AppCompatActivity {

//    @BindView(R.id.beerSave)Button mSave;
    @BindView(R.id.beerImageDetail) ImageView imageDetail;
    @BindView(R.id.nameDetail) TextView nameDetail;
    @BindView(R.id.descriptionDetail) TextView descriptionDetail;
    @BindView(R.id.abvDetail) TextView abvDetail;
    @BindView(R.id.brewerTipsDetail) TextView tipsDetail;
    @BindView(R.id.firstBrewedDetail) TextView firstBrewedDetail;
    @BindView(R.id.tagDetail) TextView tagDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
//        Toast.makeText(this,"name : "+name,Toast.LENGTH_SHORT).show();
        //
        Picasso.get().load(imageUrl).into(imageDetail);
        nameDetail.setText(name);
        descriptionDetail.setText(describe);
        abvDetail.setText(abv);
        tipsDetail.setText(tips);
        firstBrewedDetail.setText(firstBrewed);
        tagDetail.setText(tag);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
