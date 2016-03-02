package cn.com.shxt.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

//import cn.com.shxt.view.Myview_1;

import cn.com.shxt.core.Activator;
import cn.com.shxt.view.ViewMenu;

public class Action_1 extends Action{
	private IWorkbenchWindow window;
	public Action_1(IWorkbenchWindow window) {
		setImageDescriptor( Activator.getImageDescriptor("icons/zhuye.jpg") );
		this.window = window;
		
	}
	public void run() {

		try {
			window.getActivePage().showView(ViewMenu.ID);
//			window.getActivePage().showView(viewId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}
