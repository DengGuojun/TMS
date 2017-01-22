package com.lpmas.tms.waybill.busniess;

import com.lpmas.framework.util.DateKit;
import com.lpmas.tms.waybill.config.WaybillConfig;
import com.lpmas.tms.waybill.dao.WaybillRedisDao;

public class WaybillInfoNumberGenerator {
	// 在调用生成编码前，线程必须先获得同步锁,否则在大并发下会出现承运单编码重复
	public static String generateWaybillInfoNumber() {
		String date = DateKit.getCurrentDateTime("yyyyMMdd");
		String toNumber = WaybillConfig.TO_NUMBER_PREFIX + date;
		WaybillRedisDao dao = new WaybillRedisDao();
		long serialNumber = dao.getWaybillCountByDate(date);
		toNumber = toNumber + fillPreZero(serialNumber, 5);// 当天序号不足5位的在前面补0
		return toNumber;
	}

	private static String fillPreZero(long number, int scale) {
		String result = "";
		try {
			String temp = String.valueOf(number);
			int iLen = scale - temp.length();
			if (iLen >= 0) {
				for (int i = 0; i < iLen; i++) {
					temp = "0" + temp;
				}
			}
			result = temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
