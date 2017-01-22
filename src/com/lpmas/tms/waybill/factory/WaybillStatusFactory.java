package com.lpmas.tms.waybill.factory;

import com.lpmas.tms.waybill.bean.WaybillInfoBean;
import com.lpmas.tms.waybill.busniess.WaybillStatus;
import com.lpmas.tms.waybill.busniess.impl.WaybillApprovedStatusImpl;
import com.lpmas.tms.waybill.busniess.impl.WaybillClosedStatusImpl;
import com.lpmas.tms.waybill.busniess.impl.WaybillEditStatusImpl;
import com.lpmas.tms.waybill.busniess.impl.WaybillPlacedOrderStatusImpl;
import com.lpmas.tms.waybill.busniess.impl.WaybillReceivedStatusImpl;
import com.lpmas.tms.waybill.busniess.impl.WaybillWaitApproveStatusImpl;
import com.lpmas.tms.waybill.config.WaybillConfig;

public class WaybillStatusFactory {
	
	public WaybillStatus getWayBillStatus(WaybillInfoBean bean){
		if(bean.getWaybillStatus().equals(WaybillConfig.WAYBILL_STATUS_EDIT)){
			return new WaybillEditStatusImpl(bean);
		}else if(bean.getWaybillStatus().equals(WaybillConfig.WAYBILL_STATUS_WAIT_APPROVE)){
			return new WaybillWaitApproveStatusImpl(bean);
		}else if(bean.getWaybillStatus().equals(WaybillConfig.WAYBILL_STATUS_APPROVED)){
			return new WaybillApprovedStatusImpl(bean);
		}else if(bean.getWaybillStatus().equals(WaybillConfig.WAYBILL_STATUS_PLACED_ORDER)){
			return new WaybillPlacedOrderStatusImpl(bean);
		}else if(bean.getWaybillStatus().equals(WaybillConfig.WAYBILL_STATUS_RECEIVED)){
			return new WaybillReceivedStatusImpl(bean);
		}else if(bean.getWaybillStatus().equals(WaybillConfig.WAYBILL_STATUS_CLOSED)){
			return new WaybillClosedStatusImpl(bean);
		}
		return null;
	}

}
