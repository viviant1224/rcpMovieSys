package cn.com.shxt.view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import cn.com.shxt.dialog.Login;
import cn.com.shxt.dialog.Xiaxian;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;
import org.eclipse.swt.widgets.DateTime;

public class ViewMenu extends ViewPart {

	public static final String ID = "cn.com.shxt.view.ViewMenu"; //$NON-NLS-1$
	private DbUtils db = new DbUtils();

	public ViewMenu() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/view11.jpg"));
		
		ExpandBar expandBar = new ExpandBar(container, SWT.NONE);
		expandBar.setBackgroundMode(SWT.INHERIT_DEFAULT);
		expandBar.setBounds(0, 263, 332, 340);
		
		ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setExpanded(true);
		expandItem.setText("放映厅管理");
		
		Group group_2 = new Group(expandBar, SWT.NONE);
		expandItem.setControl(group_2);
		expandItem.setHeight(expandItem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		Link link_2 = new Link(group_2, SWT.NONE);
		link_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		link_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		link_2.addSelectionListener(new SelectionAdapter() {
			@Override
			//添加放映厅
			public void widgetSelected(SelectionEvent e) {
				
				
					try {
						container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/m1.jpg"));
						RcpUtil.getPage().showView(AddShowRoom.ID);
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
			}
		});
		link_2.setBounds(25, 36, 109, 27);
		link_2.setText("<a>添加放映厅</a>");
		
		Link link_9 = new Link(group_2, SWT.NONE);
		link_9.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		link_9.addSelectionListener(new SelectionAdapter() {
			@Override
			//查看fangyingting
			public void widgetSelected(SelectionEvent e) {
				try {
					container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/m1.jpg"));
					RcpUtil.getPage().showView(SearchShowRoom.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		link_9.setBounds(174, 36, 102, 27);
		link_9.setText("<a>查看放映厅</a>");
		
		ExpandItem expandItem_1 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_1.setExpanded(true);
		expandItem_1.setText("影片管理");
		
		Group group = new Group(expandBar, SWT.NONE);
		expandItem_1.setControl(group);
		expandItem_1.setHeight(expandItem_1.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		Link link = new Link(group, SWT.NONE);
		link.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		link.addSelectionListener(new SelectionAdapter() {
			@Override//添加影片
			public void widgetSelected(SelectionEvent e) {
				try {
					container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/view11.jpg"));
					RcpUtil.getPage().showView(AddMovie.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		link.setBounds(24, 10, 87, 26);
		link.setText("<a>影片添加</a>");
		
		Link link_1 = new Link(group, SWT.NONE);
		link_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		link_1.addSelectionListener(new SelectionAdapter() {
			@Override//查看影片
			public void widgetSelected(SelectionEvent e) {

				try {
					container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/view11.jpg"));
					RcpUtil.getPage().showView(SearchMovie.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});
		link_1.setBounds(24, 48, 87, 26);
		link_1.setText("<a>查看影片</a>");
		
		Link link_7 = new Link(group, SWT.NONE);
		link_7.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		link_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/view11.jpg"));
					RcpUtil.getPage().showView(ShangyingMovie.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		link_7.setBounds(183, 10, 87, 26);
		link_7.setText("<a>影片上映</a>");
		
		Link link_8 = new Link(group, SWT.NONE);
		link_8.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		link_8.addSelectionListener(new SelectionAdapter() {
			@Override
			//影片下线	
			public void widgetSelected(SelectionEvent e) {
				container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/view11.jpg"));
				Xiaxian x = new Xiaxian(container.getShell(), SWT.NONE);
				x.open();
			}
		});
		link_8.setBounds(183, 48, 87, 26);
		link_8.setText("<a>影片下线</a>");
		
		ExpandItem expandItem_2 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_2.setExpanded(true);
		expandItem_2.setText("\u552E\u7968\u533A");
		
		Group group_1 = new Group(expandBar, SWT.NONE);
		expandItem_2.setControl(group_1);
		expandItem_2.setHeight(expandItem_2.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		Link link_6 = new Link(group_1, SWT.NONE);
		link_6.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		link_6.addSelectionListener(new SelectionAdapter() {
			@Override
			//购票
			public void widgetSelected(SelectionEvent e) {
				try {
					container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/maipiao.jpg"));
					RcpUtil.getPage().showView(SellTiket.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		link_6.setBounds(28, 34, 69, 40);
		link_6.setText("<a>\u8D2D\u7968</a>");
		
		Link link_3 = new Link(group_1, SWT.NONE);
		link_3.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		link_3.addSelectionListener(new SelectionAdapter() {
			@Override
			//退票
			public void widgetSelected(SelectionEvent e) {
				try {
					container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/maipiao.jpg"));
					RcpUtil.getPage().showView(TuiPiao.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		link_3.setBounds(196, 34, 53, 40);
		link_3.setText("<a>\u9000\u7968</a>");
		
		Group group_4 = new Group(expandBar, SWT.NONE);
		
		Group group_3 = new Group(expandBar, SWT.NONE);
		
		Group group_5 = new Group(expandBar, SWT.NONE);
		
		Label label = new Label(container, SWT.WRAP);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		label.setBounds(0, 197, 133, 17);
		label.setText("当前系统时间：");
		
		final Label lblNewLabel_1 = new Label(container, SWT.WRAP);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("@Microsoft JhengHei", 12, SWT.BOLD));
		lblNewLabel_1.setBounds(162, 195, 170, 22);
		
		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("华文行楷", 14, SWT.NORMAL));
		lblNewLabel_2.setBounds(0, 240, 122, 17);
		lblNewLabel_2.setText("本次登录时间：");
		
		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("@Microsoft JhengHei", 12, SWT.BOLD));
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_3.setBounds(162, 238, 170, 17);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		lblNewLabel_3.setText(sdf.format(d));
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		label_1.setBounds(244, 10, 88, 30);
		label_1.setText("\u5F53\u524D\u7528\u6237\uFF1A");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(244, 47, 61, 17);
		lblNewLabel.setText(Login.name);
		
		DateTime dateTime = new DateTime(container, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(10, 10, 216, 167);
		

		Thread t = new Thread() {
			public void run() {
				while (true) {
					//模拟用户调用
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							lblNewLabel_1.setText(sdf.format(d));
						}
					});
					try {
						Thread.sleep(1000);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		};
		t.start();
	
		
	
		

		createActions();
		initializeToolBar();
		initializeMenu();
	}
	
	
	
	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}

 