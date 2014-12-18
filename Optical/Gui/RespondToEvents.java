
/**
 * Title:        NewProj<p>
 * Description:  <p>
 * Copyright:    Copyright (c) imt<p>
 * Company:      imt<p>
 * @author Olivier Scherler
 * @version 1.0
 */
package Optical.Gui;

import java.awt.AWTEvent;

public interface RespondToEvents
{

	public void Scrollbar( String name, int value );
	public void Checkbox( String name, boolean value );
	public void Button( String name );
}