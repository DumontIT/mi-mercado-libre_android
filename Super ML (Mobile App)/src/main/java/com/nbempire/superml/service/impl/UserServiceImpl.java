package com.nbempire.superml.service.impl;

import com.nbempire.superml.domain.Subscriptions;
import com.nbempire.superml.domain.User;
import com.nbempire.superml.service.UserService;

import java.util.Set;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 05/06/14, at 23:25.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class UserServiceImpl implements UserService {

    @Override
    public boolean subscribe(User user, Set<Subscriptions> subscriptions) {
        return false;
    }
}
