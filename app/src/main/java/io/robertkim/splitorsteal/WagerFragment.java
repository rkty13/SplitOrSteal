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

import java.util.ArrayList;
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
        ViewGroup.LayoutParams bLayout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        ArrayList<Long> wagers = genWagers(ParseUser.getCurrentUser().getLong("balance"));

        for (int i = 0; i < wagers.size(); i++) {
            Button b = new Button(getActivity());
            b.setText(Long.toString(wagers.get(i)));
            b.setLayoutParams(bLayout);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO
                }
            });
            insert.addView(b);
        }
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

    private ArrayList<Long> genWagers(long balance) {
        ArrayList<Long> gen = new ArrayList<Long>();
        int mult = 5;
        for (long i = 100; i <= balance; i *= mult) {
            gen.add(i);
            mult = mult == 5 ? 2 : 5;
        }
        return gen;
    }

    private void joinFriendlyGame(String currentUserId, String friendUserId) {
        // TODO
    }
}