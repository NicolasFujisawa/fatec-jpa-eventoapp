package fatec.jpa.eventoapp.dao;

import java.util.Optional;

public interface BaseDao<T> {
    T save(T dado);
    T commit(T dado);
}
