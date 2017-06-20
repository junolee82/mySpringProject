package com.juno.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	// Upload
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws IOException {
		UUID uuid = UUID.randomUUID();
		String savedName = uuid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);

		String uploadedFileName = null;

		if (MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		return uploadedFileName;
	}
	
	// editorUploads
	public static String editorUploadFile(String uploadPath, String originalName, byte[] fileData) throws IOException {
		UUID uuid = UUID.randomUUID();
		String savedName = uuid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		
		String uploadedFileName = null;
		
		if (MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeEditorThumbnail(uploadPath, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		return uploadedFileName;
	}

	private static String makeIcon(String uploadPath, String savedPath, String savedName) {
		String iconName = uploadPath + savedPath + File.separator + savedName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	// Directory create
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String dataPath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, dataPath);
		logger.info(dataPath);
		
		return dataPath;
	}

	private static void makeDir(String uploadPath, String... paths) {
		
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
		
	}

	// MakeThumbnail create
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws IOException {
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 200);
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	private static String makeEditorThumbnail(String uploadPath, String path, String fileName) throws IOException {
		String thumbnailName = uploadPath + path + File.separator + fileName;
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}
