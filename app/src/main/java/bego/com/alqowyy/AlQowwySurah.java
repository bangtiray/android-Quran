package bego.com.alqowyy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import bego.com.alqowyy.Adapter.RecyclerviewAdapter;
import bego.com.alqowyy.db.surah.SurahHelper;
import bego.com.alqowyy.model.ItemSurah;
import bego.com.alqowyy.util.ItemClickSupport;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AlQowwySurah extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerviewAdapter adapter;
    SurahHelper surahHelper;
    ArrayList<ItemSurah> itemSurahs;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Surah");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchView.setOnQueryTextListener(pencarian);
        setupList();
    }

    private void setupList() {

        surahHelper = new SurahHelper(this);
        adapter = new RecyclerviewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        surahHelper.open();

        itemSurahs = surahHelper.getAllData();
        surahHelper.close();
        adapter.addItem(itemSurahs);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Toast.makeText(getApplicationContext(), itemSurahs.get(position).getChapterid(), Toast.LENGTH_LONG).show();
                Intent quran = new Intent(AlQowwySurah.this, AlQowwyQuran.class);
                quran.putExtra(AlQowwyQuran.SURAID, itemSurahs.get(position).getChapterid());
                quran.putExtra(AlQowwyQuran.SURATEXT,itemSurahs.get(position).getSuraname());
                startActivity(quran);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    MaterialSearchView.OnQueryTextListener pencarian = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            surahHelper.open();
            if (query.isEmpty()) {
                itemSurahs = surahHelper.getAllData();
            } else {
                itemSurahs = surahHelper.getByKey(query);
            }
            surahHelper.close();
            adapter.addItem(itemSurahs);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            surahHelper.open();
            if (query.isEmpty()) {
                itemSurahs = surahHelper.getAllData();
            } else {
                itemSurahs = surahHelper.getByKey(query);
            }
            surahHelper.close();
            adapter.addItem(itemSurahs);
            return false;
        }
    };
}

