package cn.com.shxt.tools;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class RcpUtil {
	public static   IWorkbenchPage getPage(){
		return PlatformUI//ƽ̨�ӿ�
		.getWorkbench()//������
		.getActiveWorkbenchWindow()//��ȡ����������
		.getActivePage();//��ǰҳ��
	}

}
