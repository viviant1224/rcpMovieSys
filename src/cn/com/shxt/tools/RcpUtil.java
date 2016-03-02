package cn.com.shxt.tools;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class RcpUtil {
	public static   IWorkbenchPage getPage(){
		return PlatformUI//平台接口
		.getWorkbench()//工作区
		.getActiveWorkbenchWindow()//获取工作区窗口
		.getActivePage();//当前页面
	}

}
