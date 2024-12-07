package com.example.realestate.User.repository;


import com.example.realestate.User.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User , String> {

}
