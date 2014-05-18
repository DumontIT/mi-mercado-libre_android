package com.nbempire.pp.component.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.nbempire.pp.R;
import com.nbempire.pp.service.ProductService;
import com.nbempire.pp.service.impl.ProductServiceImpl;

public class HomeActivity extends Activity {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "HomeActivity";

    private ProductService productService = new ProductServiceImpl();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void searchAveragePrice(View view) {
        EditText query = (EditText) findViewById(R.id.homeSearchQuery);
        Log.i(TAG, "Searching average price for: " + query.getText());

        float averagePrice = productService.findAveragePrice(query.getText().toString());

        TextView searchResult = (TextView) findViewById(R.id.homeSearchResult);

        searchResult.setText(String.valueOf(averagePrice));
        searchResult.setVisibility(View.VISIBLE);
    }
}
