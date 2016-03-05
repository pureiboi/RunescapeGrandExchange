import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class AutoSuggest extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4911783909484639380L;
	private final JTextField tf;
	private final JComboBox<?> combo = new JComboBox<>();
	private final Vector<String> v = new Vector<String>();
	
	private String[] suggestList;

	public AutoSuggest() throws Exception{
		super(new BorderLayout());
		combo.setEditable(true);
		tf = (JTextField) combo.getEditor().getEditorComponent();
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public void run() {
						String text = tf.getText();
						try {
							List<Item> itemlist = DataBase.createSearchObject(text);
							List<String> stringList = StringUtil.objectListToStringList(itemlist, Item.STRING_NAME);
							suggestList = (stringList.toArray(new String [] {}));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(suggestList!=null && suggestList.length>0)
						{
							for (int i = 0; i < suggestList.length; i++) {
								v.addElement(suggestList[i]);
							}
						}
						setModel(new DefaultComboBoxModel(v), "");
						if (text.length() == 0) {
							combo.hidePopup();
							setModel(new DefaultComboBoxModel(v), "");
						} else {
							DefaultComboBoxModel<?> m = getSuggestedModel(v, text);
							if (m.getSize() == 0 || hide_flag) {
								combo.hidePopup();
								hide_flag = false;
							} else {
								setModel(m, text);
								combo.showPopup();
							}
						}
					}
				});
			}

			public void keyPressed(KeyEvent e) {
				String text = tf.getText();
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ENTER) {
					if (!v.contains(text)) {
						v.addElement(text);
						Collections.sort(v);
						setModel(getSuggestedModel(v, text), text);
					}
					hide_flag = true;
				} else if (code == KeyEvent.VK_ESCAPE) {
					hide_flag = true;
				} else if (code == KeyEvent.VK_RIGHT) {
					for (int i = 0; i < v.size(); i++) {
						String str = v.elementAt(i);
						if (str.startsWith(text)) {
							combo.setSelectedIndex(-1);
							tf.setText(str);
							return;
						}
					}
				}
			}
		});
		/*
		String[] countries = { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Argentina", "Armenia",
				"Austria", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Benin", "Bhutan",
				"Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "Bulgaria", "Burkina Faso", "Burma", "Burundi",
				"Cambodia", "Cameroon", "Canada", "China", "Colombia", "Comoros", "Congo", "Croatia", "Cuba", "Cyprus",
				"Czech Republic", "Denmark", "Georgia", "Germany", "Ghana", "Great Britain", "Greece", "Hungary",
				"Holland", "India", "Iran", "Iraq", "Italy", "Somalia", "Spain", "Sri Lanka", "Sudan", "Suriname",
				"Swaziland", "Sweden", "Switzerland", "Syria", "Uganda", "Ukraine", "United Arab Emirates",
				"United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen",
				"Zaire", "Zambia", "Zimbabwe" };
				*/
		if(suggestList!=null && suggestList.length>0)
		{
			for (int i = 0; i < suggestList.length; i++) {
				v.addElement(suggestList[i]);
			}
		}
		setModel(new DefaultComboBoxModel<>(v), "");
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createTitledBorder("AutoSuggestion Box"));
		p.add(combo, BorderLayout.NORTH);
		add(p);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(300, 150));
	}

	private boolean hide_flag = false;

	private void setModel(DefaultComboBoxModel mdl, String str) {
		combo.setModel(mdl);
		combo.setSelectedIndex(-1);
		tf.setText(str);
	}

	private static DefaultComboBoxModel<?> getSuggestedModel(List<String> stringList, String text) {
		DefaultComboBoxModel<String> comboBox = new DefaultComboBoxModel<>();
		for (String str : stringList) {
			if (str.toLowerCase().contains(text.toLowerCase()))
				comboBox.addElement(str);
		}
		return comboBox;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("start testing");
		//List<Item> itemlist = DataBase.createSearchObject("pie");
		//List<String> stringList = StringUtil.objectToStringList(itemlist, Item.STRING_NAME);
		System.out.println("ASD");
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new AutoSuggest());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}