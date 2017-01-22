package com.lpmas.tms.config;

import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lpmas.framework.cache.memcached.MemcachedPool;
import com.lpmas.framework.crypto.MD5;
import com.lpmas.tms.route.bean.PathInfoBean;
import com.lpmas.tms.route.bean.WeightedEdgeInfoBean;

public class TmsMongoConfig {

	public static String DB_NAME = "tms";

	public static final String COLLECTION_PATH_INFO = "path_info";
	
	private static final String PATH_INFO_KEY = "PATH_INFO";

	public static String getPathInfoKey(PathInfoBean bean) {
		String key = MessageFormat.format("{0}_{1}_{2}_{3}_{4}", PATH_INFO_KEY, bean.getSourceInfoType(), bean.getSourceInfoId(),
				bean.getDestinationInfoType(), bean.getDestinationInfoId());
		for (WeightedEdgeInfoBean edge : bean.getPathList()) {
			key += "_" + edge.hashCode();
		}
		return MD5.getMD5(key);
	}

	static {
		initDbName();
	}

	private static void initDbName() {
		try {
			InputStreamReader in = new InputStreamReader(MemcachedPool.class.getResourceAsStream("/config/mongodb.xml"));
			SAXReader reader = new SAXReader();
			Document document = reader.read(in);
			Element root = document.getRootElement();
			Iterator<Element> iter = root.elementIterator("mongodb");
			if (iter.hasNext()) {
				Element config = iter.next();
				DB_NAME = config.elementText("db_name");
			}
			in.close();
		} catch (Exception e) {
		}
	}
}
