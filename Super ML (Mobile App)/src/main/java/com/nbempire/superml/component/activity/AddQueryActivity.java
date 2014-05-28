package com.nbempire.superml.component.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.nbempire.superml.R;
import com.nbempire.superml.adapter.FilterAdapter;
import com.nbempire.superml.domain.AvailableFilter;
import com.nbempire.superml.domain.Product;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);

        Product product = (Product) getIntent().getSerializableExtra(PARAMETER_PRODUCT);
        if (product == null) {
            Log.e(TAG, "An error ocurred while parsing the product. Can't load activity!!");
            Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG, "Creating activity for user query: " + product.getQuery());

            TextView introductionTextView = (TextView) findViewById(R.id.add_query_introduction_text);
            introductionTextView.setText(String.format("%s %s", introductionTextView.getText(), product.getQuery()));

            ListView categories = (ListView) findViewById(R.id.listView);

            FilterAdapter filterAdapter = new FilterAdapter(this);
            categories.setAdapter(filterAdapter);

            for (AvailableFilter eachFilter : product.getAvailableFilters()) {
                filterAdapter.add(eachFilter);
            }
        }
    }
}
