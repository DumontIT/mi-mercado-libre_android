package com.nbempire.superml.dao.impl;

import android.util.Log;
import com.nbempire.superml.MainKeys;
import com.nbempire.superml.dao.SiteDao;
import com.nbempire.superml.domain.Site;
import com.nbempire.superml.dto.SitesDto;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 25/05/14, at 19:13.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class SiteDaoImplSpring implements SiteDao {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "SiteDaoImplSpring";

    @Override
    public List<Site> findSites() {

        String urlString = MainKeys.API_HOST + "/sites";

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        SitesDto result = null;
        try {
            Log.i(TAG, "GET call against: " + urlString);

            result = restTemplate.getForObject(urlString, SitesDto.class);

            Log.i(TAG, "Obtained sites: " + result.getSites().size());
        } catch (Exception restClientException) {
            Log.e(TAG, "An error occurred while searching sites" + restClientException.getMessage());
        }

        return result != null ? result.getSites() : new ArrayList<Site>();
    }
}
