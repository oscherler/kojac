
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

public class Screen extends OpticalElement
{
	private double depth = 0.0, height = 100.0;

    public Screen()
    {
		super();
    }

	public Screen( double height, double depth )
	{
		super();
		this.depth = depth;
		this.height = height;
	}

	public void SetDepth( double depth )
	{
		this.depth = depth;
	}

	public double GetDepth()
	{
		return depth;
	}

	public void SetHeight( double height )
	{
		this.height = height;
	}

	public double GetHeight()
	{
		return height;
	}

	public void PropagateRayPointSelf( RayPoint r )
	{
		double distance;
		distance = - r.GetPosition().x;

		if( distance > 0 ) r.GoStraight( distance );
	}
	
	public void DrawSelf( Graphics g )
	{
		int r = (int)Math.round( height / 2 );
		g.drawLine( 0, -r, 0, r );
	}
}