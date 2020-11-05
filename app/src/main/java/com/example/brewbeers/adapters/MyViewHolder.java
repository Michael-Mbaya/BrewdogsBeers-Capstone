package com.example.brewbeers.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brewbeers.R;
import com.example.brewbeers.models.BeersModel;
import com.example.brewbeers.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

public class MyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    View mView;
    Context mContext;
    public ImageView image;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }
        public void bindDoc (BeersModel doc) {
            image = mView.findViewById(R.id.beerImageDrag);
            TextView nameView = mView.findViewById(R.id.beerNameDrag);
            TextView raceView = mView.findViewById(R.id.tagDrag);
            TextView wikiView = mView.findViewById(R.id.firstBrewDrag);

            nameView.setText(doc.getName());
            raceView.setText(doc.getTagline());
            wikiView.setText(doc.getFirstBrewed());
            Picasso.get().load(doc.getImageUrl()).into(image);

    }


    @Override
    public void onItemSelected() {
        Log.d("Animation", "onItemSelected");
        // we will add animations here
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        Log.d("Animation", "onItemClear");
        // we will add animations here
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }

}
