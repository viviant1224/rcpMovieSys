package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class Sign extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Sign(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
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
		shell.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/zhuce.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(578, 554);
		shell.setText("\u6CE8\u518C");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(52, 63, 61, 32);
		label.setText("\u7528\u6237\u540D\uFF1A");

		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text.setBounds(191, 60, 117, 35);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(52, 160, 61, 40);
		label_1.setText("\u5BC6\u7801\uFF1A");

		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_1.setBounds(191, 157, 117, 32);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setBounds(52, 206, 117, 32);
		label_2.setText("\u518D\u6B21\u8F93\u5165\u5BC6\u7801\uFF1A");

		text_2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_2.setBounds(191, 203, 117, 35);

		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_4.setBounds(52, 117, 61, 37);
		label_4.setText("\u6027\u522B\uFF1A");

		Button button = new Button(shell, SWT.RADIO);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setBounds(191, 111, 33, 32);
		button.setText("\u7537");

		Button button_1 = new Button(shell, SWT.RADIO);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_1.setBounds(275, 110, 33, 34);
		button_1.setText("\u5973");

		Group group = new Group(shell, SWT.NONE);
		group.setText("\u4EE5\u4E0B\u4FE1\u606F\u4E3A\u9632\u6B62\u5BC6\u7801\u5FD8\u8BB0\u65F6\u5907\u7528\uFF1A");
		group.setBounds(52, 264, 412, 252);

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setBounds(10, 39, 126, 23);
		label_3.setText("\u4F60\u559C\u6B22\u7684\u989C\u8272\uFF1A");

		text_3 = new Text(group, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_3.setBounds(142, 39, 116, 37);

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(10, 96, 104, 23);
		label_5.setText("\u4F60\u7684\u51FA\u751F\u5730\uFF1A");

		text_4 = new Text(group, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_4.setBounds(142, 96, 116, 37);

		Label label_6 = new Label(group, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setBounds(10, 167, 126, 32);
		label_6.setText("\u4F60\u6700\u559C\u6B22\u7684\u7535\u5F71\uFF1A");

		text_5 = new Text(group, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_5.setBounds(142, 161, 116, 38);

		Button button_2 = new Button(group, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_2.addSelectionListener(new SelectionAdapter() {
			// 判断是否重名
			public boolean panduan() {
				boolean a = true;
				List<Map<String, Object>> list = db
						.query("select NAME from user ");
				for (Map<String, Object> map : list) {
					if (text.getText().trim()
							.equals(map.get("NAME").toString())) {

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
			 * @描述: 注册用户
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());

				if (panduan()) {
					box.setText("系统消息");
					box.setMessage("您注册的用户名已存在，请重新填写用户名！");
					box.open();
				} else if (text.getText().trim().length() == 0
						|| text_1.getText().trim().length() == 0
						|| text_2.getText().trim().length() == 0
						|| text_3.getText().trim().length() == 0
						|| text_4.getText().trim().length() == 0
						|| text_5.getText().trim().length() == 0) {
					box.setText("系统消息");
					box.setMessage("请填完整您的信息！");
					box.open();
				} else {

					if (!text_1.getText().equals(text_2.getText())) {
						box.setText("系统消息");
						box.setMessage("两次输入的密码不一致！");
						box.open();
					} else {
						int i = db
								.update("insert into user(NAME,pw,f_color,address,f_movie)values('"
										+ text.getText()
										+ "',"
										+ "'"
										+ text_2.getText()
										+ "','"
										+ text_3.getText()
										+ "','"
										+ text_4.getText()
										+ "','"
										+ text_5.getText() + "')");
						if (i != 0) {
							box.setText("系统消息");
							box.setMessage("注册成功！");
							box.open();
							text.setText("");
							text_1.setText("");
							text_2.setText("");
							text_3.setText("");
							text_5.setText("");
							text_4.setText("");
						} else {
							box.setText("系统消息");
							box.setMessage("注册失败！");
							box.open();
							result = "成功";
						}
					}

				}
			}
		});
		button_2.setBounds(311, 202, 91, 40);
		button_2.setText("\u6CE8\u518C");

	}
}
