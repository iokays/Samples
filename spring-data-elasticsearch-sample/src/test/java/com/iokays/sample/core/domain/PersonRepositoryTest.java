package com.iokays.sample.core.domain;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class PersonRepositoryTest {

    @Resource
    private PersonRepository personRepository;

    @Test
    void testSave() {
        final Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setFirstname("Peng");
        person.setLastname("Yuanbin");
        person.setCreateDate(LocalDateTime.now());
        personRepository.save(person);

        Optional<Person> optionalPerson = Optional.empty();

        optionalPerson = personRepository.findById(person.getId());
        Assertions.assertTrue(optionalPerson.isPresent());

    }

    @Test
    void testDelete() {
        final Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        personRepository.save(person);

        personRepository.deleteById(person.getId());
        Assertions.assertFalse(personRepository.findById(person.getId()).isPresent());
    }

}