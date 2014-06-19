package com.nbempire.mimercadolibre.domain;

import java.util.Date;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 26/05/14, at 19:44.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class Query {

    private String text;

    private int averagePrice;

    private int minimumPrice;

    private int maximumPrice;

    private Date lastSearch;

    public Query(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
