package com.nbempire.pp.component.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.nbempire.pp.R;

public class HomeActivity extends Activity {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "HomeActivity";

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
    }
}
