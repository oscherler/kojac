
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Elements;

import Optical.Rays.*;

import java.util.Vector;
import java.util.Enumeration;
import java.awt.*;

/**
 * The <code>OpticalDevice</code> class is a child of <code>OpticalElement</code> and
 * enables the user to build a complete optical system of <code>OpticalElement</code> objects.
 * since <code>OpticalDevice</code> inherits from <code>OpticalElement</code>, devices can be
 * nested to an arbitrary depth, making it easy to split the system into functional blocks.
 * <p>
 * The standard use of this class is to build a system using <code>OpticalElement</code>
 * subclasses into an <code>OpticalDevice</code> object and to use an
 * <code>OpticalCanvas</code> object to display it.
 * 
 * @author	Olivier Scherler
 * @see		OpticalDevice
 * @see		OpticalCanvas
 */

public class OpticalDevice extends OpticalElement
{
	private Vector elements;

    public OpticalDevice()
    {
		super();
		elements = new Vector( 5, 5 );
    }

	public Object clone()
	{
		OpticalDevice o = null;

		o = (OpticalDevice)super.clone();
		o.elements = (Vector)elements.clone();
		return o;
	}

	/**
	 * Draws the device by calling the <code>Draw</code> method for all of its
	 * child elements.
	 *
	 * @param	g	the <code>Graphics</code> object into which the device must be drawn.
	 * @see		OpticalElement#Draw
	 */
	public void Draw( Graphics g )
	{
		super.Draw( g );
		Enumeration	e = elements.elements();
		
		while( e.hasMoreElements() )
		{
			OpticalElement o = (OpticalElement)e.nextElement();
			o.Draw( g );
		}
	}

	/**
	 * Draws the device's symbol into a <code>Graphics</code> object. Does nothing
	 * since the device is drawn by drawing each of its child elements. Override this
	 * method to add extra drawings to the device.
	 *
	 * @param	g	the <code>Graphics</code> object into which the device must be drawn.
	 */
	public void DrawSelf( Graphics g )
	{
	}

	/**
	 * Draws the part of the <code>Ray</code> going through the device. The <code>DrawRay</code>
	 * method is called for every element in the device.
	 * 
	 * @param	g		the <code>Graphics</code> object in which the <code>Ray</code> must
	 * 					be drawn.
	 * @param	r		the <code>Ray</code> to draw.
	 * @param	index	the index of the <code>RayPoint</code> to draw. Used to keep track of
	 * 					the position in the <code>Ray</code> in case of multiple
	 *					<code>OpticalDevice</code> nesting.
	 * @return	the index of the next <code>RayPoint</code> to be drawn by the next element.
	 * @see		Ray
	 * @see		RayPoint
	 */
	public int DrawRay( Graphics g, Ray r, int index )
	{
		Enumeration	e = elements.elements();
		int newindex = index;

		while( e.hasMoreElements() )
		{
			OpticalElement o = (OpticalElement)e.nextElement();
			newindex = o.DrawRay( g, r, newindex );
		}
		return newindex;
	}

	/**
	 * Propagates a <code>Ray</code> through each of its child elements, by calling
	 * their <code>Propagate</code> method.
	 *
	 * @param	r	the <code>Ray</code> to propagate.
	 * @see		OpticalElement#Propagate
	 */
	public void Propagate( Ray r )
	{
		Enumeration	e = elements.elements();

		while( e.hasMoreElements() )
		{
			OpticalElement o = (OpticalElement)e.nextElement();
			o.Propagate( r );
		}
	}

	/**
	 * Does nothing since all the work is done by the Device's and the child
	 * elements Propagate methods.
	 *
	 * @param	r	the RayPoint to propagate.
	 * @see		RayPoint
	 * @see		#Propagate
	 * @see		OpticalElement#Propagate
	 */
	public void PropagateRayPointSelf( RayPoint r )
	{
	}

	/**
	 * Adds an element to the end of the child elements list, and calls its
	 * <code>PutAfter</code> method to align it on the previous element.
	 *
	 * @param	e	the element to add.
	 * @see		OpticalElement
	 * @see		OpticalElement#PutAfter
	 */
	public void Append( OpticalElement e )
	{
		if( elements.isEmpty() ) // We are adding the first element of the group
		{
			e.PutAfter( this );
		}
		else e.PutAfter( (OpticalElement)elements.lastElement() );
		e.Rearrange();
		elements.addElement( e );
	}

	/**
	 * Realigns each child element on the previous one and sets the width of
	 * the device to the cumulated width of the child elements.
	 *
	 * @see		OpticalElement
	 * @see		OpticalElement#PutAfter
	 */
	public void Rearrange()
	{
		int s = elements.size();
		OpticalElement oi, oi_1;

		SetWidth( 0 );
	
		if( s > 0 )
		{
			oi = (OpticalElement)elements.elementAt( 0 ); // First element
			oi.PutAfter( this );
			oi.Rearrange();

			SetWidth( GetWidth() + oi.GetWidth() );

			for( int i = 1; i < s; i++ )
			{
				oi = (OpticalElement)elements.elementAt( i );
				oi_1 = (OpticalElement)elements.elementAt( i - 1 );
				oi.PutAfter( oi_1 );
				oi.Rearrange();
				SetWidth( GetWidth() + oi.GetWidth() );
			}
		}
	}
}