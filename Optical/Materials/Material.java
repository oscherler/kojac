package Optical.Materials;

import Utils.*;

import java.math.*;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class Material
{
	private String name = "vaccum";
	private boolean valid = false;
	static final String parseSeparator = "\t";

	public Hashtable Parameters = new Hashtable();
	private String DefaultFormula = "Constant";

	private static final double lambdad = 0.5876, lambdaF = 0.4861, lambdaC = 0.6563;

	public Material()
	{
		super();
	}

	/*public Material( Material m )
	{
		super();
		name = new String( m.name );
		DefaultFormula = m.DefaultFormula;
		valid = m.valid;

		Parameters = (Hashtable)m.Parameters.clone();
	}*/
	
	public Material( String name, Parameter p )
	{
		super();
		this.name = new String( name );
		try
		{
			AddParameterSet( p );
			DefaultFormula = p.type();
			valid = true;
		}
		catch( Exception e )
		{
			e.printStackTrace();
			valid = false;
		}
	}

	public void AddParameterSet( Parameter p, boolean def ) throws InvalidMethodTypeEx
	{
		if( p != null )
		{
			Parameters.put( p.type(), p );
			DefaultFormula = p.type();
		}
	}
	
	public void AddParameterSet( Parameter p ) throws InvalidMethodTypeEx
	{
		AddParameterSet( p, false );
	}

	public void AddParameterSet( String s, boolean def )
		throws InvalidMethodTypeEx, NumberFormatException, WrongArraySizeException
	{
		Parameter p = null;
		StringTokenizer st = new StringTokenizer( s );
		int size = st.countTokens(), i = 0;

		if( size > 0 )
		{
			p = NewParameterSet( st.nextToken() );
			if( size > 1 )
			{
				double d[] = new double[ size - 1 ];
	
				while( st.hasMoreTokens() )
				{
					d[ i ] = (Double.valueOf( st.nextToken() )).doubleValue();
					i++;
				}
				p.SetArray( d );
			}
			AddParameterSet( p, def );
		}
	}
	
	public void AddParameterSet( String s ) throws InvalidMethodTypeEx, NumberFormatException, WrongArraySizeException
	{
		AddParameterSet( s, false );
	}

	private Parameter NewParameterSet( String s ) throws InvalidMethodTypeEx
	{
		if( s.equalsIgnoreCase( "Constant" ) )		return new ConstantParameter();
		if( s.equalsIgnoreCase( "Schott" ) )		return new SchottParameters();
		if( s.equalsIgnoreCase( "Herzberger" ) )	return new HerzbergerParameters();
		if( s.equalsIgnoreCase( "Conrady" ) )		return new ConradyParameters();
		if( s.equalsIgnoreCase( "Sellmeier1" ) )	return new Sellmeier1Parameters();
		if( s.equalsIgnoreCase( "Sellmeier2" ) )	return new Sellmeier2Parameters();
		if( s.equalsIgnoreCase( "Sellmeier3" ) )	return new Sellmeier3Parameters();
		if( s.equalsIgnoreCase( "Sellmeier4" ) )	return new Sellmeier4Parameters();
		if( s.equalsIgnoreCase( "HoO1" ) )			return new HoO1Parameters();
		if( s.equalsIgnoreCase( "HoO2" ) )			return new HoO2Parameters();
		else throw new InvalidMethodTypeEx( s );
	}

	public String GetName()
	{
		return name;
	}

	public void SetName( String s )
	{
		name = new String( s );
	}
	
	public void SetDefaultFormula( String f )
	{
		if( Parameters.containsKey( f ) )
		{
			DefaultFormula = f;
			valid = true;
		}
	}

	public double IndexAtWavelength( double wavelength )
	{
		return IndexAtWavelength( wavelength, DefaultFormula );
	}

	public double IndexAtWavelength( double wavelength, String fd )
	{
		if( Parameters.containsKey( fd ) )
			return ((Parameter)Parameters.get( fd )).IndexAtWavelength( wavelength );
		else return 0.0;
	}

	public double AbbeNumber()
	{
		return AbbeNumber( DefaultFormula );
	}

	public double AbbeNumber( String fd )
	{
		if( Parameters.containsKey( fd ) )
		{
			double nd, nF, nC;

			nd = ((Parameter)Parameters.get( fd )).IndexAtWavelength( lambdad );
			nF = ((Parameter)Parameters.get( fd )).IndexAtWavelength( lambdaF );
			nC = ((Parameter)Parameters.get( fd )).IndexAtWavelength( lambdaC );

			return ( nd - 1 ) / (nF - nC);
		}
		else return 0.0;
	}

	public double AbbeIndex()
	{
		return AbbeIndex( DefaultFormula );
	}

	public double AbbeIndex( String fd )
	{
		if( Parameters.containsKey( fd ) )
		{
			return ((Parameter)Parameters.get( fd )).IndexAtWavelength( lambdad );
		}
		else return 0.0;
	}
}
