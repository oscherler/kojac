
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

public class Spherical extends Aspherical
{
    public Spherical()
    {
		super();
    }

	public Spherical( double C, Material mat )
	{
		super( C, 0.0, mat );
	}

	public Spherical( double C, double width, double aperture, Material mat )
	{
		super( C, 0.0, width, aperture, mat );
	}
}