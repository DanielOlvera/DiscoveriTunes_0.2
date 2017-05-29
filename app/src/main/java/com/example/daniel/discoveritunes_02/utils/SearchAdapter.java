package com.example.daniel.discoveritunes_02.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.daniel.discoveritunes_02.R;
import com.example.daniel.discoveritunes_02.model.Result;
import com.example.daniel.discoveritunes_02.view.DetailsActivity.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Daniel on 5/29/17.
 */

public class SearchAdapter extends RecyclerView.Adapter <SearchAdapter.SearchHolder>{

    private static final String TAG = SearchAdapter.class.getSimpleName();

    private List<Result> results;
    private int rowLayout;
    private Context context;

    public SearchAdapter(List<Result> results, int rowLayout, Context context) {
        this.results = results;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SearchHolder(view, context, results);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        Glide.with(context)
                .load(results.get(position).getArtworkUrl30())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.artwork30ImVw);
        holder.trackName.setText(results.get(position).getTrackName());
        holder.trackPrice.setText(String.format("$ %1$.2f", results.get(position).getTrackPrice())); //TODO: fix -> Attempt to invoke virtual method 'java.lang.String java.lang.Double.toString()' on a null object reference
        holder.description.setText(results.get(position).getKind().replaceAll("-", " "));
        Log.d(TAG, "onBindViewHolder: " + results.get(position).getTrackPrice().toString()
                +results.get(position).getArtworkUrl30());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.cv_artworkUrl30)
        ImageView artwork30ImVw;
        @BindView(R.id.cv_trackName)
        TextView trackName;
        @BindView(R.id.cv_price)
        TextView trackPrice;
        @BindView(R.id.cv_description)
        TextView description;

        List<Result> results = new ArrayList<>();
        Context context;

        SearchHolder(View itemView, Context context, List<Result> results) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.context = context;
            this.results = results;
        }

        @Override
        public void onClick(View view) {
            //TODO: start here details activity
            int position = getAdapterPosition();
            Result result = this.results.get(position);
            Intent intent = prepareIntent(result);
            context.startActivity(intent);
        }

        /**
         * This method allows to send data to DetailsActivity each time we select one item
         * from the recyclerview
         * @param result the object from where we are retrieving values
         * @return is used to return the intent need to share data with DetailsActivity
         */
        Intent prepareIntent(Result result){
            Intent intent = new Intent(this.context, DetailsActivity.class);
            intent.putExtra(SearchConstants.Companion.getTRACK_NAME(), result.getTrackName());
            intent.putExtra(SearchConstants.Companion.getARTWORK_100(), result.getArtworkUrl100());
            intent.putExtra(SearchConstants.Companion.getLONG_DESCRIPTION(), result.getCollectionName());
            intent.putExtra(SearchConstants.Companion.getARTIST_NAME(), result.getArtistName());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.d(TAG, "prepareIntent: " + SearchConstants.Companion.getTRACK_NAME());
            return intent;
        }
    }
}
