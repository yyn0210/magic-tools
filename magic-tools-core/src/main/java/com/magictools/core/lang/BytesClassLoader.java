package com.magictools.core.lang;


import com.magictools.core.io.FileUtil;

import java.io.File;

/**
 * 外部类加载器
 *
 * @author janeluo
 */
public class BytesClassLoader extends ClassLoader {


	public BytesClassLoader() {
	}

	/**
	 * 加载Jar到ClassPath
	 *
	 * @param classFile class文件
	 * @return BytesClassLoader
	 */
	public static BytesClassLoader load(File classFile, String name) {
		final BytesClassLoader loader = new BytesClassLoader();
		byte[] bytes = FileUtil.readBytes(classFile);
		loader.defineClass(name, bytes, 0, bytes.length);
		return loader;
	}
	/**
	 * 加载Jar到ClassPath
	 *
	 * @param classBytes class文件字节数组
	 * @return BytesClassLoader
	 */
	public static BytesClassLoader load(byte[] classBytes, String name) {
		final BytesClassLoader loader = new BytesClassLoader();
		loader.defineClass(name, classBytes, 0, classBytes.length);
		return loader;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		return super.findClass(name);
	}


}
