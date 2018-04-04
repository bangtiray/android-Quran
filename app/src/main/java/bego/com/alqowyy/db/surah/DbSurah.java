package bego.com.alqowyy.db.surah;

import android.provider.BaseColumns;

public class DbSurah {
    static String TABLE_NAME ="table_surah";

    static final class SurahColumns implements BaseColumns {
        static String CHAPTERID="chapterid";
        static String SURANAME="suraname";
        static String SURANAME_ID="suraname_id";
    }
}
