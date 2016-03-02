package cn.com.shxt.core;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import cn.com.shxt.dialog.Login;
import cn.com.shxt.view.VIP;
import cn.com.shxt.view.ViewMenu;
import cn.com.shxt.view.ViewWelcome;
import cn.com.shxt.view.Viewmovie;



public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		if(Login.role.equals("π‹¿Ì‘±")) {
			layout.addStandaloneView(VIP.ID, false,IPageLayout.LEFT, 1.00f, layout.getEditorArea());
		} else {
			layout.addView(ViewMenu.ID, IPageLayout.LEFT, 0.25f, layout.getEditorArea());
			layout.addView(ViewWelcome.ID, IPageLayout.RIGHT, 0.75f, layout.getEditorArea());
		}
//		layout.addView(ViewMenu.ID, IPageLayout.LEFT, 0.25f, layout.getEditorArea());
//		layout.addStandaloneView(ViewMenu.ID, false,IPageLayout.LEFT, 0.25f, layout.getEditorArea());
		
		
		
	}
}
