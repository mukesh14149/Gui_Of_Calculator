package Gui_Project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;  
  
public class Calculator extends JFrame implements ActionListener{  
	static JFrame frame=new JFrame("Calculator");
	static JPanel[] panel=new JPanel[6];
	static JButton[] button=new JButton[17];
	static JTextArea text = new JTextArea(2,20);
	static int c=0;
	static String[] value={"7","8","9","+","4","5","6","-","1","2","3","*","0",".","/","=","C"};
	public static void main(String[] args) {
	
		frame.setResizable(false);
		frame.setSize(240, 270);
		frame.setLayout(new GridLayout(6,1));
		panel[0]=new JPanel();
		
		frame.add(panel[0]);
		for(int i=1;i<6;i++){
		panel[i]=new JPanel();
		panel[i].setLayout(new GridLayout(1,1));
		frame.add(panel[i]);
		}
		panel[0].add(text);
		
		text.setEditable(false);
		button[16]=new JButton(value[16]);
		panel[5].add(button[16]);
		button[16].addActionListener(new Calculator());
		for(int i=0;i<4;i++){
			button[i]=new JButton(value[i]);
			panel[1].add(button[i]);
			button[i].addActionListener(new Calculator());
			
		}
		for(int i=4;i<8;i++){
			button[i]=new JButton(value[i]);
			panel[2].add(button[i]);
			button[i].addActionListener(new Calculator());
			
		}
		for(int i=8;i<12;i++){
			button[i]=new JButton(value[i]);
			panel[3].add(button[i]);
			button[i].addActionListener(new Calculator());
			
		}
		for(int i=12;i<16;i++){
			button[i]=new JButton(value[i]);
			panel[4].add(button[i]);
			button[i].addActionListener(new Calculator());
			
		}
		frame.setVisible(true);	
	}
	public static Double evaluate(String expression)
    {
        char[] character = expression.toCharArray();
        if(character[1] == '-'){
        	expression=" 0 "+expression;
        	character = expression.toCharArray();
        }
        if(character[1] == '+'){
        	expression=" 0 "+expression;
        	character = expression.toCharArray();
        }
        	
        Stack<Double> values = new Stack<Double>();
        Stack<Character> operation = new Stack<Character>();
        for (int i = 0; i < character.length; i++)
        {
            if (character[i] == ' ')
                continue;
            if ((character[i] >= '0' && character[i] <= '9'))
            {
                StringBuffer sbstring = new StringBuffer();
                while ((i < character.length && character[i] >= '0' && character[i] <= '9'))
                    sbstring.append(character[i++]);
                while((i < character.length && character[i] == '.')){
                	sbstring.append(character[i++]);
                }
                while ((i < character.length && character[i] >= '0' && character[i] <= '9'))
                    sbstring.append(character[i++]);
                values.push(Double.parseDouble(sbstring.toString()));
            }
            else if (character[i] == '+' || character[i] == '-' ||character[i] == '*' || character[i] == '/')
            {
                while (!operation.empty() && hasPrecedence(character[i], operation.peek()))
                  values.push((double) operationwork(operation.pop(), values.pop(), values.pop()));
                operation.push(character[i]);
            }
        }
        while (!operation.empty())
            values.push((double) operationwork(operation.pop(), values.pop(), values.pop()));
        return values.pop();
    }
    public static boolean hasPrecedence(char op1, char op2)
    {

        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    public static double operationwork(char op, double b, double a)
    {
        switch (op)
        {
        case '+':
            return (double) (a + b);
        case '-':
            return (double) (a - b);
        case '*':
            return (double) (a * b);
        case '/':
            if (b == 0)
                throw new
                UnsupportedOperationException("Cannot divide by zero");
            return (double) (a / b);
        }
        return 0;
    }
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button[0])
		{
			text.append("7");
			c=0;
		}
		if(e.getSource() == button[1]){
            text.append("8");c=0;}
		if(e.getSource() == button[2]){
            text.append("9");c=0;}
		if(e.getSource() == button[3]){
			if(c==0){
			text.append(" + ");
			c=1;
			}
		}  
		if(e.getSource() == button[4]){
            text.append("4");c=0;}
		if(e.getSource() == button[5]){
            text.append("5");c=0;}
		if(e.getSource() == button[6]){
            text.append("6");c=0;}
		if(e.getSource() == button[7]){
			if(c==0){
				text.append(" - ");
				c=1;
				}
            
		}
		if(e.getSource() == button[8]){
            text.append("1");c=0;}
		if(e.getSource() == button[9]){
            text.append("2");c=0;}
		if(e.getSource() == button[10]){
            text.append("3");c=0;}
		if(e.getSource() == button[11]){
			if(c==0){
				text.append(" * ");
				c=1;
				}}
		if(e.getSource() == button[12])
            text.append("0");
		if(e.getSource() == button[13]){
			if(c==0){
				text.append(" . ");
				c=1;
				}
		}	
		if(e.getSource() == button[14]){
			if(c==0){
				text.append(" / ");
				c=1;
				}}
		if(e.getSource() == button[16])
            text.setText("");
		if(e.getSource() == button[15])
            text.append(" = "+Double.toString(Calculator.evaluate(text.getText())));
	}
}  