package net.trancool.instagramclonev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity
{
    public static final String TAG = "LoginActivity";

    private EditText et_userName;
    private EditText et_password;
    private Button btn_login;
    private Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(ParseUser.getCurrentUser() != null)
        {
            goMainActivity();
        }


        et_userName = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);


        //createing the btn
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username  = et_userName.getText().toString();
                String password = et_password.getText().toString();
                loginUser(username, password);
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username  = et_userName.getText().toString();
                String password = et_password.getText().toString();

                signup(username, password);

            }
        });



    }

    private void signup(String username, String password)
    {
        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e== null)
                {
                    //sign up with succcess!
                    goMainActivity();
                    Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT);
                }
                else
                {
                    //sign up failed!
                    return;
                }
            }
        });


    }

    private void loginUser(String username, String password)
    {

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e)
            {
                if(e != null)
                {
                    Log.i("Error:", "There was an issue login" + e);

                    return;
                }

                goMainActivity();
                Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT);



            }
        });
    }

    //logout function




    private void goMainActivity()
    {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();


    }



}