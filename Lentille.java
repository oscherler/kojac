
import java.awt.*;

import Optical.Elements.*;
import Optical.Rays.*;
import Optical.Materials.*;
import Optical.Gui.*;

import Utils.*;

public class Lentille extends OpticalApp
{
	OpticalCanvas		canvas;
	OpticalControl		controls;
	OpticalDevice		v;
	RayCaster			RC;
	Homogeneous			before;

	static final double focale = 25.0;

	public void init()
	{
		setLayout( new BorderLayout() );
		canvas = new OpticalCanvas();
		BuildDevice();
		canvas.SetDevice( v );

		RC = new ThreeRays(
			new FPoint( v.GetX(), v.GetAxis().y - 15.0, 0.0 ),
			new FPoint( before.GetX() + before.GetWidth() - focale, v.GetAxis().y, 0.0 ),
			new FPoint( before.GetX() + before.GetWidth(), v.GetAxis().y, 0.0 ),
			477 );
		canvas.SetRayCaster( RC );

		add( "Center", canvas );
		controls = new OpticalControl( this );
		BuildControls();
		add( "South", controls );
	}

	public void BuildDevice()
	{
		Material vaccum = new Material( "Vaccum", new ConstantParameter( 1.0 ) );

		v = new OpticalDevice();
		v.MoveAxis( 100, 0 );
		v.MoveOnAxis( 20 );
		
		before = new Homogeneous( 200, vaccum );
		v.Append( before );

		v.Append( new SimpleLens( focale, 50 ) );
		v.Append( new Homogeneous( 200, vaccum ) );
		
		v.Append( new Screen( 100, 100 ) );

		v.Rearrange();
	}

	public void BuildControls()
	{
		controls.setLayout( null );
		controls.setSize( 200, 120 );
		
		Scrollbar sb1 = new Scrollbar(
			Scrollbar.HORIZONTAL,
			0,	// initial
			10,							// thumb
			5,						// min
			210							// max
			);

		sb1.setName( "RaysPos" );
		sb1.addAdjustmentListener( controls );
		controls.add( sb1 );
		sb1.setBounds( 50, 20, 300, 20 );

		Button Reset = new Button( "Reset" );
		Reset.setName( "Reset" );
		Reset.addActionListener( controls );
		controls.add( Reset );
		Reset.setBounds( 50, 50, 70, 20 );
	
		Checkbox c = new Checkbox( "CB1", false );
		c.addItemListener( controls );
		c.setBounds( 370, 50, 100, 30 );
		controls.add( c );
	}

	public void Scrollbar( String name, int value )
	{
		/*if( name.equals( "RaysPos" ) )
		{
			FPoint newPos, newDir;

			newPos = new FPoint( 20.0, v.GetAxis().y + value, 0 );
			//newDir = new FPoint( 50.0, - newPos.y + v.GetAxis().y, 0 );
			((PointSource)RC).Move( newPos );
		}*/
		before.SetWidth( value );
		((ThreeRays)RC).SetIntersections(
			new FPoint( before.GetX() + before.GetWidth() - focale, v.GetAxis().y, 0.0 ),
			new FPoint( before.GetX() + before.GetWidth(), v.GetAxis().y, 0.0 ) );
		v.Rearrange();
		canvas.ForceRedraw();
	}

	public void Checkbox( String name, boolean value )
	{
		if( value )
		{
			//ds.SetDevice( "With" );
		}
		else
		{
			//ds.SetDevice( "Without" );
		}
		v.Rearrange();
		canvas.ForceRedraw();
	}

	public void Button( String name )
	{
		if( name.equals( "Reset" ) )
		{
			FPoint newPos, newDir;

			newPos = new FPoint( 20.0, v.GetAxis().y, 0 );
			((PointSource)RC).Move( newPos );
		}
		v.Rearrange();
		canvas.ForceRedraw();
	}

	public void destroy()
	{
		remove( canvas );
		remove( controls );
	}

	public void start()
	{
		controls.setEnabled( true );
	}

	public void stop()
	{
		controls.setEnabled( false );
	}
}