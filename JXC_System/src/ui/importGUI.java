package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ui.commodityGUI.buttonMouseAdapterAndActionListener.canL;
import ui.commodityGUI.buttonMouseAdapterAndActionListener.yesL;
import control.JXC_Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class importGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	private Point[] points=new Point[]{new Point(0,110),new Point(0,170),new Point(0,230),new Point(0,290),new Point(810,20),new Point(910,20)};
	private Point[] tf_points=new Point[]{new Point(270, 38),new Point(270, 118),new Point(270, 198),new Point(270, 278),new Point(500, 278),new Point(270, 357),new Point(420,357),new Point(515,357),new Point(270, 38),new Point(150, 100),new Point(150, 175),new Point(270, 118),new Point(420,118),new Point(520,118)};
    private JTextField[] textfields=new JTextField[]{new JTextField(25),new JTextField(25),new JTextField(25),new JTextField(25),new JTextField(25),new JTextField(5),new JTextField(3),new JTextField(3),new JTextField(25),new JTextField(25),new JTextField(25),new JTextField(5),new JTextField(3),new JTextField(3)};
	public ImageIcon[] OPTION_BUTTONS=new ImageIcon[]{new ImageIcon("graphics/import/import_add.png"),new ImageIcon ("graphics/import/import_del.png"),new ImageIcon("graphics/import/import_sho.png"),new ImageIcon("graphics/back.png"),new ImageIcon("graphics/ring.png"),new ImageIcon("graphics/ring.png")};
	public ImageIcon[] OPTION_BUTTONS_ENTERED=new ImageIcon[]{new ImageIcon("graphics/import/import_add2.png"),new ImageIcon ("graphics/import/import_del2.png"),new ImageIcon("graphics/import/import_sho2.png"),new ImageIcon("graphics/back.png"),new ImageIcon("graphics/minimize.png"),new ImageIcon("graphics/close.png")};
	Image bg=new ImageIcon("graphics/option_background.png").getImage();
	JButton[] buttons=new JButton[6];
	JPanel opPanel=new JPanel();
	JLabel messageLabel=new JLabel();
	JLabel customerLb=new JLabel(new ImageIcon("graphics/customer/customer_name.png"));
	JLabel commodityLb=new JLabel(new ImageIcon("graphics/commodity/commodity_name.png"));
    JLabel numberLb=new JLabel(new ImageIcon("graphics/commodity/commodity_number.png"));
    JLabel quaLb=new JLabel(new ImageIcon("graphics/import/import_quantity_del.png"));
    JLabel quantityAndImpriceLb=new JLabel(new ImageIcon("graphics/imprice&quantity.png"));
    JLabel timeLb=new JLabel(new ImageIcon("graphics/time_tf.png"));
    JLabel sheetLb=new JLabel(new ImageIcon("graphics/import/import_sheet.png"));
    JLabel startLb=new JLabel(new ImageIcon("graphics/begin_time.png"));
    JLabel endLb=new JLabel(new ImageIcon("graphics/end_time.png"));
    /*JTextField cusTf=new JTextField(25);
    JTextField comTf=new JTextField(25);
    JTextField numTf=new JTextField(25);
    JTextField quaTf=new JTextField(25);
    JTextField priTf=new JTextField(25);
    JTextField yearTf=new JTextField(5);
    JTextField monthTf=new JTextField(3);
    JTextField dayTf=new JTextField(3);
    JTextField sheTf=new JTextField(25);
    JTextField staTf=new JTextField(25);
    JTextField endTf=new JTextField(25);*/
    JButton yesBt=new JButton(new ImageIcon("graphics/confirm1.png"));
    JButton canBt=new JButton(new ImageIcon("graphics/cancel1.png"));
    JTable table;    
    int currentPage;
   
	public importGUI(mainFrame jframe){
		this.jframe=jframe;
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0, 0, jframe.FRAME_WIDTH, jframe.FRAME_HEIGHT);
		jframe.setDragable(this);
		initialButton();
		initialopPanel();
		addOpPanel();
	}
	public void addTable(){
		String[] colomn={"选择","时间","单据性质","客户姓名","商品名称","商品型号","数量","单价","总价","对应进货单号"};
		DefaultTableModel tablem=new DefaultTableModel(con.getMessageTable(),colomn){
			public boolean isCellEditable(int row,int colomn) {
	    		 if(colomn==0)
	    			 return true;
	    		 else
	    			 return false;
	    	}
	    	 
	    	public Class<?> getColumnClass(int columnIndex) 
	        { 
	            if(columnIndex==0)
	            { return Boolean.class; 
	            } 
	            return Object.class; 
	        } 
	    	   
	    };
	    JTable table=new JTable();
	    table.setModel(tablem);
	    this.table=table;
	
        JScrollPane scrollPane=new JScrollPane(table);
	    scrollPane.setBounds(80, 80, 600, 300);
	     
	    opPanel.add(scrollPane);
		
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
		view.setInstruction("IMPORT_WHOLE:");
		con.setInstruction();
		con.go();
		addTable();
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
		 commodityLb.setBounds(100, 100, 400, 60);
		 quaLb.setBounds(100, 100, 400, 60);
		 numberLb.setBounds(100, 180, 400, 60);
		 quantityAndImpriceLb.setBounds(96, 260, 550, 60);
		 timeLb.setBounds(160, 340, 550, 60);
		 sheetLb.setBounds(100,20,400,60);
		 startLb.setBounds(100,20,550,60);
		 endLb.setBounds(100,100,550,60);
		 for(int i=0;i<textfields.length;i++){
			 textfields[i].setBorder(BorderFactory.createEmptyBorder());
			 textfields[i].setFont(new Font(null,3,18));
			 if(i<3||i>7){
				 textfields[i].setBounds(tf_points[i].x, tf_points[i].y, 200, 20);
			 }
			 if(i>=3||i<=4){
				 textfields[i].setBounds(tf_points[i].x, tf_points[i].y, 100, 20);
			 }
			 if(i==5||i==11){
				 textfields[i].setBounds(tf_points[i].x, tf_points[i].y, 50, 20);
			 }
			 if(i==6||i==7||i==12||i==13){
				 textfields[i].setBounds(tf_points[i].x, tf_points[i].y, 40, 20);
			 }
		 }
		 yesBt.setBounds(100, 410, 200, 60);
		 canBt.setBounds(320,410,200,60);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 yesBt.setContentAreaFilled(false);
		 canBt.setContentAreaFilled(false);
	}
	
	public void clearComponents(){
		for(int i=0;i<textfields.length;i++)
			textfields[i].setText("");
		
	}
	public void paintComponent(Graphics g){
		 g.drawImage(bg, 0, 0, 1024, 768, null);
	}
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		importGUI imports;
		private buttonMouseAdapterAndActionListener(int i, importGUI imports){
			this.i=i;
			this.imports=imports;
			
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
			//添加进货单
			opPanel.removeAll();
			clearComponents();
	        messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));

			for(int j=0;j<8;j++){
				opPanel.add(textfields[j]);
			}		
			
			opPanel.add(customerLb);
		    opPanel.add(commodityLb);
		    opPanel.add(numberLb);
		    opPanel.add(quantityAndImpriceLb);
		    opPanel.add(timeLb);
		   
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    return;
		}
		if(i==1){
			//添加进货退货单
			opPanel.removeAll();
			clearComponents();
	        messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));
			timeLb.setBounds(160, 180, 550, 60);			
			textfields[3].setBounds(270, 118, 200, 20);
			textfields[5].setBounds(270, 198, 50, 20);
			textfields[6].setBounds(417, 198, 40, 20);
			textfields[7].setBounds(512, 198, 40, 20);
			

			for(int j=0;j<table.getRowCount();j++){
				System.out.println(table.getValueAt(j, 9).toString());
				System.out.println(table.getValueAt(j, 6).toString());
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())&&table.getValueAt(j, 2).toString().equals("ADD")){
					textfields[8].setText(table.getValueAt(j, 9).toString());
					textfields[3].setText(table.getValueAt(j, 6).toString());
					
				    break;
				}
			}
			opPanel.add(sheetLb);
			opPanel.add(quaLb);
			opPanel.add(timeLb);
			opPanel.add(textfields[3]);
			opPanel.add(textfields[5]);
			opPanel.add(textfields[6]);
			opPanel.add(textfields[7]);
			opPanel.add(textfields[8]);
			opPanel.add(yesBt);
			opPanel.add(canBt);			
		    addOpPanel();
			return;
		}
		
		if(i==2){
			//显示单据
			opPanel.removeAll();
			clearComponents();
	        messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));

			opPanel.add(startLb);
			opPanel.add(endLb);
			textfields[5].setBounds(270, 38, 50, 20);
			textfields[6].setBounds(420, 38, 40, 20);
			textfields[7].setBounds(520, 38, 40, 20);
			for(int j=0;j<textfields.length;j++){
				if((j>=5&&j<=7)||(j>=11&&j<=13))
				  opPanel.add(textfields[j]);
			}
	
			opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		
		if(i==3){
			setVisible(false);
			jframe.setContentPane(new mainGUI(jframe));
		}
		
		if(i==4){
			jframe.setExtendedState(jframe.ICONIFIED); 
			return;

		}
		
		if(i==5){
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
			    label.setBounds(300, 10,400,400);
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
						opPanel.add(customerLb);
					    opPanel.add(commodityLb);
					    opPanel.add(numberLb);
					    opPanel.add(quantityAndImpriceLb);
					    opPanel.add(timeLb);
				
					    for(int i=0;i<8;i++){
					    	textfields[i].setText(textfields[i].getText());
					    	opPanel.add(textfields[i]);
					    }
					  
					    opPanel.add(yesBt);
					    opPanel.add(canBt);
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
				confirmPanel.setBounds(270,110,754,470);
			    confirmPanel.setLayout(null);
			    confirmPanel.setVisible(true);
			    confirmPanel.setOpaque(false);
			    confirmPanel.add(label);
			    confirmPanel.add(confirmBt);
			    confirmPanel.add(cancelBt);
			    
			    imports.remove(opPanel);
				imports.add(confirmPanel);
				jframe.setContentPane(imports);
			}
			public void actionPerformed(ActionEvent e){
				switch (currentPage){
				case 0:{
					if(textfields[0].getText().trim().equals("")||textfields[1].getText().trim().equals("")||textfields[2].getText().trim().equals("")||textfields[3].getText().trim().equals("")||textfields[4].getText().trim().equals("")||textfields[5].getText().trim().equals("")||textfields[6].getText().trim().equals("")||textfields[7].getText().trim().equals("")){
						con.setMessageText("graphics/option_error_empty.png");
						System.out.println("empty error");
					}else{
						instruction="IMPORT_ADD:"+textfields[0].getText().trim()+"；"+textfields[1].getText().trim()+"；"+textfields[2].getText().trim()+"；"+textfields[3].getText().trim()+"；"+textfields[4].getText().trim()+"；"+textfields[5].getText().trim()+"/"+textfields[6].getText().trim()+"/"+textfields[7].getText().trim();
					    opPanel.setVisible(false);    
					    opPanel.removeAll();
					    JLabel label=new JLabel("<html>"+"                    确认如下进货单？"+"<br>"+"-----------------------------------------------------------"+"<br><br>"+"客户姓名："+textfields[0].getText().trim()+"<br><br>"+"商品名称："+textfields[1].getText().trim()+"<br><br>"+"商品型号："+textfields[2].getText().trim()+"<br><br>"+"数量："+textfields[3].getText().trim()+"<br><br>"+"单价："+textfields[4].getText().trim()+"<br><br>"+"时间"+textfields[5].getText().trim()+"/"+textfields[6].getText().trim()+"/"+textfields[7].getText().trim()+"<br><br>"+"---------------------------------------------------------"+"</html>");
						addConfirmPanel(label);
					}
				    break;
				}
				case 1:{
					if(textfields[8].getText().trim().equals("")||textfields[3].getText().trim().equals("")||textfields[5].getText().trim().equals("")||textfields[6].getText().trim().equals("")||textfields[7].getText().trim().equals("")){
						con.setMessageText("graphics/option_error_empty.png");
					}else{
						instruction="IMPORT_DEL:"+textfields[8].getText().trim()+"；"+textfields[3].getText().trim()+"；"+textfields[5].getText().trim()+"/"+textfields[6].getText().trim()+"/"+textfields[7].getText().trim();
				        opPanel.setVisible(false);    
					    opPanel.removeAll();
					    JLabel label=new JLabel("<html>"+"确认如下进货退货单？"+"<br>"+"-------------------------"+"<br>"+"进货单号："+textfields[8].getText().trim()+"<br>"+"退货数量："+textfields[3].getText().trim()+"<br>"+"日期："+textfields[5].getText().trim()+"/"+textfields[6].getText().trim()+"/"+textfields[7].getText().trim()+"<br>"+"---------------------------"+"</html>");
					    addConfirmPanel(label);
					}
				    break;
				}
				case 2:{
					if(textfields[5].getText().trim().equals("")||textfields[6].getText().trim().equals("")||textfields[7].getText().trim().equals("")||textfields[11].getText().trim().equals("")||textfields[12].getText().trim().equals("")||textfields[13].getText().trim().equals("")){
						con.setMessageText("graphics/option_error_empty.png");
					}else{
						instruction="IMPORT_SHO:"+textfields[5].getText().trim()+"/"+textfields[6].getText().trim()+"/"+textfields[7].getText().trim()+"；"+textfields[11].getText().trim()+"/"+textfields[12].getText().trim()+"/"+textfields[13].getText().trim();
					    view.setInstruction(instruction);
					    con.setInstruction();
					    con.go();
					    clearComponents();
						opPanel.removeAll();
						addTable();
					}
					break;
				}
				}
				String errorMessage=con.getMessageText();
				System.out.println(errorMessage);
				//initialopPanel();
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
				initialopPanel();
				addOpPanel();
			}
			
		}
	}

}
