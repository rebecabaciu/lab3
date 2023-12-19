package Service;

import Domain.Car;
import Repository.Repository;
import Repository.RepositoryInterface;
import Domain.Rent;
import Exception.RentException;

import java.io.IOException;
import java.util.List;
public class RentService {

    private RepositoryInterface<Rent> rentRepository;

    public RentService(RepositoryInterface<Rent> rentRepository){
        this.rentRepository = rentRepository;
    }

    public void addRent(int id, Car car, String startDate, String endDate) throws RentException, IOException {
        if (id < 0) {
            throw new RentException("Invalid id, it can't be negative");
        }
        for(Rent rent: rentRepository.getAll()) {
            if(rent.getId() == id)
                throw new RentException("It already exists a rent with this ID: " + id);
        }

        Rent rent = new Rent(id, car, startDate, endDate);
        rentRepository.add(rent);
    }

    public List<Rent> getAllRents() {
        return rentRepository.getAll();
    }

    public Rent getRentById(int id) throws RentException {
        Rent rent = rentRepository.getById(id);
        if(rent == null) {
            throw new RentException("Couldn't find a rent with this ID: " + id);
        }

        return rent;
    }

    public void updateRent(int id, String newStartDate, String newEndDate) throws RentException, IOException {
        Rent rent = rentRepository.getById(id);
        if(rent == null) {
            throw new RentException("Couldn't find a rent with this ID: " + id);
        }

        rent.setStartDate(newStartDate);
        rent.setEndDate(newEndDate);
        rentRepository.update(rent);
    }

    public void deleteRent(int id) throws RentException, IOException {
        Rent rent = rentRepository.getById(id);
        if(rent == null) {
            throw new RentException("Couldn't find a rent with this ID: " + id);
        }

        rentRepository.delete(rent);
    }
}
