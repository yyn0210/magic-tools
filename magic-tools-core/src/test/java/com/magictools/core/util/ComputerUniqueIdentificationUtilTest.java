package com.magictools.core.util;

import junit.framework.TestCase;

public class ComputerUniqueIdentificationUtilTest extends TestCase {

	public void testGetOSName() {
	}

	public void testGetOSNamePrefix() {
	}

	public void testGetMainBoardSerialNumber() {
	}

	public void testGetMACAddress() {
	}

	public void testGetCPUIdentification() {
		String identification = ComputerUniqueIdentificationUtil.getCPUIdentification();
		System.out.println(identification);
	}


	public void testGetComputerUniqueIdentificationString() {
		String identificationString = ComputerUniqueIdentificationUtil.getComputerUniqueIdentificationString();
		System.out.println(identificationString);
	}
}
