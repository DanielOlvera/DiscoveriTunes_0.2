package com.example.daniel.discoveritunes_02.view.DetailsActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import com.example.daniel.discoveritunes_02.R
import com.example.daniel.discoveritunes_02.model.details.SearchDetails
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        detailData(intent)
    }

    fun detailData(intent : Intent){
        if (intent.extras != null){
            var searchDetails = SearchDetails().fromExtras(intent)
            Glide.with(this)
                    .load(searchDetails.art100)
                    .listener(object : RequestListener<String, GlideDrawable> {
                        override fun onException(e: Exception, model: String, target: Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: GlideDrawable, model: String, target: Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                            return false
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(d_artworkUrl100)
            dTrackName.text = searchDetails.trackName
            d_longDescription.text = searchDetails.longDescription

        }
    }
}
