import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import  java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class PrintReceipt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PrintReceipt extends Screen implements IDisplayComponent
{
	private World world;
	private static double carWashPrice = 40.0;

	public PrintReceipt(World world) {
		this.world = world;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		world.addObject((Actor) this, 350, 410);
	}

	/**
	 * Act - do whatever the PrintReceipt wants to do. This method is called whenever
	 * the 'Act' or 'Run' button gets pressed in the environment.
	 */
	public void act() 
	{
       /* if(Greenfoot.mouseClicked(null)){
        	List<DiscountType> discountTypes = new ArrayList<DiscountType>();
            discountTypes.add(DiscountType.DISCOVER_CC);
            discountTypes.add(DiscountType.CAR_DISCOUNT);
            PrintReceipt printer = (PrintReceipt)(world.getObjects(PrintReceipt.class).get(0));
            printer.printReciept(true, 5, true, FuelType.PREMIUM_UNLEADED, discountTypes);
        }*/
		
	}

	/*public void display() {
		//this.world.addObject(this, 350, 410);
		GreenfootImage txt = new GreenfootImage(900,60);
		txt.setColor(Color.RED);
		txt.fill();
		txt.drawString(message.toString(), 550, 60);
		setImage(txt);

		List<GasStation> gs = this.world.getObjects(GasStation.class);
		GasStation g = gs.get(0);
		g.ok = false;
	}*/

	public  void printReciept() {

		List<DiscountType> discountTypes = new ArrayList<DiscountType>();
		double totalPrice = 0.0;
		double carWashPrice = 0.0;
		//Calculate price based on fuel type
		double fuelPrice = getWorld().getObjects(Clock.class).get(0).cost;
		// Give discounts based on card type
		String a = getWorld().getObjects(GasOptions.class).get(0).getCard().getClass().getCanonicalName();
		if(a == "ChaseCC")
			discountTypes.add(DiscountType.CHASE_CC);
		else if(a == "DiscoverCC")
			discountTypes.add(DiscountType.DISCOVER_CC);
		if(getWorld().getObjects(PumpNozel.class).get(0).isCarwash)
			discountTypes.add(DiscountType.CAR_DISCOUNT);
		
		int f = getWorld().getObjects(PumpNozel.class).get(0).fuelType;
		FuelType ft = null;
		if(f == 87)
			ft = ft.REGULAR_UNLEADED;
		else if(f == 89)
			ft = ft.MID_UNLEADED;
		else if(f == 93)
			ft = ft.PREMIUM_UNLEADED;
		
		for(DiscountType discountType : discountTypes){
			if(discountType.equals(DiscountType.CAR_DISCOUNT))
				carWashPrice = this.carWashPrice - this.carWashPrice * discountType.getDiscountPercent();
			else
				fuelPrice = fuelPrice - fuelPrice * discountType.getDiscountPercent();
		}
		totalPrice = carWashPrice + fuelPrice;
		String fPrice = new DecimalFormat("#.##").format(fuelPrice);
		String cwPrice = new DecimalFormat("#.##").format(carWashPrice);
		String tPrice = new DecimalFormat("#.##").format(totalPrice);
		Receipt receipt = new Receipt(true, ft, fPrice, getWorld().getObjects(PumpNozel.class).get(0).isCarwash, cwPrice, tPrice);
		System.out.println(receipt.generateReceipt());
		
		int mouseX, mouseY;
        MouseInfo mouse = Greenfoot.getMouseInfo();
        mouseX = mouse.getX();
        mouseY = mouse.getY();
        Display display = new Display(getWorld());
        display.setText(mouseX, mouseY, receipt.generateReceipt());	}

}


