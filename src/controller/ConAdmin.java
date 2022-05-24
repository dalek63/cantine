package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import view.admininterface;
import view.login;
import view.parentinterface;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;




public class ConAdmin implements ActionListener{



JTextField jTextFieldx;
JTextComponent jPasswordFieldx;
boolean x  = false;
boolean y = false;;

    public ConAdmin (JTextField jTextFieldx,JTextComponent jPasswordFieldx){
        this.jPasswordFieldx=jPasswordFieldx;
        this.jTextFieldx=jTextFieldx;
    }

    public void actionPerformed(ActionEvent e ){
      
     
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(config.url, config.user, config.password);
            String sql = "Select * from administrateur where LOGIN=? and  MOT_PASSE =?";
            String sql2 ="Select * from patient where LOGIN=? and  MOT_PASSE =?"; 
            PreparedStatement pst = con.prepareStatement(sql);
            PreparedStatement pst2 = con.prepareStatement(sql2);
            login.Id = jTextFieldx.getText();
            pst.setString(1,jTextFieldx.getText());  
			pst.setString(2,jPasswordFieldx.getText());
            pst2.setString(1,jTextFieldx.getText());  
			pst2.setString(2,jPasswordFieldx.getText());
            ResultSet rs = pst.executeQuery();
            ResultSet rs2= pst2.executeQuery();
            if (rs.next()){
                 x = true;
                 //y = false;
               JOptionPane.showMessageDialog(null, "Connection Admin Réussie");
               // verify if date already exist in db
               try {
                   Connection con3 = DriverManager.getConnection(config.url, config.user, config.password);
                   String sql4  = "SELECT * FROM SESSION WHERE JOUR_RESERVATION = ?";
                    PreparedStatement pst3 = con3.prepareStatement(sql4);
                    
                    ResultSet rs3 = pst3.executeQuery();

                        JOptionPane.showMessageDialog(null, "Vous avez déjà une réservation pour ce jour");
                  
                        Connection con2 = DriverManager.getConnection(config.url, config.user, config.password);
                        String sql3 = "insert into Session(JOUR_RESERVATION,HEURE,NOMBRE_PLACE) value (?,?,?)";
                            PreparedStatement pst1 = con.prepareStatement(sql3);
                        
                            login.fermer();
                            for (int i = 0 ; i< 5; i++) {
                                for (int j = 0; j< 9; j++) { 
                                    int day = 23 + i;
                                    int hour = 9 + j;
                                    int available = 1;
/*
                                        
                                        pst3.setString(1,String.valueOf(day) +"/05/2022");
                                        if(rs3.getString("JOUR_RESERVATION")!=String.valueOf(day) +"/05/2022"){
           */                             
                                        
                                         try{

                                            pst1.setString(1, String.valueOf(day) +"/05/2022");
                                            pst1.setString(2, String.valueOf(hour) + "H");
                                            pst1.setInt(3, available);
                                            pst1.executeUpdate();
                                            System.out.println("Insertion réussie");

                                            }
                                                catch(Exception ex){
                                              //  System.out.println("Erreur d'insertion" + ex);

                                            }

                                        }




                                        }
                       

                }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "");
                }
                admininterface p = new admininterface();
                p.main();

                
            }
            
            
             if (rs2.next()){
                x = true;
                // y = false;
              JOptionPane.showMessageDialog(null, login.Id);
              JOptionPane.showMessageDialog(null, "Connection Réussie");
                login.fermer();
                parentinterface p1 = new parentinterface();               
                p1.main();
                
            
             
                if(x == false && y == false  ){
                JOptionPane.showMessageDialog(null, "Id ou Mot de Passe Incorrecte");
                 jTextFieldx.setText("");
                 jPasswordFieldx.setText("");
                }
             
        
            con.close();
        
        
    

        
                }
        }
    catch(Exception ez){
                JOptionPane.showMessageDialog(null, ez);

            }
        
    
    }
}