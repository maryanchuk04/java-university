package core.Serialize;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import core.Interfaces.ISerialize;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class JsonSerialize<T> implements ISerialize<T> {
    private ObjectMapper objectMapper;
    private Class<?> tClass;


    public JsonSerialize() {
        this.tClass = getClass();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void writeObject(String fileName, T object) {
        try {
            objectMapper.writeValue(new File(fileName), object);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public T readObject(String fileName, Class<T> tClass) {
        try{
            var res = objectMapper.readValue(Paths.get(fileName).toFile(), tClass);
            return res;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeList(String fileName, List<T> object) {
        try{
            objectMapper.writeValue(new File(fileName), object);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> readObjectsList(String fileName, Class<T> tClass) {
        try{
            List<T> res = objectMapper.readerForListOf(tClass).readValue(new File(fileName));
            return res;
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
