
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Elements;

import Optical.Elements.*;
import Optical.Rays.*;
import Utils.*;

import java.awt.*;

public class SimpleLens extends OpticalElement
{
	private static final double TresholdAperture = 20,
								DefaultRadius = 30;

	private double	f = 20, 
					aperture = 50;

    public SimpleLens()
    {
		super();
    }

	public SimpleLens( double f )
	{
		this();
		this.f = f;
	}

	public SimpleLens( double f, double aperture )
	{
		this();
		this.f = f;
		this.aperture = aperture;
	}

	public void SetFocal( double f )
	{
		this.f = f;
	}
	
	public double GetFocal()
	{
		return f;
	}

	public void SetAperture( double aperture )
	{
		this.aperture = aperture;
	}
	
	public double GetAperture()
	{
		return aperture;
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
				// Here, the RayPoint is at the beginning of the element.
				FPoint kvect = r.GetKVector();

				double	y = r.GetPosition().y,
						z = r.GetPosition().z,
						kx = kvect.x,
						ky = kvect.y,
						kz = kvect.z,
						yp = ( ky * f ) / kx;

				if( y * y + z * z <= aperture * aperture )
				{
					kx = Math.abs( f );
					if( f >= 0 ) ky = yp - y;
					else ky = - ( yp - y );
					kz = 0;
	
					FPoint newk = new FPoint( kx, ky, kz );
					newk.Normalize( r.GetKVector().Norm() );
					r.SetKVector( newk );
				}
				else
				{
					r.Invalidate();
				}
				

			}
	}
	
	public void DrawSelf( Graphics g )
	{
		int r,
			f2 = (int)Math.round( f ),
			sf = ( f >= 0 ) ? 1 : -1;

		boolean AboveTreshold;
		double radius;

		if( aperture > TresholdAperture )
		{
			radius = aperture;
			AboveTreshold = true;
		}
		else
		{
			radius = DefaultRadius;
			AboveTreshold = false;
		}

		r = (int)Math.round( radius );
		
		g.drawLine( 0, -r, 0, r );
		
		g.drawLine( - 1, 0, 1, 0 );
		
		g.drawLine( - 4, -r + sf*4, 0, -r );
		g.drawLine( 4, -r + sf*4, 0, -r );
		
		g.drawLine( - 4, r - sf*4, 0, r );
		g.drawLine( 4, r - sf*4, 0, r );

		g.fillRect( - f2 - 1, - 3, 2, 7 );
		g.fillRect( f2 - 1, - 3, 2, 7 );

		if( !AboveTreshold )
		{
			r = (int)Math.round( aperture );
			g.drawLine( - 3, -r, 3, -r );
			g.drawLine( - 3, r, 3, r );
		}

	}
}