/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import java.util.List;

/**
 *
 * @author Gonzalo
 * @param <T>
 */
public interface DAO<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    public boolean deleteById(long id);

    public List<Integer> getAllIds();

    public void updateLOB(T book, String f);

    public void updateLOBById(long id, String f);

    void deleteAll();

}
