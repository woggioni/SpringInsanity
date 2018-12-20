package net.woggioni;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
class Config {

    @Bean
    public String foo() {
        return "FOO";
    }

    @Bean
    public String bar() {
        return "BAR";
    }

    @Bean
    public String baz() {
        return "BAZ";
    }

    @Bean
    public Map<String, String> mappa() {
        return Collections.emptyMap();
    }

    @Bean
    public List<String> lista() {
        return Collections.emptyList();
    }
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class InsanityTest {

    @Autowired
    Map<String, String> mappa;

    @Inject
    Map<String, String> mappa2;

    @Resource
    Map<String, String> mappa3;

    @Resource(name = "mappa")
    Map<String, String> mappa4;

//  At least this will break
//  @Resource(name = "lista")
//  Map<String, String> mappa5;

    @Autowired
    List<String> lista;

    @Inject
    List<String> lista2;

    @Resource
    List<String> lista3;

    @Resource(name = "lista")
    List<String> lista4;

    @Resource(name = "mappa")
    List<String> lista5;

    @Test
    public void testLista() {
        List<String> expectedList = Arrays.asList("FOO", "BAR", "BAZ");
        Assert.assertEquals(expectedList, lista);
        Assert.assertEquals(expectedList, lista2);
        Assert.assertEquals(expectedList, lista3);
        Assert.assertEquals(Collections.emptyList(), lista4);
        //This is truly insane
        Assert.assertEquals(Arrays.asList(Collections.emptyMap()), lista5);
    }

    @Test
    public void testMappa() {
        HashMap<String, String> expected_map = new HashMap<>();
        expected_map.put("foo", "FOO");
        expected_map.put("bar", "BAR");
        expected_map.put("baz", "BAZ");
        Assert.assertEquals(expected_map, mappa);
        Assert.assertEquals(expected_map, mappa2);
        Assert.assertEquals(expected_map, mappa3);
        Assert.assertEquals(Collections.emptyMap(), mappa4);
    }
}

