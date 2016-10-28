package com.example.android.miwokreworked;

/**
 * Created by Zack on 2016/10/25.
 */
public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;


    private int mImageresourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private int mAudioResourceId;

    /*
    * Returns the audio resource ID of the word
    * @return
    * */
    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int audioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceId = audioResourceId;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageresourceId, int audioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mImageresourceId = mImageresourceId;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceId = audioResourceId;
    }

    /*
    * Returns the image resource ID of the word
    * @return
    * */
    public int getmImageresourceId() {
        return mImageresourceId;
    }

    public void setmImageresourceId(int mImageresourceId) {
        this.mImageresourceId = mImageresourceId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setmDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setmMiwokTranslation(String mMiwokTranslation) {
        this.mMiwokTranslation = mMiwokTranslation;
    }

    public boolean hasImage(){
        return mImageresourceId != NO_IMAGE_PROVIDED;
    }
}
