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
import android.widget.LinearLayout;
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
public class WagerFragment extends Fragment {

    private static final String TAG = "WagerFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wager, parent, false);
        LinearLayout insert = (LinearLayout) v.findViewById(R.id.wagerLayout);
        long balance = ParseUser.getCurrentUser().getInt("balance");
        int mult = 5;
        ViewGroup.LayoutParams bLayout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        for (long i = 100; i <= balance; i *= mult) {
            Button b = new Button(getActivity());
            b.setText(Long.toString(i));
            b.setLayoutParams(bLayout);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO
                }
            });
            insert.addView(b);
            mult = mult == 5 ? 2 : 5;
        }
        parent.addView(insert);
        return v;
    }

    private void joinAnonymousGame(String currentUserId) {
        final HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user", currentUserId);
        ParseCloud.callFunctionInBackground("joinAnonymousGame", params, new FunctionCallback<ParseObject>() {
            public void done(final ParseObject parseObject, ParseException e) {
                if (e == null) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), parseObject.getObjectId(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Log.d(TAG, e.getMessage());
                }
            }
        });
    }

    private void joinFriendlyGame(String currentUserId, String friendUserId) {
        // TODO
    }
}
