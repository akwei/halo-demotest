package unittest;

import halo.web.action.HkRequest;
import halo.web.action.HkRequestImpl;
import halo.web.action.HkResponse;
import halo.web.action.HkResponseImpl;
import halo.web.action.MappingUriCreater;
import halo.web.action.NoActionException;
import halo.web.util.WebCnf;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/hkframe-web.xml" })
public class ActionTest {

	@Resource
	private WebCnf webCnf;

	@Test
	public void testInvokeMyHello() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(
				"get", "/myhello");
		MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
		HkRequest hkRequest = new HkRequestImpl(mockHttpServletRequest);
		HkResponse hkResponse = new HkResponseImpl(mockHttpServletResponse);
		try {
			this.webCnf.getActionExe().invoke(
					new MappingUriCreater().findMappingUri(hkRequest),
					hkRequest, hkResponse);
		}
		catch (NoActionException e) {
		}
	}

	@Test
	public void testInvokeExecute() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(
				"get", "/test");
		MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
		HkRequest hkRequest = new HkRequestImpl(mockHttpServletRequest);
		HkResponse hkResponse = new HkResponseImpl(mockHttpServletResponse);
		this.webCnf.getActionExe().invoke(
				new MappingUriCreater().findMappingUri(hkRequest), hkRequest,
				hkResponse);
	}

	@Test
	public void testInvokeHello() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(
				"get", "/test_hello");
		MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
		HkRequest hkRequest = new HkRequestImpl(mockHttpServletRequest);
		HkResponse hkResponse = new HkResponseImpl(mockHttpServletResponse);
		this.webCnf.getActionExe().invoke(
				new MappingUriCreater().findMappingUri(hkRequest), hkRequest,
				hkResponse);
	}

	@Test
	public void testInvokeHello2() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(
				"get", "/test_hello2");
		MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
		HkRequest hkRequest = new HkRequestImpl(mockHttpServletRequest);
		HkResponse hkResponse = new HkResponseImpl(mockHttpServletResponse);
		this.webCnf.getActionExe().invoke(
				new MappingUriCreater().findMappingUri(hkRequest), hkRequest,
				hkResponse);
	}
}