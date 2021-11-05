package com.example.mongotest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest
class edMongoSpec extends Specification {

    @Autowired
    private edHelperFunctions helpers


    def 'check customer is added'() {
        given:
        String firstName = "EdNew"
        String lastName = "Hammer2"
        int size = helpers.checkSize()

        when:
        helpers.addCustomerToDatabase(firstName,lastName)

        then:
        helpers.checkCustomerAdded(firstName)

        and:
        helpers.checkSize() == size+1


    }

    def 'check customer is deleted'() {
        given:
        String firstName = "SR"
        String lastName = "Ferris"

        when:
        helpers.addCustomerToDatabase(firstName,lastName)
        helpers.deleteCustomerFromDatabase(firstName)
        Customer test = helpers.getCustomer(firstName)

        then:
        test == null
    }

    def 'get info from external api and add to db'() {

        given: 'getting first and last name from external api'
        ResponseEntity<String> result = helpers.chooseFunctionWithParam(HttpMethod.GET,3)
        String firstName = helpers.parseFirstNameFromJson(result.getBody())
        String lastName = helpers.parseLastNameFromJson(result.getBody())
        int size = helpers.checkSize()

        when:'adding them to database'
        helpers.addCustomerToDatabase(firstName,lastName)

        then:'checking they have been added'
        helpers.checkCustomerAdded(firstName)

        and:'checking number of customers in database has increased by one'
        helpers.checkSize() == size+1


    }
}
