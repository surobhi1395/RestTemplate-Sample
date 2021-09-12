package com.rest.template.springbootresttemplateex.service;

import com.rest.template.springbootresttemplateex.exception.UserNotFoundException;
import com.rest.template.springbootresttemplateex.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.base.url}")
    private String baseUrl;

    @Value("${api.list.user.url}")
    private String listUserUrl;

    /**
     *
     * @return
     */
     public List<User> getAllUsers() {

         // add url in application.property and read using @Value annotation
         String url =baseUrl + listUserUrl;

         ResponseEntity<User> userResponseEntity = restTemplate.getForEntity(url, User.class);

         if(!ObjectUtils.isEmpty(userResponseEntity)){
             User user = userResponseEntity.getBody();
             return Arrays.asList(user);
         }
         return Collections.emptyList();

    }

    @Override
    public User createUser(User user) {

    ResponseEntity<User> response = restTemplate.getForEntity(baseUrl, User.class);

        User body = response.getBody();
        return body;
    }

    @Override
    public User updateUserById(Long id) {
    String resourceUrl = baseUrl + listUserUrl + "/" + id;
    ResponseEntity<User> response = restTemplate.getForEntity(resourceUrl, User.class);

    if(response.getStatusCode()==HttpStatus.OK){
        User body = response.getBody();
        return body;
    }

        if(ObjectUtils.isEmpty(response)){
            throw new UserNotFoundException("User Not Found");
        }

        return null;
    }

    @Override
    public User deleteUserById(Long id) {
        String resourceUrl = baseUrl+listUserUrl + "/" + id;
        //restTemplate.delete(resourceUrl);
        ResponseEntity<User> response =
                restTemplate.exchange(resourceUrl, HttpMethod.DELETE, null, User.class);
        if(response!=null){
            User body = response.getBody();
            return body;
        }

        if(ObjectUtils.isEmpty(response)){
            throw new UserNotFoundException("User Not Found");
        }

        return null;
    }


}
