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
import com.lpmas.tms.route.bean.RouteInfoBean;

public class RouteInfoDao {
	private static Logger log = LoggerFactory.getLogger(RouteInfoDao.class);

	public int insertRouteInfo(RouteInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into route_info ( route_name, source_info_type, source_info_id, destination_info_type, destination_info_id, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getRouteName());
			ps.setInt(2, bean.getSourceInfoType());
			ps.setInt(3, bean.getSourceInfoId());
			ps.setInt(4, bean.getDestinationInfoType());
			ps.setInt(5, bean.getDestinationInfoId());
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

	public int updateRouteInfo(RouteInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update route_info set route_name = ?, source_info_type = ?, source_info_id = ?, destination_info_type = ?, destination_info_id = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where route_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getRouteName());
			ps.setInt(2, bean.getSourceInfoType());
			ps.setInt(3, bean.getSourceInfoId());
			ps.setInt(4, bean.getDestinationInfoType());
			ps.setInt(5, bean.getDestinationInfoId());
			ps.setInt(6, bean.getStatus());
			ps.setInt(7, bean.getModifyUser());
			ps.setString(8, bean.getMemo());

			ps.setInt(9, bean.getRouteId());

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

	public RouteInfoBean getRouteInfoByKey(int routeId) {
		RouteInfoBean bean = null;
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from route_info where route_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, routeId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new RouteInfoBean();
				bean = BeanKit.resultSet2Bean(rs, RouteInfoBean.class);
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

	public PageResultBean<RouteInfoBean> getRouteInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<RouteInfoBean> result = new PageResultBean<RouteInfoBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from route_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String routeName = condMap.get("routeName");
			if (StringKit.isValid(routeName)) {
				condList.add("route_name like ?");
				paramList.add("%" + routeName + "%");
			}
			String sourceInfoType = condMap.get("sourceInfoType");
			if (StringKit.isValid(sourceInfoType)) {
				condList.add("source_info_type = ?");
				paramList.add(sourceInfoType);
			}
			String destinationInfoType = condMap.get("destinationInfoType");
			if (StringKit.isValid(destinationInfoType)) {
				condList.add("destination_info_type like ?");
				paramList.add(destinationInfoType);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String orderQuery = "order by route_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, RouteInfoBean.class, pageBean, db);
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

	public List<RouteInfoBean> getRouteInfoListByMap(HashMap<String, String> condMap) {
		List<RouteInfoBean> result = new ArrayList<RouteInfoBean>();
		DBFactory dbFactory = new TmsDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from route_info";
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
			String sourceInfoType = condMap.get("sourceInfoType");
			if (StringKit.isValid(sourceInfoType)) {
				condList.add("source_info_type = ?");
				paramList.add(sourceInfoType);
			}
			String sourceInfoId = condMap.get("sourceInfoId");
			if (StringKit.isValid(sourceInfoId)) {
				condList.add("source_info_id = ?");
				paramList.add(sourceInfoId);
			}
			String destinationInfoType = condMap.get("destinationInfoType");
			if (StringKit.isValid(destinationInfoType)) {
				condList.add("destination_info_type = ?");
				paramList.add(destinationInfoType);
			}
			String destinationInfoId = condMap.get("destinationInfoId");
			if (StringKit.isValid(destinationInfoId)) {
				condList.add("destination_info_id = ?");
				paramList.add(destinationInfoId);
			}
			String routeName = condMap.get("routeName");
			if (StringKit.isValid(routeName)) {
				condList.add("route_name like ?");
				paramList.add("%" + routeName + "%");
			}
			String orderQuery = "order by route_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, RouteInfoBean.class, db);
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
