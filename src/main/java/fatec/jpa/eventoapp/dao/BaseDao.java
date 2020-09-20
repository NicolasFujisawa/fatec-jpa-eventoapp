package fatec.jpa.eventoapp.dao;

public interface BaseDao<T> {
    T save(T dado);
    T commit(T dado);
}
