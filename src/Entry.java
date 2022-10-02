import java.sql.SQLException;

public class Entry {
public static int HEIGHT = 0; //these are the user entered stats
public static int WEIGHT = 0;
public static int WORKOUT = 0;
public static int BMR = 0;
public static char GENDER = 'a';
public static int AGE = 0;
public static int RECCOMENDED_PROTEIN = 0;

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//Queries q = new Queries();
		//q.insert("Raw Materials", 3000);
		//q.insert("Semifinished Goods", 4000);
		//q.insert("Finished Goods", 5000);
		//COmmenting all the sql stuff for now
		//q.printAll();
		
		HEIGHT = 67;
		WEIGHT = 140;
		GENDER = 'W';

		BMR = calculateBmr();
		AGE = 22;
		System.out.println("Your bmr is " + BMR);
		if(GENDER == 'M') {
			RECCOMENDED_PROTEIN = (int) 1.4 * WEIGHT;

		}
		else
		{
			RECCOMENDED_PROTEIN = WEIGHT;

		}
		new UserInterface(RECCOMENDED_PROTEIN);

	}

	public static int calculateBmr()
	{
		int bmr = 0;
		int weightKg = (int) (WEIGHT/2.205);
		int heightCM = (int) (HEIGHT*2.54);
		if(GENDER == 'M')
		{
			bmr = (int) ((int) (13.397 * weightKg) + (int) (4.799 * heightCM) - (int) (5.677 * AGE) + 88.362);
			bmr += 300;
		}
		else
		{
			bmr = (int) ((int) (9.247 * weightKg) + (int) (3.098 * heightCM) - (int) (4.33 * AGE) + 447.593);
			bmr += 300;
		}
		return bmr;
	}
}
