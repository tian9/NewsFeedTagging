/**
 * Entity is a specific Concept, with its own open and close tag
 * @author jingtian
 *
 */
public class Entity extends Concept {
	private final String OPEN = "<strong>";
	private final String CLOSE = "</strong>";

	@Override
	public String addTag() {
		return OPEN + super.getName() + CLOSE + " ";
	}

}
