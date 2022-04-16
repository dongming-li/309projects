package test_1;
import java.awt.*;
import java.awt.event.*;

public class component_1 extends Frame implements ActionListener{
	private Label lblInput;									//Input Text name
	private Label[] lblOutput = new Label[5];				//Output Text names
	private TextField tfInput;								//User input text
	private TextField[] tfOutput = new TextField[5];		//User output texts
	private String[] msg = new String[5];					//Storage for user output texts
	
	public component_1(){
		setLayout(new FlowLayout());						//Sets how components are set out
		
		/*Initialize the components for output and set msg to nothing*/
		for(int i = 4; i >= 0;i--){
			lblOutput[i] = new Label("Text" + i + ": ");
			add(lblOutput[i]);
			
			tfOutput[i] = new TextField(50);
			tfOutput[i].setEditable(false);
			add(tfOutput[i]);
			
			msg[i] = "";
		}
		
		/*Initialize the components for input*/
		lblInput = new Label("Text : ");
		add(lblInput);
		
		tfInput = new TextField(50);
		add(tfInput);
		
		/*Allow programmer to modify what is triggered in the "Enter" phase of the input text*/
		tfInput.addActionListener(this);
		
		/*Set the size, title of the Frame and that it shows up on your screen by "Visible"*/
		setTitle("TextBox");
		setSize(450,300);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new component_1();
	}
	
	@Override
	public void actionPerformed(ActionEvent evt){
		/*Moving the messages(msg) upward*/
		for(int i = 4;i > 0;i--){
			msg[i] = msg[i - 1];
		}
		msg[0] = tfInput.getText();
		tfInput.setText("");
		
		/*Set the new output text to the Frame*/
		for(int i = 0;i < 5;i++){
			tfOutput[i].setText(msg[i]);
		}
		
		
	}
}
