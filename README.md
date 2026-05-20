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

public CinemaReservation()
