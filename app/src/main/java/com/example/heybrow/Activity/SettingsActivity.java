package com.example.heybrow.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.heybrow.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private CircleImageView mImgView;
    TextView mDisplayName;
    private Button mBtnChangePicture,mBtnchangeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mImgView = findViewById(R.id.image_view);
        mDisplayName = findViewById(R.id.displayName);
        mBtnChangePicture = findViewById(R.id.buttonchangeimage);
        mBtnchangeStatus = findViewById(R.id.buttonchangestatus);

    }
}
