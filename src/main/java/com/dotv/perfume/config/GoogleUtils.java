package com.dotv.perfume.config;

import com.dotv.perfume.entity.Role;
import com.dotv.perfume.entity.User;
import com.dotv.perfume.entity.UserRole;
import com.dotv.perfume.entity.UserRoleId;
import com.dotv.perfume.repository.RoleRepository;
import com.dotv.perfume.repository.UserRepository;
import com.dotv.perfume.repository.UserRoleRepository;
import com.dotv.perfume.service.UserService;
import com.dotv.perfume.utils.PerfumeUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleUtils {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PerfumeUtils perfumeUtils;

    @Value("${spring.social.google.clientId}")
    String GOOGLE_CLIENT_ID;
    @Value("${spring.social.google.clientSecret}")
    String GOOGLE_CLIENT_SECRET;

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
    public String getToken(final String code, HttpServletRequest request) throws ClientProtocolException, IOException {
        String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
        String GOOGLE_REDIRECT_URI=siteURL+"/login_google";
        String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                        .add("client_secret", GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("access_token");
        return node.textValue();
    }
    public GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);
        //System.out.println(googlePojo);
        return googlePojo;
    }
    public User buildUser(GooglePojo googlePojo) {
        //Kiểm tra nếu là khách hàng mới thì đăng lưu vào db
        if(userRepository.findByEmail(googlePojo.getEmail()).size()==0){
            User user = new User();
            user.setFullName(googlePojo.getName());
            user.setUsername(googlePojo.getEmail());
            user.setPassword(RandomStringUtils.randomAlphabetic(20));
            user.setEmail(googlePojo.getEmail());
            user.setCreatedDate(perfumeUtils.getDateNow());
            user.setStatus(true);
            user.setType("GUEST");
            //lưu user vào db
            userService.saveOrUpdate(user);
            //Tìm role là khách hàng
            Role role = roleRepository.findByCode("G");
            //Tạo id user_role
            UserRoleId userRoleId = new UserRoleId(user.getId(),role.getId());
            //Tạo user_role
            UserRole userRole = new UserRole(userRoleId,"GUEST",perfumeUtils.getDateNow(),true,user,role);
            //save userRole
            userRoleRepository.save(userRole);
        }

//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("GUEST"));
//        UserDetails userDetail = new User(googlePojo.getEmail(),
//                "", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return userRepository.findByEmail(googlePojo.getEmail()).get(0);
    }
}
