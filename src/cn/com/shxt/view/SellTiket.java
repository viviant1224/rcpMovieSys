package cn.com.shxt.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.dialog.Maipiao;
import cn.com.shxt.dialog.ShowMovie;
import cn.com.shxt.dialog.VipSign;
import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;

public class SellTiket extends ViewPart {

	public static final String ID = "cn.com.shxt.view.SellTiket"; //$NON-NLS-1$
	private Table table;
	private Text text;
	private DbUtils db = new DbUtils();
	private Table table_1;

	public SellTiket() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/maipiao2.jpg"));
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(64, 171, 598, 258);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(130);
		tblclmnNewColumn.setText("电影名");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(185);
		tableColumn.setText("\u653E\u6620\u65F6\u95F4");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(64);
		tableColumn_1.setText("\u5269\u4F59\u7968\u6570");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(50);
		tableColumn_2.setText("\u7968\u4EF7");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.CHECK);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			// 查看详情信息
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem = table.getItem(table.getSelectionIndex());

				String movie_name = tableItem.getText(0);

				ShowMovie sm = new ShowMovie(container.getShell(), SWT.NONE);
				sm.open(movie_name);
			}
		});
		menuItem.setText("\u67E5\u770B\u7535\u5F71\u8BE6\u60C5\u4FE1\u606F");

		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			// 售票
			public void widgetSelected(SelectionEvent e) {

				MessageBox box = new MessageBox(new Shell());

				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				// lblNewLabel_7.setText(sdf.format(d));
				String showtime_1 = sdf.format(d);
				String[] arrTime_1 = showtime_1.split("[- :]+");
				String year_1 = arrTime_1[0];
				String mon_1 = arrTime_1[1];
				String day_1 = arrTime_1[2];
				String hour_1 = arrTime_1[3];
				String min_1 = arrTime_1[4];

				TableItem tableItem = table.getItem(table.getSelectionIndex());
				String movie_name = tableItem.getText(0);
				String roomname = tableItem.getText(4);
				String price = tableItem.getText(3);
				List<Map<String, Object>> list = db
						.query("select showtime,hour_time,min_time from"
								+ " fangying where movie_name = '" + movie_name
								+ "'");

				String showtime = list.get(0).get("showtime").toString();
				String[] arrTime = showtime.split("-");
				String year = arrTime[0];
				String mon = arrTime[1];
				String day = arrTime[2];

				String hour_time = list.get(0).get("hour_time").toString();
				String min_time = list.get(0).get("min_time").toString();
//判断时间
				if (Integer.parseInt(year_1) >= Integer.parseInt(year)) {

					if (Integer.parseInt(year_1) == Integer.parseInt(year)) {
						if (Integer.parseInt(mon_1) >= Integer.parseInt(mon)) {
							if (Integer.parseInt(mon_1) == Integer
									.parseInt(mon)) {
								if (Integer.parseInt(day_1) >= Integer
										.parseInt(day)) {
									if (Integer.parseInt(day_1) == Integer
											.parseInt(day)) {
										if (Integer.parseInt(hour_1) >= Integer
												.parseInt(hour_time)) {
											if (Integer.parseInt(hour_1) == Integer
													.parseInt(hour_time)) {
												if (Integer.parseInt(min_1) >= Integer
														.parseInt(min_time)) {
													box.setText("系统消息");
													box.setMessage("对不起，该影片已经放映，不能售票！");
													box.open();
												} else {
													Maipiao s = new Maipiao(
															container
																	.getShell(),
															SWT.NONE);
													s.open(roomname,
															movie_name, price,showtime);
												}

											} else {

												box.setText("系统消息");
												box.setMessage("对不起，该影片已经放映，不能售票！");
												box.open();
											}
										} else {
											Maipiao s = new Maipiao(container
													.getShell(), SWT.NONE);
											s.open(roomname, movie_name, price,showtime);
										}
									} else {
										box.setText("系统消息");
										box.setMessage("对不起，该影片已经放映，不能售票！");
										box.open();
									}

								} else {
									Maipiao s = new Maipiao(container
											.getShell(), SWT.NONE);
									s.open(roomname, movie_name, price,showtime);
								}

							} else {
								box.setText("系统消息");
								box.setMessage("对不起，该影片已经放映，不能售票！");
								box.open();
							}

						} else {
							Maipiao s = new Maipiao(container.getShell(),
									SWT.NONE);
							s.open(roomname, movie_name, price,showtime);
						}

					} else {
						box.setText("系统消息");
						box.setMessage("对不起，该影片已经放映，不能售票！");
						box.open();
					}

				} else {
					Maipiao s = new Maipiao(container.getShell(), SWT.NONE);
					s.open(roomname, movie_name, price,showtime);

				}

				// Maipiao s = new Maipiao(container.getShell(), SWT.NONE);
				// s.open(roomname,movie_name,price);

			}
		});
		menuItem_1.setText("\u552E\u7968");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(161);
		tblclmnNewColumn_1.setText("放映厅");

		// TableItem tableItem = new TableItem(table, SWT.NONE);
		// tableItem.setText("New TableItem");

		final Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			// 刷新
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
				getList();
				button_1.setText("刷新");
				text.setText("请输入你要查询的电影");
			}
		});
		button_1.setBounds(582, 93, 80, 27);
		button_1.setText("刷新");

		Label label = new Label(container, SWT.NONE);
		label.setBounds(382, 148, 61, 17);
		label.setText("\u67E5\u627E\u5F71\u7247\uFF1A");

		text = new Text(container, SWT.BORDER);
		text.setText("请输入你要查询的电影");
		text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText("");
			}
		});
		text.setBounds(449, 142, 129, 23);

		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			// 搜索
			public void widgetSelected(SelectionEvent e) {

				MessageBox box = new MessageBox(new Shell());
				List<Map<String, Object>> list = db
						.query(""
								+ "SELECT movie_name,showtime,price,COUNT(state),roomname FROM sell "
								+ "WHERE state = '可用' AND movie_name = '"
								+ text.getText() + "' GROUP BY movie_name");
				if (list.size() == 0) {
					box.setText("系统消息");
					box.setMessage("对不起，没有您要查询的影片！");
					box.open();
				} else {
					table.removeAll();
					Map<String, Object> map = list.get(0);
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(0, map.get("movie_name").toString());
					tableItem.setText(2, map.get("COUNT(state)").toString());
					tableItem.setText(3, map.get("price").toString());
					tableItem.setText(1, map.get("showtime").toString());
					tableItem.setText(4, map.get("roomname").toString());
				}
				button_1.setText("返回");
			}
		});
		button.setBounds(582, 138, 80, 27);
		button.setText("\u641C\u7D22");

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_1.setBounds(701, 148, 31, 17);
		label_1.setText("\u9644\uFF1A");

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel.setBounds(726, 148, 108, 17);
		lblNewLabel.setText("\uFF08\u4F1A\u5458\u6298\u6263\u8BE6\u60C5\uFF09");

		table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(701, 171, 129, 119);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tblclmnNewColumn_2 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_2.setWidth(67);
		tblclmnNewColumn_2.setText("级别");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_3.setWidth(57);
		tblclmnNewColumn_3.setText("折扣率");

		TableItem tableItem_1 = new TableItem(table_1, SWT.NONE);
		tableItem_1.setText(0, "普通用户");
		tableItem_1.setText(1, "无折扣");

		TableItem tableItem = new TableItem(table_1, SWT.NONE);
		tableItem.setText(0, "星级");
		tableItem.setText(1, "9折");

		TableItem tableItem_2 = new TableItem(table_1, SWT.NONE);
		tableItem_2.setText(0, "月亮级");
		tableItem_2.setText(1, "8.5折");

		TableItem tableItem_3 = new TableItem(table_1, SWT.NONE);
		tableItem_3.setText(0, "太阳级");
		tableItem_3.setText(1, "7.5折");

		Button button_2 = new Button(container, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				VipSign vs = new VipSign(container.getShell(), SWT.NONE);
				vs.open();
			}
		});
		button_2.setBounds(706, 312, 80, 27);
		button_2.setText("\u6CE8\u518C\u4F1A\u5458");

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("华文行楷", 30, SWT.ITALIC));
		label_2.setBounds(145, 45, 360, 58);
		label_2.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662\u8D2D\u7968\u533A");

		createActions();
		initializeToolBar();
		initializeMenu();
		getList();
	}

	public void getList() {
	
		List<Map<String, Object>> list = db
				.query("SELECT movie_name,showtime,price,COUNT(state),roomname FROM sell "
						+ "WHERE state = '可用' and state_1 = '上映' GROUP BY movie_name");

		for (int i = 0; i < list.size(); i++) {

			Map<String, Object> map = list.get(i);

			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(0, map.get("movie_name").toString());
			tableItem.setText(2, map.get("COUNT(state)").toString());
			tableItem.setText(3, map.get("price").toString());
			tableItem.setText(1, map.get("showtime").toString());
			tableItem.setText(4, map.get("roomname").toString());

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
