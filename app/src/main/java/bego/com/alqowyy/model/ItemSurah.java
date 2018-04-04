package bego.com.alqowyy.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemSurah implements Parcelable {
    int id;
    String chapterid;
    String suraname;
    String suraname_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterid() {
        return chapterid;
    }

    public void setChapterid(String chapterid) {
        this.chapterid = chapterid;
    }

    public String getSuraname() {
        return suraname;
    }

    public void setSuraname(String suraname) {
        this.suraname = suraname;
    }

    public String getSuraname_id() {
        return suraname_id;
    }

    public void setSuraname_id(String suraname_id) {
        this.suraname_id = suraname_id;
    }

    public ItemSurah(JSONObject jo) {
        try {
            String chapterid = jo.getString("chapterid");
            String suraname = jo.getString("suraname");
            String suraname_id = jo.getString("suraname_id");
            this.chapterid = chapterid;
            this.suraname = suraname;
            this.suraname_id = suraname_id;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.chapterid);
        dest.writeString(this.suraname);
        dest.writeString(this.suraname_id);
    }

    public ItemSurah() {
    }

    protected ItemSurah(Parcel in) {
        this.id = in.readInt();
        this.chapterid = in.readString();
        this.suraname = in.readString();
        this.suraname_id = in.readString();
    }

    public static final Parcelable.Creator<ItemSurah> CREATOR = new Parcelable.Creator<ItemSurah>() {
        @Override
        public ItemSurah createFromParcel(Parcel source) {
            return new ItemSurah(source);
        }

        @Override
        public ItemSurah[] newArray(int size) {
            return new ItemSurah[size];
        }
    };
}
