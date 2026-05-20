import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
double standardTicketCost = //price//;

public CinemaReservation(){
setTitle("Cinema Ticket Reservation System");
setSize(800, 500);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setLayout(null);

screenBanner = new JLabel(" MOVIE SCREEN ", SwingConstants.CENTER);
screebBanner.setBounds(40, 20, 360, 30);
screenBanner.setOpaque(true);
screenBanner.setBackground(Color.GRAY);
screenBanner.setForeground(Color.WHITE);
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
