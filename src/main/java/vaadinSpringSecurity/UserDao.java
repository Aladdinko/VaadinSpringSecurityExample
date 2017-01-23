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
        role.setName(Role.ROLE_ADMIN);
        Role role1 = new Role();
        role1.setName(Role.ROLE_USER);
        Role role2 = new Role();
        role2.setName(Role.ROLE_TRAINEE);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        roles.add(role1);
        roles.add(role2);
        user.setAuthorities(roles);
        return user;
    }
}
