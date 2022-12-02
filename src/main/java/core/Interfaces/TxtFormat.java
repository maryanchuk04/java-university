package core.Interfaces;

import core.Exceptions.ValidationException;

public interface TxtFormat<T> {
    String toStringSerialize();
    T toObject(String string) throws ValidationException;
}
