package com.udacity.jwdnd.course1.cloudstorage.services.generic_service;

import java.util.List;

public abstract class T_Service<T> {
    public abstract List<T> getAll();

    public abstract T get(int id);

    public abstract void add(T t);

    public abstract void update(T t);

    public abstract void deleteByID(int id);

    public abstract void delete(T t);
}
