package com.zhang.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.zhang.utils.config.OSSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;

@Slf4j
public class OSSUploadUtil {
	//阿里云OSS API的内或外网域名
	private static String ENDPOINT;
	//阿里云OSS API的密钥Access Key ID
	private static String ACCESS_KEY_ID;
	//阿里云OSS API的密钥Access Key Secret
	private static String ACCESS_KEY_SECRET;
	//阿里云OSS API的bucket名称
	private static String BUCKET_NAME;
	//阿里云OSS 上传文件夹
	private static String IMAGE_FOLDER;

	private static CredentialsProvider credentialsProvider;

	private static final long IMAGE_EXPIRE_TIME = 10 * 365 * 24 * 60 * 60 * 1000L;//图片访问链接的过期时间

	private static OSSClient ossClient;

	public OSSUploadUtil() {
		//TODO 完善工具类的封装
		ossClient = new OSSClient(ENDPOINT, credentialsProvider, null);
	}

	public static void setConfigInfo(OSSConfig ossClientConfig) {
		OSSUploadUtil.ENDPOINT = ossClientConfig.getEndpoint();
		OSSUploadUtil.ACCESS_KEY_ID = ossClientConfig.getAccessKeyId();
		OSSUploadUtil.ACCESS_KEY_SECRET = ossClientConfig.getAccessKeySecret();
		OSSUploadUtil.BUCKET_NAME = ossClientConfig.getBucketName();
		OSSUploadUtil.IMAGE_FOLDER = ossClientConfig.getPicLocation();

		credentialsProvider = new DefaultCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
	}

	/**
	 * 初始化
	 */
	public static void init() {
		ossClient = new OSSClient(ENDPOINT, credentialsProvider, null);
	}

	/**
	 * 销毁
	 */
	public static void destory() {
		ossClient.shutdown();
	}

	/**
	 * @param: instream
	 * @param: fileName
	 * @Return: java.lang.String
	 * @Decription: 上传图片至OSS
	 * @CreateDate: Created in 2018/12/11 16:36
	 * @Modify:
	 */
	public static String uploadImageToOSS(MultipartFile file, String fileName) {
		if (ossClient == null) {
			init();
		}

		String ret = "";
		try {
			//创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(file.getInputStream().available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."))));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			//上传文件
			PutObjectResult putResult = ossClient.putObject(BUCKET_NAME, IMAGE_FOLDER + fileName, file.getInputStream(), objectMetadata);
			ret = putResult.getETag();
		} catch (IOException e) {
			log.error("upload file to oss error name={}", fileName, e);
		} finally {
			try {
				if (file.getInputStream() != null) {
					file.getInputStream().close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * @param: FilenameExtension
	 * @Return: java.lang.String
	 * @Decription: 判断OSS服务文件上传时文件的contentType
	 * @CreateDate: Created in 2018/12/11 17:19
	 * @Modify:
	 */
	private static String getcontentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase(".bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase(".gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
				FilenameExtension.equalsIgnoreCase(".jpg") ||
				FilenameExtension.equalsIgnoreCase(".png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase(".html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase(".txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase(".vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase(".pptx") ||
				FilenameExtension.equalsIgnoreCase(".ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase(".docx") ||
				FilenameExtension.equalsIgnoreCase(".doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase(".xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}

	/**
	 * @param: fileName
	 * @Return: java.lang.String
	 * @Decription: 根据图片名称获取图片访问地址
	 * @CreateDate: Created in 2018/12/11 17:18
	 * @Modify:
	 */
	public static String getImageUrl(String fileName) {
		// 设置URL过期时间
		Date expiration = new Date(new Date().getTime() + IMAGE_EXPIRE_TIME);

		if (ossClient == null) {
			init();
		}
		// 生成URL
		URL url = ossClient.generatePresignedUrl(BUCKET_NAME, IMAGE_FOLDER + fileName, expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}
}