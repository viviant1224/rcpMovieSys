package cn.com.shxt.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.core.Activator;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.Logo_Two_Code;
import cn.com.shxt.tools.StringRegexUtils;

public class TuiPiao extends ViewPart {

	public static final String ID = "cn.com.shxt.view.TuiPiao"; //$NON-NLS-1$
	private Text text_1;
	private Text text_2;
	private DbUtils db = new DbUtils();
	private String showtime;
	private String hour_time;
	private String min_time;
	private double price;
	private Text text;

	public TuiPiao() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/maipiao2.jpg"));
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		{
			Label label = new Label(container, SWT.NONE);
			label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
			label.setBounds(271, 178, 144, 34);
			label.setText("\u8981\u9000\u7968\u7684\u5F71\u7247\u540D\uFF1A");
		}

		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(271, 455, 104, 34);
		label.setText("\u5EA7\u4F4D\u53F7\uFF1A");

		final Combo combo = new Combo(container, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo.setBounds(444, 175, 127, 37);
		List<Map<String, Object>> list = db
				.query("select movie_name from movieinfo where state = '上映'");
		for (int i = 0; i < list.size(); i++) {
			combo.add(list.get(i).get("movie_name").toString());
		}
		combo.select(0);

		final Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager
				.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_2.setBounds(444, 259, 110, 17);

		final Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager
				.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_3.setBounds(560, 259, 29, 17);

		final Label lblNewLabel_4 = new Label(container, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager
				.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_4.setBounds(595, 259, 3, 17);

		final Label lblNewLabel_5 = new Label(container, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager
				.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_5.setBounds(615, 259, 46, 17);

		final Date d = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final Label lblNewLabel_7 = new Label(container, SWT.NONE);
		lblNewLabel_7.setFont(SWTResourceManager
				.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_7.setBounds(444, 352, 170, 17);
		lblNewLabel_7.setText(sdf.format(d));

		text_1 = new Text(container, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_1.setBounds(464, 452, 20, 37);

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel.setBounds(444, 455, 14, 34);
		lblNewLabel.setText("\u7B2C");

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setBounds(560, 455, 14, 34);
		label_2.setText("\u5217");

		Label label_3 = new Label(container, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setBounds(491, 455, 20, 34);
		label_3.setText("\u6392");

		text_2 = new Text(container, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_2.setBounds(534, 452, 20, 37);

		Label label_4 = new Label(container, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_4.setBounds(744, 19, 36, 17);
		label_4.setText("\u8BF4\u660E\uFF1A");

		Label lblNewLabel_1 = new Label(container, SWT.WRAP);
		lblNewLabel_1
				.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_1.setBounds(745, 42, 177, 88);
		lblNewLabel_1
				.setText("      \u5982\u679C\u8BE5\u5F71\u7247\u5DF2\u4E0A\u6620\u6216\u4E0A\u6620\u540E\u4E0D\u5F97\u9000\u7968\uFF0C\u4E0A\u6620\u524D\u4E24\u4E2A\u5C0F\u65F6\uFF08\u4E0D\u5305\u62EC\u521A\u521A\u4E0A\u6620\u7684\u90A3\u4E2A\u65F6\u95F4\uFF09\u4E4B\u5185\uFF0C\u8FD4\u4E70\u7968\u65F6\u7968\u4EF7\u768450%\uFF0C\u5176\u4F59\u65F6\u95F4\u4E00\u5F8B\u8FD470%\u3002");

		Button button = new Button(container, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			// 提交电影票
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				List<Map<String, Object>> list_1 = db
						.query("select showtime,hour_time,min_time from fangying where movie_name = '"
								+ combo.getText() + "'");
				showtime = list_1.get(0).get("showtime").toString();
				hour_time = list_1.get(0).get("hour_time").toString();
				min_time = list_1.get(0).get("min_time").toString();
				lblNewLabel_2.setText(showtime);
				lblNewLabel_3.setText(hour_time);
				lblNewLabel_5.setText(min_time);
				lblNewLabel_4.setText(":");

			}
		});
		button.setBounds(600, 173, 80, 29);
		button.setText("\u63D0\u4EA4");

		Label label_5 = new Label(container, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(271, 259, 160, 34);
		label_5.setText("\u8BE5\u5F71\u7247\u7684\u4E0A\u6620\u65F6\u95F4\u4E3A\uFF1A");

		Button button_1 = new Button(container, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			// 退票
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if (text_1.getText().trim().toString().length() == 0
						|| text_2.getText().trim().toString().length() == 0) {
					box.setText("系统消息");
					box.setMessage("请填写完整座位号！");
					box.open();

				} else {
					final List<Map<String, Object>> list_2 = db
							.query("select showtime,hour_time,min_time from fangying"
									+ " where movie_name = '"
									+ combo.getText()
									+ "'");
					final List<Map<String, Object>> list_3 = db
							.query("select * from sell where movie_name = '"
									+ combo.getText() + "' and row = '"
									+ text_1.getText() + "' and lie = '"
									+ text_2.getText() + "'");

					if (list_3.size() == 0) {
						box.setText("系统消息");
						box.setMessage("对不起，没有该座位号！请核对您的票根");
						box.open();
					}
					if (list_3.size() != 0) {
						System.out.println("------------" + list_3.size());
						String state = list_3.get(0).get("state").toString();
						if (!StringRegexUtils.isRegexpValidate(
								text_1.getText(),
								StringRegexUtils.integer_regexp)) {
							box.setText("系统消息");
							box.setMessage("对不起，请将排数输为整形！");
							box.open();
						} else if (!StringRegexUtils.isRegexpValidate(
								text_2.getText(),
								StringRegexUtils.integer_regexp)) {
							box.setText("系统消息");
							box.setMessage("对不起，请将列数输为整形！");
							box.open();
						} else if (state.equals("可用")) {
							box.setText("系统消息");
							box.setMessage("对不起，该座位还未开卖！请核对您的票根");
							box.open();
						} else if (state.equals("维修")) {
							box.setText("系统消息");
							box.setMessage("对不起，该座位还未开卖（维修中）！请核对您的票根");
							box.open();
						} else {
							price = Double.parseDouble(list_3.get(0)
									.get("sell_price").toString());
							String time = list_2.get(0).get("showtime")
									.toString();
							String[] arrTime = time.split("-");
							String year = arrTime[0];
							String mon = arrTime[1];
							String day = arrTime[2];
							String time_1 = sdf.format(d);
							String[] arrTime_1 = time_1.split("[- :]+");
							String year_1 = arrTime_1[0];
							String mon_1 = arrTime_1[1];
							String day_1 = arrTime_1[2];
							String hour_1 = arrTime_1[3];
							String min_1 = arrTime_1[4];
							// 判断时间
							if (Integer.parseInt(year_1) >= Integer
									.parseInt(year)) {

								if (Integer.parseInt(year_1) == Integer
										.parseInt(year)) {
									if (Integer.parseInt(mon_1) >= Integer
											.parseInt(mon)) {
										if (Integer.parseInt(mon_1) == Integer
												.parseInt(mon)) {
											if (Integer.parseInt(day_1) >= Integer
													.parseInt(day)) {

												if (Integer.parseInt(day_1) == Integer
														.parseInt(day)) {

													if (Integer
															.parseInt(hour_1) >= Integer
															.parseInt(hour_time)) {

														if (Integer
																.parseInt(hour_1) == Integer
																.parseInt(hour_time)) {
															if (Integer
																	.parseInt(min_1) >= Integer
																	.parseInt(min_time)) {
																box.setText("系统消息");
																box.setMessage("对不起，该影片已经放映，不能退票！");
																box.open();

															} else {
																price = price * 0.5;
																int i = db
																		.update("UPDATE sell SET state = '可用' ,sell_price = '0' WHERE movie_name = '"
																				+ combo.getText()
																				+ "' AND ROW = '"
																				+ text_1.getText()
																				+ "' AND lie = '"
																				+ text_2.getText()
																				+ "'");
																if (i != 0) {
																	box.setText("系统消息");
																	box.setMessage("成功退票,原票价为："
																			+ list_3.get(
																					0)
																					.get("sell_price")
																					.toString()
																			+ "元，退还："
																			+ price
																			+ "元");
																	box.open();
																} else {
																	box.setText("系统消息");
																	box.setMessage("退票失败！");
																	box.open();
																}
															}

														} else {
															box.setText("系统消息");
															box.setMessage("对不起，该影片已经放映，不能退票！");
															box.open();
														}

													} else {
														if (Integer
																.parseInt(hour_time)
																- Integer
																		.parseInt(hour_1) == 2) {
															if (Integer
																	.parseInt(min_time)
																	- Integer
																			.parseInt(min_1) >= 0) {
																price = price * 0.7;
																int i = db
																		.update("UPDATE sell SET state = '可用' ,sell_price = '0' WHERE movie_name = '"
																				+ combo.getText()
																				+ "' AND ROW = '"
																				+ text_1.getText()
																				+ "' AND lie = '"
																				+ text_2.getText()
																				+ "'");
																if (i != 0) {
																	box.setText("系统消息");
																	box.setMessage("成功退票,原票价为："
																			+ list_3.get(
																					0)
																					.get("sell_price")
																					.toString()
																			+ "元，退还："
																			+ price
																			+ "元");
																	box.open();
																} else {
																	box.setText("系统消息");
																	box.setMessage("退票失败！");
																	box.open();
																}
															} else {
																price = price * 0.5;
																int i = db
																		.update("UPDATE sell SET state = '可用' ,sell_price = '0' WHERE movie_name = '"
																				+ combo.getText()
																				+ "' AND ROW = '"
																				+ text_1.getText()
																				+ "' AND lie = '"
																				+ text_2.getText()
																				+ "'");
																if (i != 0) {
																	box.setText("系统消息");
																	box.setMessage("成功退票,原票价为："
																			+ list_3.get(
																					0)
																					.get("sell_price")
																					.toString()
																			+ "元，退还："
																			+ price
																			+ "元");
																	box.open();
																} else {
																	box.setText("系统消息");
																	box.setMessage("退票失败！");
																	box.open();
																}
															}
															//
														} else {
															if (Integer
																	.parseInt(hour_time)
																	- Integer
																			.parseInt(hour_1) == 1) {
																price = price * 0.5;
																int i = db
																		.update("UPDATE sell SET state = '可用' ,sell_price = '0' WHERE movie_name = '"
																				+ combo.getText()
																				+ "' AND ROW = '"
																				+ text_1.getText()
																				+ "' AND lie = '"
																				+ text_2.getText()
																				+ "'");
																if (i != 0) {
																	box.setText("系统消息");
																	box.setMessage("成功退票,原票价为："
																			+ list_3.get(
																					0)
																					.get("sell_price")
																					.toString()
																			+ "元，退还："
																			+ price
																			+ "元");
																	box.open();
																} else {
																	box.setText("系统消息");
																	box.setMessage("退票失败！");
																	box.open();
																}
															} else {
																price = price * 0.7;
																int i = db
																		.update("UPDATE sell SET state = '可用' ,sell_price = '0' WHERE movie_name = '"
																				+ combo.getText()
																				+ "' AND ROW = '"
																				+ text_1.getText()
																				+ "' AND lie = '"
																				+ text_2.getText()
																				+ "'");
																if (i != 0) {
																	box.setText("系统消息");
																	box.setMessage("成功退票,原票价为："
																			+ list_3.get(
																					0)
																					.get("sell_price")
																					.toString()
																			+ "元，退还："
																			+ price
																			+ "元");
																	box.open();
																} else {
																	box.setText("系统消息");
																	box.setMessage("退票失败！");
																	box.open();
																}
															}

														}

													}
													// 70%

												} else {
													box.setText("系统消息");
													box.setMessage("对不起，该影片已经放映，不能退票！");
													box.open();
												}

												// ...........
											} else {
												price = price * 0.7;
												int i = db.update("UPDATE sell SET state = '可用' ,sell_price = '0' WHERE movie_name = '"
														+ combo.getText()
														+ "' AND ROW = '"
														+ text_1.getText()
														+ "' AND lie = '"
														+ text_2.getText()
														+ "'");
												System.out.println("======"
														+ text_1.getText()
														+ "-"
														+ text_2.getText());
												if (i != 0) {

													box.setText("系统消息");
													box.setMessage("成功退票,原票价为："
															+ list_3.get(0)
																	.get("sell_price")
																	.toString()
															+ "元，退还：" + price
															+ "元");
													box.open();
												} else {
													box.setText("系统消息");
													box.setMessage("退票失败！");
													box.open();
												}

												// 70%
											}

										} else {
											box.setText("系统消息");
											box.setMessage("对不起，该影片已经放映，不能退票！");
											box.open();
										}

									} else {
										price = price * 0.7;
										int i = db
												.update("UPDATE sell SET state = '可用' ,sell_price = '0' WHERE movie_name = '"
														+ combo.getText()
														+ "' AND ROW = '"
														+ text_1.getText()
														+ "' AND lie = '"
														+ text_2.getText()
														+ "'");
										if (i != 0) {

											box.setText("系统消息");
											box.setMessage("成功退票,原票价为："
													+ list_3.get(0)
															.get("sell_price")
															.toString()
													+ "元，退还：" + price + "元");
											box.open();
										} else {
											box.setText("系统消息");
											box.setMessage("退票失败！");
											box.open();
										}
										// 70%

									}

								} else {
									box.setText("系统消息");
									box.setMessage("对不起，该影片已经放映，不能退票！");
									box.open();
								}

							} else {
								price = price * 0.7;
								int i = db
										.update("UPDATE sell SET state = '可用' ,sell_price = '0' WHERE movie_name = '"
												+ combo.getText()
												+ "' AND ROW = '"
												+ text_1.getText()
												+ "' AND lie = '"
												+ text_2.getText() + "'");
								if (i != 0) {

									box.setText("系统消息");
									box.setMessage("成功退票,原票价为："
											+ list_3.get(0).get("sell_price")
													.toString() + "元，退还："
											+ price + "元");
									box.open();
								} else {
									box.setText("系统消息");
									box.setMessage("退票失败！");
									box.open();
								}
								// 70%

							}
						}
					}

				}
			}
		});
		button_1.setBounds(589, 573, 80, 27);
		button_1.setText("\u9000\u7968");

		Label lblNewLabel_6 = new Label(container, SWT.NONE);
		lblNewLabel_6.setFont(SWTResourceManager
				.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_6.setBounds(271, 352, 113, 34);
		lblNewLabel_6.setText("\u5F53\u524D\u7CFB\u7EDF\u65F6\u95F4\uFF1A");

		Group group = new Group(container, SWT.NONE);
		group.setText("\u987E\u5BA2\u4E13\u680F\uFF1A");
		group.setBounds(10, 167, 207, 433);

		Label label_6 = new Label(group, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setBounds(10, 39, 85, 37);
		label_6.setText("\u4E3A\u4F55\u9000\u7968\uFF1A");

		Button button_2 = new Button(group, SWT.CHECK);
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_2.setBounds(10, 82, 170, 27);
		button_2.setText("\u65F6\u95F4\u592A\u4ED3\u4FC3\u6CA1\u6765\u5F97\u6025");

		Button button_3 = new Button(group, SWT.CHECK);
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_3.setBounds(10, 155, 170, 27);
		button_3.setText("\u7535\u5F71\u4E0D\u597D\u770B");

		Button button_4 = new Button(group, SWT.CHECK);
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_4.setBounds(10, 229, 170, 27);
		button_4.setText("\u6709\u4E8B\u60C5\u51B2\u7A81");

		Button button_5 = new Button(group, SWT.CHECK);
		button_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_5.setBounds(10, 302, 159, 17);
		button_5.setText("\u4EF7\u683C\u539F\u56E0");

		Button button_7 = new Button(group, SWT.NONE);
		button_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_7.setBounds(117, 406, 80, 27);
		button_7.setText("\u63D0\u4EA4");

		Group group_1 = new Group(container, SWT.NONE);
		group_1.setBounds(715, 175, 207, 425);

		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_7.setBounds(10, 22, 160, 29);
		label_7.setText("\u8BF7\u63D0\u4EA4\u60A8\u5B9D\u8D35\u7684\u5EFA\u8BAE\uFF1A");

		text = new Text(group_1, SWT.BORDER | SWT.WRAP);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text.setBounds(10, 57, 187, 287);

		Button button_6 = new Button(group_1, SWT.NONE);
		button_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button_6.setBounds(117, 398, 80, 27);
		button_6.setText("\u63D0\u4EA4");

		Label label_8 = new Label(container, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("华文行楷", 30, SWT.ITALIC));
		label_8.setBounds(294, 42, 365, 52);
		label_8.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662\u9000\u7968\u533A");

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u7B2C");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(517, 455, 14, 34);
		
		
		createActions();
		initializeToolBar();
		initializeMenu();
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
