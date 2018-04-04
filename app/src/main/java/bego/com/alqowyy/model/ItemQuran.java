package bego.com.alqowyy.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemQuran implements Parcelable {
    private int id;
    private String suraid;
    private String ayat;
    private String quran;
    private String terjemah;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuraid() {
        return suraid;
    }

    public void setSuraid(String suraid) {
        this.suraid = suraid;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getQuran() {
        return quran;
    }

    public void setQuran(String quran) {
        this.quran = quran;
    }

    public String getTerjemah() {
        return terjemah;
    }

    public void setTerjemah(String terjemah) {
        this.terjemah = terjemah;
    }
    public ItemQuran(JSONObject jo){
        try{
            String suraid= jo.getString("suraid");
            String ayat= jo.getString("ayat");
            String quran= jo.getString("quran");
            String terjeman= jo.getString("terjemah");

            this.suraid=suraid;
            this.ayat=ayat;
            this.quran=quran;
            this.terjemah=terjeman;
        }catch (JSONException e){

        }
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.suraid);
        dest.writeString(this.ayat);
        dest.writeString(this.quran);
        dest.writeString(this.terjemah);
    }

    public ItemQuran() {
    }

    protected ItemQuran(Parcel in) {
        this.id = in.readInt();
        this.suraid = in.readString();
        this.ayat = in.readString();
        this.quran = in.readString();
        this.terjemah = in.readString();
    }

    public static final Parcelable.Creator<ItemQuran> CREATOR = new Parcelable.Creator<ItemQuran>() {
        @Override
        public ItemQuran createFromParcel(Parcel source) {
            return new ItemQuran(source);
        }

        @Override
        public ItemQuran[] newArray(int size) {
            return new ItemQuran[size];
        }
    };
}
