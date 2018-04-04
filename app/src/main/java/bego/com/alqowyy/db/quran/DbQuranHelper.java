package bego.com.alqowyy.db.quran;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import bego.com.alqowyy.db.surah.DbSurah;

import static android.provider.BaseColumns._ID;
import static bego.com.alqowyy.db.quran.DbQuran.SurahColumns.AYAT;
import static bego.com.alqowyy.db.quran.DbQuran.SurahColumns.QURAN;
import static bego.com.alqowyy.db.quran.DbQuran.SurahColumns.SURAID;
import static bego.com.alqowyy.db.quran.DbQuran.SurahColumns.TERJEMAH;
import static bego.com.alqowyy.db.quran.DbQuran.TABLE_NAME;

public class DbQuranHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbquran";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_QURAN = "create table " + TABLE_NAME +
            " (" + _ID + " integer primary key autoincrement, " +
            SURAID + " text not null, " +
            AYAT + " text not null, " +
            QURAN + " text not null, " +
            TERJEMAH + " text not null);";
    public DbQuranHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QURAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbQuran.TABLE_NAME);
        onCreate(db);
    }
}
