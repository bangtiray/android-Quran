package bego.com.alqowyy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import bego.com.alqowyy.Adapter.RecyclerviewAdapter;
import bego.com.alqowyy.db.surah.SurahHelper;
import bego.com.alqowyy.model.ItemSurah;
import bego.com.alqowyy.util.ItemClickSupport;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AlQowyyHome extends AppCompatActivity {
    //    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.surah_click)
    ImageView btn_surah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_qowyy_home);
        ButterKnife.bind(this);

        btn_surah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlQowyyHome.this, AlQowwySurah.class);
                startActivity(i);
            }
        });

    }


}
