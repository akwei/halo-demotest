package action.user.app;

import halo.web.action.Action;
import halo.web.action.HkRequest;
import halo.web.action.HkResponse;

public class TestAction implements Action {

	@Override
	public String execute(HkRequest req, HkResponse resp) throws Exception {
		System.out.println("test action");
		return null;
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String hello(HkRequest req, HkResponse resp) throws Exception {
		System.out.println("test action [ hello ]");
		return "1.jsp";
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String hello2(HkRequest req, HkResponse resp) throws Exception {
		System.out.println("test action [ hello2 ]");
		return "2.jsp";
	}
}