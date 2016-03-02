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
 * �ļ���ͼƬ���ϴ��Ĺ�����
 * @author Administrator 2013-6-4
 * @version 1.0
 */
public class uploadFileUtil {
	
	/**
	 * �ļ��ϴ��ķ���1
	 * 
	 * @param source
	 *            �ļ���Դ·��
	 * @return �²������ļ�����
	 */
//	public static void uploadImg(String source) {
		public static String uploadImg(String source) {
		String target = "";
		/* ͼƬ�ϴ��� ���̵�iconsĿ¼�� */
		File sourceFile = new File(source);// ͨ��ԴͼƬ������·�� ����Դ�ļ�����

		/* ���iconsĿ¼��λ�� */
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

		
		/* �����ϴ�����ļ����� */
		String fileName = new Date().getTime() + ""
				+ (int) (Math.random() * 1000) + ".jpg";
		System.out.println("������ɵ��ļ�����" + fileName);
		/* ͨ��iconsĿ¼�����ɵ��ļ��� �� �ϳ�Ŀ��ͼƬ�ļ�������·�� */
		target = str + fileName;
		File targetFile = new File(target);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		/* ͼƬ���Ʋ��裺 ������������� */
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
			System.out.println("uploadFileUtil.java����--ͼƬ�ϴ��ɹ�!");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return fileName;
	}

	/**
	 * �ļ��ϴ��ķ���2
	 * 
	 * @param filePath
	 *            �ļ�Դ·��
	 * @param fileName
	 *            ���ɵ��ļ�����
	 */
	public static void upload(String filePath, String fileName) {
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			is = new FileInputStream(filePath + "\\" + fileName);
			// ȡ��icon�ļ��е�·��
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
