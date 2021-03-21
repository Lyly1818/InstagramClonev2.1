package net.trancool.instagramclonev2;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp  extends Application
{
    public final String APPLICATION_KEY = "yU25etoDuGg7KfWfYTw84xaUB2eHCszgijGpUcOM";
    public final String CLIENT_KEY = "TeUmOhy7FjffHciKizTk0GzUd6MY5i3yGif2ZV9o";
    public final String SERVER =  "https://parseapi.back4app.com/";



    @Override
    public void onCreate()
    {
        super.onCreate();



        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APPLICATION_KEY)
                // if defined
                .clientKey(CLIENT_KEY)
                .server(SERVER)
                .build()
        );
    }
}
