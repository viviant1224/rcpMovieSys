package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;
import cn.com.shxt.tools.StringRegexUtils;

public class ShowRoomSet extends Dialog {

	protected Object result;
	protected Shell shell;
	private DbUtils db = new DbUtils();
	private Text text_1;// ��ӳ����
	private Text text_2;// ����
	private Text text_3;// ����

	private String id;
	private String roomname;
	private String lie;
	private String type;
	private String row;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ShowRoomSet(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open(String id) {
		this.id = id;
		List<Map<String, Object>> i = db
				.query("select roomname,lie,type,row from showroom where id = '"
						+ id + "'");

		this.roomname = i.get(0).get("roomname").toString();
		this.lie = i.get(0).get("lie").toString();
		this.type = i.get(0).get("type").toString();
		this.row = i.get(0).get("row").toString();
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
		shell.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/bu1.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(597, 410);
		shell.setText("\u653E\u6620\u5385\u4FEE\u6539");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager
				.getFont("�����п�", 14, SWT.NORMAL));
		lblNewLabel_1.setBounds(31, 41, 102, 17);
		lblNewLabel_1.setText("��ӳ�����ƣ�");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text_1.setBounds(150, 38, 107, 23);
		text_1.setText(roomname);

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label.setBounds(31, 215, 61, 17);
		label.setText("������");

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text_2.setBounds(150, 212, 107, 23);
		text_2.setText(lie);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label_1.setBounds(31, 134, 61, 17);
		label_1.setText("���ͣ�");

		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		combo.setBounds(150, 131, 107, 25);
		combo.add("3D");
		combo.add("IMAX");
		combo.add("��ͨ");
		combo.setText(type);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label_2.setBounds(31, 299, 61, 17);
		label_2.setText("������");

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text_3.setBounds(150, 296, 107, 23);
		text_3.setText(row);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton
				.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public boolean panduan() {
				boolean a = false;
				List<Map<String, Object>> list = db
						.query("select roomname from fangying ");
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).get("roomname").toString().equals(roomname)) {
						a = true;
					}
				}
				return a;

			}

			/**
			 * @����: �жϷ�ӳ���Ƿ����
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public boolean panduan_1() {
				boolean a = false;
				List<Map<String, Object>> list = db
						.query("SELECT roomname FROM showroom where id not in('"
								+ id + "')");
				for (int i = 0; i < list.size(); i++) {
					if (text_1.getText().trim()
							.equals(list.get(i).get("roomname").toString())) {
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
			 * @����: �޸Ľ���
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());
				if (panduan()) {
					box.setText("ϵͳ��Ϣ");
					box.setMessage("�Բ��𣬷�ӳ������ڷ�ӳ��Ӱ�������޸ģ�");
					box.open();
				} else {
					if (panduan_1()) {
						box.setText("ϵͳ��Ϣ");
						box.setMessage("�Բ��𣬸÷�ӳ���Ѵ��ڣ�");
						box.open();
					} else {
						if (text_1.getText().trim().length() == 0
								|| text_2.getText().trim().length() == 0
								|| text_3.getText().trim().length() == 0) {
							box.setText("ϵͳ��Ϣ");
							box.setMessage("�Բ���������������Ҫ�������Ϣ��");
							box.open();
						} else if (!StringRegexUtils.isRegexpValidate(
								text_2.getText(),
								StringRegexUtils.integer_regexp)) {
							box.setText("ϵͳ��Ϣ");
							box.setMessage("�Բ�������������������");
							box.open();
						} else if (!StringRegexUtils.isRegexpValidate(
								text_3.getText(),
								StringRegexUtils.integer_regexp)) {
							box.setText("ϵͳ��Ϣ");
							box.setMessage("�Բ�������������������");
							box.open();
						} else {

							boolean flag = MessageDialog.openConfirm(RcpUtil
									.getPage().getWorkbenchWindow().getShell(),
									"ϵͳ��Ϣ", "ȷ���޸ĸ÷�ӳ����");
							if (flag == true) {
								int i = db
										.update("update showroom set roomname = "
												+ "'"
												+ text_1.getText()
												+ "',lie = '"
												+ text_2.getText()
												+ "',"
												+ "type = '"
												+ combo.getText()
												+ "',row = '"
												+ text_3.getText()
												+ "' where id = '" + id + "'");

								int k = db
										.update("delete from zuowei where roomname = '"
												+ roomname + "'");

								int crow = Integer.parseInt(text_3.getText());
								int clie = Integer.parseInt(text_2.getText());

								System.out.println("crow =" + crow + " clie ="
										+ clie);
								for (int row = 1; row <= crow; row++) {
									for (int col = 1; col <= clie; col++) {
										int l = db
												.update("insert into zuowei set roomname = '"
														+ text_1.getText()
														+ "', row = '"
														+ row
														+ "',lie = '"
														+ col
														+ "',state = '����'");
									}

								}

								if (i != 0 && k != 0) {
									box.setText("ϵͳ��Ϣ");
									box.setMessage("�޸ĳɹ���");
									box.open();
									result = "�ɹ�";
									text_1.setText("");
									text_2.setText("");
									text_3.setText("");
									shell.dispose();
								} else {
									box.setText("ϵͳ��Ϣ");
									box.setMessage("�޸�ʧ�ܣ�");
									box.open();
								}

							}

						}
					}
				}

			}
		});
		btnNewButton.setBounds(501, 269, 80, 27);
		btnNewButton.setText("�޸�");

	}
}
