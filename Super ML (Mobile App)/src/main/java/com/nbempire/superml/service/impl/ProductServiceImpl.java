package com.nbempire.superml.service.impl;

import com.nbempire.superml.dao.impl.ArticleDaoImplSpring;
import com.nbempire.superml.domain.Article;
import com.nbempire.superml.service.ProductService;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 18/05/14, at 17:59.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class ProductServiceImpl implements ProductService {

    private ArticleService articleService = new ArticleServiceImpl(new ArticleDaoImplSpring());

    @Override
    public float findAveragePrice(String query) {
        List<Article> articles = articleService.findArticlesMatching(query);

        float total = 0;
        for (Article eachArticle : articles) {
            total += eachArticle.getPrice();
        }

        return total / articles.size();
    }
}
