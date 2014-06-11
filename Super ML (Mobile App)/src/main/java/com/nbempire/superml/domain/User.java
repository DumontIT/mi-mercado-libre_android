package com.nbempire.superml.domain;

import java.util.Set;

/**
 * Created on 05/06/14, at 23:24.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class User {

    private final String id;

    private Product product;

    private Set<Subscriptions> subscriptions;

    /**
     * Entity's constructor.
     *
     * @param id
     *         Should be a unique identifier for a user. Right now it is the {@link android.provider.Settings.Secure#ANDROID_ID}.
     */
    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSubscriptions(Set<Subscriptions> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<Subscriptions> getSubscriptions() {
        return subscriptions;
    }
}
