package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;

public class UserManager extends Dialog {

	protected Object result;
	protected Shell shell;
	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public UserManager(Shell parent, int style) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/zhuce.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(450, 292);
		shell.setText("\u7528\u6237\u7BA1\u7406");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述:删除用户
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				RemoveUser ru = new RemoveUser(shell, SWT.NONE);
				ru.open();
			}

			private Shell getShell() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		button.setBounds(168, 61, 80, 27);
		button.setText("\u5220\u9664\u7528\u6237");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 添加用户
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				Sign s = new Sign(shell, SWT.NONE);
				s.open();

			}
		});
		button_1.setBounds(168, 154, 80, 27);
		button_1.setText("\u6DFB\u52A0\u7528\u6237");

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			// 查看所有用户
			public void widgetSelected(SelectionEvent e) {
			}
		});
		/*
		 * button_2.setBounds(168, 181, 80, 27);
		 * button_2.setText("\u67E5\u770B\u6240\u6709\u7528\u6237");
		 * 
		 * Combo combo = new Combo(shell, SWT.READ_ONLY); combo.setBounds(33,
		 * 44, 88, 25); List<Map<String,Object>> list =
		 * db.query("select NAME from user not in('admin')"); for (int i =
		 * 0;i<list.size();i++) { combo.add(list.get(i).get("NAME").toString());
		 * } combo.select(0);
		 */

	}
}
