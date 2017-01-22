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
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.tms.express.bean.ExpressFreightInfoBean;
import com.lpmas.tms.factory.TmsDBFactory;

public class ExpressFreightInfoDao {
	private static Logger log = LoggerFactory.getLogger(ExpressFreightInfoDao.class);

	public int insertExpressFreightInfo(ExpressFreightInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into express_freight_info ( company_id, country, province, city, price, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getCompanyId());
			ps.setString(2, bean.getCountry());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setDouble(5, bean.getPrice());
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

	public int updateExpressFreightInfo(ExpressFreightInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update express_freight_info set company_id = ?, country = ?, province = ?, city = ?, price = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where freight_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getCompanyId());
			ps.setString(2, bean.getCountry());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setDouble(5, bean.getPrice());
			ps.setInt(6, bean.getStatus());
			ps.setInt(7, bean.getModifyUser());
			ps.setString(8, bean.getMemo());

			ps.setInt(9, bean.getFreightId());

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

	public ExpressFreightInfoBean getExpressFreightInfoByKey(int freightId) {
		ExpressFreightInfoBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from express_freight_info where freight_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, freightId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new ExpressFreightInfoBean();
				bean = BeanKit.resultSet2Bean(rs, ExpressFreightInfoBean.class);
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

	public ExpressFreightInfoBean getExpressFreightInfoByKey(int companyId, String country, String province, String city) {
		ExpressFreightInfoBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from express_freight_info where company_id = ? and country = ? and province = ? and city = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, companyId);
			ps.setString(2, country);
			ps.setString(3, province);
			ps.setString(4, city);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new ExpressFreightInfoBean();
				bean = BeanKit.resultSet2Bean(rs, ExpressFreightInfoBean.class);
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

	public PageResultBean<ExpressFreightInfoBean> getExpressFreightInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<ExpressFreightInfoBean> result = new PageResultBean<ExpressFreightInfoBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from express_freight_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String companyId = condMap.get("companyId");
			if (StringKit.isValid(companyId)) {
				condList.add("company_id = ?");
				paramList.add(companyId);
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

			String orderQuery = "order by freight_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, ExpressFreightInfoBean.class,
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
