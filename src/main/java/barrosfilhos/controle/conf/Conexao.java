/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class Conexao {

    static Connection con = null;

    public static Connection conectar() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://10.1.1.4/ControleBFC", "root", "bfcont@7890");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }

}
