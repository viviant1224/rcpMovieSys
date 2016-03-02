package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.StringRegexUtils;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.ResourceManager;

public class VipSign extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Table table;
	private Text text_1;
	private Text text_2;
	private DbUtils db = new DbUtils();
	private String jibie;
	private String sex;
	private double price;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VipSign(Shell parent, int style) {
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
		shell.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/zhuce.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(601, 493);
		shell.setText("\u6CE8\u518C\u4F1A\u5458");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(65, 251, 97, 17);
		label.setText("\u8BF7\u8F93\u5165\u59D3\u540D\uFF1A");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(230, 248, 118, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(65, 156, 193, 17);
		label_1.setText("\u8BF7\u9009\u62E9\u8981\u529E\u7406\u7684\u4F1A\u5458\u7EA7\u522B\uFF1A");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(65, 10, 107, 17);
		lblNewLabel.setText("\u5404\u4F1A\u5458\u7EA7\u522B\u8BE6\u8BF7\uFF1A");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(65, 47, 405, 71);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u661F\u7EA7\u4F1A\u5458");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u6708\u4EAE\u7EA7\u4F1A\u5458");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("\u592A\u9633\u7EA7\u4F1A\u5458");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
//		tableItem.setText("New TableItem");
		tableItem.setText(0, "折扣率：");
		tableItem.setText(1, "9折");
		tableItem.setText(2, "8.5折");
		tableItem.setText(3, "7.5折");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
//		tableItem_1.setText("New TableItem");
		tableItem_1.setText(0, "办理费用：");
		tableItem_1.setText(1, "100元");
		tableItem_1.setText(2, "250元");
		tableItem_1.setText(3, "500元");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(65, 354, 97, 17);
		label_2.setText("\u8BF7\u8F93\u5165\u60A8\u7684\u7535\u8BDD\uFF1A");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(230, 351, 118, 23);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(65, 404, 150, 17);
		label_3.setText("\u8BF7\u8F93\u5165\u60A8\u7684\u8EAB" +
				"\u4EFD\u8BC1\u4EF6\u53F7\u7801\uFF1A");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(230, 401, 118, 23);
		
		
		Button button_3 = new Button(shell, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			public boolean getSFZ() {
				boolean a = false;
				List<Map<String,Object>> list = db.query("select shenfenzheng from vip ");
				for(int i =0;i<list.size();i++) {
					if(list.get(i).get("shenfenzheng").toString().equals(text_2.getText().toString())) {
						a = true;
						break;
					}
				}	
				return a;	
			}
			@Override
			/**
			 * @描述: 登记注册
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if(getSFZ()) {
					box.setText("系统消息");
					box.setMessage("改身份证已登记完，请核对您的身份证！");
					box.open();
				} else if(text.getText().toString().trim().length()==0
						||text_1.getText().toString().trim().length()==0||
						text_2.getText().toString().trim().length()==0) {
					box.setText("系统消息");
					box.setMessage("请完善您的资料！");
					box.open();
				} else if(!StringRegexUtils.isRegexpValidate(text_1.getText(),StringRegexUtils.phone_regexp)) {
					box.setText("系统消息");
					box.setMessage("对不起，您填写的电话号码格式不对！");
					box.open();
				} else if(!StringRegexUtils.isRegexpValidate(text_2.getText(),StringRegexUtils.ID_card_regexp)) {
					box.setText("系统消息");
					box.setMessage("对不起，您填写的身份证号格式不对！");
					box.open();
				} else {
					VIPSignFuqian vsf = new VIPSignFuqian(shell, SWT.NONE);
					vsf.open(price,jibie,sex,text.getText(),text_1.getText(),text_2.getText());
					
					
				}
			}
		});
		button_3.setBounds(390, 399, 80, 27);
		button_3.setText("\u786E\u8BA4\u767B\u8BB0");
		
		Button button_4 = new Button(shell, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		button_4.setBounds(493, 399, 80, 27);
		button_4.setText("\u9000\u51FA\u767B\u8BB0");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(66, 301, 61, 17);
		label_4.setText("\u6027\u522B\uFF1A");
		
		Group group = new Group(shell, SWT.NONE);
		group.setBounds(65, 179, 397, 45);
		
		final Button button = new Button(group, SWT.RADIO);
		button.setBounds(10, 10, 62, 17);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				jibie = "星级";
				price = 100;
			}
		});
		
		button.setSelection(true);
		button.setText("\u661F\u7EA7");
		
		final Button button_1 = new Button(group, SWT.RADIO);
		button_1.setBounds(136, 10, 62, 17);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				jibie = "月亮级";
				price = 250;
			}
		});
		button_1.setText("\u6708\u4EAE\u7EA7");
		
		final Button button_2 = new Button(group, SWT.RADIO);
		button_2.setBounds(309, 10, 62, 17);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				jibie = "太阳级";
				price = 500;
			}
		});
		button_2.setText("\u592A\u9633\u7EA7");
		
		Group group_1 = new Group(shell, SWT.NONE);
		group_1.setBounds(168, 289, 304, 41);
		
		
		Button button_5 = new Button(group_1, SWT.RADIO);
		button_5.setBounds(10, 10, 97, 17);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			//男
			public void widgetSelected(SelectionEvent e) {
				sex = "男";
			}
		});
		button_5.setSelection(true);
		button_5.setText("\u7537");
		
		Button button_6 = new Button(group_1, SWT.RADIO);
		button_6.setBounds(207, 10, 97, 17);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			//女
			public void widgetSelected(SelectionEvent e) {
				sex = "女";
			}
		});
		button_6.setText("\u5973");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setBounds(434, 10, 61, 17);
		label_5.setText("\u767B\u8BB0\u5458\uFF1A");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(512, 10, 61, 17);
		lblNewLabel_1.setText(Login.name);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_6.setBounds(354, 404, 13, 17);
		label_6.setText("*");
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("*");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_7.setBounds(354, 251, 13, 17);
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setText("*");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_8.setBounds(482, 301, 13, 17);
		
		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setText("*");
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_9.setBounds(468, 190, 13, 17);
		
		Label label_10 = new Label(shell, SWT.NONE);
		label_10.setText("*");
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_10.setBounds(354, 354, 13, 17);
		
	

	}
}
