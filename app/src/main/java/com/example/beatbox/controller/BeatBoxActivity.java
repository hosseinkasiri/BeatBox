package com.example.beatbox.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.beatbox.R;

public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    public Fragment mFragment() {
        return BeatBoxFragment.newInstance();
    }
}