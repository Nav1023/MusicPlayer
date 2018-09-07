package com.example.soc_macmini_15.musicplayer.Fragments;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soc_macmini_15.musicplayer.Activity.MainActivity;
import com.example.soc_macmini_15.musicplayer.R;

import java.util.ArrayList;

public class TabFragment extends ListFragment {


    private static ContentResolver contentResolver1;
    public ArrayList<String> songsList;
    private ListView listView;
    private ArrayAdapter<String> adapter;


    private int position;
    private TextView textView;
    private ContentResolver contentResolver;

    public static Fragment getInstance(int position, ContentResolver mcontentResolver) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        contentResolver1=mcontentResolver;
        return tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.list_playlist);
        position = getArguments().getInt("pos");
        contentResolver=contentResolver1;
        setContent();
    }

    public void setContent() {
        songsList = new ArrayList<>();
        getMusic();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, songsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "You clicked :\n"+songsList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getMusic() {
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);
        if (songCursor != null && songCursor.moveToFirst()) {
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songPath=songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do {
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                String currentLocation=songCursor.getString(songPath);
                songsList.add(currentTitle + "\n" + currentArtist + "\n"+currentLocation);
            } while (songCursor.moveToNext());
        }
    }

}
