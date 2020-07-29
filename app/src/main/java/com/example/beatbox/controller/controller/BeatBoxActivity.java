package com.example.beatbox.controller.controller;

import androidx.fragment.app.Fragment;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    public Fragment mFragment() {
        return BeatBoxFragment.newInstance();
    }
}