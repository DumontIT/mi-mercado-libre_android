package com.nbempire.superml.service.impl;

import com.nbempire.superml.domain.Article;

import java.util.List;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 18/05/14, at 20:26.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface ArticleService {

    /**
     * Find articles in Mercado Libre by specifying a search query.
     *
     * @param query
     *         The query to look for.
     *
     * @return A List of articles in ML.
     */
    List<Article> findArticlesMatching(String query);
}
