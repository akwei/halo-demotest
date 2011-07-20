package unittest;

import halo.dao.query.PartitionTableInfo;
import halo.dao.query.SqlBuilder;

import org.junit.Assert;
import org.junit.Test;

public class SqlBuilderTest {

	@Test
	public void testCreateSQL() {
		String create_sql = "insert into user(userid,nick,gender) values(?,?,?)";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		String res_create_sql = SqlBuilder.createInsertSQL(partitionTableInfo,
				new String[] { "userid", "nick", "gender" });
		Assert.assertEquals(create_sql, res_create_sql);
	}

	@Test
	public void testUpdateSQL1() {
		String update_sql = "update user set nick=?,gender=? where userid=?";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		String res_update_sql = SqlBuilder.createUpdateSQL(partitionTableInfo,
				new String[] { "nick", "gender" }, "userid=?");
		Assert.assertEquals(update_sql, res_update_sql);
	}

	@Test
	public void testUpdateSQL2() {
		String update_sql = "update user set nick=?,gender=?";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		String res_update_sql = SqlBuilder.createUpdateSQL(partitionTableInfo,
				new String[] { "nick", "gender" }, null);
		Assert.assertEquals(update_sql, res_update_sql);
	}

	@Test
	public void testDeleteSQL1() {
		String delete_sql = "delete from user where userid=? and nick=?";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_delete_sql = SqlBuilder.createDeleteSQL(partitionTableInfo,
				"userid=? and nick=?");
		Assert.assertEquals(delete_sql, res_delete_sql);
	}

	@Test
	public void testDeleteSQL2() {
		String delete_sql = "delete from user";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_delete_sql = SqlBuilder.createDeleteSQL(partitionTableInfo,
				null);
		Assert.assertEquals(delete_sql, res_delete_sql);
	}

	@Test
	public void testCountSQL1() {
		String count_sql = "select count(*) from user u where u.userid=? and u.nick=? and u.gender=?";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_count_sql = SqlBuilder.createCountSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				"u.userid=? and u.nick=? and u.gender=?");
		Assert.assertEquals(count_sql, res_count_sql);
	}

	@Test
	public void testCountSQL2() {
		String count_sql = "select count(*) from user u,info i where u.userid=? and u.nick=?"
				+ " and u.gender=? and i.userid=? and i.nick=? and u.userid=i.userid";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_count_sql = SqlBuilder
				.createCountSQL(
						new PartitionTableInfo[] { partitionTableInfo1,
								partitionTableInfo2 },
						"u.userid=? and u.nick=?"
								+ " and u.gender=? and i.userid=? and i.nick=? and u.userid=i.userid");
		Assert.assertEquals(count_sql, res_count_sql);
	}

	@Test
	public void testCountSQL3() {
		String count_sql = "select count(*) from user u";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_count_sql = SqlBuilder.createCountSQL(
				new PartitionTableInfo[] { partitionTableInfo }, null);
		Assert.assertEquals(count_sql, res_count_sql);
	}

	@Test
	public void testCountSQL4() {
		String count_sql = "select count(*) from user u,info i";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_count_sql = SqlBuilder.createCountSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, null);
		Assert.assertEquals(count_sql, res_count_sql);
	}

	@Test
	public void testSelectSQL1() {
		String select_sql = "select u.userid,u.nick,u.gender from user u where u.userid=? and u.nick=? and u.gender=? "
				+ "order by u.createtime desc,u.nick asc";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				new String[][] { { "userid", "nick", "gender" } },
				"u.userid=? and u.nick=? and u.gender=?",
				"u.createtime desc,u.nick asc");
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testSelectSQL2() {
		String select_sql = "select u.userid,u.nick,u.gender,u.createtime,"
				+ "i.userid,i.birthday,i.fansnum "
				+ "from user u,info i where u.userid=i.userid and u.userid=? and u.nick=? "
				+ "and u.gender=? and i.birthday>? "
				+ "order by u.createtime desc,u.nick asc,i.birthday desc";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, new String[][] {
						{ "userid", "nick", "gender", "createtime" },
						{ "userid", "birthday", "fansnum" } },
				"u.userid=i.userid and u.userid=? and u.nick=? "
						+ "and u.gender=? and i.birthday>?",
				"u.createtime desc,u.nick asc,i.birthday desc");
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testSelectSQL3() {
		String select_sql = "select u.userid,u.nick,u.gender from user u "
				+ "order by u.createtime desc,u.nick asc";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				new String[][] { { "userid", "nick", "gender" } }, null,
				"u.createtime desc,u.nick asc");
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testSelectSQL4() {
		String select_sql = "select u.userid,u.nick,u.gender,u.createtime,"
				+ "i.userid,i.birthday,i.fansnum " + "from user u,info i "
				+ "order by u.createtime desc,u.nick asc,i.birthday desc";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, new String[][] {
						{ "userid", "nick", "gender", "createtime" },
						{ "userid", "birthday", "fansnum" } }, null,
				"u.createtime desc,u.nick asc,i.birthday desc");
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testSelectSQL5() {
		String select_sql = "select u.userid,u.nick,u.gender from user u";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				new String[][] { { "userid", "nick", "gender" } }, null, null);
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testSelectSQL6() {
		String select_sql = "select u.userid,u.nick,u.gender,u.createtime,"
				+ "i.userid,i.birthday,i.fansnum " + "from user u,info i";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, new String[][] {
						{ "userid", "nick", "gender", "createtime" },
						{ "userid", "birthday", "fansnum" } }, null, null);
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testSelectSQL7() {
		String select_sql = "select u.userid,u.nick,u.gender from user u where u.userid=? and u.nick=? and u.gender=?";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				new String[][] { { "userid", "nick", "gender" } },
				"u.userid=? and u.nick=? and u.gender=?", null);
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testSelectSQL8() {
		String select_sql = "select u.userid,u.nick,u.gender,u.createtime,"
				+ "i.userid,i.birthday,i.fansnum "
				+ "from user u,info i where u.userid=i.userid and u.userid=? and u.nick=? "
				+ "and u.gender=? and i.birthday>?";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, new String[][] {
						{ "userid", "nick", "gender", "createtime" },
						{ "userid", "birthday", "fansnum" } },
				"u.userid=i.userid and u.userid=? and u.nick=? "
						+ "and u.gender=? and i.birthday>?", null);
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testObjectSQL1() {
		String select_sql = "select u.userid,u.nick,u.gender from user u where userid=? and nick=? and gender=? order by createtime desc,nick asc";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				new String[][] { { "userid", "nick", "gender" } },
				"userid=? and nick=? and gender=?", "createtime desc,nick asc");
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testObjectSQL2() {
		String select_sql = "select u.userid,u.nick,u.gender,u.createtime,"
				+ "i.userid,i.birthday,i.fansnum "
				+ "from user u,info i where u.userid=i.userid and u.userid=? and u.nick=? "
				+ "and u.gender=? and i.birthday>? "
				+ "order by u.createtime desc,u.nick asc,i.birthday desc";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_select_sql = SqlBuilder.createObjectSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, new String[][] {
						{ "userid", "nick", "gender", "createtime" },
						{ "userid", "birthday", "fansnum" } },
				"u.userid=i.userid and u.userid=? and u.nick=? "
						+ "and u.gender=? and i.birthday>?",
				"u.createtime desc,u.nick asc,i.birthday desc");
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testObjectSQL3() {
		String select_sql = "select u.userid,u.nick,u.gender from user u order by createtime desc,nick asc";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				new String[][] { { "userid", "nick", "gender" } }, null,
				"createtime desc,nick asc");
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testObjectSQL4() {
		String select_sql = "select u.userid,u.nick,u.gender,u.createtime,"
				+ "i.userid,i.birthday,i.fansnum " + "from user u,info i "
				+ "order by u.createtime desc,u.nick asc,i.birthday desc";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_select_sql = SqlBuilder.createObjectSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, new String[][] {
						{ "userid", "nick", "gender", "createtime" },
						{ "userid", "birthday", "fansnum" } }, null,
				"u.createtime desc,u.nick asc,i.birthday desc");
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testObjectSQL5() {
		String select_sql = "select u.userid,u.nick,u.gender from user u";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				new String[][] { { "userid", "nick", "gender" } }, null, null);
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testObjectSQL6() {
		String select_sql = "select u.userid,u.nick,u.gender,u.createtime,"
				+ "i.userid,i.birthday,i.fansnum " + "from user u,info i";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_select_sql = SqlBuilder.createObjectSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, new String[][] {
						{ "userid", "nick", "gender", "createtime" },
						{ "userid", "birthday", "fansnum" } }, null, null);
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testObjectSQL7() {
		String select_sql = "select u.userid,u.nick,u.gender from user u where userid=? and nick=? and gender=?";
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setDsKey("ds");
		partitionTableInfo.setTableName("user");
		partitionTableInfo.setAliasName("u");
		String res_select_sql = SqlBuilder.createListSQL(
				new PartitionTableInfo[] { partitionTableInfo },
				new String[][] { { "userid", "nick", "gender" } },
				"userid=? and nick=? and gender=?", null);
		Assert.assertEquals(select_sql, res_select_sql);
	}

	@Test
	public void testObjectSQL8() {
		String select_sql = "select u.userid,u.nick,u.gender,u.createtime,"
				+ "i.userid,i.birthday,i.fansnum "
				+ "from user u,info i where u.userid=i.userid and u.userid=? and u.nick=? "
				+ "and u.gender=? and i.birthday>?";
		PartitionTableInfo partitionTableInfo1 = new PartitionTableInfo();
		partitionTableInfo1.setDsKey("ds");
		partitionTableInfo1.setTableName("user");
		partitionTableInfo1.setAliasName("u");
		PartitionTableInfo partitionTableInfo2 = new PartitionTableInfo();
		partitionTableInfo2.setDsKey("ds");
		partitionTableInfo2.setTableName("info");
		partitionTableInfo2.setAliasName("i");
		String res_select_sql = SqlBuilder.createObjectSQL(
				new PartitionTableInfo[] { partitionTableInfo1,
						partitionTableInfo2 }, new String[][] {
						{ "userid", "nick", "gender", "createtime" },
						{ "userid", "birthday", "fansnum" } },
				"u.userid=i.userid and u.userid=? and u.nick=? "
						+ "and u.gender=? and i.birthday>?", null);
		Assert.assertEquals(select_sql, res_select_sql);
	}
}
