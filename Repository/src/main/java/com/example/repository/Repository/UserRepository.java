package com.example.repository.Repository;

import com.example.repository.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findUserByUsername(String username);

    User getUserByEmail(String email);

    List<User> getUsersByRole(String role);

    @Query("select u from User u where  u.age>=?1")
    List<User> getUsersByAgeGreaterThanEqual(Integer age);
}
