package bank_management_system;
//TWELVE
//

import java.awt.Color;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    public MiniStatement(String pinnumber) {
        setTitle("Mini Statement");
        setLayout(null);

        JLabel bank = new JLabel("Kotak Mahindra Bank");
        bank.setBounds(130, 20, 200, 20);
        add(bank);

        JLabel mini = new JLabel();
//        text.setBounds(50, 50, 300, 20);
        add(mini);

//        JLabel mini = new JLabel();
//        mini.setBounds(20, 80, 100, 20);
//        add(mini);
        JLabel card = new JLabel("Kotak Mahindra Bank");
        card.setBounds(20, 80, 300, 20);
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 320, 20);
        add(balance);

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '" + pinnumber + "'");
//            ResultSet rs = conn.s.executeQuery("select * from login where pin = '4321'");
            while (rs.next()) {
                card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));

            }

        } catch (Exception e) {
            System.out.println(e);
        }
//        
        try {

            Conn conn = new Conn();
            int bal = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");

                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your current account balance is Rs "+bal);

        } catch (Exception e) {
            System.out.println(e);
        }
        mini.setBounds(20, 140, 400, 200);

    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}
