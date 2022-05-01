//package com.evol.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
//import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
///**
// * 授权服务器
// */
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfigOld extends AuthorizationServerConfigurerAdapter {
//
//    private static String REALM="USER_REALM";
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private UserApprovalHandler userApprovalHandler;
//
//    @Autowired
//    //@Qualifier("authenticationManagerBean")
//    private AuthenticationManager authenticationManager;
//
//
//    @Autowired
//    private ClientDetailsService clientDetails;
//
//    @Autowired
//    private AccessTokenConverter accessTokenConverter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//// 内存模式
//        clients.inMemory().withClient("demoApp").secret(passwordEncoder().encode("demoAppSecret"))
//         .redirectUris("http://baidu.com")// code授权添加
//         .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
//         // scopes的值就是all（全部权限），read，write等权限。就是第三方访问资源的一个权限，访问范围
//         .scopes("all")
//         // 这个资源服务的ID，这个属性是可选的，但是推荐设置并在授权服务中进行验证。
//         .resourceIds("oauth2-resource")
//         // 设置accessTokenValiditySeconds属性来设置Access Token的存活时间。
//         .accessTokenValiditySeconds(1200)
//         // 设置refreshTokenValiditySeconds属性来设置refresh Token的存活时间。
//         .refreshTokenValiditySeconds(50000);
//
//
//        // 数据库模式
//        clients.withClientDetails(clientDetails); // 表中存储的secret值是加密后的值，并非明文；
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints
//                // 认证管理器
//                .authenticationManager(authenticationManager)
//                // 允许 GET、POST 请求获取 token，即访问端点：oauth/token
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                // 要使用refresh_token的话，需要额外配置userDetailsService
//                .userDetailsService(userDetailsService)
//                // 指定token存储位置
//                .tokenStore(tokenStore)
//                // 配置JwtAccessToken转换器
//                .accessTokenConverter(accessTokenConverter)
//                // 客户端详细信息服务的基本实现 这里使用JdbcClientDetailsService
//                .setClientDetailsService(clientDetails);
////        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler).authenticationManager(authenticationManager);
//    }
//
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception{
//        oauthServer
//                //code授权填写
//                .realm(REALM + "/client")
//                //开启/oauth/token_key 验证端口无权限访问
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()")
//                //是 /oauth/token支持client_id 及 client_secret作为登录认证
//        .allowFormAuthenticationForClients()
//                //密码编码器
//        .passwordEncoder(passwordEncoder());
//    }
//
//}
