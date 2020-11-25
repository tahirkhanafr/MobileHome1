package com.example.mobilehome;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView img;
    TextView mobilename,mobileinfo,price, contact,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        img=findViewById(R.id.detilofmobile);
        mobilename=findViewById(R.id.mobilename);
        price=findViewById(R.id.mobileprice);
        mobileinfo=findViewById(R.id.mobileinfo);
        contact=findViewById(R.id.contact);
        location=findViewById(R.id.location);

//        img.setImageResource(getIntent().getIntExtra("image",0));
        Glide.with(this).load(getIntent().getStringExtra("image")).into(img);
        mobilename.setText(getIntent().getStringExtra("Mobilename"));
        price.setText(getIntent().getStringExtra("Price"));
        mobileinfo.setText(getIntent().getStringExtra("Specification"));
        contact.setText(getIntent().getStringExtra("Contect"));
        location.setText(getIntent().getStringExtra("allcities"));

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this, "wanna contact the owner!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
