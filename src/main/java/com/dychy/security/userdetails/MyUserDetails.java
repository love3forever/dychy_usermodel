package com.dychy.security.userdetails;

import com.dychy.model.PrivilegeIns;
import com.dychy.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by eclipse on 2017/1/10.
 */
public class MyUserDetails extends User implements UserDetails{
    private List<PrivilegeIns> privs;
    private User user;

    public MyUserDetails(User user, List<PrivilegeIns> privs) {
        this.user = user;
        this.privs = privs;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(privs == null || privs.size() <1){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for(PrivilegeIns pri : privs){
            commaBuilder.append(pri.getRestypeId()).append(",");
        }
        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getUsername() {
        return super.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
