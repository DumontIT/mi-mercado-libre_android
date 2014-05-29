package com.nbempire.superml.dao;

import com.nbempire.superml.domain.Site;

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
