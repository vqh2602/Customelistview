package com.example.customelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        String displayname = intent.getStringExtra("Displayname");
        Toast.makeText(MapActivity.this,"Xin Chào: "+displayname,Toast.LENGTH_LONG).show();
    }
}