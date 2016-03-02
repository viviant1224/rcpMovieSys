package cn.com.shxt.dialog;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;

import cn.com.shxt.tools.DbUtils;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class ShowMovie extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	
	private String id;
	private String movie_name;
	private String nation;
	private String director;
	private String movie_time;
//	private String showtime;
	private String infor;
	private String actor;
	private String movie_img;
	private String movie_lan;
	private DbUtils db = new DbUtils();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ShowMovie(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String movie_name) {
//		this.id = id;
		this.movie_name = movie_name;
		List<Map<String, Object>> i = db.query("select movie_name,nation,director,movie_time," +
				"movie_lan,infor,actor,movie_img from movieinfo where movie_name = '"+movie_name+"'");
		
//		this.movie_name = i.get(0).get("movie_name").toString();
		this.nation = i.get(0).get("nation").toString();
		this.director = i.get(0).get("director").toString();
		this.movie_time = i.get(0).get("movie_time").toString();
//		this.showtime = i.get(0).get("showtime").toString();
		this.movie_lan = i.get(0).get("movie_lan").toString();
		this.infor = i.get(0).get("infor").toString();
		this.actor = i.get(0).get("actor").toString();
		this.movie_img = i.get(0).get("movie_img").toString();
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN);
		shell.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/xiaxian.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(699, 467);
		shell.setText("\u67E5\u770B\u8BE6\u7EC6\u4FE1\u606F");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel.setBounds(42, 36, 107, 26);
		lblNewLabel.setText(movie_name);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_1.setBounds(110, 92, 107, 26);
		lblNewLabel_1.setText(nation);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_2.setBounds(42, 197, 61, 46);
		lblNewLabel_2.setText("µ¼ÑÝ£º");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_3.setBounds(110, 197, 118, 36);
		lblNewLabel_3.setText(director);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label.setBounds(42, 310, 36, 36);
		label.setText("Æ¬³¤£º");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_4.setBounds(83, 310, 43, 36);
		lblNewLabel_4.setText(movie_time);
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_5.setBounds(110, 249, 61, 36);
		lblNewLabel_5.setText(movie_lan);
		
//		String [] strTime = showtime.split("-");
		
		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_7.setBounds(42, 142, 61, 36);
		lblNewLabel_7.setText("Ö÷ÑÝ£º");
		
		Label lblNewLabel_8 = new Label(shell, SWT.WRAP);
		lblNewLabel_8.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_8.setBounds(110, 142, 150, 49);
		lblNewLabel_8.setText(actor);
		
		Label lblNewLabel_9 = new Label(shell, SWT.NONE);
		lblNewLabel_9.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblNewLabel_9.setBounds(230, 276, 61, 36);
		lblNewLabel_9.setText("Ó°Æ¬¼ò½é£º");
		
		text = new Text(shell, SWT.BORDER | SWT.WRAP);
		text.setEnabled(false);
		text.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		text.setBounds(230, 315, 319, 124);
		text.setText(infor);
		
		Label lblNewLabel_10 = new Label(shell, SWT.NONE);
		lblNewLabel_10.setBackgroundImage(ResourceManager.getPluginImage("rcpyingyuanxitong", "icons/"+movie_img));
		lblNewLabel_10.setBounds(270, 11, 150, 203);
		lblNewLabel_10.setText("º£±¨");
		
		Label lblMin = new Label(shell, SWT.NONE);
		lblMin.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblMin.setBounds(146, 310, 36, 36);
		lblMin.setText("min");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_1.setBounds(42, 92, 61, 26);
		label_1.setText("\u5730\u533A\uFF1A");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		label_2.setBounds(42, 249, 61, 36);
		label_2.setText("\u8BED\u8A00\uFF1A");

	}
}
