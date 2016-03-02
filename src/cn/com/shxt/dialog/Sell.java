package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.SWTResourceManager;

public class Sell extends Dialog {

	protected Object result;
	protected Shell shell;
	private String roomname;
	private String movie_name;
	private String row;
	private String lie;
	private DbUtils db = new DbUtils();
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Sell(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String roomname,String movie_name) {
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

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		/*Shell shell_1 = new Shell();
		shell_1.setSize(655, 530);*/
		Shell shell = new Shell(getParent(), SWT.CLOSE);
		shell.setSize(669, 479);
		shell.setText(getText());
		
		final MessageBox box = new MessageBox(shell);
		
		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(396, 30, 61, 17);
		label.setText("\u6E29\u99A8\u63D0\u793A\uFF1A");
		
		Label lblNewLabel_1 = new Label(shell, SWT.WRAP);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setBounds(456, 66, 181, 100);
		lblNewLabel_1.setText("      \u53CC\u51FB\u8D2D\u7968\uFF0C\u5982\u679C\u5EA7\u4F4D\u663E\u793A\u7EA2\u8272\u72B6\u6001\u5373\u53EF\u8D2D\u7968\uFF0C\u5982\u679C\u5EA7\u4F4D\u4E3A\u7070\u8272\u72B6\u6001\uFF0C\u8868\u793A\u8BE5\u5EA7\u4F4D\u5DF2\u552E\u51FA\u3002");
		
		int j = 0;
		int k = 0;
		
		
	
		
		for(int crow = 1;crow<=Integer.parseInt(row);crow++) {
			
		
		
		for(int clie = 1;clie<=Integer.parseInt(lie);clie++) {
			final String s = ""+crow+" "+clie;
			final String[] ss = s.split(" ");
//			System.out.println("crow="+ss[0]);
//			System.out.println("clie="+ss[1]);
			final Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setText(ss[0]+"-"+ss[1]);
			List<Map<String,Object>> list = db.query("select state from sell where roomname = '"+roomname+"' " +
					"and movie_name = '"+movie_name+"' and row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
			
			if(list.get(0).get("state").toString().equals("可用")) {
//				db.update("update zuowei set state = '不可用' where row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
				lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong",
						"icons/QQ\u622A\u56FE20130615161817.jpg"));
			}else if(list.get(0).get("state").toString().equals("不可用")){
//				db.update("update zuowei set state = '可用' where row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
			   	lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", 
		    			"icons/bukeyong.jpg"));
				
			}
//			lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/QQ\u622A\u56FE20130615161817.jpg"));
			
			
			lblNewLabel.setBounds(102+k*60, 109+j*60, 39, 43);
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					System.out.println("crow="+ss[0]);
					System.out.println("clie="+ss[1]);
					List<Map<String,Object>> list = db.query("select state from sell where roomname = '"+roomname+"' and movie_name = '"+movie_name+"'  and row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
					if(list.get(0).get("state").equals("可用")) {
						lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/bukeyong.jpg"));
						box.setText("系统消息");
						box.setMessage("购票");
						box.open();
						db.update("update sell set state = '不可用' where roomname = '"+roomname+"' and movie_name = '"+movie_name+"' and row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
					} else if(list.get(0).get("state").equals("不可用")){
//						db.update("update sell set state = '可用' where roomname = '"+roomname+"' and movie_name = '"+movie_name+"' and row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
						box.setText("系统消息");
						box.setMessage("该座位已售出");
						box.open();
					}
				}
			});
			if(k%Integer.parseInt(row)==Integer.parseInt(row)-1){
				j++;
				k=-1;
			}
			k++;
		}
		
		}
		
		

	}

}
