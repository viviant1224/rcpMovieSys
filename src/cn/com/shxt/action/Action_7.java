package cn.com.shxt.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;

import cn.com.shxt.core.Activator;
import cn.com.shxt.dialog.About;


public class Action_7 extends Action{
	private IWorkbenchWindow window;
	public Action_7(IWorkbenchWindow window) {
		setImageDescriptor( Activator.getImageDescriptor("icons/about.jpg") );
		this.window = window;
		
	}
	public void run() {

		About a = new About(window.getShell(), SWT.CLOSE);
		a.open();
	
		
	}
}
