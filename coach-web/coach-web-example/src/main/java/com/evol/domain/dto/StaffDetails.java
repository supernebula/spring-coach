package com.evol.domain.dto;

import com.evol.domain.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StaffDetails implements UserDetails {

    private String username;

    private String password;

    private List<Role> roleList;

    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    public StaffDetails(String username, String password, List<Role> roleList){
        this.username = username;
        this.password = password;
        this.roleList = roleList;
        this.grantedAuthorities = new ArrayList<>();
        this.roleList.forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        //authorities.add(new SimpleGrantedAuthority("user:create"));
    }

//    private List<GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
