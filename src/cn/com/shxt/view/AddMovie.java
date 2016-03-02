package cn.com.shxt.view;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
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

public class AddMovie extends ViewPart {

	public static final String ID = "cn.com.shxt.view.AddMovie"; //$NON-NLS-1$
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private DbUtils db = new DbUtils();
	private DateTime dateTime;
	private DateTime dateTime_1;

	private String fileName;
	private String source;
	private String target;
	private Text text_6;

	public AddMovie() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/view12.jpg"));
		{
			Label label = new Label(container, SWT.NONE);
			label.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
			label.setBounds(133, 66, 80, 17);
			label.setText("\u5F71\u7247\u540D\uFF1A");
		}

		text = new Text(container, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		text.setBounds(255, 63, 140, 23);

		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label.setBounds(133, 147, 61, 17);
		label.setText("\u56FD\u5BB6\uFF1A");

		text_1 = new Text(container, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		text_1.setBounds(255, 144, 88, 23);

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager
				.getFont("华文行楷", 14, SWT.NORMAL));
		lblNewLabel_1.setBounds(133, 238, 80, 17);
		lblNewLabel_1.setText("\u4E3B\u6F14\uFF1A");

		text_6 = new Text(container, SWT.BORDER | SWT.WRAP);
		text_6.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		text_6.setBounds(255, 217, 140, 72);

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_1.setBounds(133, 318, 61, 17);
		label_1.setText("\u5BFC\u6F14\uFF1A");

		text_2 = new Text(container, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		text_2.setBounds(255, 315, 140, 23);

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_2.setBounds(133, 398, 61, 17);
		label_2.setText("片长：");

		text_3 = new Text(container, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		text_3.setBounds(255, 398, 88, 23);

		Label lblMin = new Label(container, SWT.NONE);
		lblMin.setBounds(349, 402, 61, 17);
		lblMin.setText("min");

		Label label_3 = new Label(container, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_3.setBounds(133, 472, 61, 17);
		label_3.setText("\u8BED\u8A00\uFF1A");

		final Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		combo.setBounds(255, 469, 88, 25);
		combo.add("汉语");
		combo.add("英语");
		combo.select(0);

		Label label_8 = new Label(container, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_8.setBounds(443, 66, 95, 17);
		label_8.setText("\u5F71\u7247\u7B80\u4ECB\uFF1A");

		text_5 = new Text(container, SWT.BORDER | SWT.WRAP);
		text_5.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		text_5.setBounds(453, 89, 226, 166);

		Button button = new Button(container, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		final Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/alt_about.gif"));
		lblNewLabel.setBounds(846, 92, 128, 161);

		Button button_1 = new Button(container, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			// 浏览图片
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(container.getShell(), SWT.OPEN);// 创建一个可打开的文件选择框
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
		button_1.setBounds(866, 262, 80, 27);
		button_1.setText("预览");

		Button button_2 = new Button(container, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			// 上传
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
						+ (int) (Math.random() * 100000000) + ".jpg";
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
		button_2.setBounds(866, 295, 80, 27);
		button_2.setText("\u4E0A\u4F20");
		button.addSelectionListener(new SelectionAdapter() {

			// 判断电影是否存在过
			public boolean panduan() {
				boolean a = false;
				List<Map<String, Object>> list = db
						.query("select movie_name from movieinfo ");
				for (Map<String, Object> map : list) {
					if (text.getText().trim()
							.equals(map.get("movie_name").toString())) {

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
			 * @描述: 添加影片
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());

				if (panduan()) {
					box.setText("系统消息");
					box.setMessage("对不起，该电影已存在！");
					box.open();
				} else if (text.getText().trim().length() == 0
						|| text_1.getText().trim().length() == 0
						|| text_2.getText().trim().length() == 0
						|| text_3.getText().trim().length() == 0
						|| text_6.getText().trim().length() == 0) {
					box.setText("系统消息");
					box.setMessage("对不起，请填完整您所要纳入的信息！");
					box.open();
				} else if (!StringRegexUtils.isRegexpValidate(text_3.getText(),
						StringRegexUtils.integer_regexp)) {
					box.setText("系统消息");
					box.setMessage("对不起，片长必须是整型数！");
					box.open();
				} else {
					int i = db
							.update("insert into movieinfo(movie_name,nation,actor,director,movie_time,movie_lan,"
									+ "infor,movie_img)values"
									+ "('"
									+ text.getText()
									+ "','"
									+ text_1.getText()
									+ "','"
									+ text_6.getText()
									+ "','"
									+ text_2.getText()
									+ "',"
									+ "'"
									+ text_3.getText()
									+ "','"
									+ combo.getText()
									+ "','"
									+ text_5.getText()
									+ "',"
									+ "'"
									+ fileName
									+ "')");
					if (i != 0) {
						box.setText("系统消息");
						box.setMessage("添加成功！");
						box.open();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						combo.select(0);
						text_5.setText("");
						text_6.setText("");
					} else {
						box.setText("系统消息");
						box.setMessage("添加失败！");
						box.open();
					}

				}
			}

		});
		button.setBounds(846, 468, 80, 27);
		button.setText("\u786E\u8BA4\u6DFB\u52A0");

		Label label_9 = new Label(container, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_9.setBounds(754, 66, 88, 17);
		label_9.setText("\u5F71\u7247\u6D77\u62A5\uFF1A");

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
