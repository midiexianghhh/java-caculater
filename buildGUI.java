package miniproject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.PlainDocument; 
import java.lang.*;
import java.awt.event.KeyAdapter;  
import java.awt.event.KeyEvent;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent; 
import java.util.*;
import javax.swing.event.DocumentListener;  
import javax.swing.event.DocumentEvent;  
import javax.swing.JTextField;  
import javax.swing.text.Document;
import java.awt.event.*;


public class buildGUI
{
	
	public int result=0,getresult=0,alltimes=0,righttimes=0,zhuanhua=0,lastresult=0;
	String input="";
	int[] answer=new int[100];
	static int i=0;
	public String[] name= {"1","2","3","4","5","6","7","8","9","-","0","<[x]"};
	
	public buildGUI()
	{
		this.run();
	}
	
	public void run()
	{
		JFrame fra = new JFrame("Math Teacher");
		fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fra.setSize(600, 400);
		fra.setLocation(650, 200);
		
		JPanel pad = new JPanel();
		/**
		 * 	this is created to accommodate the label and button
		 */
		JPanel pad2= new JPanel();
		/**
		 * 	this is created to accommodate the digit keyboard
		 */
		pad2.setSize(400,300);
		pad2.setLayout(new GridLayout(4,3));
		JLabel result1=new JLabel(Integer.toString(righttimes)+" correct out of "+Integer.toString(alltimes));//print the result which you have done
		
		
		
		Button but = new Button("Press for answer");
		/**
		 * 	set button which is pressed to judge the result and show the next question
		 */
		but.setBounds(100, 100, 100, 50);
		
		
		JLabel but2=new JLabel("Let's begin!");
		
		TextField text = new TextField(30);
		/**
		 * 	text
		 */
		text.setBounds(200, 50, 200, 30);	
		
		
		/**
		 * 	limit the form of input when the keyboard is released
		 */        
		text.addKeyListener(new KeyAdapter() { 
            public void keyReleased(KeyEvent e) {  
            	
                    input = text.getText();
                    zhuanhua=Integer.valueOf(input);
       				System.out.println(zhuanhua);
       				
                if (zhuanhua>10) {  
                    e.consume();   
                }
                
                
                
            }  
        });  
		
		
		/**
		 * 	limit the form of input when the keyboard is pressed
		 */
		text.addKeyListener(new KeyAdapter() { 
            public void keyPressed(KeyEvent e) {  
                char ch = e.getKeyChar(); 
                String temp=text.getText();
                int zhuanhua2=Integer.valueOf(temp);
            	
            	if(!(ch>='0'&&ch<='9'))
            	{
            		e.consume();
            	}
                  
                if (zhuanhua>10) {  
                    e.consume();   
                }
                if(zhuanhua==10&&ch!='0')
                {
                	e.consume();
                }
                if(zhuanhua2<=-1)
                {
                	e.consume();
                }
                
                
            }  
        });  
		
		
		
		
		
		JLabel hint = new JLabel(this.display());
		/**
		 * 	set label which to display the quetions	
		 */
		
		
		but.addActionListener(new ActionListener() {
			/**
			 * 	The reaction when the button is pushed
			 */
			public void actionPerformed(ActionEvent e)
			{
				alltimes++;
				input = text.getText();
				zhuanhua=Integer.parseInt(input);
				if(zhuanhua==result)
				{
					righttimes++;
				}
				result1.setText(Integer.toString(righttimes)+" correct out of "+Integer.toString(alltimes));
				hint.setText(display());
				text.setText("");
				
				if(alltimes==0)
				{
					but2.setText("The answer of last question is "+lastresult);
				}
				else
				{
					int temp=alltimes;
					but2.setText("The answer of last question is "+answer[temp-1]);
				}
				
				
				
			}
		});
		


		ArrayList<JButton> digit=new ArrayList<JButton>();
		i=0;
		for(i=0;i<12;i++)//take these number to the button
		{
			digit.add(new JButton(name[i]));
			pad2.add(digit.get(i));
			digit.get(i).addActionListener(new ActionListener() {
				/**
				 * 	The reaction when the button is pushed
				 */
				public void actionPerformed(ActionEvent e)
				{
				


					
					JButton JBnumber=(JButton)e.getSource();
					
					String temp=text.getText();
					
					if(digit.indexOf(JBnumber)==9)
					{
						text.setText("-");
					}
					else if(digit.indexOf(JBnumber)==11)
					{
						text.setText("");
					}
					else if(digit.indexOf(JBnumber)==10)
					{
						text.setText(text.getText()+"0");
					}
					else
					{text.setText(text.getText()+ (digit.indexOf(JBnumber)+1));}
					
					input=text.getText();
	                   zhuanhua=Integer.valueOf(input);	
	                   if((zhuanhua<-9||zhuanhua>100)&&!(digit.indexOf(JBnumber)==11)&&!(digit.indexOf(JBnumber)==9))
	                   {
	                	   text.setText(temp);
	                   }
					
				}
			});
		}
		
		
		
		/**
		 * 	combine all the gui elements
		 */
		pad.add(hint,BorderLayout.NORTH);
		pad.add(text,BorderLayout.NORTH);
		pad.add(result1);		
		pad.add(but,BorderLayout.NORTH);
		pad.add(but2);
		
		pad.add(pad2,BorderLayout.SOUTH);
		fra.add(pad);
		

		
		fra.setVisible(true);		
		/**
		 * 	display
		 */
		
	}///////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 	display the questions for users
	 */
	public String display()
	{
		int later=0,front=0;
		/**
		 * 	the number which are calculate
		 */
		int choice =(int)(Math.random()*4)+1;		

			front=(int)(Math.random()*10)+1;
			later=(int)(Math.random()*10)+1;
			if(choice==4)
			{
				front=front*later;
			}
			
		

		char choicechar='w';
		switch(choice)
		{
		case 1:
			choicechar='+';
			result=front+later;
			break;
		case 2:
			choicechar='-';
			result=front-later;
			break;
		case 3:
			choicechar='x';
			result=front*later;
			break;
		case 4:
			choicechar='/';
			result=front/later;
			break;
		}
		
		lastresult=result;
		int temp=alltimes;
		answer[temp]=result;
		String show=new String(Integer.toString(front)+choicechar+Integer.toString(later));
		return show;
		
		
	}
	
	

	/**
	 * to display how many quetions you are right and how many questions you have done	
	 */
	public String judge2()
	{
		if(getresult==result)
		{
			alltimes++;
			righttimes++;
		}
		else
		{
			alltimes++;
		}
		return(Integer.toString(righttimes)+" correct out of "+Integer.toString(alltimes));
	}
	
	
	
}
