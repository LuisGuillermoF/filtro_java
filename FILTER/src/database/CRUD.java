package database;

import java.util.List;

public interface CRUD {

    public List<Object> findAll();

    public boolean update(Object obj);

    public boolean delete(Object obj);

    public Object insert(Object obj);

}
