package com.itcoke.mapperproxy;

import com.itcoke.bean.Person;

public interface PersonMapper {

    Person selectPersonById(Long pid);
}
