package com.example.heybrow.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heybrow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout mTilDisplayName, mTilEmail, mTilPass;
    EditText mEdtDisplayName, getmEdtDisplayEmail, mEdtPass;
    Button mBtnCreateAccount;
    String Display_name, User_email, User_pass;
    public static final String TAG = RegisterActivity.class.getSimpleName();
        //firebase
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mProgressDialog = new ProgressDialog(RegisterActivity.this);

        mTilDisplayName = findViewById(R.id.tildisplayname);
        mTilEmail = findViewById(R.id.tilemail);
        mTilPass = findViewById(R.id.tilpassword);

        mEdtDisplayName = findViewById(R.id.et_display_name);
        getmEdtDisplayEmail = findViewById(R.id.et_email);
        mEdtPass = findViewById(R.id.et_password);

        mBtnCreateAccount = findViewById(R.id.btnSubmit);

        mToolbar = findViewById(R.id.toolbar_register);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display_name = mEdtDisplayName.getText().toString();
                User_email = getmEdtDisplayEmail.getText().toString();
                User_pass = mEdtPass.getText().toString();
                if (!TextUtils.isEmpty(Display_name)) {
                    if (!TextUtils.isEmpty(User_email)) {
                        if (!TextUtils.isEmpty(User_pass)) {
                            mProgressDialog.setTitle("Registering user");
                            mProgressDialog.setMessage("Please wait while registering user acc !");
                            mProgressDialog.setCanceledOnTouchOutside(false);
                            mProgressDialog.show();
                            register_User(Display_name, User_email, User_pass);
                        } else {
                            Toast.makeText(RegisterActivity.this, "User Password can not be empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Please Enter Valid Email-id", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Display Name can not be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void register_User(final String display_name, String user_email, String user_pass) {
        Toast.makeText(this, "Register User", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(user_email, user_pass).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    HashMap<String , String> usermap = new HashMap<>();
                    usermap.put("name",display_name);
                    usermap.put("status","Hi there, I'm using HeyBrow Chat App. ");
                    usermap.put("image","default");
                    usermap.put("thumb_image","default");

                    mDatabase.setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                mProgressDialog.dismiss();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Error at database", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    mProgressDialog.hide();
                    Toast.makeText(RegisterActivity.this, "You got some error !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
