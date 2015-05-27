package io.robertkim.splitorsteal;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by robertkim on 5/27/15.
 */
public class SplitorStealApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this,
                this.getString(R.string.parse_app_id),
                this.getString(R.string.parse_client_key));
    }
}
