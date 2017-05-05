import java.text.DecimalFormat;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Clock here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Clock extends Actor
{

	public double gallons = -0.1;

	private long lastCurrentSecond;
	private long timeSaved = 0;

	public boolean drawGas = false;
	public boolean tankFull = false;
	GreenfootImage gi ;
	
	
	public Clock() {
		gi = new GreenfootImage(189,142);
	}


	public void setText(String msg)
	{
		gi.clear();
		gi.setColor(greenfoot.Color.YELLOW);
		gi.fill();
		gi.setColor(greenfoot.Color.BLACK);
		gi.setFont(gi.getFont().deriveFont(30f)); 
		gi.drawString(msg,10,50);

	}

	public void act() {
		setImage(gi);
		if ((drawGas && !tankFull)) {

			if (gallons>17.9) {
				tankFull = true;
				stopClock();

				setText("Tank Full");
			}
			else if ((System.currentTimeMillis() - lastCurrentSecond >= 10)) {
				lastCurrentSecond += 10;
				gallons = gallons + 0.1;

				String gallonsDone;
				gallonsDone = "gallons "+ new DecimalFormat(("00.00")).format(gallons);

				setText(gallonsDone);
			}

		}
	}

	//	private void drawTime() {
	//
	//		System.out.println("drawing gas");
	//
	//		//        int sec = seconds % 60;
	//
	//		//getImage().clear();
	//
	//		//GreenfootImage gi = new GreenfootImage(189,142);
	//
	//		//gi.scale(130, 70);
	//		//        getImage().setColor(greenfoot.Color.GRAY);
	//		//        getImage().fill();
	//
	//
	//		//        text.drawImage(text, 850, 50);
	//		//        time.drawImage(time, 870, 30);
	//
	//
	//
	//		//        setImage(new GreenfootImage("", 30, greenfoot.Color.BLACK, new greenfoot.Color(0, 0, 0, 0)));
	//		//
	//		//        setImage(new GreenfootImage("Time left:", 30, greenfoot.Color.BLACK, new greenfoot.Color(0, 0, 0, 0)));
	//		//        setImage(new GreenfootImage(remainingTime, 40, greenfoot.Color.BLACK, new greenfoot.Color(0, 0, 0, 0)));
	//		//       
	//
	//
	//	}





	public void startClock() {

		System.out.println("dispense started");
		lastCurrentSecond = System.currentTimeMillis() - timeSaved;
		//drawGas = true;
	}
	public void stopClock() {
		//		timeSaved = System.currentTimeMillis() - lastCurrentSecond;
		//drawGas = false;
	}
	public void resetClock() {
		//gallons = -0.1;
	}
}