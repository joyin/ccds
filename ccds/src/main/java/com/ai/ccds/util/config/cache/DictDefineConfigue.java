package com.ai.ccds.util.config.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component("dictDefineConfigue")
public class DictDefineConfigue implements ApplicationListener<ContextRefreshedEvent> {
	private static Log logger = LogFactory.getLog(DictDefineConfigue.class);

	/**
	 * 当一个ApplicationContext被初始化或刷新触发
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.debug("=====================spring容器初始化完毕==========================");
		try {
			logger.debug("===================开始加载专家字典缓存=============================");
			//DictCatche.initDict(0);
			logger.debug("===================加载专家字典缓存结束=============================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}