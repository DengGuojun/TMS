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
import com.lpmas.tms.express.bean.ExpressStoreDefaultBean;
import com.lpmas.tms.factory.TmsDBFactory;

public class ExpressStoreDefaultDao {
	private static Logger log = LoggerFactory.getLogger(ExpressStoreDefaultDao.class);

	public int insertExpressStoreDefault(ExpressStoreDefaultBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into express_store_default ( store_id, company_id, default_price, status, create_time, create_user, memo) value( ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getStoreId());
			ps.setInt(2, bean.getCompanyId());
			ps.setDouble(3, bean.getDefaultPrice());
			ps.setInt(4, bean.getStatus());
			ps.setInt(5, bean.getCreateUser());
			ps.setString(6, bean.getMemo());

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

	public int updateExpressStoreDefault(ExpressStoreDefaultBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update express_store_default set company_id = ?, default_price = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where store_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getCompanyId());
			ps.setDouble(2, bean.getDefaultPrice());
			ps.setInt(3, bean.getStatus());
			ps.setInt(4, bean.getModifyUser());
			ps.setString(5, bean.getMemo());

			ps.setInt(6, bean.getStoreId());

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

	public ExpressStoreDefaultBean getExpressStoreDefaultByKey(int storeId) {
		ExpressStoreDefaultBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from express_store_default where store_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, storeId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new ExpressStoreDefaultBean();
				bean = BeanKit.resultSet2Bean(rs, ExpressStoreDefaultBean.class);
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

	public PageResultBean<ExpressStoreDefaultBean> getExpressStoreDefaultPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<ExpressStoreDefaultBean> result = new PageResultBean<ExpressStoreDefaultBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from express_store_default";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by store_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, ExpressStoreDefaultBean.class,
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