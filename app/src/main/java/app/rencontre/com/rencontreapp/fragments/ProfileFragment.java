package app.rencontre.com.rencontreapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import app.rencontre.com.rencontreapp.R;

/**
 * Created by famille on 5/30/2018.
 */

public class ProfileFragment extends Fragment {
    Context mContext;
    FirebaseUser user;

    public ProfileFragment() {

    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.profile, container, false);
        TextView username = result.findViewById(R.id.profileusername);
        TextView useremail = result.findViewById(R.id.profileemail);
        ImageView img = (ImageView) result.findViewById(R.id.profileImg);
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
           // Uri url = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
            Glide.with(mContext).load(getImage()).apply(RequestOptions.circleCropTransform()).into(img);
            username.setText(user.getDisplayName());
            useremail.setText(user.getEmail());
        }

        return result;
    }

    private String getImage() {

        String facebookUserId = "";
        user = FirebaseAuth.getInstance().getCurrentUser();

        // find the Facebook profile and get the user's id
        for (UserInfo profile : user.getProviderData()) {
            // check if the provider id matches "facebook.com"
            if (FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
                facebookUserId = profile.getUid();
            }
        }

        // construct the URL to the profile picture, with a custom height
        // alternatively, use '?type=small|medium|large' instead of ?height=
        String photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?height=500";
        return  photoUrl;
        // (optional) use Picasso to download and show to image
        //Picasso.with(this).load(photoUrl).into(profilePicture);
    }

}
