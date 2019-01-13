package com.janus.bookCake.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.janus.bookCake.R;
import com.janus.bookCake.presentation.ui.base.BaseActivity;
import com.janus.bookCake.presentation.ui.base.BaseFragment;
import com.janus.bookCake.presentation.ui.fragments.LandingFragment;
import com.janus.bookCake.presentation.ui.fragments.LoginFragment;
import com.cremy.greenrobotutils.library.ui.SnackBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnBoardingActivity extends BaseActivity implements
        BaseFragment.OnShowMessageListener,
        BaseFragment.OnChangeFragment {

    @BindView(R.id.root_view)
    CoordinatorLayout rootView;

    private String currentFragmentTag;
    private static final int REQUEST_PHONE_VERIFICATION = 1080;
    private String mobileNumber;


    /**
     * Allows to start this activity
     * @param context
     */
    public static void startMe(Context context) {
        Intent intent = new Intent(context, OnBoardingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);

        this.getExtras(getIntent());
        this.setUpToolbar();

        initFragment(LandingFragment.TAG, null);
    }

    private void initFragment(String fragmentTag,
                              @Nullable Pair<View, String> sharedElement) {
        currentFragmentTag = fragmentTag;
        Fragment fragment = getFragment(fragmentTag);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, getFragment(fragmentTag));

        if (sharedElement != null) {
            fragmentTransaction.addSharedElement(sharedElement.first, sharedElement.second);
            fragment.setSharedElementEnterTransition(getWindow().getSharedElementEnterTransition());
            fragment.setSharedElementReturnTransition(getWindow().getSharedElementReturnTransition());
        }
        fragmentTransaction.replace(R.id.content_frame, fragment).commit();
    }

    private Fragment getFragment(String fragmentTag) {

        if (fragmentTag.equals(LoginFragment.TAG)) {
            return LoginFragment.newInstance();
        }

        return LandingFragment.newInstance();
    }

    @Override
    public void getExtras(Intent _intent) {

    }

    @Override
    public void closeActivity() {
        this.finish();
    }

    @Override
    public void setUpToolbar() {

    }

    @Override
    public Fragment getAttachedFragment(int id) {
        return getSupportFragmentManager().findFragmentById(id);
    }

    @Override
    public Fragment getAttachedFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    public void onShowMessage(String message) {
        SnackBarUtils.showSimpleSnackbar(rootView, message);
    }

    @Override
    public void onChangeFragment(String fragmentTag,
                                 @Nullable Pair<View, String> sharedElement) {
        initFragment(fragmentTag, sharedElement);
    }

    @Override
    public void onBackPressed() {
        if (!currentFragmentTag.equals(LandingFragment.TAG)) {
            initFragment(LandingFragment.TAG, null);
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case REQUEST_PHONE_VERIFICATION:
// If mobile number is verified successfully then you get your phone number to perform further operations.
                if (data != null && data.hasExtra("PHONE_NUMBER") && data.getStringExtra("PHONE_NUMBER") != null) {
                    String phoneNumber = data.getStringExtra("PHONE_NUMBER");
                    mobileNumber = phoneNumber;
                    BucketActivity.startMe(this);
                } else {
                    // If mobile number is not verified successfully You can hendle according to your requirement.
                    Toast.makeText(this, getString(R.string.fail_auth), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}