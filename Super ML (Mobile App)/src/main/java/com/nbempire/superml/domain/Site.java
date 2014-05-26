package com.nbempire.superml.domain;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 25/05/14, at 19:14.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class Site {

    private String id;

    private String name;

    private List<Currency> currencies;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }
}
