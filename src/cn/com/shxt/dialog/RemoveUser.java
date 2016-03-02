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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;
import org.eclipse.wb.swt.ResourceManager;

public class RemoveUser extends Dialog {

	protected Object result;
	protected Shell shell;
	private Button button;
	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RemoveUser(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
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
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/caidan.jpg"));
		shell.setSize(450, 300);
		shell.setText("\u5220\u9664\u7528\u6237");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel.setBounds(40, 65, 149, 20);
		lblNewLabel.setText("\u8981\u5220\u9664\u7528\u6237\u7684\u540D\u5B57\uFF1A");
		
		final Combo combo = new Combo(shell, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo.setBounds(205, 62, 120, 25);
		List<Map<String,Object>> list = db.query("SELECT NAME FROM USER WHERE NAME NOT IN ('admin') ");
		for(int i = 0;i<list.size();i++) {
			combo.add(list.get(i).get("NAME").toString());
		}
		combo.select(0);
		
		button = new Button(shell, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 删除用户
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				boolean flag = MessageDialog.openConfirm(RcpUtil.getPage().getWorkbenchWindow().getShell(), "系统消息", "确定删除该员工吗？");
				
				if(flag==true) {
					db.update("delete from user where NAME = '"+combo.getText()+"'");
					box.setText("系统消息");
					box.setMessage("删除成功！");
					box.open();	
		
				}
			}
		});
		button.setBounds(170, 208, 80, 27);
		button.setText("\u5220\u9664\u7528\u6237");
		
		

	}
}
