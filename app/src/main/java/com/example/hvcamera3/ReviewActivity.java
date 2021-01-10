package com.example.hvcamera3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ReviewActivity extends AppCompatActivity {

    private ImageView reviewImageView;
    private Button closeButton;
    private String image_path;
    private Bitmap rotatedBitmap;

    private int cameraApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        reviewImageView = findViewById(R.id.review_image_view);
        closeButton = findViewById(R.id.close_button);

        image_path = getIntent().getStringExtra("imagePath");
        cameraApiVersion = getIntent().getIntExtra("version",1);
        Bitmap bitmap = BitmapFactory.decodeFile(image_path);

        if(cameraApiVersion == 1){
            rotatedBitmap = rotate(bitmap);
        } else{
            rotatedBitmap = bitmap;
        }


        reviewImageView.setImageBitmap(rotatedBitmap);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Bitmap rotate(Bitmap bitmap) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.setRotate(90);

        return Bitmap.createBitmap(bitmap,0,0,w,h,matrix,true);
    }
}