/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.conf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zigui
 */
public class TratamentoConexao {

    public static void fecharConexao(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TratamentoConexao.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void fecharConexaoEResultSet(Connection con, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TratamentoConexao.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        fecharConexao(con);
    }

}
