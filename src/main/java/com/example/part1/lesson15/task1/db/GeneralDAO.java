package com.example.part1.lesson15.task1.db;

import java.sql.SQLException;
import java.util.List;

public interface GeneralDAO<T> {

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public T getByPK(Integer key) ;
    /** Сохраняет состояние объекта в базе данных */
    public void insert(T object);

    /** Обновляет состояние объекта в базе данных */
    public void update(T object);

    /** Удаляет запись об объекте из базы данных */
    public void delete(T object) ;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll() ;

    /** Добавляет подготовленные данные  */
    public void usePreparedStatement(List<T> rr) throws SQLException;
}
