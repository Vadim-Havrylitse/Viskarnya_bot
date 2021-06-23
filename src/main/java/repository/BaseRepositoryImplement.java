package repository;

import model.ModelElementId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseRepositoryImplement<ID, R extends ModelElementId<ID>> implements BaseRepository<ID, R> {

    private final Map<ID, R> repository = new ConcurrentHashMap<>();

    @Override
    public void deleteAll() {
        repository.clear();
    }

    @Override
    public void deleteById(ID id) {
        repository.remove(id);
    }

    @Override
    public long count() {
        return repository.size();
    }

    @Override
    public R getOne(ID id) {
        return repository.get(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.containsKey(id);
    }

    @Override
    public R save(R element) {
        if (element == null) {
            throw new RuntimeException("It's impossible");
        }
        return repository.put(element.getId(), element);
    }
}
