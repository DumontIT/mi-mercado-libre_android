package com.nbempire.superml.dto;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 27/05/14, at 21:07.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class ProductDto {

    private int averagePrice;

    private int minimumPrice;

    private int maximumPrice;

    private String currencyId;

    private FilterDto[] filters;

    private FilterDto[] availableFilters;

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

    public FilterDto[] getFilters() {
        return filters;
    }

    public FilterDto[] getAvailableFilters() {
        return availableFilters;
    }
}
