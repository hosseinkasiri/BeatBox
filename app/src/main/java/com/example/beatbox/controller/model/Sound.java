package com.example.beatbox.controller.model;

public class Sound {
    private Integer mSoundId;
    private String mName;
    private String mAssetPath;

    public Sound(String assetPath) {
        this.mAssetPath = assetPath;
        String[] splits = assetPath.split("/");
        String fileName = splits[splits.length - 1];
        mName = fileName.substring(0,fileName.lastIndexOf("."));
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public String getName() {
        return mName;
    }

    public String getAssetPath() {
        return mAssetPath;
    }
}
