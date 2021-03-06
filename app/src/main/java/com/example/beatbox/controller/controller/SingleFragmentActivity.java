package com.example.beatbox.controller.controller;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.beatbox.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment mFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager manager = getSupportFragmentManager();
        if (manager.findFragmentById(R.id.activity_fragment) == null){

            manager.beginTransaction()
                    .add(R.id.activity_fragment , mFragment())
                    .commit();
        }
    }
}
