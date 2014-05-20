package com.nbempire.superml.service.impl;

import com.nbempire.superml.dao.ArticleDao;
import com.nbempire.superml.domain.Article;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 18/05/14, at 20:27.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public List<Article> findArticlesMatching(String query) {
        return articleDao.findArticlesByQuery(query);
    }
}