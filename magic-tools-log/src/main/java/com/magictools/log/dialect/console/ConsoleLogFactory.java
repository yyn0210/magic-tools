package com.magictools.log.dialect.console;

import com.magictools.log.Log;
import com.magictools.log.LogFactory;

/**
 * 利用System.out.println()打印日志
 * @author Looly
 *
 */
public class ConsoleLogFactory extends LogFactory {

	public ConsoleLogFactory() {
		super("MagicTools Console Logging");
	}

	@Override
	public Log createLog(String name) {
		return new ConsoleLog(name);
	}

	@Override
	public Log createLog(Class<?> clazz) {
		return new ConsoleLog(clazz);
	}

}
