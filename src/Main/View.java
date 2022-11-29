package Main;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import FractalsTypes.*;

@SuppressWarnings("serial")
public class View extends JFrame
{
	//******************************************* CONSTANTS *******************************************
	public static final int FRACTAL_W = 640;//Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int FRACTAL_H = 480;//Toolkit.getDefaultToolkit().getScreenSize().height - 140;
	
	//******************************************** VARIOUS ********************************************
	private Model model;
	private Controller controller;
	private BufferedImage imageFractal;

	//********************************************** VIEW *********************************************
	//Menu
	private JMenuBar menubarMain;
	private JMenu menuFractalTypes;
	private JMenuItem menuItemKochSnowflake;
	private JMenu menusubMandelbrot;
	private JMenuItem menuItemMandelbrotStandard;
	private JMenuItem menuItemMandelbrotLeaf;
	private JMenuItem menuItemMandelbrotCactus;
	private JMenuItem menuItemMandelbrotMoonfish;
	private JMenuItem menuItemMandelbrotMill;
	private JMenuItem menuItemMandelbrotArrows;
	private JMenuItem menuItemMandelbrotBall;
	private JMenuItem menuItemMandelbrotStar;
	private JMenuItem menuItemMandelbrotLeaf2;
	private JMenuItem menuItemMandelbrotDaisy;
	private JMenuItem menuItemMandelbrotFlake;
	private JMenuItem menuItemMandelbrotFlake2;
	private JMenuItem menuItemMandelbrotLeaf3;
	private JMenuItem menuItemMandelbrotLeaf4;
	private JMenuItem menuItemMandelbrotLeaf5;
	private JMenuItem menuItemMandelbrotLeaf6;
	private JMenuItem menuItemMandelbrotLeaf7;
	//OPTIONS
	private JLabel labelIterations;
	private JTextField textIterations;
	private JLabel labelPower;
	private JTextField textPower;
	private JCheckBox checkboxGrid;
	private JButton buttonUpdate;
	private JButton buttonRestart;
	
	//Canvas
	private Canvas canvas;
	
	//***************************************** CONSTRUCTORS ******************************************
	public View()
	{
		model = new Model(FRACTAL_W, FRACTAL_H);
		controller = new Controller(this, model);
		setTitle("Fractal Viewer");
		setSize(FRACTAL_W + 6, FRACTAL_H + 102);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//VIEW
		canvas = new Canvas();
		canvas.setLayout(null);
		canvas.setBounds(0, 73, FRACTAL_W, FRACTAL_H);
		//MENU
		menubarMain = new JMenuBar();
		menubarMain.setBounds(0, 0, getWidth(), 25);
		menuFractalTypes = new JMenu("Fractal Types");
		menusubMandelbrot = new JMenu("Mandelbrot");
		menuItemMandelbrotStandard = new JMenuItem("Standard");
		menuItemMandelbrotStandard.setName("0");
		menuItemMandelbrotLeaf = new JMenuItem("Leaf");
		menuItemMandelbrotLeaf.setName("1");
		menuItemMandelbrotCactus = new JMenuItem("Cactus");
		menuItemMandelbrotCactus.setName("2");
		menuItemMandelbrotMoonfish = new JMenuItem("Moonfish");
		menuItemMandelbrotMoonfish.setName("3");
		menuItemMandelbrotMill = new JMenuItem("Mill");
		menuItemMandelbrotMill.setName("4");
		menuItemMandelbrotArrows = new JMenuItem("Arrows");
		menuItemMandelbrotArrows.setName("5");
		menuItemMandelbrotBall = new JMenuItem("Ball");
		menuItemMandelbrotBall.setName("6");
		menuItemMandelbrotStar = new JMenuItem("Star");
		menuItemMandelbrotStar.setName("7");
		menuItemMandelbrotLeaf2 = new JMenuItem("Leaf2");
		menuItemMandelbrotLeaf2.setName("8");
		menuItemMandelbrotDaisy = new JMenuItem("Daisy");
		menuItemMandelbrotDaisy.setName("9");
		menuItemMandelbrotFlake = new JMenuItem("Flake");
		menuItemMandelbrotFlake.setName("10");
		menuItemMandelbrotFlake2 = new JMenuItem("Flake2");
		menuItemMandelbrotFlake2.setName("11");
		menuItemMandelbrotLeaf3 = new JMenuItem("Leaf3");
		menuItemMandelbrotLeaf3.setName("12");
		menuItemMandelbrotLeaf4 = new JMenuItem("Leaf4");
		menuItemMandelbrotLeaf4.setName("13");
		menuItemMandelbrotLeaf5 = new JMenuItem("Leaf5");
		menuItemMandelbrotLeaf5.setName("14");
		menuItemMandelbrotLeaf6 = new JMenuItem("Leaf6");
		menuItemMandelbrotLeaf6.setName("15");
		menuItemMandelbrotLeaf7 = new JMenuItem("Leaf7");
		menuItemMandelbrotLeaf7.setName("16");
		menuItemKochSnowflake = new JMenuItem("Koch Snowflake");
		menuItemKochSnowflake.setName("17");
		//OPTIONS
		labelIterations = new JLabel("Iterations");
		labelIterations.setBounds(20, 36, 60, 24);
		textIterations = new JTextField("" + Mandelbrot.MAX_ITERATIONS_DEFAULT);
		textIterations.setBounds(100, 36, 60, 24);
		labelPower = new JLabel("Power");
		labelPower.setBounds(180, 36, 40, 24);
		textPower = new JTextField("" + Fractal.POWER_DEFAULT);
		textPower.setBounds(240, 36, 60, 24);
		checkboxGrid = new JCheckBox("Grid");
		checkboxGrid.setBounds(330, 36, 60, 24);
		checkboxGrid.setName("checkboxGrid");
		buttonUpdate = new JButton("Update");
		buttonUpdate.setBounds(430, 36, 90, 24);
		buttonUpdate.setName("buttonUpdate");
		buttonRestart = new JButton("Restart");
		buttonRestart.setBounds(530, 36, 90, 24);
		buttonRestart.setName("buttonRestart");
		
		//LISTENERS
		menuItemMandelbrotStandard.addActionListener(controller);
		menuItemMandelbrotLeaf.addActionListener(controller);
		menuItemMandelbrotCactus.addActionListener(controller);
		menuItemMandelbrotMoonfish.addActionListener(controller);
		menuItemMandelbrotMill.addActionListener(controller);
		menuItemMandelbrotArrows.addActionListener(controller);
		menuItemMandelbrotBall.addActionListener(controller);
		menuItemMandelbrotStar.addActionListener(controller);
		menuItemMandelbrotLeaf2.addActionListener(controller);
		menuItemMandelbrotDaisy.addActionListener(controller);
		menuItemMandelbrotFlake.addActionListener(controller);
		menuItemMandelbrotFlake2.addActionListener(controller);
		menuItemMandelbrotLeaf3.addActionListener(controller);
		menuItemMandelbrotLeaf4.addActionListener(controller);
		menuItemMandelbrotLeaf5.addActionListener(controller);
		menuItemMandelbrotLeaf6.addActionListener(controller);
		menuItemMandelbrotLeaf7.addActionListener(controller);
		menuItemKochSnowflake.addActionListener(controller);
		checkboxGrid.addActionListener(controller);
		buttonUpdate.addActionListener(controller);
		buttonRestart.addActionListener(controller);
		canvas.addActionListener(controller);
		
		//ADDS
		add(menubarMain);
		menubarMain.add(menuFractalTypes);
		menuFractalTypes.add(menusubMandelbrot);
		menusubMandelbrot.add(menuItemMandelbrotStandard);
		menusubMandelbrot.add(menuItemMandelbrotLeaf);
		menusubMandelbrot.add(menuItemMandelbrotCactus);
		menusubMandelbrot.add(menuItemMandelbrotMoonfish);
		menusubMandelbrot.add(menuItemMandelbrotMill);
		menusubMandelbrot.add(menuItemMandelbrotArrows);
		menusubMandelbrot.add(menuItemMandelbrotBall);
		menusubMandelbrot.add(menuItemMandelbrotStar);
		menusubMandelbrot.add(menuItemMandelbrotLeaf2);
		menusubMandelbrot.add(menuItemMandelbrotDaisy);
		menusubMandelbrot.add(menuItemMandelbrotFlake);
		menusubMandelbrot.add(menuItemMandelbrotFlake2);
		menusubMandelbrot.add(menuItemMandelbrotLeaf3);
		menusubMandelbrot.add(menuItemMandelbrotLeaf4);
		menusubMandelbrot.add(menuItemMandelbrotLeaf5);
		menusubMandelbrot.add(menuItemMandelbrotLeaf6);
		menusubMandelbrot.add(menuItemMandelbrotLeaf7);
		menuFractalTypes.add(menuItemKochSnowflake);
		add(labelIterations);
		add(textIterations);
		add(labelPower);
		add(textPower);
		add(checkboxGrid);
		add(buttonUpdate);
		add(buttonRestart);
		add(canvas);
		add(new JLabel());
		updateModel();
		updateView();
	}

	//******************************************** METHODS ********************************************
	public void start() { setVisible(true); }
	public void updateView()
	{
		labelPower.setVisible(model.isPowerEnabled());
		textPower.setVisible(model.isPowerEnabled());
		imageFractal = model.getFractalImage();
		canvas.setPicture(imageFractal);
	}
	public void updateModel()
	{
		model.setIterations(Integer.parseInt(textIterations.getText()));
		if (model.isPowerEnabled())
			model.setPower(Integer.parseInt(textPower.getText()));
		model.generateFractal();
		model.setGridVisible(checkboxGrid.isSelected());
	}
	public boolean checkForValidInput()
	{
		boolean result;

		result = textIterations.getText().matches("\\d+");
		result = result && textPower.getText().matches("\\d+") || !model.isPowerEnabled();
		if (!result)
			JOptionPane.showMessageDialog(this, "Invalid power input!");
		
		return result;
	}
}