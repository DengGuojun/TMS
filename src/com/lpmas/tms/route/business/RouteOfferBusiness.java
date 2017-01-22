package com.lpmas.tms.route.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.tms.route.bean.RouteOfferBean;
import com.lpmas.tms.route.dao.RouteOfferDao;

public class RouteOfferBusiness {
	public int addRouteOffer(RouteOfferBean bean) {
		RouteOfferDao dao = new RouteOfferDao();
		int result =  dao.insertRouteOffer(bean);
		if(result>=0){
			new PathInfoBusiness().tiggerMaintianAllPathInfo();
		}
		return result;
	}

	public int updateRouteOffer(RouteOfferBean bean) {
		RouteOfferDao dao = new RouteOfferDao();
		int result = dao.updateRouteOffer(bean);
		if(result>0){
			new PathInfoBusiness().tiggerMaintianAllPathInfo();
		}
		return result;
	}

	public RouteOfferBean getRouteOfferByKey(int routeId, int companyId) {
		RouteOfferDao dao = new RouteOfferDao();
		return dao.getRouteOfferByKey(routeId, companyId);
	}

	public PageResultBean<RouteOfferBean> getRouteOfferPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		RouteOfferDao dao = new RouteOfferDao();
		return dao.getRouteOfferPageListByMap(condMap, pageBean);
	}

	public List<RouteOfferBean> getRouteOfferAllList() {
		RouteOfferDao dao = new RouteOfferDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getRouteOfferListByMap(condMap);
	}

	public List<RouteOfferBean> getRouteOfferListByMap(HashMap<String, String> condMap) {
		RouteOfferDao dao = new RouteOfferDao();
		return dao.getRouteOfferListByMap(condMap);
	}

	public boolean isDuplicateKey(int routeId, int companyId) {
		RouteOfferBean bean = getRouteOfferByKey(routeId, companyId);
		if (bean == null) {
			return false;
		} else {
			if (bean.getStatus() == Constants.STATUS_NOT_VALID) {
				return false;
			}
		}
		return true;
	}
	
}