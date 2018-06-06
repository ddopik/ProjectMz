package com.spade.mazda.ui.about_us.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.spade.mazda.R;
import com.spade.mazda.base.BaseFragment;

public class AboutUsFragment extends BaseFragment {


    private View mainView;
    private LinearLayout callBlock;
    private ImageView faceBookIcon, twitterIcon, InstaIcon, youtubeIcon;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_about_us, container, false);
        initViews();
        return mainView;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {

        faceBookIcon = mainView.findViewById(R.id.face_book_link);
        twitterIcon = mainView.findViewById(R.id.twitter_link);
        InstaIcon = mainView.findViewById(R.id.insta_link);
        youtubeIcon = mainView.findViewById(R.id.youtube_link);
        callBlock = mainView.findViewById(R.id.call_block);

        faceBookIcon.setOnClickListener(view -> openLink(getResources().getString(R.string.facebook_link)));
//        InstaIcon.setOnClickListener(view -> openLink());
//        twitterIcon.setOnClickListener(view -> openLink());
        youtubeIcon.setOnClickListener(view -> openLink(getResources().getString(R.string.youtube_link)));
        callBlock.setOnClickListener(view -> callMazdaCenter());
    }

    private void callMazdaCenter() {

        final Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getResources().getString(R.string.mazda_number)));
        if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.no_dial_support), Toast.LENGTH_LONG).show();
        }


    }

    public void openLink(String link) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }


}
