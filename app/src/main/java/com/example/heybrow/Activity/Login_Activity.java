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

public class Login_Activity extends AppCompatActivity {
    TextInputLayout mTilEmail, mTilPass;
    EditText getmEdtDisplayEmail, mEdtPass;
    Button mBtnCreateAccount;
    Toolbar mToolbar;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        mAuth = FirebaseAuth.getInstance();
        mToolbar = findViewById(R.id.toolbar_login);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mTilEmail = findViewById(R.id.tilemail);
        mTilPass = findViewById(R.id.tilpassword);

        getmEdtDisplayEmail = findViewById(R.id.et_email);
        mEdtPass = findViewById(R.id.et_password);

        mProgressDialog =  new ProgressDialog(Login_Activity.this);

        mBtnCreateAccount = findViewById(R.id.btnlogin);


        mBtnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mTilEmail.getEditText().getText().toString();
                String pass = mTilPass.getEditText().getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass)){
                    mProgressDialog.setTitle("Logging in");
                    mProgressDialog.setMessage("Please wait while checking credentials...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    loginUser(email,pass);
                }else {
                    Toast.makeText(Login_Activity.this, "Email and password can not be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void loginUser(String email, String pass) {

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mProgressDialog.dismiss();
                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else {
                        mProgressDialog.hide();
                        Toast.makeText(Login_Activity.this, "Login unsucessfull check form and try again...", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }
}
