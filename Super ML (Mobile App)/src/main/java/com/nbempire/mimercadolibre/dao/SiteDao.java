package com.nbempire.mimercadolibre.dao;

import com.nbempire.mimercadolibre.domain.Site;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 25/05/14, at 19:13.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface SiteDao {

    List<Site> findSites();
}
