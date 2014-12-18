
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

import java.awt.*;
import java.util.Hashtable;

public class DeviceSwitcher extends OpticalElement
{
	private Hashtable	devices = new Hashtable();
	private String		currentDevice = "";
	private boolean		DefaultExists = false;

    public DeviceSwitcher()
    {
		super();
    }

	public DeviceSwitcher( OpticalElement d )
	{
		this( "Default", d );
	}

	public DeviceSwitcher( String l, OpticalElement d )
	{
		if( d != null )
		{
			devices.put( l.toLowerCase(), d );
			currentDevice = l.toLowerCase();
			DefaultExists = true;
		}
	}

	public void SetCurrentDevice( String l )
	{
		currentDevice = l.toLowerCase();
	}

	public String GetCurrentDevice()
	{
		return currentDevice;
	}

	public void AddDevice( String l, OpticalElement d )
	{
		devices.put( l.toLowerCase(), d );
		if( !DefaultExists )
		{
			currentDevice = l.toLowerCase();
			DefaultExists = true;
		}
	}

	public int DrawRay( Graphics g, Ray r, int index )
	{
		int newindex = index;
		OpticalElement	e;

		e = (OpticalElement)devices.get( currentDevice );
		if( e != null ) newindex = e.DrawRay( g, r, index );

		return newindex;
	}

	public void Draw( Graphics g )
	{
		super.Draw( g );
		OpticalElement	e;

		e = (OpticalElement)devices.get( currentDevice );
		if( e != null )
			e.Draw( g );
	}

	public void DrawSelf( Graphics g )
	{
	}

	public void Propagate( Ray r )
	{
		OpticalElement	e;

		e = (OpticalElement)devices.get( currentDevice );
		if( e != null )
			e.Propagate( r );
	}

	public void PropagateRayPointSelf( RayPoint r )
	{
	}

	public void Rearrange()
	{
		SetWidth( 0 );

		OpticalElement	e;

		e = (OpticalElement)devices.get( currentDevice );
		if( e != null )
		{
			e.PutAfter( this );
			e.Rearrange();

			SetWidth( e.GetWidth() );
		}
	}
}