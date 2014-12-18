
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Rays;

import java.util.*;
import java.awt.*;

public class Ray
{
	private Vector raypoints;
	//private Color color;

    public Ray()
    {
    	super();
		raypoints = new Vector( 5, 5 );
		//color = Color.blue;
    }
    
    public void Vide()
    {
    	//RayPoint r = (RayPoint)(raypoints.firstElement());
    	
    	raypoints.setSize( 1 );
    	//raypoints.addElement( r );
    }

	public void SetWavelength( double w )
	{
		Enumeration	e = raypoints.elements();
		
		while( e.hasMoreElements() )
		{
			RayPoint o = (RayPoint)e.nextElement();
			o.SetWavelength( w );
		}
	}

	/*public Color GetColor()
	{
		return new Color( color.getRed(), color.getGreen(), color.getBlue() );
	}

	public void SetColor( Color color )
	{
		this.color = new Color( color.getRed(), color.getGreen(), color.getBlue() );
	}*/

	public Ray( RayPoint r )
	{
		super();
		raypoints = new Vector( 5, 5 );
		raypoints.addElement( r );
		//color = Color.blue;
	}

	public Vector raypoints()
	{
		return raypoints;
	}

	public void Append( RayPoint r )
	{
		raypoints.addElement( r );
	}	

	public void Append( Ray r )
	{
		int i, s = r.raypoints().size();
		if( s != 0 )
			for( i = 0; i < s; i++ )
				raypoints.addElement( r.raypoints().elementAt( i ) );
	}

	public RayPoint Last()
	{
		return (RayPoint)(raypoints.lastElement());
	}
	
	public boolean Empty()
	{
		return raypoints.isEmpty();
	}
}