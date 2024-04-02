package com.java.choretracker.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.java.choretracker.models.LoginUser;
import com.java.choretracker.models.User;
import com.java.choretracker.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
    
    //register a new user
    public User register(User newUser, BindingResult result) {
    	
        Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
        if (potentialUser.isPresent()) {result.rejectValue("email", "Matches", "Email exists");}
        
        if (!newUser.getPassword().equals(newUser.getConfirm())) {result.rejectValue("confirm", "Matches", "Passwords do not match");}
        
        if (result.hasErrors()) {return null;}
    
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        return userRepository.save(newUser);
    }

    
 
    
	 // login an existing user
	 public User login(LoginUser newLoginObject, BindingResult result) {
	
	     Optional<User> potentialUser = userRepository.findByEmail(newLoginObject.getEmail());
	
	     if (!potentialUser.isPresent()) {
	         result.rejectValue("email", "Matches", "Invalid email or password");
	         return null;
	     }
	
	     User user = potentialUser.get();
	
	     if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
	         result.rejectValue("password", "Matches", "Invalid email or password");
	         return null;
	     }
	
	     if (result.hasErrors()) {
	         return null;
	     }
	
	     return user;
	 }

    
    
	 
    // return all users
    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }
    
    
    // create user
    public User createUser(User u) {
        return userRepository.save(u);
    }
    
    
    // retrieve user
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
    


}
