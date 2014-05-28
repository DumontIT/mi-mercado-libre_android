package com.nbempire.superml.domain;

import java.io.Serializable;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 26/05/14, at 00:25.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class Filter implements Serializable {

    /**
     * The serialVersionUID of this class.
     */
    private static final long serialVersionUID = 2080570034799102049L;

    protected String id;

    protected String name;

    public Filter(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Filter(String id, String name, Filter appliedFilter) {
        this(id, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
