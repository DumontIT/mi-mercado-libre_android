package com.nbempire.superml.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 05/06/14, at 23:24.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class User {

    private final String id;

    private List<Product> products;

    /**
     * Entity's constructor.
     *
     * @param id
     *         Should be a unique identifier for a user. Right now it is the {@link android.provider.Settings.Secure#ANDROID_ID}.
     */
    public User(String id) {
        this.id = id;
        this.products = new ArrayList<Product>();
    }

    public List<Product> getProducts() {
        return products;
    }
}
