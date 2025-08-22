package com.iokays.sample.config;

import com.google.common.collect.Maps;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class ValueConfigurationTest {

    @Resource
    PropertyValueConfiguration propertyValueConfiguration;

    @Resource
    BeanValueConfiguration beanValueConfiguration;

    @Resource
    SpELValueConfiguration spELValueConfiguration;

    @Resource
    ResourceValueConfiguration resourceValueConfiguration;

    @Test
    void testPropertyValue() {
        Assertions.assertEquals("spring-value-sample", propertyValueConfiguration.getApplicationName());
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 5}, propertyValueConfiguration.getArray());
        Assertions.assertTrue(Maps.difference(Map.of("key1", "value1", "key2", "value2"), propertyValueConfiguration.getMap()).areEqual());
    }

    @Test
    void testBeanValue() {
        Assertions.assertEquals("this is bean object", beanValueConfiguration.getName());
    }

    @Test
    void testSpELValue() {
        Assertions.assertEquals("pengyuanbin", spELValueConfiguration.getUserName());
        Assertions.assertEquals(9900, spELValueConfiguration.getIntValue());
        Assertions.assertEquals(9900, spELValueConfiguration.getAnotherIntValue());
    }

    @Test
    void testResourceValue() {
        final List<String> content = resourceValueConfiguration.content();
        Assertions.assertLinesMatch(List.of("This is a text file.", "It contains some text."), content);
        Assertions.assertLinesMatch(List.of("This is a text file.", "It contains some text."), resourceValueConfiguration.content());
        Assertions.assertLinesMatch(List.of("This is a text0 file."), resourceValueConfiguration.content0());
        Assertions.assertLinesMatch(List.of("This is a text1 file."), resourceValueConfiguration.content1());
    }

}