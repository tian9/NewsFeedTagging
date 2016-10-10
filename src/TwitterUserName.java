/**
 * TwitterUserName is a specific Concept, with its own open and close tag
 * @author jingtian
 *
 */
public class TwitterUserName extends Concept{
	private final String OPEN = "<a href=\"http://twitter.com/";
	private final String CLOSE = "</a>";

	@Override
	public String addTag() {
		String name = super.getName();
		return OPEN + name + "\">" + name + CLOSE + " ";
	}

}
