package com.nbempire.superml.service.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.nbempire.superml.MainKeys;
import com.nbempire.superml.dao.SiteDao;
import com.nbempire.superml.dao.impl.SiteDaoImplSpring;
import com.nbempire.superml.domain.Currency;
import com.nbempire.superml.domain.Site;
import com.nbempire.superml.service.SiteService;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 25/05/14, at 19:11.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class SiteServiceImpl implements SiteService {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "SiteServiceImpl";

    private SiteDao siteDao = new SiteDaoImplSpring();

    @Override
    public void loadSitesInformation(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        JSONArray sitesArray = new JSONArray();
        String sitesIdsJson = sharedPreferences.getString(MainKeys.Keys.SITES, null);
        if (sitesIdsJson != null) {
            try {
                sitesArray = new JSONArray(sitesIdsJson);
            } catch (JSONException e) {
                Log.e(TAG, "An error occurred while parsing JSON sites from sharedPreferences: " + e.getMessage());
            }
        }

        if (sitesArray.length() == 0) {
            List<Site> sites = siteDao.findSites();

            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            for (Site eachSite : sites) {

                String key = MainKeys.Keys.SITE_ID_PREFFIX + eachSite.getId();
                if (!sharedPreferences.contains(key)) {
                    sharedPreferencesEditor.putString(key, eachSite.getName());
                }
                sitesArray.put(eachSite.getId());

                for (Currency eachCurrency : eachSite.getCurrencies()) {
                    key = MainKeys.Keys.CURRENCY_ID_PREFFIX + eachCurrency.getId();
                    if (!sharedPreferences.contains(key)) {
                        sharedPreferencesEditor.putString(key, eachCurrency.getSymbol());
                    }
                }
            }

            sharedPreferencesEditor.putString(MainKeys.Keys.SITES, sitesArray.toString());
            sharedPreferencesEditor.commit();
        }
    }
}
