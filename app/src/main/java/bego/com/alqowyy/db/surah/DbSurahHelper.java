package bego.com.alqowyy.db.surah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static bego.com.alqowyy.db.surah.DbSurah.SurahColumns.CHAPTERID;
import static bego.com.alqowyy.db.surah.DbSurah.SurahColumns.SURANAME;
import static bego.com.alqowyy.db.surah.DbSurah.SurahColumns.SURANAME_ID;
import static bego.com.alqowyy.db.surah.DbSurah.TABLE_NAME;

public class DbSurahHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbsurah";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_SURAH = "create table " + TABLE_NAME +
            " (" + _ID + " integer primary key autoincrement, " +
            CHAPTERID + " text not null, " +
            SURANAME + " text not null, " +
            SURANAME_ID + " text not null);";

    public DbSurahHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SURAH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
