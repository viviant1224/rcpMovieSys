package cn.com.shxt.view;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.shxt.core.Activator;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.StringRegexUtils;

import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddMovie extends ViewPart {

	public static final String ID = "cn.com.shxt.view.AddMovie"; //$NON-NLS-1$
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private DbUtils db = new DbUtils();
	private DateTime dateTime;
	private DateTime dateTime_1;

	private String fileName;
	private String source;
	private String target;
	private Text text_6;

	public AddMovie() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/view12.jpg"));
		{
			Label label = new Label(container, SWT.NONE);
			label.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
			label.setBounds(133, 66, 80, 17);
			label.setText("\u5F71\u7247\u540D\uFF1A");
		}

		text = new Text(container, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text.setBounds(255, 63, 140, 23);

		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label.setBounds(133, 147, 61, 17);
		label.setText("\u56FD\u5BB6\uFF1A");

		text_1 = new Text(container, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text_1.setBounds(255, 144, 88, 23);

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager
				.getFont("�����п�", 14, SWT.NORMAL));
		lblNewLabel_1.setBounds(133, 238, 80, 17);
		lblNewLabel_1.setText("\u4E3B\u6F14\uFF1A");

		text_6 = new Text(container, SWT.BORDER | SWT.WRAP);
		text_6.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text_6.setBounds(255, 217, 140, 72);

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label_1.setBounds(133, 318, 61, 17);
		label_1.setText("\u5BFC\u6F14\uFF1A");

		text_2 = new Text(container, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text_2.setBounds(255, 315, 140, 23);

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label_2.setBounds(133, 398, 61, 17);
		label_2.setText("Ƭ����");

		text_3 = new Text(container, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text_3.setBounds(255, 398, 88, 23);

		Label lblMin = new Label(container, SWT.NONE);
		lblMin.setBounds(349, 402, 61, 17);
		lblMin.setText("min");

		Label label_3 = new Label(container, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label_3.setBounds(133, 472, 61, 17);
		label_3.setText("\u8BED\u8A00\uFF1A");

		final Combo combo = new Combo(container, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		combo.setBounds(255, 469, 88, 25);
		combo.add("����");
		combo.add("Ӣ��");
		combo.select(0);

		Label label_8 = new Label(container, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label_8.setBounds(443, 66, 95, 17);
		label_8.setText("\u5F71\u7247\u7B80\u4ECB\uFF1A");

		text_5 = new Text(container, SWT.BORDER | SWT.WRAP);
		text_5.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		text_5.setBounds(453, 89, 226, 166);

		Button button = new Button(container, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		final Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/alt_about.gif"));
		lblNewLabel.setBounds(846, 92, 128, 161);

		Button button_1 = new Button(container, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			// ���ͼƬ
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(container.getShell(), SWT.OPEN);// ����һ���ɴ򿪵��ļ�ѡ���
				source = fd.open();
				System.out.println("source:---" + source);
				if (source != null) {
					// ʵ����ƬԤ��
					lblNewLabel.setImage(SWTResourceManager.getImage(source));// ע�⣬��Ҫʹ�����ñ���ͼƬsetBackgroundImage()����
					// ��source������ֵһ��
					// String fileNamessString =
					// fd.getFilterPath()+File.separator+fd.getFileName();
				}
			}
		});
		button_1.setBounds(866, 262, 80, 27);
		button_1.setText("Ԥ��");

		Button button_2 = new Button(container, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			// �ϴ�
			public void widgetSelected(SelectionEvent e) {
				/* ͼƬ�ϴ��� ���̵�iconsĿ¼�� */
				File sourceFile = new File(source);// ͨ��ԴͼƬ������·�� ����Դ�ļ�����

				/* ���iconsĿ¼��λ�� */
				URL url = Activator.getDefault().getBundle()
						.getResource("icons");
				String str = "";
				try {
					str = FileLocator.toFileURL(url).toString().substring(6);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				/* �����ϴ�����ļ����� */
				fileName = new Date().getTime() + ""
						+ (int) (Math.random() * 100000000) + ".jpg";
				System.out.println("������ɵ��ļ�����" + fileName);
				/* ͨ��iconsĿ¼�����ɵ��ļ��� �� �ϳ�Ŀ��ͼƬ�ļ�������·�� */
				target = str + fileName;
				File targetFile = new File(target);
				if (!targetFile.exists()) {
					try {
						targetFile.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				/* ͼƬ���Ʋ��裺 ������������� */
				BufferedInputStream in = null;
				BufferedOutputStream out = null;
				byte[] bs = new byte[(int) sourceFile.length()];
				try {
					in = new BufferedInputStream(
							new FileInputStream(sourceFile));
					in.read(bs);
					out = new BufferedOutputStream(new FileOutputStream(
							targetFile));
					out.write(bs);
					out.flush();
					out.close();
					in.close();

					System.out.println("ͼƬ�ϴ��ɹ�!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(866, 295, 80, 27);
		button_2.setText("\u4E0A\u4F20");
		button.addSelectionListener(new SelectionAdapter() {

			// �жϵ�Ӱ�Ƿ���ڹ�
			public boolean panduan() {
				boolean a = false;
				List<Map<String, Object>> list = db
						.query("select movie_name from movieinfo ");
				for (Map<String, Object> map : list) {
					if (text.getText().trim()
							.equals(map.get("movie_name").toString())) {

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
			 * @����: ���ӰƬ
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				MessageBox box = new MessageBox(new Shell());

				if (panduan()) {
					box.setText("ϵͳ��Ϣ");
					box.setMessage("�Բ��𣬸õ�Ӱ�Ѵ��ڣ�");
					box.open();
				} else if (text.getText().trim().length() == 0
						|| text_1.getText().trim().length() == 0
						|| text_2.getText().trim().length() == 0
						|| text_3.getText().trim().length() == 0
						|| text_6.getText().trim().length() == 0) {
					box.setText("ϵͳ��Ϣ");
					box.setMessage("�Բ���������������Ҫ�������Ϣ��");
					box.open();
				} else if (!StringRegexUtils.isRegexpValidate(text_3.getText(),
						StringRegexUtils.integer_regexp)) {
					box.setText("ϵͳ��Ϣ");
					box.setMessage("�Բ���Ƭ����������������");
					box.open();
				} else {
					int i = db
							.update("insert into movieinfo(movie_name,nation,actor,director,movie_time,movie_lan,"
									+ "infor,movie_img)values"
									+ "('"
									+ text.getText()
									+ "','"
									+ text_1.getText()
									+ "','"
									+ text_6.getText()
									+ "','"
									+ text_2.getText()
									+ "',"
									+ "'"
									+ text_3.getText()
									+ "','"
									+ combo.getText()
									+ "','"
									+ text_5.getText()
									+ "',"
									+ "'"
									+ fileName
									+ "')");
					if (i != 0) {
						box.setText("ϵͳ��Ϣ");
						box.setMessage("��ӳɹ���");
						box.open();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						text_3.setText("");
						combo.select(0);
						text_5.setText("");
						text_6.setText("");
					} else {
						box.setText("ϵͳ��Ϣ");
						box.setMessage("���ʧ�ܣ�");
						box.open();
					}

				}
			}

		});
		button.setBounds(846, 468, 80, 27);
		button.setText("\u786E\u8BA4\u6DFB\u52A0");

		Label label_9 = new Label(container, SWT.NONE);
		label_9.setFont(SWTResourceManager.getFont("�����п�", 14, SWT.NORMAL));
		label_9.setBounds(754, 66, 88, 17);
		label_9.setText("\u5F71\u7247\u6D77\u62A5\uFF1A");

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
