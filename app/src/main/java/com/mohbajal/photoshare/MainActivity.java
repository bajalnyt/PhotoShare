package com.mohbajal.photoshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.mohbajal.photoshare.models.Photo;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private int GALLERY_REQUEST_CODE = 2312;
    //@Bind(R.id.main_button_take_photo)  Button takePhotoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To test parse
        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bar");
        //testObject.saveInBackground();

        ButterKnife.bind(this);
    }


    @OnClick(R.id.main_button_take_photo)
    public void onTakePhotoButtonClicked(){
        //Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult( Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == RESULT_OK && data !=null){
                byte[] photoContents = loadImage(data.getData());

                /*ParseObject photoObject = new ParseObject("Photo");
                photoObject.put("photographer", ParseUser.getCurrentUser());
                if(photoContents != null)
                    photoObject.put("photo", new ParseFile(photoContents));
                */
                Photo photo = new Photo();
                photo.setPhotographer(ParseUser.getCurrentUser());
                if(photoContents != null)
                    photo.setPhoto(new ParseFile(photoContents));

                photo.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            //Success
                            Toast.makeText(MainActivity.this, "Succesfully saved photo", Toast.LENGTH_SHORT).show();
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private byte[] loadImage(Uri pathToImage) {
        try {

            InputStream fileInputStream = getContentResolver().openInputStream(pathToImage);

            return IOUtils.toByteArray(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
