
import java.awt.*;

import Optical.Elements.*;
import Optical.Rays.*;
import Optical.Materials.*;
import Optical.Gui.*;

import Utils.*;

public class FieldLensApp extends OpticalApp
{
	OpticalCanvas		canvas;
	OpticalControl		controls;
	OpticalDevice		v;

	Homogeneous		h;
	DeviceSwitcher		ds;
	RayCaster		RC;

	double initialRadius = 100.0, initialApertureRadius = 30.0;

	public void init()
	{
		setLayout( new BorderLayout() );
		canvas = new OpticalCanvas();
		BuildDevice();
		canvas.SetDevice( v );

		/*RC = new ParallelRays(
			new FPoint( 20.0, v.GetAxis().y, 0 ),
			new FPoint( 1, 0.0, 0 ),
			15, 120, 430 );*/
		RC = new PointSource(
			new FPoint( 20.0, v.GetAxis().y, 0.0 ),
			new FPoint( 120.0, v.GetAxis().y  - 48.0, 0.0 ),
			new FPoint( 120.0, v.GetAxis().y  + 48.0, 0.0 ),
			9, 0.430 );
		canvas.SetRayCaster( RC );

		add( "Center", canvas );
		controls = new OpticalControl( this );
		BuildControls();
		add( "West", controls );
	}

	public void BuildDevice()
	{
		Material vaccum, glass;
		SimpleLens		fl;
		Nothing			nofl;

		vaccum = new Material( "Vaccum", new ConstantParameter( 1.0 ) );

		v = new OpticalDevice();
		v.MoveAxis( 100, 0 );
		v.MoveOnAxis( 20 );

		v.Append( new Homogeneous( 100, vaccum ) );

		v.Append( new SimpleLens( 50, 50 ) );
		v.Append( new Homogeneous( 100, vaccum ) );

		fl = new SimpleLens( 50, 50 );
		nofl = new Nothing();

		ds = new DeviceSwitcher();
		ds.AddDevice( "With", fl );
		ds.AddDevice( "Without", nofl );
		ds.SetCurrentDevice( "Without" );
		v.Append( ds );

		v.Append( new Homogeneous( 100, vaccum ) );
		v.Append( new SimpleLens( 50, 50 ) );
		v.Append( new Homogeneous( 100, vaccum ) );
		v.Append( new Screen( 100, 100 ) );

		v.Rearrange();
	}

	public void BuildControls()
	{
		controls.setLayout( null );
		controls.setSize( 80, 250 );

		Scrollbar sb1 = new Scrollbar(
			Scrollbar.VERTICAL,
			0,	// initial
			10,	// thumb
			-48,	// min
			58	// max
			);

		sb1.setName( "RaysPos" );
		sb1.addAdjustmentListener( controls );
		controls.add( sb1 );
		sb1.setBounds( 50, 30, 20, 140 );

		Checkbox c = new Checkbox( "field lens", false );
		c.addItemListener( controls );
		c.setBounds( 10, 180, 70, 30 );
		controls.add( c );

		Button Reset = new Button( "Reset" );
		Reset.setName( "Reset" );
		Reset.addActionListener( controls );
		controls.add( Reset );
		Reset.setBounds( 10, 220, 70, 20 );

	}

	public void Scrollbar( String name, int value )
	{
		if( name.equals( "RaysPos" ) )
		{
			FPoint newPos, newDir;

			newPos = new FPoint( 20.0, v.GetAxis().y + value, 0 );
			//newDir = new FPoint( 50.0, - newPos.y + v.GetAxis().y, 0 );
			((PointSource)RC).Move( newPos );
		}
		canvas.ForceRedraw();
	}

	public void Checkbox( String name, boolean value )
	{
		if( value )
		{
			ds.SetCurrentDevice( "With" );
		}
		else
		{
			ds.SetCurrentDevice( "Without" );
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
			ds.SetCurrentDevice( "Without" );
   			((Checkbox) controls.getComponent(1)).setState(false);
			((Scrollbar) controls.getComponent(0)).setValue(0);
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