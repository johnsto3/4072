package seminar2;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;



public class Textedit extends JFrame{
	private JTextArea area = new JTextArea(20,120);
	//text entered in textarea
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	//used to open files
	private String currentFile = "Untitled";
	//default file
	private boolean changed = false;
	//used for save prompts
	public Textedit() {
		area.setFont(new Font("Times New Roman",Font.PLAIN,16));
		//{possibly change this font later
		JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scroll,BorderLayout.CENTER);
		
		JMenuBar JMB = new JMenuBar();
		setJMenuBar(JMB);
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMB.add(file); JMB.add(edit);
		//template for menus
		file.add(New);
		file.add(Open);
		file.add(Save);
		file.add(Quit);
		file.add(SaveAs);
		file.addSeparator();
		//filemenu setup
		for(int i=0; i<4; i++)
			file.getItem(i).setIcon(null);
		
		edit.add(Cut);
		edit.add(Copy);
		edit.add(Paste);

		edit.getItem(0).setText("Cut (Ctrl-X)");
		edit.getItem(1).setText("Copy (Ctrl-C)");
		edit.getItem(2).setText("Paste (Ctrl-V)");
		
		//JToolBar tool = new JToolBar();
		//add(tool,BorderLayout.NORTH);
		//tool.add(New);
		//tool.add(Open);
		//tool.add(Save);
		
		//tool.addSeparator();
		
		//No toolbars yet, may remove these
		
		//JButton cut = tool.add(Cut), cop = tool.add(Copy),pas = tool.add(Paste);
		
		//cut.setText("Cut");// cut.setIcon(new ImageIcon("cut.gif"));
		//cop.setText(null); cop.setIcon(new ImageIcon("copy.gif"));
		//pas.setText(null); pas.setIcon(new ImageIcon("paste.gif"));
		
		//Update the above images or remove them
		
		
		Save.setEnabled(false);
		SaveAs.setEnabled(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		area.addKeyListener(k1);
		setTitle(currentFile);
		setVisible(true);
	}
		
		
	private KeyListener k1 = new KeyAdapter() {
	
	};
	//Will be used to detect changes to file
	
	Action New = new AbstractAction("New", new ImageIcon("Path/image.gif")) {
		public void actionPerformed(ActionEvent e) {
			//saveOld();
			//Used to save current file before opening
			//UNCOMMENT THIS LATER
			area.setText("");
			currentFile = "Untitled";
			setTitle(currentFile);
			changed = false;
			Save.setEnabled(false);
			SaveAs.setEnabled(false);
		}
	};
		//New is implemented now the rest will come later
	
	Action Open = new AbstractAction("Open", new ImageIcon("open.gif")) {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	Action Save = new AbstractAction("Save", new ImageIcon("save.gif")) {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	Action SaveAs = new AbstractAction("Save as...") {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	
	Action Quit = new AbstractAction("Quit") {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	//Actions will be coded later, want to get UI working first
	
	ActionMap m = area.getActionMap();
	Action Cut = m.get(DefaultEditorKit.cutAction);
	Action Copy = m.get(DefaultEditorKit.copyAction);
	Action Paste = m.get(DefaultEditorKit.pasteAction);
	//Get actions for clipboard above
	
	public static void main(String[] args) {
		new Textedit();
		//all we need to do is start the program here
	}

}
