package com.nbempire.mimercadolibre.dto;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 09/06/14, at 23:11.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class SelectedValuesDto {

    private final String name;

    private String id;

    public SelectedValuesDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
