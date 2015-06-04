package io.robertkim.splitorsteal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.HashMap;

/**
 * Created by robertkim on 5/30/15.
 */
public class ProfileFragment extends Fragment {

    ImageView mImageView;
    RoundImage mRoundProfileImage;
    TextView mBalanceText;
    Button mPlayButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, parent, false);

        mImageView = (ImageView) v.findViewById(R.id.profileImage);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.default_profile);
        mRoundProfileImage = new RoundImage(bm);
        mImageView.setImageDrawable(mRoundProfileImage);
        mBalanceText = (TextView) v.findViewById(R.id.balanceText);
        mBalanceText.setText(Integer.toString(ParseUser.getCurrentUser().getInt("balance")));
        mPlayButton = (Button) v.findViewById(R.id.playButton);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asdfasdf", "joon is dum");
                final HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("user", ParseUser.getCurrentUser().getObjectId());
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

        });

        return v;
    }
}
