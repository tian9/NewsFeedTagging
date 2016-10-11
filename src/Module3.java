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
	 * @param list : list of extractedData(InputDataUnit: range indices, type and a Concept Object)
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
			int start = curr.getStart();
			
			if(preEnd > start){
				System.out.println("Wrong Input, OverLapped Tagging from Module 2, ignored the curr concept.");
				System.out.println(String.format("Curr concept starting frm %d: overlapped with previous concept end with %d", start, preEnd));
				listInd++;
				continue;
			}


			if(ind < start){ //non-concept characters
				wrappedList.append(str.substring(ind, start));
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

				Concept concept = curr.getConcept();
				
				//corner case"
				if(concept == null || !existClass(map.get(curr.getType()))) {
					wrappedList.append(str.substring(start, preEnd) + " ");
					System.out.println(String.format("No concept tagged or no Class for the concept at range: %d to %d, \nplain text was appended. ", start, preEnd));
					continue;
				}
				//set concept's string and add its own tag
				concept.setName(str.substring(start, preEnd));
				wrappedList.append(concept.addTag());

			}

		}//while

		//remaining non-concept characters
		if(ind < str.length()) wrappedList.append(str.substring(ind));

		return wrappedList.toString().trim();
	}

	@SuppressWarnings("finally")
	private boolean existClass(String className){
		if(className == null) return false;
		
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
