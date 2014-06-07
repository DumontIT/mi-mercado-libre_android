package com.nbempire.superml.service.impl;

import android.content.Context;
import android.util.Log;
import com.nbempire.superml.dao.UserDao;
import com.nbempire.superml.domain.User;
import com.nbempire.superml.exception.UnfixableException;
import com.nbempire.superml.service.UserService;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 05/06/14, at 23:25.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class UserServiceImpl implements UserService {

    /**
     * Tag for class' log.
     */
    private static final String TAG = "UserServiceImpl";

    private UserDao userDao = new UserDaoImplSpring();

    @Override
    public boolean updateSubscriptions(User user) throws UnfixableException {
        return userDao.updateSubscriptions(user);
    }

    @Override
    public User create(Context context) {
        String userId = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        User user = new User(userId);

        Log.i(TAG, "Created user with ANDROID_ID: " + userId);
        return user;
    }
}
