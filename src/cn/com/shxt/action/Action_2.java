package cn.com.shxt.action;

import org.eclipse.jface.action.Action;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import cn.com.shxt.core.Activator;


public class Action_2 extends Action {

	private IWorkbenchWindow window;
	
	public Action_2(IWorkbenchWindow window) {
		setImageDescriptor( Activator.getImageDescriptor("icons/chonqi.jpg") );
		this.window = window;
		
	}
	
	public void run() {

		
		try {

		     PlatformUI.getWorkbench().restart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
