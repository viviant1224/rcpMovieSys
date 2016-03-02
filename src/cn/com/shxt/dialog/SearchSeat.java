package cn.com.shxt.dialog;

import java.util.ArrayList;
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
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;

public class SearchSeat extends Dialog {

	protected Object result;
	protected Shell shell;
	private String id;
	private String lie;
	private String row;
	private String roomname;
	private static int crow;
	private static int clie;
	private Label lblNewLabel;
	public static List myList = new ArrayList();

	private String[] sss;
	private boolean canUse = false;

	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public SearchSeat(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open(String id, String roomname) {
		this.id = id;
		this.roomname = roomname;

		List<Map<String, Object>> list = db
				.query("select lie,row from showroom where id = '" + id + "' ");

		row = list.get(0).get("row").toString();
		lie = list.get(0).get("lie").toString();
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

	// �жϷ�ӳ���Ƿ�����ӳ��Ӱ
	public boolean panduan() {
		boolean a = false;
		List<Map<String, Object>> list_1 = db
				.query("select roomname from sell");
		System.out.println("size=========" + list_1.size());

		for (int i = 0; i < list_1.size(); i++) {
			if (list_1.size() != 0) {
				if (list_1.get(i).get("roomname").toString().equals(roomname)) {
					a = true;
					break;
				}
			}

		}
		return a;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.CLOSE);
		shell.setSize(794, 579);
		shell.setText("\u67E5\u770B\u5EA7\u4F4D");

		final MessageBox box = new MessageBox(shell);

		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(570, 65, 61, 17);
		label.setText("\u6E29\u99A8\u63D0\u793A:");

		Label lblNewLabel_1 = new Label(shell, SWT.WRAP);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setBounds(570, 93, 215, 73);
		lblNewLabel_1
				.setText("      \u53CC\u51FB\uFF1A \u7EF4\u4FEE\u5EA7\u4F4D\u3001\u5355\u51FB\uFF1A\u6062\u590D\u5EA7\u4F4D \uFF08\u5982\u679C\u8BE5\u653E\u6620\u5385\u5DF2\u6709\u4E0A\u6620\u7535\u5F71\uFF0C\u4E0D\u80FD\u5BF9\u6B64\u653E\u6620\u5385\u7684\u72B6\u6001\u8FDB\u884C\u66F4\u6539\uFF01\uFF09");

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_BLACK));
		lblNewLabel_2.setBounds(47, 28, 502, 29);
		lblNewLabel_2.setText("New Label");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(10, 10, 61, 17);
		label_1.setText("\u5C4F\u5E55\uFF1A");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(10, 78, 45, 17);
		label_2.setText("\u5EA7\u4F4D\uFF1A");

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setText("\u5165\u53E3");
		lblNewLabel_3
				.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_3.setBounds(0, 112, 31, 34);

		int j = 0;
		int k = 0;

		for (int crow = 1; crow <= Integer.parseInt(row); crow++) {

			for (int clie = 1; clie <= Integer.parseInt(lie); clie++) {
				final String s = "" + crow + " " + clie;
				final String[] ss = s.split(" ");
				// System.out.println("crow="+ss[0]);
				// System.out.println("clie="+ss[1]);
				final Label lblNewLabel = new Label(shell, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("΢���ź�", 14, SWT.BOLD));
				lblNewLabel.setText(ss[0] + "-" + ss[1]);

				List<Map<String, Object>> list = db
						.query("select state from zuowei where roomname = '"
								+ roomname + "' " + " and row = '" + ss[0]
								+ "' and lie = '" + ss[1] + "'");

				if (list.get(0).get("state").toString().equals("����")) {
					// db.update("update zuowei set state = '������' where row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
					lblNewLabel.setBackgroundImage(ResourceManager
							.getPluginImage("rcpyingyuanxitong",
									"icons/QQ\u622A\u56FE20130615161817.jpg"));
				} else if (list.get(0).get("state").toString().equals("ά��")) {
					// db.update("update zuowei set state = '����' where row = '"+ss[0]+"' and lie = '"+ss[1]+"'");
					lblNewLabel
							.setBackgroundImage(ResourceManager.getPluginImage(
									"rcpyingyuanxitong", "icons/bu.jpg"));

				}
				//

				lblNewLabel.setBounds(102 + k * 60, 109 + j * 60, 39, 43);
				lblNewLabel.addMouseListener(new MouseAdapter() {

					@Override
					/**
					 * @����: ������λ
					 * @���ߣ�������
					 * @�汾��0.9
					 * @����ʱ�䣺2013-6-04����11:20:59
					 */
					public void mouseDoubleClick(MouseEvent e) {
						System.out.println("crow=" + ss[0]);
						System.out.println("clie=" + ss[1]);
						String str = "" + ss[0] + " " + ss[1];

						if (panduan()) {
							box.setText("ϵͳ��Ϣ");
							box.setMessage("�÷�ӳ��������ӳ��Ӱ������ά�ޣ������ʱ���ٲ���");
							box.open();
						} else {
							List<Map<String, Object>> list = db
									.query("select state from zuowei where roomname = '"
											+ roomname
											+ "'   and row = '"
											+ ss[0]
											+ "' and lie = '"
											+ ss[1]
											+ "'");
							if (list.get(0).get("state").equals("����")) {
								myList.add(str);
								lblNewLabel.setBackgroundImage(ResourceManager
										.getPluginImage("rcpyingyuanxitong",
												"icons/bu.jpg"));
								box.setText("ϵͳ��Ϣ");
								box.setMessage("��λά��");
								box.open();

								db.update("update zuowei set state = 'ά��' where roomname = '"
										+ roomname
										+ "'  and row = '"
										+ ss[0]
										+ "' and lie = '" + ss[1] + "'");
							}
							System.out.println("" + myList);
						}

					}

					public void mouseDown(MouseEvent e) {
						System.out.println("crow=" + ss[0]);
						System.out.println("clie=" + ss[1]);
						String str = "" + ss[0] + " " + ss[1];
						if (panduan()) {
							box.setText("ϵͳ��Ϣ");
							box.setMessage("�÷�ӳ��������ӳ��Ӱ������ά�ޣ������ʱ���ٲ���");
							box.open();
						} else {
							List<Map<String, Object>> list = db
									.query("select state from zuowei where roomname = '"
											+ roomname
											+ "' "
											+ "  and row = '"
											+ ss[0]
											+ "' and lie = '"
											+ ss[1]
											+ "'");
							if (list.get(0).get("state").equals("ά��")) {
								myList.remove(str);
								lblNewLabel.setBackgroundImage(ResourceManager
										.getPluginImage("rcpyingyuanxitong",
												"icons/QQ\u622A\u56FE20130615161817.jpg"));
								db.update("update zuowei set state = '����' where roomname = '"
										+ roomname
										+ "'  and row = '"
										+ ss[0]
										+ "' and lie = '" + ss[1] + "'");
								box.setText("ϵͳ��Ϣ");
								box.setMessage("��λ�ָ�");
								box.open();
							}
						}
						System.out.println("" + myList);
					}

				});
				if (k % Integer.parseInt(lie) == Integer.parseInt(lie) - 1) {
					j++;
					k = -1;
				}
				k++;
			}

		}

	}
}
