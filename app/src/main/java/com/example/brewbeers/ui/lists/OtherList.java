package com.example.brewbeers.ui.lists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brewbeers.R;
import com.example.brewbeers.adapters.BeersCustomAdapter;
import com.example.brewbeers.models.BeersModel;
import com.example.brewbeers.network.BeerAPI;
import com.example.brewbeers.network.BeerClient;

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
    public static final String TAG = BeersActivity.class.getSimpleName();
    private String[] tracksFound = new String[]{"Music Track 1", "Music Track 2", "Music Track 3", "Music Track 4", "Music Track 5", "Music Track 6",
            "Music Track 7", "Music Track 8", "Music Track 9", "Music Track 10","Music Track 11","Music Track 12","Music Track 13",
            "Music Track 14","Music Track 15"};

    private String[] tracksArtist = new String[] {"Artist OG.", "Mulamwah", "Azziad", "Dj Mo", "Shakila", "Size 8",
            "Namba Nane", "NF ft Logic", "Lil Baby", "Eminem", "Cardi B", "Migos", "Lil Pump",
            "Yelawolf", "Moji Short Baba" };


    @BindView(R.id.welcomeTextView) TextView mWlcome;
    @BindView(R.id.beersList) ListView mBeersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);

        //getting/pull data from intent extra
        Intent intent = getIntent();
        String input = intent.getStringExtra("myName");
        mWlcome.setText("Welcome "+input+"!");
//        apicall
        BeerAPI beerAPI = BeerClient.getClient();
        Call<List<BeersModel>> call = beerAPI.getBeers(); //query abv_gt Double alcoholByVolume greater than the given

//        //call response and/or failure
//        //
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BEERS_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        BeerAPI api = retrofit.create(BeerAPI.class);
//        Call<List<BeersModel>> call = api.getBeers();
        //
        call.enqueue(new Callback<List<BeersModel>>() {
            @Override
            public void onResponse(Call<List<BeersModel>> call, Response<List<BeersModel>> response) {
                Log.d("On Response","Got Response");
                List<BeersModel> beers = response.body();

                    for (BeersModel b:beers){
                        Log.d("Name",b.getName());
                        Log.d("Tagline",b.getTagline());
                        Log.d("Contributed By",b.getContributedBy());
                    }

            }

            @Override
            public void onFailure(Call<List<BeersModel>> call, Throwable t) {
                Log.d("on Failure","Failed attempted call");
                Log.d("on Failure", t.getMessage());
//                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });

        ArrayAdapter adapter
                = new BeersCustomAdapter(OtherList.this, android.R.layout.simple_list_item_1, tracksFound, tracksArtist);
        mBeersList.setAdapter(adapter);

        mBeersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String listItem = ((TextView)view).getText().toString();
                Toast.makeText(OtherList.this, listItem, Toast.LENGTH_LONG).show();
            }
        });

    }

}