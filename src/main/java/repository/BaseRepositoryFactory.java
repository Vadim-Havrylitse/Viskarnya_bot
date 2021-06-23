package repository;

import model.ModelElementId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseRepositoryFactory {
    private final static Map<String, BaseRepository> ALL_Repositories = new ConcurrentHashMap<>();

    public synchronized static <ID, R extends ModelElementId<ID>> BaseRepository of(Class<R> modelClass) {
        String nameClass = modelClass.getName();
        if (!ALL_Repositories.containsKey(nameClass)){
            ALL_Repositories.put(nameClass, new BaseRepositoryImplement());
        }
        return ALL_Repositories.get(nameClass);

    }

}
