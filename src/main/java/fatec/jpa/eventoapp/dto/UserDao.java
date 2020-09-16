package fatec.jpa.eventoapp.dto;

import fatec.jpa.eventoapp.entity.User;

public interface UserDao {
    User create(String username);

    User save(User user);

    User commit(User user);
}
