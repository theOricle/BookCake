package com.janus.bookCake.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.janus.bookCake.R;
import com.janus.bookCake.presentation.ui.base.BaseActivity;
import com.janus.bookCake.presentation.ui.fragments.BucketFragment;
import static com.janus.bookCake.data.repositories.datasource.UserDataSourceRemote.firebaseAuth;


import butterknife.ButterKnife;

public class BucketActivity extends BaseActivity {

    /**
     * Allows to start this activity
     * @param context
     */
    public static void startMe(Context context) {
        Intent intent = new Intent(context, BucketActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    /**
     * Allows to get the intent to launch this activity from a notification
     * With a given preselected category name
     * @param context
     */
    public static Intent getIntentForNotification(Context context) {
        Intent intent = new Intent(context, BucketActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);
        ButterKnife.bind(this);

        this.getExtras(getIntent());
        this.setUpToolbar();

        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, BucketFragment.newInstance())
                .commit();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_popup_bucket, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();

        // Do the task
        if (menuItem == R.id.menu_action_logout) {
            firebaseAuth.signOut();
            finish();
            Intent myInt = new Intent(BucketActivity.this, OnBoardingActivity.class);
            startActivity(myInt);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}