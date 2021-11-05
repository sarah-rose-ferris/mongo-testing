package com.example.mongotest

import org.mockito.internal.matchers.Null
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class mongoSPEC extends Specification {

    @Autowired
    private helperFunctions helpers

   


    def 'check customer is added'() {
        given:
        String firstName = "Ed"
        String lastName = "Hammer"
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
}