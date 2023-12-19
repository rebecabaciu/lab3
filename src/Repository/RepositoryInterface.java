package Repository;

import Domain.Entity;

import java.io.IOException;
import java.util.ArrayList;
public interface RepositoryInterface<T extends Entity> {
    ArrayList<T> getAll();
    void add(T Entity) throws IOException;
    T get(int poz);
    T getById(int id);
    int size();
    void delete(T Entity) throws IOException;

    void update(T Entity) throws IOException;
}
