package fatec.jpa.eventoapp.dao;

public interface BaseDao<T> {
    T save(T data);
    T commit(T data);
    void delete(T data);
}
