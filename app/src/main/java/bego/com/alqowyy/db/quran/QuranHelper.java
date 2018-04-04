package bego.com.alqowyy.db.quran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

import java.util.ArrayList;

import bego.com.alqowyy.db.surah.DbSurah;
import bego.com.alqowyy.model.ItemQuran;


import static android.provider.BaseColumns._ID;
import static bego.com.alqowyy.db.quran.DbQuran.SurahColumns.AYAT;
import static bego.com.alqowyy.db.quran.DbQuran.SurahColumns.QURAN;
import static bego.com.alqowyy.db.quran.DbQuran.SurahColumns.SURAID;
import static bego.com.alqowyy.db.quran.DbQuran.SurahColumns.TERJEMAH;
import static bego.com.alqowyy.db.quran.DbQuran.TABLE_NAME;


public class QuranHelper {
    private Context c;
    private DbQuranHelper dbQuranHelper;

    private SQLiteDatabase database;

    public QuranHelper(Context c) {
        this.c = c;
    }

    public QuranHelper open() throws SQLException {
        dbQuranHelper = new DbQuranHelper(c);
        database = dbQuranHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbQuranHelper.close();
    }


    public ArrayList<ItemQuran> getByKey(String param) {
        String result = "";
        Cursor cursor = database.query(TABLE_NAME, null, SURAID + " LIKE ?", new String[]{param}, null, null, MediaStore.Audio.Playlists.Members._ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<ItemQuran> arrayList = new ArrayList<>();
        ItemQuran itemQuran;
        if (cursor.getCount() > 0) {
            do {
                itemQuran = new ItemQuran();
                itemQuran.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                itemQuran.setSuraid(cursor.getString(cursor.getColumnIndexOrThrow(SURAID)));
                itemQuran.setAyat(cursor.getString(cursor.getColumnIndexOrThrow(AYAT)));
                itemQuran.setQuran(cursor.getString(cursor.getColumnIndexOrThrow(QURAN)));
                itemQuran.setTerjemah(cursor.getString(cursor.getColumnIndexOrThrow(TERJEMAH)));

                arrayList.add(itemQuran);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public ArrayList<ItemQuran> getAllData() {
        String result = "";
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<ItemQuran> arrayList = new ArrayList<>();
        ItemQuran ItemQuran;
        if (cursor.getCount() > 0) {
            do {
                ItemQuran = new ItemQuran();
                ItemQuran.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                ItemQuran.setSuraid(cursor.getString(cursor.getColumnIndexOrThrow(SURAID)));
                ItemQuran.setAyat(cursor.getString(cursor.getColumnIndexOrThrow(AYAT)));
                ItemQuran.setQuran(cursor.getString(cursor.getColumnIndexOrThrow(QURAN)));
                ItemQuran.setTerjemah(cursor.getString(cursor.getColumnIndexOrThrow(TERJEMAH)));

                arrayList.add(ItemQuran);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(ItemQuran ItemQuran) {
        ContentValues values = new ContentValues();
        values.put(SURAID, ItemQuran.getSuraid());
        values.put(AYAT, ItemQuran.getAyat());
        values.put(QURAN, ItemQuran.getQuran());
        values.put(TERJEMAH, ItemQuran.getTerjemah());
        return database.insert(TABLE_NAME, null, values);
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }
}
