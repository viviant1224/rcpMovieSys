package cn.com.shxt.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import cn.com.shxt.core.Activator;
import cn.com.shxt.tools.RcpUtil;

public class Action_6 extends Action{



	private IWorkbenchWindow window;

	public Action_6(IWorkbenchWindow window) {
		setImageDescriptor( Activator.getImageDescriptor("icons/tuichu.jpg") );
		this.window = window;
		
	}
	public void run() {

		
		try {
			boolean flag = MessageDialog.openConfirm(RcpUtil.getPage().getWorkbenchWindow().getShell(),
					"系统消息", "确定退出该系统吗？");
			if(flag==true) {
				PlatformUI.getWorkbench().close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}





}
