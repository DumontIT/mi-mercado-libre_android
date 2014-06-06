package com.nbempire.superml.service;

import com.nbempire.superml.domain.Subscriptions;
import com.nbempire.superml.domain.User;

import java.util.Set;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 05/06/14, at 23:23.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public interface UserService {

    boolean subscribe(User user, Set<Subscriptions> subscriptions);
}
