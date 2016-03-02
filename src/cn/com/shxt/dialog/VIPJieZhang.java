package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;

public class VIPJieZhang extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private DbUtils db = new DbUtils();
	private String movie_name;
	private String showtime;
	private String price;
	private double mon;
	private List myList;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public VIPJieZhang(Shell parent, int style) {
		super(parent, style);
		setText("VIP结账");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open(String movie_name, String price, List myList,String showtime) {
		this.showtime = showtime;
		this.myList = myList;
		this.movie_name = movie_name;
		this.price = price;
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
		shell.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/zhuce.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(450, 300);
		shell.setText(getText());

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		lblNewLabel.setBounds(39, 22, 146, 27);
		lblNewLabel.setText("请输入VIP编号：");

		final Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_1
				.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_1.setBounds(98, 67, 64, 23);

		final Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_4.setBounds(356, 145, 30, 27);

		final Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_3.setBounds(211, 96, 118, 30);
		lblNewLabel_3.setText(movie_name);

		final Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_6.setBounds(146, 132, 61, 27);
		lblNewLabel_6.setText("" + myList);

		text = new Text(shell, SWT.BORDER);
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text.setBounds(202, 22, 127, 27);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public boolean getId() {
				boolean a = false;
				List<Map<String, Object>> list = db.query("select id from vip");
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).get("id").toString()
							.equals(text.getText().toString().trim())) {
						a = true;
						break;
					}

				}
				return a;
			}

			@Override
			// 进入VIP个人用户
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if (getId()) {
					String houzui = null;
					List<Map<String, Object>> list_1 = db
							.query("select sex,vip_name,role from vip where id = '"
									+ text.getText() + "'");
					String name = list_1.get(0).get("vip_name").toString();
					String xing = name.substring(0, 1);
					mon = Double.parseDouble(price);
					if (list_1.get(0).get("role").toString().equals("星级")) {
						mon = mon * 0.9;
					} else if (list_1.get(0).get("role").toString()
							.equals("月亮级")) {
						mon = mon * 0.85;
					} else if (list_1.get(0).get("role").toString()
							.equals("太阳级")) {
						mon = mon * 0.75;
					}
					if (list_1.get(0).get("sex").toString().equals("男")) {
						houzui = "先生";
					} else if (list_1.get(0).get("sex").toString().equals("女")) {
						houzui = "女士";
					}
					String chenghu = "" + xing + houzui;
					lblNewLabel_1.setText(chenghu);
					lblNewLabel_4.setText("" + mon);

				} else {
					box.setText("系统消息");
					box.setMessage("对不起，没有该VIP编号");
					box.open();
				}
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		button.setBounds(337, 22, 80, 27);
		button.setText("\u8FDB\u5165");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(39, 67, 48, 23);
		label.setText("\u4EB2\u7231\u7684");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(39, 96, 130, 30);
		label_1.setText("\u60A8\u672C\u6B21\u8D2D\u4E70\u4E86\u4E00\u5F20");

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(175, 100, 30, 17);
		lblNewLabel_2.setText("------");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("------");
		label_2.setBounds(335, 100, 30, 17);

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setBounds(374, 99, 60, 27);
		label_3.setText("\u7684\u5F71\u7968");

		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_4.setBounds(290, 145, 60, 30);
		label_4.setText("\u9700\u652F\u4ED8\uFF1A");

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_5.setBounds(409, 145, 25, 27);
		lblNewLabel_5.setText("\u5143");

		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(39, 210, 168, 30);
		label_5.setText("\u53D6\u6D88\u8BA2\u5355\u8BF7\u70B9\u51FB\u8FD9\u91CC\uFF1A");

		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setBounds(39, 171, 168, 33);
		label_6.setText("\u7EE7\u7EED\u652F\u4ED8\u8BF7\u70B9\u51FB\u8FD9\u91CC\uFF1A");

		Link link = new Link(shell, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 付钱
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				VIPFuqian vf = new VIPFuqian(shell, SWT.NONE);

				vf.open(mon, movie_name, myList,showtime);
				Maipiao.myList.removeAll(myList);

			}
		});
		link.setBounds(211, 171, 53, 17);
		link.setText("<a>\u652F\u4ED8</a>");

		Link link_1 = new Link(shell, SWT.NONE);
		link_1.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 取消订单
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {

				MessageBox box = new MessageBox(new Shell());
				int a = 0;
				for (int i = 0; i < myList.size(); i++) {
					String str = myList.get(i).toString();
					String str1[] = str.split(" ");
					a = db.update("update sell set state = '可用' where showtime = '"+showtime+"' and movie_name = '"
							+ movie_name
							+ "'"
							+ " and row = '"
							+ str1[0]
							+ "' and lie = '" + str1[1] + "'");
					System.out.println("=====" + str1[0] + " " + str1[1]);
					a++;

				}
				// System.out.println("====="+s1+" "+s2);
				// int i =
				// db.update("update sell set state = '可用' where movie_name = '"+movie_name+"' and row = '"+s1+"' and lie = '"+s2+"'");
				if (a != 0) {
					box.setText("系统消息");
					box.setMessage("取消订单成功");
					box.open();
					Maipiao.myList.removeAll(myList);
					shell.dispose();

				}

			}
		});
		link_1.setBounds(211, 210, 53, 17);
		link_1.setText("<a>\u53D6\u6D88</a>");

		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_7.setBounds(39, 132, 88, 27);
		label_7.setText("\u5EA7\u4F4D\u53F7\u4E3A\uFF1A");
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_8.setBounds(168, 67, 36, 17);
		label_8.setText(":");

	}
}
