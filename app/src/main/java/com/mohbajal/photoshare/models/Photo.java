package com.mohbajal.photoshare.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by 908752 on 1/3/16.
 */
@ParseClassName("Photo")
public class Photo extends ParseObject {

    public Photo() {
    }

    public ParseUser getPhotographer(){
        return getParseUser("photographer");
    }
    public void setPhotographer(ParseUser photographer){
        put("photographer" , photographer);
    }

    public ParseFile getPhoto(){
        return getParseFile("photo");
    }
    public void setPhoto(ParseFile photo){
        put("photo", photo);
    }


}
