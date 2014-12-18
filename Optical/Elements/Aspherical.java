
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
import Optical.Materials.*;
import Utils.*;

import java.awt.*;

public class Aspherical extends OpticalElement
{
	private double	C = 1 / 20.0,
					K = -1.0,
					aperture = 80.0;
					
	// if false, rays not falling on the surface will be propagated
	// w/o being deflected.
	private boolean	solidAperture = true; 
		

	private Material mat;
					
	static final double defaultWidth = 20.0;

    public Aspherical()
    {
		super();
    }

	public Aspherical( double C, double K, Material mat )
	{
		this();
		this.C = C;
		this.K = K;
		this.mat = mat;
		SetWidth( defaultWidth );
	}

	public Aspherical( double C, double K, double width, double aperture, Material mat )
	{
		this( C, K, mat );
		this.aperture = aperture;
		SetWidth( width );
	}

	public void SetSolidAperture( boolean solidAperture )
	{
		this.solidAperture = solidAperture;
	}

	public boolean GetSolidAperture()
	{
		return solidAperture;
	}

	public void SetAperture( double a )
	{
		aperture = a;
	}

	public double GetAperture( double a )
	{
		return aperture;
	}

	public void SetC( double C )
	{
		this.C = C;
	}

	public void SetK( double K )
	{
		this.K = K;
	}

	public double GetC()
	{
		return C;
	}

	public double GetK()
	{
		return K;
	}

	private double DepthAtAperture()
	{
		double a;
	
		a = asph( aperture );
		if( Double.isNaN( a ) || Double.isInfinite( a ) )
		{
			if( C != 0 ) a = - 1 / C;
			else a = 0;
		}
		return a;
	}
	
	public void PropagateRayPointSelf( RayPoint r )
	{
		boolean n_horizontal = false;
	
		double distance;

		double	xr, yr, zr,
				kx, ky, kz, k_norm,
				a, b, c,
				u1, u2, d;
			
		// Intersection
		xr = r.GetPosition().x;
		yr = r.GetPosition().y;
		zr = r.GetPosition().z;
			
		kx = r.GetKVector().x;
		ky = r.GetKVector().y;
		kz = r.GetKVector().z;

		k_norm = kx*kx + ky*ky + kz*kz;
		
		a = - C * ( (K+1)*kx*kx + ky*ky + kz*kz );
		b = - 2 * C * ( (K+1)*kx*xr + ky*yr + kz*zr ) - 2*kx;
		c = - C*( (K+1)*xr*xr + yr*yr + zr*zr ) - 2*xr;

		double xi, yi, zi;

		if( a == 0 ) // Horizontal ray with parabolic surface
		{
			u1 = - c / b;
		}
		else
		{
			d = b*b - 4*a*c;
			if( d < 0 ) // No intersection
			{
				distance = - xr;
				if( distance <= 0 ) // Ray already inside medium, kill it
				{
					r.Invalidate();
				}
				else
				{
					r.GoStraight( distance + DepthAtAperture() );
					if( solidAperture ) r.Invalidate();
				}

				// Update position variables
				xr = r.GetPosition().x;
				yr = r.GetPosition().y;
				zr = r.GetPosition().z;

				// Don't calculate n vector, but use a horizontal one
				n_horizontal = true;
				u1 = 0;
			}
			else // An intersection
			{
				if( d == 0 )
				{
					u1 = -b/(2*a);
				}
				else
				{
					u1 = (-b - Math.sqrt( d ))/(2*a);
					u2 = (-b + Math.sqrt( d ))/(2*a);

					if( C < 0 && K < -1 || C > 0 && K >= -1 ) // )( -> ( or () -> )
					{
						u1 = Math.max( u1, u2 );
					}
					else // )( -> ) or () -> (
					{
						u1 = Math.min( u1, u2 );
					}
				}
			}
		}

		// Move raypoint to intersection
		xi = xr + kx*u1;
		yi = yr + ky*u1;
		zi = zr + kz*u1;
					
		// Refraction
		double	nx, ny, nz,
				n_norm, k_dot_n,
				k_after, k_after_2;

		if( yi*yi + zi*zi > aperture*aperture ) // Ray falls outside the aperture
		{
			// Move it to surface height
			r.GoStraight( -xr + DepthAtAperture() );
			// Kill it if aperture is solid (default)
			if( solidAperture ) r.Invalidate();
			// Refraction on a planar interface
			n_horizontal = true;
		}
		
		if( n_horizontal ) // Use an horizontal n vector
		{
			nx = -1;
			ny = 0;
			nz = 0;
		}
		else // Calculate n vector
		{
			r.SetPosition( new FPoint( xi, yi, zi ) );
			if( xi < xr ) // Ray already inside medium, kill it
			{
				r.Invalidate();
			}
				
			if( K == -1 )
			{
				nx = -1.0;
				ny = - C * yi;
				nz = - C * zi;
			}
			else
			{
				nx = -1.0;
				ny = - (C * yi) / Math.sqrt( 1.0 - (K+1)*C*C*(yi*yi+zi*zi) );
				nz = - (C * zi) / Math.sqrt( 1.0 - (K+1)*C*C*(yi*yi+zi*zi) );
			}
		}

		if( r.IsValid() )
		{
			n_norm = nx*nx + ny*ny + nz*nz;
			k_dot_n = nx*kx + ny*ky + nz*kz;
				
			k_after = ( 2 * Math.PI * mat.IndexAtWavelength( r.GetWavelength() ) ) / r.GetWavelength();
			k_after_2 = k_after * k_after;

			a = n_norm;

			b = 2 * k_dot_n;
			c = k_norm - k_after_2;
									
			d = b*b - 4*a*c;
					
			if( d < 0 )
			{
				r.Invalidate();
			}
			else if( d == 0 )
			{
				u1 = -b/(2*a);
			}
			else
			{
				u1 = (-b - Math.sqrt( d ))/(2*a);
				u2 = (-b + Math.sqrt( d ))/(2*a);
			}

			FPoint kvect = new FPoint( kx + u1*nx, ky + u1*ny, 0 );	
			r.SetKVector( kvect );
		}
	}
	
	public double asph( double y )
	{
		return -( C * y * y ) / ( 1 + Math.sqrt( 1 - C * C * ( K + 1 ) * y * y ) );
	}

	public void DrawSelf( Graphics g )
	{
		Color savecolor = g.getColor();
		g.setColor( Color.red );
		int a = (int)Math.round( aperture );
		double x, oldx, y;

		y = - a;
		oldx = asph( y );

		for( y = - a + 1; y <= a; y++ )
		{
			x = asph( y );
			int xp, oldxp, yp;
			xp = (int)Math.round( x );
			oldxp = (int)Math.round( oldx );
			yp = (int)Math.round( y );
			if( !(Double.isNaN( x ) || Double.isNaN( oldx ) || Double.isNaN( y )) )
				g.drawLine( oldxp, yp - 1, xp, yp );
			oldx = x;
		}

		g.setColor( savecolor );
	}
}