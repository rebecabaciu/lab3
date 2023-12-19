package Repository;

import Domain.Car;
import Domain.Entity;
import Domain.IEntityFactory;
import Domain.Rent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileRepository <T extends Entity> extends Repository<T> {
    private  String fileName;
    private IEntityFactory<T> entityFactory;

    public FileRepository(String fileName, IEntityFactory<T> entityFactory) throws FileNotFoundException {
        this.fileName = fileName;
        this.entityFactory = entityFactory;

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

    private void writeFile() {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (T entity : entities) {
                fw.write(entityFactory.toStringEntity(entity));
                fw.write("\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void readFile() throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            T entity = entityFactory.createEntity(line);
            entities.add((entity));
        }
    }

    public void clear() {
        entities.clear();
        writeFile();
    }
}
