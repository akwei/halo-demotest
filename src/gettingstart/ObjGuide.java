package gettingstart;

import halo.dao.query.CountParam;
import halo.dao.query.DeleteParam;
import halo.dao.query.HkObjQuery;
import halo.dao.query.QueryParam;
import halo.dao.query.UpdateParam;
import halo.util.DateUtil;
import halo.util.HaloUtil;
import halo.util.P;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import bean.Member;
import bean.TestUser;
import bean.UserVo;

/**
 * 配置spring文件，看guide.xml<br/>
 * 以对象的方式来处理sql，此类中的所有例子对于类中的属性值会有所限制<br/>
 * 只允许short,byte,int,long,float,double,java .lang.String,java.util.Date这些数据类型<br/>
 * 查询不支持group by have in not in等复杂操作，不支持数据库函数。如果需要，请自行扩展
 * 
 * @author akwei
 */
public class ObjGuide {

	/**
	 * 创建对象
	 */
	public void insert() {
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		// 创建数据对象
		TestUser testUser = new TestUser();
		testUser.setUserid(1);
		testUser.setNick("akweiwei");
		testUser.setCreatetime(DateUtil.createNoMillisecondTime(new Date()));
		testUser.setGender((byte) 1);
		testUser.setMoney(29.9);
		testUser.setPurchase(21.1f);
		// 创建操作
		@SuppressWarnings("unused")
		Object obj = hkObjQuery.insertObj("userid", testUser.getUserid(),
				testUser);
		// 如果设置mysql数据库表id自增，返回值obj代表自增id的值
	}

	/**
	 * 修改对象
	 */
	public void updateObj() {
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		// 创建数据对象
		TestUser testUser = new TestUser();
		testUser.setUserid(1);
		testUser.setNick("akweiwei");
		testUser.setCreatetime(DateUtil.createNoMillisecondTime(new Date()));
		testUser.setGender((byte) 1);
		testUser.setMoney(29.9);
		testUser.setPurchase(21.1f);
		// update，要更新对象，对象必须有id
		hkObjQuery.updateObj("userid", testUser.getUserid(), testUser);
	}

	/**
	 * 修改
	 */
	public void update() {
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		UpdateParam updateParam = new UpdateParam("userid", 1);
		// 设置需要修改的列
		updateParam.setUpdateColumns(new String[] { "gender", "createtime",
				"nick" });
		// 设置sql where
		updateParam.setWhere("nick=?");
		// 设置修改列与where条件的对应的参数
		updateParam
				.setParams(new Object[] { 1, new Date(), "akweiwei", "aaa" });
		// update
		hkObjQuery.update(updateParam, TestUser.class);
	}

	/**
	 * 根据id查询单对象
	 */
	public void selectObj() {
		long userid = 5;
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		QueryParam queryParam = new QueryParam("userid", userid);
		// select
		TestUser testUser = hkObjQuery.getObjectById(queryParam,
				TestUser.class, userid);
		P.println(testUser);
	}

	/**
	 * 单表查询
	 */
	public void selectList() {
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		QueryParam queryParam = new QueryParam("userid", new Long(4));
		// 添加需要查询的表,如果查询的表与返回值类型相同，可不设置此参数
		queryParam.addClass(TestUser.class);
		// 设置查询范围如果size为<0时，取所有数据
		queryParam.setRange(0, 10);
		queryParam.setWhereAndParams("testuser.nick=?",
				new Object[] { "akwei" });
		queryParam.setOrder("gender desc");
		// select
		List<TestUser> list = hkObjQuery.getList(queryParam, TestUser.class);
		// hkObjQuery.getList(queryParam, mapper);//为自定义RowMapper方式
		for (TestUser o : list) {
			P.println(o);
		}
	}

	/**
	 * 多表查询，返回所有列
	 */
	public void selectList2() {
		// 虽然支持多表查询，但是仍然不建议使用
		final HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil
				.getBean("hkObjQuery");
		QueryParam queryParam = new QueryParam("userid", new Long(4));
		// 添加需要查询的表，此添加顺序会影响返回列的顺序
		queryParam.addClass(TestUser.class);
		queryParam.addClass(Member.class);
		// 设置分区参数以及值
		queryParam.addKeyAndValue("memberuserid", new Long(4));
		// 设置查询范围
		queryParam.setRange(0, -1);
		// 设置查询条件，尽量使用别名区分表
		// 设置查询参数
		queryParam.setWhereAndParams(
				"testuser.userid=member.memberuserid and testuser.userid=?",
				new Object[] { 4 });
		// 设置排序
		queryParam.setOrder("testuser.nick asc");
		// 返回值的rowmapper
		RowMapper<Member> mapper = new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet arg0, int arg1) throws SQLException {
				// 返回所有列时，可以使用表对应的类
				TestUser testUser = hkObjQuery.getRowMapper(TestUser.class)
						.mapRow(arg0, arg1);
				Member member = hkObjQuery.getRowMapper(Member.class).mapRow(
						arg0, arg1);
				member.setTestUser(testUser);
				return member;
			}
		};
		// select
		List<Member> list = hkObjQuery.getList(queryParam, mapper);
		for (Member o : list) {
			P.println(o);
		}
	}

	/**
	 * 多表查询返回选定列
	 */
	public void selectList2WithColumns() {
		final HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil
				.getBean("hkObjQuery");
		QueryParam queryParam = new QueryParam("userid", new Long(4));
		// 添加需要查询的表，此添加顺序会影响返回列的顺序
		queryParam.addClass(TestUser.class);
		queryParam.addClass(Member.class);
		// 设置分区参数以及值
		queryParam.addKeyAndValue("memberuserid", new Long(4));
		// 设置需要返回的列，每组列必须与addClass的类顺序一致
		queryParam.setColumns(new String[][] { { "usetid", "nick", "gender" },
				{ "memberid", "membername" } });
		queryParam.setRange(0, -1);
		queryParam.setWhereAndParams(
				"testuser.userid=member.memberuserid and testuser.userid=?",
				new Object[] { 4 });
		queryParam.setOrder("testuser.nick asc");
		// select
		List<UserVo> list = hkObjQuery.getList(queryParam, UserVo.class);
		for (UserVo o : list) {
			P.println(o);
		}
	}

	/**
	 * 单表统计
	 */
	public void count() {
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		// count
		// 设置分区参数以及值
		CountParam countParam = new CountParam("userid", new Long(2));
		// 设置要统计的类
		countParam.addClass(TestUser.class);
		// 设置查询条件与参数
		countParam.setWhereAndParams("nick=?", new Object[] { "akweiwei" });
		hkObjQuery.count(countParam);
	}

	/**
	 * 多表统计
	 */
	public void count2() {
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		CountParam countParam = new CountParam();
		// 设置要统计的类
		countParam.addClass(TestUser.class);
		countParam.addClass(Member.class);
		// 设置分区参数以及值
		countParam.addKeyAndValue("userid", new Long(4));
		countParam.addKeyAndValue("memberuserid", new Long(4));
		// 设置查询条件与参数
		countParam.setWhereAndParams(
				"testuser.userid=member.memberuserid and testuser.userid=?",
				new Object[] { 4 });
		// count
		hkObjQuery.count(countParam);
	}

	/**
	 * 删除
	 */
	public void delete() {
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		// 设置分区参数以及值
		DeleteParam deleteParam = new DeleteParam("userid", new Long(10));
		// 设置查询条件与参数
		deleteParam.setWhereAndParams("nick=?", new Object[] { "akwei" });
		// delete，设置要删除的对象类型
		hkObjQuery.delete(deleteParam, TestUser.class);
	}

	/**
	 * 根据id删除对象
	 */
	public void deleteObjById() {
		HkObjQuery hkObjQuery = (HkObjQuery) HaloUtil.getBean("hkObjQuery");
		long id = 100;
		hkObjQuery.deleteById("userid", new Long(100), TestUser.class, id);
	}
}
