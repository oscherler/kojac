package Optical.Rays;

import java.awt.Color;

public class Wavelength
{
	public static Color wvColor( double w, double gamma )
	{
		double wavelength = w * 1000.0;

		Color color;

		if( (wavelength >= 300.0) && (wavelength <= 800.0) )
		{
			double red, green, blue;

//      600  540  450  300  
//  1    [1; 0]  0  [0; 1]

			if( wavelength > 600.0 )
				red = 1.0;
			else if( wavelength > 540.0 )
				red = ( wavelength - 540.0 ) / 61.0;
			else if( (wavelength > 300.0) && (wavelength <= 450.0) )
				red = ( 450.0 - wavelength ) / 150.0;
			else
				red = 0.0;

//      650  580  550  400  
//  0    [0; 1]  1  [1; 0]  0

			if( wavelength < 400.0 )
				green = 0.0;
			else if( wavelength <= 550.0 )
				green = ( wavelength - 400.0 ) / 151.0;
			else if( wavelength < 580.0 )
				green = 1.0;
			else if( wavelength < 650.0 )
				green = (650.0 - wavelength) / 71.0;
			else
				green = 0.0;

//      550    480    300  
//    0   [0; 1]   1    

		if( (wavelength >= 300.0) && (wavelength < 480.0) )
			blue = 1.0;
		else if( wavelength < 550.0 )
			blue = ( 550.0 - wavelength ) / 71.0;
		else
			blue = 0.0;

		color = new Color(
			(float)(red * gamma),
			(float)(green * gamma),
			(float)(blue * gamma) );
	    }
		else
		{
			color = Color.black;
	    }
		return color;
	}
}

