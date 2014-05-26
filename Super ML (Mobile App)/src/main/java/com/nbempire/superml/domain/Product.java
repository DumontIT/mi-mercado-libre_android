package com.nbempire.superml.domain;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 20/05/14, at 02:59.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class Product {

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
}
