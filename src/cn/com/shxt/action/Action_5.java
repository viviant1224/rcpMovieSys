package cn.com.shxt.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import cn.com.shxt.core.Activator;
import cn.com.shxt.dialog.InforSet;

import cn.com.shxt.tools.RcpUtil;
import cn.com.shxt.view.ViewMenu;


public class Action_5 extends Action {


	private IWorkbenchWindow window;

	public Action_5(IWorkbenchWindow window) {
		setImageDescriptor( Activator.getImageDescriptor("icons/xiugaigeren.jpg") );
		this.window = window;
		
	}
	public void run() {

		try {
			InforSet is = new InforSet(window.getShell(), SWT.CLOSE);
			is.open();
//			window.getActivePage().showView(viewId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}



}
