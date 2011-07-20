package unittest;

import halo.dao.partition.TableCnf;
import halo.dao.query.ObjectSqlInfo;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import query.TestUserDbPartitionHelper;
import bean.TestUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/query-test.xml" })
public class TestSqlUpdateMapper {

	@Test
	public void testCreateObjectInfo() throws ClassNotFoundException {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		TestUser testUser = new TestUser();
		testUser.setUserid(1);
		testUser.setNick("akwei");
		testUser.setGender((byte) 2);
		testUser.setMoney(25);
		testUser.setPurchase(30f);
		testUser.setCreatetime(date);
		TestUserDbPartitionHelper dbPartitionHelper = new TestUserDbPartitionHelper();
		dbPartitionHelper.setBaseDatasourceKey("mysql_test");
		TableCnf tableCnf = new TableCnf();
		tableCnf.setClassName(TestUser.class.getName());
		tableCnf.setDbPartitionHelper(dbPartitionHelper);
		ObjectSqlInfo<TestUser> objectSqlInfo = new ObjectSqlInfo<TestUser>(
				tableCnf);
		int want_fieldListSize = 5;
		int want_allFieldListSize = 6;
		Assert.assertEquals(want_fieldListSize, objectSqlInfo
				.getFieldIgnoreIdList().size());
		Assert.assertEquals(want_allFieldListSize, objectSqlInfo
				.getAllfieldList().size());
		// id assert
		Assert.assertEquals(new Long(testUser.getUserid()), objectSqlInfo
				.getSqlUpdateMapper().getIdParam(testUser));
		// objects for insert
		Object[] objs = objectSqlInfo.getSqlUpdateMapper().getParamsForInsert(
				testUser);
		for (Object o : objs) {
			System.out.print(o.toString() + " | ");
		}
		System.out.println("\n");
		// objects for update
		objs = objectSqlInfo.getSqlUpdateMapper().getParamsForUpdate(testUser);
		for (Object o : objs) {
			System.out.print(o.toString() + " | ");
		}
	}
}
