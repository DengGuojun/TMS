package com.lpmas.tms.route.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.nosql.mongodb.MongoDB;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.tms.config.TmsMongoConfig;
import com.lpmas.tms.route.bean.PathInfoBean;

public class PathInfoDao {

	private static Logger logger = LoggerFactory.getLogger(PathInfoDao.class);

	public int insertPathInfo(PathInfoBean bean) {
		try {
			return MongoDB.getInstance().insert(TmsMongoConfig.DB_NAME, TmsMongoConfig.COLLECTION_PATH_INFO, bean);
		} catch (Exception e) {
			logger.error("", e);
			return -1;
		}
	}

	public int updatePathInfo(PathInfoBean bean) {
		return (int) MongoDB.getInstance().update(TmsMongoConfig.DB_NAME, TmsMongoConfig.COLLECTION_PATH_INFO, bean);
	}

	public List<PathInfoBean> getPathInfoListByMap(Map<String, Object> condMap,Map<String, Object> orderBy) {
		try {
			return MongoDB.getInstance().getRecordListResult(TmsMongoConfig.DB_NAME, TmsMongoConfig.COLLECTION_PATH_INFO, condMap, PathInfoBean.class, orderBy);
		} catch (Exception e) {
			logger.error("", e);
			return new ArrayList<PathInfoBean>(1);
		}
	}

	public PathInfoBean getPathInfoByKey(String _id) {
		try {
			return MongoDB.getInstance().getRecordById(TmsMongoConfig.DB_NAME, TmsMongoConfig.COLLECTION_PATH_INFO, _id, PathInfoBean.class);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	public int deletePathInfoByMap(Map<String, Object> condMap){
		return (int) MongoDB.getInstance().delete(TmsMongoConfig.DB_NAME, TmsMongoConfig.COLLECTION_PATH_INFO, condMap);
	}
	
	public PageResultBean<PathInfoBean> getPathInfoPageListByMap(Map<String, Object> condMap, Map<String, Object> orderBy,PageBean pageBean) {
		try {
			return MongoDB.getInstance().getPageListResult(TmsMongoConfig.DB_NAME, TmsMongoConfig.COLLECTION_PATH_INFO, condMap, PathInfoBean.class, pageBean, orderBy);
		} catch (Exception e) {
			logger.error("", e);
			PageResultBean<PathInfoBean> resultBean = new PageResultBean<PathInfoBean>();
			resultBean.setRecordList(new ArrayList<PathInfoBean>(1));
			return resultBean;
		}
	}

}
