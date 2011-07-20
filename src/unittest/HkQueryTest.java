package unittest;

import halo.dao.query.HkQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import bean.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/query-test2.xml" })
@Transactional
public class HkQueryTest {

	@Resource
	private HkQuery hkQuery;

	@Test
	public void insert() {
		this.hkQuery.insertBySQL(null,
				"insert into member(userid,nick,groupid) values(?,?,?)",
				new Object[] { 1, "akwei", 99 });
	}

	@Test
	public void update() {
		Member member = new Member();
		member.setUserid(1);
		member.setNick("akei");
		member.setGroupid(88);
		this.hkQuery.insertBySQL(
				null,
				"insert into member(userid,nick,groupid) values(?,?,?)",
				new Object[] { member.getUserid(), member.getNick(),
						member.getGroupid() });
		int result = this.hkQuery.updateBySQL(null,
				"update member set nick=?,groupid=? where userid=?",
				new Object[] { "akweiwei", 99, 1 });
		Assert.assertEquals(1, result);
	}

	@Test
	public void delete() {
		Member member = new Member();
		member.setUserid(1);
		member.setNick("akei");
		member.setGroupid(88);
		this.hkQuery.insertBySQL(
				null,
				"insert into member(userid,nick,groupid) values(?,?,?)",
				new Object[] { member.getUserid(), member.getNick(),
						member.getGroupid() });
		int result = this.hkQuery.updateBySQL(null,
				"delete from member where userid=?", new Object[] { 2 });
		Assert.assertEquals(0, result);
		result = this.hkQuery.updateBySQL(null,
				"delete from member where userid=?",
				new Object[] { member.getUserid() });
		Assert.assertEquals(1, result);
	}

	@Test
	public void selectList() {
		Member member = new Member();
		member.setUserid(1);
		member.setNick("akei");
		member.setGroupid(88);
		this.hkQuery.insertBySQL(
				null,
				"insert into member(userid,nick,groupid) values(?,?,?)",
				new Object[] { member.getUserid(), member.getNick(),
						member.getGroupid() });
		List<Member> list = this.hkQuery.getListBySQL(null,
				"select * from member", null, 0, 9, new RowMapper<Member>() {

					@Override
					public Member mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Member m = new Member();
						m.setUserid(rs.getLong("userid"));
						m.setNick(rs.getString("nick"));
						m.setGroupid(rs.getLong("groupid"));
						return m;
					}
				});
		Assert.assertEquals(1, list.size());
	}

	@Test
	public void getObject() {
		Member member = new Member();
		member.setUserid(1);
		member.setNick("akei");
		member.setGroupid(88);
		this.hkQuery.insertBySQL(
				null,
				"insert into member(userid,nick,groupid) values(?,?,?)",
				new Object[] { member.getUserid(), member.getNick(),
						member.getGroupid() });
		Member m2 = this.hkQuery.getObjectBySQL(null,
				"select * from member where userid=?",
				new Object[] { member.getUserid() }, new RowMapper<Member>() {

					@Override
					public Member mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Member m = new Member();
						m.setUserid(rs.getLong("userid"));
						m.setNick(rs.getString("nick"));
						m.setGroupid(rs.getLong("groupid"));
						return m;
					}
				});
		Assert.assertEquals(member.getUserid(), m2.getUserid());
		Assert.assertEquals(member.getGroupid(), m2.getGroupid());
		Assert.assertEquals(member.getNick(), m2.getNick());
	}

	@Test
	public void count() {
		Member member = new Member();
		member.setUserid(1);
		member.setNick("akei");
		member.setGroupid(88);
		this.hkQuery.insertBySQL(
				null,
				"insert into member(userid,nick,groupid) values(?,?,?)",
				new Object[] { member.getUserid(), member.getNick(),
						member.getGroupid() });
		int count = this.hkQuery.countBySQL(null,
				"select count(*) from member where userid=?",
				new Object[] { 2 });
		Assert.assertEquals(0, count);
		count = this.hkQuery.countBySQL(null,
				"select count(*) from member where userid=?",
				new Object[] { member.getUserid() });
		Assert.assertEquals(1, count);
	}
}