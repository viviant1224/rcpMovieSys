package cn.com.shxt.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.eclipse.core.runtime.FileLocator;

import cn.com.shxt.core.Activator;

/**
 * 文件（图片）上传的工具类
 * @author Administrator 2013-6-4
 * @version 1.0
 */
public class uploadFileUtil {
	
	/**
	 * 文件上传的方法1
	 * 
	 * @param source
	 *            文件的源路径
	 * @return 新产生的文件名称
	 */
//	public static void uploadImg(String source) {
		public static String uploadImg(String source) {
		String target = "";
		/* 图片上传至 工程的icons目录下 */
		File sourceFile = new File(source);// 通过源图片的完整路径 创建源文件对象

		/* 获得icons目录的位置 */
		//URL url = Activator.getDefault().getBundle().getResource("icons");
		URL url = Activator.getDefault().getBundle().getResource("uploadImg");
		System.out.println("url==="+url);
		String str = "";
		try {
			System.out.println("FileLocator.toFileURL(url).toString():"+FileLocator.toFileURL(url).toString());
			str = FileLocator.toFileURL(url).toString().substring(6);
			System.out.println("str==="+str);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		/* 生成上传后的文件名称 */
		String fileName = new Date().getTime() + ""
				+ (int) (Math.random() * 1000) + ".jpg";
		System.out.println("随机生成的文件名：" + fileName);
		/* 通过icons目录和生成的文件名 ， 合成目标图片文件的完整路径 */
		target = str + fileName;
		File targetFile = new File(target);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		/* 图片复制步骤： 输入流、输出流 */
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		byte[] bs = new byte[(int) sourceFile.length()];
		try {
			in = new BufferedInputStream(new FileInputStream(sourceFile));
			in.read(bs);
			out = new BufferedOutputStream(new FileOutputStream(targetFile));
			out.write(bs);
			out.flush();
			out.close();
			in.close();
			System.out.println("uploadFileUtil.java类中--图片上传成功!");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return fileName;
	}

	/**
	 * 文件上传的方法2
	 * 
	 * @param filePath
	 *            文件源路径
	 * @param fileName
	 *            生成的文件名称
	 */
	public static void upload(String filePath, String fileName) {
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			is = new FileInputStream(filePath + "\\" + fileName);
			// 取得icon文件夹的路径
			URL url = Activator.getDefault().getBundle().getResource("icons");
			String myUrl = FileLocator.toFileURL(url).toString().substring(6);
			// System.out.println(myUrl);//D:\shxt14_workspace\rcp_project_demo\icons\test.jpg
			os = new FileOutputStream(myUrl + "/" + fileName);

			byte[] buffer = new byte[1024];
			int readLength = 0;
			while ((readLength = is.read(buffer)) > 0) {
				os.write(buffer, 0, readLength);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
