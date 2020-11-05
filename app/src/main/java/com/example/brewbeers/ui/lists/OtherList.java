package com.example.brewbeers.ui.lists;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.brewbeers.ui.MainActivity;
import com.example.brewbeers.ui.accounts.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

public class OtherList extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = OtherList.class.getSimpleName();

    ArrayList<BeersModel> beersModels = new ArrayList<>();
    private BeersAdapter beersAdapter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @BindView(R.id.beerButton) Button mBeers;
    @BindView(R.id.beerRecycler) RecyclerView mBeersRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.bind(this);
//        //getting/pull data from intent extra
//        Intent intent = getIntent();
//        String input = intent.getStringExtra("myName");
//        mWlcome.setText("Welcome "+input+"!");
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
        //
        mBeers.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
    if(v == mBeers){
        mBeersRecycler.setLayoutManager(new LinearLayoutManager(this));
        getResponse();
    }
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
        Intent logout = new Intent(OtherList.this, LoginActivity.class);
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
