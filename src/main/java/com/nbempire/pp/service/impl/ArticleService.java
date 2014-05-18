package com.nbempire.pp.service.impl;

import com.nbempire.pp.domain.Article;

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

    List<Article> findArticlesMatching(String query);
}
