package com.star.tablayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTextClick(View view) {
        Intent intent = new Intent(this, TextTabActivity.class);
        startActivity(intent);
    }

    public void onCustormClick(View view) {
        Intent intent = new Intent(this, CustomTabItemActivity.class);
        startActivity(intent);
    }

    public void onImageClick(View view) {
        Intent intent = new Intent(this, ImageTabActivity.class);
        startActivity(intent);
    }
}
