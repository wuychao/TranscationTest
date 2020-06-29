package com.example.controller;

import com.example.dao.PersonMapper;
import com.example.domain.PersonDO;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by FlySheep on 17/3/25.
 */
@EnableTransactionManagement  // 需要事务的时候加上
@RestController
public class PersonController {

    @Resource
    private PersonMapper personMapper;

    @Resource
    private PersonService personService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/save")
    public Integer save() {
        PersonDO personDO = new PersonDO();
        personDO.setName("张三");
        personDO.setAge(18);
        personMapper.insert(personDO);
        return personDO.getId();
    }

    @RequestMapping("/update")
    public long update() {
        PersonDO personDO = new PersonDO();
        personDO.setId(2);
        personDO.setName("wyc");
        personDO.setAge(20);
        return personService.update(personDO);
    }

    @RequestMapping("/delete")
    public Long delete() {
        return personMapper.delete(1);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @RequestMapping("/selectById")
    public PersonDO selectById() throws InterruptedException {

        PersonDO res = personService.selectById(2);
        System.out.println(res.toString());
        Thread.sleep(20000);
        PersonDO res1 = personService.selectById(2);
        System.out.println(res1.toString());
        return null;
    }

    @RequestMapping("/selectAll")
    public List<PersonDO> selectAll() {
        return personMapper.selectAll();
    }

//    @Transactional
    @Transactional
    @RequestMapping("/transaction")
    public PersonDO transaction() {
        PersonDO personDO = new PersonDO();
        personDO.setId(2);
        personDO.setAge(20);
        personDO.setName("wyc");
        personService.trans(personDO);
        return personDO;
    }

}
