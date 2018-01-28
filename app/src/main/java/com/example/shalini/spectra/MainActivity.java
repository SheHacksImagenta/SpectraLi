package com.example.shalini.spectra;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.microsoft.projectoxford.emotion.EmotionServiceClient;
import com.microsoft.projectoxford.emotion.EmotionServiceRestClient;
import com.microsoft.projectoxford.emotion.contract.RecognizeResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    GridLayout mainGrid;

    public EmotionServiceClient emotionServiceClient = new EmotionServiceRestClient("e53f5abc2a534272a4544d2f65dddef9");
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            },SPLASH_TIME_OUT);
        }
    }

    public void setSingleEvent(GridLayout mainGrid){
        for(int i = 0; i < mainGrid.getChildCount(); i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener(){
               public void onClick(View view){
                   //Toast.makeText(MainActivity.this, "Clicked at " + finalI, Toast.LENGTH_SHORT).show();
                   if (finalI == 0) { //happy
                       Intent intent = new Intent(MainActivity.this, ShowHappyActivity.class);
                       startActivity(intent);
                   }
                   if (finalI == 1){ //sad
                       Intent intent = new Intent(MainActivity.this, ShowSadActivity.class);
                       startActivity(intent);
                   }
                   if (finalI == 2){ //angry
                       Intent intent = new Intent(MainActivity.this, ShowAngryActivity.class);
                       startActivity(intent);
                   }
                   if (finalI == 3){ //scared
                       Intent intent = new Intent(MainActivity.this, ShowScaredActivity.class);
                       startActivity(intent);
                   }
                   if (finalI == 4) { //stressed
                       Intent intent = new Intent(MainActivity.this, ShowStressedActivity.class);
                       startActivity(intent);
                   }
               }
            });
        }
    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void imageActivityResponse(View view){ //from process button
        Intent intent = new Intent(this, SelectImageActivity.class);
        startActivity(intent);
    }

    public void recognitionActivity(View view){ //from process button
        Intent intent = new Intent(this, RecognizeActivity.class);
        startActivity(intent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            ImageView mImageView;
//            mImageView.setImageBitmap(imageBitmap);
//        }
//    }

//    public void happyResponse(View view){
//        Intent intent = new Intent(this, ShowHappyActivity.class);
//        startActivity(intent);
//    }
//
//    }

}
