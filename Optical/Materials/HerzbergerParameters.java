
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

public class HerzbergerParameters extends Parameter
{
    static final int nParameters = 6;
    private double  a = 1,
                    b = 0,
                    c = 0,
                    d = 0,
                    e = 0,
                    f = 0;

    public String type()
    {
        return "Herzberger";
    }

    public HerzbergerParameters()
    {
        super();
    }

    public HerzbergerParameters( double a, double b, double c, double d, double e, double f )
    {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public HerzbergerParameters( HerzbergerParameters p )
    {
        super();
        this.a = p.a;
        this.b = p.b;
        this.c = p.c;
        this.d = p.d;
        this.e = p.e;
        this.f = p.f;
    }

    public HerzbergerParameters( double[] n ) throws WrongArraySizeException
    {
        super();
        SetArray( n );
    }

    public double IndexAtWavelength( double w )
    {
        double w2 = w * w;
        double L = 1 / ( w2 - 0.028 );
        return  a + b*L + c*Math.pow( L, 2 ) + d*w2 + e*Math.pow( w2, 2 ) + f*Math.pow( w2, 3 );
    }

    public double[] AsArray()
    {
        double[] arr = { a, b, c, d, e, f };
        return arr;
    }

    public void SetArray( double[] n ) throws WrongArraySizeException
    {
        if( n.length != nParameters ) throw new WrongArraySizeException();
        this.a = n[0];
        this.b = n[1];
        this.c = n[2];
        this.d = n[3];
        this.e = n[4];
        this.f = n[5];
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

    public double E()
    {
        return e;
    }

    public double F()
    {
        return f;
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

    public void SetE( double n )
    {
        this.e = n;
    }

    public void SetF( double n )
    {
        this.f = n;
    }
}
