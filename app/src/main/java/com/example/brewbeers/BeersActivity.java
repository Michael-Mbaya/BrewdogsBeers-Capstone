package com.example.brewbeers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brewbeers.adapters.BeersCustomListAdapter;
import com.example.brewbeers.models.Doc;
import com.example.brewbeers.models.MyPreciousResponse;
import com.example.brewbeers.network.LotrAPI;
import com.example.brewbeers.network.LotrClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeersActivity extends AppCompatActivity {
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

        //apicall
        LotrAPI client = LotrClient.getClient();
        Call<MyPreciousResponse> call = client.getBeers(); //query abv_gt Double alcoholByVolume greater than the given

        //call response and/or failure
        call.enqueue(new Callback<MyPreciousResponse>() {
            @Override
            public void onResponse(Call<MyPreciousResponse> call, Response<MyPreciousResponse> response) {
                if (response.isSuccessful()) {

                   MyPreciousResponse lotrRes = response.body();
                    Log.i("Response Body",lotrRes.toString());

                    List<Doc> newList = lotrRes.getDocs();
                    Log.i("Characters List",newList.toString());
                    if(newList==null){
                        Log.i("Empty results","Can' believe this Empty Things");
                    }else {
                        Log.i("Some results","I believe this! Wonders Man!!!");
                    }

                    String[] characterNames = new String[newList.size()];
                    String[] characterRaces = new String[newList.size()];    //Am not Racist

                    for (int i = 0; i < characterNames.length; i++){
                        characterNames[i] = newList.get(i).getName();
                    }

                    for (int i = 0; i < characterRaces.length; i++) {
                        characterRaces[i] = newList.get(i).getRace();
                    }

                    //Custom adapter
                    ArrayAdapter adapter
                            = new BeersCustomListAdapter(BeersActivity.this, android.R.layout.simple_list_item_1, characterNames, characterRaces);
                    mBeersList.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<MyPreciousResponse> call, Throwable t) {

            }

        });

        ArrayAdapter adapter
                = new BeersCustomListAdapter(BeersActivity.this, android.R.layout.simple_list_item_1, tracksFound, tracksArtist);
        mBeersList.setAdapter(adapter);

        mBeersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String listItem = ((TextView)view).getText().toString();
                Toast.makeText(BeersActivity.this, listItem, Toast.LENGTH_LONG).show();
            }
        });

    }

}

//adapter
//    ArrayAdapter adapter
//            = new BeersCustomListAdapter(BeersActivity.this, android.R.layout.simple_list_item_1, tracksFound, tracksArtist);
//        mBeersList.setAdapter(adapter);