package com.nbempire.superml.service;

import com.nbempire.superml.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class ProductServiceTest {

    private ProductService productService = new ProductServiceImpl();

    /**
     * Test method for findAveragePrice().
     */
    @Test
    public void testfindAveragePrice() throws Exception {
        float averagePrice = productService.findByQuery("MLA", "gol trend pack iii");

        Assert.assertNotEquals((float) 0, averagePrice);
        Assert.assertEquals("Average price is not OK", 1790.25, averagePrice, 0);
    }
}