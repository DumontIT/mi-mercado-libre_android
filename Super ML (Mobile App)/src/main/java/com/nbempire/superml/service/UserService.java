package com.nbempire.superml.service;

import android.content.Context;
import com.nbempire.superml.domain.User;
import com.nbempire.superml.exception.UnfixableException;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 05/06/14, at 23:23.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface UserService {

    User create(Context context);

    boolean updateSubscriptions(User user) throws UnfixableException;
}
