package net.trancool.instagramclonev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity
{

    public static final String TAG = "MainActivity";
    private EditText description;
    private Button takeCapture;
    private Button submit;
    private ImageView postImage;
    private Button btn_logout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description = findViewById(R.id.et_description);
        takeCapture = findViewById(R.id.btn_takepicture);
        postImage = findViewById(R.id.iv_post_image);
        submit = findViewById(R.id.btn_submit);
        btn_logout = findViewById(R.id.btn_logout);

        queryPosts();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogout();
                goLoginActivity();
            }
        });


    }

    private void goLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private void userLogout()
    {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();//should == null
    }

    private void queryPosts()
    {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {

                if(e!= null)
                {
                    Log.e("Error: " , "Issue in getting the posts", e);
                    return;
                }

                for(Post post : posts)
                {

                    Log.i(TAG, "Post" + post.getDescription());
                }

            }
        });
    }
}