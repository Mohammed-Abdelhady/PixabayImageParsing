package com.programming.congar.pixabayimageparsing;

public class Image {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public Image(String imageUrl, String creator, int likes) {
        mImageUrl = imageUrl;
        mCreator = creator;
        mLikes = likes;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }

    public int getLikeCount() {
        return mLikes;
    }
}
