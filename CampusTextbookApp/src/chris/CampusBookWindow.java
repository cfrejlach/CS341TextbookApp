package chris;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class CampusBookWindow {

	private JFrame frame;
	private JTextField title;
	private JTextField sku;
	private JTextField price;
	private JTextField quantity;
	
	public static ArrayList<Book> bookList = new ArrayList<Book>();
	
	public static void readSave() {
		
		File stockFile = new File("inventory.txt");
		
		try {
			if(!stockFile.createNewFile() && stockFile.length() != 0) {
				ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(stockFile));
				int size = objIn.readInt();
				
				for(int i = 0; i < size; i++) {
					Book t = (Book) objIn.readObject();
					bookList.add(t);
				}
				
				objIn.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					readSave();
					CampusBookWindow window = new CampusBookWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				save();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CampusBookWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Title:");
		lblNewLabel.setBounds(21, 11, 63, 14);
		frame.getContentPane().add(lblNewLabel);
		
		title = new JTextField();
		title.setBounds(82, 8, 205, 23);
		frame.getContentPane().add(title);
		title.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("SKU:");
		lblNewLabel_1.setBounds(10, 45, 35, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		sku = new JTextField();
		sku.setBounds(44, 42, 109, 20);
		frame.getContentPane().add(sku);
		sku.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price: $");
		lblNewLabel_2.setBounds(163, 45, 44, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		price = new JTextField();
		price.setBounds(207, 42, 80, 20);
		frame.getContentPane().add(price);
		price.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity:");
		lblNewLabel_3.setBounds(292, 45, 54, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		quantity = new JTextField();
		quantity.setBounds(356, 42, 54, 20);
		frame.getContentPane().add(quantity);
		quantity.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(21, 144, 389, 106);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Add Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sku.getText() != "" && title.getText() != "" && price.getText() != "" && quantity.getText() != "") {
					String bookTitle = title.getText();
					int bookSku = Integer.valueOf(sku.getText());
					double bookPrice = Double.valueOf(price.getText());
					int bookQuan = Integer.valueOf(quantity.getText());
					Book newBook = new Book(bookTitle, bookSku, bookPrice, bookQuan);
					bookList.add(newBook);
					System.out.println("book added!");
					title.setText("");
					sku.setText("");
					price.setText("");
					quantity.setText("");
					textArea.setText("book added!");
				}
			}
		});
		btnNewButton.setBounds(20, 70, 109, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bookSku = Integer.valueOf(sku.getText());
				for(int i = 0; i <= bookList.size(); i++) {
					if(bookSku == bookList.get(i).getSku()) {
						bookList.remove(bookList.get(i));
					}
				}
			}
		});
		btnNewButton_1.setBounds(302, 70, 109, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("look up (SKU)");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sku.getText().trim().length() != 0) {
					int bookSku = Integer.valueOf(sku.getText());
					System.out.println("bookSku is " + bookSku);
					for(int i = 0; i < bookList.size(); i++) {
						System.out.println(bookList.get(i).getSku());
						if(bookSku == bookList.get(i).getSku()) {
							textArea.setText(bookList.get(i).toString());
						}else {
							textArea.setText("BOOK NOT FOUND");
						}
					}
				}else {
					textArea.setText("PLEASE ENTER A SKU NUMBER TO LOOK UP THE BOOK BY");
				}
			}
		});
		btnNewButton_2.setBounds(21, 110, 109, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Inventory");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = "";
				if(bookList.size() > 0) {
					for(int i = 0; i < bookList.size(); i++) {
						output += bookList.get(i).toString(); 
					}
					textArea.setText(output);
				}else {
					textArea.setText("NO BOOKS IN INVENTORY");
				}
			}
		});
		btnNewButton_3.setBounds(301, 110, 109, 23);
		frame.getContentPane().add(btnNewButton_3);
	}
	
	public static void save() {
		File stockFile = new File("inventory.txt");
		
		ObjectOutputStream objOut;
		try {
			objOut = new ObjectOutputStream(new FileOutputStream(stockFile));
		
			objOut.writeInt(bookList.size());
			Iterator<Book> i = bookList.iterator();
			while(i.hasNext()){
				objOut.writeObject(i.next());
			}
		
			objOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
