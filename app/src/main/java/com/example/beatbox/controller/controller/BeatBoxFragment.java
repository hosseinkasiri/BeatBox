package com.example.beatbox.controller.controller;

import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.beatbox.R;
import com.example.beatbox.controller.model.Sound;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeatBoxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeatBoxFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SoundAdapter mAdapter;
    private BeatBox mBeatBox;
    private SeekBar mSeekBar;
    private TextView mProgress;
    private AudioManager mAudioManager;

    public BeatBoxFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BeatBoxFragment newInstance() {
        BeatBoxFragment fragment = new BeatBoxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBeatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        findViews(view);
        if (isTablet())
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        else
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        handelSeekBar();

        mAdapter = new SoundAdapter(mBeatBox.getSounds());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void handelSeekBar() {
        mSeekBar.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        mSeekBar.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mProgress.setText("Playback speed :" + progress);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.beat_box_recyclerview);
        mSeekBar = view.findViewById(R.id.seek_bar);
        mProgress = view.findViewById(R.id.progress_text_view);
    }

    private class SoundHolder extends RecyclerView.ViewHolder{

        private Button mButton;
        private Sound mSound;

        public SoundHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.list_item_sound_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBeatBox.play(mSound);
                }
            });
        }
        public void bind(Sound sound){
            mSound = sound;
            mButton.setText(sound.getName());
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_sound,parent,false);
            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
    public boolean isTablet() {
        boolean xlarge = ((getActivity().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((getActivity().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
}