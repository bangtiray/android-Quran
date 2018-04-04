package bego.com.alqowyy.db.quran;

import android.provider.BaseColumns;

public class DbQuran {
    static String TABLE_NAME ="table_quran";

    static final class SurahColumns implements BaseColumns {
        static String SURAID="suraid";
        static String AYAT="ayat";
        static String QURAN="quran";
        static String TERJEMAH="terjemah";
    }
}
