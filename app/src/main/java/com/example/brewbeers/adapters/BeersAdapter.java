package com.example.brewbeers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brewbeers.R;
import com.example.brewbeers.models.BeersModel;
import com.squareup.picasso.Picasso;

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        //
        private ImageView beerImage;
        private TextView beerName, beerTag, beerDescription;
        //
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            beerImage = (ImageView) itemView.findViewById(R.id.beerImageView);
            beerName = (TextView) itemView.findViewById(R.id.beerNameTextView);
            beerTag = (TextView) itemView.findViewById(R.id.taglineTextView);
            beerDescription = (TextView) itemView.findViewById(R.id.descriptionTextView);
            //
        }
    }
}
