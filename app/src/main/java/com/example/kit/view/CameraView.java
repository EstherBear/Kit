package com.example.kit.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.kit.Camera2BasicFragment;
import com.example.kit.R;

public class CameraView extends View {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CameraView(Context context) {
        super(context);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CameraView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    FragmentManager fragmentManager;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

        Camera2BasicFragment myFragment = (Camera2BasicFragment) getFragmentManager().findFragmentByTag("camera");
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {
//        Fragment fragment = new PropertyFragment();

        if (fragmentManager == null) {
//            FragmentManager fragmentManager = fragment.getFragmentManager();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.container, Camera2BasicFragment.newInstance());
//            fragmentTransaction.replace(R.id.container, myFragment, "camera");
            fragmentTransaction.commit();
//
        }
//            getFragmentManager().beginTransaction()
//                .replace(R.id.container, Camera2BasicFragment.newInstance()).commit();
    }
}
