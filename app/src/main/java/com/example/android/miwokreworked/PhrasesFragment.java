package com.example.android.miwokreworked;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    ArrayList<Word> words = new ArrayList<Word>();
    MediaPlayer mediaPlayer;

    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener monAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //the AUDIO_LOSS_TRANSIENT case means that we've lost audio focus in a short
                // space of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sounds but at a lower volume.
                //We use both cases the same way because our app is playing short sound file.

                //Pause playback and reset player to the start of the file.
                // That way, we play the word from the beginning when we resume playback

                //pause playback
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //Resume Playback
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //The AUDIOFOCUS_LOSS case means we've lost audio focus and stop playback and clean up resources
                releaseMediaPlayer();
                //stop Playback
            }
        }
    };
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);



        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is you name?", "tina oyaase'na", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyasset", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michaksas", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "aanas'aa", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I'm coming", "haa aanam", R.raw.phrase_yes_im_coming));


        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();


                //Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(monAudioFocusChangeListener,
                        //use the music stream.
                        AudioManager.STREAM_MUSIC,
                        //Request permanent Focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //We have audio focus now

                    //Create and setup the {@Link MediaPlayer} for the audio resource associated
                    // with the current word
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());
                    mediaPlayer.start();

                    //setup a listener on the media player, so that we can stop and release the
                    //media player once the sounds has finished playing.
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer(){
        if (mediaPlayer != null)
            mediaPlayer.release();

        mediaPlayer = null;
        mAudioManager.abandonAudioFocus(monAudioFocusChangeListener);
    }

}
