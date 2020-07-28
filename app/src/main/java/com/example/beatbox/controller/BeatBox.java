package com.example.beatbox.controller;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.beatbox.controller.model.Sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String SOUND_FOLDER =  "sample_sound";
    private static final String TAG = "beatBox";
    private List<Sound> mSounds = new ArrayList<>();
    private AssetManager mAssetManager;

    public List<Sound> getSounds() {
        return mSounds;
    }

    public BeatBox(Context context) {
        mAssetManager = context.getAssets();
        try {
            String[] filesNames = mAssetManager.list(SOUND_FOLDER);
            for (String fileName : filesNames){
                String assetPath = SOUND_FOLDER + File.separator + fileName;
                Sound sound = new Sound(assetPath);
                mSounds.add(sound);
            }


        }catch (IOException e){
            e.printStackTrace();
        }

    }
}


