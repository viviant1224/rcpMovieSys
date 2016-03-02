package cn.com.shxt.dialog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
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

public class MovieSet extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;

	private DateTime dateTime;
	private DateTime dateTime_1;
	private DbUtils db = new DbUtils();
	private String source;
	private String fileName;
	private String target;

	private String id;
	private String movie_name;
	private String nation;
	private String movie_lan;
	private String director;
	private String movie_time;

	private String infor;

	private String actor;
	private String movie_img;
	private String str;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public MovieSet(Shell parent, int style) {
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
				.query("select movie_name,nation,director,movie_time,movie_lan,infor,actor,movie_img from movieinfo where id = '"
						+ id + "'");

		this.movie_name = i.get(0).get("movie_name").toString();
		this.nation = i.get(0).get("nation").toString();
		this.director = i.get(0).get("director").toString();
		this.movie_time = i.get(0).get("movie_time").toString();

		this.movie_lan = i.get(0).get("movie_lan").toString();

		this.infor = i.get(0).get("infor").toString();

		this.actor = i.get(0).get("actor").toString();
		this.movie_img = i.get(0).get("movie_img").toString();

		str = i.get(0).get("movie_img").toString();
		System.out.println("============================="
				+ i.get(0).get("movie_img").toString());
		System.out.println("---------" + movie_img);

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
				"rcpyingyuanxitong", "icons/xiaxian.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(892, 514);
		shell.setText("\u5F71\u7247\u7F16\u8F91");

		Label label_10 = new Label(shell, SWT.NONE);
		label_10.setBounds(31, 10, 61, 17);
		label_10.setText("��ţ�");

		text_6 = new Text(shell, SWT.BORDER);
		text_6.setEnabled(false);
		text_6.setBounds(129, 7, 73, 23);
		text_6.setText(id);

		Label label_11 = new Label(shell, SWT.NONE);
		label_11.setBounds(31, 89, 61, 17);
		label_11.setText("���ݣ�");

		text_7 = new Text(shell, SWT.BORDER | SWT.WRAP);
		text_7.setBounds(129, 83, 128, 56);
		text_7.setText(actor);

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(31, 48, 61, 17);
		label.setText("��Ӱ����");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(129, 45, 73, 23);
		text.setText(movie_name);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(31, 148, 61, 17);
		label_1.setText("���ң�");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(129, 145, 73, 23);
		text_1.setText(nation);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(31, 197, 61, 17);
		label_2.setText("���ݣ�");

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(129, 194, 73, 23);
		text_2.setText(director);

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(31, 242, 61, 17);
		label_3.setText("Ƭ����");

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(129, 239, 73, 23);
		text_3.setText(movie_time);

		Label lblMin = new Label(shell, SWT.NONE);
		lblMin.setBounds(208, 242, 61, 17);
		lblMin.setText("min");

		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(31, 293, 61, 17);
		label_4.setText("���ԣ�");

		final Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setBounds(129, 290, 88, 25);
		combo.add("����");
		combo.add("Ӣ��");
		combo.setText(movie_lan);

		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setBounds(318, 10, 61, 17);
		label_9.setText("ӰƬ��飺");

		text_5 = new Text(shell, SWT.BORDER | SWT.WRAP);
		text_5.setBounds(329, 45, 221, 250);
		text_5.setText(infor);

		final Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/" + str));
		lblNewLabel.setBounds(646, 48, 140, 196);

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {

			@Override
			/**
			 * @����: ѡ�񺣱�
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {

				FileDialog fd = new FileDialog(shell);// ����һ���ɴ򿪵��ļ�ѡ���
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

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @����: �ϴ�
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
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
						+ (int) (Math.random() * 100) + ".jpg";
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
		button_2.setBounds(732, 268, 80, 27);
		button_2.setText("ȷ��");

		button_1.setBounds(629, 268, 80, 27);
		button_1.setText("\u9009\u62E9\u6D77\u62A5");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {

			/**
			 * @����: �ж�ӰƬ�Ƿ����
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public boolean panduan() {
				boolean a = false;
				List<Map<String, Object>> list = db
						.query("select movie_type from movietype");
				for (Map<String, Object> map : list) {
					if (text.getText().trim()
							.equals(map.get("movie_type").toString())) {

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
			 * @����: �޸�ӰƬ
			 * @���ߣ�������
			 * @�汾��0.9
			 * @����ʱ�䣺2013-6-04����11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {

				MessageBox box = new MessageBox(shell);

				if (panduan()) {
					box.setText("ϵͳ��Ϣ");
					box.setMessage("�Բ��𣬸õ�Ӱ�Ѵ��ڣ�");
					box.open();
				} else if (!StringRegexUtils.isRegexpValidate(text_3.getText(),
						StringRegexUtils.integer_regexp)) {
					box.setText("ϵͳ��Ϣ");
					box.setMessage("�Բ���Ƭ����������������");
					box.open();
				} else {
					int i = db.update("update movieinfo set movie_name = '"
							+ text.getText() + "' , " + "nation = '"
							+ text_1.getText() + "' ," + " director = '"
							+ text_2.getText() + "'," + "movie_time = '"
							+ text_3.getText() + "',movie_lan = '"
							+ combo.getText() + "'," + "infor = '"
							+ text_5.getText() + "' ," + "actor ='"
							+ text_7.getText() + "',movie_img = '" + fileName
							+ "' where id = '" + id + "'");
					if (i != 0) {
						box.setText("ϵͳ��Ϣ");
						box.setMessage("�༭�ɹ���");
						box.open();
						result = "�ɹ�";
						// �رյ������ڵķ���
						shell.dispose();
					} else {
						box.setText("ϵͳ��Ϣ");
						box.setMessage("�༭ʧ��������༭��");
						box.open();
					}
				}

			}
		});
		button.setBounds(760, 431, 80, 27);
		button.setText("����");

	}
}
