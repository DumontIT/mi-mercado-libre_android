package com.nbempire.superml.component.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.nbempire.superml.R;
import com.nbempire.superml.domain.Category;
import com.nbempire.superml.domain.Product;
import com.nbempire.superml.service.ProductService;
import com.nbempire.superml.service.impl.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HomeActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "HomeActivity";

    private static ProductService productService = new ProductServiceImpl();

    private static SharedPreferences sharedPreferences;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the sections. We use a {@link FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory intensive, it may be best to switch to a {@link
     * android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static EditText query;

    private static TextView averagePrice;

    private static TextView minimumPrice;

    private static TextView maximumPrice;

    private static TextView moneySymbol;

    private static TextView countryLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding tab. We can also use ActionBar.Tab#select() to do this if we have a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter. Also specify this Activity object, which implements the
            // TabListener interface, as the callback (listener) for when this tab is selected.
            actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        boolean consumed;

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
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
            // getItem is called to instantiate the fragment for the given page. Return a PlaceholderFragment (defined as a static inner class below).
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

        /**
         * The fragment argument representing the section number for this fragment.
         */
        private static final String ARG_SECTION_NUMBER = "sectionNumber";

        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(arguments);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int sectionNumber = this.getArguments().getInt(ARG_SECTION_NUMBER);
            Log.d(TAG, "Creating fragment view for sectionNumber: " + sectionNumber);

            int fragmentLayoutId = R.layout.fragment_average_price;

            switch (sectionNumber) {
                case 2:
                    fragmentLayoutId = R.layout.fragment_my_queries;
            }
            View createdLayoutView = inflater.inflate(fragmentLayoutId, container, false);

            switch (sectionNumber) {
                case 1:
                    onCreateViewForAveragePriceFragment(createdLayoutView);
                    break;
                case 2:
                    onCreateViewForMyQueriesFragment(createdLayoutView);
            }

            return createdLayoutView;
        }

        private void onCreateViewForAveragePriceFragment(View container) {
            query = (EditText) container.findViewById(R.id.homeSearchQuery);
            averagePrice = (TextView) container.findViewById(R.id.homeAveragePrice);
            minimumPrice = (TextView) container.findViewById(R.id.homeMinimumPrice);
            maximumPrice = (TextView) container.findViewById(R.id.homeMaximumPrice);
            moneySymbol = (TextView) container.findViewById(R.id.homeMoneySymbol);
            countryLabel = (TextView) container.findViewById(R.id.homeCountryLabel);

            String choosenCountry = getEntryForListPreferenceValue(sharedPreferences.getString("country", "MLA"), R.array.pref_countries_values,
                                                                   R.array.pref_countries_entries);
            countryLabel.setText(String.format("%s %s", getText(R.string.home_checking_ml_site), choosenCountry));
        }

        /**
         * Get selected setting entry (the label) for a specified ListPreference entries-values pair.
         *
         * @param settingValue
         *         The setting stored value. You can get that value by doing sharedPreferences.getString({keyName}, "defaultValue").
         * @param valuesResourceId
         *         Resource ID for the string array resource where to look for the specified {@code settingValue}. This resource is set in
         *         ListPreference definition.
         * @param entriesResourceId
         *         Resource ID that contains entries for the specified {@code valuesResourceId}.
         *
         * @return The corresponding entry for the specified {@code settingValue}.
         */
        private String getEntryForListPreferenceValue(String settingValue, int valuesResourceId, int entriesResourceId) {
            //  TODO : Refactor :  Extract this method to some helper class or something similar.
            Resources resources = getResources();

            String[] values = resources.getStringArray(valuesResourceId);
            int index;
            for (index = 0; index < values.length; index++) {
                String eachKey = values[index];
                if (eachKey.equals(settingValue)) {
                    break;
                }
            }

            return resources.getStringArray(entriesResourceId)[index];
        }

        private void onCreateViewForMyQueriesFragment(final View container) {
            final ListView categories = (ListView) container.findViewById(R.id.listView);

            final CategoryAdapter mAdapter = new CategoryAdapter(container.getContext());
            categories.setAdapter(mAdapter);

            // Prepare the loader.  Either re-connect with an existing one, or start a new one.
            getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<List<Category>>() {
                @Override
                public Loader<List<Category>> onCreateLoader(int id, Bundle args) {
                    return new CategoryListLoader(container.getContext());
                }

                @Override
                public void onLoadFinished(Loader<List<Category>> loader, List<Category> data) {
                    // Set the new data in the adapter.
                    mAdapter.setData(data);
                }

                @Override
                public void onLoaderReset(Loader<List<Category>> loader) {
                    // Clear the data in the adapter.
                    mAdapter.setData(null);
                }
            });
        }
    }

    private void updateViewsVisibility(final int visibility, View[] views) {
        for (View eachView : views) {
            eachView.setVisibility(visibility);
        }
    }

    public void searchAveragePrice(View view) {
        Log.i(TAG, "Searching product: " + query.getText());

        if (query.getText().toString().equals("")) {
            Toast.makeText(this, R.string.average_price_must_enter_query, Toast.LENGTH_SHORT).show();
        } else {
            updateViewsVisibility(View.INVISIBLE, new View[]{averagePrice, minimumPrice, maximumPrice, moneySymbol});

            //  TODO : Extract "MLA" default hard-coding to MainKeys or something similar.
            new CallSuperMLApiAsyncTask().execute(sharedPreferences.getString("country", "MLA"), query.getText().toString());
        }
    }

    /**
     * AsyncTask that calls server to find a specified product's average price. This way of calling a REST API is required in newewst Android
     * versions.
     */
    private class CallSuperMLApiAsyncTask extends AsyncTask<String, Boolean, Product> {

        @Override
        protected Product doInBackground(String... params) {
            return productService.findByQuery(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(Product result) {
            if (result == null) {
                Toast.makeText(averagePrice.getContext(), R.string.error_generic, Toast.LENGTH_SHORT).show();
            } else {
                averagePrice.setText(String.valueOf(result.getAveragePrice()));
                minimumPrice.setText(String.valueOf(result.getMinimumPrice()));
                maximumPrice.setText(String.valueOf(result.getMaximumPrice()));

                updateViewsVisibility(View.VISIBLE, new View[]{averagePrice, minimumPrice, maximumPrice, moneySymbol});
            }
        }
    }

    public static class CategoryAdapter extends ArrayAdapter<Category> {

        private final LayoutInflater layoutInflater;

        public CategoryAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1);
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<Category> data) {
            clear();
            if (data != null) {
                for (Category eachCategory : data) {
                    add(eachCategory);
                }
            }
        }

        /**
         * Populate new items in the list.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.abc_activity_chooser_view_list_item, parent, false);
            } else {
                view = convertView;
            }

            Category category = getItem(position);
            ((TextView) view.findViewById(R.id.title)).setText(category.getName());

            return view;
        }
    }

    /**
     * Helper class to look for interesting changes to the installed apps so that the loader can be updated.
     */
    public static class PackageIntentReceiver extends BroadcastReceiver {

        final CategoryListLoader mLoader;

        public PackageIntentReceiver(CategoryListLoader loader) {
            mLoader = loader;
            IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
            filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
            filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
            filter.addDataScheme("package");
            mLoader.getContext().registerReceiver(this, filter);
            // Register for events related to sdcard installation.
            IntentFilter sdFilter = new IntentFilter();
            sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
            sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
            mLoader.getContext().registerReceiver(this, sdFilter);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // Tell the loader about the change.
            mLoader.onContentChanged();
        }
    }

    /**
     * A custom Loader that loads all of the installed applications.
     */
    public static class CategoryListLoader extends AsyncTaskLoader<List<Category>> {

        private final PackageManager packageManager;

        private List<Category> categories;

        private PackageIntentReceiver packageIntentReceiver;

        public CategoryListLoader(Context context) {
            super(context);

            // Retrieve the package manager for later use; note we don't use 'context' directly but instead the save global application context returned by getContext().
            packageManager = getContext().getPackageManager();
        }

        /**
         * This is where the bulk of our work is done.  This function is called in a background thread and should generate a new set of data to be
         * published by the loader.
         */
        @Override
        public List<Category> loadInBackground() {
            // Retrieve all known applications.
            List<ApplicationInfo> apps = packageManager.getInstalledApplications(
                    PackageManager.GET_UNINSTALLED_PACKAGES |
                    PackageManager.GET_DISABLED_COMPONENTS
            );
            if (apps == null) {
                apps = new ArrayList<ApplicationInfo>();
            }

            // Create corresponding array of categories and load their labels.
            List<Category> categories = new ArrayList<Category>(apps.size());
            for (ApplicationInfo app : apps) {
                if (app.className != null && !app.className.equals("")) {
                    categories.add(new Category(app.className));
                }
            }

            return categories;
        }

        /**
         * Called when there is new data to deliver to the client.  The super class will take care of delivering it; the implementation here just adds
         * a little more logic.
         */
        @Override
        public void deliverResult(List<Category> data) {
            if (isReset()) {
                // An async query came in while the loader is stopped.  We don't need the result.
                if (data != null) {
                    onReleaseResources(data);
                }
            }

            List<Category> oldApps = data;
            categories = data;

            if (isStarted()) {
                // If the Loader is currently started, we can immediately deliver its results.
                super.deliverResult(data);
            }

            // At this point we can release the resources associated with 'oldApps' if needed; now that the new result is delivered we know that it is no longer in use.
            if (oldApps != null) {
                onReleaseResources(oldApps);
            }
        }

        /**
         * Handles a request to start the Loader.
         */
        @Override
        protected void onStartLoading() {
            if (categories != null) {
                // If we currently have a result available, deliver it
                // immediately.
                deliverResult(categories);
            }

            // Start watching for changes in the app data.
            if (packageIntentReceiver == null) {
                packageIntentReceiver = new PackageIntentReceiver(this);
            }

            if (takeContentChanged() || categories == null) {
                // If the data has changed since the last time it was loaded or is not currently available, start a load.
                forceLoad();
            }
        }

        /**
         * Handles a request to stop the Loader.
         */
        @Override
        protected void onStopLoading() {
            // Attempt to cancel the current load task if possible.
            cancelLoad();
        }

        /**
         * Handles a request to cancel a load.
         */
        @Override
        public void onCanceled(List<Category> data) {
            super.onCanceled(data);

            // At this point we can release the resources associated with 'data' if needed.
            onReleaseResources(data);
        }

        /**
         * Handles a request to completely reset the Loader.
         */
        @Override
        protected void onReset() {
            super.onReset();

            // Ensure the loader is stopped
            onStopLoading();

            // At this point we can release the resources associated with 'apps' if needed.
            if (categories != null) {
                onReleaseResources(categories);
                categories = null;
            }

            // Stop monitoring for changes.
            if (packageIntentReceiver != null) {
                getContext().unregisterReceiver(packageIntentReceiver);
                packageIntentReceiver = null;
            }
        }

        /**
         * Helper function to take care of releasing resources associated with an actively loaded data set.
         */
        protected void onReleaseResources(List<Category> apps) {
            // For a simple List<> there is nothing to do.  For something like a Cursor, we would close it here.
        }
    }
}
