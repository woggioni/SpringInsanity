package net.woggioni;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@Data
class Person {
    private String name;
    private String surname;
}

@Configuration
class Config7 {

    @Bean
    public String name() {
        return "Jeff";
    }

    @Bean
    public String surname() {
        return "Bezos";
    }

    /**
     * This only works if the code is compiled with debug symbols ("-g"),
     * otherwise the method parameter names are lost in the .class file and Spring
     * has no way to disambiguate between the two beans "name" and "surname" since
     * they are both of type java.lang.String and suitable for injection.
     * The end result is that this code throws an exception at runtime
     * when it is compiled without debugging symbols but works fine otherwise,
     * which typically means during development (looks like this behaviour
     * was intentionally designed to pass the tests but fail in prod)
     */
    @Bean
    public Person person(String name, String surname) {
        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        return person;
    }
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config7.class)
public class InsanityTest7 {

    @Resource
    Person person;

    @Test
    public void test() {
        Assert.assertEquals(person.getName(), "Jeff");
        Assert.assertEquals(person.getSurname(), "Bezos");
    }
}

