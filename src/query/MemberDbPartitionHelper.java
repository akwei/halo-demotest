package query;

import halo.dao.partition.DbPartitionHelper;
import halo.dao.query.PartitionTableInfo;

import java.util.Map;

public class MemberDbPartitionHelper extends DbPartitionHelper {

	@Override
	public PartitionTableInfo parse(String name, Map<String, Object> ctxMap) {
		long userid = (Long) ctxMap.get("memberuserid");
		String lastChar = this.get01(userid);
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		partitionTableInfo.setAliasName(name);
		partitionTableInfo.setTableName("member" + lastChar);
		partitionTableInfo.setDsKey("mysql_test" + lastChar);
		return partitionTableInfo;
	}
}
