package com.nbempire.superml.component.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.nbempire.superml.MainKeys;
import com.nbempire.superml.R;
import com.nbempire.superml.domain.Product;

/**
 * Created on 03/06/14, at 21:53.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class ChooseSubscriptionActivity extends BaseActionBarActivity {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "ChooseSubscriptionActivity";

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subscription);

        product = (Product) getIntent().getSerializableExtra(MainKeys.Keys.PRODUCT);
    }

    public void saveSubscriptions(View view) {
        Log.i(TAG, "Saving subscriptions for product: " + product.getQuery());
        Toast.makeText(this, R.string.subscribing, Toast.LENGTH_SHORT).show();
    }
}
