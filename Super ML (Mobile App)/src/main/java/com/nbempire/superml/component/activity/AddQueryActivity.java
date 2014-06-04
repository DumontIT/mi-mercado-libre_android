package com.nbempire.superml.component.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.nbempire.superml.MainKeys;
import com.nbempire.superml.R;
import com.nbempire.superml.adapter.FilterExpandableListAdapter;
import com.nbempire.superml.domain.AvailableFilter;
import com.nbempire.superml.domain.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Here the user will subscribe itself to a custom query to receive notifications about new articles.
 * <p/>
 * Created on 22/05/14, at 00:01.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class AddQueryActivity extends BaseActionBarActivity {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "AddQueryActivity";

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);

        product = (Product) getIntent().getSerializableExtra(MainKeys.Keys.PRODUCT);
        if (product == null) {
            Log.e(TAG, "An error ocurred while parsing the product. Can't load activity!!");
            Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG, "Creating activity for user query: " + product.getQuery());

            TextView categoryLabel = (TextView) findViewById(R.id.addQuery_categoryLabel);
            categoryLabel.setText(String.format("%1s %2s", categoryLabel.getText().toString(), product.getCategory().getName()));

            TextView introductionTextView = (TextView) findViewById(R.id.add_query_introduction_text);
            introductionTextView.setText(String.format("%s %s", introductionTextView.getText(), product.getQuery()));

            List<String> filterNames = new ArrayList<String>();
            List<List<AvailableFilter>> filterValues = new ArrayList<List<AvailableFilter>>();
            for (AvailableFilter eachAvailableFilter : product.getAvailableFilters()) {
                filterNames.add(eachAvailableFilter.getName());

                List<AvailableFilter> valuesForEachFilter = new ArrayList<AvailableFilter>();
                Collections.addAll(valuesForEachFilter, eachAvailableFilter.getPossibleValues());
                filterValues.add(valuesForEachFilter);
            }

            ExpandableListView filtersExpandableListView = (ExpandableListView) findViewById(R.id.listView);
            FilterExpandableListAdapter adapter = new FilterExpandableListAdapter(this, filterNames, filterValues);
            filtersExpandableListView.setAdapter(adapter);
        }
    }

    public void saveProduct(View view) {
        Log.i(TAG, "Preparing product for subscription...");

        startActivity(new Intent(this, ChooseSubscriptionActivity.class).putExtra(MainKeys.Keys.PRODUCT, product));
    }
}
