package Repository;

import Domain.Car;
import Domain.Entity;
import Domain.Rent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Repository <T extends Entity> implements RepositoryInterface<T> {
    public ArrayList<T> entities;
    public Repository() {
        this.entities = new ArrayList<T>();
    }

    public ArrayList<T> getAll() {
        return entities;
    }

    public void add(T Entity) throws IOException {
        this.entities.add(Entity);
    }

    public T get(int poz) {
        if (poz < this.entities.size() - 1 && poz >= 0) return entities.get(poz);
        throw new NoSuchElementException();
    }

    public T getById(int id) {
        for (T entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }

    public void update(T updatedEntity) throws IOException {
        int id = updatedEntity.getId();
        for (int i = 0; i < entities.size(); i++) {
            T existingEntity = entities.get(i);
            if (existingEntity.getId() == id) {
                if (updatedEntity instanceof Car && existingEntity instanceof Car) {
                    Car updatedCar = (Car) updatedEntity;
                    Car existingCar = (Car) existingEntity;
                    existingCar.setBrand(updatedCar.getBrand());
                    existingCar.setModel(updatedCar.getModel());
                } else if (updatedEntity instanceof Rent && existingEntity instanceof Rent) {
                    Rent updatedRent = (Rent) updatedEntity;
                    Rent existingRent = (Rent) existingEntity;
                    existingRent.setStartDate(updatedRent.getStartDate());
                    existingRent.setEndDate(updatedRent.getEndDate());
                } else {
                    throw new IllegalArgumentException("Invalid type for update");
                }
                return;
            }
        }
        throw new NoSuchElementException("Entity with ID " + id + " not found for update");
    }



    public int size() {
        return entities.size();
    }

    public void delete(T Entity) throws IOException {
        this.entities.remove(Entity);
    }
}
