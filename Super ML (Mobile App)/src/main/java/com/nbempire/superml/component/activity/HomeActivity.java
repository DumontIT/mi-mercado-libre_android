package com.nbempire.superml.component.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.nbempire.superml.R;
import com.nbempire.superml.domain.Product;
import com.nbempire.superml.service.ProductService;
import com.nbempire.superml.service.impl.ProductServiceImpl;

import java.util.Locale;


public class HomeActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "HomeActivity";

    private static ProductService productService = new ProductServiceImpl();

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the sections. We use a {@link FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory intensive, it may be best to switch to a {@link
     * android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    private static EditText query;

    private static TextView averagePrice;

    private static TextView minimumPrice;

    private static TextView maximumPrice;

    private static TextView moneySymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence result = null;

            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    result = getString(R.string.title_section_average_price).toUpperCase(l);
                    break;
                case 1:
                    result = getString(R.string.title_section_my_queries).toUpperCase(l);
            }
            return result;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private int sectionNumer;

        /**
         * The fragment argument representing the section number for this fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment(sectionNumber);
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        public PlaceholderFragment() {
        }

        public PlaceholderFragment(int sectionNumer) {
            this.sectionNumer = sectionNumer;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int fragmentLayoutId = R.layout.fragment_average_price;
            switch (sectionNumer) {
                case 2:
                    fragmentLayoutId = R.layout.fragment_my_queries;
            }

            query = (EditText) container.findViewById(R.id.homeSearchQuery);
            averagePrice = (TextView) container.findViewById(R.id.homeAveragePrice);
            minimumPrice = (TextView) container.findViewById(R.id.homeMinimumPrice);
            maximumPrice = (TextView) container.findViewById(R.id.homeMaximumPrice);
            moneySymbol = (TextView) container.findViewById(R.id.homeMoneySymbol);

            return inflater.inflate(fragmentLayoutId, container, false);
        }
    }

    public void searchAveragePrice(View view) {
        Log.i(TAG, "Searching product: " + query.getText());

        updateViewsVisibility(View.INVISIBLE, new View[]{averagePrice, minimumPrice, maximumPrice, moneySymbol});

        //  TODO : Do this in an AsyncTask because it is required in lastest Android versions.
        Product product = productService.findByQuery(query.getText().toString());

        averagePrice.setText(String.valueOf(product.getAveragePrice()));
        minimumPrice.setText(String.valueOf(product.getMinimumPrice()));
        maximumPrice.setText(String.valueOf(product.getMaximumPrice()));

        updateViewsVisibility(View.VISIBLE, new View[]{averagePrice, minimumPrice, maximumPrice, moneySymbol});
    }

    private void updateViewsVisibility(final int visibility, View[] views) {
        for (View eachView : views) {
            eachView.setVisibility(visibility);
        }
    }
}
