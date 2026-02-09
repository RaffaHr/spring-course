package com.raffadev.springcourse.repository;

import com.raffadev.springcourse.model.UserModel;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
