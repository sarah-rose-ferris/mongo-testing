package com.example.mongotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoTestApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CredentialsRepository credRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

        //credentials data
        credRepository.deleteAll();

        // save some credentials
        credRepository.save(new Credentials("eve.holt@reqres.in", "cityslicka"));
        credRepository.save(new Credentials("sr@test.com", "test"));

        // fetch credentials
        System.out.println("Credentials found with findAll():");
        System.out.println("-------------------------------");
        for (Credentials cred : credRepository.findAll()) {
            System.out.println(cred);
        }
        System.out.println();

        // fetch an individual entry
        System.out.println("Credential found with findByEmail('eve.holt@reqres.in'):");
        System.out.println("--------------------------------");
        System.out.println(credRepository.findByEmail("eve.holt@reqres.in"));

        System.out.println("Customers found with findByPassword('cityslicka'):");
        System.out.println("--------------------------------");
        System.out.println(credRepository.findByPassword("cityslicka"));

    }
    }


