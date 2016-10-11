import java.util.Collections;
import java.util.List;

/**
 * input : newsFeed string -input of module1, and
 * 		   extractedData - a list of InputDataUnit
 * output : wrapped data with specific open and close tag based on the type.
 * 
 * @author jingtian
 *
 */
public class Module3 {
	// final wrapped string 
	private String wrappedNewFeed;

	/**
	 * Constructor : based on input newsFeed string and extractedData(range indices and type)
	 * 				 get final wrapped string.
	 * @param newsFeedStr
	 * @param extractedData
	 */
	public Module3(String newsFeedStr, List<InputDataUnit> extractedData){
		this.wrappedNewFeed = wrap(newsFeedStr, extractedData);
	}

	public String getWrappedNewFeed(){
		return this.wrappedNewFeed; 
	}	

	/**
	 * Go through the concept list from module2 and the input string, 
	 * get final wrapped string.
	 * 
	 * @param str : input newsFeed string
	 * @param list : list of extractedData(InputDataUnit: range indices, type and a Concept Object)
	 * @return 
	 */
	public String wrap(String str, List<InputDataUnit> list){
		StringBuilder wrappedList = new StringBuilder();
		//sort the input by start index
		Collections.sort(list, (a, b) -> a.getStart() - b.getStart());

		int ind = 0;
		int listInd = 0;
		while(ind < str.length() && listInd < list.size()){
			InputDataUnit curr = list.get(listInd);
			Concept concept = curr.getConcept();
			int start = curr.getStart();
			int end = curr.getEnd();
			
			if(ind < start){ //non-concept characters
				wrappedList.append(str.substring(ind, start));
				ind = start;
			}
			//set current concept's string and add its own tag
			concept.setName(str.substring(start, end));
			wrappedList.append(concept.addTag());
			ind = end + 1;
			listInd++;
		}
		//remaining non-concept characters
		if(ind < str.length()) wrappedList.append(str.substring(ind));

		return wrappedList.toString().trim();
	}

}
