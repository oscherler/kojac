
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
import Utils.*;

import java.awt.*;

public class AntiAperture extends OpticalElement
{
	private double	radius = 25;

    public AntiAperture()
    {
		super();
    }

	public AntiAperture( double radius )
	{
		this();
		this.radius = radius;
	}

	public void SetRadius( double a )
	{
		radius = a;
	}

	public double GetRadius()
	{
		return radius;
	}

	public void PropagateRayPointSelf( RayPoint r )
	{
		double distance;
		distance = - r.GetPosition().x;
		if( distance < 0 )
			r.Invalidate();
		else
		{
			if( distance > 0 ) r.GoStraight( distance );

			double	y = r.GetPosition().y,
					z = r.GetPosition().z;

			if( y * y + z * z < radius * radius )
				r.Invalidate();
		}
	}
	
	public void DrawSelf( Graphics g )
	{
		/*int x = (int)Math.round( X() ),
			y = (int)Math.round( Axis().y + OffAxis().y ),
			top = y - (int)Math.round( radius ),
			bottom = y + (int)Math.round( radius );

		g.drawLine( x, top, x, top + 10 );

		g.drawLine( x, bottom, x, bottom - 10 );
		
		g.drawLine( x - 5, top, x + 5, top );
		g.drawLine( x - 5, bottom, x + 5, bottom );*/
		
		int	r = (int)Math.round( radius );

		g.drawLine( 0, -r, 0, -r + 10 );

		g.drawLine( 0, r, 0, r - 10 );
		
		g.drawLine( - 5, -r, 5, -r );
		g.drawLine( - 5, r, 5, r );
	}
}