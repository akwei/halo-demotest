package rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.TestUser;

public class TestUserRowMapper implements RowMapper<TestUser> {

	@Override
	public TestUser mapRow(ResultSet rs, int arg1) throws SQLException {
		TestUser o = new TestUser();
		o.setUserid(rs.getLong("testuser.userid"));
		o.setNick(rs.getString("testuser.nick"));
		rs.getObject("nick");
		if (!rs.wasNull()) {
			o.setNick(rs.getString("nick"));
		}
		return o;
	}
}