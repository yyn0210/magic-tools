/*
 * Copyright (C) 2017 hutool.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.magictools;


import com.magictools.core.lang.ConsoleTable;
import com.magictools.core.util.ClassUtil;
import com.magictools.core.util.StrUtil;

import java.util.Set;

public class MagicTools {

	private MagicTools() {
	}

	/**
	 * 显示Hutool所有的工具类
	 *
	 * @return 工具类名集合
	 * @since 5.5.2
	 */
	public static Set<Class<?>> getAllUtils() {
		return ClassUtil.scanPackage("com.magictools",
				(clazz) -> (!clazz.isInterface()) && StrUtil.endWith(clazz.getSimpleName(), "Util"));
	}

	/**
	 * 控制台打印所有工具类
	 */
	public static void printAllUtils() {
		final Set<Class<?>> allUtils = getAllUtils();
		final ConsoleTable consoleTable = ConsoleTable.create().addHeader("工具类名", "所在包");
		for (Class<?> clazz : allUtils) {
			consoleTable.addBody(clazz.getSimpleName(), clazz.getPackage().getName());
		}
		consoleTable.print();
	}
}
