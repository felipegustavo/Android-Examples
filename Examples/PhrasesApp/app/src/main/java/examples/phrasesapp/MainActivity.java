package examples.phrasesapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Map<String, Integer> sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sounds = new HashMap<>(8);
        sounds.put("do_you_speak_english", R.raw.doyouspeakenglish);
        sounds.put("good_evening", R.raw.goodevening);
        sounds.put("hello", R.raw.hello);
        sounds.put("how_are_you", R.raw.howareyou);
        sounds.put("i_live_in", R.raw.ilivein);
        sounds.put("my_name_is", R.raw.mynameis);
        sounds.put("please", R.raw.please);
        sounds.put("welcome", R.raw.welcome);
    }

    public void tappedMsg(View view) {
        int resource = sounds.get(view.getTag());
        MediaPlayer mediaPlayer = MediaPlayer.create(this, resource);
        mediaPlayer.start();
    }

}
