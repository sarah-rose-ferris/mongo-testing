package com.example.mongotest

import com.example.mongotest.WebController
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import spock.lang.Specification

@SpringBootTest
@Component
class helperFunctions extends Specification {


    @Autowired
    private WebController controller;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CredentialsRepository credRepository;

//test

    public ResponseEntity<String> chooseFunction(HttpMethod type){

        switch (type) {
            case HttpMethod.GET:
                return getExecute();
            case HttpMethod.PUT:
                return putExecute();
            case HttpMethod.POST:
                return postExecute();
            default :
                System.out.println("invalid choice of method type");
                return null;
        }

    }

    public ResponseEntity<String> chooseFunctionWithParam(HttpMethod type,int param){

        switch (type) {
            case HttpMethod.GET:
                return getExecuteCustom(param);
            case HttpMethod.PUT:
                return putExecute();
            case HttpMethod.POST:
                return postExecute();
            default :
                System.out.println("invalid choice of method type");
                return null;
        }

    }

    public ResponseEntity<String> postExecute(){
        return controller.createUser();
    }

    public ResponseEntity<String> putExecute(){
        return controller.updateUser();
    }

    public ResponseEntity<String> putRequest(Credentials cred){
        return controller.userCredentials(cred);
    }

    public ResponseEntity<String> getExecute(){
        return controller.getTest();
    }

    public ResponseEntity<String> getExecuteCustom(int param){
        return controller.getTestCustom(param);
    }

    public Customer addCustomerToDatabase(String firstName, String lastName) {
        Customer newCustomer = new Customer(firstName, lastName)
        repository.save(newCustomer);
        return newCustomer
    }

    public int checkSize(){
        return repository.findAll().size()
    }

    public void deleteCustomerFromDatabase(String firstName){
        repository.delete(repository.findByFirstName(firstName))

    }

    public Customer getCustomer(String firstName){
        return repository.findByFirstName(firstName)
    }

    public Credentials getCredentials(String email){
        return credRepository.findByEmail(email)
    }

    public String parseFirstNameFromJson(String input){
        def jsonSlurper = new JsonSlurper()
        def data = jsonSlurper.parseText(input)

        //print(data.data.first_name)


        return data.data.first_name

    }
    public String parseLastNameFromJson(String input){
        def jsonSlurper = new JsonSlurper()
        def data = jsonSlurper.parseText(input)

        //print(data.data.last_name)


        return data.data.last_name

    }
    void testIfResponseBodyContains(ResponseEntity<String> input,String contains){
        assert input.getBody().contains(contains);
    }

    void testResponseBodyType(ResponseEntity<String> input, MediaType type){

        assert input.getHeaders().getContentType().isCompatibleWith(type);
        //slight hack - the format of the content type from the header was different to that of the MediaType;
        // ie the header contained the charset as well
    }

    void checkStatusCode(ResponseEntity<String> input, HttpStatus code){
        assert input.getStatusCode() == code;
    }

    void checkCustomerAdded(String firstName){
        assert repository.findByFirstName(firstName).getFirstName() == firstName
    }
}
