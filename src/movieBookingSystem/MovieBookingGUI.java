package movieBookingSystem;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class MovieBookingGUI extends JFrame {
	private JRadioButton adult;
	private JRadioButton elderly;
	private JRadioButton child;
	private JCheckBox comp;
	private JList<MovieSession> DefaultListModel;
	private SimpleDriver sd = new SimpleDriver();
	private ArrayList<SeatReservation> currentReservation;
	private JButton[][] seatingButtons;
	private JButton exit;
	private JButton new1;
	private JButton book;
	private JLabel title;
	int oo = 0;
	ButtonGroup buttonGroup = new ButtonGroup();
	
	public void eventHandleListSelection() {
		int index = this.DefaultListModel.getSelectedIndex();
		oo = index;
		for(int i = 0;i<sd.getMovieSessionList()[index].getNUM_COLS();i++) {
			for(int j = 0;j<sd.getMovieSessionList()[index].getNUM_ROWS();j++) {
				char a =this.converIndexToRow(i);
				if(sd.getMovieSessionList()[index].getSeats(a, j)!=null) {
//					this.seatingButtons[i][j].setEnabled(false);
					switch(sd.getMovieSessionList()[index].getSeats(a, j).getType()) {
					case 'C':
						this.seatingButtons[i][j].setBackground(Color.YELLOW);
						break;
					case 'E':
						this.seatingButtons[i][j].setBackground(Color.blue);
						break;
					case 'A':
						this.seatingButtons[i][j].setBackground(Color.RED);
						break;
					}
				} else {
					this.seatingButtons[i][j].setBackground(Color.white);
				}
				this.seatingButtons[i][j].setEnabled(false);
			}
		}
		this.adult.setEnabled(false);
		this.elderly.setEnabled(false);
		this.child.setEnabled(false);
		this.comp.setEnabled(false);
		this.exit.setEnabled(false);
		this.book.setEnabled(false);
		this.new1.setEnabled(true);
	}
	public void eventHandleExit() {
		this.eventHandleListSelection();
		currentReservation.clear();
		
	}
	public void eventHandleNew() {
	    try {
	    	eventHandleListSelection();
	    } catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, "You have not choose one of the film!", "Error",JOptionPane.ERROR_MESSAGE); 
	    }
		this.adult.setEnabled(true);
		this.elderly.setEnabled(true);
		this.child.setEnabled(true);
		this.comp.setEnabled(true);
		this.exit.setEnabled(true);
		this.book.setEnabled(true);
		currentReservation = new ArrayList<SeatReservation>();
		
	}
	public void eventHandleAdult() {
		for(int i = 0;i<sd.getMovieSessionList()[oo].getNUM_COLS();i++) {
			for(int j = 0;j<sd.getMovieSessionList()[oo].getNUM_ROWS();j++) {
				char a =this.converIndexToRow(i);
				if(sd.getMovieSessionList()[oo].getSeats(a, j)!=null) {
					this.seatingButtons[i][j].setEnabled(false);
					switch(sd.getMovieSessionList()[oo].getSeats(a, j).getType()) {
					case 'C':
						this.seatingButtons[i][j].setBackground(Color.YELLOW);
						break;
					case 'E':
						this.seatingButtons[i][j].setBackground(Color.blue);
						break;
					case 'A':
						this.seatingButtons[i][j].setBackground(Color.RED);
						break;
					}
				} else {
					this.seatingButtons[i][j].setEnabled(true);
				}
			}
		}
	}

	public void eventHandleButton(int i,int j) {
		char a = this.converIndexToRow(i);
		if (adult.isSelected()) {
			AdultReservation ad = new AdultReservation(a, j);
			this.seatingButtons[i][j].setBackground(Color.green);
			if (comp.isSelected() == true) {
				ad.setComplementary(true);
			}
			this.currentReservation.add(ad);
		}
		else if(elderly.isSelected()) {
			ElderlyReservation ed = new ElderlyReservation(a,j);
			this.seatingButtons[i][j].setBackground(Color.GREEN);
			if(comp.isSelected() == true) {
				ed.setComplementary(true);
			}
			this.currentReservation.add(ed);
		} else if(child.isSelected()) {
			ChildReservation ch = new ChildReservation(a,j);
			this.seatingButtons[i][j].setBackground(Color.GREEN);
			if(comp.isSelected() == true) {
				ch.setComplementary(true);
			}
			this.currentReservation.add(ch);
		}
		
	}
	public void eventHandleBook() {
		boolean success = sd.getMovieSessionList()[oo].applyBookings(currentReservation);
		if(success == true) {
			float price = 0;
			for(int i = 0;i<currentReservation.size();i++) {
				int j = currentReservation.get(i).getCol();
				int k = this.convertRowToIndex(currentReservation.get(i).getRow());
				switch(currentReservation.get(i).getType()) {
				case 'C':
					this.seatingButtons[k][j].setBackground(Color.YELLOW);
					break;
				case 'E':
					this.seatingButtons[k][j].setBackground(Color.blue);
					break;
				case 'A':
					this.seatingButtons[k][j].setBackground(Color.RED);
					break;
				}
				price = price + currentReservation.get(i).getTicketPrice();
			}
			JOptionPane.showMessageDialog(null, "Ticket Cost is : &"+price, currentReservation.size()+" Ticket",JOptionPane.ERROR_MESSAGE); 
			this.eventHandleListSelection();
		} else {
			JOptionPane.showMessageDialog(null, "CANNOT BOOK CHILD IN R MOVIE OR UNSUPERVISED IN M MOVIE", "Ticket not booked",JOptionPane.ERROR_MESSAGE);
			this.eventHandleListSelection();
		}
	}
	public MovieBookingGUI()  {
		
		setLayout(null);
		
		this.DefaultListModel = new JList<MovieSession>(this.sd.getMovieSessionList());
		seatingButtons = new JButton[sd.getMovieSessionList()[oo].getNUM_COLS()][sd.getMovieSessionList()[oo].getNUM_ROWS()];
		
		this.DefaultListModel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(DefaultListModel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setLocation(780, 100);
		scrollPane.setSize(500, 620);
		this.add(scrollPane);
		
		this.title = new JLabel("    MOVIE BOOKING SYSTEM");
		this.title.setLocation(0, 0);
		this.title.setSize(1280, 100);
		this.title.setFont(new Font("Arial",1,80));
		this.add(title);
		
		
		this.adult = new JRadioButton("Adult");
		this.adult.setLocation(150, 720);
		this.adult.setSize(150, 50);
		this.add(adult);
		this.adult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventHandleAdult();
			}
		});

		
		this.child = new JRadioButton("Child");
		this.child.setLocation(300, 720);
		this.child.setSize(150, 50);
		this.add(child);
		this.child.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventHandleAdult();
			}
		});

		
		this.elderly = new JRadioButton("Elderly");
		this.elderly.setLocation(450, 720);
		this.elderly.setSize(150, 50);
		this.add(elderly);
		this.elderly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventHandleAdult();
			}
		});
		
		buttonGroup.add(adult);
		buttonGroup.add(child);
		buttonGroup.add(elderly);
		
		this.comp = new JCheckBox("Complementary");
		this.comp.setLocation(600,720);
		this.comp.setSize(250, 50);
		this.add(comp);
		
		this.exit = new JButton("Exit");
		this.exit.setLocation(850,720);
		this.exit.setSize(100, 50);
		this.add(exit);
		this.exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventHandleExit();
			}
		});
		
		this.new1 = new JButton("New");
		this.new1.setLocation(960,720);
		this.new1.setSize(100, 50);
		this.add(new1);
		this.new1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventHandleNew();
			}
		});
		
		this.book = new JButton("Book");
		this.book.setLocation(1070,720);
		this.book.setSize(100, 50);
		this.add(book);
		this.book.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventHandleBook();
			}
		});
		
		for(int i = 0;i<sd.getMovieSessionList()[oo].getNUM_COLS();i++) {
			for(int j =0;j<sd.getMovieSessionList()[oo].getNUM_ROWS();j++) {
				seatingButtons[i][j] = new JButton(this.converIndexToRow(j)+","+i);
				seatingButtons[i][j].setLocation(i*130, 100+j*77);
				seatingButtons[i][j].setSize(130, 77);
				this.add(seatingButtons[i][j]);
				int k = i;
				int l = j;
				this.seatingButtons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eventHandleButton(k,l);
					}
				});
			}
		}
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				eventHandleWindowOpened();
			}
		});
		
		setSize(1280, 800); 
		this.update();
		
		
	}
	private void eventHandleWindowOpened() {
		this.adult.setEnabled(false);
		this.elderly.setEnabled(false);
		this.child.setEnabled(false);
		this.comp.setEnabled(false);
		this.exit.setEnabled(false);
		this.book.setEnabled(false);
		this.new1.setEnabled(true);
		for(int i = 0;i<6;i++) {
			for (int j = 0;j<8;j++) {
				this.seatingButtons[i][j].setEnabled(false);
			}
		}
		
	}
	public void update() {
		this.DefaultListModel.setListData(this.sd.getMovieSessionList());
		
	}
	private char converIndexToRow(int rowIndex) {
		switch(rowIndex) {
		case 0:
			return 'A';
		case 1:
			return 'B';
		case 2:
			return 'C';
		case 3:
			return 'D';
		case 4:
			return 'E';
		case 5:
			return 'F';
		case 6:
			return 'G';
		case 7:
			return 'H';
		}
		return ' ';
	}
	public static int convertRowToIndex(char rowLetter) {
		switch(rowLetter) {
		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		case 'F':
			return 5;
		case 'G':
			return 6;
		case 'H':
			return 7;
		}
		return -1;
	}
}
