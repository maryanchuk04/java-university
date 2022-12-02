package Serialize;

import core.Interfaces.ISerialize;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlSerialize<T> implements ISerialize<T> {
    private ObjectMapper objectMapper;

    public XmlSerialize() {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        objectMapper = new XmlMapper(xmlModule);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void writeObject(String fileName, T object) {
        try{
            objectMapper.writeValue(new File(fileName), object);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public T readObject(String fileName, Class<T> tClass) {
        try{
            var res = objectMapper.readValue(new File(fileName), tClass);
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
        catch (IOException e){
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
