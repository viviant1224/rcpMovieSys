package cn.com.shxt.dialog;

import java.util.ArrayList;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.StringRegexUtils;
import org.eclipse.wb.swt.ResourceManager;

public class VIPSignFuqian extends Dialog {

	protected Object result;
	protected Shell shlVip;
	private Text text;
	private Text text_1;
	private Text text_2;
	private double price;
	private String sex;
	private String jibie;
	private String vip_name;
	private String telephone;
	private String shenfenzheng;
	

	private DbUtils db = new DbUtils();

	private List myList = new ArrayList();
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public VIPSignFuqian(Shell parent, int style) {
		super(parent, style);
		setText("付钱");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(double price,String jibie,String sex,String vip_name,String telephone,String shenfenzheng) {
		this.jibie = jibie;
		this.vip_name = vip_name;
		this.telephone = telephone;
		this.sex = sex;
		this.shenfenzheng = shenfenzheng;
		this.price = price;
		createContents();
		shlVip.open();
		shlVip.layout();
		Display display = getParent().getDisplay();
		while (!shlVip.isDisposed()) {
			try{
				V_Card.shellv.dispose();
			} catch(Exception e) {
				
			}
			
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
		shlVip = new Shell(getParent(), SWT.BORDER | SWT.CLOSE);
		shlVip.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/zhuce.jpg"));
		shlVip.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shlVip.setSize(450, 300);
		shlVip.setText("VIP\u6CE8\u518C\u4ED8\u6B3E");
		
		Label label = new Label(shlVip, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label.setBounds(48, 55, 103, 37);
		label.setText("\u5E94\u4ED8\u91D1\u989D\uFF1A");
		
		text = new Text(shlVip, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text.setBounds(170, 55, 73, 37);
		text.setText(""+price);
		
		Label label_1 = new Label(shlVip, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_1.setBounds(48, 115, 103, 26);
		label_1.setText("\u5B9E\u6536\u91D1\u989D\uFF1A");
		
		text_1 = new Text(shlVip, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text_1.setBounds(170, 115, 73, 37);
		
		Label label_2 = new Label(shlVip, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_2.setBounds(48, 170, 103, 37);
		label_2.setText("\u627E\u96F6\uFF1A");
		
		text_2 = new Text(shlVip, SWT.BORDER | SWT.READ_ONLY);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text_2.setBounds(170, 174, 73, 33);
		
		Label label_3 = new Label(shlVip, SWT.NONE);
		label_3.setBounds(249, 55, 30, 17);
		label_3.setText("\u5143");
		
		Label label_4 = new Label(shlVip, SWT.NONE);
		label_4.setText("\u5143");
		label_4.setBounds(249, 115, 30, 17);
		
		Label label_5 = new Label(shlVip, SWT.NONE);
		label_5.setText("\u5143");
		label_5.setBounds(249, 180, 30, 17);
		
		Button button = new Button(shlVip, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 确认
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if(!StringRegexUtils.isRegexpValidate(text_1.getText(),StringRegexUtils.positive_rational_numbers_regexp)) {
					box.setText("系统消息");
					box.setMessage("对不起，您输入钱币的格式不对！");
					box.open(); 
				} else {
					double fu  = Double.parseDouble(text_1.getText());
					if(fu<price) {
						box.setText("系统消息");
						box.setMessage("对不起，您付的金额不够！");
						box.open();
					} else{
						double zhaoling = fu-price;
						String zl = String.valueOf(zhaoling);
						text_2.setText(zl);
						int i = db.update("insert into vip (vip_name,telephone," +
								"shenfenzheng,role,sex)values ('"+vip_name+"','"+telephone+"'," +
										"'"+shenfenzheng+"','"+jibie+"','"+sex+"')");
						if(i!=0) {
							V_Card vc = new V_Card(shlVip, SWT.NONE);
							vc.open(jibie,shenfenzheng);
							
							box.setText("系统消息");
							box.setMessage("登记成功！");
							
							box.open();
						} else {
							box.setText("系统消息");
							box.setMessage("登记失败！");
							box.open();
						}
						
						
					}
				}

			}
		});
		button.setBounds(324, 115, 80, 27);
		button.setText("\u786E\u8BA4");

	}
}
