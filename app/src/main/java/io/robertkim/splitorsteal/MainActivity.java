package io.robertkim.splitorsteal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (ParseUser.getCurrentUser() == null) {
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
        } else {
            Log.d("ffdsa", "asdfsdfasdf");
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.profileFragmentContainer);

            if (fragment == null) {
                fragment = new ProfileFragment();
                fm.beginTransaction()
                        .add(R.id.profileFragmentContainer, fragment)
                        .commit();
            }
        }
    }
}
