package com.janus.bookCake.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.janus.bookCake.R;
import com.janus.bookCake.presentation.ui.base.BaseActivity;
import com.janus.bookCake.presentation.ui.fragments.CreateTaskFragment;

public class CreateTaskActivity extends BaseActivity {

    /**
     * Allows to start this activity
     * @param context
     */
    public static void startMe(Context context) {
        Intent intent = new Intent(context, CreateTaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        this.getExtras(getIntent());
        this.setUpToolbar();

        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, CreateTaskFragment.newInstance())
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
}