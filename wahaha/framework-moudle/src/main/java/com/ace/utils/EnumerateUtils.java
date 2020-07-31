package com.ace.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理配置项的工具，调用bases域的服务实现
 * @author zhaoxg
 *
 */
@Component
public class EnumerateUtils implements ApplicationContextAware {
	/**
	 * 引用服务的全名称
	 */
	private static final String CLASS_NAME = "com.ace.inter.ConfigService";

	/**
	 * 方法名称
	 */
	private static final String METHOD_NAME = "queryConfig";

	/**
	 * 方法入参名称
	 */
	private static final String REQUST_CLASS_NAME = "com.ace.domain.QueryConfigReq";
	/**
	 * 方法出参名称
	 */
	private static final String RESPONSE_CLASS_NAME = "com.ace.domain.QueryConfigResp";

	private static Class requestClass;

	private static Class responseClass;

	/**
	 * 服务实例
	 */
	private volatile static Object bean;

	/**
	 * 目标方法
	 */
	private static Method queryConfig;

	/**
	 * 方法入参的setter集合，用来设置属性
	 */
	private static Map<String, Method> requestSetters = new HashMap<String, Method>();

	/**
	 * 方法出参的getter集合，用来获取属性
	 */
	private static Map<String, Method> responseGetters = new HashMap<String, Method>();

	private static ApplicationContext ctx;

	/**
	 * 获取属性值
	 * @param attrCode
	 * @return 属性值
	 */
	public static String getAttrValue(String attrCode) {
		init();

		try {
			Object req = requestClass.newInstance();
			requestSetters.get("setAttrCode").invoke(req, new Object[] {attrCode});
			Object resp = queryConfig.invoke(bean, req);
			return (String)responseGetters.get("getAttrValue").invoke(resp, new Object[0]);
		} catch (InstantiationException e) {
			throw new IllegalStateException("InstantiationException", e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("IllegalAccessException", e);
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException("IllegalArgumentException", e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException("InvocationTargetException", e);
		}
	}

	public static String[] getProperties(String attrCode) {
		init();

		try {
			Object req = requestClass.newInstance();
			requestSetters.get("setAttrCode").invoke(req, new Object[] {attrCode});
			Object resp = queryConfig.invoke(bean, req);
			String[] props = {
				(String)responseGetters.get("getAttrValue").invoke(resp, new Object[0]),
				(String)responseGetters.get("getParentAttrValue").invoke(resp, new Object[0])
			};
			return props;
		} catch (InstantiationException e) {
			throw new IllegalStateException("InstantiationException", e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("IllegalAccessException", e);
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException("IllegalArgumentException", e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException("InvocationTargetException", e);
		}
	}

	/**
	 * 获取属性值
	 * @param remark
	 * @return 属性值
	 */
	public static String getRemark(String attrCode) {
		init();

		try {
			Object req = requestClass.newInstance();
			requestSetters.get("setAttrCode").invoke(req, new Object[] {attrCode});
			Object resp = queryConfig.invoke(bean, req);
			return (String)responseGetters.get("getRemark").invoke(resp, new Object[0]);
		} catch (InstantiationException e) {
			throw new IllegalStateException("InstantiationException", e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("IllegalAccessException", e);
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException("IllegalArgumentException", e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException("InvocationTargetException", e);
		}
	}

	/**
	 * 执行初始化操作
	 */
	private static void init() {
		if (bean != null) {
			return;
		}
		synchronized (EnumerateUtils.class) {
			if (bean == null) {
				try {
					Class clazz = Class.forName(CLASS_NAME);
					requestClass = Class.forName(REQUST_CLASS_NAME);
					responseClass = Class.forName(RESPONSE_CLASS_NAME);

					queryConfig = clazz.getMethod(METHOD_NAME, new Class[] {requestClass});

					Method[] methods = requestClass.getMethods();
					for (int i = 0; i < methods.length; i++) {
						Method m = methods[i];
						String name = m.getName();
						if (name.startsWith("set")) {
							requestSetters.put(name, m);
						}
					}

					methods = responseClass.getMethods();
					for (int i = 0; i < methods.length; i++) {
						Method m = methods[i];
						String name = m.getName();
						if (name.startsWith("get")) {
							responseGetters.put(name, m);
						}
					}

					bean = ctx.getBean(clazz);
				} catch (ClassNotFoundException e) {
					throw new IllegalStateException("ClassNotFoundException", e);
				} catch (NoSuchMethodException e) {
					throw new IllegalStateException("NoSuchMethodException", e);
				} catch (BeansException e) {
					throw new IllegalStateException("BeansException", e);
				}
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.ctx = arg0;
	}

}
