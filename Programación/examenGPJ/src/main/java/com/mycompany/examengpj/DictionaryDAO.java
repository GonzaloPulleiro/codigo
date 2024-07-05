 package com.mycompany.examengpj;

import java.util.List;

/**
 *
 * @author Gonzalo
 */
public interface DictionaryDAO<T, K>{
    
    List<T> getAll();
    List<T> getAllFromFile();
    boolean save(T t);
    T get(K palabra);
}
