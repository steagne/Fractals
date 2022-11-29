package Main;

import java.awt.event.*;

import javax.swing.*;

import FractalsTypes.*;

public class Controller implements ActionListener
{
	private Model model;
	private View view;
	
	public Controller(View view, Model model)
	{
		this.view = view;
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JComponent component;
		
		component = (JComponent)e.getSource();
		if (component instanceof JButton)
		{
			if (component.getName().equals("buttonUpdate"))
			{
				if (view.checkForValidInput())
				{
					view.updateModel();
					view.updateView();
				}
			}
			else if (component.getName().equals("buttonRestart"))
			{
				model.resetValues();
				view.updateModel();
				view.updateView();
			}
		}
		else if (component instanceof JCheckBox)
		{
			if (component.getName().equals("checkboxGrid"))
			{
				view.updateModel();
				view.updateView();
			}
		}
		else if (component instanceof JMenuItem)
		{
			model.resetValues();
			model.requestFractal(FractalType.getValues[Integer.parseInt(component.getName())]);
			view.updateModel();
			view.updateView();
		}
		else if (component instanceof Canvas)
		{
			if (e.getActionCommand().equals("1"))
				model.zoomIn(((Canvas)e.getSource()).getRectangleZoom());
			else
				model.zoomOut(((Canvas)e.getSource()).getMousePosition());
			view.updateModel();
			view.updateView();
		}
	}
}