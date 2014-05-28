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

    private Category category;

    private AppliedFilter[] appliedFilters;

    private AvailableFilter[] availableFilters;

    public Product() {
        this.appliedFilters = new AppliedFilter[]{};
        this.availableFilters = new AvailableFilter[]{};
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(int averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public int getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(int maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AppliedFilter[] getAppliedFilters() {
        return appliedFilters;
    }

    public void setAppliedFilters(AppliedFilter[] appliedFilters) {
        this.appliedFilters = appliedFilters;
    }

    public void setAvailableFilters(AvailableFilter[] availableFilters) {
        this.availableFilters = availableFilters;
    }

    public AvailableFilter[] getAvailableFilters() {
        return availableFilters;
    }
}
