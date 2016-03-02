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

public class Fuqian extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private double price;//单价
	private double lastPrice;//z总价
	private DbUtils db = new DbUtils();
	private String movie_name;
	private String showtime;
	private int count;//计数
	private List myList = new ArrayList();
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Fuqian(Shell parent, int style) {
		super(parent, style);
		setText("付钱");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(double lastPrice,String movie_name,int count,double price,List myList,String showtime) {
		this.showtime = showtime;
		this.myList = myList;
		this.lastPrice = lastPrice;
		this.price = price;
		this.movie_name = movie_name;
		this.count = count;
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
		shell = new Shell(getParent(), SWT.CLOSE);
		shell.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/fuqian.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(563, 348);
		shell.setText(getText());
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label.setBounds(140, 85, 103, 37);
		label.setText("\u5E94\u4ED8\u91D1\u989D\uFF1A");
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text.setBounds(256, 82, 73, 37);
		text.setText(""+lastPrice);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_1.setBounds(140, 148, 103, 26);
		label_1.setText("\u5B9E\u6536\u91D1\u989D\uFF1A");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text_1.setBounds(256, 145, 73, 37);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_2.setBounds(140, 221, 103, 37);
		label_2.setText("\u627E\u96F6\uFF1A");
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		text_2.setBounds(256, 218, 73, 33);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(346, 92, 30, 17);
		label_3.setText("\u5143");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u5143");
		label_4.setBounds(346, 155, 30, 17);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u5143");
		label_5.setBounds(346, 228, 30, 17);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 确认付钱
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
					if(fu<lastPrice) {
						box.setText("系统消息");
						box.setMessage("对不起，您付的金额不够！");
						box.open();
					} else{
						double zhaoling = fu-lastPrice;
						String zl = String.valueOf(zhaoling);
						text_2.setText(zl);
						for( int p = 0;p<myList.size();p++) {
							String str = myList.get(p).toString();
							String[] str1 = str.split(" ");
						int i =	db.update("UPDATE sell SET state = '不可用',sell_price = '"+price+"' WHERE movie_name = '"+movie_name+"' AND showtime = '"+showtime+"' AND ROW = '"+str1[0]+"' AND lie = '"+str1[1]+"'");
						
							System.out.println("-price"+price);
							System.out.println("-movie_name"+movie_name);
							System.out.println("-showtime"+showtime);
							Chupiao c = new Chupiao(shell, SWT.NONE);
							c.open(movie_name,price, str);
						}
						
						List<Map<String,Object>> list = db.query("select showtime,hour_time,min_time from fangying where movie_name = '"+movie_name+"'");
						String time = list.get(0).get("showtime").toString();
						String hour = list.get(0).get("hour_time").toString();
						String min = list.get(0).get("min_time").toString();
							box.setText("系统消息");
							box.setMessage("成功购买"+count+"张---"+movie_name+"---的影票。上映时间为："+time+"  "+hour+":"+min);
							box.open();
							
							
						
						myList.removeAll(myList);//移除座位
						Maipiao.myList.removeAll(myList);//移除座位
						
					}
				}

			}
		});
		button.setBounds(414, 259, 80, 27);
		button.setText("\u786E\u8BA4");
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setBounds(473, 10, 61, 17);
		label_6.setText("\u6536\u94F6\u5458\uFF1A");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(473, 46, 61, 17);
		lblNewLabel.setText(Login.name);
		
		

	}
}
