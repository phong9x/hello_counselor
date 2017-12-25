package org.trams.hello.web.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtils {

	public static String saveFileOrigin(MultipartFile file, ServletContext servletContext) {
		String imgPatch = "";
		try {
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;

			inputStream = file.getInputStream();
			String separator = java.nio.file.FileSystems.getDefault().getSeparator();
			String patch = separator+"data"+separator+"opt"+separator+"hello_image";
			String fileNewName = System.nanoTime() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = patch +  separator + fileNewName;
			outputStream = new FileOutputStream(fileName);
			int readBytes = 0;
			byte[] buffer = new byte[10000];
			while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();
			imgPatch = "/images/" + fileNewName;

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return ConstantUtils.getConfig("domain")+imgPatch;
	}
}
