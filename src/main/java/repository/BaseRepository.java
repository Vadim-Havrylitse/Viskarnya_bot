package repository;

import model.ModelElementId;

import java.util.Optional;

public interface BaseRepository <ID, R extends ModelElementId<ID>>{

    public void deleteAll();

    public void deleteById(ID id);

    public long count();

    public R getOne(ID id);

    public boolean existsById(ID id);

    public R save(R element);


}
