package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;

public class V_Card extends Dialog {

	protected Object result;
	public static Shell shellv;
	private String jibie;
	private String shenfenzheng;
	private DbUtils db = new DbUtils();
	private String id;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public V_Card(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open(String jibie, String shenfenzheng) {
		this.shenfenzheng = shenfenzheng;
		this.jibie = jibie;
		createContents();
		shellv.open();
		shellv.layout();
		Display display = getParent().getDisplay();
		while (!shellv.isDisposed()) {
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
		List<Map<String, Object>> list = db
				.query("select id from vip where shenfenzheng = '"
						+ shenfenzheng + "'");
		id = list.get(0).get("id").toString();
		shellv = new Shell(getParent(), SWT.BORDER);
		if (jibie.equals("星级")) {
			shellv.setBackgroundImage(ResourceManager.getPluginImage(
					"rcpyingyuanxitong", "icons/VIPstart.jpg"));
		} else if (jibie.equals("月亮级")) {
			shellv.setBackgroundImage(ResourceManager.getPluginImage(
					"rcpyingyuanxitong", "icons/VIPmoon.jpg"));
		} else if (jibie.equals("太阳级")) {
			shellv.setBackgroundImage(ResourceManager.getPluginImage(
					"rcpyingyuanxitong", "icons/VIPsun.jpg"));
		}

		shellv.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shellv.setSize(317, 204);
		shellv.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662\u8D35\u5BBE\u5361");

		Label lblNewLabel = new Label(shellv, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.BOLD));
		lblNewLabel.setBounds(176, 28, 58, 19);
		lblNewLabel.setText(jibie);

		Label label = new Label(shellv, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.BOLD));
		label.setBounds(240, 28, 33, 17);
		label.setText("\u5361");

		Label lblNewLabel_1 = new Label(shellv, SWT.NONE);
		lblNewLabel_1
				.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
		lblNewLabel_1.setBounds(215, 134, 70, 17);
		lblNewLabel_1.setText(id);

		Label lblNum = new Label(shellv, SWT.NONE);
		lblNum.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
		lblNum.setBounds(169, 134, 40, 17);
		lblNum.setText("NUM:");

		Label lblNewLabel_2 = new Label(shellv, SWT.NONE);
		lblNewLabel_2.setBounds(29, 48, 82, 17);
		lblNewLabel_2.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662");

	}
}
