package com.example.soc_macmini_15.musicplayer.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.soc_macmini_15.musicplayer.Model.SongsList;

import java.util.ArrayList;

public class FavoritesOperations {

    public static final String TAG = "Favorites Database";

    SQLiteOpenHelper dbHandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            FavoritesDBHandler.COLUMN_ID,
            FavoritesDBHandler.COLUMN_TITLE,
            FavoritesDBHandler.COLUMN_SUBTITLE,
            FavoritesDBHandler.COLUMN_PATH
    };

    public FavoritesOperations(Context context) {
        dbHandler = new FavoritesDBHandler(context);
    }

    public void open() {
        Log.i(TAG, " Database Opened");
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        Log.i(TAG, "Database Closed");
        dbHandler.close();
    }

    public void addSongFav(SongsList songsList) {
        open();
        ContentValues values = new ContentValues();
        values.put(FavoritesDBHandler.COLUMN_TITLE, songsList.getTitle());
        values.put(FavoritesDBHandler.COLUMN_SUBTITLE, songsList.getSubTitle());
        values.put(FavoritesDBHandler.COLUMN_PATH, songsList.getPath());

        database.insertWithOnConflict(FavoritesDBHandler.TABLE_SONGS, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        close();
    }

    public ArrayList<SongsList> getAllFavorites() {
        open();
        Cursor cursor = database.query(FavoritesDBHandler.TABLE_SONGS, allColumns,
                null, null, null, null, null);
        ArrayList<SongsList> favSongs = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                SongsList songsList = new SongsList(cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.COLUMN_TITLE))
                        , cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.COLUMN_SUBTITLE))
                        , cursor.getString(cursor.getColumnIndex(FavoritesDBHandler.COLUMN_PATH)));
                favSongs.add(songsList);
            }
        }
        close();
        return favSongs;
    }

    public void removeSong(String songPath) {
        open();
        String whereClause =
                FavoritesDBHandler.COLUMN_PATH + "=?";
        String[] whereArgs = new String[]{songPath};

        database.delete(FavoritesDBHandler.TABLE_SONGS, whereClause, whereArgs);
        close();
    }

}






















