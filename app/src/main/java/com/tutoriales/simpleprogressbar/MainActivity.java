package com.tutoriales.simpleprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.time.Clock;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbCicular;
    ProgressBar pbHorizontal;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbCicular=findViewById(R.id.pbCircular);
        pbHorizontal=findViewById(R.id.pbHorizontal);
        pbCicular.setVisibility(View.INVISIBLE);
        pbHorizontal.setVisibility(View.INVISIBLE);
        handler= new Handler();
    }

    public void startProgressBars(View view){
        if(pbCicular.getVisibility()==View.INVISIBLE && pbHorizontal.getVisibility()==View.INVISIBLE){

            pbCicular.setVisibility(View.VISIBLE);
            pbHorizontal.setVisibility(View.VISIBLE);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i <= 10; i++) {
                        SystemClock.sleep(500);
                        int progress = i*10;
                        handler.post(() -> {
                            pbCicular.setProgress(progress);
                            pbHorizontal.setProgress(progress);

                            if(progress==100){
                                Toast.makeText(getApplicationContext(),"Thread finalize",Toast.LENGTH_SHORT).show();
                                pbCicular.setVisibility(View.INVISIBLE);
                                pbHorizontal.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }
            });
            thread.start();
        }
    }
}