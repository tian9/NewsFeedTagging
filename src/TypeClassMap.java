import java.util.HashMap;
import java.util.Map;

/**
 * This class store, add, and modify (add, remove) concept type from module 2 (key) 
 * 		and the corresponding Class name created in Module 3 (value)
 * @author jingtian
 *
 */
public class TypeClassMap {
	private Map<String, String> map;

	public TypeClassMap(){
		this.map = new HashMap<String, String>();
	}

	public Map<String, String> getMap(){
		return this.map;
	}

	public void addToMap(String type, String className){
		this.map.put(type, className);
	}

	public void removeType(String type){
		this.map.remove(type);
	}
}
