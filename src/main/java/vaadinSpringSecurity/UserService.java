package vaadinSpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Maggouh on 20/01/17.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userDao.loadUserByUsername(username);
        return  user;

    }
}
