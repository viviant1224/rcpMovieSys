package cn.com.shxt.view;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.shxt.dialog.ShowMovie;
import cn.com.shxt.tools.DbUtils;
import cn.com.shxt.tools.RcpUtil;

public class SearchMovieImg extends ViewPart {

	public static final String ID = "cn.com.shxt.view.SearchMovieImg"; //$NON-NLS-1$
	private DbUtils db = new DbUtils();

	public SearchMovieImg() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(ResourceManager.getPluginImage(
				"rcpyingyuanxitong", "icons/002.jpg"));
		{
			Composite composite = new Composite(container, SWT.NONE);
			composite.setBounds(734, 10, 155, 44);
			{
				Button btnNewButton = new Button(composite, SWT.NONE);
				btnNewButton.addSelectionListener(new SelectionAdapter() {
					@Override
					/**
					 * @描述: 以列表方式查看
					 * @作者：黄威威
					 * @版本：0.9
					 * @开发时间：2013-6-04上午11:20:59
					 */
					public void widgetSelected(SelectionEvent e) {

						try {

							RcpUtil.getPage().showView(SearchMovie.ID);
						} catch (PartInitException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnNewButton.setBounds(0, 0, 155, 44);
				btnNewButton
						.setText("\u4EE5\u5217\u8868\u65B9\u5F0F\u663E\u793A");
			}
		}
		{

			TabFolder tabFolder = new TabFolder(container, SWT.NONE);
			tabFolder.setBounds(0, 61, 899, 559);
			{
				// 正在热映
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setText("\u6B63\u5728\u70ED\u6620");
				{
					Composite composite = new Composite(tabFolder, SWT.NONE);
					composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
					composite.setBackgroundImage(ResourceManager
							.getPluginImage("rcpyingyuanxitong",
									"icons/002.jpg"));
					tabItem.setControl(composite);
					{

						List<Map<String, Object>> list = db
								.query("select movie_img,movie_name from movieinfo where state = '上映'");
						int j = 0;
						int k = 0;
						for (int i = 0; i < list.size(); i++) {
							final String movie_name = list.get(i)
									.get("movie_name").toString();
							String img = list.get(i).get("movie_img")
									.toString();
							Label lblNewLabel = new Label(composite, SWT.NONE);
							Label lblNewLabel_1 = new Label(composite, SWT.NONE);
							lblNewLabel_1.setText(movie_name);
							lblNewLabel_1.setFont(SWTResourceManager.getFont(
									"华文行楷", 14, SWT.BOLD));
							lblNewLabel.setBounds(155 + k * 150, 220 + j * 235,
									10, 30);

							lblNewLabel.setBackgroundImage(ResourceManager
									.getPluginImage("rcpyingyuanxitong",
											"icons/" + img));
							lblNewLabel.addMouseListener(new MouseAdapter() {
								@Override
								// 查看影片详情信息
								public void mouseDoubleClick(MouseEvent e) {

									ShowMovie sm = new ShowMovie(new Shell(),
											SWT.NONE);
									sm.open(movie_name);

								}
							});

							lblNewLabel.setBounds(0 + k * 150, 0 + j * 235,
									145, 198);

							lblNewLabel_1.setBounds(10 + k * 150,
									199 + j * 235, 123, 29);
							if (k % 6 == 5) {
								j++;
								k = -1;
							}
							k++;

						}
					}

				}
			}
			{
				// 已经下线
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setText("\u4E0B\u7EBF\u7535\u5F71");
				{
					Composite composite = new Composite(tabFolder, SWT.NONE);
					composite.setBackgroundImage(ResourceManager
							.getPluginImage("rcpyingyuanxitong",
									"icons/002.jpg"));
					composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
					tabItem.setControl(composite);
					{

						List<Map<String, Object>> list = db
								.query("select movie_img,movie_name from movieinfo where state = '下线'");
						int j = 0;
						int k = 0;
						for (int i = 0; i < list.size(); i++) {
							final String movie_name = list.get(i)
									.get("movie_name").toString();
							String img = list.get(i).get("movie_img")
									.toString();
							Label lblNewLabel = new Label(composite, SWT.NONE);
							Label lblNewLabel_1 = new Label(composite, SWT.NONE);
							lblNewLabel_1.setText(movie_name);
							lblNewLabel_1.setFont(SWTResourceManager.getFont(
									"华文行楷", 14, SWT.BOLD));
							lblNewLabel.setBounds(155 + k * 150, 220 + j * 235,
									10, 30);

							lblNewLabel.setBackgroundImage(ResourceManager
									.getPluginImage("rcpyingyuanxitong",
											"icons/" + img));
							lblNewLabel.addMouseListener(new MouseAdapter() {
								@Override
								// 查看影片详情信息
								public void mouseDoubleClick(MouseEvent e) {

									ShowMovie sm = new ShowMovie(new Shell(),
											SWT.NONE);
									sm.open(movie_name);

								}
							});

							lblNewLabel.setBounds(0 + k * 150, 0 + j * 235,
									145, 198);

							lblNewLabel_1.setBounds(10 + k * 150,
									199 + j * 235, 123, 29);
							if (k % 6 == 5) {
								j++;
								k = -1;
							}
							k++;

						}

					}

				}
			}
			{
				// 即将上映
				TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
				tabItem.setText("\u656C\u8BF7\u671F\u5F85");
				{
					Composite composite = new Composite(tabFolder, SWT.NONE);
					composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
					composite.setBackgroundImage(ResourceManager
							.getPluginImage("rcpyingyuanxitong",
									"icons/002.jpg"));
					tabItem.setControl(composite);
					{

						List<Map<String, Object>> list = db
								.query("select movie_img,movie_name from movieinfo where state = '待定'");
						int j = 0;
						int k = 0;
						for (int i = 0; i < list.size(); i++) {
							final String movie_name = list.get(i)
									.get("movie_name").toString();
							String img = list.get(i).get("movie_img")
									.toString();
							Label lblNewLabel = new Label(composite, SWT.NONE);
							Label lblNewLabel_1 = new Label(composite, SWT.NONE);
							lblNewLabel_1.setText(movie_name);
							lblNewLabel_1.setFont(SWTResourceManager.getFont(
									"华文行楷", 14, SWT.BOLD));
							lblNewLabel.setBounds(155 + k * 150, 220 + j * 235,
									10, 30);

							lblNewLabel.setBackgroundImage(ResourceManager
									.getPluginImage("rcpyingyuanxitong",
											"icons/" + img));
							lblNewLabel.addMouseListener(new MouseAdapter() {
								@Override
								// 查看影片详情信息
								public void mouseDoubleClick(MouseEvent e) {

									ShowMovie sm = new ShowMovie(new Shell(),
											SWT.NONE);
									sm.open(movie_name);

								}
							});

							lblNewLabel.setBounds(0 + k * 150, 0 + j * 235,
									145, 198);

							lblNewLabel_1.setBounds(10 + k * 150,
									199 + j * 235, 123, 29);
							if (k % 6 == 5) {
								j++;
								k = -1;
							}
							k++;

						}

					}
				}
			}

		}

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
