package com.nbempire.superml;

/**
 * Contains some application properties.
 * <p/>
 * Created on 25/05/14, at 19:28.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class MainKeys {

    public static final String API_HOST = "http://super-ml.herokuapp.com";

    public static final String DEFAULT_COUNTRY_ID = "MLA";

    public static final String DEFAULT_CURRENCY_SYMBOL = "$";

    /**
     * Keys used in local storage and/or intent's extra parameters.
     */
    public class Keys {

        public static final String SITES = "sites";

        public static final String CURRENT_COUNTRY = "country";

        public static final String CURRENCY_ID_PREFFIX = "currencyId_";

        public static final String SITE_ID_PREFFIX = "siteId_";

        public static final String PRODUCT = "product";
    }
}
