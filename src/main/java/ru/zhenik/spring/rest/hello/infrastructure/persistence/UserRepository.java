package ru.zhenik.spring.rest.hello.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zhenik.spring.rest.hello.model.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}

//@Transactional
//interface UserRepositoryCustom {
//    Long createUser(String username, String password);
//}
//
//class UserRepositoryCustomImpl implements UserRepositoryCustom{
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public Long createUser(String username, String password) {
//        User user = new User(username,password);
//        em.persist(user);
//        return user.getId();
//    }
//}

