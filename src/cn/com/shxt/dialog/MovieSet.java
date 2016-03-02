package cn.com.shxt.dialog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.shxt.core.Activator;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.StringRegexUtils;

import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class MovieSet extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;

	private DateTime dateTime;
	private DateTime dateTime_1;
	private DbUtils db = new DbUtils();
	private String source;
	private String fileName;
	private String target;

	private String id;
	private String movie_name;
	private String nation;
	private String movie_lan;
	private String director;
	private String movie_time;

	private String infor;

	private String actor;
	private String movie_img;
	private String str;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public MovieSet(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open(String id) {
		this.id = id;
		List<Map<String, Object>> i = db
				.query("select movie_name,nation,director,movie_time,movie_lan,infor,actor,movie_img from movieinfo where id = '"
						+ id + "'");

		this.movie_name = i.get(0).get("movie_name").toString();
		this.nation = i.get(0).get("nation").toString();
		this.director = i.get(0).get("director").toString();
		this.movie_time = i.get(0).get("movie_time").toString();

		this.movie_lan = i.get(0).get("movie_lan").toString();

		this.infor = i.get(0).get("infor").toString();

		this.actor = i.get(0).get("actor").toString();
		this.movie_img = i.get(0).get("movie_img").toString();

		str = i.get(0).get("movie_img").toString();
		System.out.println("============================="
				+ i.get(0).get("movie_img").toString());
		System.out.println("---------" + movie_img);

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
		shell = new Shell(getParent(), SWT.CLOSE);
		shell.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/xiaxian.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(892, 514);
		shell.setText("\u5F71\u7247\u7F16\u8F91");

		Label label_10 = new Label(shell, SWT.NONE);
		label_10.setBounds(31, 10, 61, 17);
		label_10.setText("序号：");

		text_6 = new Text(shell, SWT.BORDER);
		text_6.setEnabled(false);
		text_6.setBounds(129, 7, 73, 23);
		text_6.setText(id);

		Label label_11 = new Label(shell, SWT.NONE);
		label_11.setBounds(31, 89, 61, 17);
		label_11.setText("主演：");

		text_7 = new Text(shell, SWT.BORDER | SWT.WRAP);
		text_7.setBounds(129, 83, 128, 56);
		text_7.setText(actor);

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(31, 48, 61, 17);
		label.setText("电影名：");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(129, 45, 73, 23);
		text.setText(movie_name);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(31, 148, 61, 17);
		label_1.setText("国家：");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(129, 145, 73, 23);
		text_1.setText(nation);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(31, 197, 61, 17);
		label_2.setText("导演：");

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(129, 194, 73, 23);
		text_2.setText(director);

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(31, 242, 61, 17);
		label_3.setText("片长：");

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(129, 239, 73, 23);
		text_3.setText(movie_time);

		Label lblMin = new Label(shell, SWT.NONE);
		lblMin.setBounds(208, 242, 61, 17);
		lblMin.setText("min");

		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(31, 293, 61, 17);
		label_4.setText("语言：");

		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setBounds(129, 290, 88, 25);
		combo.add("汉语");
		combo.add("英语");
		combo.setText(movie_lan);

		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setBounds(318, 10, 61, 17);
		label_9.setText("影片简介：");

		text_5 = new Text(shell, SWT.BORDER | SWT.WRAP);
		text_5.setBounds(329, 45, 221, 250);
		text_5.setText(infor);

		final Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/" + str));
		lblNewLabel.setBounds(646, 48, 140, 196);

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {

			@Override
			/**
			 * @描述: 选择海报
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {

				FileDialog fd = new FileDialog(shell);// 创建一个可打开的文件选择框
				source = fd.open();
				System.out.println("source:---" + source);
				if (source != null) {
					// 实现照片预览
					lblNewLabel.setImage(SWTResourceManager.getImage(source));// 注意，不要使用设置背景图片setBackgroundImage()方法
					// 跟source变量的值一样
					// String fileNamessString =
					// fd.getFilterPath()+File.separator+fd.getFileName();
				}

			}
		});

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 上传
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {

				/* 图片上传至 工程的icons目录下 */
				File sourceFile = new File(source);// 通过源图片的完整路径 创建源文件对象

				/* 获得icons目录的位置 */
				URL url = Activator.getDefault().getBundle()
						.getResource("icons");
				String str = "";
				try {
					str = FileLocator.toFileURL(url).toString().substring(6);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				/* 生成上传后的文件名称 */
				fileName = new Date().getTime() + ""
						+ (int) (Math.random() * 100) + ".jpg";
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

			}
		});
		button_2.setBounds(732, 268, 80, 27);
		button_2.setText("确定");

		button_1.setBounds(629, 268, 80, 27);
		button_1.setText("\u9009\u62E9\u6D77\u62A5");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {

			/**
			 * @描述: 判断影片是否存在
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public boolean panduan() {
				boolean a = false;
				List<Map<String, Object>> list = db
						.query("select movie_type from movietype");
				for (Map<String, Object> map : list) {
					if (text.getText().trim()
							.equals(map.get("movie_type").toString())) {

						a = true;
						break;
					} else {
						a = false;
					}

				}
				return a;
			}

			@Override
			/**
			 * @描述: 修改影片
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {

				MessageBox box = new MessageBox(shell);

				if (panduan()) {
					box.setText("系统消息");
					box.setMessage("对不起，该电影已存在！");
					box.open();
				} else if (!StringRegexUtils.isRegexpValidate(text_3.getText(),
						StringRegexUtils.integer_regexp)) {
					box.setText("系统消息");
					box.setMessage("对不起，片长必须是整型数！");
					box.open();
				} else {
					int i = db.update("update movieinfo set movie_name = '"
							+ text.getText() + "' , " + "nation = '"
							+ text_1.getText() + "' ," + " director = '"
							+ text_2.getText() + "'," + "movie_time = '"
							+ text_3.getText() + "',movie_lan = '"
							+ combo.getText() + "'," + "infor = '"
							+ text_5.getText() + "' ," + "actor ='"
							+ text_7.getText() + "',movie_img = '" + fileName
							+ "' where id = '" + id + "'");
					if (i != 0) {
						box.setText("系统消息");
						box.setMessage("编辑成功！");
						box.open();
						result = "成功";
						// 关闭弹出窗口的方法
						shell.dispose();
					} else {
						box.setText("系统消息");
						box.setMessage("编辑失败请继续编辑！");
						box.open();
					}
				}

			}
		});
		button.setBounds(760, 431, 80, 27);
		button.setText("保存");

	}
}
