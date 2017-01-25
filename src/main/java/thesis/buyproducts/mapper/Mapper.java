package thesis.buyproducts.mapper;

import java.util.List;

public interface Mapper<Entity, VO> {
	
	public VO mapVOFrom(Entity entity);
	
	public Entity mapEntityFrom(VO vo);
	
	public List<VO> mapEntityFrom(List<Entity> entityList);
	
	public List<Entity> mapVOFrom(List<VO> voList);
	
}
