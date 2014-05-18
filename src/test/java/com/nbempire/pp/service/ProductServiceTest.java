package com.nbempire.pp.service;

import com.nbempire.pp.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class ProductServiceTest {

    private ProductService productService = new ProductServiceImpl();

    /**
     * Test method for findAveragePrice().
     */
    @Test
    public void testfindAveragePrice() throws Exception {
        float averagePrice = productService.findAveragePrice("gol trend pack iii");

        Assert.assertNotEquals((float)0, averagePrice);
        Assert.assertTrue("Average price should be between 95.000 and 110.000, and it is: "+averagePrice, averagePrice >= 95000 && averagePrice < 110000);
    }
}