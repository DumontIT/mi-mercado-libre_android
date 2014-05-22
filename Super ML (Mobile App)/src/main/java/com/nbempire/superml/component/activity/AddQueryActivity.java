package com.nbempire.superml.component.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import com.nbempire.superml.R;

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
    public static final String PARAMETER_QUERY = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);

        String query = getIntent().getStringExtra(PARAMETER_QUERY);
        Log.i(TAG, "Creating activity for user query: " + query);

        TextView introductionTextView = (TextView) findViewById(R.id.add_query_introduction_text);
        introductionTextView.setText(String.format("%s %s", introductionTextView.getText(), query));
    }
}
