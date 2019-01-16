package com.janus.bookCake.presentation.ui.fragments.tabs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.janus.bookCake.R;
import com.janus.bookCake.presentation.ui.fragments.BucketFragment;


public class TabStripFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private int mainColor = 0xFFFFFFFF;
    public static final String TAG = TabStripFragment.class
            .getSimpleName();
    private PagerSlidingTabStrip tabs;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_strip, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);

        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        tabs.setIndicatorColor(mainColor);
        tabs.setIndicatorHeight((int) getActivity().getResources().getDimension(R.dimen.tabStripIndicatorSize));

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setViewPager(pager);

        tabs.setOnPageChangeListener(this);
        tabs.setUnderlineColor(0xF5F5F5);

        LinearLayout mTabsLinearLayout = ((LinearLayout) tabs.getChildAt(0));
        for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);

            if(i == 1){
                tv.setTextColor(mainColor);
                tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
            } else {
                tv.setTextColor(mainColor);
                tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
            }
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        LinearLayout mTabsLinearLayout = ((LinearLayout) tabs.getChildAt(0));
        for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
            if(i == position){
                tv.setTextColor(mainColor);
                tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
            } else {
                tv.setTextColor(mainColor);
                tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static TabStripFragment newInstance() {
        return new TabStripFragment();
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES;
        private Fragment[] pages;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            TITLES = new String[]{"Appointments", "My Work Place"};
            pages = new Fragment[TITLES.length];
            pages[1] = new BucketFragment();
            pages[0] = new BucketFragment();

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {


            return pages[position];

        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }
}
