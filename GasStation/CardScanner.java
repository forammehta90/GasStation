import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CardScanner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardScanner extends Screen implements IDisplayComponent
{   CardMachine cm = null;
    private World world;
    Card c = null;
    CardMachine cardmachine;
    boolean f = true;
    ZipcodeMachine zipcodemachine;
    boolean flag = true;
    Receiver r = null;
    CancelCommand cc = null;
    Invoker in = null;
    boolean disp_screen= true;
    
    public CardScanner(World world) {
        this.world = world;
        this.r  = new CreditReceiver();
        
    }

    /**
     * Act - do whatever the CardScanner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    	
    	if (disp_screen)
    	{
    		display_screen();
    	}
        if (getOneIntersectingObject(Card.class) != null)
        {
        	
 
        	
            c = (Card)getOneIntersectingObject(Card.class);
            
            
        if ( c.getClass() == ChaseCC.class)
        {
        	getWorld().removeObject(getWorld().getObjects(ChaseCC.class).get(0));
        	cardmachine.insertCard();
        	if (flag)
        	{
        		
        		cardmachine.insertPin();
        		flag = false;
        	}
            
        }
        if ( c.getClass() == FakeCC.class && f)
        {
        	f = false;
        	getWorld().removeObject(getWorld().getObjects(FakeCC.class).get(0));
        	cardmachine.insertCard();
            cardmachine.ejectCard();
            getWorld().addObject(new FakeCC(getWorld()), 100, 350);
            
        }
        if ( c.getClass() == DiscoverCC.class)
        {
        	getWorld().removeObject(getWorld().getObjects(DiscoverCC.class).get(0));
        	
        	cardmachine.insertCard();
        	if (flag)
        	{
        		
        		cardmachine.insertPin();
        		flag = false;
        	}
        }
        }
                
        MouseInfo mouse = Greenfoot.getMouseInfo();
   	 if (Greenfoot.mouseClicked(null) )
   			 {
   		 
   			 if ((mouse != null) && (!flag) && cardmachine.cardState.getClass().getCanonicalName() == cardmachine.hasCorrectPin.getClass().getCanonicalName())
        {
   		 	if (mouse.getX() >= 370 && mouse.getX()<=525 && mouse.getY() >= 280 && mouse.getY() <= 450)
   		 	{
   		 		if (mouse.getX() >= 379 && mouse.getX()<=403 && mouse.getY() >= 291 && mouse.getY() <= 315)
   		 		{
   		 			zipcodemachine.number("1");
   		 		}
   		 		if (mouse.getX() >= 411 && mouse.getX()<=437 && mouse.getY() >= 291 && mouse.getY() <= 315)
   		 		{
   		 			zipcodemachine.number("2");
   		 		}
   		 		if (mouse.getX() >= 444 && mouse.getX()<=471 && mouse.getY() >= 291 && mouse.getY() <= 315)
   		 		{
   		 			zipcodemachine.number("3");
   		 		}
   		 		if (mouse.getX() >= 379 && mouse.getX()<=403 && mouse.getY() >= 327 && mouse.getY() <= 352 )
   		 		{
   		 			zipcodemachine.number("4");
   		 		}
   		 		if (mouse.getX() >= 411 && mouse.getX()<=437 && mouse.getY() >= 327 && mouse.getY() <= 352)
   		 		{
   		 			zipcodemachine.number("5");
   		 		}
   		 		if (mouse.getX() >= 444 && mouse.getX()<=471 && mouse.getY() >= 327 && mouse.getY() <= 352)
   		 		{
   		 			zipcodemachine.number("6");
   		 		}
   		 		if (mouse.getX() >= 379 && mouse.getX()<=403 && mouse.getY() >= 363 && mouse.getY() <= 388)
   		 		{
   		 			zipcodemachine.number("7");
   		 		}
   		 		if (mouse.getX() >= 411 && mouse.getX()<=437 && mouse.getY() >= 363 && mouse.getY() <= 388)
   		 		{
   		 			zipcodemachine.number("8");
   		 		}
   		 		if (mouse.getX() >= 444 && mouse.getX()<=471 && mouse.getY() >= 363 && mouse.getY() <= 388)
   		 		{
   		 			zipcodemachine.number("9");
   		 		}
   		 		if (mouse.getX() >= 411 && mouse.getX()<=437 && mouse.getY() >= 399 && mouse.getY() <= 424)
   		 		{
   		 			zipcodemachine.number("0");
   		 		}
   		 		if (mouse.getX() >= 485 && mouse.getX()<=514 && mouse.getY() >= 327 && mouse.getY() <= 352)
   		 		{
   		 			zipcodemachine.backspace();
   		 		}
   		 		if (mouse.getX() >= 485 && mouse.getX()<=514 && mouse.getY() >= 291 && mouse.getY() <= 315)
   		 		{
   		 			zipcodemachine.cancel();
   		 		}
   		 		if (mouse.getX() >= 485 && mouse.getX()<=514 && mouse.getY() >= 363 && mouse.getY() <= 388)
   		 		{
   		 			zipcodemachine.enter();
   		 			if(zipcodemachine.validZipcode() ) {
   		 				boolean vaildZip = true;
   		 				getWorld().getObjects(GasOptions.class).get(0).setCard(c);
   		 				getWorld().getObjects(GasOptions.class).get(0).act1(vaildZip);
   		 				
   		 			}
   		 		}
   		 	}
        }
   			 
   			 if (mouse != null && mouse.getX() >= 569 && mouse.getX()<=584 && mouse.getY() >= 208 && mouse.getY() <= 225)
   			 {
   				 System.out.println("Cancel");
   				 this.cc = new CancelCommand(r);
   				 in = new Invoker(cc);
   				 in.execute();
   				 
   			 }
   			if (mouse != null && mouse.getX() >= 319 && mouse.getX()<=333 && mouse.getY() >= 121 && mouse.getY() <= 136)
  			 {
  				 System.out.println("Help");
  			 }
   			 }
   		 
    }

    private void display_screen() {
    	Display d  =getWorld().getObjects(Display.class).get(0);
        this.cardmachine = new CardMachine(d);
        this.zipcodemachine = new ZipcodeMachine(d);

    	
    	d.clear();
    	d.setText("Help");
    	d.setText("Cancel");
		d.setText("Scan Your Card!");
    	disp_screen = false;
	}

	@Override
    public void display() {
        world.addObject(this, 550, 380);
    }
}