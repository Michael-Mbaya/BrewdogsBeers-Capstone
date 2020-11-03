package com.example.brewbeers.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class BeersCustomListAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mTracks;
    private String[] mTrackArtists;

    public BeersCustomListAdapter(Context mContext, int resource, String[] mTracks, String[] mTrackArtists) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mTracks = mTracks;
        this.mTrackArtists = mTrackArtists;
    }

    @Override
    public Object getItem(int position) {
        String tracks = mTracks[position];
        String trackArtists = mTrackArtists[position];
        return String.format("%s \n Of Race: %s", tracks, trackArtists);
    }

    @Override
    public int getCount() {
        return mTracks.length;
    }
}
