package com.androiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import static com.androiddemo.ImageListActivity.EXTRA_URL;

import com.squareup.picasso.Picasso;


public class Detail extends AppCompatActivity {


    //resim detay sayfası
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);

        ImageView imageView = findViewById(R.id.imageDetail);

        Picasso.with(Detail.this).load(imageUrl).into(imageView);

    }
}
