//Note:
//source guide galing kay gemini, pasensya na hindi po ako magaling magcode :((
// "//" di ko alam ano input/display natin jan pero just incase ano pwede ilagay ty.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CinemaReservation extends JFrame implements ActionListener {

JLabel mainTitleLabel, moviePromptLabel, namePromptLabel, chosenSeatPromptLabel, dynamicSeatLabel, screenBanner;
JTextField customerNameField;
JComboBox selectedMovieBox;
JButton btnConfirmBooking;
JTextArea receiptDisplayArea;
JScrollPane receiptScroll;

JButton[][] theaterGridButtons = new JButton[4][5];
int[][] seatReservationMatrix = new int [4][5]; 
int currentSelectedRow = -1;
int currentSelectedCol = -1;

@SuppressWarnings("empty-statement")
public CinemaReservation(){
setTitle("Cinema Ticket Reservation System");
setSize(800, 500);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setLayout(null);
getContentPane().setBackground(Color.decode("#141821"));

screenBanner = new JLabel(" CINEMA RESERVATION ", SwingConstants.CENTER);
screenBanner.setBounds(40, 20, 360, 30);
	screenBanner.setOpaque(true);
	// Title banner should be white with dark text
	screenBanner.setBackground(Color.WHITE);
	screenBanner.setForeground(Color.BLACK);
add(screenBanner);

int xCoordinate = 40;
int yCoordinate = 80;

for (int r = 0; r < 4; r++) {
for (int c = 0; c < 5; c++) {

char rowIdentifier = (char) ('A' + r);
String displayLabel = "" + rowIdentifier + (c + 1);
theaterGridButtons[r][c] = new JButton(displayLabel);
theaterGridButtons[r][c].setBounds(xCoordinate, yCoordinate, 65, 50);
theaterGridButtons[r][c].setBackground(Color.GREEN);
theaterGridButtons[r][c].addActionListener(this);

add(theaterGridButtons[r][c]);
xCoordinate += 75;
}
xCoordinate = 40;
yCoordinate += 60;
}

mainTitleLabel = new JLabel("Booking Details");
mainTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
mainTitleLabel.setForeground(Color.WHITE);
mainTitleLabel.setBounds(460, 20, 200, 30);
add(mainTitleLabel);

moviePromptLabel = new JLabel("Select Movie:");
moviePromptLabel.setBounds(460, 60, 100, 25);
add(moviePromptLabel);
moviePromptLabel.setForeground(Color.WHITE);

String[] sampleMoviesList = {
    "Avengers: Endgame",
    "Top Gun: Maverick",
    "The Batman",
    "Mission: Impossible",
    "Dead Reckoning"
};
	selectedMovieBox = new JComboBox(sampleMoviesList);
	selectedMovieBox.setBounds(570, 60, 180, 25);
	selectedMovieBox.setBackground(Color.DARK_GRAY);
	selectedMovieBox.setForeground(Color.WHITE);
	add(selectedMovieBox);

namePromptLabel = new JLabel("Customer Name:");
namePromptLabel.setBounds(460, 100, 110, 25);
add(namePromptLabel);
mainTitleLabel.setForeground(Color.WHITE);
namePromptLabel.setForeground(Color.WHITE);

	customerNameField = new JTextField();
	customerNameField.setBounds(570, 100, 180, 25);
	customerNameField.setBackground(Color.DARK_GRAY);
	customerNameField.setForeground(Color.WHITE);
	add(customerNameField);

chosenSeatPromptLabel = new JLabel("Selected Seat:");
chosenSeatPromptLabel.setBounds(460, 140, 100, 25);
add(chosenSeatPromptLabel);
chosenSeatPromptLabel.setForeground(Color.WHITE);

dynamicSeatLabel = new JLabel("None Selected");
dynamicSeatLabel.setFont(new Font("Arial", Font.BOLD, 12));
dynamicSeatLabel.setForeground(Color.RED);
dynamicSeatLabel.setBounds(570, 140, 150, 25);
add(dynamicSeatLabel);

        
btnConfirmBooking = new JButton("Confirm Reservation");
btnConfirmBooking.setBounds(460, 180, 290, 35);
btnConfirmBooking.setBackground(Color.LIGHT_GRAY);
btnConfirmBooking.addActionListener(this); 
add(btnConfirmBooking);

        
	receiptDisplayArea = new JTextArea();
	receiptDisplayArea.setEditable(false);
	receiptDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
	receiptDisplayArea.setBackground(Color.BLACK);
	receiptDisplayArea.setForeground(Color.WHITE);
    
	receiptScroll = new JScrollPane(receiptDisplayArea);
	receiptScroll.setBounds(460, 230, 290, 190);
	receiptScroll.getViewport().setBackground(Color.BLACK);
	add(receiptScroll);
}

@Override
public void actionPerformed(ActionEvent event) {

for (int r = 0; r < 4; r++) {
for (int c = 0; c < 5; c++) {
if (event.getSource() == theaterGridButtons[r][c]) {

if (seatReservationMatrix[r][c] == 1) {
JOptionPane.showMessageDialog(this, "Error: This seat is already booked!");
return;
}

currentSelectedRow = r;
currentSelectedCol = c;

char rowChar = (char) ('A' + r);
dynamicSeatLabel.setText("Row " + rowChar + " - Seat " + (c + 1));

receiptDisplayArea.setText(""); 
return; 
}
}
}

if (event.getSource() == btnConfirmBooking) {
String typedCustomerName = customerNameField.getText().trim();
String finalizedMovieString = selectedMovieBox.getSelectedItem().toString();

if (typedCustomerName.isEmpty()) {
JOptionPane.showMessageDialog(this, "Validation Failed: Please fill out the Customer Name field!");
return;
}

if (currentSelectedRow == -1 || currentSelectedCol == -1) {
JOptionPane.showMessageDialog(this, "Validation Failed: Please physically click a seat from the theater grid layout!");
return;
}

seatReservationMatrix[currentSelectedRow][currentSelectedCol] = 1;
theaterGridButtons[currentSelectedRow][currentSelectedCol].setBackground(Color.RED);

char seatLetterLabel = (char) ('A' + currentSelectedRow);
String fullSeatCode = "" + seatLetterLabel + (currentSelectedCol + 1);

receiptDisplayArea.setText(""); 
receiptDisplayArea.append("    =================================\n");
receiptDisplayArea.append("          RESERVATION CONFIRMED      \n");
receiptDisplayArea.append("    =================================\n");
receiptDisplayArea.append("     Movie:    " + finalizedMovieString + "\n");
receiptDisplayArea.append("     Customer: " + typedCustomerName + "\n");
receiptDisplayArea.append("     Seat No:  " + fullSeatCode + "\n");
receiptDisplayArea.append("     Status:   Reserved Successfully \n");
receiptDisplayArea.append("    =================================\n");

customerNameField.setText("");
dynamicSeatLabel.setText("None Selected");
currentSelectedRow = -1;
currentSelectedCol = -1;

JOptionPane.showMessageDialog(this, "Success: Ticket reservation confirmed!");
}
}

public static void main(String[] args) {
CinemaReservation appWindow = new CinemaReservation();
appWindow.setVisible(true);
}
}
