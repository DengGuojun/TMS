package com.lpmas.tms.waybill.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.tms.factory.TmsDBFactory;
import com.lpmas.tms.waybill.bean.WaybillInfoBean;

public class WaybillInfoDao {
	private static Logger log = LoggerFactory.getLogger(WaybillInfoDao.class);

	public int insertWaybillInfo(WaybillInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into waybill_info ( waybill_number, logistics_company_id, route_id, info_type, info_id, consigner_name, consigner_country, consigner_province, consigner_city, consigner_region, consigner_address, consigner_telephone, consigner_mobile, receiver_type, receiver_name, receiver_country, receiver_province, receiver_city, receiver_region, receiver_address, receiver_telephone, receiver_mobile, total_quantity, total_weight, freight, pickup_charge, delivery_charge, warehouse_entry_charge, package_charge, insurance_charge, other_charge, total_amount, approval_status, waybill_status, sync_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getWaybillNumber());
			ps.setInt(2, bean.getLogisticsCompanyId());
			ps.setInt(3, bean.getRouteId());
			ps.setInt(4, bean.getInfoType());
			ps.setInt(5, bean.getInfoId());
			ps.setString(6, bean.getConsignerName());
			ps.setString(7, bean.getConsignerCountry());
			ps.setString(8, bean.getConsignerProvince());
			ps.setString(9, bean.getConsignerCity());
			ps.setString(10, bean.getConsignerRegion());
			ps.setString(11, bean.getConsignerAddress());
			ps.setString(12, bean.getConsignerTelephone());
			ps.setString(13, bean.getConsignerMobile());
			ps.setInt(14, bean.getReceiverType());
			ps.setString(15, bean.getReceiverName());
			ps.setString(16, bean.getReceiverCountry());
			ps.setString(17, bean.getReceiverProvince());
			ps.setString(18, bean.getReceiverCity());
			ps.setString(19, bean.getReceiverRegion());
			ps.setString(20, bean.getReceiverAddress());
			ps.setString(21, bean.getReceiverTelephone());
			ps.setString(22, bean.getReceiverMobile());
			ps.setInt(23, bean.getTotalQuantity());
			ps.setDouble(24, bean.getTotalWeight());
			ps.setDouble(25, bean.getFreight());
			ps.setDouble(26, bean.getPickupCharge());
			ps.setDouble(27, bean.getDeliveryCharge());
			ps.setDouble(28, bean.getWarehouseEntryCharge());
			ps.setDouble(29, bean.getPackageCharge());
			ps.setDouble(30, bean.getInsuranceCharge());
			ps.setDouble(31, bean.getOtherCharge());
			ps.setDouble(32, bean.getTotalAmount());
			ps.setString(33, bean.getApprovalStatus());
			ps.setString(34, bean.getWaybillStatus());
			ps.setString(35, bean.getSyncStatus());
			ps.setInt(36, bean.getStatus());
			ps.setInt(37, bean.getCreateUser());
			ps.setString(38, bean.getMemo());

			result = db.executePstmtInsert();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public int updateWaybillInfo(WaybillInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update waybill_info set waybill_number = ?, logistics_company_id = ?, route_id = ?, info_type = ?, info_id = ?, consigner_name = ?, consigner_country = ?, consigner_province = ?, consigner_city = ?, consigner_region = ?, consigner_address = ?, consigner_telephone = ?, consigner_mobile = ?, receiver_type = ?, receiver_name = ?, receiver_country = ?, receiver_province = ?, receiver_city = ?, receiver_region = ?, receiver_address = ?, receiver_telephone = ?, receiver_mobile = ?, total_quantity = ?, total_weight = ?, freight = ?, pickup_charge = ?, delivery_charge = ?, warehouse_entry_charge = ?, package_charge = ?, insurance_charge = ?, other_charge = ?, total_amount = ?, approval_status = ?, waybill_status = ?, sync_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where waybill_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getWaybillNumber());
			ps.setInt(2, bean.getLogisticsCompanyId());
			ps.setInt(3, bean.getRouteId());
			ps.setInt(4, bean.getInfoType());
			ps.setInt(5, bean.getInfoId());
			ps.setString(6, bean.getConsignerName());
			ps.setString(7, bean.getConsignerCountry());
			ps.setString(8, bean.getConsignerProvince());
			ps.setString(9, bean.getConsignerCity());
			ps.setString(10, bean.getConsignerRegion());
			ps.setString(11, bean.getConsignerAddress());
			ps.setString(12, bean.getConsignerTelephone());
			ps.setString(13, bean.getConsignerMobile());
			ps.setInt(14, bean.getReceiverType());
			ps.setString(15, bean.getReceiverName());
			ps.setString(16, bean.getReceiverCountry());
			ps.setString(17, bean.getReceiverProvince());
			ps.setString(18, bean.getReceiverCity());
			ps.setString(19, bean.getReceiverRegion());
			ps.setString(20, bean.getReceiverAddress());
			ps.setString(21, bean.getReceiverTelephone());
			ps.setString(22, bean.getReceiverMobile());
			ps.setInt(23, bean.getTotalQuantity());
			ps.setDouble(24, bean.getTotalWeight());
			ps.setDouble(25, bean.getFreight());
			ps.setDouble(26, bean.getPickupCharge());
			ps.setDouble(27, bean.getDeliveryCharge());
			ps.setDouble(28, bean.getWarehouseEntryCharge());
			ps.setDouble(29, bean.getPackageCharge());
			ps.setDouble(30, bean.getInsuranceCharge());
			ps.setDouble(31, bean.getOtherCharge());
			ps.setDouble(32, bean.getTotalAmount());
			ps.setString(33, bean.getApprovalStatus());
			ps.setString(34, bean.getWaybillStatus());
			ps.setString(35, bean.getSyncStatus());
			ps.setInt(36, bean.getStatus());
			ps.setInt(37, bean.getModifyUser());
			ps.setString(38, bean.getMemo());

			ps.setInt(39, bean.getWaybillId());

			result = db.executePstmtUpdate();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public WaybillInfoBean getWaybillInfoByKey(int waybillId) {
		WaybillInfoBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from waybill_info where waybill_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, waybillId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new WaybillInfoBean();
				bean = BeanKit.resultSet2Bean(rs, WaybillInfoBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			bean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}

	public PageResultBean<WaybillInfoBean> getWaybillInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<WaybillInfoBean> result = new PageResultBean<WaybillInfoBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from waybill_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String infoType = condMap.get("infoType");
			if (StringKit.isValid(infoType)) {
				condList.add("info_type = ?");
				paramList.add(infoType);
			}
			String infoId = condMap.get("infoId");
			if (StringKit.isValid(infoId)) {
				condList.add("info_id = ?");
				paramList.add(infoId);
			}
			String approvalStatus = condMap.get("approvalStatus");
			if (StringKit.isValid(approvalStatus)) {
				condList.add("approval_status = ?");
				paramList.add(approvalStatus);
			}
			String waybillStatus = condMap.get("waybillStatus");
			if (StringKit.isValid(waybillStatus)) {
				condList.add("waybill_status = ?");
				paramList.add(waybillStatus);
			}
			String createUser = condMap.get("createUser");
			if (StringKit.isValid(createUser)) {
				condList.add("create_user = ?");
				paramList.add(createUser);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String orderQuery = "order by waybill_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, WaybillInfoBean.class, pageBean, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public List<Integer> getWaybillInfoCreaterUserList(int status) {
		List<Integer> result = new ArrayList<Integer>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "select create_user from waybill_info where status = ? group by create_user";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, status);
			ResultSet rs = db.executePstmtQuery();
			while (rs.next()) {
				result.add(rs.getInt("create_user"));
			}

		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public int getWaybillInfoCountForToday() {
		int result = 0;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select count(1) from waybill_info where create_time >= curdate()";
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

}
