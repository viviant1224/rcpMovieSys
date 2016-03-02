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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class Wangjimima extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Wangjimima(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong",
				"icons/657a3ba64f0fb90464c3c00bd572c858.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(459, 325);
		shell.setText("\u627E\u56DE\u5BC6\u7801");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label.setBounds(11, 31, 85, 26);
		label.setText("\u60A8\u7684\u7528\u6237\u540D\uFF1A");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(168, 30, 73, 23);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_1.setBounds(10, 100, 109, 17);
		label_1.setText("\u4F60\u6700\u559C\u6B22\u7684\u989C\u8272\uFF1A");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(168, 99, 73, 23);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_2.setBounds(194, 165, 96, 17);
		label_2.setText("\u4F60\u6700\u559C\u6B22\u7684\u7535\u5F71:");

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(354, 164, 73, 23);

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		label_3.setBounds(194, 218, 61, 17);
		label_3.setText("\u4F60\u7684\u4F4F\u5740\uFF1A");

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(354, 217, 73, 23);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {

			public String panduan() {
				String name = null;
				List<Map<String, Object>> list = db
						.query("select NAME from user");
				for (Map<String, Object> map : list) {
					if (text.getText().trim()
							.equals(map.get("NAME").toString())) {
						name = (String) map.get("NAME");
					}
				}

				return name;
			}

			@Override
			/**
			 * @描述: 找回密码
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				String pw;
				String f_color;
				String f_movie;
				String address;
				MessageBox box = new MessageBox(new Shell());

				if (panduan() == null) {
					box.setText("系统消息");
					box.setMessage("对不起，该用户不存在！");
					box.open();
				} else {
					List<Map<String, Object>> i = db
							.query("select f_color,address,f_movie,pw from user where NAME='"
									+ text.getText() + "'");
					f_color = i.get(0).get("f_color").toString();
					f_movie = i.get(0).get("f_movie").toString();
					address = i.get(0).get("address").toString();
					pw = i.get(0).get("pw").toString();

					if (text_1.getText().equals(f_color)
							&& text_3.getText().equals(address)
							&& text_2.getText().equals(f_movie)) {

						box.setText("系统消息");
						box.setMessage("您的密码为：" + pw + ",请妥善保管！");
						box.open();

					} else {
						box.setText("系统消息");
						box.setMessage("验证信息填写错误！");
						box.open();
					}
				}

			}

		});
		button.setBounds(354, 270, 80, 27);
		button.setText("\u786E\u5B9A");

	}
}
