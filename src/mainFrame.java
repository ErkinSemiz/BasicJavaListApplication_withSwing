import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class mainFrame extends JFrame {
	
	
	private int [] myarray=null;
	public mainFrame(String title) {
		super(title);
		GridBagConstraints gc = new  GridBagConstraints();
		
		
		
		setLayout(new GridBagLayout());
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		//Labels
		
		JLabel lbl_nothing = new JLabel("nothing");
		gc.gridx = 2;
		gc.gridy = 1;
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(10,10,10,10);
		JLabel lbl_ArraySize = new JLabel("The size of array");
		gc.gridx = 2;
		gc.gridy = 1;
		add(lbl_ArraySize,gc);
		
		
		JLabel lbl_EnterInt = new JLabel("Enter new integer");
		gc.gridx = 3;
		gc.gridy = 1;
		add(lbl_EnterInt,gc);

		
		JLabel lbl_SearchValue = new JLabel("Value to be search");
		gc.gridx = 2;
		gc.gridy = 5;
		add(lbl_SearchValue,gc);
		
		//Buttons
		
		JButton btn_AddInteger = new JButton("Add Integer");
		gc.gridx = 2;
		gc.gridy = 3;

		add(btn_AddInteger,gc);
		
		JButton btn_DelInteger = new JButton("Delete Integer");
		gc.gridx = 3;
		gc.gridy = 3;
	
		add(btn_DelInteger,gc);
		
		JButton btn_SortArray = new JButton("Sort Array");
		gc.gridx = 4;
		gc.gridy = 4;
		
		add(btn_SortArray,gc);
		
		JButton btn_SearchArray = new JButton("Search in array");
		gc.gridx = 4;
		gc.gridy = 5;
		
		add(btn_SearchArray,gc);
		
		//Text Fields
		
		JTextField	txtField_ArraySize = new JTextField (10);
		gc.gridx = 2;
		gc.gridy = 2;
		add(txtField_ArraySize,gc);
		txtField_ArraySize.setText("0");
		
		JTextField  txtField_EnterInt = new JTextField (10);
		gc.gridx = 3;
		gc.gridy = 2;
		add(txtField_EnterInt,gc);
		
		JTextField  txtField_SearchValue = new JTextField (10);
		gc.gridx = 3;
		gc.gridy = 5;
		add(txtField_SearchValue,gc);
		
		//Radio Buttons
		JRadioButton radioBtn_EnterManually = new JRadioButton("Enter Manually");
		JRadioButton radioBtn_GenerateRandomly = new JRadioButton("Generate Randomly");
		
		radioBtn_EnterManually.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				radioBtn_EnterManually.setSelected(true);
				radioBtn_GenerateRandomly.setSelected(false);
			}
			
		});
		radioBtn_GenerateRandomly.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				radioBtn_EnterManually.setSelected(false);
				radioBtn_GenerateRandomly.setSelected(true);
			}
			
		});
		radioBtn_EnterManually.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				JRadioButton a=(JRadioButton)arg0.getSource();
				if (a.isSelected())
					txtField_EnterInt.setEnabled(true);
					
			}
		});
		
		
		radioBtn_EnterManually.setSelected(true);
		gc.gridx = 4;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.WEST;
		add(radioBtn_EnterManually,gc);
		
		
		radioBtn_GenerateRandomly.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JRadioButton a=(JRadioButton)e.getSource();
				if (a.isSelected())
					txtField_EnterInt.setEnabled(false);
					
			}
		});
		gc.gridx = 4;
		gc.gridy = GridBagConstraints.RELATIVE;
		gc.anchor = GridBagConstraints.WEST;
		
		
		add(radioBtn_GenerateRandomly,gc);
		
	
		
		
		//List
		
		 DefaultListModel<Integer> dataModel = new DefaultListModel<>();
		 JList<Integer> list = new JList<>(dataModel);
		
		 
		
		gc.gridx = 3;
		gc.gridy = 6;
		add(list,gc);
		
		
		
		btn_AddInteger.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent arg0) {
				if(radioBtn_GenerateRandomly.isSelected())
					dataModel.addElement(ThreadLocalRandom.current().nextInt(0, 100 + 1));
				else
					dataModel.addElement(Integer.parseInt(txtField_EnterInt.getText()));
				
				txtField_ArraySize.setText(String.valueOf(Integer.parseInt(txtField_ArraySize.getText())+1));
			}
		});
	
	
	btn_DelInteger.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				int i = list.getSelectedIndex();
				if (i>=0)
				{
					dataModel.removeElementAt(i);
					txtField_ArraySize.setText(String.valueOf(Integer.parseInt(txtField_ArraySize.getText())-1));
				}
				else
					JOptionPane.showMessageDialog(null, "Delete Error! please choose the value from the list"); 
				
				
			}
			
	});
	btn_SortArray.addActionListener(new ActionListener() {
		
		
		public void actionPerformed(ActionEvent arg0) {
			Object[] options = {"Sort with bubble sort",
                    "Sort with selection sort"};
			int n = JOptionPane.showOptionDialog(null,
				    "Which sorting algorithm you want to use ? ",
				    "Sorting Algoritms",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[1]);
			int [] array=new int [list.getModel().getSize()];
			 for(int i=0;i<list.getModel().getSize();i++)
			 {
				 array[i]=list.getModel().getElementAt(i);
			 }
			if (n==0)
			{
					bubblesort.bubble_srt(array);
					for(int i=0;i<list.getModel().getSize();i++)
					{
						 dataModel.setElementAt(array[i], i);
				    }	 
			}		
			else
			{
				selectionsort selection = new selectionsort();
				array = selection.doSelectionSort(array);
				for(int i=0;i<list.getModel().getSize();i++)
				{
					 dataModel.setElementAt(array[i], i);
				}	 
			}
			myarray=array;
			txtField_ArraySize.setText(String.valueOf(array.length));
		}
		
		});
	
	btn_SearchArray.addActionListener(new ActionListener() {
		
		
		public void actionPerformed(ActionEvent arg0) {
			Object[] options = {"Search with binary search",
            "Search with linear search"};
			int n = JOptionPane.showOptionDialog(null,
			"Which sorting algorithm you want to use ? ",
		    "Sorting Algoritms",
		    JOptionPane.YES_NO_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[1]);
			 int [] array = new int [list.getModel().getSize()];
			 for(int i=0;i<list.getModel().getSize();i++)
			 {
				 array[i]=list.getModel().getElementAt(i);
			 }
			if (n==0)
			{
				if (myarray==null)
					JOptionPane.showMessageDialog(null, "Before doing the binary search, please sort the list elements");
				else
				{
					binarysearch binary = new binarysearch ();
					int returnVal=binary.recursiveBinarySearch(array, 0, array.length,Integer.parseInt(txtField_SearchValue.getText()));
					if (returnVal==-1)
						JOptionPane.showMessageDialog(null, "Binary search doesn't find the value");
					else
						JOptionPane.showMessageDialog(null, "Binary search find the value in at index "+ returnVal+ " in the list");
				}
			}
			else
			{
				linearsearch linear = new linearsearch ();
				int returnVal=linear.linerSearch(array,Integer.parseInt(txtField_SearchValue.getText()));
				if (returnVal==-1)
					JOptionPane.showMessageDialog(null, "Linear search doesn't find the value");
				else
					JOptionPane.showMessageDialog(null, "Linear search find the value in at index "+ returnVal+ " in the list");
			}
				}
		});
	
	}
}

