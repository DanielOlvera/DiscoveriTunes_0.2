package com.example.daniel.discoveritunes_02.model.details;

import android.content.Intent;

import com.example.daniel.discoveritunes_02.utils.SearchConstants;

/**
 * Created by Daniel on 5/29/17.
 */

public class SearchDetails {

    private String trackName;
    private String art100;
    private String longDescription;

    public SearchDetails fromExtras(Intent details){
        this.trackName = details.getStringExtra(SearchConstants.Companion.getTRACK_NAME());
        this.art100 = details.getStringExtra(SearchConstants.Companion.getARTWORK_100());
        this.longDescription = details.getStringExtra(SearchConstants.Companion.getLONG_DESCRIPTION());
        return this;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArt100() {
        return art100;
    }

    public void setArt100(String art100) {
        this.art100 = art100;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
