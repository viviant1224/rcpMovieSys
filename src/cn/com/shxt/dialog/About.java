package cn.com.shxt.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class About extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public About(Shell parent, int style) {
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
		shell.setSize(618, 473);
		shell.setText("\u5173\u4E8E\u672C\u7CFB\u7EDF");
		
		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label.setBounds(129, 265, 61, 37);
		label.setText("\u5236\u4F5C\u4EBA:");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_1.setBounds(225, 265, 61, 28);
		label_1.setText("\u9EC4\u5A01\u5A01");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_2.setBounds(129, 308, 80, 28);
		label_2.setText("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel.setBounds(225, 308, 136, 17);
		lblNewLabel.setText("18686672327");
		
		Label lblVersion = new Label(shell, SWT.NONE);
		lblVersion.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblVersion.setBounds(474, 402, 61, 17);
		lblVersion.setText("Version\uFF1A");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setBounds(541, 402, 61, 17);
		label_3.setText("13.06.23");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_4.setBounds(129, 354, 61, 28);
		label_4.setText("\u6765\u81EA\uFF1A");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_5.setBounds(216, 354, 254, 28);
		label_5.setText("\u957F\u6625\u5DE5\u4E1A\u5927\u5B66\u3001\u56DB\u6D77\u5174\u5510-\u9526\u7EE32");
		
		Label lblNewLabel_1 = new Label(shell, SWT.WRAP);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_1.setBounds(116, 87, 458, 172);
		lblNewLabel_1.setText("\u672C\u7CFB\u7EDF\u4E3B\u8981\u529F\u80FD\u4E3A\uFF1A\r\n      \u5F71\u7247\uFF1A\u4E0A\u6620\u3001\u67E5\u770B\u3002\u4E0B\u7EBF\u3001\u6DFB\u52A0\u548C\u4FEE\u6539\r\n      \u552E\u7968\u533A\uFF1A\u552E\u7968\u3001\u5356\u7968\u3001\u4F1A\u5458\u6CE8\u518C\r\n      \u653E\u6620\u5385\uFF1A\u6DFB\u52A0\u3001\u4FEE\u6539\u3001\u67E5\u770B\r\n      \u7EDF\u8BA1\uFF1A\u7BA1\u7406\u5458\u6240\u5177\u6709\u7684\u7684\u529F\u80FD\uFF0C\u53EF\u4EE5\u7EDF\u8BA1\u5F71\u7247\u7684\u4E0A\u6620\u60C5\u51B5\u53CA\u7968\u623F\u7684\u5BF9\u6BD4\u3002\r\n      \u672C\u7CFB\u7EDF\u64CD\u4F5C\u7B80\u5355\uFF0C\u5B9E\u7528\u6027\u4E00\u822C\uFF0C\u4F46\u5BB9\u6613\u4E0A\u624B\u3002\u4EFB\u4F55\u4EBA\u672A\u7ECF\u51FA\u7248\u4EBA\u540C\u610F\u4E0D\u5F97\u76D7\u7528\u672C\u7CFB\u7EDF\u3002");
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("»ªÎÄÐÐ¿¬", 30, SWT.BOLD));
		label_6.setBounds(127, 32, 408, 48);
		label_6.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662\u7CFB\u7EDF");

	}
}
