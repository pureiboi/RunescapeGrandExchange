/**
 * 
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import rs.ui.RoundBorder;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class mainUi {

	private final long buttonWidth = 80;
	private final long buttonHeight = this.buttonWidth / 4;
	
	private final int textBoxWidth = 120;
	private final int textBoxHeight = 25;
	private final int labelWidth = 80;
	private final int labelHeight = 25;
	
	private final int borderSize = 10;
	
	private final int minWindowWidth = 450;
	private final int minWindowHeight = 600; 
	
	
	private JTextField txtBxBSItemName;
	private JTextField txtBxBSPrice;
	private JTextField txtBxBSQuantity;
	private JTextField txtBxSSitemName;
	private JTextField txtBxSSPrice;
	private JTextField txtBxSSQuantity;
	private JTextField txtBxProfitLoss;
	private JTextArea sysConsole;
	
	//main window
	private JFrame frmRunescapeGrandexchange;
	//container for all components
	private JSplitPane splitPaneMainHolder;
	//container holding all dynamic components 
	private List<Component> buyComponentList;
	private List<Component> sellComponentList;
	
	private static List<Item> itemList = new ArrayList<Item>();  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println("write file test begin");
		//List<Item> itemlist = DataBase.createSearchObject("pie");
		//List<String> item = StringUtil.objectListToStringList(itemlist, Item.STRING_NAME);
		System.out.println("ASD");
		//for(Item s : itemlist)
		//	s.displayItem();
		
        /*
		System.out.println("working path " + System.getProperty("user.name") );
		System.out.println("working path " + System.getProperty("user.home") );
		System.out.println("working path " + System.getProperty("user.dir") );
		System.out.println("working path " + System.getProperty("os.version") );
		System.out.println("working path " + System.getProperty("os.name") );
		System.out.println("working path " + System.getProperty("os.arch") );
		System.out.println("size of args " + args.length);
		
		DataBase.loadFromFile(itemList);
		
		for (String s : args)
		{
			System.out.println(s);
			
			
		}
		
		*/
		/*
		PrintWriter writer;
		try {
			writer = new PrintWriter("printwriter.txt", "UTF-8");
			writer.println("The first line");
			writer.println("The second line");
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<String> lines = Arrays.asList("The first line", "The second line");
		Path file = Paths.get("pathFile.txt");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		
		System.out.println("write file test end");
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUi window = new mainUi();
					window.frmRunescapeGrandexchange.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public mainUi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void calculateProfitLoss()
	{
		long buyingPrice = 0;
		long sellingPrice = 0;
		
		buyingPrice = Long.parseLong(this.txtBxBSPrice.getText()) * Long.parseLong(this.txtBxBSQuantity.getText());
		sellingPrice = Long.parseLong(this.txtBxSSPrice.getText()) * Long.parseLong(this.txtBxSSQuantity.getText());
		long result = sellingPrice - buyingPrice;
		this.txtBxProfitLoss.setText(String.valueOf(result));
	}
	
	
	private void initialize() {
		buyComponentList = new ArrayList<>();
		sellComponentList = new ArrayList<>();
		frmRunescapeGrandexchange = new JFrame();
		//frmRunescapeGrandexchange.setResizable(false);
		frmRunescapeGrandexchange.setTitle("Runescape GrandExchange");
		//center the window
		//frmRunescapeGrandexchange.setLocationRelativeTo(null);
		frmRunescapeGrandexchange.setBounds(600, 200, minWindowWidth, minWindowHeight);
		//frmRunescapeGrandexchange.setSize(minWindowWidth, minWindowHeight);
		frmRunescapeGrandexchange.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRunescapeGrandexchange.getContentPane().setLayout(null);
		frmRunescapeGrandexchange.setMinimumSize(new Dimension(minWindowWidth, minWindowHeight));
		
		frmRunescapeGrandexchange.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				int currWidth = e.getComponent().getWidth();
				int currHeight = e.getComponent().getHeight();
				if(currWidth<=minWindowWidth || currHeight<=minWindowHeight)
				{
					//e.getComponent().setSize(minWindowWidth, minWindowHeight);
				}
				System.out.println(e.getComponent().getWidth());
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		splitPaneMainHolder = new JSplitPane();
		splitPaneMainHolder.setEnabled(false);
		splitPaneMainHolder.setResizeWeight(0.5);
		splitPaneMainHolder.setRequestFocusEnabled(false);
		splitPaneMainHolder.setBounds(10, 11, 417, 133);
		frmRunescapeGrandexchange.getContentPane().add(splitPaneMainHolder);
		
		
		JPanel pnlBuySection = new JPanel();
		pnlBuySection.setBorder(new TitledBorder(null, "Buy Section", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPaneMainHolder.setLeftComponent(pnlBuySection);
		pnlBuySection.setLayout(null);
		
		JLabel lblBSimg = new JLabel("");
		lblBSimg.setBounds(6, 16, 96, 27);
		pnlBuySection.add(lblBSimg);
		
		JLabel lblBSSpace = new JLabel("");
		lblBSSpace.setBounds(102, 16, 96, 27);
		pnlBuySection.add(lblBSSpace);
		
		JLabel lblBSitemName = new JLabel("Item Name");
		lblBSitemName.setBounds(6, 43, 96, 27);
		pnlBuySection.add(lblBSitemName);
		
		txtBxBSItemName = new JTextField();
		txtBxBSItemName.setBounds(102, 43, 96, 27);
		txtBxBSItemName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		pnlBuySection.add(txtBxBSItemName);
		txtBxBSItemName.setColumns(10);
		
		JLabel lblVSPrice = new JLabel("Price $");
		lblVSPrice.setBounds(6, 70, 96, 27);
		pnlBuySection.add(lblVSPrice);
		
		txtBxBSPrice = new JTextField();
		txtBxBSPrice.setBounds(102, 70, 96, 27);
		txtBxBSPrice.setEditable(false);
		pnlBuySection.add(txtBxBSPrice);
		txtBxBSPrice.setColumns(10);
		
		JLabel lblBSQuantity = new JLabel("Quantity");
		lblBSQuantity.setBounds(6, 97, 96, 27);
		pnlBuySection.add(lblBSQuantity);
		
		txtBxBSQuantity = new JTextField();
		txtBxBSQuantity.setBounds(102, 97, 96, 27);
		pnlBuySection.add(txtBxBSQuantity);
		txtBxBSQuantity.setColumns(10);
		
		JPanel pnlSellSection = new JPanel();
		pnlSellSection.setBorder(new TitledBorder(null, "Sell Section", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPaneMainHolder.setRightComponent(pnlSellSection);
		pnlSellSection.setLayout(null);
		
		JLabel lblSSimg = new JLabel("");
		lblSSimg.setBounds(6, 16, 142, 27);
		pnlSellSection.add(lblSSimg);
		
		JLabel lblSSSpace = new JLabel("");
		lblSSSpace.setBounds(148, 16, 142, 27);
		pnlSellSection.add(lblSSSpace);
		
		JLabel lblSSitemName = new JLabel("Item Name");
		lblSSitemName.setBounds(6, 43, 142, 27);
		pnlSellSection.add(lblSSitemName);
		
		txtBxSSitemName = new JTextField();
		txtBxSSitemName.setBounds(148, 43, 142, 27);
		pnlSellSection.add(txtBxSSitemName);
		txtBxSSitemName.setColumns(10);
		
		JLabel lblSSPrice = new JLabel("Price $");
		lblSSPrice.setBounds(6, 70, 142, 27);
		pnlSellSection.add(lblSSPrice);
		
		txtBxSSPrice = new JTextField();
		txtBxSSPrice.setBounds(148, 70, 142, 27);
		txtBxSSPrice.setEditable(false);
		pnlSellSection.add(txtBxSSPrice);
		txtBxSSPrice.setColumns(10);
		
		JLabel lblSSQuantity = new JLabel("Quantity");
		lblSSQuantity.setBounds(6, 97, 142, 27);
		pnlSellSection.add(lblSSQuantity);
		
		txtBxSSQuantity = new JTextField();
		txtBxSSQuantity.setBounds(148, 97, 142, 27);
		pnlSellSection.add(txtBxSSQuantity);
		txtBxSSQuantity.setColumns(10);
		
		sysConsole = new JTextArea();
		sysConsole.setBackground(Color.WHITE);
		sysConsole.setBorder(new TitledBorder(null, "System", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sysConsole.setBounds(0, 260, minWindowWidth-borderSize*2, 133);
		System.out.println(sysConsole.getBounds().getWidth());
		frmRunescapeGrandexchange.getContentPane().add(sysConsole);
		
		JPanel pnlResult = new JPanel();
		pnlResult.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlResult.setBounds(10, 202, 407, 47);
		frmRunescapeGrandexchange.getContentPane().add(pnlResult);
		
		JLabel lblCalculate = new JLabel("Profit or Loss $");
		pnlResult.add(lblCalculate);
		
		txtBxProfitLoss = new JTextField();
		lblCalculate.setLabelFor(txtBxProfitLoss);
		pnlResult.add(txtBxProfitLoss);
		txtBxProfitLoss.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setActionCommand("New button");
		pnlResult.add(btnReset);
		
		JButton btnCalculate = new JButton("Calculate");

		
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculateProfitLoss();
			}
		});
		
		pnlResult.add(btnCalculate);
		
		Button button = new Button((String) null);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		button.setFont(new Font("Arial Black", Font.PLAIN, 30));
		button.setForeground(Color.DARK_GRAY);
		button.setBackground(Color.GREEN);
		button.setBounds(236, 163, 25, 21);

		//button.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		frmRunescapeGrandexchange.getContentPane().add(button);
		
		JButton btnNewButton = new JButton("making");
		btnNewButton.setBackground(Color.green);
		btnNewButton.setBounds(84, 159, 50, 50);
		btnNewButton.setBorder(new Border() {
			
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				Rectangle rec = c.getBounds();
				int currWidth = (int)rec.getWidth();
				int currHeight = (int)rec.getHeight();
				g.drawRoundRect(0, 0, currWidth-1, currHeight-1, currWidth, currHeight);
			}
			
			@Override
			public boolean isBorderOpaque() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Insets getBorderInsets(Component c) {
				// TODO Auto-generated method stub
				Rectangle rec = c.getBounds();
				int currWidth = (int)rec.getWidth();
				int currHeight = (int)rec.getHeight();
				return new Insets(currHeight,currWidth,currHeight,currWidth);
			}
		});
		
		

		frmRunescapeGrandexchange.getContentPane().add(btnNewButton);
		
		JButton butn = new JButton("bb");
		butn.setBackground(Color.GRAY);
		butn.setBounds(0, 159, 50, 50);
		butn.setBorder(new RoundBorder());
		
		btnNewButton.setBackground(Color.red);

		
		frmRunescapeGrandexchange.getContentPane().add(butn);
		
		Button button_1 = new Button("New button");
		button_1.setBounds(319, 163, 70, 22);
		frmRunescapeGrandexchange.getContentPane().add(button_1);
	}
	
	public void componentResized(ComponentEvent e) {
        System.out.println(e.getComponent().getClass().getName() + " --- Resized ");            
    }
}
