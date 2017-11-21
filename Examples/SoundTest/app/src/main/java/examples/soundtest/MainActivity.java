package examples.soundtest;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;
    private AudioManager audioManager;

    private SeekBar volumeCtrl;
    private SeekBar musicCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volumeCtrl = (SeekBar) findViewById(R.id.volume_ctrl);
        musicCtrl = (SeekBar) findViewById(R.id.music_ctrl);

        mPlayer = MediaPlayer.create(this, R.raw.laugh);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        volumeCtrl.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeCtrl.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        volumeCtrl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        musicCtrl.setMax(mPlayer.getDuration());

        musicCtrl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayer.start();
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                musicCtrl.setProgress(mPlayer.getCurrentPosition());
            }
        }, 0, 1000);
    }

    public void playSong(View view) {
        mPlayer.start();
    }

    public void pauseSong(View view) {
        mPlayer.pause();
    }

}