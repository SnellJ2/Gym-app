
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface implements ActionListener {

	private static JTextField text;
	private static JFrame frame;
	private static JButton submit;
	private static JButton newDay;
	private static JLabel label;
	private static JLabel label2;
	public static int proteinAmount = 0;
	public UserInterface(int protein)
	{
		proteinAmount = protein;
		submit = new JButton("Submit");
		label = new JLabel("Enter Protein Amount Consumed");
		label2 = new JLabel("Protein left today is " + proteinAmount);
		
		frame = new JFrame("GymApp");
		text = new JTextField(16);
		label2.setBounds(150, 40, 200, 50);
		JPanel p = new JPanel();
		submit.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					proteinAmount = proteinAmount - Integer.parseInt(text.getText().trim());
					label2.setText("Protein left today is " + proteinAmount);
					text.setText(" ");
				}
			}
		);
		
		newDay = new JButton("Go To Next Day");
		newDay.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						proteinAmount = protein;
						label2.setText("Protein left today is " + proteinAmount);
						text.setText(" ");
					}
				}
			);
		p.add(newDay);
		p.add(text);
		p.add(submit);
		p.add(label);
		frame.add(label2);
		frame.add(p);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		frame.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}
