package com.sm.portal.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sm.portal.model.Users;
import com.sm.portal.model.UsersDto;
import com.sm.portal.service.UserService;

@RestController
@RequestMapping(APIConstants.BASE_API_URL)
public class UserRestController {

	@Autowired
    private UserService userService;
	
	@GetMapping(value = APIConstants.USER_PROFILE_API_URL)
    public ResponseEntity<Users> getUserDetails(@PathVariable("username") String username) {
		Users user = userService.findUserByUserName(username);
        if(user == null){
            return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }
	
	@PostMapping(value = APIConstants.CREATE_USER_API_URL)
    public ResponseEntity<Void> createUser(@RequestBody UsersDto user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getFirstname());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getFirstname() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
