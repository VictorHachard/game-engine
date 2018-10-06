package gameengine.entities.builder;

public class WrapperGameObject {
	private String id;
	private BuilderGameObjectFactory builder;
	
	
	public WrapperGameObject(String id, BuilderGameObjectFactory builder) {
		super();
		this.id = id;
		this.builder = builder;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BuilderGameObjectFactory getBuilder() {
		return builder;
	}
	public void setBuilder(BuilderGameObjectFactory builder) {
		this.builder = builder;
	}
	
}
