package com.solvd.dao;

import java.util.List;
import java.util.Optional;

public interface IBaseDAO<T> {
    T create(T entity);

    Optional<T> getById(Long id);

    List<T> getAll();

    T update(T entity);

    boolean deleteById(Long id);
}