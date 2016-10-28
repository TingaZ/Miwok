package com.example.android.miwokreworked;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zack on 2016/10/25.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    private int mColourResouceId;

    public WordAdapter(Context context, ArrayList<Word> words, int ColourResouceId) {
        super(context, 0, words);
        mColourResouceId = ColourResouceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwork_text_view);
        miwokTextView.setText(currentWord.getmMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getmDefaultTranslation());

        ImageView imageResourceId = (ImageView) listItemView.findViewById(R.id.image_resource);
        if(currentWord.hasImage()) {
            imageResourceId.setImageResource(currentWord.getmImageresourceId());
            imageResourceId.setVisibility(View.VISIBLE);
        }
        else{
            imageResourceId.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);

        int colour = ContextCompat.getColor(getContext(), mColourResouceId);

        textContainer.setBackgroundColor(colour);

        return listItemView;

    }
}
