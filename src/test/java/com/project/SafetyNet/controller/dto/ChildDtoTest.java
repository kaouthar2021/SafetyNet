package com.project.SafetyNet.controller.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChildDtoTest {
    @Test
    void testGetters() {

        ChildDto childDto = new ChildDto();
        childDto.setFirstName("John");
        childDto.setLastName("Boyd");
        childDto.setAge(39);
        List<String> family;
        family = Arrays.asList("Parent1", "Parent2");
        childDto.setFamily(family);
        assertEquals("John", childDto.getFirstName());
        assertEquals("Boyd", childDto.getLastName());
        assertEquals(39, childDto.getAge());
        assertEquals(family, childDto.getFamily());
    }
}
