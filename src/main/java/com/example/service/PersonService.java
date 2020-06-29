package com.example.service;

import com.example.dao.PersonMapper;
import com.example.domain.PersonDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static java.lang.Thread.sleep;

@Service
public class PersonService {

    @Resource
    private PersonMapper personMapper;

//    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean trans(PersonDO personDO){
        personMapper.updateByName(personDO);
        System.out.println("更新成功！");
        return true;
    }

    @Transactional
    public PersonDO selectById(int id){
        return personMapper.selectById(id);
    }

    @Transactional
    public long update(PersonDO personDO){
       return personMapper.update(personDO);
    }
}
