package action;

import halo.web.action.HkRequest;
import halo.web.action.HkResponse;

public class Child2TestAction extends TestAction {

	private TestAction testAction;

	@Override
	public String execute(HkRequest req, HkResponse resp) throws Exception {
		return this.testAction.hello2(req, resp);
	}
}
