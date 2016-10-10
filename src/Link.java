/**
 * Link is a specific Concept, with its own open and close tag
 * @author jingtian
 *
 */
public class Link extends Concept{
	private final String OPEN = "<a href=\"";
	private final String CLOSE = "</a>";

	@Override
	public String addTag(){
		String name = getName();
		return OPEN + name + "\">" + name + CLOSE + " ";
	}
}
