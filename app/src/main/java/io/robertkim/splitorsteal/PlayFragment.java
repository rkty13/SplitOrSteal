package io.robertkim.splitorsteal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by robertkim on 6/4/15.
 */
public class PlayFragment extends Fragment {

    Button mAnonymousButton;
    Button mFriendlyButton;

    private static final String TAG = "PlayFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_play, parent, false);
        mAnonymousButton = (Button) v.findViewById(R.id.anonymousButton);
        mAnonymousButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putBoolean("anonymous", true);
                FragmentManager fm = getFragmentManager();
                WagerFragment fragment = new WagerFragment();
                fragment.setArguments(b);
                fm.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainer, fragment, "WagerFragment")
                        .commit();
            }
        });

        mFriendlyButton = (Button) v.findViewById(R.id.friendlyButton);
        mFriendlyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putBoolean("anonymous", false);
                FragmentManager fm = getFragmentManager();
                WagerFragment fragment = new WagerFragment();
                fragment.setArguments(b);
                fm.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainer, fragment, "WagerFragment")
                        .commit();
            }
        });
        return v;
    }
}
