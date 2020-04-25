import java.util.*;

import javax.swing.*;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		

		// Create an instance of TestFrame
		// GUIJustAClassForTesting tf = new GUIJustAClassForTesting();
		

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		// Create all the component objects
		JButton button = new JButton( "OK" );
		JButton cancel = new JButton( "Cancel" );
		JLabel label = new JLabel( "Enter your name: " );
		JTextField tfield = new JTextField( null );
		JCheckBox bold = new JCheckBox( "Bold" );
		JCheckBox italic = new JCheckBox( "italic" );
		JRadioButton red = new JRadioButton( "Red" );
		JRadioButton blue = new JRadioButton( "Blue" );
		JComboBox colors =
		 new JComboBox( new String[] {"Freshmen", "Sophomore",
		"Junior", "Senior"} );
		// Add all the components to the panel
		panel.add( button );
		panel.add( cancel );
		panel.add( label );
		panel.add( tfield );
		panel.add( bold );
		panel.add( italic );
		panel.add( red );
		panel.add( blue );
		panel.add( colors );
		// Attach the panel to the frame
		frame.add( panel );
		frame.setTitle( "Sameple GUI Components" );
		frame.setSize( 1000, 1000 );
		frame.setLocation( 200, 100 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
		
	}
	
	
	
}