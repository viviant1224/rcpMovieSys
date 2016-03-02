package cn.com.shxt.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
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
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;
import org.eclipse.swt.widgets.Link;

public class Maipiao extends Dialog {

	protected Object result;
	protected Shell shell;

	private String roomname;
	private String movie_name;
	private String showtime;
	private String price;
	private String row;
	private String lie;
	private DbUtils db = new DbUtils();
	private int count;
	private Table table;
	private String ss1;
	private String ss2;
	public static  List myList = new ArrayList();;
//	private static String[] ss;
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Maipiao(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String roomname,String movie_name,String price,String showtime) {
		this.showtime = showtime;
		this.price = price;
		this.movie_name = movie_name;
		this.roomname = roomname;
		List<Map<String,Object>> list = db.query("select row,lie from showroom where roomname = '"+roomname+"' ");

		this.row = list.get(0).get("row").toString();
		this.lie = list.get(0).get("lie").toString();
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
	
	public void getSell(){
		
	}
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX);
		shell.setToolTipText("\u6CE8\u610F\uFF1A");
		shell.setSize(849, 545);
		shell.setText("卖票界面");
		
		final MessageBox box = new MessageBox(shell);
		
		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(652, 10, 61, 17);
		label.setText("\u6E29\u99A8\u63D0\u793A\uFF1A");
		
		Label lblNewLabel_1 = new Label(shell, SWT.WRAP);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setBounds(652, 33, 181, 100);
		lblNewLabel_1.setText("      \u53CC\u51FB\u8D2D\u7968\uFF0C\u5982\u679C\u5EA7\u4F4D\u663E\u793A\u7EA2\u8272\u72B6\u6001\u5373\u53EF\u8D2D\u7968\uFF0C\u5982\u679C\u5EA7\u4F4D\u4E3A\u7070\u8272\u72B6\u6001\uFF0C\u8868\u793A\u8BE5\u5EA7\u4F4D\u5DF2\u552E\u51FA\u3002\uFF08\u6CE8\u610F\uFF1A\u5EA7\u4F4D\u4E3A\u6D45\u7EFF\u8272\u8868\u793A\u8BE5\u5EA7\u4F4D\u4E0D\u53EF\u7528\uFF0C\u62A5\u4FEE\u4E2D\u3002\uFF09");
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 普通用户购票
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				if(count!=0) {
					PuTongJieZhang ptjz = new PuTongJieZhang(shell, SWT.NONE);
					ptjz.open(price,count,movie_name,myList,showtime);
				} else {
					MessageBox box = new MessageBox(new Shell());
					box.setText("系统消息");
					box.setMessage("抱歉，未购买任何影票！");
					box.open();
				}
				
			}
		});
		button.setBounds(652, 207, 80, 27);
		button.setText("\u666E\u901A\u7528\u6237\u7ED3\u8D26");
		
		Button btnVip = new Button(shell, SWT.NONE);
		btnVip.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: VIP购票
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if(count>1) {
					
					box.setText("系统消息");
					box.setMessage("对不起一个VIP账户仅限购买一张票！");
					box.open();
				} else if(count==0) {
					box.setText("系统消息");
					box.setMessage("对不起,您未购买任何一张影票！");
					box.open();
				} else{
					VIPJieZhang vjz = new VIPJieZhang(shell, SWT.NONE);
					vjz.open(movie_name, price,myList,showtime);
				}
			}
		});
		btnVip.setBounds(652, 293, 80, 27);
		btnVip.setText("VIP\u7528\u6237\u7ED3\u8D26");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setText("\u6CE8\u610F\uFF1A");
		label_1.setBounds(652, 326, 61, 17);
		
		Label lblNewLabel_2 = new Label(shell, SWT.WRAP);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_2.setBounds(652, 349, 132, 35);
		lblNewLabel_2.setText("\u4E00\u4E2AVIP\u8D26\u6237" +
				"\u4EC5\u9650\u8D2D\u4E70\u4E00\u5F20\u5F71\u7968\u3002");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(20, 10, 48, 17);
		label_2.setText("\u7968\u4EF7\u8868\uFF1A");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 29, 395, 51);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(89);
		tableColumn.setText("\u666E\u901A\u7968\u4EF7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setText("\u661F\u7EA7\u4F1A\u5458\u7968\u4EF7");
		tableColumn_1.setWidth(100);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u6708\u4EAE\u7EA7\u4F1A\u5458\u7968\u4EF7");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u592A\u9633\u7EA7\u4F1A\u5458\u7968\u4EF7");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		
		double xing = Double.parseDouble(price)*0.9;
		String xing_1 = String.valueOf(xing);
		
		double yue = Double.parseDouble(price)*0.85;
		String yue_1 = String.valueOf(yue);
		
		double yang = Double.parseDouble(price)*0.75;
		String yang_1 = String.valueOf(yang);
		
		tableItem.setText(0,price);
		tableItem.setText(1,xing_1);
		tableItem.setText(2,yue_1);
		tableItem.setText(3,yang_1);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(421, 10, 61, 17);
		label_3.setText("\u8BF4\u660E\uFF1A");
		
		Label lblNewLabel_3 = new Label(shell, SWT.WRAP);
		lblNewLabel_3.setBounds(421, 29, 220, 69);
		lblNewLabel_3.setText("      \u666E\u901A\u4F1A\u5458\u4E0D\u6253\u6298" +
				"\uFF0C\u661F\u7EA7\u4F1A\u5458\u62539\u6298\uFF0C\u6708\u4EAE\u7EA7" +
				"\u4F1A\u5458\u62538.5\u6298\uFF0C\u592A\u9633\u7EA7\u4F1A\u5458\u62537.5\u6298");
		
		Button btnvip = new Button(shell, SWT.NONE);
		btnvip.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 注册VIP
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				VipSign vs = new VipSign(shell, SWT.NONE);
				vs.open();
			}
		});
		btnvip.setBounds(652, 390, 80, 27);
		btnvip.setText("\u6CE8\u518CVIP\u7528\u6237");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(354, 10, 61, 17);
		label_4.setText("\u5355\u4F4D\uFF1A\u5143");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(738, 212, 72, 17);
		lblNewLabel_4.setText("\uFF08\u53D6\u6D88\u8BA2\u5355\uFF09");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setBounds(652, 133, 61, 17);
		label_5.setText("\u5F71\u7247\u540D\uFF1A");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		lblNewLabel_5.setBounds(652, 170, 158, 31);
		lblNewLabel_5.setText(movie_name);
		
		getlist();
		

	}
	/**
	 * @描述: 显示座位
	 * @作者：黄威威
	 * @版本：0.9
	 * @开发时间：2013-6-04上午11:20:59
	 */
	public void getlist() {
		final MessageBox box = new MessageBox(shell);
		int j = 0;
		int k = 0;
		for(int crow = 1;crow<=Integer.parseInt(row);crow++) {
		for(int clie = 1;clie<=Integer.parseInt(lie);clie++) {
			final String s = ""+crow+" "+clie;
			final String[] ss = s.split(" ");

			final Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
			lblNewLabel.setText(ss[0]+"-"+ss[1]);
			List<Map<String,Object>> list = db.query("select state from sell where roomname = '"+roomname+"' " +
					"and movie_name = '"+movie_name+"' and row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
			
			if(list.get(0).get("state").toString().equals("可用")) {
				

				lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong",
						"icons/QQ\u622A\u56FE20130615161817.jpg"));
			}else if(list.get(0).get("state").toString().equals("不可用")){
				lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

			   	lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", 
		    			"icons/bukeyong.jpg"));
				
			} else if(list.get(0).get("state").toString().equals("维修")) {
				lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", 
		    			"icons/bu.jpg"));
				
			}
			
			lblNewLabel.setBounds(102+k*60, 109+j*60, 39, 43);
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				/**
				 * @描述: 订票
				 * @作者：黄威威
				 * @版本：0.9
				 * @开发时间：2013-6-04上午11:20:59
				 */
				public void mouseDoubleClick(MouseEvent e) {
					System.out.println("crow="+ss[0]);
					System.out.println("clie="+ss[1]);
					List<Map<String,Object>> list = db.query("select state from sell where roomname = '"+roomname+"' and movie_name = '"+movie_name+"'  and row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
					if(list.get(0).get("state").equals("可用")) {
						
						boolean flag = MessageDialog.openConfirm(RcpUtil.getPage().getWorkbenchWindow().getShell(),
								"系统消息", "确定预定该座位吗？");
						if(flag==true) {
							
							lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/bukeyong.jpg"));
							box.setText("系统消息");
							box.setMessage("订票成功，座位号为：第"+ss[0]+"排，第"+ss[1]+"列");
							box.open();
							ss1 = ss[0];
							ss2 = ss[1];
							String str = ""+ss1+" "+ss2;
							
							myList.add(str);
							count = myList.size();
//							db.update("update sell set state = '不可用' where roomname = '"+roomname+"' and movie_name = '"+movie_name+"' and row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
						} else {
							
							box.setText("系统消息");
							box.setMessage("订票失败！");
							box.open();
							myList.removeAll(myList);
						}
						
						
					} else if(list.get(0).get("state").equals("不可用")){
//						db.update("update sell set state = '可用' where roomname = '"+roomname+"' and movie_name = '"+movie_name+"' and row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
						box.setText("系统消息");
						box.setMessage("该座位已售出");
						box.open();
					} else if(list.get(0).get("state").equals("维修")) {
						box.setText("系统消息");
						box.setMessage("该座位不可用");
						box.open();
					}
				}
			});
			if(k%Integer.parseInt(lie)==Integer.parseInt(lie)-1){
				j++;
				k=-1;
			}
			k++;
		}
		
		}
	}
}
