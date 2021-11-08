package com.example.mongotest

import groovy.json.JsonSlurper
import org.apache.groovy.json.internal.LazyMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpStatusCodeException
import spock.lang.Specification

@SpringBootTest
class credSpec extends Specification{

    @Autowired
    helperFunctions helperFunctions;

    def 'get credentials from db and try post request' () {
        given:
        Credentials cred = helperFunctions.getCredentials("eve.holt@reqres.in")
        ResponseEntity<String> result = helperFunctions.putRequest(cred)

        expect:
        result != null;

        and:
        helperFunctions.checkStatusCode(result, HttpStatus.OK)
        and:
        result.body.contains("token")


    }

    def 'get wrong credentials from db and try post request' () {
        given:
        Credentials cred = helperFunctions.getCredentials("sr@test.com")
        HttpStatus resultingStatusCode
        LazyMap resultingBody

        try {
        ResponseEntity<String> result = helperFunctions.putRequest(cred)
    } catch (HttpStatusCodeException e) {
         resultingStatusCode = e.statusCode
    }
        expect:
        resultingStatusCode == HttpStatus.BAD_REQUEST


    }
}
