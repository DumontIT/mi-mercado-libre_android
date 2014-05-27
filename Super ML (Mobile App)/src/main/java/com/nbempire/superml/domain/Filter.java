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

    private String id;

    private String name;

    private String type;

    private Filter[] values;

    private Filter[] path_from_root;

    private Integer results;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Filter[] getValues() {
        return values;
    }

    public Filter[] getPath_from_root() {
        return path_from_root;
    }

    public Integer getResults() {
        return results;
    }
}
