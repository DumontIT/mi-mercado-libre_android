package com.nbempire.pp.service.impl;

import com.nbempire.pp.domain.Article;
import com.nbempire.pp.service.ArticleDao;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 18/05/14, at 23:51.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class ArticleDaoImplSpring implements ArticleDao {

    @Override
    public List<Article> findArticlesByQuery(String query) {
        List<Article> articles = new ArrayList<>();

        articles.add(new Article(123));
        articles.add(new Article(43));
        articles.add(new Article(1251));
        articles.add(new Article(5744));

        return articles;
    }
}
