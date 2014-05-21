
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class LoginGUI extends JPanel {
	mainFrame jframe;
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	//JPanel opPanel=new JPanel();
	JButton[] buttons=new JButton[]{new JButton("登陆"),new JButton("注册"),new JButton("最小化"),new JButton("关闭")};
	Point[] points=new Point[]{new Point(300,575),new Point(500,575),new Point(700,20),new Point(801,20)};
	JLabel idLb=new JLabel("账号");
    JLabel passwordLb=new JLabel("密码");
    JLabel newidLb=new JLabel("输入账号");
    JLabel newpassLb=new JLabel("输入密码");
    JLabel passagianLb=new JLabel("再次输入密码");

    JTextField idTf=new JTextField(25);
    JPasswordField passTp=new JPasswordField(25);
    JTextField newidTf=new JTextField(25);
    JPasswordField newpassTp=new JPasswordField(25);
    JPasswordField passagainTp=new JPasswordField(25);
    
    JRadioButton[] rads=new JRadioButton[]{new JRadioButton("库存管理员"),new JRadioButton("销售人员"),new JRadioButton("财务人员")}; 
    ButtonGroup group = new ButtonGroup();

    
    JButton yesBt=new JButton("确认");
    JButton canBt=new JButton("取消");
    
    int currentPage;
    String name="";
    String position="";
   
   
	public LoginGUI(mainFrame jframe){
		this.jframe=jframe;
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0, 0, jframe.FRAME_WIDTH, jframe.FRAME_HEIGHT);
		jframe.setDragable(this);
		initialButton();
		initialComponents();
		initialPanel();
		//initialopPanel();
		//addOpPanel();
	}
	
	/*(public void addOpPanel(){
		opPanel.setBounds(512,0,512,768);
		opPanel.setLayout(null);
        opPanel.setVisible(true);
		this.add(opPanel);
		jframe.setContentPane(this);
	}
	public void initialopPanel(){
		JLabel label=new JLabel("请点击左边按钮进行相关操作~");
		label.setBounds(200, 200,300,300);
		opPanel.add(label);
		
	}*/
    public void initialButton(){
		for(int i=0;i<buttons.length;i++){
			buttons[i].setBorderPainted(false);
			buttons[i].setBounds(points[i].x, points[i].y, 100, 50);
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
    
    public void initialPanel(){
    	this.add(idLb);
    	this.add(passwordLb);
    	this.add(idTf);
    	this.add(passTp);
		jframe.setContentPane(this);

    }

	
	public void initialComponents(){
		 idLb.setBounds(300, 100, 100, 50);
	     passwordLb.setBounds(300, 275, 100, 50);
	     newidLb.setBounds(300,100,100,50);
	     newpassLb.setBounds(300,200,100,50);
	     passagianLb.setBounds(300,300,100,50);

	
		 idTf.setBounds(400, 100,300,20);
		 passTp.setBounds(400, 275, 300, 20);
		 newidTf.setBounds(400, 100, 300, 20);
		 newpassTp.setBounds(400, 200, 300, 20);
	     passagainTp.setBounds(400,300,300,20);
	     
	     for(int i=0;i<3;i++){
	    	 group.add(rads[i]);
	    	 rads[i].setBounds(350+100*i, 350, 50, 25);
	     }

	
		 
		 yesBt.setBounds(300, 475, 100, 50);
		 canBt.setBounds(450,475,100,50);
		 yesBt.setBorderPainted(false);
		 canBt.setBorderPainted(false);
		 
	}
	
	public void clearComponents(){
		idTf.setText("");
		passTp.setText("");
		newpassTp.setText("");;
	    passagainTp.setText("");;
		
	}
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		LoginGUI login;
		private buttonMouseAdapterAndActionListener(int i,LoginGUI login){
			this.i=i;
			this.login=login;
			
		}
		
		/*public void mouseEntered(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS_ENTERED[i]);
			
		}
		public void mouseExited(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS[i]);
			
		}*/
		public void actionPerformed(ActionEvent e) {
			initialComponents();
			if(yesBt.getActionListeners().length<1)
		        yesBt.addActionListener(new yesL());
			canBt.addActionListener(new canL());
			String instruction;
			currentPage=i;
			System.out.println(currentPage);
		    
		if(i==0){
			//登陆
			instruction="LOGIN_LOG:"+idTf.getText().trim()+"；"+passTp.getText().trim();
		    view.setInstruction(instruction);
			con.setInstruction();
			con.go();
		    if(con.login.isSuccessful()){
		    	name=idTf.getText();
		    	position=con.login.getPosition();
		    	setVisible(false);
		    	Helper helper=new Helper();
		    	ArrayList<String> loginInfo=new ArrayList<String>();
		    	helper.setFilename("data/current.txt");
		    	loginInfo.add(name+"；"+position);
		    	helper.output(loginInfo);
		    	//mainGUI maingui=new mainGUI(jframe);
		    	//maingui.setPosition(position);
		    	//maingui.setName(name);
		    	jframe.setContentPane(new mainGUI(jframe));		    	
				clearComponents();

		    }
		    return;
		}
		if(i==1){
			//注册
			login.removeAll();
			clearComponents();
			
			
			login.add(newidLb);
			login.add(newpassLb);
			login.add(passagianLb);
			
			login.add(newidTf);
			login.add(newpassTp);
			login.add(passagainTp);
			
			for(int i=0;i<3;i++){
				login.add(rads[i]);
			}
			
			login.add(yesBt);
			login.add(canBt);
		    jframe.setContentPane(login);
		    
		  
			return;
		}
		
		if(i==2){
			//最小化
			jframe.setExtendedState(jframe.ICONIFIED); 
			//System.out.println(1);
			return;
		}
		
		if(i==3){
			//关闭
			System.exit(0);
			//System.out.println(0);
			return;
		}
		
		}
		
		
		class yesL implements ActionListener{
			String keyword;
			String instruction;
		    //@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				String position="";
				if(newpassTp.getText().equals(passagainTp.getText())){
					    if(rads[0].isSelected()){
					    	position="stockManager";
					    }
					    if(rads[1].isSelected()){
					    	position="salesman";
					    }
					    if(rads[2].isSelected()){
					    	position="account";
					    }
						instruction="LOGIN_RES:"+newidTf.getText().trim()+"；"+newpassTp.getText().trim()+"；"+position;
					    login.removeAll();
					    //注册成功

				}
				view.setInstruction(instruction);
				con.setInstruction();
				con.go();
				
			    setVisible(false);
			    jframe.setContentPane(new LoginGUI(jframe));
				
	
			}
		    
			
		}
		
		class canL implements ActionListener{
			public void actionPerformed(ActionEvent e){
				login.removeAll();
				login.initialButton();
				login.initialPanel();
			}
			
		}
	}
	

}
