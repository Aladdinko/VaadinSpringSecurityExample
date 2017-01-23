package vaadinSpringSecurity;


import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by Maggouh on 20/01/17.
 */
public class User implements UserDetails {

    //authentication
    private String username;
    private String password;

    //Spring security related fields
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;


    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }
    @Override
    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String getUsername() {
        return username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
