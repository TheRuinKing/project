package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.NewFollowingRequest;
import com.sorsix.finkicommunity.domain.requests.NewUserRequest;
import com.sorsix.finkicommunity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(NewUserRequest newUserRequest){
        User user = new User();

        user.setUsername(newUserRequest.getUsername());
        user.setEmail(newUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(newUserRequest.getPassword()));
        user.setFirstName(newUserRequest.getFirstName());
        user.setLastName(newUserRequest.getLastName());
        user.setBirthdate(newUserRequest.getBirthdate());
        user.addRole("USER");

        try{
            return userRepository.save(user);
        }catch(RuntimeException ex){
            return null;
        }
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<NewFollowingRequest> addNewFollowing(NewFollowingRequest newFollowingRequest){

        Optional<User> userFollowing = userRepository.findById(newFollowingRequest.getUserIdFollowing());
        Optional<User> userFollowed = userRepository.findById(newFollowingRequest.getUserIdFollowed());

        return userFollowing.map(
                user1 -> Optional.of(newFollowingRequest)
        ).orElseGet(
                ()-> Optional.empty()
        );

//        if(userFollowing.isPresent() && userFollowed.isPresent()){
//            User user1 = userFollowing.get();
//            User user2 = userFollowed.get();
//
//            user1.addNewFollowing(user2);
//            userRepository.save(user1);
//            Optional.of(newFollowingRequest);
//        }
//        return Optional.empty();
    }
}
