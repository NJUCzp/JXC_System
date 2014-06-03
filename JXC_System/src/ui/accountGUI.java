package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import control.JXC_Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class accountGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	public JXC_Controller con=new JXC_Controller(view);
	private Point[] points=new Point[]{new Point(0,110),new Point(0,170),new Point(0,230),new Point(0,290),new Point(0,350),new Point(810,20),new Point(910,20)};
	public ImageIcon[] OPTION_BUTTONS=new ImageIcon[]{new ImageIcon("graphics/account/account_in.png"),new ImageIcon ("graphics/account/account_out.png"),new ImageIcon("graphics/account/account_all.png"),new ImageIcon("graphics/account/account_ini.png"),new ImageIcon("graphics/back.png"),new ImageIcon("graphics/ring.png"),new ImageIcon("graphics/ring.png")};
	public ImageIcon[] OPTION_BUTTONS_ENTERED=new ImageIcon[]{new ImageIcon("graphics/account/account_in2.png"),new ImageIcon ("graphics/account/account_out2.png"),new ImageIcon("graphics/account/account_all2.png"),new ImageIcon("graphics/account/account_ini2.png"),new ImageIcon("graphics/back.png"),new ImageIcon("graphics/minimize.png"),new ImageIcon("graphics/close.png")};
	//JButton[] buttons=new JButton[]{new JButton("创建收款单"),new JButton("创建付款单"),new JButton("查看总账目"),new JButton("账目初始化"),new JButton("后退")};
	//Point[] points=new Point[]{new Point(20,100),new Point(20,175),new Point(20,325),new Point(20,400),new Point(20,475)};
	Image bg=new ImageIcon("graphics/option_background.png").getImage();
	JButton[] buttons=new JButton[7];
	JPanel opPanel=new JPanel();
	JLabel messageLabel=new JLabel();
	JLabel customerLb=new JLabel(new ImageIcon("graphics/customer/customer_name.png"));
    JLabel receiveLb=new JLabel(new ImageIcon("graphics/account/account_cashrec.png"));
    JLabel payLb=new JLabel(new ImageIcon("graphics/account/account_cashpay.png"));
    JLabel timeLb=new JLabel(new ImageIcon("graphics/time_tf.png"));
    JLabel initialLb=new JLabel(new ImageIcon("graphics/account/account_inipr.png"));
    
    JTextField cusTf=new JTextField(25);
    JTextField recTf=new JTextField(25);
    JTextField payTf=new JTextField(25);
    JTextField yearTf=new JTextField(5);
    JTextField monthTf=new JTextField(3);
    JTextField dayTf=new JTextField(3);
    JTextField iniTf=new JTextField(25);
    
    JButton yesBt=new JButton(new ImageIcon("graphics/confirm1.png"));
    JButton canBt=new JButton(new ImageIcon("graphics/cancel1.png"));
   
    int currentPage;
   
   
	public accountGUI(mainFrame jframe){
		this.jframe=jframe;
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0, 0, jframe.FRAME_WIDTH, jframe.FRAME_HEIGHT);
		jframe.setDragable(this);
		initialButton();
		initialopPanel();
		addOpPanel();
	}
	
	public void addOpPanel(){
		opPanel.setBounds(270,110,754,470);
		opPanel.setLayout(null);
        opPanel.setVisible(true);
        opPanel.setOpaque(false);
        messageLabel.setOpaque(false);
        messageLabel.setBounds(270, 580, 754, 188);
		this.add(opPanel);
        this.add(messageLabel);
		jframe.setContentPane(this);
	}
	public void initialopPanel(){
        messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));
		String instruction="ACCOUNT_DET:";
	    view.setInstruction(instruction);
	    con.setInstruction();
	    con.go();
	    
		
		String[] colomn={"时间","单据性质","客户姓名","收款金额","付款金额"};

		 DefaultTableModel tablem=new DefaultTableModel(con.getMessageTable(),colomn){
	    	 public boolean isCellEditable(int row,int colomn) {
	    	     return false;
	    	    }
	     };
	     JTable table=new JTable();
	     table.setModel(tablem);
	    JScrollPane scrollPane=new JScrollPane(table);
	    scrollPane.setBounds(0, 80, 500, 300);
		opPanel.add(scrollPane);
		
	}
	public void initialButton(){

		for(int i=0;i<buttons.length;i++){
			buttons[i]=new JButton(OPTION_BUTTONS[i]);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorderPainted(false);
			buttons[i].setBounds(points[i].x, points[i].y, OPTION_BUTTONS[i].getImage().getWidth(null), OPTION_BUTTONS[i].getImage().getHeight(null));
			buttonMouseAdapterAndActionListener buttonMouseAdapterAndActionListener=new buttonMouseAdapterAndActionListener(i,this);
   			if(buttons[i].getMouseListeners().length<2){
			buttons[i].addMouseListener(buttonMouseAdapterAndActionListener);
			}
			if(buttons[i].getActionListeners().length<1){
			buttons[i].addActionListener(buttonMouseAdapterAndActionListener);
			}
			this.add(buttons[i]);
		}
		
		 jframe.setContentPane(this);

	}
	
	public void initialComponents(){
		 customerLb.setBounds(100, 20, 400, 60);
	     receiveLb.setBounds(100, 100, 400, 60);
	     payLb.setBounds(100, 100, 400, 60);
		 timeLb.setBounds(100, 180, 550, 60);
		 initialLb.setBounds(100,20,400,60);
	
		 cusTf.setBounds(250, 35,200,20);
		 recTf.setBounds(250, 115,200,20);
	     payTf.setBounds(250, 115,200,20);
	     yearTf.setBounds(220, 195,50,20);
		 monthTf.setBounds(360, 195,30,20);
		 dayTf.setBounds(460, 195,30,20);
		 iniTf.setBounds(250, 35,200,20);
		 
		 cusTf.setBorder(BorderFactory.createEmptyBorder());
		 recTf.setBorder(BorderFactory.createEmptyBorder());
		 payTf.setBorder(BorderFactory.createEmptyBorder());
		 yearTf.setBorder(BorderFactory.createEmptyBorder());
		 iniTf.setBorder(BorderFactory.createEmptyBorder());
		 monthTf.setBorder(BorderFactory.createEmptyBorder());
		 dayTf.setBorder(BorderFactory.createEmptyBorder());


		 cusTf.setFont(new Font(null,3,18));
		 recTf.setFont(new Font(null,3,18));
		 payTf.setFont(new Font(null,3,18));
		 yearTf.setFont(new Font(null,3,18));
		 monthTf.setFont(new Font(null,3,18));
		 dayTf.setFont(new Font(null,3,18));
		 iniTf.setFont(new Font(null,3,18));

		 yesBt.setBounds(100, 375, 200, 60);
		 canBt.setBounds(320,375,200,60);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 yesBt.setContentAreaFilled(false);
		 canBt.setContentAreaFilled(false);
		 
	}
	
	public void clearComponents(){
		cusTf.setText("");
		recTf.setText("");
		payTf.setText("");
		iniTf.setText("");
		yearTf.setText("");
		monthTf.setText("");
		dayTf.setText("");
		
	}
	
	public void paintComponent(Graphics g){
		 //super.paintComponent(g);
		 g.drawImage(bg, 0, 0, 1024, 768, null);
		 /*for(int i=0;i<buttons.length;i++){
			 g.drawImage(LOGIN_BUTTON[i], points[i].x, points[i].y, LOGIN_BUTTONS[i].getImage().getWidth(null), LOGIN_BUTTONS[i].getImage().getHeight(null), null);
		 }*/
	}
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		accountGUI account;
		private buttonMouseAdapterAndActionListener(int i, accountGUI account){
			this.i=i;
			this.account=account;
			
		}
		
		public void mouseEntered(MouseEvent e){
			buttons[i].setIcon(OPTION_BUTTONS_ENTERED[i]);
			
		}
		public void mouseExited(MouseEvent e){
			buttons[i].setIcon(OPTION_BUTTONS[i]);
			
		}
		
		public void actionPerformed(ActionEvent e) {
			initialComponents();
			if(yesBt.getActionListeners().length<1){
		        yesBt.addActionListener(new yesL());
			    canBt.addActionListener(new canL());
			}
			
			if(canBt.getMouseListeners().length<2){
				yesBt.addMouseListener(new yesL());
			    canBt.addMouseListener(new canL());
			}
			currentPage=i;
			System.out.println(currentPage);
		    
		if(i==0){
			//添加收款单
			opPanel.removeAll();
			clearComponents();
			messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));

					
			opPanel.add(customerLb);
		    opPanel.add(receiveLb);
		    opPanel.add(timeLb);
	 
		    opPanel.add(cusTf);
		    opPanel.add(recTf);
		    opPanel.add(yearTf);
		    opPanel.add(monthTf);
		    opPanel.add(dayTf);
		   
		
		    //yesBt.setIcon(new ImageIcon("graphics/next_step.png"));
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==1){
			//添加付款单
			opPanel.removeAll();
			clearComponents();
			messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));

			
			opPanel.add(customerLb);
			opPanel.add(payLb);
			opPanel.add(timeLb);
		
			opPanel.add(cusTf);
			opPanel.add(payTf);
			opPanel.add(yearTf);
		    opPanel.add(monthTf);
		    opPanel.add(dayTf);
			
			opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		
		
		
		if(i==2){
			//显示总账目
			String instruction="ACCOUNT_ALL:";
	        view.setInstruction(instruction);
	        con.setInstruction();
	        con.go();
			opPanel.removeAll();
			clearComponents();
			messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));

			JLabel label=new JLabel(con.getMessageText());
			System.out.println("Message text: "+con.getMessageText());
			label.setBounds(50, 50,300,300);
			opPanel.add(label);
			//opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		if(i==3){
			//初始化账目
			opPanel.removeAll();
			clearComponents();
			messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));
			opPanel.add(initialLb);
			opPanel.add(iniTf);
			opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		
		if(i==4){
			setVisible(false);
			jframe.setContentPane(new mainGUI(jframe));
		}
		
		if(i==5){
			jframe.setExtendedState(jframe.ICONIFIED); 
			return;

		}
		
		if(i==6){
			System.exit(0);
			return;
		}
		
		}
		
		
		class yesL extends MouseAdapter implements ActionListener{
			String keyword;
			String instruction;
			
			public void mouseEntered(MouseEvent e){
				yesBt.setIcon(new ImageIcon("graphics/confirm2.png"));
				
			}
			public void mouseExited(MouseEvent e){
				yesBt.setIcon(new ImageIcon("graphics/confirm1.png"));
				
			}
			
			public void addConfirmPanel(JLabel label){
			    final JPanel confirmPanel=new JPanel();
			    label.setBounds(50, 50,400,400);
				label.setOpaque(false);
				final JButton confirmBt=new JButton(new ImageIcon("graphics/confirm1.png"));
				final JButton cancelBt=new JButton(new ImageIcon("graphics/last_step2.png"));
				confirmBt.setContentAreaFilled(false);
				confirmBt.setBorderPainted(false);
				cancelBt.setContentAreaFilled(false);
				cancelBt.setBorderPainted(false);
				confirmBt.setBounds(100, 375, 200, 60);
				cancelBt.setBounds(320, 375, 200, 60);
				confirmBt.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						confirmPanel.removeAll();
						view.setInstruction(instruction);
						con.setInstruction();
						con.go();
						opPanel.removeAll();
						String errorMessage=con.getMessageText();
						initialopPanel();
						if(!errorMessage.equals("")){
						    messageLabel.setIcon(new ImageIcon(errorMessage));
					    }
						addOpPanel();
					}
				});
				
				cancelBt.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//opPanel.removeAll();
						//initialopPanel();
						confirmPanel.setVisible(false);
						opPanel.setVisible(true);
						opPanel.add(canBt);
						opPanel.add(yesBt);
						opPanel.add(cusTf);
						opPanel.add(customerLb);
						opPanel.add(dayTf);
						opPanel.add(yearTf);
						opPanel.add(monthTf);
						opPanel.add(timeLb);
						opPanel.add(recTf);
						opPanel.add(receiveLb);
						addOpPanel();
					}
				});
				
				confirmBt.addMouseListener(new MouseAdapter(){
					public void mouseEntered(MouseEvent e){
						confirmBt.setIcon(new ImageIcon("graphics/confirm2.png"));
					}
					public void mouseExited(MouseEvent e){
						confirmBt.setIcon(new ImageIcon("graphics/confirm1.png"));
						
					}
					
				});
				
				cancelBt.addMouseListener(new MouseAdapter(){
					public void mouseEntered(MouseEvent e){
						cancelBt.setIcon(new ImageIcon("graphics/last_step.png"));
						
					}
					public void mouseExited(MouseEvent e){
						cancelBt.setIcon(new ImageIcon("graphics/last_step2.png"));
						
					}
				});
			    confirmPanel.add(label);
			    confirmPanel.add(confirmBt);
			    confirmPanel.add(cancelBt);
			    confirmPanel.setBounds(270,110,754,470);
			    confirmPanel.setLayout(null);
			    confirmPanel.setVisible(true);
			    confirmPanel.setOpaque(false);
			    account.remove(opPanel);
				account.add(confirmPanel);
				jframe.setContentPane(account);
			}
			public void actionPerformed(ActionEvent e){
				switch (currentPage){
				case 0:{
					if(cusTf.getText().trim().equals("")||recTf.getText().trim().equals("")){
						con.setMessageText("graphics/option_error_empty.png");
					}else{
						instruction="ACCOUNT_IN:"+cusTf.getText().trim()+"；"+recTf.getText().trim()+"；"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim();
					    opPanel.setVisible(false);
					    opPanel.removeAll();
					    JLabel label=new JLabel("<html>"+"                    确认如下收款单？"+"<br>"+"-----------------------------------------------------------"+"<br><br>"+"客户姓名："+cusTf.getText().trim()+"<br><br>"+"收款金额："+recTf.getText().trim()+"<br><br>"+"时间"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim()+"<br><br>"+"---------------------------------------------------------"+"</html>");
						addConfirmPanel(label);		
					}
				    break;
				}
				case 1:{
				    if(cusTf.getText().trim().equals("")||recTf.getText().trim().equals("")){
					    con.setMessageText("graphics/option_error_empty.png");
				    }else{
				    	instruction="ACCOUNT_OUT:"+cusTf.getText().trim()+"；"+payTf.getText().trim()+"；"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim();
				    	opPanel.setVisible(false);
					    opPanel.removeAll();				    
					    JLabel label=new JLabel("<html>"+"                    确认如下付款单？"+"<br>"+"-----------------------------------------------------------"+"<br><br>"+"客户姓名："+cusTf.getText().trim()+"<br><br>"+"付款金额："+recTf.getText().trim()+"<br><br>"+"时间"+yearTf.getText().trim()+"/"+monthTf.getText().trim()+"/"+dayTf.getText().trim()+"<br><br>"+"---------------------------------------------------------"+"</html>");
						addConfirmPanel(label);
				    }
				    break;
				}
				
				case 3:{
					if(iniTf.getText().trim().equals("")){
					    con.setMessageText("graphics/option_error_empty.png");
				    }else{
				    	instruction="ACCOUNT_INI:"+iniTf.getText().trim();
				        view.setInstruction(instruction);
				        con.setInstruction();
				        con.go();
				        //clearComponents();
					    opPanel.removeAll();
				    }
			        break;
			    }
				case 4:{
				    setVisible(false);
				    jframe.setContentPane(new mainGUI(jframe));
				    break;
				}
				}
				initialopPanel();
				String errorMessage=con.getMessageText();
				System.out.println(errorMessage);
				if(!errorMessage.equals("")){
				    messageLabel.setIcon(new ImageIcon(errorMessage));
			    }
				addOpPanel();
			}
			
		}
		
		class canL extends MouseAdapter implements ActionListener{
			public void mouseEntered(MouseEvent e){
				canBt.setIcon(new ImageIcon("graphics/cancel2.png"));
				
			}
			public void mouseExited(MouseEvent e){
				canBt.setIcon(new ImageIcon("graphics/cancel1.png"));
				
			}
			public void actionPerformed(ActionEvent e){
				opPanel.removeAll();
				jframe.setContentPane(new accountGUI(jframe));
			}
			
		}
	}

}

