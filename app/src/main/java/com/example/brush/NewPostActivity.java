package com.example.brush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NewPostActivity extends AppCompatActivity {

    private ImageButton bidButton;
    private ImageButton galleryButton;
    private ImageButton announcementButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        bidButton = (ImageButton) findViewById(R.id.bidButton);
        galleryButton = (ImageButton) findViewById(R.id.galleryButton);
        announcementButton = (ImageButton) findViewById(R.id.announcementButton);

        bidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(NewPostActivity.this, BidPostActivity.class);
                startActivity(loginIntent);
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent registerIntent = new Intent(NewPostActivity.this, GalleryPostActivity.class);
                startActivity(registerIntent);

            }
        });

        announcementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent registerIntent = new Intent(NewPostActivity.this, AnnouncementPostActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}