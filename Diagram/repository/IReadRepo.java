package repository;

import java.util.*;

public interface IReadRepo <TKey,TValue> extends IRepository {
   TValue getById(TKey id);
   ArrayList<TValue> getAll();

}