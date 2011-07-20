package unittest;

import halo.dao.partition.TableCnf;
import halo.dao.query.ObjectSqlInfo;
import halo.dao.query.PartitionTableInfo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import query.TestUserDbPartitionHelper;
import bean.TestUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/query-test.xml" })
public class DbPartitionHelperTest {

	/**
	 * 测试描述，检测分表分库的准确性,testuser:testuser0,1;db:test0,1<br/>
	 * 按照userid进行分区
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testDbParitionHelper() throws ClassNotFoundException {
		TestUserDbPartitionHelper dbPartitionHelper = new TestUserDbPartitionHelper();
		TestUser testUser = new TestUser();
		testUser.setUserid(2);
		TableCnf tableCnf = new TableCnf();
		tableCnf.setClassName(TestUser.class.getName());
		tableCnf.setDbPartitionHelper(dbPartitionHelper);
		ObjectSqlInfo<TestUser> objectSqlInfo = new ObjectSqlInfo<TestUser>(
				tableCnf);
		Map<String, Object> ctxMap = new HashMap<String, Object>();
		ctxMap.put("userid", testUser.getUserid());
		PartitionTableInfo partitionTableInfo = dbPartitionHelper.parse(
				objectSqlInfo.getTableName(), ctxMap);
		Assert.assertEquals("testuser0", partitionTableInfo.getTableName());
		Assert.assertEquals("mysql_test0", partitionTableInfo.getDsKey());
		Assert.assertEquals("testuser", objectSqlInfo.getTableName());
	}
}
