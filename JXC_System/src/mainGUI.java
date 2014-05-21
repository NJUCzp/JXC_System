
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;



import javax.swing.*;

public class mainGUI extends JPanel{
	private mainFrame jframe;
	String position="";
	JXC_View view=new JXC_View();
	JXC_Controller con=new JXC_Controller(view);
	Helper helper=new Helper();
	private JButton[] buttons;
	private JLabel infoLb=new JLabel();
	private Point[] buttonPoints=new Point[]{new Point(757,142),new Point(632,334),new Point(20,20),new Point(20,200),new Point(20,400),new Point(300,200),new Point(300,400),new Point(300,600)};
	public ImageIcon[] LOGIN_BUTTONS=new ImageIcon[]{new ImageIcon("graphics/01.png"),new ImageIcon ("graphics/02.png"),new ImageIcon("graphics/春分.png"),new ImageIcon("graphics/客户管理.png"),new ImageIcon("graphics/库存管理.png"),new ImageIcon("graphics/进货管理.png"),new ImageIcon("graphics/销售管理.png"),new ImageIcon("graphics/账目管理.png")};
	public ImageIcon[] LOGIN_BUTTONS_ENTERED=new ImageIcon[]{new ImageIcon("graphics/03.png"),new ImageIcon ("graphics/04.png"),new ImageIcon("graphics/春分.png"),new ImageIcon("graphics/客户管理.png"),new ImageIcon("graphics/库存管理2.png"),new ImageIcon("graphics/进货管理2.png"),new ImageIcon("graphics/销售管理2.png"),new ImageIcon("graphics/账目管理2.png")};
	public Image bg=new ImageIcon("graphics/background_1.png").getImage();
	public Image[] LOGIN_BUTTON=new Image[]{new ImageIcon("graphics/01.png").getImage(),new ImageIcon ("graphics/02.png").getImage(),new ImageIcon("graphics/春分.png").getImage(),new ImageIcon("graphics/客户管理.png").getImage(),new ImageIcon("graphics/库存管理.png").getImage(),new ImageIcon("graphics/进货管理.png").getImage(),new ImageIcon("graphics/销售管理.png").getImage(),new ImageIcon("graphics/销售管理.png").getImage()};
	public mainGUI(mainFrame jframe){
		this.jframe=jframe;
		this.buttons=new JButton[]{new JButton(LOGIN_BUTTONS[0]),new JButton(LOGIN_BUTTONS[1]),new JButton(LOGIN_BUTTONS[2]),new JButton(LOGIN_BUTTONS[3]),new JButton(LOGIN_BUTTONS[4]),new JButton(LOGIN_BUTTONS[5]),new JButton(LOGIN_BUTTONS[6]),new JButton(LOGIN_BUTTONS[7])};
		jframe.setDragable(this);
		this.setBounds(0, 0, jframe.FRAME_WIDTH, jframe.FRAME_HEIGHT);
		this.setLayout(null);
		helper.setFilename("data/current.txt");
		helper.split(helper.readfile().get(0));
		position=helper.sArray[1];
		infoLb.setText("Welcome!"+helper.sArray[1]+" "+helper.sArray[0]);
		infoLb.setBounds(700,500,300,20);
		this.add(infoLb);
		buttonInitial();
	}
	private void buttonInitial() {
		for(int i=0;i<buttons.length;i++){
			
			
			buttons[i].setBorderPainted(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBounds(buttonPoints[i].x, buttonPoints[i].y,LOGIN_BUTTONS[i].getImage().getWidth(null),LOGIN_BUTTONS[i].getImage().getHeight(null));
			//buttons[2].setBounds(20,20,200,100);
			buttonMouseAdapterAndActionListener buttonMouseAdapterAndActionListener=new buttonMouseAdapterAndActionListener(i,this);
   			if(buttons[i].getMouseListeners().length<2){
			buttons[i].addMouseListener(buttonMouseAdapterAndActionListener);
			}
			if(buttons[i].getActionListeners().length<1){
			buttons[i].addActionListener(buttonMouseAdapterAndActionListener);
			}
			this.add(buttons[i]);
		}
		
	}
	
	public void paintComponent(Graphics g){
		 g.drawImage(bg, 0, 0, 1024, 768, null);
		 for(int i=0;i<buttons.length;i++){
			 g.drawImage(LOGIN_BUTTON[i], buttonPoints[i].x, buttonPoints[i].y, LOGIN_BUTTONS[i].getImage().getWidth(null), LOGIN_BUTTONS[i].getImage().getHeight(null), null);
		 }
	}
	
	
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{
		int i;
		mainGUI main;
		private buttonMouseAdapterAndActionListener(int i, mainGUI main){
			this.i=i;
			this.main=main;
			
		}
		public void mouseEntered(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS_ENTERED[i]);
			
		}
		public void mouseExited(MouseEvent e){
			buttons[i].setIcon(LOGIN_BUTTONS[i]);
			
		}
		public void actionPerformed(ActionEvent e) {
		if(i==0){
			//最小化
			jframe.setExtendedState(jframe.ICONIFIED); 
			//System.out.println(1);
			return;
		}
		if(i==1){
			//关闭
			System.exit(0);
			//System.out.println(0);
			return;
		}
		if(i==2&&position.equals("stockManager")){
			setVisible(false);
			jframe.setContentPane(new commodityGUI(jframe));
		}else{}
		if(i==3&&position.equals("salesman")){
			setVisible(false);
			jframe.setContentPane(new customerGUI(jframe));
		}else{}
	    if(i==4&&position.equals("stockManager")){
			setVisible(false);
			jframe.setContentPane(new stockGUI(jframe));
		}else{}
	    if(i==5&&position.equals("salesman")){
	    	setVisible(false);
			jframe.setContentPane(new importGUI(jframe));
	    }else{}
	    if(i==6&&position.equals("salesman")){
	    	setVisible(false);
			jframe.setContentPane(new exportGUI(jframe));
	    }else{}
	    if(i==7&&position.equals("account")){
	    	setVisible(false);
			jframe.setContentPane(new accountGUI(jframe));
	    }else{}
		}
	}
	

	
}

