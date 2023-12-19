package Repository;

import Domain.Entity;

import java.io.*;
import java.util.ArrayList;

public class BinaryFileRepository<T extends Entity> extends Repository<T> {
    private String fileName;

    public BinaryFileRepository(String fileName) throws IOException, ClassNotFoundException {
        this.fileName = fileName;
//        try {
//            writeFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        readFile();
    }

    @Override
    public void add(T entity) throws IOException {
        super.add(entity);
        writeFile();
    }

    @Override
    public void delete(T entity) throws IOException {
        super.delete(entity);
        writeFile();
    }

    public void update(T entity) throws IOException {
        super.update(entity);
        writeFile();
    }

    private void readFile() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            this.entities = (ArrayList<T>) ois.readObject();
        } catch (EOFException e) {
            System.out.println("File is empty or does not contain valid serialized data.");
            this.entities = new ArrayList<>();
        }
    }

    private void writeFile() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(entities);
        }
    }

    public void clear() throws IOException {
        entities.clear();
        writeFile();
    }
}
