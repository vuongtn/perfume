package com.dotv.perfume.config;

import com.dotv.perfume.entity.Role;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.entity.UserRole;
import com.dotv.perfume.entity.UserRoleId;
import com.dotv.perfume.repository.RoleRepository;
import com.dotv.perfume.repository.UserRoleRepository;
import com.dotv.perfume.service.UserService;
import com.dotv.perfume.utils.PerfumeUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RestFacebook {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    PerfumeUtils perfumeUtils;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    public static String FACEBOOK_APP_ID = "702774770765046";
    public static String FACEBOOK_APP_SECRET = "7ed7978e1a6c53b78fe6d1baf287795a";
    public static String FACEBOOK_REDIRECT_URL = "https://localhost:8443/login_facebook";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
    public String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("access_token");
        return node.textValue();
    }

    public com.restfb.types.User getUserInfo(final String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
        return facebookClient.fetchObject("me", com.restfb.types.User.class);
    }
    public UserDetails buildUser(com.restfb.types.User user) {
        String pass = bCryptPasswordEncoder.encode(RandomStringUtils.randomAlphabetic(6));
        User userDb = new User();
        userDb.setUsername(user.getUsername());
        userDb.setPassword(pass);
        userDb.setEmail(user.getEmail());
        userDb.setFullName(user.getFirstName());
        userDb.setCreatedDate(perfumeUtils.getDateNow());
        userDb.setStatus(true);
        userDb.setType("GUEST");
        //lưu user vào db
        userService.saveOrUpdate(userDb);
        //Tìm role là khách hàng
        Role role = roleRepository.findByCode("G");
        //Tạo id user_role
        UserRoleId userRoleId = new UserRoleId(userDb.getId(),role.getId());
        //Tạo user_role
        UserRole userRole = new UserRole(userRoleId,"GUEST",perfumeUtils.getDateNow(),true,userDb,role);
        userRoleRepository.save(userRole);


//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;

        //chuyển user -> userDetail để spring security quản lý
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("GUEST"));
//        UserDetails userDetail = new User(user.getId(), "", enabled, accountNonExpired, credentialsNonExpired,
//                accountNonLocked, authorities);
        UserDetails userDetail=userDb;
        return userDetail;
    }
}
