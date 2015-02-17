package com.example.mustafaguven.testproject;

import android.content.Context;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MustafaGuven on 16.2.2015.
 */
public class User {

    public User(int id, String fullname, int color, int drawableId) {
        this.mId = id;
        this.mFullName = fullname;
        this.mBackgroundColor = color;
        this.mDrawableId = drawableId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public void setDrawableId(int mDrawableId) {
        this.mDrawableId = mDrawableId;
    }

    private int mId;
    private String mFullName;
    private int mDrawableId;
    private int mBackgroundColor;

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }


    public static List<User> getUserList(Context context) {
        List<User> a = new ArrayList<>();
        int i=0;
        a.add(new User(++i, "GERRARD BASGAN", Color.CYAN, context.getResources().getIdentifier("gerrard", "drawable", context.getPackageName())));
        a.add(new User(++i, "Merve Reyiz", Color.BLUE, context.getResources().getIdentifier("merve", "drawable", context.getPackageName())));
        a.add(new User(++i, "Bekir Reyiz",  Color.GREEN, context.getResources().getIdentifier("bekirreyiz", "drawable", context.getPackageName())));
        a.add(new User(++i, "Üşüyoruz Reyiz",  Color.RED, context.getResources().getIdentifier("bankadagitti", "drawable", context.getPackageName())));
        a.add(new User(++i, "Murat Basgan",  Color.YELLOW, context.getResources().getIdentifier("murat", "drawable", context.getPackageName())));
        return a;
    }
}
