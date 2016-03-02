package cn.com.shxt.view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
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

import cn.com.shxt.dialog.SearchUser;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;

public class VIP extends ViewPart {

	public static final String ID = "cn.com.shxt.view.VIP"; //$NON-NLS-1$
	private DbUtils db = new DbUtils();

	public VIP() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/VIPji.jpg"));
		container.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		DateTime dateTime = new DateTime(container, SWT.BORDER | SWT.CALENDAR);
		dateTime.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		dateTime.setBounds(1073, 10, 226, 167);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			//主页
			public void mouseDoubleClick(MouseEvent e) {
				try {
					RcpUtil.getPage().showView(ViewMenu.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel.setBounds(57, 40, 109, 121);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			//用户管理
			public void mouseDoubleClick(MouseEvent e) {
				SearchUser su = new SearchUser(container.getShell(), SWT.NONE);
				su.open();
				
			}
		});
		lblNewLabel_1.setBounds(0, 302, 122, 121);
		
		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			//查看放映厅
			public void mouseDoubleClick(MouseEvent e) {
				try {
//					container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/m1.jpg"));
					RcpUtil.getPage().showView(SearchShowRoom.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_2.setBounds(229, 356, 116, 128);
		
		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			//查看电影
			public void mouseDoubleClick(MouseEvent e) {


				try {
//					container.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/view11.jpg"));
					RcpUtil.getPage().showView(SearchMovie.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			
			}
		});
		lblNewLabel_3.setBounds(457, 246, 116, 128);
		
		Label lblNewLabel_4 = new Label(container, SWT.NONE);
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			//统计上座率
			public void mouseDoubleClick(MouseEvent e) {

				int count = 0;
				DefaultCategoryDataset  dg = new DefaultCategoryDataset();
				List<Map<String,Object>> list = db.query("select movie_name from sell  group by movie_name");
				for(int i = 0;i<list.size();i++) {
					List<Map<String,Object>> list_1 = db.query("select state from sell where movie_name = '"+list.get(i).get("movie_name").toString()+"'");
				for(int j = 0;j<list_1.size();j++) {
					if(list_1.get(j).get("state").toString().equals("不可用")) {
						count++;
					}
				}
				double zong = list_1.size();
				double lv = count/zong;
 					dg.setValue(lv, list.get(i).get("movie_name").toString(), list.get(i).get("movie_name").toString());
 					count = 0;
				}

				
				 JFreeChart jc = ChartFactory.createBarChart3D("hello", "上座率", "电影分布", 
						 	dg, PlotOrientation.VERTICAL, true, true, false); 
				 jc.setTitle(new TextTitle("小威少威影院售上座率对比",new Font("宋体",Font.BOLD
						+Font.ITALIC,20)));
				 CategoryPlot plot = (CategoryPlot) jc.getPlot();

					CategoryAxis categoryAxis = plot.getDomainAxis();

					categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));
					
					ChartFrame cf = new ChartFrame("小威少威影院上座率",jc);
					
					cf.pack();
					
					cf.setVisible(true);
			
			}
		});
		lblNewLabel_4.setBounds(614, 246, 93, 128);
		
		Label lblNewLabel_5 = new Label(container, SWT.NONE);
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			//统计卖票率
			public void mouseDoubleClick(MouseEvent e) {

				int count = 0;
				DefaultCategoryDataset  dg = new DefaultCategoryDataset();
				List<Map<String,Object>> list = db.query("select movie_name from sell  group by movie_name");
				for(int i = 0;i<list.size();i++) {
					List<Map<String,Object>> list_1 = db.query("select state from sell where movie_name = '"+list.get(i).get("movie_name").toString()+"'");
				for(int j = 0;j<list_1.size();j++) {
					if(list_1.get(j).get("state").toString().equals("不可用")) {
						count++;
					}
				}
 					dg.setValue(count, list.get(i).get("movie_name").toString(), list.get(i).get("movie_name").toString());
 					count = 0;
				}

				
				 JFreeChart jc = ChartFactory.createBarChart3D("hello", "卖票数量", "电影分布", 
						 	dg, PlotOrientation.VERTICAL, true, true, false); 
				 jc.setTitle(new TextTitle("小威少威影院售票数对比",new Font("宋体",Font.BOLD
						+Font.ITALIC,20)));
				 CategoryPlot plot = (CategoryPlot) jc.getPlot();

					CategoryAxis categoryAxis = plot.getDomainAxis();

					categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 12));
					
					ChartFrame cf = new ChartFrame("小威少威影院卖票率",jc);
					
					cf.pack();
					
					cf.setVisible(true);
			
			}
		});
		lblNewLabel_5.setBounds(814, 266, 109, 108);
		
		Label lblNewLabel_6 = new Label(container, SWT.NONE);
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			//统计票房
			public void mouseDoubleClick(MouseEvent e) {

				
				double count = 0;
				
				DefaultPieDataset dpd = new DefaultPieDataset();
				List<Map<String,Object>> list = db.query("select movie_name from sell  group by movie_name");
				
				for(int i = 0;i<list.size();i++) {
					List<Map<String,Object>> list_1 = db.query("select sell_price,state from sell where movie_name = '"+list.get(i).get("movie_name").toString()+"'");
					System.out.println("------list_1-------"+list.get(i).get("movie_name").toString());
				
					for(int k = 0;k<list_1.size();k++) {
					if(list_1.get(k).get("state").toString().equals("不可用")) {
						double mon = Double.parseDouble(list_1.get(k).get("sell_price").toString());
						count = count+mon;
					}
					
						
					}

					
				
 					dpd.setValue(list.get(i).get("movie_name").toString(),count);
 					count = 0;
				}
				JFreeChart jc = ChartFactory.createPieChart3D("小威少威影院票房统计",dpd,true,true,true);
				//jc为一个看到的图表
				ChartFrame cf = new ChartFrame("小威少威影院票房统计",jc);
				cf.pack();
				cf.setVisible(true);
			
			}
		});
		lblNewLabel_6.setBounds(1010, 212, 128, 97);
		
		final Label lblNewLabel_7 = new Label(container, SWT.NONE);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_7.setBounds(1122, 180, 177, 26);
		
		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.ITALIC));
		label.setBounds(0, -2, 137, 17);
		label.setText("\u5C0F\u5A01\u5C11\u5A01\u5F71\u9662\u7BA1\u7406\u7CFB\u7EDF");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(89, 167, 61, 17);
		label_1.setText("\u4E3B\u9875");
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBounds(257, 490, 61, 17);
		label_2.setText("\u67E5\u770B\u653E\u6620\u5385");
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setBounds(479, 380, 61, 17);
		label_3.setText("\u67E5\u770B\u5F71\u7247");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setBounds(634, 380, 61, 17);
		label_4.setText("\u67E5\u770B\u4E0A\u5EA7\u7387");
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setBounds(836, 380, 61, 17);
		label_5.setText("\u67E5\u770B\u552E\u7968");
		
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setBounds(1045, 315, 61, 17);
		label_6.setText("\u67E5\u770B\u7968\u623F");
		
		Label label_7 = new Label(container, SWT.NONE);
		label_7.setBounds(30, 429, 61, 17);
		label_7.setText("\u67E5\u770B\u7528\u6237");
		
		
		
		
		Thread t = new Thread() {
			public void run() {
				while (true) {
					//模拟用户调用
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
							Date d = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							lblNewLabel_7.setText(sdf.format(d));
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
