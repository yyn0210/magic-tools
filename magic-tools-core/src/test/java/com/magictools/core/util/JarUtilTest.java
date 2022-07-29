package com.magictools.core.util;

import junit.framework.TestCase;

import java.io.IOException;

public class JarUtilTest extends TestCase {

	public void testWriteJarToFile() {
		JarUtil.writeJarToFile("C:\\Developments\\jetbra\\ja-netfilter.jar", "D:\\tmp\\bgap-demo-1.0.0-SNAPSHOT.jar.txt");
	}
	public void testWriteFileToJar() throws IOException {
		JarUtil.writeFileToJar( "D:\\tmp\\bgap-demo-1.0.0-SNAPSHOT.jar.txt","D:\\tmp\\ja-netfilter.jar");
	}
}
