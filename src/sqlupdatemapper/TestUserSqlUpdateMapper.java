package sqlupdatemapper;

import halo.dao.query.ParamListUtil;
import halo.dao.query.SqlUpdateMapper;
import bean.TestUser;

public class TestUserSqlUpdateMapper implements SqlUpdateMapper<TestUser> {

	@Override
	public Object getIdParam(TestUser t) {
		return ParamListUtil.toObject(t.getUserid());
	}

	@Override
	public Object[] getParamsForInsert(TestUser t) {
		ParamListUtil paramListUtil = new ParamListUtil();
		paramListUtil.addLong(t.getUserid());
		paramListUtil.addByte(t.getGender());
		paramListUtil.addString(t.getNick());
		paramListUtil.addDate(t.getCreatetime());
		paramListUtil.addDouble(t.getMoney());
		paramListUtil.addFloat(t.getPurchase());
		return paramListUtil.toObjects();
	}

	@Override
	public Object[] getParamsForUpdate(TestUser t) {
		ParamListUtil paramListUtil = new ParamListUtil();
		paramListUtil.addByte(t.getGender());
		paramListUtil.addString(t.getNick());
		paramListUtil.addDate(t.getCreatetime());
		paramListUtil.addDouble(t.getMoney());
		paramListUtil.addFloat(t.getPurchase());
		paramListUtil.addLong(t.getUserid());
		return paramListUtil.toObjects();
	}
}
