package com.example.brewbeers.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.brewbeers.R;
import com.example.brewbeers.models.BeersModel;
import com.example.brewbeers.DetailActivity;
import com.example.brewbeers.util.ItemTouchHelperAdapter;
import com.example.brewbeers.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseRecAdapter extends FirebaseRecyclerAdapter<BeersModel, MyViewHolder> implements ItemTouchHelperAdapter {

    private Query mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    //
    private ChildEventListener mChildEventListener;
    private ArrayList<BeersModel> mCharacters = new ArrayList<>();
    //
    public FirebaseRecAdapter(FirebaseRecyclerOptions<BeersModel> options,
                              Query ref,
                              OnStartDragListener onStartDragListener,
                              Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
        //
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mCharacters.add(dataSnapshot.getValue(BeersModel.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull BeersModel model) {
        holder.bindDoc(model);
        //
        holder.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    mOnStartDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
        //
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.putExtra("characters", Parcels.wrap(mCharacters));
                mContext.startActivity(intent);
            }
        });
        //
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.char_list_item_drag, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mCharacters, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    @Override
    public void stopListening() {
        super.stopListening();
        mRef.removeEventListener(mChildEventListener);
    }

    @Override
    public void onItemDismiss(int position) {
        mCharacters.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (BeersModel doc : mCharacters) {
            int index = mCharacters.indexOf(doc);
            DatabaseReference ref = getRef(index);
            doc.setIndex(Integer.toString(index));
            ref.setValue(doc);
        }
    }

}
