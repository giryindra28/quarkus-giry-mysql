package api.service;

import java.util.List;

public interface BaseServiceCrud <T, B, ID>{
    List<T> getAll();
    T getById(ID id);
    T create(B b);
    T update(ID id, B b);
    void delete(ID id);
}
