package bego.com.alqowyy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import bego.com.alqowyy.Adapter.RecyclerviewAdapter;
import bego.com.alqowyy.Adapter.RecyclerviewQuran;
import bego.com.alqowyy.db.quran.QuranHelper;
import bego.com.alqowyy.db.surah.SurahHelper;
import bego.com.alqowyy.model.ItemQuran;
import bego.com.alqowyy.model.ItemSurah;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AlQowwyQuran extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerviewQuran adapter;
    QuranHelper surahHelper;
    ArrayList<ItemQuran> itemSurahs;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    String surahid;
    String suraname;
    public static String SURAID = "suraid";
    public static String SURATEXT = "suratext";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        surahid = getIntent().getStringExtra(SURAID);
        suraname=getIntent().getStringExtra(SURATEXT);
        getSupportActionBar().setTitle(suraname.toString());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupList();
    }

    private void setupList() {

        surahHelper = new QuranHelper(this);
        adapter = new RecyclerviewQuran(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        surahHelper.open();

        itemSurahs = surahHelper.getByKey(surahid);
        surahHelper.close();
        adapter.addItem(itemSurahs);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
