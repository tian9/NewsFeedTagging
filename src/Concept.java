/**
 * the concept that has a name, which is an extracted substring of a newsFeed.
 * @author jingtian
 *
 */
public class Concept {
	private String name;

	public Concept(){}

	public Concept(String name){
		this.name = name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	/**
	 * 
	 * @return the string that with tag wrapped.
	 */
	public String addTag(){
		return this.name;
	}



}
