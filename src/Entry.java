import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Entry implements ActionListener {
public static int HEIGHT = 0; //these are the user entered stats
public static int WEIGHT = 0;
public static int WORKOUT = 0;
public static int GENDER_INT = 0;
public static int BMR = 0;
public static String GENDER = "a";
public static int AGE = 0;
public static int RECCOMENDED_PROTEIN = 0;
public static int PROTEIN_AMOUNT = 0;
private static JTextField text;
private static JFrame frame;
private static JButton submit;
private static JButton submit2;
private static JButton submit3;
private static JButton submit4;

private static JButton submitProtein;
private static JButton newDay;
private static JLabel label;
private static JLabel label2;
public static Queries q;
public static boolean test;
public static int proteinAmount = 0;
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		q = new Queries();
	
		test = q.tableExists();
		if(q.tableExists())
		{
			HEIGHT = q.get_height();
			WEIGHT = q.get_weight();
			AGE = q.get_age();
			GENDER_INT = q.get_gender();
			System.out.println("HEIGHT IS " + HEIGHT);
			System.out.println("WEIGHT IS " + WEIGHT);
			System.out.println("GENDER IS " + GENDER_INT);
			System.out.println("AGE IS " + AGE);
		}

		q.newTable();

		submit = new JButton("Submit");
		submit.setBounds(400, 50, 100, 25);
		label2 = new JLabel("Protein left today is " + proteinAmount);
		label2.setBounds(200, 80, 300, 25);
		JPanel p = new JPanel();
		
		p.setLayout(null);		
		p.setBounds(100, 40, 400, 200);
		submitProtein = new JButton("Submit");
		submitProtein.setBounds(400, 50, 100, 25);

		frame = new JFrame("GymApp");
		
		newDay = new JButton("Go To Next Day");
		newDay.setBounds(25, 50, 150, 25);
		p.add(newDay);
		newDay.setVisible(false);
		if(HEIGHT == 0 || WEIGHT == 0)
		{
		
			label = new JLabel("Enter Gender Please: M or F ");	

		
			text = new JTextField(16);
			submit2 = new JButton("Submit");
			submit2.setBounds(400, 50, 100, 25);
			submit3 = new JButton("Submit");
			submit3.setBounds(400, 50, 100, 25);
			submit4 = new JButton("Submit");
			submit4.setBounds(400, 50, 100, 25);
			p.add(submit2); //ok its confusing it goes submit2-> submit4->submit1->submit3 
			submit.addActionListener(
				new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					HEIGHT = Integer.parseInt(text.getText().trim());
					text.setText(" ");
					label.setText("Enter Weight in lbs ");					
					p.remove(submit);
					p.add(submit3);

					
					}
				}
			);
			
			submit4.addActionListener(
					new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						AGE = Integer.parseInt(text.getText().trim());
						text.setText(" ");
						if(AGE > 0 && AGE <= 100 )
						{
							label.setText("Enter Height in Inches: ");					
							p.remove(submit4);
							p.add(submit);
						}
						else
						{
							label.setText("Enter Your Age Please: 0-100 only ");					
						}
						
					}
				}
			);
			
			submit2.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GENDER = text.getText().trim();
						if(GENDER.equals("M") || GENDER.equals("m") || GENDER.equals("F") || GENDER.equals("f"))
						{
							GENDER_INT = 1;
							label.setText("Enter Your Age Please: ");	
							p.remove(submit2);
							p.add(submit4);
						}
						text.setText(" ");

					}
				}
			);
			submit3.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					WEIGHT = Integer.parseInt(text.getText().trim());
					text.setText(" ");
					RECCOMENDED_PROTEIN = getProtein();
					proteinAmount = RECCOMENDED_PROTEIN;
					p.add(label2);
					p.remove(submit3);
					label.setText("Enter Protein Amount Consumed");
					label2.setText("Protein left today is " + proteinAmount);
					BMR = calculateBmr();
					p.add(submitProtein);
					newDay.setVisible(true);
					}
				}
			);
		}
		
		else
		{
			label = new JLabel("Enter Protein Amount Consumed");
			
			frame = new JFrame("GymApp");
			text = new JTextField(16);
			
			RECCOMENDED_PROTEIN = getProtein();
			proteinAmount = RECCOMENDED_PROTEIN;
			label2.setText("Protein left today is " + proteinAmount);
			frame.add(label2);
			newDay.setVisible(true);
			
			frame.add(submitProtein);

		}

		submitProtein.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
					proteinAmount = proteinAmount - Integer.parseInt(text.getText().trim());
					label2.setText("Protein left today is " + proteinAmount);
					text.setText(" ");
					}
				}
			);

		newDay.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					proteinAmount = RECCOMENDED_PROTEIN;						
					label2.setText("Protein left today is " + proteinAmount);
					text.setText(" ");
					}
				}
			);
			
		text.setBounds(200, 50, 200, 25);
		label.setBounds(200, 20, 300, 25);
		p.add(text);
		p.add(label);
		frame.add(p);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.setVisible(true);
		
		
	}

	public static int calculateBmr()
	{
		
		int bmr = 0;
		int weightKg = (int) (WEIGHT/2.205);
		int heightCM = (int) (HEIGHT*2.54);
		if(GENDER.equals("M") || GENDER.equals("m"))
		{
			bmr = (int) ((int) (13.397 * weightKg) + (int) (4.799 * heightCM) - (int) (5.677 * AGE) + 88.362);
			bmr += 300;
		}
		else
		{
			bmr = (int) ((int) (9.247 * weightKg) + (int) (3.098 * heightCM) - (int) (4.33 * AGE) + 447.593);
			bmr += 300;
		}
		q.insert(HEIGHT, WEIGHT, GENDER_INT, AGE);
		System.out.println("inserting");
		return bmr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static int getProtein()
	{
		int pro = 0;
		if(GENDER.equals("M") || GENDER.equals("m"))
		{
			pro = (int) (1.4 * WEIGHT);
		}
		else
		{
			pro = WEIGHT;

		}

		return pro;
		
	}
	
}

