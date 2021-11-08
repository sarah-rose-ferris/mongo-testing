package com.example.mongotest;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CredentialsRepository extends MongoRepository<Credentials, String> {

    public Credentials findByEmail(String email);

    public Credentials findByPassword(String password);

}
