package com.nbempire.mimercadolibre.component;

import android.app.Application;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nbempire.mimercadolibre.R;

import java.util.HashMap;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 18/06/14, at 01:15.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class MiMercadoLibreApplication extends Application {

    public enum TrackerName {
        APP_TRACKER
    }

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public MiMercadoLibreApplication() {
        super();
    }

    public synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {
            Tracker tracker = GoogleAnalytics.getInstance(this).newTracker(R.xml.app_tracker);
            mTrackers.put(trackerId, tracker);
        }
        return mTrackers.get(trackerId);
    }
}
