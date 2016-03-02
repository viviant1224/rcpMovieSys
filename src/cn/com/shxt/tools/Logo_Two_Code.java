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
     * ���ɶ�ά��(QRCode)ͼƬ 
     * @param content ��ά��ͼƬ������
     * @param imgPath ���ɶ�ά��ͼƬ������·��
     * @param ccbpath  ��ά��ͼƬ�м��logo·��
     */  
    public static String createQRCode(String content, String imgPath,String ccbPath) {  
    	
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            //���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            //N��������,A�����ַ�a-Z,B���������ַ�
            qrcodeHandler.setQrcodeEncodeMode('B'); 
            // �������ö�ά��汾��ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��  
            qrcodeHandler.setQrcodeVersion(6);  
  
            byte[] contentBytes = content.getBytes("gb2312");  
            BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, 140, 140);  
  
            // �趨ͼ����ɫ > BLACK  
            gs.setColor(Color.BLACK);  
  
            // ����ƫ���� �����ÿ��ܵ��½�������  
            int pixoff = 2;  
            // ������� > ��ά��  
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
            Image img = ImageIO.read(new File(ccbPath));//ʵ����һ��Image����
            gs.drawImage(img, 50, 50, null);
            gs.dispose();  
            bufImg.flush();  
  
            // ���ɶ�ά��QRCodeͼƬ  
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

    		
    		/* �����ϴ�����ļ����� */
    		String fileName = new Date().getTime() + ""
    				+ (int) (Math.random() * 1000) + ".jpg";
    		System.out.println("������ɵ��ļ�����" + fileName);
    		/* ͨ��iconsĿ¼�����ɵ��ļ��� �� �ϳ�Ŀ��ͼƬ�ļ�������·�� */
    		String target = str + fileName;
    		File targetFile = new File(target);
    		if (!targetFile.exists()) {
    			try {
    				targetFile.createNewFile();
    			} catch (IOException e1) {
//    				e1.printStackTrace();
    			}
    		}
    		/* ͼƬ���Ʋ��裺 ������������� */
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
    			System.out.println("uploadFileUtil.java����--ͼƬ�ϴ��ɹ�!");
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
