package repository;

import java.util.*;

public interface IWriteRepo <TValue> extends IRepository {
   void add(TValue value);
   void update(TValue value);
   void delete(TValue value);

}