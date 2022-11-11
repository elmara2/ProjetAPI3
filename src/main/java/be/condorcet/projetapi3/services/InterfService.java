package be.condorcet.projetapi3.services;

import java.util.List;

public interface InterfService<T> {
    public T create(T t) throws Exception;
    public T read(T t) throws Exception;
    public T update(T t) throws Exception;
    public void delete(T t) throws Exception;
    public List<T> all() throws Exception;
}
