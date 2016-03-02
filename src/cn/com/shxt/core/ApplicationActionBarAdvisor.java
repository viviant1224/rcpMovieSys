package cn.com.shxt.core;


import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import cn.com.shxt.action.Action_1;
import cn.com.shxt.action.Action_2;
import cn.com.shxt.action.Action_3;
import cn.com.shxt.action.Action_4;
import cn.com.shxt.action.Action_5;
import cn.com.shxt.action.Action_6;
import cn.com.shxt.action.Action_7;
import cn.com.shxt.action.Action_8;
import cn.com.shxt.dialog.Login;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private Action_1 action_1;
	private Action_2 action_2;
	private Action_3 action_3;
	private Action_4 action_4;
	private Action_5 action_5;
	private Action_6 action_6;
	private Action_7 action_7;
	private Action_8 action_8;
	

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	action_1 = new Action_1(window);
    	action_1.setText("回到主菜单");
    	

    	action_2 = new Action_2(window);
    	action_2.setText("重新登录");  
    	
    	action_3 = new Action_3(window); 
    	action_3.setText("退出");
    	
    	action_4 = new Action_4(window);
    	action_4.setText("用户管理");
    	
    	action_5 = new Action_5(window);
    	action_5.setText("修改个人信息");
    	
    	action_6 = new Action_6(window);
    	action_6.setText("退出");
    	
    	action_7 = new Action_7(window);
    	action_7.setText("关于");
    	
    	action_8 = new Action_8(window);
    	action_8.setText("帮助");
    	
    }

    protected void fillMenuBar(IMenuManager menuBar) {
//    	IMenuManager menu1 = new MenuManager("菜单1");
//    	menu1.add(action_1);
//    	menuBar.add(menu1);
    }
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager tool1 = new ToolBarManager();
		IToolBarManager tool2 = new ToolBarManager();
		IToolBarManager tool3 = new ToolBarManager();
		IToolBarManager tool4 = new ToolBarManager();
		IToolBarManager tool5 = new ToolBarManager();
		IToolBarManager tool6 = new ToolBarManager();
		IToolBarManager tool7 = new ToolBarManager();
		IToolBarManager tool8 = new ToolBarManager();
		
		tool1.add(action_1);
		coolBar.add(tool1);
		
		
		tool2.add(action_2);
		coolBar.add(tool2);
		
		
//		tool3.add(action_3);
//		coolBar.add(tool3);
		
		if(Login.role.equals("管理员")) {
			tool4.add(action_4);
			coolBar.add(tool4);
		}
		
		
		
		tool5.add(action_5);
		coolBar.add(tool5);
		
		tool7.add(action_7);
		coolBar.add(tool7);
		
		tool8.add(action_8);
		coolBar.add(tool8);
		
		tool6.add(action_6);
		coolBar.add(tool6);
	}
    
}
