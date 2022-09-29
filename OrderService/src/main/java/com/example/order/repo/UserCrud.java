package com.example.order.repo;

import com.example.order.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrud extends CrudRepository<User,Integer> {
}
