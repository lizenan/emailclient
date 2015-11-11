/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailclient;

import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class Emailclient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame myframe = new emailinterface();
          myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          myframe.setSize(630,450);
          myframe.setVisible(true);// TODO code application logic here
    }
    
}
