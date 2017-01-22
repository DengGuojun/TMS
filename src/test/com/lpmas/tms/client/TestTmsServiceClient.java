package test.com.lpmas.tms.client;

import org.junit.Test;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.tms.client.TmsServiceClient;

public class TestTmsServiceClient {
	private static int storeId = 2;
	private static int companyId = 1;
	private static String country = "中国";
	private static String province = "广东";
	private static String city = "广州";

	@Test
	public void testGetDefaultCompanyInfoByCity() {
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("city");
		System.out.println(JsonKit.toJson(client.getDefaultCompanyInfoByCity(storeId, country, province, city)));
	}

	@Test
	public void testGetDefaultCompanyInfoByProvince() {
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("province");
		System.out.println(JsonKit.toJson(client.getDefaultCompanyInfoByProvince(storeId, country, province)));
	}

	@Test
	public void testGetDefaultCompanyInfoByCountry() {
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("country");
		System.out.println(JsonKit.toJson(client.getDefaultCompanyInfoByCountry(storeId, country)));
	}

	@Test
	public void testGetDefaultCompanyInfoByStoreId() {
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("store");
		System.out.println(JsonKit.toJson(client.getDefaultCompanyInfoByStoreId(storeId)));
	}

	@Test
	public void testGetExpressFreightByCity() {
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("city");
		System.out.println(JsonKit.toJson(client.getExpressFreightByCity(companyId, country, province, city)));
	}

	@Test
	public void testGetExpressFreightByProvince() {
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("province");
		System.out.println(JsonKit.toJson(client.getExpressFreightByProvince(companyId, country, province)));
	}

	@Test
	public void testGetExpressFreightByCountry() {
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("country");
		System.out.println(JsonKit.toJson(client.getExpressFreightByCountry(companyId, country)));
	}

	@Test
	public void testGetExpressStoreDefaultByKey() {
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("store default");
		System.out.println(JsonKit.toJson(client.getExpressStoreDefaultByKey(storeId)));
	}
	
	@Test
	public void testGetExpressCompanyInfoAllList(){
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("express company list");
		System.out.println(JsonKit.toJson(client.getExpressCompanyInfoAllList()));
	}
	
	@Test
	public void testGetLogisticsCompanyInfoAllList(){
		TmsServiceClient client = new TmsServiceClient();
		System.out.println("logistics company list");
		System.out.println(JsonKit.toJson(client.getLogisticsCompanyInfoAllList()));
	}
}
