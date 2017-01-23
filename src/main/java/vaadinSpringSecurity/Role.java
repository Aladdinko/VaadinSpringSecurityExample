package vaadinSpringSecurity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Maggouh on 20/01/17.
 */
public class Role implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
