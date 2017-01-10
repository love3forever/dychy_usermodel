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

    public MyUserDetails(User user, List<PrivilegeIns> privs) {
        super(user);
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
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
