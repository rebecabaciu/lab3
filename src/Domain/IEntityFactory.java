package Domain;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IEntityFactory<T extends Entity> {
    public T createEntity(String line);

    public String toStringEntity(T entity);

}
