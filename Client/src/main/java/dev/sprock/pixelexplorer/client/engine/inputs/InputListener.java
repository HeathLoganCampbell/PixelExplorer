package dev.sprock.pixelexplorer.client.engine.inputs;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener  implements KeyListener, FocusListener {
	public boolean[] keys = new boolean[65536];

	public boolean recordBuffer = false;
	public String keyBuffer = "";

	public void resetKeyBuffer()
	{
		this.keyBuffer = "";
	}

	public void setKeyRecorder(boolean recordKeys)
	{
		this.recordBuffer = recordKeys;
	}
	
	//KeyEvent.VK_LEFT
	public boolean isPressed(int keyCode)
	{
		return this.keys[keyCode];
	}
	
	public void setPressed(int keyCode, boolean pressed)
	{
		this.keys[keyCode] = pressed;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		for (int i=0; i<keys.length; i++) {
			keys[i] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); 
		if (code>0 && code<keys.length) {
			keys[code] = true;
		}

		if(recordBuffer)
		{
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE)
			{
				if(keyBuffer.length() > 0)
				{
					keyBuffer = keyBuffer.substring(0, keyBuffer.length() - 1);
				}
			}
			else
			{
				keyBuffer += e.getKeyChar();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode(); 
		if (code>0 && code<keys.length) {
			keys[code] = false;
		}
	}

}
