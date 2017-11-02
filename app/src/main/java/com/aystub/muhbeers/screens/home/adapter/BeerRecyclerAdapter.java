package com.aystub.muhbeers.screens.home.adapter;


import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.aystub.muhbeers.R;
import com.aystub.muhbeers.data.Beer;
import com.aystub.muhbeers.data.adapters.FirestoreAdapter;
import com.aystub.muhbeers.util.UiUtils;
import com.bumptech.glide.Glide;
import com.google.firebase.firestore.Query;


public class BeerRecyclerAdapter extends FirestoreAdapter<BeerRecyclerAdapter.BeerRecyclerViewHolder> {

    private OnBeerSelectedListener listener;


    public BeerRecyclerAdapter(Query query, OnBeerSelectedListener listener) {
        super(query);
        this.listener = listener;
    }


    @Override
    public BeerRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeerRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_beer_card, parent, false));
    }


    @Override
    public void onBindViewHolder(BeerRecyclerViewHolder holder, int position) {
        final Beer beer = getSnapshot(position).toObject(Beer.class);
        if (position == 0) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            params.topMargin = UiUtils.dpToPx(holder.itemView.getContext(), 8);
            holder.itemView.setLayoutParams(params);
        }
        if (!TextUtils.isEmpty(beer.getImageURL())) {
            Glide.with(holder.itemView)
                    .load(beer.getImageURL())
                    .into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.beer);
        }
        holder.title.setText(beer.getName());
        holder.taster.setText(beer.getTaster());
        holder.rating.setRating(beer.getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBeerSelected(beer);
            }
        });
    }


    public interface OnBeerSelectedListener {
        void onBeerSelected(Beer beer);
    }


    class BeerRecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, taster;
        RatingBar rating;

        BeerRecyclerViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.beer_name);
            taster = itemView.findViewById(R.id.taster);
            rating = itemView.findViewById(R.id.rating_bar);
        }
    }
}
