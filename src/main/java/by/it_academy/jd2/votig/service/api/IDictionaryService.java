package by.it_academy.jd2.votig.service.api;

import java.util.List;
import java.util.Optional;

public interface IDictionaryService<T, CU> {
    T create(CU data);
    List<T> get();
    Optional<T> get(long id);
    T update(long id, CU data);
    void delete(long id);
}
