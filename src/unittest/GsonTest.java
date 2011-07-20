package unittest;

import halo.util.JsonUtil;
import halo.util.P;

import java.util.Map;
import java.util.Map.Entry;

public class GsonTest {

	public static void main(String[] args) {
		// {"uid":"2","email":"","mobile":"","truename":"\u6c88\u6615\u5b87-cn","nickname":"\u6c88\u6615\u5b87-cn","gender":"m","image":"http:\/\/tp3.sinaimg.cn\/1400714330\/50\/0\/1","check_status":"0","status":"0"}
		String json = "{\"uid\":\"2\",\"email\":\"\",\"mobile\":\"\",\"truename\":\"\u6c88\u6615\u5b87-cn\",\"nickname\":\"\u6c88\u6615\u5b87-cn\",\"gender\":\"m\",\"image\":\"http:\\/\\/tp3.sinaimg.cn\\/1400714330\\/50\\/0\\/1\",\"check_status\":\"0\",\"status\":\"0\"}";
		byte[] by = json.getBytes();
		Map<String, String> map = JsonUtil.getMapFromJson(json);
		for (Entry<String, String> e : map.entrySet()) {
			P.println(e.getValue());
		}
		json = JsonUtil.toJson(map);
		byte[] by2 = json.getBytes();
		P.println(json);
		P.println(by.length + " , " + by2.length);
	}
}
