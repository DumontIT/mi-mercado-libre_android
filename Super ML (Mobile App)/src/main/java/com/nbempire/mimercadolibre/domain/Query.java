package com.nbempire.mimercadolibre.domain;

/**
 * Represents a query made by the user. It has got the information that was shown to the user.
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

    public Query(String text, int averagePrice, int minimumPrice, int maximumPrice) {
        this.text = text;
        this.averagePrice = averagePrice;
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;
    }

    public String getText() {
        return text;
    }
}
