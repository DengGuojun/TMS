package com.lpmas.tms.express.dao;

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
import com.lpmas.framework.db.SqlKit;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.tms.express.bean.ExpressRegionDefaultBean;
import com.lpmas.tms.factory.TmsDBFactory;

public class ExpressRegionDefaultDao {
	private static Logger log = LoggerFactory.getLogger(ExpressRegionDefaultDao.class);

	public int insertExpressRegionDefault(ExpressRegionDefaultBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into express_region_default ( store_id, country, province, city, company_id, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getStoreId());
			ps.setString(2, bean.getCountry());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setInt(5, bean.getCompanyId());
			ps.setInt(6, bean.getStatus());
			ps.setInt(7, bean.getCreateUser());
			ps.setString(8, bean.getMemo());

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

	public int updateExpressRegionDefault(ExpressRegionDefaultBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update express_region_default set store_id = ?, country = ?, province = ?, city = ?, company_id = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where express_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getStoreId());
			ps.setString(2, bean.getCountry());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setInt(5, bean.getCompanyId());
			ps.setInt(6, bean.getStatus());
			ps.setInt(7, bean.getModifyUser());
			ps.setString(8, bean.getMemo());

			ps.setInt(9, bean.getExpressId());

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

	public ExpressRegionDefaultBean getExpressRegionDefaultByKey(int expressId) {
		ExpressRegionDefaultBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from express_region_default where express_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, expressId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new ExpressRegionDefaultBean();
				bean = BeanKit.resultSet2Bean(rs, ExpressRegionDefaultBean.class);
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

	public ExpressRegionDefaultBean getExpressRegionDefaultByKey(int storeId, String country, String province,
			String city) {
		ExpressRegionDefaultBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from express_region_default where store_id = ? and country = ? and province = ? and city = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, storeId);
			ps.setString(2, country);
			ps.setString(3, province);
			ps.setString(4, city);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new ExpressRegionDefaultBean();
				bean = BeanKit.resultSet2Bean(rs, ExpressRegionDefaultBean.class);
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

	public PageResultBean<ExpressRegionDefaultBean> getExpressRegionDefaultPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<ExpressRegionDefaultBean> result = new PageResultBean<ExpressRegionDefaultBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from express_region_default";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();

			// 条件处理
			String storeId = condMap.get("storeId");
			if (StringKit.isValid(storeId)) {
				condList.add("store_id = ?");
				paramList.add(storeId);
			}

			String storeIds = condMap.get("storeIds");
			if (StringKit.isValid(storeIds)) {
				String[] storeIdArray = storeIds.split(",");
				if (storeIdArray.length > 0) {
					condList.add("store_id in (" + SqlKit.getInQueryPreparedStmt(storeIdArray.length) + ")");
					for (String storeStr : storeIdArray) {
						paramList.add(storeStr);
					}
				}
			}
			String country = condMap.get("country");
			if (StringKit.isValid(country)) {
				condList.add("country like ?");
				paramList.add("%" + country + "%");
			}
			String province = condMap.get("province");
			if (StringKit.isValid(province)) {
				condList.add("province like ?");
				paramList.add("%" + province + "%");
			}
			String city = condMap.get("city");
			if (StringKit.isValid(city)) {
				condList.add("city like ?");
				paramList.add("%" + city + "%");
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by express_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, ExpressRegionDefaultBean.class,
					pageBean, db);
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