package action;

import halo.web.action.Action;
import halo.web.action.HkRequest;
import halo.web.action.HkResponse;

import org.springframework.stereotype.Component;

@Component("/test")
public class TestAction implements Action {

	@Override
	public String execute(HkRequest req, HkResponse resp) throws Exception {
//		req.setAttribute("key", "value");
//		int num = req.getInt("num");// 获得int 数据
//		num = req.getInt("num", 1);// 获得int 数据，如果没有num参数，则默认返回 值1
//		long num_long = req.getInt("sysid");
//		num_long = req.getLong("num", 9L);
//		double num_double = req.getDouble("num");
//		num_double = req.getDouble("num", 4.0d);
//		float num_float = req.getFloat("num");
//		num_float = req.getFloat("num", 89f);
//		byte num_byte = req.getByte("num");
//		num_byte = req.getByte("num", (byte) 8);
//		boolean bool = req.getBoolean("bool");
//		String value = req.getString("name");// 获得String 参数值
//		value = req.getString("name", "default");
//		value = req.getStringRow("name");// 获得String值，并强制取消回车与换行
//		String[] values = req.getStrings("name");// 获得一组String 值
//		int[] nums = req.getInts("nums");
//		long[] num_longs = req.getLongs("nums");
//		Cookie cookie = req.getCookie("cookie_name");// 根据name获得cookie
//		File file = req.getFile("file_name");// 根据name获得上传的文件
//		File[] files = req.getFiles();// 获得所有上传的文件
//		String fileName = req.getOriginalFileName("file_name");//
//		// 获得文件存储的原文件名称
//		Object obj_in_session = req.getSessionValue("key");//
//		// 获得存储到session中的数据
//		req.setSessionValue("key", obj_in_session);// 存储数据到session;
//		// 向页面输出xml数据，xml头信息已经输出<?xml version=\"1.0\" encoding=\"UTF-8\"?>
//		resp.sendXML("<resp>hello</resp>");
//		// 向页面输出数据html页面显示
//		resp.sendHtml("data");
		return null;
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String hello(HkRequest req, HkResponse resp) throws Exception {
		return "/page/1.jsp";
		// forward到/page/1.jsp
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String hello2(HkRequest req, HkResponse resp) throws Exception {
		return "r:/page/2.jsp";
		// redirect到/page/2.jsp
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String hello3(HkRequest req, HkResponse resp) throws Exception {
		return "r:/test_hello.do?v=1";
		// redirect到${requestContextpath}/test_hello.do?v=1
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String hello4(HkRequest req, HkResponse resp) throws Exception {
		// 假如你使用其他代理服务器(apache,ngnix)进行urlrewrite
		// 定义的url为 http://www.xxx.com/login
		return "rr:/login";
		// redirect到 http://www.xxx.com/login上
	}
}