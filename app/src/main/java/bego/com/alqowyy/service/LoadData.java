package bego.com.alqowyy.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.Preference;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bego.com.alqowyy.AlQowyyHome;
import bego.com.alqowyy.BuildConfig;
import bego.com.alqowyy.R;
import bego.com.alqowyy.db.quran.QuranHelper;
import bego.com.alqowyy.db.surah.SurahHelper;
import bego.com.alqowyy.model.ItemQuran;
import bego.com.alqowyy.model.ItemSurah;
import bego.com.alqowyy.util.AppPreference;
import cz.msebera.android.httpclient.Header;

public class LoadData extends AsyncTask<Void, Integer, Void> {
    Context c;
    Preference preference;
    SurahHelper surahHelper;
    QuranHelper quranHelper;
    AppPreference appPreference;
    NumberProgressBar progressBar;
    double progress;
    double maxprogress = 100;
    boolean status = false;
    String error;
    private RequestParams params;

    public LoadData(Context c, NumberProgressBar progressBar) {
        this.c = c;
        this.progressBar = progressBar;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return this.getData();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        surahHelper = new SurahHelper(c);
        quranHelper = new QuranHelper(c);
        appPreference = new AppPreference(c);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(status) {
            Intent intent = new Intent(c, AlQowyyHome.class);
            c.startActivity(intent);
            ((Activity) c).finish();
            ((Activity) c).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }else{
            Toast.makeText(c,error,Toast.LENGTH_LONG).show();
        }
    }

    private Void getData() {
        SurahService();
        QuranService();

        return null;
    }

    private void SetPref(boolean b) {
        appPreference.setFirstRun(b);

    }

    private void QuranService() {
        ArrayList<ItemQuran> itemQurans = PreloaderQuran();
        if (status) {
            quranHelper.open();
            progress = 1;
            publishProgress((int) progress);
            Double progressMaxInsert = 80.0;
            Double progressDiff = (progressMaxInsert - progress) / itemQurans.size();

            quranHelper.beginTransaction();
            try {
                for (ItemQuran itemQuran : itemQurans) {
                    quranHelper.insert(itemQuran);
                    progress += progressDiff;
                    publishProgress((int) progress);
                }
                quranHelper.setTransactionSuccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            quranHelper.endTransaction();
            quranHelper.close();
            publishProgress((int) maxprogress);
            SetPref(false);
        }
    }

    private void SurahService() {
        ArrayList<ItemSurah> itemSurahs = PreloaderSurah();
        if (status) {
            surahHelper.open();
            surahHelper.beginTransaction();
            try {
                for (ItemSurah itemSurah : itemSurahs) {
                    surahHelper.insert(itemSurah);
                }
                surahHelper.setTransactionSuccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            surahHelper.endTransaction();
            surahHelper.close();
        }
    }

    private ArrayList<ItemSurah> PreloaderSurah() {
        final ArrayList<ItemSurah> itemSurahs = new ArrayList<>();
        SyncHttpClient client = new SyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("token", BuildConfig.TOKEN);
        client.get(BuildConfig.BASE_SURAH, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String res = new String(responseBody);
                    JSONObject jo = new JSONObject(res);
                    if (!jo.getBoolean("status")) {
                        status = false;
                        error = jo.getString("error");
                    } else {
                        JSONArray ja = jo.getJSONArray("response");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo_if = ja.getJSONObject(i);
                            ItemSurah is = new ItemSurah(jo_if);
                            itemSurahs.add(is);

                        }
                        status = true;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return itemSurahs;
    }

    private ArrayList<ItemQuran> PreloaderQuran() {
        final ArrayList<ItemQuran> itemQurans = new ArrayList<>();
        SyncHttpClient client = new SyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("token", BuildConfig.TOKEN);
        client.get(BuildConfig.BASE_QURAN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String res = new String(responseBody);
                    JSONObject jo = new JSONObject(res);
                    if (!jo.getBoolean("status")) {
                        status = false;
                        error = jo.getString("error");
                    } else {
                        JSONArray ja = jo.getJSONArray("response");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jo_if = ja.getJSONObject(i);
                            ItemQuran iq = new ItemQuran(jo_if);
                            itemQurans.add(iq);
                        }
                        status = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return itemQurans;
    }
}
