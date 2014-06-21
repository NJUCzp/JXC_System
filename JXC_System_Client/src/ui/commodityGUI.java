package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class commodityGUI extends JPanel {
	mainFrame jframe;
	JXC_View view;
	private Point[] points=new Point[]{new Point(0,110),new Point(0,170),new Point(0,230),new Point(0,290),new Point(0,350),new Point(810,20),new Point(910,20)};
	public ImageIcon[] OPTION_BUTTONS=new ImageIcon[]{new ImageIcon("graphics/commodity/commodity_add.png"),new ImageIcon ("graphics/commodity/commodity_del.png"),new ImageIcon("graphics/commodity/commodity_upd.png"),new ImageIcon("graphics/commodity/commodity_fin.png"),new ImageIcon("graphics/back.png"),new ImageIcon("graphics/ring.png"),new ImageIcon("graphics/ring.png")};
	public ImageIcon[] OPTION_BUTTONS_ENTERED=new ImageIcon[]{new ImageIcon("graphics/commodity/commodity_add2.png"),new ImageIcon ("graphics/commodity/commodity_del2.png"),new ImageIcon("graphics/commodity/commodity_upd2.png"),new ImageIcon("graphics/commodity/commodity_fin2.png"),new ImageIcon("graphics/back.png"),new ImageIcon("graphics/minimize.png"),new ImageIcon("graphics/close.png")};
	//JButton[] buttons=new JButton[]{new JButton("添加商品"),new JButton("删除商品"),new JButton("更新商品"),new JButton("查找商品"),new JButton("后退")};
	//Point[] points=new Point[]{new Point(20,100),new Point(20,175),new Point(20,250),new Point(20,325),new Point(20,475)};
	JButton[] buttons=new JButton[7];
	Image bg=new ImageIcon("graphics/option_background.png").getImage();
	JPanel opPanel=new JPanel();
	JLabel messageLabel=new JLabel();
	JLabel commodityLb=new JLabel(new ImageIcon("graphics/commodity/commodity_name.png"));
    JLabel numberLb=new JLabel(new ImageIcon("graphics/commodity/commodity_number.png"));
    JLabel defaultImLb=new JLabel(new ImageIcon("graphics/commodity/commodity_defaultIm.png"));
    JLabel defaultExLb=new JLabel(new ImageIcon("graphics/commodity/commodity_defaultEx.png"));
    JTextField comTf=new JTextField(25);
    JTextField numTf=new JTextField(25);
    JTextField imTf=new JTextField(25);
    JTextField exTf=new JTextField(25);
    
    JButton yesBt=new JButton(new ImageIcon("graphics/confirm1.png"));
    JButton canBt=new JButton(new ImageIcon("graphics/cancel1.png"));
    
    JTable table;    
    int currentPage;
   
	public commodityGUI(JXC_View view,mainFrame jframe){
		this.view=view;
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
	public void addTable(){
		String[] colomn={"选择","商品名称","商品型号","数量","默认进价","默认售价","上一次进价","上一次售价"};
		DefaultTableModel tablem=new DefaultTableModel(view.receiveMessageTable(),colomn){
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
	    //table.setOpaque(false);
        JScrollPane scrollPane=new JScrollPane(table);
	    scrollPane.setBounds(80, 80, 500, 300);
	    //scrollPane.setOpaque(false);
	    opPanel.add(scrollPane);
		
	}
	public void initialopPanel(){
        messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));
		view.setInstruction("COMMODITY_SHO:");
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
		 commodityLb.setBounds(100, 20, 400, 60);
		 numberLb.setBounds(100, 100, 400, 60);
		 defaultImLb.setBounds(100, 180, 400, 60);
		 defaultExLb.setBounds(100, 260, 400, 60);
		    
		 comTf.setBounds(250, 35,200,20);
		 numTf.setBounds(250, 115,200,20);
		 imTf.setBounds(250, 195,200,20);
		 exTf.setBounds(250, 275,200,20);
		 
		 comTf.setBorder(BorderFactory.createEmptyBorder());
		 numTf.setBorder(BorderFactory.createEmptyBorder());
		 imTf.setBorder(BorderFactory.createEmptyBorder());
		 exTf.setBorder(BorderFactory.createEmptyBorder());

		 comTf.setFont(new Font(null,3,18));
		 numTf.setFont(new Font(null,3,18));
		 imTf.setFont(new Font(null,3,18));
		 exTf.setFont(new Font(null,3,18));

		 yesBt.setBounds(100, 375, 200, 60);
		 canBt.setBounds(320,375,200,60);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 yesBt.setContentAreaFilled(false);
		 canBt.setContentAreaFilled(false);
		 
	}
	
	public void clearComponents(){
		comTf.setText("");
		numTf.setText("");
		imTf.setText("");
		exTf.setText("");
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
		commodityGUI commodity;
		private buttonMouseAdapterAndActionListener(int i, commodityGUI commodity){
			this.i=i;
			this.commodity=commodity;
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
			//添加商品
			//commodity.buttons[1].setEnabled(false);
			//buttons[1].setIcon(new ImageIcon("graphics/commodity/commodity_del_dis.png"));
			//jframe.setContentPane(commodity);
			opPanel.removeAll();
			clearComponents();
			messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));
					
			opPanel.add(commodityLb);
		    opPanel.add(numberLb);
		    opPanel.add(defaultImLb);
		    opPanel.add(defaultExLb);
		    opPanel.add(comTf);
		    opPanel.add(numTf);
		    opPanel.add(imTf);
		    opPanel.add(exTf);
		
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    return;
		}
		if(i==1){
			//删除商品
			opPanel.removeAll();
			clearComponents();
			messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));
			for(int j=0;j<table.getRowCount();j++){
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())){
					comTf.setText(table.getValueAt(j, 1).toString());		
					numTf.setText(table.getValueAt(j, 2).toString());
					break;
				}
			}
			
			opPanel.add(commodityLb);
			opPanel.add(numberLb);
		    opPanel.add(comTf);
		    opPanel.add(numTf);
 		    opPanel.add(yesBt);
		    opPanel.add(canBt);
		    addOpPanel();
			return;
		}
		if(i==2){
			//修改商品信息
			//commodity.buttons[1].setEnabled(false);
			//buttons[1].setIcon(new ImageIcon("graphics/commodity/commodity_del_dis.png"));
			//jframe.setContentPane(commodity);
			opPanel.removeAll();
			clearComponents();
			messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));
		
			opPanel.add(commodityLb);
		    opPanel.add(numberLb);
		    opPanel.add(defaultImLb);
		    opPanel.add(defaultExLb);
		    opPanel.add(comTf);
		    opPanel.add(numTf);
		    opPanel.add(imTf);
		    opPanel.add(exTf);
		    
		    for(int j=0;j<table.getRowCount();j++){
				if(Boolean.parseBoolean(table.getValueAt(j, 0).toString())){
					comTf.setText(table.getValueAt(j, 1).toString());
					numTf.setText(table.getValueAt(j, 2).toString());
					imTf.setText(table.getValueAt(j, 4).toString());
					exTf.setText(table.getValueAt(j, 5).toString());
				}
			}
			
		    opPanel.add(yesBt);
		    opPanel.add(canBt);

		    addOpPanel();
		    
		    return;
		}
		if(i==3){
			//commodity.buttons[1].setEnabled(false);
			//buttons[1].setIcon(new ImageIcon("graphics/commodity/commodity_del_dis.png"));
			//jframe.setContentPane(commodity);
			opPanel.removeAll();
			clearComponents();
			messageLabel.setIcon(new ImageIcon("graphics/option_message_default.png"));
			
			opPanel.add(commodityLb);
			opPanel.add(numberLb);
			opPanel.add(comTf);
			opPanel.add(numTf);
			
			opPanel.add(yesBt);
			opPanel.add(canBt);
		    addOpPanel();
		
			return;
		}
		
		if(i==4){
			setVisible(false);
			jframe.setContentPane(new mainGUI(view,jframe));
			return;
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
				String errorMessage="";
				switch (currentPage){
				case 0:{
					if(comTf.getText().trim().equals("")||numTf.getText().trim().equals("")||imTf.getText().trim().equals("")||exTf.getText().trim().equals("")){
						errorMessage=("graphics/option_error_empty.png");
					}else{
						instruction="COMMODITY_ADD:"+comTf.getText().trim()+"；"+numTf.getText().trim()+"；"+imTf.getText().trim()+"；"+exTf.getText().trim();
						view.setInstruction(instruction);
						errorMessage=view.receiveMessageText();
					}						
				    break;
				}
				case 1:{
					if(comTf.getText().trim().equals("")||numTf.getText().trim().equals("")){
						errorMessage=("graphics/option_error_empty.png");
					}else{
						instruction="COMMODITY_DEL:"+comTf.getText().trim()+"；"+numTf.getText().trim();
						view.setInstruction(instruction);
						errorMessage=view.receiveMessageText();
					}
				    break;
				}
				case 2:{
					if(comTf.getText().trim().equals("")||numTf.getText().trim().equals("")||imTf.getText().trim().equals("")||exTf.getText().trim().equals("")){
						errorMessage=("graphics/option_error_empty.png");
					}else{
						instruction="COMMODITY_UPD:"+comTf.getText().trim()+"；"+numTf.getText().trim()+"；"+imTf.getText().trim()+"；"+exTf.getText().trim();
						view.setInstruction(instruction);
						errorMessage=view.receiveMessageText();
					}
				    break;
				}
				case 3:{
					if(comTf.getText().trim().equals("")||numTf.getText().trim().equals("")){
						errorMessage=("graphics/option_error_empty.png");
					}else{
						instruction="COMMODITY_FIN:"+comTf.getText().trim()+"；"+numTf.getText().trim();
					    view.setInstruction(instruction);
					    errorMessage="graphics/option_message_default.png";
				        break;
					}
			    }
				}
				clearComponents();
				opPanel.removeAll();
				switch (currentPage){
				case 3:{
					if(!errorMessage.equals("graphics/option_error_empty.png")){
						buttons[1].setEnabled(true);
						buttons[1].setIcon(OPTION_BUTTONS[1]);
						addTable(); 
						opPanel.add(canBt);
					}else{
						initialopPanel();
					}
					break;
				}
				default:{
					buttons[1].setEnabled(true);
					buttons[1].setIcon(OPTION_BUTTONS[1]);
					System.out.println("errorMessage: "+errorMessage);
					initialopPanel();
					break;
				}
				}
			    messageLabel.setIcon(new ImageIcon(errorMessage));
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
				jframe.setContentPane(new commodityGUI(view,jframe));
			}
			
		}
	}
	
}
