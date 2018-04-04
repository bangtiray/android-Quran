package bego.com.alqowyy.db.surah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bego.com.alqowyy.model.ItemSurah;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static bego.com.alqowyy.db.surah.DbSurah.SurahColumns.CHAPTERID;
import static bego.com.alqowyy.db.surah.DbSurah.SurahColumns.SURANAME;
import static bego.com.alqowyy.db.surah.DbSurah.SurahColumns.SURANAME_ID;
import static bego.com.alqowyy.db.surah.DbSurah.TABLE_NAME;

public class SurahHelper {
    private Context c;
    private DbSurahHelper dbSurahHelper;

    private SQLiteDatabase database;

    public SurahHelper(Context c) {
        this.c = c;
    }

    public SurahHelper open() throws SQLException {
        dbSurahHelper = new DbSurahHelper(c);
        database = dbSurahHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbSurahHelper.close();
    }

    public ArrayList<ItemSurah> getByKey(String param) {
        String result = "";
        Cursor cursor = database.query(TABLE_NAME, null, SURANAME_ID + " LIKE ?", new String[]{'%' + param + '%'}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<ItemSurah> arrayList = new ArrayList<>();
        ItemSurah itemSurah;
        if (cursor.getCount() > 0) {
            do {
                itemSurah = new ItemSurah();
                itemSurah.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                itemSurah.setChapterid(cursor.getString(cursor.getColumnIndexOrThrow(CHAPTERID)));
                itemSurah.setSuraname(cursor.getString(cursor.getColumnIndexOrThrow(SURANAME)));
                itemSurah.setSuraname_id(cursor.getString(cursor.getColumnIndexOrThrow(SURANAME_ID)));

                arrayList.add(itemSurah);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<ItemSurah> getAllData() {
        String result = "";
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<ItemSurah> arrayList = new ArrayList<>();
        ItemSurah itemSurah;
        if (cursor.getCount() > 0) {
            do {
                itemSurah = new ItemSurah();
                itemSurah.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                itemSurah.setChapterid(cursor.getString(cursor.getColumnIndexOrThrow(CHAPTERID)));
                itemSurah.setSuraname(cursor.getString(cursor.getColumnIndexOrThrow(SURANAME)));
                itemSurah.setSuraname_id(cursor.getString(cursor.getColumnIndexOrThrow(SURANAME_ID)));

                arrayList.add(itemSurah);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(ItemSurah itemSurah) {
        ContentValues values = new ContentValues();
        values.put(CHAPTERID, itemSurah.getChapterid());
        values.put(SURANAME, itemSurah.getSuraname());
        values.put(SURANAME_ID, itemSurah.getSuraname_id());
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
