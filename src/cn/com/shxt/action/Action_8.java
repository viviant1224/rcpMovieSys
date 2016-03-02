package cn.com.shxt.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;

import cn.com.shxt.core.Activator;
import cn.com.shxt.dialog.Help;

public class Action_8 extends Action{
	private IWorkbenchWindow window;
	public Action_8(IWorkbenchWindow window) {
		setImageDescriptor( Activator.getImageDescriptor("icons/bangzhu.jpg") );
		this.window = window;
		
	}
	public void run() {

		Help h = new Help(window.getShell(), SWT.CLOSE);
		h.open(window);
	
		
	}
}
