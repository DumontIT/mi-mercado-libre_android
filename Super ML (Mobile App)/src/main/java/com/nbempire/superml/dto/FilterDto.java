package com.nbempire.superml.dto;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 27/05/14, at 21:07.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class FilterDto {

    private String id;

    private String name;

    private String type;

    private FilterDto[] values;

    private FilterDto[] path_from_root;

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

    public FilterDto[] getValues() {
        return values;
    }

    public FilterDto[] getPath_from_root() {
        return path_from_root;
    }

    public Integer getResults() {
        return results;
    }
}
