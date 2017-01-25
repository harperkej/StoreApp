package thesis.buyproducts.mapper;

import java.util.List;

public interface Mapper<Entity, Dto> {

    public Dto mapEntityFrom(Entity entity);

    public Entity mapDtoFrom(Dto dto);

    public List<Dto> mapEntitiesFrom(List<Entity> entityList);

    public List<Entity> mapDtosFrom(List<Dto> dtoList);

}
