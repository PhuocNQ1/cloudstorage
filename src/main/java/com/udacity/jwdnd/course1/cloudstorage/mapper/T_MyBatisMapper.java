package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

public interface T_MyBatisMapper<T> {

    public abstract List<T> getAll();

    public abstract T get(int id);

    public abstract void add(T t);

    public abstract void update(T t);

    public abstract void deleteByID(int id);

    public abstract List<T> getAllByUserId(int id);
}
