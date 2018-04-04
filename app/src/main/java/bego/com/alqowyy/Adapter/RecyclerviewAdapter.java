package bego.com.alqowyy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bego.com.alqowyy.R;
import bego.com.alqowyy.model.ItemSurah;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private Context c;
    private LayoutInflater inflater;
    private ArrayList<ItemSurah> mData = new ArrayList<>();

    public RecyclerviewAdapter(Context c) {
        this.c = c;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(ArrayList<ItemSurah> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sura_title.setText(mData.get(position).getSuraname());
        holder.sura_number.setText(mData.get(position).getChapterid());
        holder.surah_id.setText(mData.get(position).getSuraname_id());
        // holder._txtsdesc.setText(mData.get(position).getS_DESC());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sura_number;
        private TextView sura_title;
        private TextView surah_id;

        // private TextView _txtsdesc;
        public ViewHolder(View itemView) {
            super(itemView);
            sura_number = (TextView) itemView.findViewById(R.id.sura_number);
            // _txtsdesc=(TextView)itemView.findViewById(R.id.txt_sdesc);
            sura_title = (TextView) itemView.findViewById(R.id.sura_title);
            surah_id = (TextView) itemView.findViewById(R.id.surah_id);
        }
    }
}
