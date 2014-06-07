package com.nbempire.superml.dao;

import com.nbempire.superml.domain.User;
import com.nbempire.superml.exception.UnfixableException;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 06/06/14, at 21:28.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface UserDao {

    boolean updateSubscriptions(User user) throws UnfixableException;
}
