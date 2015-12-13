package org.mbweb.fss.rest.TestController;

import java.util.concurrent.atomic.AtomicLong;

import org.mbweb.fss.rest.TestDAO.TestHibernateList;
import org.mbweb.fss.rest.TestModel.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/greeting", method = RequestMethod.GET,headers="Accept=application/json")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/greeting1")
    public String thl() throws JsonProcessingException{
    	TestHibernateList thl1 = new TestHibernateList();
    	String horse = thl1.HibernateList();
		return horse;
    	
    }
}