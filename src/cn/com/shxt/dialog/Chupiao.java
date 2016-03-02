package cn.com.shxt.dialog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.core.Activator;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.Logo_Two_Code;

public class Chupiao extends Dialog {

	protected Object result;
	protected Shell shell;
	private String movie_name;
	private String showtime;
	private String hour_time;
	private String min_time;
	private String type;
	private String roomname;
	private double price;
	private String zuowei;
	private DbUtils db = new DbUtils();//链接数据库的包

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Chupiao(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String movie_name,double price,String zuowei) {
		this.movie_name = movie_name;
		this.price = price;
		this.zuowei = zuowei;
		List<Map<String,Object>> list = db.query("select showtime,hour_time,min_time,type,roomname from fangying where movie_name = '"+movie_name+"'");
		this.showtime = list.get(0).get("showtime").toString();
		this.hour_time = list.get(0).get("hour_time").toString();
		this.min_time = list.get(0).get("min_time").toString();
		this.type = list.get(0).get("type").toString();
		this.roomname = list.get(0).get("roomname").toString();
		
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.BORDER | SWT.CLOSE);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/chupiao.jpg"));
		shell.setSize(386, 341);
		shell.setText("\u7535\u5F71\u7968");
		
		Label lblFangying = new Label(shell, SWT.NONE);
		lblFangying.setBounds(45, 194, 72, 17);
		lblFangying.setText(roomname);
		
		Label lblZuowei = new Label(shell, SWT.NONE);
		lblZuowei.setBounds(174, 194, 61, 17);
		lblZuowei.setText(zuowei);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(319, 194, 34, 17);
		lblNewLabel.setText(type);
		
		Label lblYingpianming = new Label(shell, SWT.NONE);
		lblYingpianming.setBounds(45, 271, 120, 17);
		lblYingpianming.setText(movie_name);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(54, 231, 86, 23);
		lblNewLabel_1.setText(showtime);
		
		Label lblShijian = new Label(shell, SWT.NONE);
		lblShijian.setBounds(195, 271, 18, 17);
		lblShijian.setText(hour_time);
		
		Label lblPiaojia = new Label(shell, SWT.NONE);
		lblPiaojia.setBounds(292, 271, 61, 17);
		lblPiaojia.setText(""+price);
		
		
		Calendar ca = Calendar.getInstance();
		long time  = ca.getTimeInMillis();
		
		Label lblBianhao = new Label(shell, SWT.NONE);
		lblBianhao.setBounds(273, 152, 98, 17);
		lblBianhao.setText(""+time);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(219, 271, 8, 17);
		label.setText(":");
		
		Label lblFen = new Label(shell, SWT.NONE);
		lblFen.setText(min_time);
		lblFen.setBounds(232, 271, 18, 17);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("华文行楷", 20, SWT.ITALIC));
		label_1.setBounds(200, 2, 170, 29);
		label_1.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662");
		String[] seat = zuowei.split(" ");
		System.out.println("=="+seat[0]+seat[1]);
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		
		
		String infoPiao = "放映厅："+roomname+"，座位："+zuowei+"，类型："+type+"，影片名："+movie_name+"，上映时间："+showtime+" "+hour_time+":"+min_time+"，票价："+price;
		System.out.println(infoPiao);
		String imgPath = "d:/java/bu.jpg";
		String ccbPath = "D:/java/83.gif"; 
		
		Logo_Two_Code.createQRCode(infoPiao, imgPath, ccbPath);
		File sourceFile = new File(imgPath);// 通过源图片的完整路径 创建源文件对象

		/* 获得icons目录的位置 */
		URL url = Activator.getDefault().getBundle()
				.getResource("icons");
		String str1 = "";
		try {
			str1 = FileLocator.toFileURL(url).toString().substring(6);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String fileName = new Date().getTime() + ""
				+ (int) (Math.random() * 100000000) + ".jpg";
		
		
		String target = str1 + fileName;
		System.out.println("---filaName--"+fileName);
		System.out.println("---target--"+target);
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
			in = new BufferedInputStream(
					new FileInputStream(sourceFile));
			in.read(bs);
			out = new BufferedOutputStream(new FileOutputStream(
					targetFile));
			out.write(bs);
			out.flush();
			out.close();
			in.close();

			System.out.println("图片上传成功!");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		lblNewLabel_2.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/"+fileName));
		lblNewLabel_2.setBounds(10, 10, 130, 126);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(233, 152, 34, 17);
		label_2.setText("\u7F16\u53F7\uFF1A");
		
		
		

	}

}
