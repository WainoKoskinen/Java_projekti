import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class Lahjalista{

    public static void main(String[] args){
        JFrame frame =new JFrame("Lahjalista");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button =new JButton("klikkaa");
        frame.add(button);

        JLabel label= new JLabel("teksti");
        frame.add(label);

        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    label.setText("painoit");
                }
            }
        );
        



        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

    }
}