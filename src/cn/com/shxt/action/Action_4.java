package cn.com.shxt.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

import cn.com.shxt.core.Activator;
import cn.com.shxt.dialog.UserManager;



public class Action_4 extends Action {

	private IWorkbenchWindow window;
	protected Shell shell;
	public Action_4(IWorkbenchWindow window) {
		setImageDescriptor( Activator.getImageDescriptor("icons/yonghuguanli.jpg") );
		this.window = window;
		
	}
	public void run() {

		
		try {
			
			UserManager um = new UserManager(window.getShell(), SWT.CLOSE);
			um.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}


}
