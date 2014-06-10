package com.nbempire.superml.dto;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 06/06/14, at 23:14.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class SubscriptionDto {

    private String query;

    private List<SelectedFiltersDto> selectedFilters;

    public SubscriptionDto(String query) {
        this.query = query;
    }

    public void setSelectedFilters(List<SelectedFiltersDto> selectedFilters) {
        this.selectedFilters = selectedFilters;
    }
}
