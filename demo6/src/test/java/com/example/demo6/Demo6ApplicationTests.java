package com.example.demo6;

import com.example.demo6.dao.UserDao;
import com.example.demo6.domain.Role;
import com.example.demo6.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo6ApplicationTests {

    @Autowired
    private UserDao userDao;


    @Test
    public void contextLoads() {
      // System.out.println("haha");
        User user = new User();
        user.setuName("赵六");

        Role role = new Role();
        role.setrName("才人");
        role.setrQu("读书");

        user.getRoles().add(role);
        userDao.save(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void test2(){
       /* Specification<User> specification = new Specification<User>() {
        @Override
        public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
            Path uName = root.get("uName");
            Predicate predicate = criteriaBuilder.equal(uName, "王五");

            return predicate;
        }
    };*/
       User  user = new User();
       user.setuName("王五");
       Example<User> example = Example.of(user);

        Optional<User> one = userDao.findOne(example);
        System.out.println(one+ "88888888888888888888888888888");
}

}
