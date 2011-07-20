package query;

import halo.dao.partition.DbPartitionHelper;
import halo.dao.query.PartitionTableInfo;

import java.util.Map;

public class TestUserDbPartitionHelper extends DbPartitionHelper {

	@Override
	public PartitionTableInfo parse(String tableLogicName,
			Map<String, Object> ctxMap) {
		// 取出在程序中传递的分表分库关键字
		long userid = (Long) ctxMap.get("userid");
		// 对关键字进行分析，最终要获得真实操作的数据源key,表名称
		String lastChar = this.get01(userid);
		PartitionTableInfo partitionTableInfo = new PartitionTableInfo();
		// 设置表的逻辑表名称，也是表的别名
		partitionTableInfo.setAliasName(tableLogicName);
		// 设置通过分析后获得的真实表名称
		partitionTableInfo.setTableName("testuser" + lastChar);
		// 设置通过分析后获得的真实数据源key(此key在配置数据源时指定)
		partitionTableInfo.setDsKey("mysql_test" + lastChar);
		return partitionTableInfo;
	}
}