package net.freegos.editmk;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.FileDialog;

public class TheUI {
	private Shell shell;
	private Menu menuBar;
	private MenuItem fileMenuButton, openFileMenuAction;
	
	private Text t;
	
	public TheUI(Display display) {
		shell = new Shell(display);
		shell.setText("Edit MK");
		
		initUI();
		
		shell.open();
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void initUI() {
		//Menu
		Menu menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);
		
		MenuItem fileMenuButton = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuButton.setText("&Fichier");
		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuButton.setMenu(fileMenu);
		MenuItem openFileMenuAction = new MenuItem(fileMenu, SWT.PUSH);
		openFileMenuAction.setText("&Ouvrir");
		
		openFileMenuAction.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
            	FileDialog dialog = new FileDialog(shell, SWT.OPEN);
            	String path = dialog.open();
                if (path != null) {
                    t.setText(path);
                    MkFile theFile = new MkFile(path, "tada");
                    t.setText(theFile.content());
                }
            }
        });
		
		
		//Content
		shell.setLayout(new FillLayout());
		
		t = new Text(this.shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		new TheUI(display);		
		display.dispose();
	}
}

