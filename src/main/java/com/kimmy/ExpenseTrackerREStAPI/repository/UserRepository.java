package com.kimmy.ExpenseTrackerREStAPI.repository;

import com.kimmy.ExpenseTrackerREStAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
