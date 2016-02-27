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
		
	
		Save.setEnabled(false);
		SaveAs.setEnabled(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		area.addKeyListener(k1);
		setTitle(currentFile);
		setVisible(true);
	}
		
		
	private KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			changed = true;
			Save.setEnabled(true);
			SaveAs.setEnabled(true);
		}
	};
	//Will be used to detect changes to file
	
	//Resets text area and offers to save it
	Action New = new AbstractAction("New") {
		public void actionPerformed(ActionEvent e) {
			saveOld();
			area.setText("");
			currentFile = "Untitled";
			setTitle(currentFile);
			changed = false;
			Save.setEnabled(false);
			SaveAs.setEnabled(false);
		}
	};
		
	//Open a file
	Action Open = new AbstractAction("Open") {
		public void actionPerformed(ActionEvent e) {
				saveOld();
				if(dialog.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
					readInFile(dialog.getSelectedFile().getAbsolutePath());
				}
				SaveAs.setEnabled(true);
		}
	};
	//default save
	Action Save = new AbstractAction("Save") {
		public void actionPerformed(ActionEvent e) {
			if(!currentFile.equals("Untitled"))
				saveFile(currentFile);
			else
				saveFileAs();
		}
	};
	//save as
	Action SaveAs = new AbstractAction("Save as...") {
		public void actionPerformed(ActionEvent e) {
			saveFileAs();
		}
	};
	
	//Exit program, need to find a way to make the x trigger this
	Action Quit = new AbstractAction("Quit") {
		public void actionPerformed(ActionEvent e) {
			saveOld();
			System.exit(0);
		}
	};
	//Actions will be coded later, want to get UI working first
	
	//Methods here
	//Save as method
	private void saveFileAs() {
		if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			saveFile(dialog.getSelectedFile().getAbsolutePath());
	}
	//Save a file if quitting or using new
	private void saveOld() {
		if(changed) {
			if(JOptionPane.showConfirmDialog(this, "Would you like to save "+ currentFile +" ?","Save",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
				saveFile(currentFile);
		}
	}
	//open a file
	private void readInFile(String fileName) {
		try {
			FileReader r = new FileReader(fileName);
			area.read(r,null);
			r.close();
			currentFile = fileName;
			setTitle(currentFile);
			changed = false;
		}
		catch(IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this,"Editor can't find the file called "+fileName);
		}
	}
	//save a file
	private void saveFile(String fileName) {
		try {
			FileWriter w = new FileWriter(fileName);
			area.write(w);
			w.close();
			currentFile = fileName;
			setTitle(currentFile);
			changed = false;
			Save.setEnabled(false);
		}
		catch(IOException e) {
		}
	}
	
	
	
	
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
