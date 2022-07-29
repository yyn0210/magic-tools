package com.magictools.mybatis.interceptor;

import com.magictools.core.date.DateUtil;
import com.magictools.core.date.TimeInterval;
import com.magictools.core.util.StrUtil;
import com.magictools.json.JSONUtil;
import com.magictools.mybatis.toolkit.PluginUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 打印sql拦截器
 */
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class PrintSqlInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger("PrintSql");

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		TimeInterval timer = DateUtil.timer();


		Object target = invocation.getTarget();
		final StatementHandler statementHandler = PluginUtils.realTarget(target);
		final MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
		final MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		String statementId = mappedStatement.getId();

		final BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
		String originalSql = boundSql.getSql();
		final Object parameterObject = boundSql.getParameterObject();

		try {
			return invocation.proceed();
		} finally {
			if (logger.isDebugEnabled()) {
				originalSql = originalSql.replaceAll("\n", " ").replaceAll("\r", " ");
				Object p;
				// 这里的参数这样判断是因为加了@Param的和没有加的好像有点区别，调试的时候看到的
				if (parameterObject instanceof Map) {
					Map<?, ?> map = (Map<?, ?>) parameterObject;
					p = new HashSet<Object>(map.values());
				} else {
					p = parameterObject;
				}
				StringBuilder logStr = new StringBuilder("\n" +
						"┏━━━━━ Debug [{originalSql}] ━━━ \n" +
						"┣ SQL：\t\t {originalSql} \n" +
						"┣ 参数：\t\t " + JSONUtil.toJsonStr(p) + "\n" +
						"┣ 位置：\t\t " + statementId + " \n" +
						"┣ 时间：\t\t {times} 毫秒\n"
				);
				logStr.append("┗━━━━━ Debug [{originalSql}] ━━━");
				Map<String, Object> logPar = new HashMap<>();
				logPar.put("originalSql", originalSql);
				logPar.put("times", timer.intervalMs());
				String format = StrUtil.format(logStr, logPar);
				logger.debug(format);
			}
		}


	}

}
