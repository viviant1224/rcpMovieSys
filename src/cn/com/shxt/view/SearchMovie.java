package cn.com.shxt.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

import cn.com.shxt.dialog.MovieSet;
import cn.com.shxt.dialog.ShowMovie;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;
import cn.com.shxt.tools.StringRegexUtils;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseAdapter;

public class SearchMovie extends ViewPart {

	public static final String ID = "cn.com.shxt.view.SearchMovie"; //$NON-NLS-1$
	private Table table;
	private DbUtils db = new DbUtils();
	private Text text;

	private int start = 1;
	private int end = 5;
	private int maxPage;

	// 分页所需四个按钮
	private Button first;
	private Button pre;
	private Button next;
	private Button last;
	private Button button_7;// 跳转
	private Text text_1;
	private Text text_2;

	public SearchMovie() {
	}

	public int getMaxPage() {
		List<Map<String, Object>> list = db
				.query("select * from movieinfo limit " + (start - 1) * end
						+ "," + end + "");
		int maxNum = db.query("select * from movieinfo").size();
		// 总页数
		maxPage = maxNum / end + (maxNum % end == 0 ? 0 : 1);
		return maxPage;
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/view12.jpg"));

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(131, 183, 647, 229);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(59);
		tblclmnId.setText("\u5E8F\u53F7");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(92);
		tableColumn.setText("\u7535\u5F71\u540D");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(61);
		tblclmnNewColumn.setText("\u56FD\u5BB6");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(123);
		tblclmnNewColumn_1.setText("\u5BFC\u6F14");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(64);
		tableColumn_1.setText("\u7247\u957F");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(56);
		tableColumn_2.setText("\u8BED\u8A00");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(188);
		tblclmnNewColumn_2.setText("主演");

		TableItem tableItem = new TableItem(table, SWT.NONE);

		// tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		tableItem.setText("New TableItem");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			// 删除影片
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				TableItem tableItem = table.getItem(table.getSelectionIndex());
				String id = tableItem.getText(0);
				boolean flag = MessageDialog.openConfirm(RcpUtil.getPage()
						.getWorkbenchWindow().getShell(), "系统消息", "确定删除该影片吗？");
				if (flag == true) {
					int i = db.update("delete from movieinfo where id = '" + id
							+ "'");
					if (i != 0) {
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
		});
		menuItem.setText("删除影片");

		MenuItem menuItem_3 = new MenuItem(menu, SWT.NONE);
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			// 查看详情信息
			public void widgetSelected(SelectionEvent e) {

				TableItem tableItem = table.getItem(table.getSelectionIndex());

				String movie_name = tableItem.getText(1);

				ShowMovie sm = new ShowMovie(container.getShell(), SWT.NONE);
				sm.open(movie_name);
			}
		});
		menuItem_3.setText("\u67E5\u770B\u5F71\u7247\u8BE6\u7EC6\u4FE1\u606F");

		MenuItem menuItem_4 = new MenuItem(menu, SWT.NONE);
		menuItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			// 修改影片
			public void widgetSelected(SelectionEvent e) {

				TableItem tableItem = table.getItem(table.getSelectionIndex());
				String id = tableItem.getText(0);
				MovieSet ms = new MovieSet(container.getShell(), SWT.NONE);
				Object result = ms.open(id);
				if (result != null && result.toString().equals("成功")) {
					getPageList();
				}

			}
		});
		menuItem_4.setText("\u4FEE\u6539\u5F71\u7247\u4FE1\u606F");

		final Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			// 刷新
			public void widgetSelected(SelectionEvent e) {
				text_1.setText(String.valueOf(getMaxPage()));
				first.setEnabled(true);
				pre.setEnabled(false);
				next.setEnabled(true);
				last.setEnabled(true);
				getPageList();
				button.setText("刷新");
			}
		});
		button.setBounds(670, 64, 108, 40);
		button.setText("刷新");

		first = new Button(container, SWT.NONE);
		first.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 首页
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				start = 1;
				getPageList();
			}
		});
		first.setBounds(131, 453, 80, 27);
		first.setText("首页");

		pre = new Button(container, SWT.NONE);
		pre.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述:上一页
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				start--;
				getPageList();
			}
		});
		pre.setBounds(261, 453, 80, 27);
		pre.setText("上一页");

		next = new Button(container, SWT.NONE);
		next.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 下一页
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				start++;
				getPageList();
			}
		});
		next.setBounds(390, 453, 80, 27);
		next.setText("下一页");

		last = new Button(container, SWT.NONE);
		last.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 尾页
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				start = maxPage;
				getPageList();
			}
		});
		last.setBounds(527, 453, 80, 27);
		last.setText("尾页");

		Label label = new Label(container, SWT.NONE);
		label.setBounds(612, 458, 24, 17);
		label.setText("跳到");

		text = new Text(container, SWT.BORDER);
		text.setBounds(642, 455, 24, 23);

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(670, 458, 24, 17);
		label_1.setText("页");

		button_7 = new Button(container, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 跳转
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if (!StringRegexUtils.isRegexpValidate(text.getText(),
						StringRegexUtils.integer_regexp)) {
					box.setText("系统消息");
					box.setMessage("请输入范围内的数字！");
					box.open();
				} else {
					start = Integer.parseInt(text.getText().toString());
					if (start > maxPage) {
						box.setText("系统消息");
						box.setMessage("请输入范围内的数字！");
						box.open();
					} else {
						getPageList();
					}
				}

			}
		});
		button_7.setBounds(700, 453, 80, 27);
		button_7.setText("跳转");

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBounds(138, 160, 12, 17);
		label_2.setText("\u5171");

		text_1 = new Text(container, SWT.BORDER | SWT.READ_ONLY);
		text_1.setEnabled(false);
		text_1.setBounds(156, 154, 24, 23);
		text_1.setText(String.valueOf(getMaxPage()));

		Label label_3 = new Label(container, SWT.NONE);
		label_3.setBounds(186, 160, 12, 17);
		label_3.setText("\u9875");

		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 以图片形式查看
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				try {
					RcpUtil.getPage().showView(SearchMovieImg.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(134, 64, 108, 40);
		btnNewButton.setText("以图片形式查看");

		Label label_4 = new Label(container, SWT.WRAP);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_4.setBounds(305, 87, 302, 40);
		label_4.setText("\u84DD\u8272\u4EE3\u8868\u6B63\u5728\u4E0A\u6620\uFF0C\u7EA2\u8272\u4EE3\u8868\u5DF2\u4E0B\u7EBF\uFF0C\u9ED8\u8BA4\u4E3A\u672A\u4E0A\u6620");

		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 搜索影片
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				List<Map<String, Object>> list = db
						.query("select * from movieinfo where movie_name = '"
								+ text_2.getText() + "'");
				if (list.size() == 0) {
					box.setText("系统消息");
					box.setMessage("对不起，没有您要查询的影片！");
					box.open();
				} else {
					table.removeAll();
					Map<String, Object> map = list.get(0);
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, map.get("id").toString());
					tableItem.setText(1, map.get("movie_name").toString());
					tableItem.setText(2, map.get("nation").toString());
					tableItem.setText(3, map.get("director").toString());
					tableItem.setText(4, map.get("movie_time").toString());
					tableItem.setText(5, map.get("movie_lan").toString());
					tableItem.setText(6, map.get("actor").toString());
					if (map.get("state").toString().equals("上映")) {
						tableItem.setBackground(SWTResourceManager
								.getColor(SWT.COLOR_LIST_SELECTION));
					}
					if (map.get("state").toString().equals("下线")) {
						tableItem.setBackground(SWTResourceManager
								.getColor(SWT.COLOR_RED));
					}
					pre.setEnabled(false);
					next.setEnabled(false);
					first.setEnabled(false);
					last.setEnabled(false);
					button_7.setEnabled(false);
					text_1.setText("1");
					button.setText("返回");
				}
			}
		});
		button_1.setBounds(698, 155, 80, 27);
		button_1.setText("\u641C\u7D22");

		text_2 = new Text(container, SWT.BORDER);
		text_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text_2.setText("");
			}
		});
		text_2.setText("请输入要查询的电影");

		text_2.setBounds(554, 154, 140, 23);

		createActions();
		initializeToolBar();
		initializeMenu();

		getPageList();
	}

	public void getPageList() {
		table.removeAll();
		List<Map<String, Object>> list = db
				.query("select * from movieinfo limit " + (start - 1) * end
						+ "," + end + "");
		int maxNum = db.query("select * from movieinfo").size();
		// 总页数
		maxPage = maxNum / end + (maxNum % end == 0 ? 0 : 1);
		// 首页
		if (start == 1) {
			pre.setEnabled(false);
			next.setEnabled(true);
		}
		if (start == maxPage) {
			next.setEnabled(false);
			pre.setEnabled(true);
		}
		if (start != 1 && start != maxPage) {
			next.setEnabled(true);
			pre.setEnabled(true);
		}

		for (int i = 0; i < list.size(); i++) {

			Map<String, Object> map = list.get(i);

			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, map.get("id").toString());
			tableItem.setText(1, map.get("movie_name").toString());
			tableItem.setText(2, map.get("nation").toString());
			tableItem.setText(3, map.get("director").toString());
			tableItem.setText(4, map.get("movie_time").toString());
			tableItem.setText(5, map.get("movie_lan").toString());
			tableItem.setText(6, map.get("actor").toString());
			if (map.get("state").toString().equals("上映")) {
				tableItem.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_LIST_SELECTION));
			}
			if (map.get("state").toString().equals("下线")) {
				tableItem.setBackground(SWTResourceManager
						.getColor(SWT.COLOR_RED));
			}
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
