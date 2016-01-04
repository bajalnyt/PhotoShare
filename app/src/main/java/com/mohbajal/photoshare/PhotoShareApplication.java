package com.mohbajal.photoshare;

import android.app.Application;

import com.mohbajal.photoshare.models.Photo;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

/**
 * Created by 908752 on 1/3/16.
 */
public class PhotoShareApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Photo.class);

        Parse.initialize(this);
        ParseFacebookUtils.initialize(this);

    }
}
