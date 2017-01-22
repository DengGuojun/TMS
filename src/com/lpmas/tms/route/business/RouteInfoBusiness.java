package com.lpmas.tms.route.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.tms.route.bean.RouteInfoBean;
import com.lpmas.tms.route.bean.RouteInfoEntityBean;
import com.lpmas.tms.route.cache.RouteInfoCache;
import com.lpmas.tms.route.dao.RouteInfoDao;

public class RouteInfoBusiness {
	public int addRouteInfo(RouteInfoBean bean) {
		RouteInfoDao dao = new RouteInfoDao();
		return dao.insertRouteInfo(bean);
	}

	public int updateRouteInfo(RouteInfoBean bean) {
		RouteInfoDao dao = new RouteInfoDao();
		int result = dao.updateRouteInfo(bean);
		if (result > 0) {
			new RouteInfoCache().refreshRouteNameCache(bean.getRouteId());
		}
		return result;
	}

	public RouteInfoBean getRouteInfoByKey(int routeId) {
		RouteInfoDao dao = new RouteInfoDao();
		return dao.getRouteInfoByKey(routeId);
	}

	public PageResultBean<RouteInfoBean> getRouteInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		RouteInfoDao dao = new RouteInfoDao();
		return dao.getRouteInfoPageListByMap(condMap, pageBean);
	}

	public List<RouteInfoBean> getRouteInfoAllList() {
		RouteInfoDao dao = new RouteInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getRouteInfoListByMap(condMap);
	}

	public List<RouteInfoBean> getRouteInfoListByMap(HashMap<String, String> condMap) {
		RouteInfoDao dao = new RouteInfoDao();
		return dao.getRouteInfoListByMap(condMap);
	}

	public RouteInfoBean getRouteInfoByCondition(int sourceInfoType, int sourceInfoId, int destinationInfoType, int destinationInfoId) {
		RouteInfoDao dao = new RouteInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("sourceInfoType", String.valueOf(sourceInfoType));
		condMap.put("sourceInfoId", String.valueOf(sourceInfoId));
		condMap.put("destinationInfoType", String.valueOf(destinationInfoType));
		condMap.put("destinationInfoId", String.valueOf(destinationInfoId));
		List<RouteInfoBean> list = dao.getRouteInfoListByMap(condMap);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public ReturnMessageBean velidateRouteInfoBean(RouteInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (bean.getSourceInfoType() == 0 || bean.getSourceInfoId() == 0) {
			result.setMessage("发货方必须填写");
		} else if (bean.getDestinationInfoType() == 0 || bean.getDestinationInfoId() == 0) {
			result.setMessage("收货方必须填写");
		}
		return result;
	}

	public RouteInfoEntityBean getRouteInfoEntityByRouteInfo(RouteInfoBean routeInfoBean) {
		RouteInfoEntityBean routeInfoEntityBean = new RouteInfoEntityBean();
		StationInfoMediator stationInfoMediator = new StationInfoMediator();
		routeInfoEntityBean.setRouteId(routeInfoBean.getRouteId());
		routeInfoEntityBean.setRouteName(routeInfoBean.getRouteName());
		routeInfoEntityBean
				.setSourceContactName(stationInfoMediator.getContactNameByKey(routeInfoBean.getSourceInfoType(), routeInfoBean.getSourceInfoId()));
		routeInfoEntityBean.setSourceCountry(stationInfoMediator.getCountryByKey(routeInfoBean.getSourceInfoType(), routeInfoBean.getSourceInfoId()));
		routeInfoEntityBean
				.setSourceProvince(stationInfoMediator.getProvinceByKey(routeInfoBean.getSourceInfoType(), routeInfoBean.getSourceInfoId()));
		routeInfoEntityBean.setSourceCity(stationInfoMediator.getCityByKey(routeInfoBean.getSourceInfoType(), routeInfoBean.getSourceInfoId()));
		routeInfoEntityBean.setSourceRegion(stationInfoMediator.getRegionByKey(routeInfoBean.getSourceInfoType(), routeInfoBean.getSourceInfoId()));
		routeInfoEntityBean.setSourceAddress(stationInfoMediator.getAddressByKey(routeInfoBean.getSourceInfoType(), routeInfoBean.getSourceInfoId()));
		routeInfoEntityBean
				.setSourceTelephone(stationInfoMediator.getTelephoneByKey(routeInfoBean.getSourceInfoType(), routeInfoBean.getSourceInfoId()));
		routeInfoEntityBean.setSourceMobile(stationInfoMediator.getMobileByKey(routeInfoBean.getSourceInfoType(), routeInfoBean.getSourceInfoId()));
		routeInfoEntityBean.setDestinationContactName(
				stationInfoMediator.getContactNameByKey(routeInfoBean.getDestinationInfoType(), routeInfoBean.getDestinationInfoId()));
		routeInfoEntityBean.setDestinationCountry(
				stationInfoMediator.getCountryByKey(routeInfoBean.getDestinationInfoType(), routeInfoBean.getDestinationInfoId()));
		routeInfoEntityBean.setDestinationProvince(
				stationInfoMediator.getProvinceByKey(routeInfoBean.getDestinationInfoType(), routeInfoBean.getDestinationInfoId()));
		routeInfoEntityBean
				.setDestinationCity(stationInfoMediator.getCityByKey(routeInfoBean.getDestinationInfoType(), routeInfoBean.getDestinationInfoId()));
		routeInfoEntityBean.setDestinationRegion(
				stationInfoMediator.getRegionByKey(routeInfoBean.getDestinationInfoType(), routeInfoBean.getDestinationInfoId()));
		routeInfoEntityBean.setDestinationAddress(
				stationInfoMediator.getAddressByKey(routeInfoBean.getDestinationInfoType(), routeInfoBean.getDestinationInfoId()));
		routeInfoEntityBean.setDestinationTelephone(
				stationInfoMediator.getTelephoneByKey(routeInfoBean.getDestinationInfoType(), routeInfoBean.getDestinationInfoId()));
		routeInfoEntityBean.setDestinationMobile(
				stationInfoMediator.getMobileByKey(routeInfoBean.getDestinationInfoType(), routeInfoBean.getDestinationInfoId()));
		return routeInfoEntityBean;
	}

}