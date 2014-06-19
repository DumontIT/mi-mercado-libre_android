package com.nbempire.mimercadolibre.dao.impl;

import android.util.Log;
import com.nbempire.mimercadolibre.MainKeys;
import com.nbempire.mimercadolibre.dao.ProductDao;
import com.nbempire.mimercadolibre.domain.AppliedFilter;
import com.nbempire.mimercadolibre.domain.AvailableFilter;
import com.nbempire.mimercadolibre.domain.Category;
import com.nbempire.mimercadolibre.domain.Product;
import com.nbempire.mimercadolibre.dto.FilterDto;
import com.nbempire.mimercadolibre.dto.ProductDto;
import com.nbempire.mimercadolibre.exception.UnfixableException;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 20/05/14, at 02:58.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class ProductDaoImplSpring implements ProductDao {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "ProductDaoImplSpring";

    @Override
    public Product findByQuery(String siteId, String query) throws UnfixableException {
        String urlString = String.format(MainKeys.API_HOST + "/%1$s/averagePrice/%2$s", siteId, query);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        ProductDto dto = null;
        try {
            Log.i(TAG, "GET call against: " + urlString);

            dto = restTemplate.getForObject(urlString, ProductDto.class);

            Log.i(TAG, "Obtained product: " + dto.getAveragePrice());
        } catch (RestClientException restClientException) {
            Log.e(TAG, "An error occurred while searching for: \"" + query + "\", " + restClientException.getMessage());
            throw new UnfixableException(restClientException);
        }

        return parseProduct(dto);
    }

    /**
     * Parse the ProductDto and generates the Product domain object.
     *
     * @param dto
     *
     * @return
     */
    private Product parseProduct(ProductDto dto) {
        Product result = new Product();

        result.setAveragePrice(dto.getAveragePrice());
        result.setMinimumPrice(dto.getMinimumPrice());
        result.setMaximumPrice(dto.getMaximumPrice());
        result.setCurrencyId(dto.getCurrencyId());

        if (dto.getFilters().length > 0) {
            result.setCategory(parseCategory(dto.getFilters()[0]));

            if (dto.getFilters().length > 1) {
                result.setAppliedFilters(parseAppliedFilters(dto.getFilters()));
            }
        }

        result.setAvailableFilters(parseAvailableFilters(dto.getAvailableFilters()));

        return result;
    }

    /**
     * Generates the Category domain object based on a retrieved filter.
     *
     * @param filterDto
     *
     * @return
     */
    private Category parseCategory(FilterDto filterDto) {
        FilterDto categoryDto = filterDto.getValues()[0];
        return new Category(categoryDto.getId(), categoryDto.getName(), parsePathFromRootFilters(categoryDto.getPath_from_root()));
    }

    /**
     * Parse filters and generates the category's path from root.
     *
     * @param pathFromRoot
     *
     * @return
     */
    private AppliedFilter[] parsePathFromRootFilters(FilterDto[] pathFromRoot) {
        AppliedFilter[] categoriesPath = new AppliedFilter[pathFromRoot.length];
        for (int i = 0; i < pathFromRoot.length; i++) {
            categoriesPath[i] = new AppliedFilter(pathFromRoot[i].getId(), pathFromRoot[i].getName());
        }

        return categoriesPath;
    }

    /**
     * @param availableFiltersDto
     *
     * @return
     */
    private AvailableFilter[] parseAvailableFilters(FilterDto[] availableFiltersDto) {

        AvailableFilter[] availableFilters = new AvailableFilter[availableFiltersDto.length];
        for (int i = 0; i < availableFiltersDto.length; i++) {
            FilterDto eachFilterDto = availableFiltersDto[i];

            AvailableFilter[] possibleValues = new AvailableFilter[eachFilterDto.getValues().length];
            for (int j = 0; j < eachFilterDto.getValues().length; j++) {
                FilterDto eachPossibleValue = eachFilterDto.getValues()[j];
                possibleValues[j] = new AvailableFilter(eachPossibleValue.getId(), eachPossibleValue.getName(), eachPossibleValue.getResults());
            }

            availableFilters[i] = new AvailableFilter(eachFilterDto.getId(), eachFilterDto.getName(), possibleValues);
        }

        return availableFilters;
    }

    /**
     * Parse the applied filters and generates our domain objects.
     *
     * @param filters
     *
     * @return
     */
    private AppliedFilter[] parseAppliedFilters(FilterDto[] filters) {
        AppliedFilter[] appliedFilters = new AppliedFilter[filters.length];
        for (int i = 1; i < filters.length; i++) {
            String filterId = filters[i].getId();
            String filterName = filters[i].getName();

            FilterDto filterDtoValue = filters[i].getValues()[0];
            appliedFilters[i] = new AppliedFilter(filterId, filterName, new AppliedFilter(filterDtoValue.getId(), filterDtoValue.getName()));
        }

        return appliedFilters;
    }
}
