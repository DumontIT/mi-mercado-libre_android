package com.nbempire.superml.component.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.nbempire.superml.R;
import com.nbempire.superml.domain.AvailableFilter;
import com.nbempire.superml.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Here the user will subscribe itself to a custom query to receive notifications about new articles.
 * <p/>
 * Created on 22/05/14, at 00:01.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class AddQueryActivity extends ActionBarActivity {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "AddQueryActivity";

    /**
     * Intent parameter.
     */
    public static final String PARAMETER_PRODUCT = "product";

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);

        product = (Product) getIntent().getSerializableExtra(PARAMETER_PRODUCT);
        if (product == null) {
            Log.e(TAG, "An error ocurred while parsing the product. Can't load activity!!");
            Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG, "Creating activity for user query: " + product.getQuery());

            TextView categoryLabel = (TextView) findViewById(R.id.addQuery_categoryLabel);
            categoryLabel.setText(String.format("%1s %2s", categoryLabel.getText().toString(), product.getCategory().getName()));

            TextView introductionTextView = (TextView) findViewById(R.id.add_query_introduction_text);
            introductionTextView.setText(String.format("%s %s", introductionTextView.getText(), product.getQuery()));

            ExpandableListView categories = (ExpandableListView) findViewById(R.id.listView);

            List<Map<String, String>> groupData = generateExpandableListViewGroupsData(product.getAvailableFilters());
            List<List<Map<String, String>>> listOfChildGroups = generateExpandableListViewChildrenData(product.getAvailableFilters());

            SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                    this,
                    groupData,
                    android.R.layout.simple_expandable_list_item_1,
                    new String[]{"ROOT_NAME"},
                    new int[]{android.R.id.text1},
                    listOfChildGroups,
                    R.layout.list_item_checkbox,
                    new String[]{"CHILD_NAME"},
                    new int[]{R.id.list_item_checkbox}
            );

            categories.setAdapter(adapter);
        }
    }

    private List<Map<String, String>> generateExpandableListViewGroupsData(AvailableFilter[] availableFilters) {
        List<Map<String, String>> groups = new ArrayList<Map<String, String>>();

        for (AvailableFilter eachAvailableFilter : availableFilters) {
            Map<String, String> groupFields = new HashMap<String, String>();
            groupFields.put("ROOT_NAME", eachAvailableFilter.getName());
            groups.add(groupFields);
        }

        return groups;
    }

    private List<List<Map<String, String>>> generateExpandableListViewChildrenData(AvailableFilter[] availableFilters) {
        List<List<Map<String, String>>> availableFiltersValues = new ArrayList<List<Map<String, String>>>();

        for (AvailableFilter eachAvailableFilter : availableFilters) {
            List<Map<String, String>> possibleValues = new ArrayList<Map<String, String>>();

            for (AvailableFilter eachPossibleValue : eachAvailableFilter.getPossibleValues()) {
                Map<String, String> eachChildFields = new HashMap<String, String>();
                eachChildFields.put("CHILD_NAME", eachPossibleValue.getName());
                possibleValues.add(eachChildFields);
            }

            availableFiltersValues.add(possibleValues);
        }

        return availableFiltersValues;
    }
}
