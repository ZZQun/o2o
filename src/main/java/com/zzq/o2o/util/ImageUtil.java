package com.zzq.o2o.util;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.CodingErrorAction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zzq.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {
	
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final Random r = new Random();
	
	private final static Logger logger = LoggerFactory.getLogger(ImageUtil.class);;
	
	public static String generateThumbnail(ImageHolder thumbnail,String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is:" + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200).
			outputQuality(0.8f).toFile(dest);
		}catch(IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}
	
	public static String generateNormalImg(ImageHolder thumbnail,String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is:" + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(337,640).
			outputQuality(0.9f).toFile(dest);
		}catch(IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}
	
	/**
	 * 创建目标路径所涉及的目录
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩展名
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日时分秒+五位随机数
	 * @return
	 */
	private static String getRandomFileName() {
		//获取随机五位数
		int rannum = r.nextInt(89999) + 1000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}
	/**
	 * storePath是文件的路径还是目录的路径
	 * 如果storePath是文件路径则删除该文件
	 * 如果storePath是目录路径则删除该目录下的所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for(int i = 0;i < files.length;i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
