package bego.com.alqowyy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.daimajia.numberprogressbar.NumberProgressBar;

import bego.com.alqowyy.service.LoadData;
import bego.com.alqowyy.util.AppPreference;

public class MainActivity extends AppCompatActivity {
    NumberProgressBar progressBar;
    AppPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = (NumberProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        preference = new AppPreference(MainActivity.this);
        Boolean firstRun = preference.getFirstRun();
        if (firstRun) {
            StartService();
        } else {
            Thread mThread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(5000);
                        Intent i = new Intent(MainActivity.this, AlQowyyHome.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            mThread.start();
        }
    }

    private void StartService() {
       // progressBar.setVisibility(View.VISIBLE);
        new LoadData(this, progressBar).execute();
    }

}
