package com.ace.inter;


import com.ace.domain.QueryConfigReq;
import com.ace.domain.QueryConfigResp;

public interface ConfigService {

	public QueryConfigResp queryConfig(QueryConfigReq req);

/*	*//**
	 * 查询配置信息
	 * @param req
	 * @return
	 *//*
	@POST
	@Path("/queryCodeValue")
	public CodeValueResp queryCodeValue(CodeValueReq req);

	*//**
	 * 查询配置信息
	 * @param req
	 * @return
	 *//*
	@POST
	@Path("/queryConfigAll")
	public QueryConfigResp queryConfigAll(QueryConfigReq req);

	*//**
	 * 查询版本说明
	 * @param req
	 * @return
	 *//*
	@POST
	@Path("/queryVersionDetail")
	public BaseResp queryVersionDetail();

	*//**
	 * 根据经纬度查询电子围栏开关状态和销售范围
	 * @param req
	 * @return
	 *//*
	@POST
	@Path("/queryLocation")
	public BaseResp queryLocation(QueryLocationReq req);

	@POST
	@Path("/qryLoginPic")
	public BaseResp qryLoginPic(CodeValueReq req);

	*//**根据枚举key值，查取枚举列表*//*
	@POST
	@Path("/queryEnmuData")
	public BaseResp queryEnmuData(QueryConfigReq req);

	*//**
	 * 查询配置信息
	 * @param req
	 * @return
	 *//*
	@POST
	@Path("/qryQrCodeUrl")
	public CodeValueResp queryQrCodeUrl(CodeValueReq req);*/
}
