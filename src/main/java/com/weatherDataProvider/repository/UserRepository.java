package com.weatherDataProvider.repository;

import com.weatherDataProvider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByName(String name);
    List<User> findAllUserByActive(boolean flag);
}
