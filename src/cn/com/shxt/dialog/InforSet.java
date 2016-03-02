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
import org.eclipse.swt.widgets.Text;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class InforSet extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;//�û���
	private Label label;
	private Text text_1;//ԭ����
	private Label label_1;
	private Text text_2;//������
	private Label label_2;
	private Text text_3;//������
	private Label label_3;
	private Text text_4;//ϲ����ɫ
	private Label label_4;
	private Text text_5;//ϲ����Ӱ
	private Label label_5;
	private Text text_6;//סַ
	private Button button;
	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public InforSet(Shell parent, int style) {
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
		shell.setSize(516, 309);
		shell.setText("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		lblNewLabel.setBounds(51, 29, 90, 33);
		lblNewLabel.setText("�û�����");
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text.setEnabled(false);
		text.setBounds(182, 29, 103, 30);
		final String str = Login.name;
		text.setText(str);
		
		label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label.setBounds(51, 87, 73, 23);
		label.setText("ԭ���룺");
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text_1.setBounds(182, 84, 103, 26);
		
		label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label_1.setBounds(51, 146, 103, 23);
		label_1.setText("���������룺");
		
		text_2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_2.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text_2.setBounds(182, 143, 103, 26);
		
		label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label_2.setBounds(51, 195, 125, 23);
		label_2.setText("�ٴ����������룺");
		
		text_3 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_3.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text_3.setBounds(182, 192, 103, 26);
		
		label_3 = new Label(shell, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label_3.setBounds(298, 29, 97, 23);
		label_3.setText("\u559C\u6B22\u7684\u989C\u8272\uFF1A");
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text_4.setBounds(401, 26, 99, 30);
		
		label_4 = new Label(shell, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label_4.setBounds(298, 87, 97, 23);
		label_4.setText("\u559C\u6B22\u7684\u7535\u5F71\uFF1A");
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text_5.setBounds(401, 87, 99, 30);
		
		label_5 = new Label(shell, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label_5.setBounds(298, 149, 84, 20);
		label_5.setText("\u4F4F\u5740\uFF1A");
		
		text_6 = new Text(shell, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text_6.setBounds(401, 146, 99, 30);
		
		button = new Button(shell, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			/**
			 * @����: �ж��û��Ƿ����
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public boolean panduan() {
				boolean a = true; 
				List<Map<String,Object>> list = db.query("select NAME from user ");
				for(Map<String,Object> map:list) {
					if(text.getText().trim().equals(map.get("NAME").toString())) {
						
						a = true;
						break;
					}else{
						a = false;
					}
					
				}
				return a;
			}
			@Override
			/**
			 * @����:�޸��û���Ϣ
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox = new MessageBox(new Shell());
				List<Map<String,Object>> list = db.query("select pw from user where NAME = '"+text.getText()+"'");
				
					if(text_2.getText().equals(text_3.getText())&&text_2!=null) {
						if(
								text_4.getText().trim().length()==0||
								text_5.getText().trim().length()==0||
								text_6.getText().trim().length()==0) {
							messageBox.setText("ϵͳ��Ϣ");
							messageBox.setMessage("������������Ϣ��");
							messageBox.open();
						} else {
							if (text_1.getText().equals(list.get(0).get("pw").toString())) {
								
								int i = db.update("update user set pw = '"+text_2.getText()+"'," +
										"f_color = '"+text_4.getText()+"',f_movie = '"+text_5.getText()+"'," +
												"address = '"+text_6.getText()+"' where NAME = '"+str+"'");
								if(i!=0) {
									messageBox.setText("ϵͳ��Ϣ");
									messageBox.setMessage("�޸ĳɹ���");
									messageBox.open();
									text_1.setText("");text_2.setText("");
									text_3.setText("");text_4.setText("");text_5.setText("");text_6.setText("");
								} else {
									messageBox.setText("ϵͳ��Ϣ");
									messageBox.setMessage("�޸�ʧ�ܣ�");
									messageBox.open();
								} 
							} else {
								
								messageBox.setText("ϵͳ��Ϣ");
								messageBox.setMessage("ԭ�����������");
								messageBox.open();
							}
						}
					} else {
						messageBox.setText("ϵͳ��Ϣ");
						messageBox.setMessage("�����������벻һ��");
						messageBox.open();
					}
			
				
			}
		});
		button.setBounds(401, 243, 80, 27);
		button.setText("\u786E\u5B9A\u4FEE\u6539");

	}
}
