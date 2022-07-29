package com.magictools.core.util;

import com.magictools.core.io.FileUtil;
import com.magictools.core.io.IoUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

public class JarUtil {

	public static void writeJarToFile(String jarPath, String outputFilePath) {
		File originalFile = new File(jarPath);
		try (
				FileInputStream inputStream = new FileInputStream(originalFile);
				JarInputStream fis = new JarInputStream(inputStream, false);
		) {


			byte[] bytes = IoUtil.readBytes(inputStream);
			FileUtil.writeBytes(bytes, outputFilePath);

//			StringBuilder stringBuilder = new StringBuilder();
//
//			int ch;
// 			while ((ch = fis.read()) != -1) {
//				stringBuilder.append((char) ch);
//			}
//
//			//Create content string
//			String content = stringBuilder.toString();
//
//			FileUtil.writeBytes(content.getBytes(StandardCharsets.UTF_8), outputFilePath);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void writeFileToJar(String filePath, String outputJarPath) throws IOException {
		//Init new jar input stream
		InputStream jarInputStream = Files.newInputStream(Paths.get(filePath));



		File newFile = new File(outputJarPath);
		//Init new jar output stream
		OutputStream fos = Files.newOutputStream(newFile.toPath());

		if (!newFile.exists()) {
			newFile.createNewFile();
		}

		int BUFFER_SIZE = 10240;
		byte[] buffer = new byte[BUFFER_SIZE];

		while (true) {

			int nRead = jarInputStream.read(buffer, 0,
					buffer.length);
			if (nRead <= 0)
				break;

			fos.write(buffer, 0, nRead);
		}

		//Write content to new jar file.
		fos.flush();
		fos.close();
		jarInputStream.close();
	}
}
