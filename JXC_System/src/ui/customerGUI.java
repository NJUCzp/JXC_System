package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ui.commodityGUI.buttonMouseAdapterAndActionListener;
import ui.commodityGUI.buttonMouseAdapterAndActionListener.canL;
import ui.commodityGUI.buttonMouseAdapterAndActionListener.yesL;
import control.JXC_Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class customerGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	private Point[] points=new Point[]{new Point(0,110),new Point(0,170),new Point(0,230),new Point(0,290),new Point(0,350),new Point(810,20),new Point(910,20)};
	public ImageIcon[] OPTION_BUTTONS=new ImageIcon[]{new ImageIcon("graphics/customer/customer_add.png"),new ImageIcon ("graphics/customer/customer_del.png"),new ImageIcon("graphics/customer/customer_upd.png"),new ImageIcon("graphics/customer/customer_fin.png"),new ImageIcon("graphics/back.png"),new ImageIcon("graphics/ring.png"),new ImageIcon("graphics/ring.png")};
	public ImageIcon[] OPTION_BUTTONS_ENTERED=new ImageIcon[]{new ImageIcon("graphics/customer/customer_add2.png"),new ImageIcon ("graphics/customer/customer_del2.png"),new ImageIcon("graphics/customer/customer_upd2.png"),new ImageIcon("graphics/customer/customer_fin2.png"),new ImageIcon("graphics/back.png"),new ImageIcon("graphics/minimize.png"),new ImageIcon("graphics/close.png")};
	
	//JButton[] buttons=new JButton[]{new JButton("添加客户"),new JButton("删除客户"),new JButton("更新客户"),new JButton("查找客户"),new JButton("后退")};
	//Point[] points=new Point[]{new Point(20,100),new Point(20,175),new Point(20,250),new Point(20,325),new Point(20,475)};
	JButton[] buttons=new JButton[7];
	Image bg=new ImageIcon("graphics/option_background.png").getImage();
	JPanel opPanel=new JPanel();
	JLabel customerLb=new JLabel(new ImageIcon("graphics/customer/customer_name.png"));
    JLabel numberLb=new JLabel(new ImageIcon("graphics/customer/customer_phoneNumber.png"));
    JTextField cusTf=new JTextField(25);
    JTextField numTf=new JTextField(25);
    
    JTable table;    
    
    JButton yesBt=new JButton(new ImageIcon("graphics/confirm1.png"));
    JButton canBt=new JButton(new ImageIcon("graphics/cancel1.png"));
    
    int currentPage;
   
   
	public customerGUI(mainFrame jframe){
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
		this.add(opPanel);
		jframe.setContentPane(this);
	}
	public void addTable(){
		String[] colomn={"客户姓名","联系方式","应收账款金额","应付账款金额","合计"};

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
	    scrollPane.setBounds(0, 80, 500, 300);
		opPanel.add(scrollPane);
	}
	
	
	public void initialopPanel(){
		view.setInstruction("CUSTOMER_SHO:");
		con.setInstruction();
		con.go();		
		addTable();
		
	}
	public void initialButton(){
		for(int i=0;i<buttons.length;i++){
			buttons[i]=new JButton(OPTION_BUTTONS[i]);
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			
			if(i<6)
			    buttons[i].setBounds(points[i].x, points[i].y, OPTION_BUTTONS[i].getImage().getWidth(null), OPTION_BUTTONS[i].getImage().getHeight(null));
			else
				buttons[i].setBounds(points[i].x, points[i].y, OPTION_BUTTONS[i].getImage().getWidth(null), OPTION_BUTTONS[i].getImage().getHeight(null));
			
			buttonMouseAdapterAndActionListener buttonMouseAdapterAndActionListener=new buttonMouseAdapterAndActionListener(i,this);
   			if(buttons[i].getMouseListeners().length<2){
			buttons[i].addMouseListener(buttonMouseAdapterAndActionListener);
			}
			if(buttons[i].getActionListeners().length<1){
			buttons[i].addActionListener(buttonMouseAdapterAndActionListener);
			}
			buttons[i].setEnabled(true);
			this.add(buttons[i]);
		}
		
		 jframe.setContentPane(this);

	}
	
	public void initialComponents(){
		 customerLb.setBounds(100, 20, 400, 60);
		 numberLb.setBounds(100, 100, 400, 60);
		 
		 cusTf.setBounds(250, 35,200,20);
		 numTf.setBounds(250, 115,200,20);
		 
		 cusTf.setBorder(BorderFactory.createEmptyBorder());
		 numTf.setBorder(BorderFactory.createEmptyBorder());
		
		 cusTf.setFont(new Font(null,3,18));
		 numTf.setFont(new Font(null,3,18));
		 
		 yesBt.setBounds(100, 375, 200, 60);
		 canBt.setBounds(320,375,200,60);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 yesBt.setContentAreaFilled(false);
		 canBt.setContentAreaFilled(false);
		 
	}
	
	public void clearComponents(){
		cusTf.setText("");
		numTf.setText("");
		
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
		customerGUI customer;
		private buttonMouseAdapterAndActionListener(int i, customerGUI customer){
			this.i=i;
			this.customer=customer;
			
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
			//添加客户
			customer.buttons[1].setEnabled(false);
			buttons[1].setIcon(new ImageIcon("graphics/customer/customer_del_dis.png"));
			jframe.setContentPane(customer);
			opPanel.removeAll();
			clearComponents();
			
					
			opPanel.add(customerLb);
		    opPanel.add(numberLb);
		    opPanel.add(cusTf);
		    opPanel.add(numTf);
		   
		
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==1){
			//删除客户
			ArrayList<String> instructions=new ArrayList<String>();
			instructions.clear();
			
			for(int j=0;j<table.getRowCount();j++){
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())){
					instructions.add("CUSTOMER_DEL:"+table.getValueAt(j, 1).toString());
				}
			}
			
			System.out.println(instructions.size());
			for(int j=0;j<instructions.size();j++){
				view.setInstruction(instructions.get(j));
				con.setInstruction();
				con.go();
			}
			
			instructions.clear();
			initialopPanel();		
			return;
		}
		if(i==2){
			//更新客户
			customer.buttons[1].setEnabled(false);
			buttons[1].setIcon(new ImageIcon("graphics/customer/customer_del_dis.png"));
			jframe.setContentPane(customer);
			
			opPanel.removeAll();
			clearComponents();
		
		    
			opPanel.add(customerLb);
		    opPanel.add(numberLb);
		    opPanel.add(cusTf);
		    opPanel.add(numTf);
		    
		    for(int j=0;j<table.getRowCount();j++){
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())){
					cusTf.setText(table.getValueAt(j, 1).toString());
					numTf.setText(table.getValueAt(j, 2).toString());
				}
			}
			
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==3){
			//查找客户
			customer.buttons[1].setEnabled(false);
			buttons[1].setIcon(new ImageIcon("graphics/customer/customer_del_dis.png"));
			jframe.setContentPane(customer);
			opPanel.removeAll();
			clearComponents();
			
			
			opPanel.add(customerLb);
			opPanel.add(cusTf);
			
			
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
			
			public void actionPerformed(ActionEvent e){
				switch (currentPage){
				
				case 0:{instruction="CUSTOMER_ADD:"+cusTf.getText().trim()+"；"+numTf.getText().trim();
				    break;
				}
				case 2:{instruction="CUSTOMER_UPD:"+cusTf.getText().trim()+"；"+numTf.getText().trim();
				    break;
				}
				case 3:{instruction="CUSTOMER_FIN:"+cusTf.getText().trim();
			        break;
			    }
				
				}
				
				view.setInstruction(instruction);
				con.setInstruction();
				con.go();

				clearComponents();
				opPanel.removeAll();
				
				switch (currentPage){
				case 3:{
					/*JLabel label=new JLabel(con.getMessageText());
					label.setBounds(50, 200,300,300);
					opPanel.add(label);*/
					buttons[1].setEnabled(true);
					buttons[1].setIcon(OPTION_BUTTONS[1]);
					addTable();
					opPanel.add(canBt);
					break;
				}
				default:initialopPanel();
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
