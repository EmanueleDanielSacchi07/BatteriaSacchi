package com.example.batteriasacchi;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(8)
                .setAudioAttributes(attrs)
                .build();

        collegaPad(R.id.btnKick,        soundPool.load(this, R.raw.kick, 1));
        collegaPad(R.id.btnSnare,       soundPool.load(this, R.raw.snare, 1));
        collegaPad(R.id.btnHihatClosed, soundPool.load(this, R.raw.hihat_closed, 1));
        collegaPad(R.id.btnHihatOpen,   soundPool.load(this, R.raw.hihat_high, 1));
        collegaPad(R.id.btnTomHigh,     soundPool.load(this, R.raw.tom_high, 1));
        collegaPad(R.id.btnTomLow,      soundPool.load(this, R.raw.tom_low, 1));
        collegaPad(R.id.btnClap,        soundPool.load(this, R.raw.clap, 1));
        collegaPad(R.id.btnCrash,       soundPool.load(this, R.raw.crash, 1));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void collegaPad(int buttonId, int soundId) {
        findViewById(buttonId).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                soundPool.play(soundId, 1f, 1f, 1, 0, 1f);
            }
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}