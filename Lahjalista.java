import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Lahjalista {
    // Lista johon lahja-oliot tallennetaan
    static ArrayList<Lahja> lahjat = new ArrayList<>();
    static double kokonaisHinta = 0;

    public static void main(String[] args) {
        // Swingin käynnistys oikeassa säikeessä
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Pääikkunan luonti
        JFrame frame = new JFrame("Gift list");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Taulukon luonti
        String[] columnNames = {"Gift Name", "Price (€)", "Receiver", "Category"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override // Estetään solujen muokkaus suoraan taulukossa
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input-kentät
        JPanel ItemInput = new JPanel();
        ItemInput.setLayout(new GridLayout(4, 2, 10, 10)); 
        ItemInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    
        JTextField tuote = new JTextField();
        JTextField hintaKentta = new JTextField();
        
        // lista nimistä ja asetetaan se (pudotusvalikkoon)
        String[] nimet = {"Wäinö", "Leevi", "Janne", "Inna"};
        JComboBox<String> saajaValikko = new JComboBox<>(nimet);
        
        JTextField kategoria = new JTextField();
       

        ItemInput.add(new JLabel("Gift name:"));
        ItemInput.add(tuote);
        ItemInput.add(new JLabel("Price (€):"));
        ItemInput.add(hintaKentta);
        ItemInput.add(new JLabel("Gift receiver:"));
        ItemInput.add(saajaValikko); // Lisätään valikko paneeliin tekstikentän sijaan
        ItemInput.add(new JLabel("Category:"));
        ItemInput.add(kategoria);

        // Napit
        JPanel nappiPaneeli = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add");
        JButton saveButton = new JButton("Save to File");
        
        JLabel totalLabel = new JLabel("Total: 0.0 €");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        nappiPaneeli.add(addButton);
        nappiPaneeli.add(saveButton);
        nappiPaneeli.add(totalLabel);

        JPanel under = new JPanel();
        under.setLayout(new BorderLayout());

        under.add(ItemInput, BorderLayout.CENTER);
        under.add(nappiPaneeli, BorderLayout.SOUTH);
        
        frame.add(under, BorderLayout.SOUTH);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nimi = tuote.getText();
                    String priceText = hintaKentta.getText().replace(",", "."); 
                    
                    if(nimi.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a gift name.", "Input Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    double hinta = Double.parseDouble(priceText);
                    
                    
                    String receiver = (String) saajaValikko.getSelectedItem();
                    
                    String category = kategoria.getText();

                    Lahja uusiLahja = new Lahja(hinta, nimi, receiver, category);
                    lahjat.add(uusiLahja);

                    model.addRow(new Object[]{nimi, hinta, receiver, category});

                    kokonaisHinta += hinta;
                    totalLabel.setText(String.format("Total: %.2f €", kokonaisHinta));

                    // Tyhjennetään kentät
                    tuote.setText("");
                    hintaKentta.setText("");
                    kategoria.setText("");
                    // Palautetaan valinta ensimmäiseen nimeen (Wäinö)
                    saajaValikko.setSelectedIndex(0);
                    
                    tuote.requestFocus();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Price! Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tallennus-napin toiminta
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileWriter writer = new FileWriter("lahjat.txt")) {
                    for (Lahja l : lahjat) {
                        writer.write(l.toString() + "\n");
                    }
                    JOptionPane.showMessageDialog(frame, "Saved to lahjat.txt!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    // Luokka
    public static class Lahja {
        private double price;
        private String gift;
        private String receiver;
        private String category;

        public Lahja(double price, String gift, String receiver, String category) {
            this.price = price;
            this.gift = gift;
            this.receiver = receiver;
            this.category = category;
        }

        public double getPrice() { 
            return price; }
        public String getGift() { 
            return gift; }
        public String getReceiver() { 
            return receiver; }
        public String getCategory() {
             return category; }

        @Override
        public String toString() {
            return gift + "," + price + "," + receiver + "," + category;
        }
    }
}