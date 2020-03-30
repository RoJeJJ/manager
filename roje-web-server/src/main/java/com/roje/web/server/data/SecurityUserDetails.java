package com.roje.web.server.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author Ro
 */
public class SecurityUserDetails extends User {
    private static final long serialVersionUID = -2347652262360565669L;

    private SysUser user;

    public SecurityUserDetails(@NotNull SysUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }
}
