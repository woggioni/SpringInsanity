package net.woggioni;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Configuration
class Config3 {

    @Bean
    public List<String> lista() {
        return Arrays.asList("element1");
    }

    @Bean(name = "lista")
    public List<String> lista2() {
        return Arrays.asList("element2");
    }
}


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config3.class)
public class InsanityTest3 {

    @Resource(name = "lista")
    List<String> lista;

    @Test
    public void test() {
        Assert.assertEquals(lista, Arrays.asList("element1"));
    }
}

