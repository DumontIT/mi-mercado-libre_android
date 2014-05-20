package com.nbempire.superml.dao;

import com.nbempire.superml.domain.Article;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 18/05/14, at 23:51.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface ArticleDao {

    /**
     * Find articles in Mercado Libre by specifying a search query.
     *
     * @param query
     *         The query to look for.
     *
     * @return A List of articles in ML.
     */
    List<Article> findArticlesByQuery(String query);
}
