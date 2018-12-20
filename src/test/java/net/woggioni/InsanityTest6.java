package net.woggioni;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@Configuration
class Config6 {

    @Bean(name = "lista")
    public List<String> lista() {
        return Arrays.asList("element2");
    }
}

@Configuration
class Config6_2 {

    @Bean
    public List<String> lista() {
        return Arrays.asList("element1");
    }
}


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config6.class, Config6_2.class})
public class InsanityTest6 {

    @Autowired
    @Qualifier("lista")
    List<String> lista;

    @Test
    public void test() {
        Assert.assertEquals(lista, Arrays.asList("element1"));
    }
}

