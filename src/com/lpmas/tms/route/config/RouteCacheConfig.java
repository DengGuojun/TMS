package com.lpmas.tms.route.config;

import java.text.MessageFormat;

public class RouteCacheConfig {

	private static final String ROUTE_NAME_CACHE_KEY = "ROUTE_NAME";

	public static String getRouteNameCacheKey(int routeId) {
		return MessageFormat.format("{0}_{1}", ROUTE_NAME_CACHE_KEY, routeId);
	}
}
