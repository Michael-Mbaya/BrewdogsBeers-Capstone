package com.example.brewbeers.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brewbeers.DetailActivity;
import com.example.brewbeers.R;
import com.example.brewbeers.models.BeersModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class BeersAdapter extends RecyclerView.Adapter<BeersAdapter.ViewHolder> {

    private ArrayList<BeersModel> beersModels = new ArrayList<>();
    private Context context;

    public BeersAdapter(Context context, ArrayList<BeersModel> beersModels) {
        this.context = context;
        this.beersModels = beersModels;
    }

    @NonNull
    @Override
    public BeersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beers_list_item,parent,false);
        return new BeersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeersAdapter.ViewHolder holder, int position) {
        holder.beerName.setText(beersModels.get(position).getName());
        holder.beerTag.setText(beersModels.get(position).getTagline());
        holder.beerDescription.setText(beersModels.get(position).getFirstBrewed());
        //
        Picasso.get()
                .load(beersModels.get(position).getImageUrl())
                .placeholder(R.drawable.beer_placeholder)
                .error(R.drawable.beer_placeholder)
                .into(holder.beerImage);
    }

    @Override
    public int getItemCount() {
        return beersModels.size();
//        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //
        private ImageView beerImage;
        private TextView beerName, beerTag, beerDescription;
        //
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            beerImage = itemView.findViewById(R.id.beerImageView);
            beerName = itemView.findViewById(R.id.beerNameTextView);
            beerTag = itemView.findViewById(R.id.taglineTextView);
            beerDescription = itemView.findViewById(R.id.firstBrewTextView);
            //
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            int position = getAdapterPosition();
//            Toast.makeText(context,"clicked position: "+position,Toast.LENGTH_SHORT).show();
//            Intent detail = new Intent(context, BeerDetail.class);
//            detail.putExtra("name",beersModels.get(position).getName());
//            detail.putExtra("image",beersModels.get(position).getImageUrl());
//            detail.putExtra("tag",beersModels.get(position).getTagline());
//            detail.putExtra("firstBrewed",beersModels.get(position).getFirstBrewed());
//            detail.putExtra("abv",beersModels.get(position).getAbv().toString());
//            detail.putExtra("description",beersModels.get(position).getDescription());
//            detail.putExtra("tips",beersModels.get(position).getBrewersTips());
//            context.startActivity(detail);

            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("characters", Parcels.wrap(beersModels));
            context.startActivity(intent);

        }
    }
}
