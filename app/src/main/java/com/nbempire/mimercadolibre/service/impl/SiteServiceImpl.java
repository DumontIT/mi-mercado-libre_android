package com.nbempire.mimercadolibre.service.impl;

import android.content.SharedPreferences;
import android.util.Log;

import com.nbempire.mimercadolibre.MainKeys;
import com.nbempire.mimercadolibre.dao.SiteDao;
import com.nbempire.mimercadolibre.dao.impl.SiteDaoImplSpring;
import com.nbempire.mimercadolibre.domain.Currency;
import com.nbempire.mimercadolibre.domain.Site;
import com.nbempire.mimercadolibre.service.SiteService;

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
    public void loadSitesInformation(SharedPreferences sharedPreferences) {
        JSONArray sitesArray = new JSONArray();
        String sitesIdsJson = sharedPreferences.getString(MainKeys.Keys.SITES, null);
        if (sitesIdsJson != null) {
            try {
                sitesArray = new JSONArray(sitesIdsJson);
            } catch (JSONException e) {
                Log.e(TAG, "An error occurred while parsing JSON sites from sharedPreferences: " + e.getMessage());
            }
        }

        boolean updateSites = sharedPreferences.getBoolean(MainKeys.Keys.SITES_FLAG, true);
        if (updateSites || sitesArray.length() == 0) {
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
            if (updateSites) {
                sharedPreferencesEditor.putBoolean(MainKeys.Keys.SITES_FLAG, false);
            }
            sharedPreferencesEditor.commit();
        }
    }
}
