package cn.com.shxt.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import cn.com.shxt.dialog.Login;
import cn.com.shxt.dialog.SearchSeat;
import cn.com.shxt.dialog.ShowRoomSet;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;
import org.eclipse.swt.widgets.Button;

public class SearchShowRoom extends ViewPart {

	public static final String ID = "cn.com.shxt.view.SearchShowRoom"; //$NON-NLS-1$
	private Table table;
	private DbUtils db = new DbUtils();

	public SearchShowRoom() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/m2.jpg"));
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(410, 135, 467, 225);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(66);
		tableColumn.setText("序号");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(90);
		tblclmnNewColumn.setText("名称");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("列数");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("类型");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(106);
		tableColumn_2.setText("排数");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("New TableItem");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			//删除放映厅
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				TableItem  tableItem  = table.getItem(table.getSelectionIndex());
				String id = tableItem.getText(0);
				if(!Login.role.equals("管理员")) {
					box.setText("系统消息");
					box.setMessage("对不起，你没有该权限！");
					box.open();
				} else {
					List<Map<String,Object>> list = db.query("select roomname from showroom where id = '"+id+"'");
					String name = list.get(0).get("roomname").toString();
					boolean flag = MessageDialog.openConfirm(RcpUtil.getPage()
							.getWorkbenchWindow().getShell(), "系统消息", "确定删除该放映厅吗？");
					if (flag == true) {
						int i = db.update("delete from showroom where id = '"+id+"'");
						int j = db.update("delete from zuowei where roomname = '"+name+"'");
						int k = db.update("delete from fangying where roomname = '"+name+"'");
						if(i!=0) {
							box.setText("系统消息");
							box.setMessage("删除成功！");
							box.open();
						} else {
							box.setText("系统消息");
							box.setMessage("删除失败！");
							box.open();
						}
					}
				}
			
				
				
			}
		});
		menuItem.setText("\u5220\u9664\u653E\u6620\u5385");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			//修改放映厅
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if(!Login.role.equals("管理员")) {
					box.setText("系统消息");
					box.setMessage("对不起，你没有该权限！");
					box.open();
				} else {
					TableItem  tableItem  = table.getItem(table.getSelectionIndex());
					String id = tableItem.getText(0);
					ShowRoomSet srs = new ShowRoomSet(container.getShell(), SWT.NONE);
					Object result = srs.open(id);
					if(result != null && result.toString().equals("成功")){
						getList();
					}
				}
				
				
			}
		});
		menuItem_1.setText("\u4FEE\u6539\u653E\u6620\u5385");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			//查看座位表
			public void widgetSelected(SelectionEvent e) {
			
				
				TableItem  tableItem  = table.getItem(table.getSelectionIndex());
				String id = tableItem.getText(0);
				String roomname = tableItem.getText(1);
				SearchSeat ss = new SearchSeat(container.getShell(), SWT.NONE);
				ss.open(id,roomname);
			}
		});
		menuItem_2.setText("查看座位表");
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			//刷新
			public void widgetSelected(SelectionEvent e) {
				getList();
			}
		});
		button.setBounds(797, 102, 80, 27);
		button.setText("刷新");
		
		getList();
		
		createActions();
		initializeToolBar();
		initializeMenu();
	}
	public void getList() {
		table.removeAll();
		List<Map<String,Object>> list = db.query("select id,roomname,lie,row,type from showroom");
		for(int i = 0;i<list.size();i++) {
			Map<String,Object> map = list.get(i);
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0,map.get("id").toString());
			tableItem.setText(1,map.get("roomname").toString());
			tableItem.setText(2,map.get("lie").toString());
			tableItem.setText(3,map.get("type").toString());
			tableItem.setText(4,map.get("row").toString());
		}
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
