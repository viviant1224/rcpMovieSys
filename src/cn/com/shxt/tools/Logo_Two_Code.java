package cn.com.shxt.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

import org.eclipse.core.runtime.FileLocator;

import cn.com.shxt.core.Activator;

import com.swetake.util.Qrcode;

public class Logo_Two_Code {
	/** 
     * 生成二维码(QRCode)图片 
     * @param content 二维码图片的内容
     * @param imgPath 生成二维码图片完整的路径
     * @param ccbpath  二维码图片中间的logo路径
     */  
    public static String createQRCode(String content, String imgPath,String ccbPath) {  
    	
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            //N代表数字,A代表字符a-Z,B代表其他字符
            qrcodeHandler.setQrcodeEncodeMode('B'); 
            // 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大  
            qrcodeHandler.setQrcodeVersion(6);  
  
            byte[] contentBytes = content.getBytes("gb2312");  
            BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, 140, 140);  
  
            // 设定图像颜色 > BLACK  
            gs.setColor(Color.BLACK);  
  
            // 设置偏移量 不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容 > 二维码  
            if (contentBytes.length > 0 && contentBytes.length <150) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                System.err.println("QRCode content bytes length = "  
                        + contentBytes.length + " not in [ 0,125]. ");  
                return null;
            }  
            Image img = ImageIO.read(new File(ccbPath));//实例化一个Image对象。
            gs.drawImage(img, 50, 50, null);
            gs.dispose();  
            bufImg.flush();  
  
            // 生成二维码QRCode图片  
            File imgFile = new File(imgPath);  
            ImageIO.write(bufImg, "jpg", imgFile); 
            URL url = Activator.getDefault().getBundle().getResource("imgFile");
//            System.out.println("url==="+url);
    		String str = "";
    		try {
//    			System.out.println("FileLocator.toFileURL(url).toString():"+FileLocator.toFileURL(url).toString());
    			str = FileLocator.toFileURL(url).toString().substring(6);
    			System.out.println("str==="+str);
    		} catch (IOException e1) {
//    			e1.printStackTrace();
    		}

    		
    		/* 生成上传后的文件名称 */
    		String fileName = new Date().getTime() + ""
    				+ (int) (Math.random() * 1000) + ".jpg";
    		System.out.println("随机生成的文件名：" + fileName);
    		/* 通过icons目录和生成的文件名 ， 合成目标图片文件的完整路径 */
    		String target = str + fileName;
    		File targetFile = new File(target);
    		if (!targetFile.exists()) {
    			try {
    				targetFile.createNewFile();
    			} catch (IOException e1) {
//    				e1.printStackTrace();
    			}
    		}
    		/* 图片复制步骤： 输入流、输出流 */
    		BufferedInputStream in = null;
    		BufferedOutputStream out = null;
    		byte[] bs = new byte[(int) imgFile.length()];
    		try {
    			in = new BufferedInputStream(new FileInputStream(imgFile));
    			in.read(bs);
    			out = new BufferedOutputStream(new FileOutputStream(targetFile));
    			out.write(bs);
    			out.flush();
    			out.close();
    			in.close();
    			System.out.println("uploadFileUtil.java类中--图片上传成功!");
    		} catch (Exception e1) {
//    			e1.printStackTrace();
    		}
    		return fileName;
            
            
  
        } catch (Exception e) 
        {  
//            e.printStackTrace();  
            return null;
        }  
        
        
    }  

}
