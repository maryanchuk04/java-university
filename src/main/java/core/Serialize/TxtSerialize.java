package Serialize;



import core.Exceptions.ValidationException;
import core.Interfaces.ISerialize;
import core.Interfaces.TxtFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TxtSerialize<T extends TxtFormat> implements ISerialize<T> {

    @Override
    public void writeObject(String fileName, T object) throws IOException {

        try(FileWriter fos = new FileWriter(fileName)){
            fos.write(object.toStringSerialize());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public T readObject(String fileName, Class<T> tClass) throws IOException, InstantiationException, IllegalAccessException, ValidationException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        String objectLine = sb.toString();
        return (T) tClass.newInstance().toObject(objectLine);
    }

    @Override
    public void writeList(String fileName, List<T> object) {

    }

    @Override
    public List<T> readObjectsList(String fileName, Class<T> tClass) {
        return null;
    }
}
