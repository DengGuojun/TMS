package com.lpmas.tms.route.dao;

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
import com.lpmas.tms.route.bean.RouteOfferBean;

public class RouteOfferDao {
	private static Logger log = LoggerFactory.getLogger(RouteOfferDao.class);

	public int insertRouteOffer(RouteOfferBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into route_offer ( route_id, company_id, duration, currency, transport_price, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getRouteId());
			ps.setInt(2, bean.getCompanyId());
			ps.setDouble(3, bean.getDuration());
			ps.setString(4, bean.getCurrency());
			ps.setDouble(5, bean.getTransportPrice());
			ps.setInt(6, bean.getStatus());
			ps.setInt(7, bean.getCreateUser());
			ps.setString(8, bean.getMemo());

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

	public int updateRouteOffer(RouteOfferBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update route_offer set duration = ?, currency = ?, transport_price = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where route_id = ? and company_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setDouble(1, bean.getDuration());
			ps.setString(2, bean.getCurrency());
			ps.setDouble(3, bean.getTransportPrice());
			ps.setInt(4, bean.getStatus());
			ps.setInt(5, bean.getModifyUser());
			ps.setString(6, bean.getMemo());

			ps.setInt(7, bean.getRouteId());
			ps.setInt(8, bean.getCompanyId());

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

	public RouteOfferBean getRouteOfferByKey(int routeId, int companyId) {
		RouteOfferBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from route_offer where route_id = ? and company_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, routeId);
			ps.setInt(2, companyId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new RouteOfferBean();
				bean = BeanKit.resultSet2Bean(rs, RouteOfferBean.class);
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

	public PageResultBean<RouteOfferBean> getRouteOfferPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<RouteOfferBean> result = new PageResultBean<RouteOfferBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from route_offer";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String routeId = condMap.get("routeId");
			if (StringKit.isValid(routeId)) {
				condList.add("route_id = ?");
				paramList.add(routeId);
			}
			String companyId = condMap.get("companyId");
			if (StringKit.isValid(companyId)) {
				condList.add("company_id = ?");
				paramList.add(companyId);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String orderQuery = "order by route_id,company_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, RouteOfferBean.class, pageBean, db);
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

	public List<RouteOfferBean> getRouteOfferListByMap(HashMap<String, String> condMap) {
		List<RouteOfferBean> result = new ArrayList<RouteOfferBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from route_offer";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String routeId = condMap.get("routeId");
			if (StringKit.isValid(routeId)) {
				condList.add("route_id = ?");
				paramList.add(routeId);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String companyId = condMap.get("companyId");
			if (StringKit.isValid(companyId)) {
				condList.add("company_id = ?");
				paramList.add(companyId);
			}
			String orderQuery = "order by route_id,company_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, RouteOfferBean.class, db);
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
