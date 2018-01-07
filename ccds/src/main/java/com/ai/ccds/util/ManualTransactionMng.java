package com.ai.ccds.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ai.UuidUtil;
import com.ai.ccds.servlet.ApplicationContextHelper;

/**
 * 手动控制事务提交
 * @author joyin
 *
 */
public class ManualTransactionMng {
	//缓存对应的事务状态
	private static Map<String, TransactionStatus> transactionStatusMap = new HashMap<String, TransactionStatus>();
	//事务管理器
	private DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) ApplicationContextHelper.getBean("transactionManager");
	//事务状态
	private TransactionStatus transactionStatus = null;
	
	/**
	 * 取得事务状态
	 * 作者：陈嘉瑛
	 * 时间：2017-03-20
	 * @return
	 */
	private TransactionStatus getTransactionStatus(){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		return status;
	}
	
	public void beginTran(){
		transactionStatus = getTransactionStatus(); // 获得事务状态
	}
	
	/**
	 * 启动事务，并返回事务在缓存中的标识
	 * 作者：陈嘉瑛
	 * 时间：2017-03-20
	 * @return
	 */
	public String beginTranAndCache(){
		transactionStatus = getTransactionStatus(); // 获得事务状态
		String statusUuid = UuidUtil.getUUID();
		transactionStatusMap.put(statusUuid, transactionStatus);
		return statusUuid;
	}
	
	/**
	 * 把对应缓存中的事务提交
	 * 作者：陈嘉瑛
	 * 时间：2017-03-20
	 * @return
	 */
	public void commitFromCache(String statusUuid){
		TransactionStatus status = transactionStatusMap.get(statusUuid);
		if(status != null) {
			transactionStatusMap.remove(statusUuid);
			transactionManager.commit(status);
		}
	}
	
	/**
	 * 延迟指定毫秒时间提交事务
	 * 作者：陈嘉瑛
	 * 时间：2017-03-20
	 * @param millis 延迟时间-毫秒
	 * @param statusUuid 事务在缓存中的标识
	 */
	public void lazyCommit4Millis(long millis, final String statusUuid){
		try {
			final ManualTransactionMng that = this;
			Thread.sleep(millis);
			(new Thread(){
				public void run(){
					if(StringUtils.isEmpty(statusUuid)) that.commit();
					else that.commitFromCache(statusUuid);
				}
			}).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交事务
	 * 作者：陈嘉瑛
	 * 时间：2017-03-20
	 */
	public void commit(){
		if(transactionStatus != null) transactionManager.commit(transactionStatus);
	}
	
	/**
	 * 回滚缓存中的事务
	 * 作者：陈嘉瑛
	 * 时间：2017-03-20
	 */
	public void rollbackFromCache(String statusUuid){
		TransactionStatus status = transactionStatusMap.get(statusUuid);
		if(status != null) {
			transactionStatusMap.remove(statusUuid);
			transactionManager.rollback(status);
		}
	}
	
	/**
	 * 延迟指定毫秒时间回滚事务
	 * 作者：陈嘉瑛
	 * 时间：2017-03-20
	 * @param millis 延迟时间-毫秒
	 * @param statusUuid 事务在缓存中的标识
	 */
	public void lazyRollback4Millis(long millis, final String statusUuid){
		try {
			final ManualTransactionMng that = this;
			Thread.sleep(millis);
			(new Thread(){
				public void run(){
					if(StringUtils.isEmpty(statusUuid)) that.rollback();
					else that.rollbackFromCache(statusUuid);
				}
			}).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 事务回滚
	 * 作者：陈嘉瑛
	 * 时间：2017-03-20
	 */
	public void rollback(){
		if(transactionStatus != null) transactionManager.rollback(transactionStatus);
	}
}
