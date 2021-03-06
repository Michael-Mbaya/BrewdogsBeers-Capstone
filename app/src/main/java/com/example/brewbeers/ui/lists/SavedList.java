package com.example.brewbeers.ui.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.brewbeers.Constants;
import com.example.brewbeers.R;
import com.example.brewbeers.adapters.FirebaseRecAdapter;
import com.example.brewbeers.models.BeersModel;
import com.example.brewbeers.util.OnStartDragListener;
import com.example.brewbeers.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedList extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference databaseReference;
    private FirebaseRecAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.savedBeerRecycler) RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("My Saved");
        setContentView(R.layout.activity_saved_list);
        ButterKnife.bind(this);


        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        //
        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_BEER)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);
        FirebaseRecyclerOptions<BeersModel> options =
                new FirebaseRecyclerOptions.Builder<BeersModel>()
                        .setQuery(query, BeersModel.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecAdapter(options, query, this, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
//        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
    public void onStartDrag(RecyclerView.ViewHolder viewHolder){
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.stopListening();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}