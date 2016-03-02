package cn.com.shxt.view;



import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.shxt.tools.DbUtils;

public class Viewmovie extends ViewPart {

	public static final String ID = "cn.com.shxt.view.Viewmovie"; //$NON-NLS-1$
	private Text text;
	private Text text_1;
	private DbUtils db = new DbUtils();
	private int [] id;
//	private String time ;
	private Combo  combo;
	private DateTime dateTime;
	

	public Viewmovie() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("华文隶书", 20, SWT.NORMAL));
		label.setBounds(135, 73, 216, 35);
		label.setText("添加电影");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(108, 157, 61, 17);
		label_1.setText("电影名字：");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(235, 151, 73, 23);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(108, 213, 61, 17);
		label_2.setText("电影主演：");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(235, 213, 73, 23);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(108, 281, 61, 17);
		label_3.setText("上映时间：");
		dateTime = new DateTime(composite, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.setBounds(235, 281, 128, 24);
		
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBounds(108, 364, 61, 17);
		label_4.setText("影片类型：");
		
		combo = new Combo(composite, SWT.NONE);
		combo.setBounds(235, 364, 88, 25);

		id = new int[100];
		List<Map<String,Object>> list = db.query("select * from movietype order by id");
		for(int i = 0;i<list.size();i++) {
			Map<String,Object> map = list.get(i); 
			id[i] = (Integer)map.get("id");
			combo.add(map.get("movie_type").toString());
		}
		combo.select(0);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String time = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay();
				System.out.println(time);
				MessageBox box = new MessageBox(new Shell());
				if(text.getText().trim().length()==0||text_1.getText().trim().length()==0) {
					box.setText("系统消息");
					box.setMessage("对不起，您有未填的项，请添完整！");
					box.open();
				}else{
					int i = db.update("insert into movie(actor,date_time,movie_name,type_id)values('"+text_1.getText()+"','"+time+"','"+text.getText()+"','"+id[combo.getSelectionIndex()]+"')");
					if(i!=0) {
						box.setText("系统消息");
						box.setMessage("添加成功！");
						box.open();
					} else {
						box.setText("系统消息");
						box.setMessage("添加失败！");
						box.open();
					}
					
				}
				
			}
		});
		button.setBounds(415, 417, 80, 27);
		button.setText("\u786E\u5B9A");
		
		
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
