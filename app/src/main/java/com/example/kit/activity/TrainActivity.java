//CPR_tutorial
package com.example.kit.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.kit.R;
import com.example.kit.Sentence;
import com.example.kit.SentenceAdapter;

import java.util.ArrayList;

public class TrainActivity extends AppCompatActivity {

    MediaPlayer player;
    Toolbar myToolbar;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releasePlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        final ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        sentences.add(new Sentence("0.开始", R.raw.a));
        sentences.add(new Sentence("1.环境", R.raw.b));
        sentences.add(new Sentence("2.检查患者", R.raw.c));
        sentences.add(new Sentence("3.1拍肩呼喊", R.raw.d));
        sentences.add(new Sentence("3.2喂喂", R.raw.e));
        sentences.add(new Sentence("4.1检查呼吸", R.raw.f));
        sentences.add(new Sentence("4.2数数", R.raw.g));
        sentences.add(new Sentence("5.大声呼喊1", R.raw.h));
        sentences.add(new Sentence("6.大声呼喊2", R.raw.i));
        sentences.add(new Sentence("7.检查颈部", R.raw.j));
        sentences.add(new Sentence("8.松开衣服", R.raw.k));
        sentences.add(new Sentence("9.按压开始", R.raw.l));
        sentences.add(new Sentence("10.找点", R.raw.m));
        sentences.add(new Sentence("11.按压数数", R.raw.n));
        sentences.add(new Sentence("12.口对口人工呼吸", R.raw.o));
        sentences.add(new Sentence("13.摆位置", R.raw.p));
        sentences.add(new Sentence("14.捏住鼻孔", R.raw.q));
        sentences.add(new Sentence("15.嘴唇", R.raw.r));
        sentences.add(new Sentence("16.离开", R.raw.s));
        sentences.add(new Sentence("17.观察胸廓", R.raw.t));
        sentences.add(new Sentence("18.松开手指", R.raw.u));
        sentences.add(new Sentence("19.判断", R.raw.v));
        sentences.add(new Sentence("20.观察", R.raw.w));


        SentenceAdapter itemsAdapter = new SentenceAdapter(this, sentences);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releasePlayer();
                Sentence sentence = sentences.get(position);
                player = MediaPlayer.create(TrainActivity.this, sentence.getAudioResourceID());
                player.start();

                player.setOnCompletionListener(completionListener);
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_point:
                Intent i;

                i = new Intent(TrainActivity.this, CameraActivity.class);

                startActivity(i);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
