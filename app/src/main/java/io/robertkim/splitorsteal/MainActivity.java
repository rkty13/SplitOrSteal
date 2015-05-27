package io.robertkim.splitorsteal;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.parse.ui.ParseLoginBuilder;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
        startActivityForResult(builder.build(), 0);
    }
}
