package core.Interfaces;

import core.Exceptions.ValidationException;
import java.io.IOException;
import java.util.List;


public interface ISerialize<T> {
    void writeObject(String fileName, T object) throws IOException;

    T readObject(String fileName, Class<T> tClass) throws IOException, InstantiationException, IllegalAccessException, ValidationException;

    void writeList(String fileName, List<T> object);

    List<T> readObjectsList(String fileName, Class<T> tClass);

}
