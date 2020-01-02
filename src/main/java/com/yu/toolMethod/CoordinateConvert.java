package com.yu.toolMethod;

import com.yu.model.MapCoordinate;
import com.yu.utils.CoordinateConvertUtil;

/**
 * 坐标转换
 * 
 * @author Administrator
 *
 */
public class CoordinateConvert {

	/**
     * 火星坐标系 (GCJ-02)转为百度坐标系 (BD-09)
     * 
     * @param gcLon 火星坐标系经度
	 * @param gcLat火星坐标系纬度
     * 
     */
	public static MapCoordinate gcj02_To_Bd09(double gcLon,double gcLat){
		//60486.107832982100	49554.379575900450
		
		MapCoordinate actualLoction = CoordinateConvertUtil.szbj54_To_Bd09(gcLat, gcLon);
		
		System.out.println(actualLoction.toString());
		
		return actualLoction;
	}
}
