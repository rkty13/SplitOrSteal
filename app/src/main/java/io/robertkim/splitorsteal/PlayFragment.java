package io.robertkim.splitorsteal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.HashMap;

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
                final HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("user", ParseUser.getCurrentUser().getObjectId());
                joinAnonymousGame(params);
            }
        });

        mFriendlyButton = (Button) v.findViewById(R.id.friendlyButton);
        mFriendlyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        return v;
    }

    private void joinAnonymousGame(HashMap<String, Object> params) {
        ParseCloud.callFunctionInBackground("joinAnonymousGame", params, new FunctionCallback<ParseObject>() {
            public void done(final ParseObject parseObject, ParseException e) {
                if (e == null) {
                    Log.d("fdfsadf", "joon likes mean");
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("fffdsa", "joon is also gei");
                            Toast.makeText(getActivity(), parseObject.getObjectId(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Log.d("fdsafasdgadfh", e.getMessage());
                }
            }
        });
    }
}
