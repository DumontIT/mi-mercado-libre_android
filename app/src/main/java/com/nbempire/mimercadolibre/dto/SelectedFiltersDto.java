package com.nbempire.mimercadolibre.dto;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 10/06/14, at 01:53.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class SelectedFiltersDto {

    private String id;

    private List<String> values;

    public SelectedFiltersDto(String id, List<String> values) {
        this.id = id;
        this.values = values;
    }
}
