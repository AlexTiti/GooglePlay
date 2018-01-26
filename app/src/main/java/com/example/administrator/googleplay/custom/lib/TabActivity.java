package com.example.administrator.googleplay.custom.lib;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.googleplay.R;

public class TabActivity extends TabCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        TabHelper tabHelper = getTabHelper();

        CompatTab photosTab = tabHelper.newTab("photos")
                .setText(R.string.tab_photos)
                .setIcon(R.drawable.ic_tab_photos)
                .setTabListener(new InstantiatingTabListener(this, PhotosFragment.class));
        tabHelper.addTab(photosTab);

        CompatTab videosTab = tabHelper.newTab("videos")
                .setText(R.string.tab_videos)
                .setIcon(R.drawable.ic_tab_videos)
                .setTabListener(new InstantiatingTabListener(this, VideosFragment.class));
        tabHelper.addTab(videosTab);
    }

public static class InstantiatingTabListener implements CompatTabListener {

    private final TabCompatActivity mActivity;
    private final Class mClass;

    public InstantiatingTabListener(TabCompatActivity activity, Class<? extends Fragment> cls) {
        mActivity = activity;
        mClass = cls;
    }


    @Override
    public void onTabSelected(CompatTab tab, FragmentTransaction ft) {

        Fragment fragment = tab.getFragment();
        if (fragment == null) {
            fragment = Fragment.instantiate(mActivity, mClass.getName());
            tab.setFragment(fragment);
            ft.add(android.R.id.tabcontent, fragment, tab.getTag());
        } else {
            // If it exists, simply attach it in order to show it
            ft.attach(fragment);
        }
    }

    @Override
    public void onTabUnselected(CompatTab tab, FragmentTransaction ft) {
        Fragment fragment = tab.getFragment();
        if (fragment != null) {
            // Detach the fragment, because another one is being attached
            ft.detach(fragment);
        }
    }

    @Override
    public void onTabReselected(CompatTab tab, FragmentTransaction ft) {
        // User selected the already selected tab. Do nothing.
    }
}

public static class PhotosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setText(R.string.tab_photos);
        return textView;
    }
}

public static class VideosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setText(R.string.tab_videos);
        return textView;
    }
}
}
