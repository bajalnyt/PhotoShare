package com.mohbajal.photoshare;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 908752 on 1/3/16.
 */
public class PhotosActivity extends AppCompatActivity {

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);
    }
}
