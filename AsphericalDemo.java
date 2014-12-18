
import java.awt.*;

import Optical.Elements.*;
import Optical.Rays.*;
import Optical.Materials.*;
import Optical.Gui.*;

import Utils.*;

public class AsphericalDemo extends OpticalApp
{
	OpticalCanvas		canvas;
	OpticalControl		controls;
	OpticalDevice		v;

	Aspherical			asph;
	Homogeneous			h1, h2;
	Aperture			apert;
	RayCaster			rc;

	Material			mat1, mat2;

	Label				DispIndex, DispK, DispC;

	static final double	initDeviceX = 20.0,
						initDeviceY = 100.0,
						initBeamY = 0.0,
						initBeamWidth = 120.0,
						minBeamY = -80.0,
						maxBeamY = 80.0,
						initBeamWavelength = 0.45,
						initAsphRadius = 50.0,
						initAsphC = 1 / 30.0,
						minAsphC = -1 / 10.0,
						maxAsphC = 1 / 10.0,
						AsphCFactor = 1000,
						initAsphK = -2.25,
						minAsphK = -3.0,
						maxAsphK = 2.0,
						AsphKFactor = 100.0,
						initMat1Index = 1.5,
						initMat2Index = 1.0,
						maxIndex = 2.0,
						IndexFactor = 100.0,
						initH1Width = 50.0,
						initH2Width = 80.0,
						initApertureRadius = 40.0,
						minApertureRadius = 0.0,
						maxApertureRadius = 65.0,
						initApertureY = 0.0,
						minApertureY = -50.0,
						maxApertureY = 50.0,
						initScreenDistance = 300.0,
						initScreenWidth = 150.0;

	static final int	initBeamRays = 15;

	public void init()
	{
		setLayout( new BorderLayout() );
		canvas = new OpticalCanvas();
		BuildDevice();
		canvas.SetDevice( v );

		rc = new ParallelRays(
			new FPoint( initDeviceX, initDeviceY + initBeamY, 0.0 ),
			new FPoint( asph.GetX() - initDeviceX, - initBeamY, 0.0 ),
			initBeamRays,
			initBeamWidth,
			initBeamWavelength,
			mat1 );
		canvas.SetRayCaster( rc );

		add( "Center", canvas );
		controls = new OpticalControl( this );
		BuildControls();
		add( "South", controls );
	}

	public void BuildDevice()
	{
		mat1 = new Material( "Mat1", new ConstantParameter( initMat1Index ) );
		mat2 = new Material( "Mat2", new ConstantParameter( initMat2Index ) );

		v = new OpticalDevice();
		v.MoveAxis( initDeviceY, 0.0 );
		v.MoveOnAxis( initDeviceX );

		h1 = new Homogeneous( initH1Width, mat1 );
		apert = new Aperture( initApertureRadius );
		h2 = new Homogeneous( initH2Width, mat1 );
		asph = new Aspherical( initAsphC, initAsphK, initScreenDistance, initAsphRadius, mat2 );

		v.Append( h1 );
		v.Append( apert );
		v.Append( h2 );
		v.Append( asph );
		//v.Append( new Homogeneous( initScreenDistance, mat2 ) );
		v.Append( new Screen( initScreenWidth, initScreenWidth ) );

		v.Rearrange();
	}

	public void BuildControls()
	{
		controls.setLayout( null );
		controls.setSize( 200, 200 );

		Scrollbar sb1 = new Scrollbar(
			//Scrollbar.VERTICAL,
			Scrollbar.HORIZONTAL,
			(int)initBeamY,		// initial
			1,					// thumb
			(int)minBeamY,		// min
			(int)maxBeamY + 1	// max
			);

		sb1.setName( "Beam" );
		sb1.addAdjustmentListener( controls );
		controls.add( sb1 );
		//sb1.setBounds( 20, 20, 20, 110 );
		sb1.setBounds( 110, 110, 250, 20 );

		Label lbl1 = new Label( "Beam direction" );
		controls.add( lbl1 );
		lbl1.setBounds( 10, 110, 95, 20 );

		Scrollbar sb2 = new Scrollbar(
			//Scrollbar.VERTICAL,
			Scrollbar.HORIZONTAL,
			(int)initApertureRadius,		// initial
			1,								// thumb
			(int)minApertureRadius,			// min
			(int)maxApertureRadius + 1		// max
			);

		sb2.setName( "ApertureRad" );
		sb2.addAdjustmentListener( controls );
		controls.add( sb2 );
		//sb2.setBounds( 50, 20, 20, 110 );
		sb2.setBounds( 110, 140, 250, 20 );

		Label lbl2 = new Label( "Aperture rad." );
		controls.add( lbl2 );
		lbl2.setBounds( 10, 140, 95, 20 );

		Scrollbar sb3 = new Scrollbar(
			//Scrollbar.VERTICAL,
			Scrollbar.HORIZONTAL,
			(int)initApertureY,		// initial
			1,								// thumb
			(int)minApertureY,			// min
			(int)maxApertureY + 1		// max
			);

		sb3.setName( "AperturePos" );
		sb3.addAdjustmentListener( controls );
		controls.add( sb3 );
		//sb3.setBounds( 80, 20, 20, 110 );
		sb3.setBounds( 110, 170, 250, 20 );

		Label lbl3 = new Label( "Aperture Y" );
		controls.add( lbl3 );
		lbl3.setBounds( 10, 170, 95, 20 );

		Scrollbar sb4 = new Scrollbar(
			Scrollbar.HORIZONTAL,
			(int)(initAsphK * AsphKFactor),					// initial
			1,					// thumb
			(int)(minAsphK * AsphKFactor),					// min
			(int)(maxAsphK * AsphKFactor) + 1					// max
			);

		sb4.setName( "K" );
		sb4.addAdjustmentListener( controls );
		controls.add( sb4 );
		sb4.setBounds( 110, 20, 250, 20 );

		Label lbl4 = new Label( "Asphere K" );
		controls.add( lbl4 );
		lbl4.setBounds( 10, 20, 95, 20 );

		DispK = new Label( D2S( initAsphK ) );
		controls.add( DispK );
		DispK.setBounds( 370, 20, 95, 20 );

		Scrollbar sb5 = new Scrollbar(
			Scrollbar.HORIZONTAL,
			(int)(initAsphC * AsphCFactor),					// initial
			1,					// thumb
			(int)(minAsphC * AsphCFactor),					// min
			(int)(maxAsphC * AsphCFactor) + 1					// max
			);

		sb5.setName( "C" );
		sb5.addAdjustmentListener( controls );
		controls.add( sb5 );
		sb5.setBounds( 110, 50, 250, 20 );

		Label lbl5 = new Label( "Asphere C" );
		controls.add( lbl5 );
		lbl5.setBounds( 10, 50, 95, 20 );

		DispC = new Label( D2S( initAsphC ) );
		controls.add( DispC );
		DispC.setBounds( 370, 50, 95, 20 );

		Scrollbar sb6 = new Scrollbar(
			Scrollbar.HORIZONTAL,
			(int)((initMat1Index - 1.0) * IndexFactor),					// initial
			1,					// thumb
			(int)(- (maxIndex - 1.0) * IndexFactor),					// min
			(int)((maxIndex - 1.0) * IndexFactor) + 1					// max
			);

		//System.out.println( sb6.getMinimum() + " " + sb6.getMaximum() );

		sb6.setName( "Index" );
		sb6.addAdjustmentListener( controls );
		controls.add( sb6 );
		sb6.setBounds( 110, 80, 250, 20 );

		Label lbl6 = new Label( "Index" );
		controls.add( lbl6 );
		lbl6.setBounds( 10, 80, 95, 20 );

		DispIndex = new Label(
			"n1=" + D2S( initMat1Index ) + "  " +
			"n2=" + D2S( initMat2Index ) );
		controls.add( DispIndex );
		DispIndex.setBounds( 370, 80, 120, 20 );

		/*Checkbox c = new Checkbox( "CB1", true );
		c.addItemListener( controls );
		c.setBounds( 370, 50, 100, 30 );
		controls.add( c );*/
	}

	public void Scrollbar( String name, int value )
	{
		if( name.equals( "Beam" ) )
		{
			/*System.out.println(
				initDeviceX + " " + asph.GetX() + " " +
				initDeviceY + " " + value
				);*/
			((ParallelRays)rc).Move(
				new FPoint( initDeviceX, initDeviceY + (double)value, 0.0 ),
				new FPoint( asph.GetX() - initDeviceX, - value, 0.0 )
				);
		}
		else if( name.equals( "ApertureRad" ) )
		{
			apert.SetRadius( value );
		}
		else if( name.equals( "AperturePos" ) )
		{
			apert.MoveOffAxis( value, 0.0 );
		}
		else if( name.equals( "K" ) )
		{
			asph.SetK( value / AsphKFactor );
			DispK.setText( D2S( value / AsphKFactor ) );
		}
		else if( name.equals( "C" ) )
		{
			asph.SetC( value / AsphCFactor );
			DispC.setText( D2S( value / AsphCFactor ) );
		}
		else if( name.equals( "Index" ) )
		{
			double n1, n2;

			if( value < 0 )
			{
				n1 = 1.0;
				n2 = - (value / IndexFactor) + 1;
			}
			else if( value == 0 )
			{
				n1 = 1.0;
				n2 = 1.0;
			}
			else
			{
				n1 = (value / IndexFactor) + 1;
				n2 = 1.0;
			}
			((ConstantParameter)(mat1.Parameters.get( "Constant" ))).SetN( n1 );
			((ConstantParameter)(mat2.Parameters.get( "Constant" ))).SetN( n2 );
			DispIndex.setText(
				"n1=" + D2S( n1 ) + "  " +
				"n2=" + D2S( n2 ) );
		}
		v.Rearrange();
		canvas.ForceRedraw();
	}

	public String D2S( double d )
	{
		return Double.toString( Math.round(d*100.0)/100.0 );
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