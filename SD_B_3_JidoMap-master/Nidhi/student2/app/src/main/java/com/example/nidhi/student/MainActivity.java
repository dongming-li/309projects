package com.example.nidhi.student;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE=1;
    ImageView imageToupload, downloadedImage;
    Button bUploadImage, bdownloadImage;
    EditText UploadImageName, DownloadImageName;


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                break;
            case R.id.Uploadimage:
                break;

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null  ){
            Uri selectedImage = data.getData();
            imageToupload.setImageURI(selectedImage);


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageToupload = (ImageView) findViewById(R.id.imageToUpload);


        bUploadImage = (Button) findViewById(R.id. Uploadimage);

        UploadImageName = (EditText) findViewById(R.id.UploadName);


        imageToupload.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);
        bdownloadImage.setOnClickListener(this);






    }
}
