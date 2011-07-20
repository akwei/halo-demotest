package action;

import halo.web.action.Action;
import halo.web.action.HkRequest;
import halo.web.action.HkResponse;
import halo.web.util.WebCnf;

import org.springframework.stereotype.Component;

/**
 * 假设配置的{@link WebCnf#setUrl_extension(String)}值为.do
 * 
 * @author akwei
 */
@Component("/hello")
public class HelloAction implements Action {

	/**
	 * 请求为 http://localhost:8080/webapp/hello.do
	 */
	@Override
	public String execute(HkRequest req, HkResponse resp) throws Exception {
		return "/page/hello.jsp";
	}

	/**
	 * 请求为 http://localhost:8080/webapp/hello_method1.do
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String method1(HkRequest req, HkResponse resp) throws Exception {
		return "/page/1.jsp";
		// forward到/page/1.jsp
	}

	/**
	 * 请求为 http://localhost:8080/webapp/hello_method2.do
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String metod2(HkRequest req, HkResponse resp) throws Exception {
		return "r:/page/2.jsp";
		// redirect到/page/2.jsp
	}

	/**
	 * 请求为 http://localhost:8080/webapp/hello_method4.do
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String method4(HkRequest req, HkResponse resp) throws Exception {
		return "r:/test_hello.do?v=1";
		// redirect到${requestContextpath}/test_hello.do?v=1
	}

	/**
	 * 请求为 http://localhost:8080/webapp/hello_method5.do
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String method5(HkRequest req, HkResponse resp) throws Exception {
		// 假如你使用其他代理服务器(apache,ngnix)进行urlrewrite
		// 定义的url为 http://www.xxx.com/login
		return "rr:/login";
		// redirect到 http://www.xxx.com/login上
	}

	/**
	 * 请求为 http://localhost:8080/webapp/hello_method6.do
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String method6(HkRequest req, HkResponse resp) throws Exception {
		// 需要定义到应用之外的url
		return "r:http://www.google.com";
		// 只能为redirect方式
	}
}
