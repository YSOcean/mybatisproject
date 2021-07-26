package com.itcoke.mapper;

import com.itcoke.bean.Person;


public interface PersonMapper {

    Person selectPersonById(long pid);

    void updatePersonById(Person person);

    void insertPerson(Person person);

    void deletePersonById(long pid);
}
