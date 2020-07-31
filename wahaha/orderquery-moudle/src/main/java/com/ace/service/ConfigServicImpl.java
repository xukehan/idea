package com.ace.service;

import com.ace.domain.QueryConfigReq;
import com.ace.domain.QueryConfigResp;
import com.ace.inter.ConfigService;
import com.ace.mapper.TPEnumerateMapper;
import com.ace.pojo.TPEnumerate;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

@Slf4j
@RequestMapping(value = "/configService")
@Api(value = "configService|配置查询服务")
@Controller
public class ConfigServicImpl implements ConfigService {


	@Autowired
	private TPEnumerateMapper tPEnumerateMapper;
	/*@Resource
	private TBCodeMapper tBCodeMapper;
	@Resource
	private TBCodevalueMapper tBCodevalueMapper;
	@Resource
	private TBReleasenotesMapper tBReleasenotesMapper;
	@Resource
	private TBStaffrightMapper tBStaffrightMapper;*/

	@Override
	@ResponseBody
	@RequestMapping(value = "/queryConfig", method = RequestMethod.POST)
	@ApiOperation(value = "查询配置", notes ="xukh")
	public QueryConfigResp queryConfig(@RequestBody QueryConfigReq req) {
		log.info("queryConfig服务入参：" + JSON.toJSONString(req));
		TPEnumerate enu = tPEnumerateMapper.selectByPrimaryKey(req
				.getAttrCode());
		QueryConfigResp resp = new QueryConfigResp();
		if (enu == null) {
			resp.setAttrValue("");
			resp.setParentAttrValue("");
			resp.setRemark("");
		} else {
			resp.setAttrValue(enu.getAttrValue());
			resp.setParentAttrValue(enu.getParentAttrValue());
			resp.setRemark(enu.getRemark());
		}
		resp.setCode("0000");
		resp.setMsg("success");
		return resp;
	}

	/*@Override
	public CodeValueResp queryCodeValue(CodeValueReq req) {
		logger.info("queryCodeValue服务入参：" + JSON.toJSONString(req));
		String codeId = req.getCodeId();
		String codeValueId = req.getCodeValueId();
		String upCodeValueId = req.getUpCodeValueId();
		List<String> codeValueIds = req.getCodeValueIds();

		CodeValueResp result = new CodeValueResp();
		Map<String, Object> whereParam = new HashMap<String, Object>();
		whereParam.put("codeId", codeId);
		if (StringUtils.isNotEmpty(codeValueId)) {
			whereParam.put("codeValueId", codeValueId);
		}
		if (StringUtils.isNotEmpty(upCodeValueId)) {
			whereParam.put("upCodeValueId", upCodeValueId);
		}
		if (codeValueIds != null && codeValueIds.size() > 0) {
			whereParam.put("codeValueIds", codeValueIds);
		}

		List<Map<String, String>> codeValues = tBCodeMapper
				.queryCodeValue(whereParam);
		Map<String, Object> data = new HashMap<String, Object>();
		if (codeValues != null && codeValues.size() > 0) {
			result.setCodeId(codeId);
			result.setCodeName(codeValues.get(0).get("codeName"));
			data.put("data", codeValues);
		}
		result.setCode(Constants.Common.INTER_SUCCESS_CODE);
		result.setMsg(Constants.Common.INTER_SUCCESS_MSG);
		result.setData(data);
		return result;
	}

	@Override
	public BaseResp queryVersionDetail() {
		Example example = new Example(TBReleasenotes.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("flag", "0");
		example.setOrderByClause("rule ASC");
		List<TBReleasenotes> list = tBReleasenotesMapper
				.selectByExample(example);
		BaseResp result = new BaseResp();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("data", list);
		result.setCode(Constants.Common.INTER_SUCCESS_CODE);
		result.setMsg(Constants.Common.INTER_SUCCESS_MSG);
		result.setData(data);
		return result;
	}

	@Override
	public QueryConfigResp queryConfigAll(QueryConfigReq req) {
		logger.info("queryConfigAll服务入参：" + JSON.toJSONString(req));
		QueryConfigResp resp = new QueryConfigResp();
		Example example = new Example(TPEnumerate.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("attrCode", req.getAttrCode());
		criteria.andLessThanOrEqualTo("startDate", new Date());
		criteria.andGreaterThanOrEqualTo("endDate", new Date());
		List<TPEnumerate> enuList = tPEnumerateMapper.selectByExample(example);
		if (enuList.size() > 0
				&& !StringUtils.isEmpty(enuList.get(0).getAttrCode())) {
			TPEnumerate enu = enuList.get(0);

			if (enu == null) {
				resp.setAttrValue("");
				resp.setParentAttrValue("");
				resp.setRemark("");
				resp.setCode("0011");
				resp.setMsg(Constants.Common.INTER_SUCCESS_MSG);
			} else {
				resp.setAttrValue(enu.getAttrValue());
				resp.setParentAttrValue(enu.getParentAttrValue());
				resp.setRemark(enu.getRemark());
				resp.setAtteValueName(enu.getAttrValueName());
				resp.setCode(Constants.Common.INTER_SUCCESS_CODE);
				resp.setMsg(Constants.Common.INTER_SUCCESS_MSG);
			}
		} else {
			resp.setCode("0011");
			resp.setMsg(Constants.Common.INTER_SUCCESS_MSG);
		}

		return resp;
	}

	@Override
	public BaseResp queryLocation(QueryLocationReq req) {
		logger.info("queryLocation服务入参：" + JSON.toJSONString(req));
		BaseResp resp = new BaseResp();
		Map<String, Object> data = new HashMap<String, Object>();
		String attrValue = EnumerateUtils.getAttrValue("dzwlFlag");
		String remark = EnumerateUtils.getRemark("dzwlFlag");
		if(attrValue == null || attrValue == ""){
			resp.setCode("0001");
			resp.setMsg("查询电子围栏开关状态失败！");
			return resp;
		}
		//查询工号的特殊权限
		List<String> staffDatas = new ArrayList<String>();
		List<Map<String, Object>> dataList = tBStaffrightMapper.queryStaffDataRights(req.getStaffId());
		if(dataList.size() > 0){
			for(Map<String, Object> staffData:dataList){
				staffDatas.add((String)staffData.get("rightCode"));
			}
		}

		// 查询电子围栏距离
		Example enuExample = new Example(TPEnumerate.class);
		Criteria enuCriteria = enuExample.createCriteria();
		List<Object> enuAttrCodes = new ArrayList<Object>();
		enuAttrCodes.add("dianZiWeiLan");//电子围栏距离
		enuCriteria.andIn("attrCode", enuAttrCodes);
		List<TPEnumerate> enuList = tPEnumerateMapper.selectByExample(enuExample);
		String dianZiWeiLan  = "";
		if(enuList != null && enuList.size() > 0){
			for(TPEnumerate enumerate:enuList){
				String attrCode = enumerate.getAttrCode();
				String dzwlattrValue = enumerate.getAttrValue();
				if ("dianZiWeiLan".equals(attrCode)) {
					dianZiWeiLan = dzwlattrValue;
				}
			}
		}
		data.put("dianZiWeiLan", dianZiWeiLan);
		data.put("staffDatas", staffDatas);
		data.put("attrValue", attrValue);
		data.put("remark", remark);
		// 公共API无法在测试环境使用，已经将查询地理位置的方法写到前端。
//		if(attrValue == "0"){
//			resp.setCode("0000");
//			resp.setMsg("success");
//			resp.setData(data);
//			return resp;
//		}
//		String longitude = req.getLongitude();
//		String latitude = req.getLatitude();
//		String addres = GetLocation.getAdd(longitude, latitude);
//		logger.info("查询地址信息>>>:addres：" + addres);
//		if(addres != null && addres != ""){
//			JSONObject jsonObject = JSONObject.parseObject(addres);
//			JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("addrList"));
//			JSONObject j_2 = JSONObject.parseObject(String.valueOf(jsonArray.get(0)));
//			String allAdd = j_2.getString("admName");
//			String arr[] = allAdd.split(",");
//			String cityName = arr[0];
//			data.put("data", addres);
//			data.put("cityName", cityName);
//		}else {
//			resp.setCode("0011");
//			resp.setMsg("对不起，没有查询到您所在的城市，请稍后重试。");
//		}
		resp.setCode("0000");
		resp.setMsg("success");
		resp.setData(data);
		return resp;
	}

	@Override
	public BaseResp qryLoginPic(CodeValueReq req) {
		logger.info("qryLoginPic服务入参：" + JSON.toJSONString(req));
		BaseResp resp = new BaseResp();
		resp.setCode("0000");
		resp.setMsg("查询成功");
		String codeId = req.getCodeId();
		if (codeId == null || "".equals(codeId)) {
			resp.setCode("9999");
			resp.setMsg("code_id为空");
		}
		try {
			TBCodevalue value = new TBCodevalue();
			value.setCodeId(codeId);
			List<TBCodevalue> valueList = new ArrayList<TBCodevalue>();
			valueList = tBCodevalueMapper.select(value);
			if (valueList == null || valueList.size() == 0) {
				resp.setCode("9999");
				resp.setMsg("查询结果为空");
			}
			Map<String, Object> dateMap = new HashMap<String, Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String sysDate = df.format(new Date());
			List<TBCodevalue> newValueList = new ArrayList<TBCodevalue>();
			for (TBCodevalue v : valueList) {
				String startDate = v.getCodeValueDesc().substring(0, 8);
				String endDate = v.getUpCodeValueId().substring(0, 8);
				if (startDate.compareTo(sysDate) <= 0 && endDate.compareTo(sysDate) >= 0) {
					newValueList.add(v);
				}
			}
			logger.info("newValueList"+newValueList.size());
			if (newValueList != null && newValueList.size() > 0) {
				if (newValueList.size() == 1) {
					dateMap.put("codeValueId", newValueList.get(0).getCodeValueId());
					dateMap.put("codeValueName", newValueList.get(0).getCodeValueName());
				} else {
					for (TBCodevalue v : newValueList) {
						if ("0".equals(v.getCodeRule().toString().trim())) {
							dateMap.put("codeValueId", v.getCodeValueId());
							dateMap.put("codeValueName", v.getCodeValueName());
							break;
						}
					}
				}
			}

			if (dateMap == null || dateMap.size() == 0) {
				resp.setCode("8888");
				resp.setMsg("查询结果为空");
			} else {
				resp.setData(dateMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode("00001");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

	*//**根据枚举key值，查取枚举列表*//*
	@Override
	public BaseResp queryEnmuData(QueryConfigReq req){
		logger.info("ConfigServicImpl_queryEnmuData入参："+ JSON.toJSONString(req));
		BaseResp baseResp = new BaseResp();
		baseResp.setCode("0000");
		baseResp.setMsg("查询成功");
		Map<String, Object> map = new HashMap<>();
		if (req.getCodeList().size() < 0 || null ==req.getCodeList()){
			baseResp.setCode("9999");
			baseResp.setMsg("查询列表为空，请检查");
			return baseResp;
		}

		List<Map<String, Object>> resultList = null;
		List list = req.getCodeList();
		try{
			resultList = tPEnumerateMapper.queryForList(list);
		}catch (Exception e){
			e.printStackTrace();
			throw new BusiException("查询结果异常："+e.getMessage());
		}
		if (resultList != null && resultList.size() > 0){
			for (Map<String, Object> resultMap : resultList) {
				map.put(String.valueOf(resultMap.get("ATTR_CODE")),resultMap.get("ATTR_VALUE"));
			}
		}

		baseResp.setData(map);
		logger.info("ConfigServicImpl_queryEnmuData出参："+ JSON.toJSONString(baseResp));
		return baseResp;
	}

	*//**
	 * 查询图片及生成二维码图片
	 * @param req
	 * @return
	 *//*
	@Override
	public CodeValueResp queryQrCodeUrl(CodeValueReq req) {
		logger.info("查询图片及生成二维码图片请求"+ JSON.toJSONString(req));
		CodeValueResp resp = new CodeValueResp();
		String codeId = req.getCodeId();
		String rqStr = req.getStr();
		resp.setCode("0000");
		resp.setMsg("查询成功");

		if (StringUtils.isEmpty(codeId) || StringUtils.isEmpty(rqStr)) {
			resp.setCode("9999");
			resp.setMsg("参数异常");
		}
		String path = EnumerateUtils.getAttrValue(codeId);//获取主机模板路径
		String erBase = "";//合成的二维码base64串

		//把string变成二维码然后转成Base64导出
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型
		hints.put(EncodeHintType.MARGIN, 1);  //设置白边
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(rqStr, BarcodeFormat.QR_CODE, 300, 300,hints);
			int width = bitMatrix.getWidth();
			int height = bitMatrix.getHeight();
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);//0xFF000000黑；0xFFFFFFFF白
				}
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			//输出二维码图片流
			try {
				ImageIO.write(image, "png",outputStream);
				erBase = Base64.encodeBase64String(outputStream.toByteArray());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.setCode("8888");
				resp.setMsg("生成二维码异常");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			resp.setCode("8888");
			resp.setMsg("生成二维码异常");
		}
		resp.setCodeId(path);
		resp.setCodeName(erBase);
		logger.info("查询图片及生成二维码图片返回"+ JSON.toJSONString(resp));
		return resp;
	}*/
}
