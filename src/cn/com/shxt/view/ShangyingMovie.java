package cn.com.shxt.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.StringRegexUtils;

public class ShangyingMovie extends ViewPart {

	public static final String ID = "cn.com.shxt.view.ShangyingMovie"; //$NON-NLS-1$
	private Text text;
	private DbUtils db = new DbUtils();
	private DateTime dateTime;
	private Combo combo;
	private Combo combo_1;
	private Spinner spinner;
	private Spinner spinner_1;
	private List myList_1 = new ArrayList();

	public ShangyingMovie() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/view12.jpg"));
		container.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));

		Label label = new Label(container, SWT.WRAP);
		label.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label.setBounds(161, 49, 100, 40);
		label.setText("\u8BF7\u9009\u62E9\u8981\u4E0A\u6620\u7684\u5F71\u7247\uFF1A");

		combo = new Combo(container, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		combo.setBounds(295, 46, 166, 27);
		getMovie();

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_1.setBounds(161, 134, 100, 17);
		label_1.setText("\u4E0A\u6620\u65E5\u671F\uFF1A");

		dateTime = new DateTime(container, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		dateTime.setBounds(295, 134, 166, 24);

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_2.setBounds(502, 49, 139, 17);
		label_2.setText("\u5177\u4F53\u5F00\u573A\u65F6\u95F4\uFF1A");

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		lblNewLabel.setBounds(716, 49, 25, 17);
		lblNewLabel.setText("时");

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager
				.getFont("华文行楷", 14, SWT.NORMAL));
		lblNewLabel_1.setBounds(813, 49, 30, 17);
		lblNewLabel_1.setText("分");

		spinner = new Spinner(container, SWT.BORDER);
		spinner.setMaximum(20);
		spinner.setBounds(663, 47, 47, 23);

		spinner_1 = new Spinner(container, SWT.BORDER);
		spinner_1.setMaximum(59);
		spinner_1.setBounds(747, 47, 47, 23);

		Label label_4 = new Label(container, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_4.setBounds(502, 134, 123, 17);
		label_4.setText("选择放映厅");

		combo_1 = new Combo(container, SWT.NONE);
		combo_1.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		combo_1.setBounds(663, 131, 166, 27);
		List<Map<String, Object>> list_1 = db
				.query("select roomname from showroom ");
		for (int i = 0; i < list_1.size(); i++) {
			combo_1.add(list_1.get(i).get("roomname").toString());

		}
		combo_1.select(0);

		Label label_3 = new Label(container, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label_3.setBounds(161, 222, 61, 17);
		label_3.setText("\u7968\u4EF7\uFF1A");

		text = new Text(container, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		text.setBounds(295, 219, 36, 23);

		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager
				.getFont("华文行楷", 14, SWT.NORMAL));
		lblNewLabel_2.setBounds(350, 222, 25, 27);
		lblNewLabel_2.setText("元");

		Button button = new Button(container, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			// 判断是否上映冲突
			public boolean panduan() {
				boolean a = false;
				String time = dateTime.getYear() + "-"
						+ (dateTime.getMonth() + 1) + "-" + dateTime.getDay();
				List<Map<String, Object>> list_5 = db
						.query("select roomname from fangying");
				for (int i = 0; i < list_5.size(); i++) {
					if (list_5.get(i).get("roomname").toString()
							.equals(combo_1.getText())) {
						List<Map<String, Object>> list_4 = db
								.query("select showtime from fangying where roomname = '"
										+ combo_1.getText() + "'");
						for (int j = 0; j < list_4.size(); j++) {
							if (list_4.get(j).get("showtime").equals(time)) {
								a = true;
								break;
							}
						}
					}
				}
				return a;
			}

			@Override
			// 上映电影
			public void widgetSelected(SelectionEvent e) {
				int hour;
				int min;

				MessageBox box = new MessageBox(new Shell());
				// 获取结束时间
				List<Map<String, Object>> list_2 = db
						.query("select movie_time ,showtime from movieinfo where movie_name = '"
								+ combo.getText() + "'");
				List<Map<String, Object>> list_3 = db
						.query("select type from showroom where roomname = '"
								+ combo_1.getText() + "'");

				int shijian = Integer.parseInt(list_2.get(0).get("movie_time")
						.toString());
				int m = (int) (shijian % 60);// 分钟取余
				if (m + Integer.parseInt(spinner_1.getText()) > 60) {
					hour = (int) ((int) (shijian / 60) + 1);
					min = (m + Integer.parseInt(spinner_1.getText())) % 60;
				} else {
					hour = (int) (shijian / 60);
					min = m + Integer.parseInt(spinner_1.getText());
				}

				String time = dateTime.getYear() + "-"
						+ (dateTime.getMonth() + 1) + "-" + dateTime.getDay();
				// if(showtime)

				if (panduan()) {
					box.setText("系统消息");
					box.setMessage("对不起，该放映厅当天已有上映电影，请更改日期或则更改放映厅！");
					box.open();
				} else if (text.getText().trim().equals("")) {
					box.setText("系统消息");
					box.setMessage("对不起，票价不能为空！");
					box.open();
				} else {
					if (!StringRegexUtils.isRegexpValidate(text.getText(),
							StringRegexUtils.integer_regexp)) {
						box.setText("系统消息");
						box.setMessage("对不起，票价必须是整型数！");
						box.open();
					} else {
						int i = db
								.update("update movieinfo set state = '上映',showtime = '"
										+ time
										+ "',"
										+ "price = '"
										+ text.getText()
										+ "' where movie_name = '"
										+ combo.getText() + "'");
						//
						int j = db
								.update("insert into fangying(roomname,type,movie_name,showtime,hour_time,min_time,price)values"
										+ "('"
										+ combo_1.getText()
										+ "',"
										+ "'"
										+ list_3.get(0).get("type").toString()
										+ "','"
										+ combo.getText()
										+ "','"
										+ time
										+ "',"
										+ "'"
										+ spinner.getText()
										+ "','"
										+ spinner_1.getText()
										+ "','"
										+ text.getText() + "')");//
						List<Map<String, Object>> list = db
								.query("select row,lie from showroom where roomname = '"
										+ combo_1.getText() + "'");
						String row = list.get(0).get("row").toString();
						String lie = list.get(0).get("lie").toString();
						for (int crow = 1; crow <= Integer.parseInt(row); crow++) {
							for (int clie = 1; clie <= Integer.parseInt(lie); clie++) {
								int k = db
										.update("INSERT INTO sell(roomname,movie_name,showtime,ROW,lie,price)VALUES('"
												+ combo_1.getText()
												+ "','"
												+ combo.getText()
												+ "','"
												+ time
												+ "','"
												+ crow
												+ "','"
												+ clie
												+ "','"
												+ text.getText()
												+ "')");
							}
						}
						List<Map<String, Object>> list_4 = db
								.query("select *from zuowei where state = '维修'");
						for (int w = 0; w < list_4.size(); w++) {
							String wrow = list_4.get(w).get("row").toString();
							String wlie = list_4.get(w).get("lie").toString();
							String weixiu = "" + wrow + " " + wlie;
							myList_1.add(weixiu);
						}

						if (myList_1.size() != 0) {
							for (int q = 0; q < myList_1.size(); q++) {
								String str = myList_1.get(q).toString();
								String str1[] = str.split(" ");
								db.update("update sell set state = '维修' where row = '"
										+ str1[0]
										+ "' and lie = '"
										+ str1[1]
										+ "' and roomname = '"
										+ combo_1.getText() + "'");
							}
						}

					
						if (i != 0 && j != 0) {
							box.setText("系统消息");
							box.setMessage("影片成功上映在"
									+ combo_1.getText()
									+ "！具体播放时间为："
									+ time
									+ "---"
									+ spinner.getText()
									+ "时"
									+ spinner_1.getText()
									+ "分。"
									+ "结束时间为："
									+ time
									+ "---"
									+ (hour + Integer.parseInt(spinner
											.getText())) + "时" + min + "分");
							box.open();

						} else {
							box.setText("系统消息");
							box.setMessage("影片上映失败！");
							box.open();
						}
					}
				}

			}
		});
		button.setBounds(161, 334, 130, 27);
		button.setText("增加上映计划");

		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getMovie();
			}
		});
		button_1.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		button_1.setBounds(661, 317, 80, 27);
		button_1.setText("\u5237\u65B0");

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void getMovie() {
		List<Map<String, Object>> list = db
				.query("select movie_name from movieinfo where state = '待定'");
		for (int i = 0; i < list.size(); i++) {
			combo.add(list.get(i).get("movie_name").toString());

		}
		combo.select(0);
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
