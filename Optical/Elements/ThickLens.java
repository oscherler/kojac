
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

import java.awt.*;

public class ThickLens extends OpticalDevice
{
	Spherical first, second;
	Material mat1, mat2;
	double aperture;

    public ThickLens( double C1, double C2, double thickness, double width,
		double aperture, Material mat1, Material mat2 )
    {
		super();

		double a, R1, R2, Rp;

		if( C1 == 0 )
		{
			if( C2 == 0 ) a = 10000;
			else
			{
				R2 = Math.abs( 1 / C2 );
				Rp = R2 - thickness;
				a = Math.sqrt( R2*R2 - Rp*Rp );
			}
		}
		else
		{
			if( C2 == 0 )
			{
				R1 = Math.abs( 1 / C1 );
				Rp = R1 - thickness;
				a = Math.sqrt( R1*R1 - Rp*Rp );
			}
			else
			{
				R1 = Math.abs( 1 / C1 );
				R2 = Math.abs( 1 / C2 );
		
				double x2, xi;
				x2 = -( R1 + R2 - thickness );
				
				xi = (R1*R1 - R2*R2 + x2*x2) / ( 2*x2);
				a = Math.sqrt( R1*R1 - xi*xi );
			}
		}
		
		a = Math.min( aperture, a );
		this.aperture = a;

		/*R1 = Math.abs( 1/C1 );
		R2 = Math.abs( 1/C2 );

		alpha = - ( 2*R2 + 1 ) / ( 2*thickness*( R1-R2-1 ) );
		a = Math.sqrt( 2*alpha*thickness*R1 - (alpha*alpha*thickness*thickness ) );
		System.out.println( a );
		a = Math.min( aperture, a );*/

		this.mat1 = mat1;
		this.mat2 = mat2;
		first = new Spherical( C1, thickness, a, mat1 );
		//first.SetWidth( thickness );
		Append( first );
		second = new Spherical( C2, width, a, mat2 );
		//second.SetWidth( nextElement );
		Append( second );
    }

	public void SetC1( double c )
	{
		first.SetC( c );
	}

	public void SetC2( double c )
	{
		second.SetC( c );
	}

	public double GetC1()
	{
		return first.GetC();
	}

	public double GetC2()
	{
		return second.GetC();
	}

	public void SetMaterial1( Material mat )
	{
		mat1 = mat;
	}

	public void SetMaterial2( Material mat )
	{
		mat2 = mat;
	}

	public Material GetMaterial1()
	{
		return mat1;
	}

	public Material GetMaterial2()
	{
		return mat2;
	}

	public void DrawSelf( Graphics g )
	{
		super.DrawSelf( g );

		Color savecolor = g.getColor();
		g.setColor( Color.red );
		
		int x1, x2, yt, yb;
		double pos1, pos2;

		yt = (int)Math.round( - aperture );
		yb = (int)Math.round( aperture );

		pos1 = 0;
		pos2 = first.GetWidth();

		x1 = (int)Math.round( first.asph( aperture ) );
		x2 = (int)Math.round( pos2 + second.asph( aperture ) );

		g.drawLine( x1, yt, x2, yt );
		g.drawLine( x1, yb, x2, yb );
		g.setColor( savecolor );
	}
}