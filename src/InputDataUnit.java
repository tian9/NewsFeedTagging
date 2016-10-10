/**
 * input data unit that save data generated from module 2:
 * start index in the newsFeed string (inclusive)
 * end index in the newsFeed string (exclusive)
 * type that defined by the concept
 * @author jingtian
 *
 */
public class InputDataUnit{
	private int start;
	private int end;
	private String type;

	/**
	 * constructor
	 * @param start : start index of the concept substring (inclusive)
	 * @param end : end index of the concept substring (exclusive)
	 * @param type : the concept type, such as "Entity".
	 */
	public InputDataUnit(int start, int end, String type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}

	/**
	 * constructor without a specific type
	 * @param start
	 * @param end
	 */
	public InputDataUnit(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * set both start and end index (exclusive) of a substring
	 * @param start
	 * @param end
	 */
	public void setRange(int start, int end){
		this.start = start;
		this.end = end;
	}

	public int getStart(){
		return this.start;
	}

	public int getEnd(){
		return this.end;
	}

}
