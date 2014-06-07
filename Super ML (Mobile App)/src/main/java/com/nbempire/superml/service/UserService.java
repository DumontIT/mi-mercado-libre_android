package com.nbempire.superml.service;

import android.content.Context;
import com.nbempire.superml.domain.User;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 05/06/14, at 23:23.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface UserService {

    boolean updateSubscriptions(User user);

    User create(Context context);
}
