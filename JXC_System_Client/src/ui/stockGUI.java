package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class stockGUI extends JPanel{
	mainFrame jframe;
	JXC_View view;
	//JXC_Controller con=new JXC_Controller(view);
	ImageIcon OPTION=new ImageIcon("graphics/confirm1.png");
	ImageIcon OPTION_ENTERED=new ImageIcon("graphics/confirm2.png");
	Image bg=new ImageIcon("graphics/option_background.png").getImage();
	JButton button=new JButton(new ImageIcon("graphics/confirm1.png"));
	Point point=new Point(412,600);
	
	public stockGUI(JXC_View view,mainFrame jframe){
		this.view=view;
		this.jframe=jframe;
		this.setLayout(null);
		this.setVisible(true);
		this.setBounds(0, 0, jframe.FRAME_WIDTH, jframe.FRAME_HEIGHT);
		jframe.setDragable(this);
		initialButton();	
		view.setInstruction("STOCK_SHO");
		addTable();
	}
	public void paintComponent(Graphics g){
		 //super.paintComponent(g);
		 g.drawImage(bg, 0, 0, 1024, 768, null);
		 /*for(int i=0;i<buttons.length;i++){
			 g.drawImage(LOGIN_BUTTON[i], points[i].x, points[i].y, LOGIN_BUTTONS[i].getImage().getWidth(null), LOGIN_BUTTONS[i].getImage().getHeight(null), null);
		 }*/
	}
	
	public void initialButton(){
		button.setBorderPainted(false);
		button.setBounds(point.x, point.y, OPTION.getImage().getWidth(null), OPTION.getImage().getHeight(null));
		buttonMouseAdapterAndActionListener buttonMouseAdapterAndActionListener=new buttonMouseAdapterAndActionListener(this);
		if(button.getMouseListeners().length<2){
			button.addMouseListener(buttonMouseAdapterAndActionListener);
			}
		if(button.getActionListeners().length<1){
			button.addActionListener(buttonMouseAdapterAndActionListener);
			}
		this.add(button);
		jframe.setContentPane(this);


	}
	
	public void addTable(){
	     String[] colomn={"商品名称","商品型号","进货数量","进货平均单价","进货总价","销售数量","销售单价","销售总价","库存数量","库存平均单价","库存总价"};

	     DefaultTableModel tablem=new DefaultTableModel(view.receiveMessageTable(),colomn){
	    	 public boolean isCellEditable(int row,int colomn) {
	    	     return false;
	    	    }
	     };
	     JTable table=new JTable();
	     table.setModel(tablem);
	
         JScrollPane scrollPane=new JScrollPane(table);
         scrollPane.setBounds(100, 200, 850, 300);
	     this.add(scrollPane);
	     jframe.setContentPane(this);
	}
	class buttonMouseAdapterAndActionListener extends MouseAdapter implements ActionListener{

		stockGUI stock;
		private buttonMouseAdapterAndActionListener(stockGUI stock){
			this.stock=stock;
		}
		
		public void mouseEntered(MouseEvent e){
			button.setIcon(OPTION_ENTERED);
			
		}
		public void mouseExited(MouseEvent e){
			button.setIcon(OPTION);
			
		}
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			jframe.setContentPane(new mainGUI(view,jframe));
			// TODO Auto-generated method stub
			
		}

		
		
	}

}
