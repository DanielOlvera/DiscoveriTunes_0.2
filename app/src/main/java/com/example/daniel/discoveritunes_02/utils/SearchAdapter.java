package com.example.daniel.discoveritunes_02.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.daniel.discoveritunes_02.R;
import com.example.daniel.discoveritunes_02.model.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Daniel on 5/29/17.
 */

public class SearchAdapter extends RecyclerView.Adapter <SearchAdapter.SearchHolder>{

    private static final String TAG = SearchAdapter.class.getSimpleName();
    private static final String BASE_URL_IMAGE = "http://a1.itunes.apple.com/r10/Music/3b/6a/33";

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
        holder.trackPrice.setText(results.get(position).getTrackPrice().toString());
        holder.desciption.setText(results.get(position).getKind());
        Log.d(TAG, "onBindViewHolder: " + results.get(position).getTrackPrice().toString()
                +results.get(position).getArtworkUrl30());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class SearchHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.cv_artworkUrl30)
        ImageView artwork30ImVw;
        @BindView(R.id.cv_trackName)
        TextView trackName;
        @BindView(R.id.cv_price)
        TextView trackPrice;
        @BindView(R.id.cv_description)
        TextView desciption;

        List<Result> results = new ArrayList<>();
        Context context;

        public SearchHolder(View itemView, Context context, List<Result> results) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.context = context;
            this.results = results;
        }

        @Override
        public void onClick(View view) {
            //TODO: start here details activity
            Toast.makeText(context, "recyclerItem selected", Toast.LENGTH_SHORT).show();
        }

        //TODO: Add  preparedIntent method to share the estras for the detail activity
    }
}
