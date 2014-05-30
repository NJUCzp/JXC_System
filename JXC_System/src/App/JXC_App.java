package App;
import control.JXC_Controller;
import ui.JXC_View;


public class JXC_App {

	public static void main(String[] args) {
		JXC_View view=new JXC_View();
		JXC_Controller controller=new JXC_Controller(view);
		view.initialApp();// TODO Auto-generated method stub

	}

}
