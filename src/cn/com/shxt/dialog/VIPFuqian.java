package cn.com.shxt.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.StringRegexUtils;
import org.eclipse.wb.swt.ResourceManager;

public class VIPFuqian extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private double price;
	private DbUtils db = new DbUtils();
	private String movie_name;
	private String showtime;
	private List myList = new ArrayList();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public VIPFuqian(Shell parent, int style) {
		super(parent, style);
		setText("付钱");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open(double price, String movie_name, List myList,String showtime) {
		this.showtime = showtime;
		this.myList = myList;
		this.price = price;
		this.movie_name = movie_name;
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
				"rcpyingyuanxitong", "icons/fuqian.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(563, 340);
		shell.setText(getText());

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label.setBounds(153, 87, 103, 37);
		label.setText("\u5E94\u4ED8\u91D1\u989D\uFF1A");

		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text.setBounds(262, 84, 73, 37);
		text.setText("" + price);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_1.setBounds(153, 145, 103, 26);
		label_1.setText("\u5B9E\u6536\u91D1\u989D\uFF1A");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text_1.setBounds(262, 142, 73, 37);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_2.setBounds(153, 205, 103, 37);
		label_2.setText("\u627E\u96F6\uFF1A");

		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text_2.setBounds(262, 202, 73, 33);

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(355, 94, 30, 17);
		label_3.setText("\u5143");

		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u5143");
		label_4.setBounds(355, 152, 30, 17);

		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u5143");
		label_5.setBounds(355, 212, 30, 17);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 确认
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if (!StringRegexUtils.isRegexpValidate(text_1.getText(),
						StringRegexUtils.positive_rational_numbers_regexp)) {
					box.setText("系统消息");
					box.setMessage("对不起，您输入钱币的格式不对！");
					box.open();
				} else {
					double fu = Double.parseDouble(text_1.getText());
					if (fu < price) {
						box.setText("系统消息");
						box.setMessage("对不起，您付的金额不够！");
						box.open();
					} else {
						double zhaoling = fu - price;
						String zl = String.valueOf(zhaoling);
						text_2.setText(zl);
						String str = myList.get(0).toString();
						String[] str1 = str.split(" ");

						Chupiao c = new Chupiao(shell, SWT.NONE);
						c.open(movie_name, price, str);

						List<Map<String, Object>> list = db
								.query("select showtime,hour_time,min_time from fangying where movie_name = '"
										+ movie_name + "'");
						String time = list.get(0).get("showtime").toString();
						String hour = list.get(0).get("hour_time").toString();
						String min = list.get(0).get("min_time").toString();
						db.update("update sell set state = '不可用',sell_price = '"
								+ price
								+ "' where showtime = '"+showtime+"' and movie_name = '"
								+ movie_name
								+ "' and row = '"
								+ str1[0]
								+ "' and lie = '"
								+ str1[1] + "'");
						box.setText("系统消息");
						box.setMessage("成功购买1张---" + movie_name
								+ "---的影票。上映时间为：" + time + "  " + hour + ":"
								+ min);
						box.open();

					}
				}

			}
		});
		button.setBounds(435, 256, 80, 27);
		button.setText("\u786E\u8BA4");

		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setBounds(472, 10, 61, 17);
		label_6.setText("\u6536\u94F6\u5458\uFF1A");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(472, 44, 61, 17);
		lblNewLabel.setText(Login.name);

	}
}
