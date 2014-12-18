
import java.awt.*;

import Optical.Elements.*;
import Optical.Rays.*;
import Optical.Materials.*;
import Optical.Gui.*;

import Utils.*;

public class RealDoublet extends OpticalApp
{
	OpticalCanvas		canvas;
	OpticalControl		controls;
	// details of controls
	Scrollbar		position_scrollbar;
 	Button			reset_button;

	OpticalDevice		dev;
	// details of the Device
	RayCaster		object_point;
	ThickLens		lens1, lens2;
	Homogeneous		h1, h2, h3;
 	Screen			screen;

	static final double minGap=0.01;

	double 	lens1Position = 100.0, lensCurvatureRadius = 200.0,
 			lensDistance = 100.0, lensAperture = 80.0,
	 		screenDistance = 250.0, screensize = 200.0,
    		lensThickness = 30.0,
			effectiveLens1Position, effectiveLens2Position, effectiveScreenPosition;

	Material	vacuum, BK7;

	public void init()
	{
		setLayout( new BorderLayout() );
		canvas = new OpticalCanvas();
		BuildDevice();
		canvas.SetDevice( dev );

		object_point = new PointSource(
			new FPoint( 0.0, dev.GetAxis().y + 0.0, 0.0 ),
			new FPoint( 100.0, dev.GetAxis().y  - 48.0, 0.0 ),
			new FPoint( 100.0, dev.GetAxis().y  + 48.0, 0.0 ),
			9, 0.430 );
		canvas.SetRayCaster( object_point );

		add( "Center", canvas );
		controls = new OpticalControl( this );
		BuildControls();
		add( "South", controls );
	}

	public void BuildDevice()
	{
		BK7 = new Material( "BK7", new Sellmeier1Parameters(
			1.03961212,
			6.00069867e-3,
			2.31792344e-1,
			2.00179144e-2,
			1.01046945,
			1.03560653e-2
 		) );

		vacuum = new Material( "Vaccum", new ConstantParameter( 1.0 ) );

		dev = new OpticalDevice();
		dev.MoveAxis( 150, 0 );
		//dev.MoveOnAxis( 20 );

  		// definition of the elements
		h1 = new Homogeneous( lens1Position, vacuum );
  		lens1 = new ThickLens( -1/lensCurvatureRadius, 1/lensCurvatureRadius, lensThickness, minGap, lensAperture, BK7, vacuum );
    	effectiveLens1Position = lens1Position;
		h2 = new Homogeneous( lensDistance, vacuum );
  		lens2 = new ThickLens( -1/lensCurvatureRadius, 1/lensCurvatureRadius, lensThickness, minGap, lensAperture, BK7, vacuum );
    	effectiveLens2Position = lens1Position + lensDistance;
		h3 = new Homogeneous( screenDistance, vacuum );
  		screen = new Screen( screensize, screensize );
    	effectiveScreenPosition = lens1Position + lensDistance + screenDistance;

		// appending them to the system
		dev.Append( h1);
		dev.Append( lens1 );
		dev.Append( h2);
  		dev.Append( lens2 );
		dev.Append( h3);
		dev.Append( screen );

		dev.Rearrange();
	}

	public void BuildControls()
	{
		controls.setLayout( null );
		controls.setSize( 250, 80 );

		Scrollbar sb11 = new Scrollbar(
			Scrollbar.HORIZONTAL,
			0,	// initial
			1,	// thumb
			-95,	// min
			155	// max
			);

		sb11.setName( "lens1Pos" );
		sb11.addAdjustmentListener( controls );
		controls.add( sb11 );
		sb11.setBounds( 10, 0, 200, 20 );

		Scrollbar sb12 = new Scrollbar(
			Scrollbar.HORIZONTAL,
			0,	// initial
			1,	// thumb
			-130,	// min
			130	// max
			);

		sb12.setName( "lens2Pos" );
		sb12.addAdjustmentListener( controls );
		controls.add( sb12 );
		sb12.setBounds( 10, 40, 200, 20 );

		/*Button Reset = new Button( "Reset" );
		Reset.setName( "Reset" );
		Reset.addActionListener( controls );
		controls.add( Reset );
		Reset.setBounds( 10, 220, 70, 20 );*/

	}

	public void Scrollbar( String name, int value )
	{
		if( name.equals( "lens1Pos" ) )
		{
  			effectiveLens1Position=lens1Position + value;
			h1.SetWidth( effectiveLens1Position );
			h2.SetWidth( effectiveLens2Position - effectiveLens1Position );
			dev.Rearrange();
		}
  		else if( name.equals( "lens2Pos" ) )
			{
	  			effectiveLens2Position = lens1Position + lensDistance + value;
      			//effectiveScreenPosition = lens1Position + lensDistance + screenDistance;
				h2.SetWidth( effectiveLens2Position - effectiveLens1Position );
        		//((OpticalElement) h3).SetWidth( screenDistance );
				h3.SetWidth( effectiveScreenPosition - effectiveLens2Position );
 				dev.Rearrange();
			}
		canvas.ForceRedraw();
	}

/*	public void Checkbox( String name, boolean value )
	{
		if( value )
		{
//			ds.SetDevice( "With" );
		}
		else
		{
//			ds.SetDevice( "Without" );
		}
		dev.Rearrange();
		canvas.ForceRedraw();
	}*/

/*	public void Button( String name )
	{
		if( name.equals( "Reset" ) )
		{
			FPoint newPos, newDir;

			newPos = new FPoint( 20.0, dev.GetAxis().y, 0 );
			((PointSource)object_point).Move( newPos );
			//ds.SetDevice( "Without" );
   			((Checkbox) controls.getComponent(1)).setState(false);
			((Scrollbar) controls.getComponent(0)).setValue(0);
		}
		dev.Rearrange();
		canvas.ForceRedraw();
	}
*/
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