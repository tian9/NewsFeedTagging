import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Module3 {
	// final wrapped string 
	private String wrappedNewFeed;

	/**
	 * Constructor : based on input newsFeed string, map(type, className) and extractedData(range indices and type)
	 * 				 get final wrapped string.
	 * @param newsFeedStr
	 * @param extractedData
	 * @param map
	 */
	public Module3(String newsFeedStr, List<InputDataUnit> extractedData, TypeClassMap map){

		this.wrappedNewFeed = wrap(newsFeedStr, extractedData, map.getMap());
	}

	public String getWrappedNewFeed(){
		return this.wrappedNewFeed; 
	}	

	/**
	 * Go through the concept list and the input string, get final wrapped string.
	 * 
	 * @param str : input newsFeed string
	 * @param list : list of extractedData(InputDataUnit: range indices and type)
	 * @param map : hashmap: key: type, value : className
	 * @return 
	 */
	public String wrap(String str, 
			List<InputDataUnit> list,
			Map<String, String> map)
	{
		StringBuilder wrappedList = new StringBuilder();

		//sort the input by index
		Collections.sort(list, (a, b) -> a.getStart() - b.getStart());

		int ind = 0;
		int listInd = 0;
		int preEnd = -1; // to check any overlap tags
		while(ind < str.length() && listInd < list.size()){
			InputDataUnit curr = list.get(listInd);
			String substr = str.substring(curr.getStart(), curr.getEnd());
			int start = curr.getStart();

			if(preEnd > start){
				System.out.println("Wrong Input, OverLapped Tagging from Module 2, ignored the curr concept.");
				System.out.println(String.format("Curr concept starting frm %d: overlapped with previous concept end with %d", start, preEnd));
				listInd++;
				continue;
			}


			if(ind > start){
				//through Excption;
				System.out.println("Wrong Input");
				break;
			}
			else if(ind < start){ //non-concept characters
				wrappedList.append(str.substring(ind, start));
				//System.out.println(String.format("append plain text: %s", str.substring(ind, start)));
				ind = start;
			}
			else{ // concepts
				/*
				 * form a tag by a derived Concept class.
				 */

				//update
				preEnd = curr.getEnd();
				ind = curr.getEnd() + 1;
				listInd++;

				String type = curr.getType();
				if(type == null) {
					wrappedList.append(substr + " ");
					System.out.println(String.format("Type %s is not defined for %s, plain text was appended. ", type, substr));
					continue;
				}
				// use reflection to get an instance of derived class:  Class.forName(className)

				String className = map.get(type);
				if(className == null){
					wrappedList.append(substr + " ");
					System.out.println(String.format("No class assigned for type: %s, plain text was appended. ", type));
					continue;	
				} 
				Class<?> classID = null;
				if(existClass(className)){
					try {
						classID = Class.forName(className);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}else{
					wrappedList.append(substr + " ");
					System.out.println(String.format("No class exist for className: %s, plain text was appended. ", className));
					continue;
				}

				if(classID == null){
					wrappedList.append(substr + " ");
					System.out.println(String.format("No class found for className: %s, plain text was appended. ", className));
					continue;
				}

				Concept concept = null;
				try {
					concept = (Concept) classID.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}

				//append the content (with tag) to result
				if(concept == null) {
					wrappedList.append(substr + " ");
					System.out.println(String.format("Cannot intantiate for class: %s, plain text was appended. ", className));
					continue;
				}
				concept.setName(substr);
				wrappedList.append(concept.addTag());
				//System.out.println("added : " + substr +" with tag");

			}

		}//while

		//remaining non-concept characters
		if(ind < str.length()) wrappedList.append(str.substring(ind));

		return wrappedList.toString().trim();

	}

	@SuppressWarnings("finally")
	private boolean existClass(String className){
		Class<?> classname= null;
		try {
			classname = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(classname == null) 
				return false;
			else 
				return true;
		}
	}

}
