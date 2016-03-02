package cn.com.shxt.view;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.StringRegexUtils;

public class AddShowRoom extends ViewPart {

	public static final String ID = "cn.com.shxt.view.AddShowRoom"; //$NON-NLS-1$
	private Text text;// 影厅名
	private Text text_1;// 排数
	private Text text_2;// 列数
	private DbUtils db = new DbUtils();// 数据库连接的工具包

	public AddShowRoom() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/m2.jpg"));
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		{
			Label lblNewLabel = new Label(container, SWT.NONE);
			lblNewLabel.setFont(SWTResourceManager.getFont("华文行楷", 20,
					SWT.NORMAL));
			lblNewLabel.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_LIST_FOREGROUND));
			lblNewLabel.setBounds(514, 132, 162, 23);
			lblNewLabel.setText("放映厅名称：");
		}
		{
			text = new Text(container, SWT.BORDER);
			text.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
			text.setBounds(682, 127, 151, 28);
		}
		{
			Label lblNewLabel_1 = new Label(container, SWT.NONE);
			lblNewLabel_1.setFont(SWTResourceManager.getFont("华文行楷", 20,
					SWT.NORMAL));
			lblNewLabel_1.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_LIST_FOREGROUND));
			lblNewLabel_1.setBounds(514, 267, 108, 23);
			lblNewLabel_1.setText("\u5217\u6570\uFF1A");
		}
		{
			text_1 = new Text(container, SWT.BORDER);
			text_1.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
			text_1.setBounds(682, 262, 151, 28);
		}
		{
			Label lblNewLabel_2 = new Label(container, SWT.NONE);
			lblNewLabel_2.setFont(SWTResourceManager.getFont("华文行楷", 20,
					SWT.NORMAL));
			lblNewLabel_2.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_LIST_FOREGROUND));
			lblNewLabel_2.setBounds(514, 202, 162, 23);
			lblNewLabel_2.setText("放映厅类型：");
		}

		final Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		combo.setBounds(682, 202, 151, 27);
		combo.add("3D");
		combo.add("IMAX");
		combo.add("普通");
		combo.select(0);

		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager
				.getFont("华文行楷", 20, SWT.NORMAL));
		lblNewLabel_3.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_FOREGROUND));
		lblNewLabel_3.setBounds(514, 330, 108, 27);
		lblNewLabel_3.setText("排数：");
		{
			text_2 = new Text(container, SWT.BORDER);
			text_2.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
			text_2.setBounds(682, 330, 151, 28);
		}
		{
			Button button = new Button(container, SWT.NONE);
			button.setFont(SWTResourceManager.getFont("华文行楷", 20, SWT.NORMAL));
			button.addSelectionListener(new SelectionAdapter() {
				/**
				 * @描述: 判断放映厅是否存在
				 * @作者：黄威威
				 * @版本：0.9
				 * @开发时间：2013-6-04上午11:20:59
				 */
				public boolean panduan() {
					boolean a = false;
					List<Map<String, Object>> list = db
							.query("select roomname from showroom");
					for (Map<String, Object> map : list) {
						if (text.getText().trim().equals(map.get("roomname").toString())) {
							a = true;
							break;
						} else {
							a = false;
						}
					}
					return a;
				}

				@Override
				/**
				 * @描述: 添加放映厅
				 * @作者：黄威威
				 * @版本：0.9
				 * @开发时间：2013-6-04上午11:20:59
				 */
				public void widgetSelected(SelectionEvent e) {
					MessageBox box = new MessageBox(new Shell());
					if (panduan()) {
						box.setText("系统消息");
						box.setMessage("您添加的放映厅已存在！");
						box.open();
					} else {
						if (text.getText().trim().length() == 0
								|| text.getText().trim().length() == 0
								|| text.getText().trim().length() == 0) {
							box.setText("系统消息");
							box.setMessage("请填完整您所要纳入的信息！");
							box.open();
						} else if (!StringRegexUtils.isRegexpValidate(
								text_1.getText(),
								StringRegexUtils.integer_regexp)) {
							box.setText("系统消息");
							box.setMessage("对不起，排数请输入整型数！");
							box.open();
						} else if (!StringRegexUtils.isRegexpValidate(
								text_2.getText(),
								StringRegexUtils.integer_regexp)) {
							box.setText("系统消息");
							box.setMessage("对不起，列数请输入整型数！");
							box.open();
						} else {
							int i = db
									.update("insert into showroom(roomname,lie,type,row)values("
											+ "'"
											+ text.getText()
											+ "','"
											+ text_1.getText()
											+ "'"
											+ ",'"
											+ combo.getText()
											+ "','"
											+ text_2.getText() + "')");
							for (int a = 1; a <= Integer.parseInt(text_2
									.getText()); a++) {
								for (int b = 1; b <= Integer.parseInt(text_1
										.getText()); b++) {
									int j = db.update("insert into zuowei set roomname = '"
													+ text.getText()
													+ "',"
													+ "lie = '"
													+ b
													+ "',row = '"
													+ a
													+ "',state = '可用'");
								}
							}

							if (i != 0) {
								box.setText("系统消息");
								box.setMessage("添加成功！");
								box.open();

								text.setText("");
								text_1.setText("");
								text_2.setText("");
							}

						}
					}

				}
			});
			button.setBounds(812, 397, 90, 37);
			button.setText("确认");
		}

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
