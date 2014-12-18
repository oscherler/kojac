
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Materials;

import Utils.*;

public class HoO2Parameters extends Parameter
{
    static final int nParameters = 4;
    private double  a = 1,
                    b = 0,
                    c = 0,
                    d = 0;

    public String type()
    {
        return "HoO2";
    }

    public HoO2Parameters()
    {
        super();
    }

    public HoO2Parameters( double a, double b, double c, double d )
    {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public HoO2Parameters( HoO2Parameters p )
    {
        super();
        this.a = p.a;
        this.b = p.b;
        this.c = p.c;
        this.d = p.d;
    }

    public HoO2Parameters( double[] n ) throws WrongArraySizeException
    {
        super();
        SetArray( n );
    }

    public double IndexAtWavelength( double w )
    {
        double w2 = w * w;
        return Math.sqrt( a + ( b * w2 )/( w2 - c ) - d*w2 ); 
    }

    public double[] AsArray()
    {
        double[] arr = { a, b, c, d };
        return arr;
    }

    public void SetArray( double[] n ) throws WrongArraySizeException
    {
        if( n.length != nParameters ) throw new WrongArraySizeException();
        this.a = n[0];
        this.b = n[1];
        this.c = n[2];
        this.d = n[3];
    }

    public double A()
    {
        return a;
    }

    public double B()
    {
        return b;
    }

    public double C()
    {
        return c;
    }

    public double D()
    {
        return d;
    }

    public void SetA( double n )
    {
        this.a = n;
    }

    public void SetB( double n )
    {
        this.b = n;
    }

    public void SetC( double n )
    {
        this.c = n;
    }

    public void SetD( double n )
    {
        this.d = n;
    }
}
