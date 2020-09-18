package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.User;

import javax.persistence.EntityManager;

public class UserDaoJpa extends BaseDaoJpa<User> implements BaseDao<User> {
    public UserDaoJpa() {
        super();
    }

    public UserDaoJpa(EntityManager em) {
        super(em);
    }

    public User create(String username) {
        User user = User.builder()
                .name(username)
                .build();
        return save(user);
    }
}
