package vaadinSpringSecurity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maggouh on 20/01/17.
 */
@Repository
public class UserDao {

    public User loadUserByUsername(final String username) {
        User user = new User();
        user.setUsername("admin@admin.com");
        user.setPassword("password88");
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setAuthorities(roles);
        return user;
    }
}
