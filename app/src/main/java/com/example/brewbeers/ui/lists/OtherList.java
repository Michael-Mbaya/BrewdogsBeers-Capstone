package com.example.brewbeers.ui.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brewbeers.R;
import com.example.brewbeers.adapters.BeersAdapter;
import com.example.brewbeers.adapters.BeersCustomAdapter;
import com.example.brewbeers.models.BeersModel;
import com.example.brewbeers.network.BeerAPI;
import com.example.brewbeers.network.BeerClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.brewbeers.Constants.BEERS_BASE_URL;

public class OtherList extends AppCompatActivity {
    public static final String TAG = OtherList.class.getSimpleName();

    ArrayList<BeersModel> beersModels = new ArrayList<>();
    private BeersAdapter beersAdapter;

    @BindView(R.id.welcomeTextView) TextView mWlcome;
    @BindView(R.id.beerRecycler) RecyclerView mBeersRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);

        //getting/pull data from intent extra
        Intent intent = getIntent();
        String input = intent.getStringExtra("myName");
        mWlcome.setText("Welcome "+input+"!");

//        mBeersRecycler.setLayoutManager(new LinearLayoutManager(this));

        getResponse();

    }

    private void getResponse() {
        //        apicall
        BeerAPI beerAPI = BeerClient.getClient();
        Call<List<BeersModel>> call = beerAPI.getBeers(); //query abv_gt Double alcoholByVolume greater than the given
//        call response or failure
        call.enqueue(new Callback<List<BeersModel>>() {
            @Override
            public void onResponse(Call<List<BeersModel>> call, Response<List<BeersModel>> response) {
                Log.d("On Response","Got Response");
                beersModels = new ArrayList<>(response.body());
                beersAdapter = new BeersAdapter(OtherList.this,beersModels);
                mBeersRecycler.setLayoutManager(new LinearLayoutManager(OtherList.this));
                mBeersRecycler.setAdapter(beersAdapter);
                mBeersRecycler.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<BeersModel>> call, Throwable t) {
                Log.d("on Failure","Failed attempted call");
                Log.d("on Failure", t.getMessage());
            }

        });
    }

}
