package com.nbempire.mimercadolibre.component.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.nbempire.mimercadolibre.MainKeys;
import com.nbempire.mimercadolibre.R;
import com.nbempire.mimercadolibre.domain.Product;
import com.nbempire.mimercadolibre.domain.Subscriptions;
import com.nbempire.mimercadolibre.domain.User;
import com.nbempire.mimercadolibre.exception.UnfixableException;
import com.nbempire.mimercadolibre.service.UserService;
import com.nbempire.mimercadolibre.service.impl.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

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

    private UserService userService;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subscription);

        product = (Product) getIntent().getSerializableExtra(MainKeys.Keys.PRODUCT);
        userService = new UserServiceImpl();
    }

    public void saveSubscriptions(View view) {
        Log.i(TAG, "Saving subscriptions for product: " + product.getQuery());

        Set<Subscriptions> subscriptions = getSubscriptions(R.id.lowestPrice, R.id.priceHigherThanAverage, R.id.priceLesserThanAverage, R.id.allNew);
        Log.d(TAG, "Selected subscriptions: " + subscriptions.size());

        Toast.makeText(this, R.string.subscribing, Toast.LENGTH_SHORT).show();
        subscribe(product, subscriptions);
    }

    private void subscribe(Product product, Set<Subscriptions> subscriptions) {
        product.setSubscriptions(subscriptions);

        //  TODO : Functionality : Save user somewhere???
        User anUser = userService.create(this);
        anUser.setProduct(product);
        anUser.setSubscriptions(subscriptions);

        new UpdateSubscriptionsAsyncTask().execute(anUser);
    }

    private Set<Subscriptions> getSubscriptions(int lowestPrice, int higherThanAverage, int lesserThanAverage, int allNew) {
        Set<Subscriptions> subscriptions = new HashSet<Subscriptions>();

        addWhenNecessary(subscriptions, lowestPrice, Subscriptions.LOWEST);
        addWhenNecessary(subscriptions, higherThanAverage, Subscriptions.HIGHER_THAN_AVERAGE);
        addWhenNecessary(subscriptions, lesserThanAverage, Subscriptions.LESSER_THAN_AVERAGE);
        addWhenNecessary(subscriptions, allNew, Subscriptions.ALL);

        return subscriptions;
    }

    private void addWhenNecessary(Set<Subscriptions> subscriptions, int view, Subscriptions subscription) {
        CheckBox lowest = (CheckBox) findViewById(view);
        if (lowest.isChecked()) {
            subscriptions.add(subscription);
        }
    }

    private class UpdateSubscriptionsAsyncTask extends AsyncTask<User, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(User... params) {
            boolean result = false;
            try {
                result = userService.updateSubscriptions(params[0]);
            } catch (UnfixableException unfixableException) {
                String message = "An error occurred while trying to subscribe notifications for a product: " + unfixableException.getMessage();
                Log.e(TAG, message);
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory("exception")
                        .setAction("updateSubscriptions")
                        .setLabel(message)
                        .build());
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            int resultMessage = R.string.cant_subscribe;
            if (result != null && result) {
                resultMessage = R.string.subscribed;
            }
            Toast.makeText(context, resultMessage, Toast.LENGTH_SHORT).show();
        }
    }
}

