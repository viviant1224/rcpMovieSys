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
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;

public class Login extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;// �û���
	private Text text_1;
	private DbUtils db = new DbUtils();
	public static String role;
	public static String name;
	private Text text_2;// ����
	private Text text_3;// ��֤��
	private ProgressBar progressBar = null;
	private int z = 0;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Login(Shell parent, int style) {
		super(parent, style);

		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
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

		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/20071113164419984_2.jpg"));
		shell.setSize(450, 300);
		shell.setText("\u767B\u5F55\u754C\u9762");

		// ��ȡ�����
		double d = Math.random();
		int i = (int) (d * 100000);
		String s = "" + i;
		final String str = s.substring(0, 4);

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label.setBounds(77, 82, 73, 23);
		label.setText("�û�����");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label_1.setBounds(77, 135, 61, 23);
		label_1.setText("���룺");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		label_2.setBounds(77, 183, 62, 20);
		label_2.setText("\u9A8C\u8BC1\u7801\uFF1A");

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setFont(SWTResourceManager
				.getFont("Goudy Stout", 12, SWT.ITALIC));
		text_2.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/yanzheng.jpg"));
		text_2.setBounds(145, 181, 71, 23);
		text_2.setEnabled(false);
		text_2.setEditable(false);
		text_2.setText("" + str);

		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text.setBounds(220, 79, 73, 23);

		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text_1.setBounds(220, 132, 73, 23);

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("΢���ź�", 12, SWT.NORMAL));
		text_3.setBounds(222, 180, 73, 23);

		Link link_1 = new Link(shell, SWT.NONE);
		link_1.setBounds(305, 82, 53, 17);
		link_1.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @����: �û�ע��
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				Sign s = new Sign(shell, SWT.CLOSE);
				s.open();
			}
		});
		link_1.setText("<a>�û�ע��</a>");

		Link link = new Link(shell, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @����: ��������
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				Wangjimima w = new Wangjimima(shell, SWT.CLOSE);
				w.open();
			}
		});
		link.setBounds(305, 135, 73, 17);
		link.setText("<a>��������?</a>");

		Button button = new Button(shell, SWT.NONE);
		button.setBounds(298, 218, 80, 27);
		button.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong",
				"icons/657a3ba64f0fb90464c3c00bd572c858.jpg"));
		button.addSelectionListener(new SelectionAdapter() {
			/**
			 * @����: ������֤��
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public String yanzhengma() {
				double d = Math.random();
				int i = (int) (d * 100000);
				String s = "" + i;
				final String str = s.substring(0, 4);
				return str;
			}

			@Override
			/**
			 * @����: ��¼
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {

				List<Map<String, Object>> list = db
						.query("select *from user where NAME = '"
								+ text.getText() + "'");
				final MessageBox messageBox = new MessageBox(new Shell());
				if (list.size() > 0) {
					role = list.get(0).get("role").toString();
					name = list.get(0).get("NAME").toString();
					Map<String, Object> map = list.get(0);
					if (text_3.getText().trim().equals(text_2.getText())) {
						if (text_1.getText().trim()
								.equals(map.get("pw").toString())) {
							// ������
							progressBar = new ProgressBar(shell, SWT.NONE);
							progressBar.setBounds(0, 255, 444, 17);
							progressBar.setMaximum(200);

							progressBar.getDisplay().asyncExec(new Runnable() {

								public void run() {
									for (z = 1; z <= 100; z++) {
										progressBar.setSelection(z * 20);
										try {

											if (z == 100) {
												messageBox.setText("ϵͳ��Ϣ");
												messageBox.setMessage("��¼�ɹ���");
												messageBox.open();

												result = "��¼�ɹ�";
												shell.dispose();
											}
											Thread.sleep(10);
										} catch (Exception q) {
											q.printStackTrace();
										}

									}

								}
							});

						} else {
							text_2.setText("" + yanzhengma());
							messageBox.setText("ϵͳ��Ϣ");
							messageBox.setMessage("�Բ����������");
							messageBox.open();
						}
					} else {

						text_2.setText("" + yanzhengma());
						messageBox.setText("ϵͳ��Ϣ");
						messageBox.setMessage("��֤������������������룡");
						messageBox.open();

					}

				} else {
					text_2.setText("" + yanzhengma());
					messageBox.setText("ϵͳ��Ϣ");
					messageBox.setMessage("�Բ����û��������ڣ�");
					messageBox.open();
				}

			}
		});
		button.setText("\u767B\u5F55");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			public String yanzhengma() {
				double d = Math.random();
				int i = (int) (d * 100000);
				String s = "" + i;
				final String str = s.substring(0, 4);
				return str;
			}

			@Override
			/**
			 * @����: ˢ����֤��
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				text_2.setText("" + yanzhengma());
			}
		});
		button_1.setBounds(301, 176, 73, 27);
		button_1.setText("\u770B\u4E0D\u6E05\u695A");

	}
}
