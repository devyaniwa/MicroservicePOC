package com.example.orderconsumer.repo;

import com.example.orderconsumer.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrud extends CrudRepository<User,Integer> {
}
