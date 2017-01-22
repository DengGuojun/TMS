package com.lpmas.tms.logistics.dao;

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
import com.lpmas.tms.logistics.bean.LogisticsCompanyInfoBean;

public class LogisticsCompanyInfoDao {
	private static Logger log = LoggerFactory.getLogger(LogisticsCompanyInfoDao.class);

	public int insertLogisticsCompanyInfo(LogisticsCompanyInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into logistics_company_info ( company_name, company_code, status, create_time, create_user, memo) value( ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getCompanyName());
			ps.setString(2, bean.getCompanyCode());
			ps.setInt(3, bean.getStatus());
			ps.setInt(4, bean.getCreateUser());
			ps.setString(5, bean.getMemo());

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

	public int updateLogisticsCompanyInfo(LogisticsCompanyInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update logistics_company_info set company_name = ?, company_code = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where company_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getCompanyName());
			ps.setString(2, bean.getCompanyCode());
			ps.setInt(3, bean.getStatus());
			ps.setInt(4, bean.getModifyUser());
			ps.setString(5, bean.getMemo());

			ps.setInt(6, bean.getCompanyId());

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

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByKey(int companyId) {
		LogisticsCompanyInfoBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from logistics_company_info where company_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, companyId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new LogisticsCompanyInfoBean();
				bean = BeanKit.resultSet2Bean(rs, LogisticsCompanyInfoBean.class);
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

	public LogisticsCompanyInfoBean getLogisticsCompanyInfoByCode(String companyCode) {
		LogisticsCompanyInfoBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from logistics_company_info where company_code = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, companyCode);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new LogisticsCompanyInfoBean();
				bean = BeanKit.resultSet2Bean(rs, LogisticsCompanyInfoBean.class);
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
	
	public List<LogisticsCompanyInfoBean> getLogisticsCompanyInfoAllList() {
		List<LogisticsCompanyInfoBean> list = new ArrayList<LogisticsCompanyInfoBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from logistics_company_info";
			PreparedStatement ps = db.getPreparedStatement(sql);

			ResultSet rs = db.executePstmtQuery();
			while (rs.next()) {
				LogisticsCompanyInfoBean bean = new LogisticsCompanyInfoBean();
				bean = BeanKit.resultSet2Bean(rs, LogisticsCompanyInfoBean.class);

				list.add(bean);
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
		return list;
	}

	public PageResultBean<LogisticsCompanyInfoBean> getLogisticsCompanyInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<LogisticsCompanyInfoBean> result = new PageResultBean<LogisticsCompanyInfoBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from logistics_company_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String companyCode = condMap.get("companyCode");
			if (StringKit.isValid(companyCode)) {
				condList.add("company_code like ?");
				paramList.add("%" + companyCode + "%");
			}
			String companyName = condMap.get("companyName");
			if (StringKit.isValid(companyName)) {
				condList.add("company_name like ?");
				paramList.add("%" + companyName + "%");
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by company_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, LogisticsCompanyInfoBean.class,
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
