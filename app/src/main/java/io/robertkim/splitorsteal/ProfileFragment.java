package io.robertkim.splitorsteal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by robertkim on 5/30/15.
 */
public class ProfileFragment extends Fragment {

    ImageView mImageView;
    RoundImage mRoundProfileImage;
    TextView mBalanceText;
    Button mPlayButton;

    private static final String TAG = "ProfileFragment";

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
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.profileFragmentContainer, new PlayFragment(), "PlayFragment")
                    .commit();
            }
        });
        return v;
    }
}
