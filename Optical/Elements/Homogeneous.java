
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

public class Homogeneous extends OpticalElement
{
	private Material mat;

    public Homogeneous()
    {
		super();
    }

	public Homogeneous( double w, Material mat )
	{
		super( w );
		this.mat = mat;
	}

	public Material GetMaterial()
	{
		return mat;
	}

	public void SetMaterial( Material mat )
	{
		this.mat = mat;
	}

	public void PropagateRayPointSelf( RayPoint r )
	{
		double distance;
		distance = - r.GetPosition().x;
			
		double kb, ka, wb;

		kb = r.GetKVector().Norm();
		wb = r.GetWavelength();
		ka = ( 2 * Math.PI * mat.IndexAtWavelength( wb ) ) / wb;

		// The index is the same before and after, so no matter whether the RayPoint
		// is located after the begining of the element. A threshold is used because
		// of rounding errors.
		//if( Math.abs( ka - kb ) > 1e-8 ) 
		{

			if( distance < 0 )
			//if( distance < -1e-8 )  // Si erreur d'arrondi
				r.Invalidate();
			else
			{
				if( distance > 0 ) r.GoStraight( distance );
				// Here, the RayPoint is at the beginning of the element.
				FPoint kvect = r.GetKVector();
			
				double kx, kx2, ky, kz, l, w;

				ky = kvect.y;
				kz = kvect.z;
				w = r.GetWavelength();
				l = ( 2 * Math.PI * mat.IndexAtWavelength( w ) ) / w;
				kx2 = l * l - ky * ky - kz * kz;
				if( kx2 >= 0 )
				{
					kx = Math.sqrt( kx2 );
					FPoint newk = new FPoint( kx, ky, kz );
					r.SetKVector( newk );
				}
				else
				{
					r.Invalidate();
				}
			}
		}
	}
	
	public void DrawSelf( Graphics g )
	{
		//g.drawLine( 0, -50, 0, 50 );
	}
}