package com.nbempire.superml.service.impl;

import com.nbempire.superml.dao.impl.ArticleDaoImplSpring;
import com.nbempire.superml.domain.Article;
import com.nbempire.superml.service.impl.ArticleService;
import com.nbempire.superml.service.impl.ArticleServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ArticleServiceTest {

    private ArticleService articleService = new ArticleServiceImpl(new ArticleDaoImplSpring());

    /**
     * Test method for findArticlesMatching().
     */
    @Test
    public void testfindArticlesMatching() throws Exception {
        List<Article> articles = articleService.findArticlesMatching("ipod");

        Assert.assertNotNull("Articles list must not be null", articles);
        Assert.assertFalse("Articles list must not be empty", articles.isEmpty());

        for (Article eachArticle : articles) {
            Assert.assertNotNull("Article's price must not be null", eachArticle.getPrice());
        }
    }
}