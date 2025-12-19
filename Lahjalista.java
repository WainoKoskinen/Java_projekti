import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Lahjalista {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gift list");

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

    
        JPanel ItemInput = new JPanel();
        ItemInput.setLayout(new GridLayout(2, 2)); 

       
        JTextField tuote = new JTextField();
        JTextField hintaKentta = new JTextField();
        JTextField Username = new JTextField();
        JTextField kategoria = new JTextField();

        //input boxes down
        ItemInput.add(new JLabel("Price (€):"));
        ItemInput.add(new JLabel("Gift name:"));
        ItemInput.add(new JLabel("Saaja:"));
        ItemInput.add(new JLabel("kategoria:"));
        ItemInput.add(tuote);
        ItemInput.add(hintaKentta);
        ItemInput.add(Username);
        ItemInput.add(kategoria);

       
        
        JPanel nappiPaneeli = new JPanel(new FlowLayout());
        nappiPaneeli.add(new JButton("Save"));
        nappiPaneeli.add(new JButton("Load"));

    
        JPanel under = new JPanel();
        under.setLayout(new BorderLayout());

        under.add(ItemInput, BorderLayout.CENTER); 
        under.add(nappiPaneeli, BorderLayout.SOUTH); 

     
        frame.add(under, BorderLayout.SOUTH);

        
        frame.setVisible(true);
    }

        public class lahja{

     //attributes
        private double Price;
        private String Gift;
        private String receiver;
        private String gatecory;
        //constructor
        public lahja(double Price, String Gift, String receiver, String gatecory){
            this.Price =Price;
            this.Gift =Gift;
            this.receiver = receiver;
            this.gatecory = gatecory;
        }

        public double getPrice(){
            return Price;
        }
        public String getGift(){
            return Gift;
        }
        public String getreceiver(){
            return receiver;
        }
        public String getgatecory(){
            return gatecory;
        }

    }
}