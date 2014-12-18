
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

public class Nothing extends OpticalElement
{
    public Nothing()
    {
		super();
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
		}
	}
	
	public void DrawSelf( Graphics g )
	{
	}
}