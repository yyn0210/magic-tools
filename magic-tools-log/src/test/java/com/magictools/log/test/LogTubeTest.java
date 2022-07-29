package com.magictools.log.test;

import com.magictools.log.Log;
import com.magictools.log.LogFactory;
import com.magictools.log.dialect.logtube.LogTubeLogFactory;
import org.junit.Test;

public class LogTubeTest {

	@Test
	public void logTest(){
		LogFactory factory = new LogTubeLogFactory();
		LogFactory.setCurrentLogFactory(factory);
		Log log = LogFactory.get();
		log.debug("LogTube debug test.");
	}
}
