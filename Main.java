/******************************************************************************************\
 * Title 		- 	A Java program **(Using Swing)** that works as an advanced 
 *					scientific calculator. Use a grid layout to arrange buttons for
 *					the digits and create all the operations available currently for it.
 *					Add a text field to display the result.
 * 
 * @author Kirandeep Singh
\******************************************************************************************/

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Driver class.
 * 
 * @author Kirandeep Singh
 *
 */
public class Main {

	/**
	 * Driver function
	 * 
	 * @param 	args 			Command line arguments passed
	 * @throws 	IOException 	If the input data type is invalid.
	 */
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.setVisible(true);
		calc.setSize(500, 300);
		calc.setResizable(false);
		calc.setTitle("Calculator - Kirandeep Singh");
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/**
 * Calculator GUI to perform advanced arithmetics calculations.
 */
public class Calculator extends JFrame implements ActionListener{

	/** Dimention of keypad */
	private static final int rows = 6, cols = 6;

	/** Key-Map of keypad */
	private static final String[][] keyLayout = 
	{	{"sin"	, "cos"		, "tan"		, "÷"	,"%"	,"1/x"},
		{"asin"	, "acos"	, "atan"	, "+"	,"-"	,"×"  },
		{"log"	, "xʸ"		, "x²"		, "1"	,"2"	,"3"  },
		{"√x"	, "eˣ"		, "x³"		, "4"	,"5"	,"6"  },
		{"ˣ√y"	, "π"		, "10ˣ"		, "7"	,"8"	,"9"  },
		{"!"	, "mod"		, "ln"		, "."	,"0"	,"="  }
	};

	/** Display of the calculator */
	private JTextField display;

	/** Panel to hold keys */
	private JPanel keyPad;

	/** Stores Operand */
	private String operand;
	/** Stores Operator */
	private String operator;

	/** Flag to store the state of calculator. True if the display is holding the result */
	private boolean isStoringResult;

	/** Constructor to setup calculator
	 * 
	 * @constructs Calculator
	 *  */
	Calculator(){
		///////// Setup Layout ////////////
		setLayout(new BorderLayout());
		Font MONOSPACE_BUTTON_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 15);

		//////// Initialize Components //////////
		JButton clearAll = new JButton("C");
		JButton clear    = new JButton("CE");
		JButton del      = new JButton("⇦");
		JPanel sidePanel = new JPanel(new GridLayout(3, 1));
		clearAll.setFont(MONOSPACE_BUTTON_FONT);
		clear.setFont(MONOSPACE_BUTTON_FONT);
		del.setFont(MONOSPACE_BUTTON_FONT);

		display = new JTextField("0");
		display.setEnabled(false);
		keyPad = new JPanel(new GridLayout(rows, cols));

		display.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));

		////////// Setup keypad ///////////
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				JButton button = new JButton(keyLayout[row][col]);
				button.addActionListener(this);
				button.setFont(MONOSPACE_BUTTON_FONT);

				keyPad.add(button);
			}
		}

		//////// Setup side pad buttons ////////
		clearAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText("");
				operand = "";
				operator = "";
			}
		});

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText("");
			}
		});

		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					display.setText(display.getText().substring(0, display.getText().length() - 1));
				}catch(Exception exception){
					System.out.println(exception);
				}
			}
		});

		//////////Setup side panel /////////
		sidePanel.add(del);
		sidePanel.add(clear);
		sidePanel.add(clearAll);

		///////// Setup display /////////
		add(display, BorderLayout.NORTH);
		add(keyPad, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.EAST);

	}

	/** Function to listen to keys on kaypad.
	 * 
	 * <p>
	 * Deals with the comutational functions and implements them.
	 * </p>
	 * 
	 * @param 	ActionEvent 	Caller Event to be processed.
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {

		try{
		// Switches according to the text of the button pressed.
			switch(e.getActionCommand()){

			//////////// Functions With two parameters /////////////
			case "+":
				operator = "+";
				operand = display.getText();
				display.setText("");
				isStoringResult = false;
				break;

			case "-":
				if(display.getText().equals("")){
					display.setText("-");
				}else{
					operator = "-";
					operand = display.getText();
					display.setText("");
					isStoringResult = false;
				}
				break;

			case "×":
				operator = "×";
				operand = display.getText();
				display.setText("");
				isStoringResult = false;
				break;

			case "÷":
				operator = "÷";
				operand = display.getText();
				display.setText("");
				isStoringResult = false;
				break;

			case "%":
				operator = "%";
				operand = display.getText();
				display.setText("");
				isStoringResult = false;
				break;

			case "xʸ":
				operator = "^";
				operand = display.getText();
				display.setText("");
				isStoringResult = false;
				break;

			case "ˣ√y":
				operator = "√";
				operand = display.getText();
				display.setText("");
				isStoringResult = false;
				break;

			/////////////////// No-paramter functions /////////////////
			case "=":
				operand = calculate(operand, display.getText(), operator);
				display.setText(operand);
				isStoringResult = true;
				break;

			///////////// Functions with one parameters //////////////
			case "sin":
				operand = Double.toString(Math.sin(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "cos":
				operand = Double.toString(Math.cos(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "tan":
				operand = Double.toString(Math.tan(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "asin":
				operand = Double.toString(Math.asin(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "acos":
				operand = Double.toString(Math.acos(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "atan":
				operand = Double.toString(Math.atan(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "log":
				operand = Double.toString(Math.log10(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "x²":
				operand = Double.toString(Math.pow(Double.parseDouble(display.getText()), 2));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "x³":
				operand = Double.toString(Math.pow(Double.parseDouble(display.getText()), 3));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "√x":
				operand = Double.toString(Math.sqrt(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "eˣ":
				operand = Double.toString(Math.pow(Math.E, Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "10ˣ":
				operand = Double.toString(Math.pow(10, Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "!":
				operand = Double.toString(factorial(Integer.parseInt(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "mod":
				operand = Double.toString(Math.abs(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;

			case "ln":
				operand = Double.toString(Math.log(Double.parseDouble(display.getText())));
				display.setText(operand);
				isStoringResult = true;
				break;


			case "1/x":
				display.setText(Double.toString(1.0 / Double.parseDouble(display.getText())));
				isStoringResult = true;
				break;

			///////////// Printable Values //////////////
			case "π":
				display.setText(Double.toString(Math.E));
				isStoringResult = false;
				break;

			/////////// Numbers //////////
			default:
				if(isStoringResult){
					display.setText(e.getActionCommand());
				}else{
					display.setText(display.getText() + e.getActionCommand());
				}
				isStoringResult = false;
			}
		}catch(Exception exception){
			System.out.println(exception);
		}
	}

	/**Function to calculate the result after the application of given operator on two operands.
	 * 
	 * @param 	operand1 	First operand
	 * @param 	operand2 	Second operand
	 * @param 	operator 	Operator
	 * 
	 * @return 				Result after applying operation on given operands.
	 * */
	private static String calculate(String operand1, String operand2, String operator){
		switch (operator) {
		case "+": return Double.toString(Double.parseDouble(operand1) + Double.parseDouble(operand2));
		case "-": return Double.toString(Double.parseDouble(operand1) - Double.parseDouble(operand2));
		case "×": return Double.toString(Double.parseDouble(operand1) * Double.parseDouble(operand2));
		case "÷": return Double.toString(Double.parseDouble(operand1) / Double.parseDouble(operand2));
		case "%": return Double.toString(Double.parseDouble(operand1) % Double.parseDouble(operand2));

		case "^": return Double.toString(Math.pow(Double.parseDouble(operand1), Double.parseDouble(operand2)));
		case "√": return Double.toString(Math.pow(Double.parseDouble(operand1), 1.0 / Double.parseDouble(operand2)));
		}

		return operand2;
	}

	/**Function to find the factorial of a number using recursive approach.
	 * 
	 * @param 	x 	Input to find the factorial of.
	 * 
	 * @return 		Factoral of the input number.
	 * 
	 * @throws 	IllegalArgumentException If a negative integer value is entered as input
	 * */
	public int factorial(int x) throws IllegalArgumentException {
		if (x < 0) {
			throw new IllegalArgumentException("A positive integer value expected in factorial : " + x);
		}
		if (x <= 2) return x;
		else return x * factorial(x - 1);
	}
}
