package com.nbempire.mimercadolibre.component.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.nbempire.mimercadolibre.MainKeys;
import com.nbempire.mimercadolibre.R;
import com.nbempire.mimercadolibre.domain.Product;
import com.nbempire.mimercadolibre.domain.Site;
import com.nbempire.mimercadolibre.exception.UnfixableException;
import com.nbempire.mimercadolibre.service.ProductService;
import com.nbempire.mimercadolibre.service.SiteService;
import com.nbempire.mimercadolibre.service.impl.ProductServiceImpl;
import com.nbempire.mimercadolibre.service.impl.SiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HomeActivity extends BaseActionBarActivity implements ActionBar.TabListener {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "HomeActivity";

    private static ProductService productService = new ProductServiceImpl();

    private SiteService siteService = new SiteServiceImpl();

    private static SharedPreferences sharedPreferences;

    private Product product;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the sections. We use a {@link FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory intensive, it may be best to switch to a {@link
     * android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter sectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;

    private static EditText query;

    private static TextView categoryLabel;

    private static TextView averagePrice;

    private static TextView minimumPrice;

    private static TextView maximumPrice;

    private static TextView moneySymbol;

    private static TextView countryLabel;

    private static Button saveQueryButton;

    SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(MainKeys.Keys.CURRENT_COUNTRY)) {
                countryLabel.setText(generateCurrentCountryLabel(getBaseContext()));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int playServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (playServicesAvailable != ConnectionResult.SUCCESS) {
            GooglePlayServicesUtil.getErrorDialog(playServicesAvailable, this, 0);
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        loadGeneralDataFromServer();

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three primary sections of the activity.
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        // When swiping between different sections, select the corresponding tab. We can also use ActionBar.Tab#select() to do this if we have a reference to the Tab.
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter. Also specify this Activity object, which implements the
            // TabListener interface, as the callback (listener) for when this tab is selected.
            actionBar.addTab(actionBar.newTab().setText(sectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
        }
    }

    private static String generateCurrentCountryLabel(Context context) {
        String choosenCountry =
                getEntryForListPreferenceValue(context, sharedPreferences.getString(MainKeys.Keys.CURRENT_COUNTRY, MainKeys.DEFAULT_COUNTRY_ID),
                                               R.array.pref_countries_values, R.array.pref_countries_entries);
        return String.format("%s %s", context.getText(R.string.home_checking_ml_site), choosenCountry);
    }

    /**
     * Get selected setting entry (the label) for a specified ListPreference entries-values pair.
     *
     * @param settingValue
     *         The setting stored value. You can get that value by doing sharedPreferences.getString({keyName}, "defaultValue").
     * @param valuesResourceId
     *         Resource ID for the string array resource where to look for the specified {@code settingValue}. This resource is set in ListPreference
     *         definition.
     * @param entriesResourceId
     *         Resource ID that contains entries for the specified {@code valuesResourceId}.
     *
     * @return The corresponding entry for the specified {@code settingValue}.
     */
    private static String getEntryForListPreferenceValue(Context context, String settingValue, int valuesResourceId, int entriesResourceId) {
        //  TODO : Refactor :  Extract this method to some helper class or something similar.
        Resources resources = context.getResources();

        String[] values = resources.getStringArray(valuesResourceId);
        int index;
        for (index = 0; index < values.length; index++) {
            String eachKey = values[index];
            if (eachKey.equals(settingValue)) {
                break;
            }
        }

        return resources.getStringArray(entriesResourceId)[index == values.length ? index - 1 : index];
    }

    private void loadGeneralDataFromServer() {
        //  TODO : We should NOT run this on every app start.
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            new LoadSitesInfoAsyncTask().execute(sharedPreferences);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        viewPager.setCurrentItem(tab.getPosition());
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

            Locale locale = Locale.getDefault();
            switch (position) {
                case 0:
                    result = getString(R.string.title_section_average_price).toUpperCase(locale);
                    break;
                case 1:
                    result = getString(R.string.title_section_my_queries).toUpperCase(locale);
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
            categoryLabel = (TextView) container.findViewById(R.id.homeCategoryLabel);
            averagePrice = (TextView) container.findViewById(R.id.homeAveragePrice);
            minimumPrice = (TextView) container.findViewById(R.id.homeMinimumPrice);
            maximumPrice = (TextView) container.findViewById(R.id.homeMaximumPrice);
            moneySymbol = (TextView) container.findViewById(R.id.homeMoneySymbol);
            countryLabel = (TextView) container.findViewById(R.id.homeCountryLabel);
            saveQueryButton = (Button) container.findViewById(R.id.saveQueryButton);

            countryLabel.setText(generateCurrentCountryLabel(container.getContext()));
        }

        private void onCreateViewForMyQueriesFragment(final View container) {
            final ListView myQueries = (ListView) container.findViewById(R.id.listView);

            final MyQueriesAdapter myQueriesAdapter = new MyQueriesAdapter(container.getContext());
            myQueries.setAdapter(myQueriesAdapter);

            // Prepare the loader.  Either re-connect with an existing one, or start a new one.
            getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<List<Product>>() {
                @Override
                public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
                    return new MyQueriesListLoader(container.getContext());
                }

                @Override
                public void onLoadFinished(Loader<List<Product>> loader, List<Product> data) {
                    // Set the new data in the adapter.
                    myQueriesAdapter.setData(data);
                }

                @Override
                public void onLoaderReset(Loader<List<Product>> loader) {
                    // Clear the data in the adapter.
                    myQueriesAdapter.setData(null);
                }
            });
        }
    }

    private static void updateViewsVisibility(final int visibility, View[] views) {
        for (View eachView : views) {
            eachView.setVisibility(visibility);
        }
    }

    public void searchAveragePrice(View view) {
        Log.d(TAG, "View average price button pressed");
        Log.i(TAG, "Searching product: " + query.getText());

        tracker.send(new HitBuilders.EventBuilder()
                             .setCategory("search")
                             .setAction("averagePrice")
                             .setLabel(query.getText().toString())
                             .build());

        if (query.getText().toString().equals("")) {
            Toast.makeText(this, R.string.average_price_must_enter_query, Toast.LENGTH_SHORT).show();
        } else {
            updateViewsVisibility(View.INVISIBLE, new View[]{averagePrice, minimumPrice, maximumPrice, moneySymbol, saveQueryButton, categoryLabel});

            String currentSite = sharedPreferences.getString(MainKeys.Keys.CURRENT_COUNTRY, MainKeys.DEFAULT_COUNTRY_ID);

            //  TODO : Functionality : Check if this product already exists in stored ones and search again only if it has been a long time since the last query
            new FindProductAsyncTask().execute(currentSite, query.getText().toString());
        }
    }

    public void saveQuery(View view) {
        Log.d(TAG, "Save query button pressed.");

        if (query.getText().toString().equals("")) {
            Toast.makeText(this, R.string.average_price_must_enter_query, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, AddQueryActivity.class).putExtra(MainKeys.Keys.PRODUCT, product));
        }
    }

    /**
     * AsyncTask that calls server to find a specified product's average price. This way of calling a REST API is required in newewst Android
     * versions.
     */
    private class FindProductAsyncTask extends AsyncTask<String, Boolean, Product> {

        @Override
        protected Product doInBackground(String... params) {
            Product result = null;

            try {
                result = productService.findByQuery(params[0], params[1]);
            } catch (UnfixableException unfixableException) {
                Log.e(TAG, "An error occurred while finding a product and the search could not be finished: " + unfixableException.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(Product result) {
            if (result == null) {
                Toast.makeText(averagePrice.getContext(), R.string.error_generic, Toast.LENGTH_SHORT).show();
            } else {
                product = result;

                updateAveragePriceFragment(averagePrice.getContext(), product, false);

                productService.add(sharedPreferences, product);
            }
        }
    }

    private static void updateAveragePriceFragment(Context context, Product product, boolean isStored) {
        moneySymbol.setText(
                sharedPreferences.getString(MainKeys.Keys.CURRENCY_ID_PREFFIX + product.getCurrencyId(), MainKeys.DEFAULT_CURRENCY_SYMBOL));

        if (product.getCategory() != null) {
            categoryLabel.setText(String.format("%s %s", context.getText(R.string.category), product.getCategory().getName()));
        }
        averagePrice.setText(String.valueOf(product.getAveragePrice()));
        minimumPrice.setText(String.valueOf(product.getMinimumPrice()));
        maximumPrice.setText(String.valueOf(product.getMaximumPrice()));

        List<View> viewsToShow = new ArrayList<View>();
        viewsToShow.add(averagePrice);
        viewsToShow.add(minimumPrice);
        viewsToShow.add(maximumPrice);
        viewsToShow.add(moneySymbol);
        viewsToShow.add(categoryLabel);

        List<View> viewsToHide = new ArrayList<View>();
        if (isStored) {
            query.setText(product.getQuery());
            viewsToHide.add(saveQueryButton);
        } else {
            viewsToShow.add(saveQueryButton);
        }

        updateViewsVisibility(View.VISIBLE, viewsToShow.toArray(new View[viewsToShow.size()]));

        if (!viewsToHide.isEmpty()) {
            updateViewsVisibility(View.INVISIBLE, viewsToHide.toArray(new View[viewsToHide.size()]));
        }
    }

    private class LoadSitesInfoAsyncTask extends AsyncTask<SharedPreferences, Boolean, List<Site>> {

        @Override
        protected List<Site> doInBackground(SharedPreferences... params) {
            siteService.loadSitesInformation(params[0]);
            return null;
        }
    }

    public static class MyQueriesAdapter extends ArrayAdapter<Product> {

        //  Change this to a list of my queries (the ones that I'm subscribed).
        private final LayoutInflater layoutInflater;

        public MyQueriesAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1);
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<Product> data) {
            clear();
            if (data != null) {
                for (Product eachProduct : data) {
                    add(eachProduct);
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
                view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            final Product product = getItem(position);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(product.getQuery());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Selected saved product: " + product.getQuery());
                    updateAveragePriceFragment(v.getContext(), product, true);
                }
            });

            return view;
        }
    }

    /**
     * Helper class to look for interesting changes to the installed apps so that the loader can be updated.
     */
    public static class PackageIntentReceiver extends BroadcastReceiver {

        final MyQueriesListLoader mLoader;

        public PackageIntentReceiver(MyQueriesListLoader loader) {
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
     * A custom Loader that loads all stored products.
     */
    public static class MyQueriesListLoader extends AsyncTaskLoader<List<Product>> {

        private List<Product> userQueries;

        private PackageIntentReceiver packageIntentReceiver;

        public MyQueriesListLoader(Context context) {
            super(context);
        }

        /**
         * This is where the bulk of our work is done.  This function is called in a background thread and should generate a new set of data to be
         * published by the loader.
         */
        @Override
        public List<Product> loadInBackground() {
            return productService.findAll(sharedPreferences);
        }

        /**
         * Called when there is new data to deliver to the client.  The super class will take care of delivering it; the implementation here just adds
         * a little more logic.
         */
        @Override
        public void deliverResult(List<Product> data) {
            if (isReset()) {
                // An async query came in while the loader is stopped.  We don't need the result.
                if (data != null) {
                    onReleaseResources(data);
                }
            }

            List<Product> oldApps = data;
            userQueries = data;

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
            if (userQueries != null) {
                // If we currently have a result available, deliver it immediately.
                deliverResult(userQueries);
            }

            // Start watching for changes in the app data.
            if (packageIntentReceiver == null) {
                packageIntentReceiver = new PackageIntentReceiver(this);
            }

            if (takeContentChanged() || userQueries == null) {
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
        public void onCanceled(List<Product> data) {
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
            if (userQueries != null) {
                onReleaseResources(userQueries);
                userQueries = null;
            }
        }

        /**
         * Helper function to take care of releasing resources associated with an actively loaded data set.
         */
        protected void onReleaseResources(List<Product> products) {
            // For a simple List<> there is nothing to do.  For something like a Cursor, we would close it here.
        }
    }
}
