package action;

import halo.web.action.Action;
import halo.web.action.HkRequest;
import halo.web.action.HkResponse;

public class ChildTestAction implements Action {

	private TestAction action;

	@Override
	public String execute(HkRequest req, HkResponse resp) throws Exception {
		return this.action.hello(req, resp);
	}
}