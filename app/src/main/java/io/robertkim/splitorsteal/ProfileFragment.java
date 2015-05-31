package io.robertkim.splitorsteal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override public void onCreate(Bundle savedInstanceState) {
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

        return v;
    }
}
