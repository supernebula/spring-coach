package com.evol.domain.dto;

import com.evol.domain.model.Permission;
import com.evol.domain.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AccountDetails implements UserDetails {

    private String username;

    private String nickname;

    private String password;

    private Date expiredTime;

    private Integer locked;

    private Date passwordExpiredTime;

    private Integer enabled;

    private List<Role> roleList;

    private List<Permission> permissionList;

    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    public AccountDetails(String username, String password, String nickname, Date expiredTime, Integer locked, Date passwordExpiredTime, Integer enabled, List<Role> roleList, List<Permission> permissionList){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.expiredTime = expiredTime;
        this.locked = locked;
        this.passwordExpiredTime = passwordExpiredTime;
        this.enabled = enabled;
        this.roleList = roleList;
        this.permissionList = permissionList;
        this.grantedAuthorities = new ArrayList<>();
        if(this.roleList != null && this.roleList.size() != 0){
            this.roleList.forEach(role -> {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            });
        }

        if(this.permissionList != null && this.permissionList.size() != 0){
            this.permissionList.forEach(permis -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(permis.getName()));
            });
        }

    }

    public AccountDetails(String username, String password, String nickname, List<Role> roleList,
                          List<Permission> permissionList){
        this(username, password, nickname, null, null, null,null, roleList, permissionList);
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

    public String getNickname() {
        return this.nickname;
    }


    /**
     * 判断账号是否已经未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.expiredTime == null || this.expiredTime.getTime() > System.currentTimeMillis();
    }

    /**
     * 判断账号是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.locked == null || this.locked.equals(0);
    }

    /**
     * 判断密码（用户凭证）是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.passwordExpiredTime == null || this.passwordExpiredTime.getTime() > System.currentTimeMillis();
    }

    /**
     * 判断账号是否已启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enabled == null || this.enabled.equals(0);
    }
}
