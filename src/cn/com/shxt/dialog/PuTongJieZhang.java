package cn.com.shxt.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;

public class PuTongJieZhang extends Dialog {

	protected Object result;
	protected Shell shell;
	private double price;
	private String movie_name;
	private String showtime;
	private int count;
	private DbUtils db = new DbUtils();
	private List myList = new ArrayList();;
	private double lastPrice;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public PuTongJieZhang(Shell parent, int style) {
		super(parent, style);
		setText("结账与取消订单");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open(String price, int count, String movie_name, List myList,String showtime) {
		// this.s1 = s1;
		// this.s2 = s2;
		this.myList = myList;
		this.movie_name = movie_name;
		this.showtime = showtime;
		this.price = Double.parseDouble(price);
		this.count = count;
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
		shell.setSize(450, 289);
		shell.setText(getText());

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel.setBounds(28, 22, 84, 21);
		lblNewLabel.setText("\u6B64\u6B21\u5171\u8D2D\u4E70");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setBounds(124, 22, 11, 17);
		lblNewLabel_1.setText(String.valueOf(count));

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_2.setBounds(144, 22, 113, 26);
		lblNewLabel_2.setText("\u5F20\u5EA7\u4F4D\u7968\uFF0C\u5171\u8BA1");

		lastPrice = count * price;

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_3.setBounds(263, 22, 38, 17);
		lblNewLabel_3.setText(String.valueOf(lastPrice));

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_4.setBounds(307, 22, 23, 21);
		lblNewLabel_4.setText("\u5143");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(28, 71, 84, 32);
		label_1.setText("\u5EA7\u4F4D\u53F7\u4E3A\uFF1A");

		Label lblNewLabel_6 = new Label(shell, SWT.WRAP);
		lblNewLabel_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_6.setBounds(118, 71, 230, 92);
		lblNewLabel_6.setText("" + myList);

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_5.setBounds(28, 169, 165, 26);
		lblNewLabel_5
				.setText("\u7EE7\u7EED\u652F\u4ED8\u8BF7\u70B9\u51FB\u6B64\u5904\uFF1A");

		Link link = new Link(shell, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 支付
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {

				Fuqian f = new Fuqian(shell, SWT.NONE);
				System.out.println("---------------" + myList.size());
				f.open(lastPrice, movie_name, count, price, myList,showtime);

			}
		});
		link.setBounds(208, 169, 29, 17);
		link.setText("<a>\u652F\u4ED8</a>");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(28, 201, 165, 38);
		label.setText("\u53D6\u6D88\u8BA2\u5355\u8BF7\u70B9\u51FB\u6B64\u5904\uFF1A");

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
		link_1.setBounds(208, 201, 29, 17);
		link_1.setText("<a>\u53D6\u6D88</a>");

	}
}
