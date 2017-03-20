package com.dychy.security.userdetails;

import com.dychy.model.Resource;
import com.dychy.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by eclipse on 2017/1/10.
 * 完成UserDetails中一些方法的实现(带有override注解的都是)
 */
public class MyUserDetails extends User implements UserDetails{
    // 用户能够访问的资源列表
    private List<Resource> resources;

    public MyUserDetails(User user, List<Resource> resources) {
        super(user);
        this.resources = resources;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(resources == null || resources.size() <1){
            // 将用户可访问资源序列化
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for(Resource r : resources){
            commaBuilder.append(r.getResURL()).append(",");
        }
        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
        // 返回用户可访问资源实例
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
