package com.example.repository.Service;

import com.example.repository.ApiResponse.ApiException;
import com.example.repository.Model.User;
import com.example.repository.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void updateUser(Integer id,User user){
        User oldUser = userRepository.findUserById(id);

        if(oldUser != null){
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setEmail(user.getEmail());
            oldUser.setAge(user.getAge());
            oldUser.setName(user.getName());
            oldUser.setRole(user.getRole());
            userRepository.save(oldUser);
        }else throw new ApiException("user not updated");
    }
    public void deleteUser(Integer id){
        User olduser = userRepository.findUserById(id);
        if(olduser != null){
            userRepository.delete(olduser);
        }else throw new ApiException("user not deleted");
    }

    public User findUserById(Integer id){
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        return user;
    }

    public String checkUsernamePassword(String username, String password){
        User user= userRepository.findUserByUsername(username);
        if(user == null){
            throw new ApiException("user not found");
        }
        if(!user.getPassword().equals(password)){
            throw new ApiException("wrong password");
        }
        return "username and password are correct";
    }


    public User getUserByEmail(String email){
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new ApiException("user with exact email not found");
        }
        return user;
    }

    public List<User> getUserByRole(String role){
        List<User> users = userRepository.getUsersByRole(role);
        if (users == null) {
            throw new ApiException("user with exact role not found");
        }else return users;
    }

    public List<User> getUsersByAgeGreaterThanEqual(Integer age){
        List<User> users = userRepository.getUsersByAgeGreaterThanEqual(age);
        if (users == null) {
            throw new ApiException("user with exact age not found");
        }
        return users;
    }
}
