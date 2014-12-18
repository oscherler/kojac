
/**
 * Title:        SlimApp2<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Gui;

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

public class OpticalControl extends Panel
	implements ItemListener, AdjustmentListener, ActionListener
{
	private RespondToEvents resp;

    public void adjustmentValueChanged(AdjustmentEvent e)
	{
		if( e.getAdjustable() instanceof Scrollbar )
			resp.Scrollbar( ((Scrollbar)e.getAdjustable()).getName(), e.getValue() );
	}

	public void itemStateChanged( ItemEvent e )
	{
		if( e.getItemSelectable() instanceof Checkbox )
			resp.Checkbox( ((Checkbox)e.getItemSelectable()).getName(), (e.getStateChange() == ItemEvent.SELECTED) );
	}

	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() instanceof Button )
			resp.Button( ((Button)e.getSource()).getLabel() );		
	}

	public OpticalControl( RespondToEvents a )
    {
		resp = a;
	}
}