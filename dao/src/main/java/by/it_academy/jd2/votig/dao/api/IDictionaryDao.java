package by.it_academy.jd2.votig.dao.api;

import java.util.List;
import java.util.Optional;

public interface IDictionaryDao<T> {
    T create(T data);
    List<T> get();
    Optional<T> get(long id);
    T update(long id, T data);
    void delete(long id);
}
