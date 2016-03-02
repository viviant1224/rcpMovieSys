package cn.com.shxt.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.view.SellTiket;
import cn.com.shxt.view.ViewMenu;
import org.eclipse.wb.swt.ResourceManager;

public class Help extends Dialog {

	protected Object result;
	protected Shell shell;
	private IWorkbenchWindow window;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Help(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(IWorkbenchWindow window) {
		this.window = window;
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
		shell.setSize(450, 300);
		shell.setText("\u5E2E\u52A9");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(27, 40, 98, 33);
		label.setText("\u627E\u56DE\u4E3B\u83DC\u5355\uFF1A");
		
		Link link = new Link(shell, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 找回主菜单
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				try {
					window.getActivePage().showView(ViewMenu.ID);
//					window.getActivePage().showView(viewId);
				} catch (Exception q) {
					// TODO Auto-generated catch block
					q.printStackTrace();
				}
			}
		});
		link.setBounds(131, 40, 53, 17);
		link.setText("<a>\u70B9\u51FB\u8FD9\u91CC</a>");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(27, 93, 80, 33);
		label_1.setText("\u6CE8\u518C\u4F1A\u5458\uFF1A");
		
		Link link_1 = new Link(shell, 0);
		link_1.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述:注册会员
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				VipSign vs = new VipSign(shell.getShell(), SWT.NONE);
				vs.open();
			}
		});
		link_1.setText("<a>\u70B9\u51FB\u8FD9\u91CC</a>");
		link_1.setBounds(131, 93, 53, 17);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setBounds(27, 152, 80, 37);
		label_2.setText("\u76F4\u63A5\u552E\u7968\uFF1A");
		
		Link link_2 = new Link(shell, 0);
		link_2.addSelectionListener(new SelectionAdapter() {
			@Override
			/**
			 * @描述: 售票
			 * @作者：黄威威
			 * @版本：0.9
			 * @开发时间：2013-6-04上午11:20:59
			 */
			public void widgetSelected(SelectionEvent e) {
				try {
					window.getActivePage().showView(SellTiket.ID);
//					window.getActivePage().showView(viewId);
				} catch (Exception q) {
					// TODO Auto-generated catch block
					q.printStackTrace();
				}
			}
		});
		link_2.setText("<a>\u70B9\u51FB\u8FD9\u91CC</a>");
		link_2.setBounds(131, 152, 53, 17);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setBounds(27, 195, 176, 33);
		label_3.setText("\u5982\u679C\u9700\u8981\u5E2E\u52A9\uFF0C\u8BF7\u8054\u7CFB\uFF1A");
		
		Label lblNewLabel = new Label(shell, SWT.WRAP);
		lblNewLabel.setBounds(209, 199, 190, 40);
		lblNewLabel.setText("\u7535\u8BDD\uFF1A18686672327\r\nQQ:\t839562677");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.ITALIC));
		label_4.setBounds(210, 10, 224, 33);
		label_4.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662\u552E\u7968\u7CFB\u7EDF");

	}

}
