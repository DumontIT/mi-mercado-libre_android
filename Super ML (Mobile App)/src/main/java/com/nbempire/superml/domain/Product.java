package com.nbempire.superml.domain;

import java.io.Serializable;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 20/05/14, at 02:59.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class Product implements Serializable {

    /**
     * The serialVersionUID of this class.
     */
    private static final long serialVersionUID = 8535478854394125896L;

    private String query;

    private int averagePrice;

    private int minimumPrice;

    private int maximumPrice;

    private String currencyId;

    private Filter[] filters;

    private Filter[] availableFilters;

    public int getAveragePrice() {
        return averagePrice;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public int getMaximumPrice() {
        return maximumPrice;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public Filter[] getFilters() {
        return filters;
    }

    public Filter[] getAvailableFilters() {
        return availableFilters;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
