
import java.awt.*;

import Optical.Elements.*;
import Optical.Rays.*;
import Optical.Materials.*;
import Optical.Gui.*;

import Utils.*;

public class PlanoConvex extends OpticalApp
{
	OpticalCanvas		canvas;
	OpticalControl		controls;
	OpticalDevice		v;

	Homogeneous		h;
	DeviceSwitcher	ds;
	RayCaster		RC;
 	Material		vacuum;

 	double 	LensRadius = 100.0, LensAperture = 100.0,
			BeamWidth = 100.0, Wavelength = 0.4,
   			PlaneCurvature = 0.0, LensThickness = 30.0,
      		LensWidth = 50.0, Distance = 300.0;

	int RaysNumber = 20;

	public void init()
	{
		setLayout( new BorderLayout() );
		canvas = new OpticalCanvas();
		BuildDevice();
		canvas.SetDevice( v );

  		vacuum = new Material( "Vacuum", new ConstantParameter( 1.0 ) );

		RC = new ParallelRays(
			new FPoint( 10.0, 100.0, 0.0 ),
			new FPoint( 1.0, 0.0, 0.0 ),
			RaysNumber,
			BeamWidth,
			Wavelength,
			vacuum );
		canvas.SetRayCaster( RC );

		add( "Center", canvas );
		controls = new OpticalControl( this );
		BuildControls();
		add( "South", controls );
	}

	public void BuildDevice()
	{
		Material vaccum, glass;
		ThickLens forward, backward;

 		vacuum = new Material( "Vacuum", new ConstantParameter( 1.0 ) );
   		glass = new Material( "Glass", new ConstantParameter( 1.5 ) );

  		v = new OpticalDevice();
		v.MoveAxis( 100, 0 );
		v.MoveOnAxis( 20 );

		v.Append( new Homogeneous( 40, vacuum ) );

		forward = new ThickLens( -1/LensRadius, PlaneCurvature, LensThickness, LensWidth, LensAperture, glass, vacuum );
		backward = new ThickLens( PlaneCurvature, 1/LensRadius, LensThickness, LensWidth, LensAperture, glass, vacuum );

		ds = new DeviceSwitcher();
		ds.AddDevice( "Right", forward );
		ds.AddDevice( "Wrong", backward );
		ds.SetCurrentDevice( "Wrong" );
		v.Append( ds );

		v.Append( new Homogeneous( Distance, vacuum ) );
		v.Append( new Screen( 100, 100 ) );

		v.Rearrange();
	}

	public void BuildControls()
	{
		controls.setLayout( null );
		controls.setSize( 200, 60 );

		Button Wrong = new Button( "Wrong" );
		Wrong.setName( "Wrong orientation" );
		Wrong.addActionListener( controls );
		controls.add( Wrong );
		Wrong.setBounds( 240, 20, 60, 20 );

		Button Right = new Button( "Right" );
		Right.setName( "Right orientation" );
		Right.addActionListener( controls );
		controls.add( Right );
		Right.setBounds( 150, 20, 60, 20 );

	}


	public void Button( String name )
	{
		if( name.equals( "Right" ) )
		{
			ds.SetCurrentDevice( "Right" );
		}
		else
		{
			ds.SetCurrentDevice( "Wrong" );
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