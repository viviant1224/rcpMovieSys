package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.ResourceManager;

public class SearchUser extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SearchUser(Shell parent, int style) {
		super(parent, style);
		setText("查看用户");
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
		shell.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/zhuce.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(455, 369);
		shell.setText(getText());
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(76, 80, 282, 190);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(72);
		tableColumn.setText("\u59D3\u540D");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u4F4F\u5740");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u89D2\u8272");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("New TableItem");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(92, 21, 295, 30);
		label.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662\u7CFB\u7EDF\u5404\u5DE5\u4F5C\u4EBA\u5458\u540D\u5355");
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			//删除人员
			public void widgetSelected(SelectionEvent e) {
				RemoveUser ru = new RemoveUser(shell, SWT.NONE);
				ru.open();
			}
		});
		button.setBounds(76, 311, 80, 27);
		button.setText("\u5220\u9664\u4EBA\u5458");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			//添加人员
			public void widgetSelected(SelectionEvent e) {
				Sign s = new Sign(shell, SWT.NONE);
				s.open();
			}
		});
		button_1.setBounds(278, 311, 80, 27);
		button_1.setText("\u6DFB\u52A0\u4EBA\u5458");
		getUser();

	}
	//获取工作人员
	public void getUser() {
		table.removeAll();
		List<Map<String,Object>> list = db.query("select NAME,role,address from user");
		for(int i = 0;i<list.size();i++) {
			Map<String, Object> map = list.get(i);
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, map.get("NAME").toString());
			tableItem.setText(1, map.get("address").toString());
			tableItem.setText(2, map.get("role").toString());
			if(map.get("role").equals("管理员")) {
				tableItem.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_LIST_SELECTION));
			}
		}
		
	}
}
