package net.freegos.editmk;

import java.nio.file.Files;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
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
	    Label label = new Label(shell, SWT.CENTER);
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
            @Override
            public void widgetSelected(SelectionEvent e) {
            	FileDialog dialog = new FileDialog(shell, SWT.OPEN);
            	String path = dialog.open();
                if (path != null) {
                    System.out.println(path);
                    t.setText(path);
                    byte[] fileArray;
                    fileArray = Files.readAllBytes((Path) path);
                }
            }
        });
		
		
		
		//Content
		shell.setLayout(new FillLayout());
		
		t= new Text(this.shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		new TheUI(display);		
		display.dispose();
	}
}

