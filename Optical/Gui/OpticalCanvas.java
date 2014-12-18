
package Optical.Gui;

import Optical.Elements.*;
import Optical.Rays.*;
import Utils.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Vector;
import java.util.Enumeration;

public class OpticalCanvas extends Canvas
{
	Image offScreenBuffer;
	OpticalDevice	v = null;
	RayCaster raycaster = null;

	private boolean shouldRedraw = true;

	public OpticalCanvas()
	{

	}

	public void SetDevice( OpticalDevice e )
	{
		v = e;
	}

	public void SetRayCaster( RayCaster r )
	{
		raycaster = r;
	}

	public void ForceRedraw()
	{
		shouldRedraw = true;
		repaint();
	}
		
	public void paint( Graphics g )
	{
		g.clearRect( 0,0,getSize().width, getSize().height );
		v.Draw( g );
		if( raycaster != null ) raycaster.DrawRays( g, v );
	}

	public void update(Graphics g)
    {
		Graphics gr; 
		// Will hold the graphics context from the offScreenBuffer.
		// We need to make sure we keep our offscreen buffer the same size
		// as the graphics context we're working with.
		if (offScreenBuffer==null ||
			(! (offScreenBuffer.getWidth(this) == this.getSize().width
			&& offScreenBuffer.getHeight(this) == this.getSize().height)))
		{
			offScreenBuffer = this.createImage(getSize().width, getSize().height);
			shouldRedraw = true;
		}

		// We need to use our buffer Image as a Graphics object:
		gr = offScreenBuffer.getGraphics();

		if( shouldRedraw )
		{
			paint(gr);
			shouldRedraw = false;
		}
		// Passes our off-screen buffer to our paint method, which,
		// unsuspecting, paints on it just as it would on the Graphics
		// passed by the browser or applet viewer.
		g.drawImage(offScreenBuffer, 0, 0, this);
		// And now we transfer the info in the buffer onto the
		// graphics context we got from the browser in one smooth motion.
    }
}