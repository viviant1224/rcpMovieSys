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
	private Text text;// Ӱ����
	private Text text_1;// ����
	private Text text_2;// ����
	private DbUtils db = new DbUtils();// ���ݿ����ӵĹ��߰�

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
		container.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/m2.jpg"));
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		{
			Label lblNewLabel = new Label(container, SWT.NONE);
			lblNewLabel.setFont(SWTResourceManager.getFont("�����п�", 20,
					SWT.NORMAL));
			lblNewLabel.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_LIST_FOREGROUND));
			lblNewLabel.setBounds(514, 132, 162, 23);
			lblNewLabel.setText("��ӳ�����ƣ�");
		}
		{
			text = new Text(container, SWT.BORDER);
			text.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
			text.setBounds(682, 127, 151, 28);
		}
		{
			Label lblNewLabel_1 = new Label(container, SWT.NONE);
			lblNewLabel_1.setFont(SWTResourceManager.getFont("�����п�", 20,
					SWT.NORMAL));
			lblNewLabel_1.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_LIST_FOREGROUND));
			lblNewLabel_1.setBounds(514, 267, 108, 23);
			lblNewLabel_1.setText("\u5217\u6570\uFF1A");
		}
		{
			text_1 = new Text(container, SWT.BORDER);
			text_1.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
			text_1.setBounds(682, 262, 151, 28);
		}
		{
			Label lblNewLabel_2 = new Label(container, SWT.NONE);
			lblNewLabel_2.setFont(SWTResourceManager.getFont("�����п�", 20,
					SWT.NORMAL));
			lblNewLabel_2.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_LIST_FOREGROUND));
			lblNewLabel_2.setBounds(514, 202, 162, 23);
			lblNewLabel_2.setText("��ӳ�����ͣ�");
		}

		final Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		combo.setBounds(682, 202, 151, 27);
		combo.add("3D");
		combo.add("IMAX");
		combo.add("��ͨ");
		combo.select(0);

		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager
				.getFont("�����п�", 20, SWT.NORMAL));
		lblNewLabel_3.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_FOREGROUND));
		lblNewLabel_3.setBounds(514, 330, 108, 27);
		lblNewLabel_3.setText("������");
		{
			text_2 = new Text(container, SWT.BORDER);
			text_2.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
			text_2.setBounds(682, 330, 151, 28);
		}
		{
			Button button = new Button(container, SWT.NONE);
			button.setFont(SWTResourceManager.getFont("�����п�", 20, SWT.NORMAL));
			button.addSelectionListener(new SelectionAdapter() {
				/**
				 * @����: �жϷ�ӳ���Ƿ����
				 * @���ߣ�������
				 * @�汾��0.9
				 * @����ʱ�䣺2013-6-04����11:20:59
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
				 * @����: ��ӷ�ӳ��
				 * @���ߣ�������
				 * @�汾��0.9
				 * @����ʱ�䣺2013-6-04����11:20:59
				 */
				public void widgetSelected(SelectionEvent e) {
					MessageBox box = new MessageBox(new Shell());
					if (panduan()) {
						box.setText("ϵͳ��Ϣ");
						box.setMessage("����ӵķ�ӳ���Ѵ��ڣ�");
						box.open();
					} else {
						if (text.getText().trim().length() == 0
								|| text.getText().trim().length() == 0
								|| text.getText().trim().length() == 0) {
							box.setText("ϵͳ��Ϣ");
							box.setMessage("������������Ҫ�������Ϣ��");
							box.open();
						} else if (!StringRegexUtils.isRegexpValidate(
								text_1.getText(),
								StringRegexUtils.integer_regexp)) {
							box.setText("ϵͳ��Ϣ");
							box.setMessage("�Բ���������������������");
							box.open();
						} else if (!StringRegexUtils.isRegexpValidate(
								text_2.getText(),
								StringRegexUtils.integer_regexp)) {
							box.setText("ϵͳ��Ϣ");
							box.setMessage("�Բ���������������������");
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
													+ "',state = '����'");
								}
							}

							if (i != 0) {
								box.setText("ϵͳ��Ϣ");
								box.setMessage("��ӳɹ���");
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
			button.setText("ȷ��");
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
