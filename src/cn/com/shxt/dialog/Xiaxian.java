package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class Xiaxian extends Dialog {

	protected Object result;
	protected Shell shell;
	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Xiaxian(Shell parent, int style) {
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
		shell = new Shell(getParent(), SWT.CLOSE);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/xiaxian.jpg"));
		shell.setSize(622, 373);
		shell.setText("\u5F71\u7247\u4E0B\u7EBF");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label.setBounds(29, 47, 152, 21);
		label.setText("\u9009\u62E9\u4E0B\u7EBF\u7684\u7535\u5F71\uFF1A");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_1.setBounds(29, 149, 111, 17);
		label_1.setText("\u4E0B\u7EBF\u65F6\u95F4\uFF1A");

		final DateTime dateTime = new DateTime(shell, SWT.BORDER
				| SWT.DROP_DOWN | SWT.CALENDAR);
		dateTime.setBackgroundMode(SWT.INHERIT_DEFAULT);
		dateTime.setBounds(146, 135, 226, 167);

		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(197, 45, 175, 25);
		List<Map<String, Object>> list = db
				.query("select movie_name from movieinfo where state = '上映'");
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				combo.add(list.get(i).get("movie_name").toString());
			}
			combo.select(0);
		}

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 下线电影
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				List<Map<String, Object>> list = db
						.query("select showtime from fangying where movie_name = '"
								+ combo.getText() + "'");
				String showtime = list.get(0).get("showtime").toString();
				String time = dateTime.getYear() + "-"
						+ (dateTime.getMonth() + 1) + "-" + dateTime.getDay();
				System.out.println("----------" + time);
				MessageBox box = new MessageBox(new Shell());
				boolean flag = MessageDialog.openConfirm(RcpUtil.getPage()
						.getWorkbenchWindow().getShell(), "系统消息", "确定将该影片下线吗？");
				if (flag == true) {
					int i = db
							.update("update movieinfo set state = '下线',endtime = '"
									+ time
									+ "'where movie_name = '"
									+ combo.getText() + "'");
					int j = db
							.update("delete from fangying where movie_name = '"
									+ combo.getText() + "'");
					int k = db
							.update("update sell set state_1 = '下线' where movie_name = '"
									+ combo.getText()
									+ "' and showtime = '"
									+ showtime + "'");
					// int k =
					// db.update("update sell set state = '下线' where movie_name = '"+combo.getText()+"' and showtime = '"+combo.getText()+"'");
					if (i != 0 && j != 0&&k!=0) {
						box.setText("系统消息");
						box.setMessage("影片成功下线！");
						box.open();
					} else {
						box.setText("系统消息");
						box.setMessage("影片下线失败！");
						box.open();
					}
				}

			}
		});
		button.setBounds(414, 308, 80, 27);
		button.setText("下线");

	}
}
