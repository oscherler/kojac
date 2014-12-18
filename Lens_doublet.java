
import java.awt.*;

import Optical.Elements.*;
import Optical.Rays.*;
import Optical.Materials.*;
import Optical.Gui.*;

import Utils.*;

public class Lens_doublet extends OpticalApp
{
    OpticalCanvas       canvas;
    OpticalControl      controls;
    // details of controls
    Scrollbar       position_scrollbar;
    Button          reset_button;

    OpticalDevice       dev;
    // details of the Device
    RayCaster       object_point;
    SimpleLens      lens1, lens2;
    Homogeneous     h1, h2, h3;
    Screen          screen;

    Scrollbar       sb11, sb12;

    double  lens1Position = 100.0, lens1FocalLength = 100.0,
            lensDistance = 100.0, lens2FocalLength = 100.0,
            lens1Aperture = 80.0, lens2Aperture = 80.0,
            screenDistance = 200.0, screensize = 100.0,
            effectiveLens1Position, effectiveLens2Position,
            totalDistance = lens1Position + lensDistance + screenDistance;

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
        Material    vacuum;

        vacuum = new Material( "Vacuum", new ConstantParameter( 1.0 ) );

        dev = new OpticalDevice();
        dev.MoveAxis( 150, 0 );
        //dev.MoveOnAxis( 20 );

        // definition of the elements
        h1 = new Homogeneous( lens1Position, vacuum );
        lens1 = new SimpleLens( lens1FocalLength, lens1Aperture );
        effectiveLens1Position = lens1Position;
        h2 = new Homogeneous( lensDistance, vacuum );
        lens2 =new SimpleLens( lens2FocalLength, lens2Aperture );
        effectiveLens2Position = lens1Position + lensDistance;
        h3 = new Homogeneous( screenDistance, vacuum );
        screen = new Screen( screensize, screensize );

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

        sb11 = new Scrollbar(
            Scrollbar.HORIZONTAL,
            (int)lens1Position,  // initial
            1,  // thumb
            0,  // min
            (int)(lens1Position + lensDistance) // max
            );

        sb11.setName( "lens1Pos" );
        sb11.addAdjustmentListener( controls );
        controls.add( sb11 );
        sb11.setBounds( 0, 0, 200, 20 );

        sb12 = new Scrollbar(
            Scrollbar.HORIZONTAL,
            (int)lensDistance,  // initial
            1,  // thumb
            0,  // min
            (int)(lensDistance + screenDistance) // max
            );

        sb12.setName( "lens2Pos" );
        sb12.addAdjustmentListener( controls );
        controls.add( sb12 );
        sb12.setBounds( 0, 40, 200, 20 );

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
            h1.SetWidth( value );
            h2.SetWidth( totalDistance - (int)h1.GetWidth() - (int)h3.GetWidth() );
            sb12.setMaximum( (int)h2.GetWidth() + (int)h3.GetWidth() );
            sb12.setValue( (int)h2.GetWidth() );
            dev.Rearrange();
        }
        else if( name.equals( "lens2Pos" ) )
            {
                h2.SetWidth( value );
                h3.SetWidth( totalDistance - (int)h1.GetWidth() - (int)h2.GetWidth() );
                sb11.setMaximum( (int)h1.GetWidth() + (int)h2.GetWidth() );
                sb11.setValue( (int)h1.GetWidth() );
                dev.Rearrange();
            }
        canvas.ForceRedraw();
    }

/*  public void Checkbox( String name, boolean value )
    {
        if( value )
        {
//          ds.SetDevice( "With" );
        }
        else
        {
//          ds.SetDevice( "Without" );
        }
        dev.Rearrange();
        canvas.ForceRedraw();
    }*/

/*  public void Button( String name )
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
