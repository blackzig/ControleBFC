/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.dao;

import barrosfilhos.controle.conf.Conexao;
import barrosfilhos.controle.conf.TratamentoConexao;
import barrosfilhos.controle.model.PdfDae;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michel
 */
public class PdfDaeDAO {

    Connection con = null;

    public PdfDaeDAO() {
        con = Conexao.conectar();
    }

    public List<PdfDae> verificarTabelaPdfDae(PdfDae p) {
        List<PdfDae> pdfsdae = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `pdf_dae` "
                    + "where cpfDae = ? "
                    + "and apuracaoDae = ? "
                    + "and vencimentoDae = ? "
                    + "and valorDae = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getCpfDae());
            ps.setString(2, p.getApuracaoDae());
            ps.setString(3, p.getVencimentoDae());
            ps.setString(4, p.getValorDae());
            ResultSet rs = ps.executeQuery();
            pdfsdae = correrTabela(rs);
        } catch (SQLException e) {
            System.out.println("Erro loadEmpresas " + e.getMessage());
        }
        return pdfsdae;
    }

    private List<PdfDae> correrTabela(ResultSet rs) {
        List<PdfDae> pdfsdae = new ArrayList<>();
        try {
            while (rs.next()) {

                int idDae = rs.getInt("idDae");
                String cpfDae = rs.getString("cpfDae");
                String apuracaoDae = rs.getString("apuracaoDae");
                String vencimentoDae = rs.getString("vencimentoDae");
                String valorDae = rs.getString("valorDae");
                
                PdfDae p = new PdfDae();
                p.setIdDae(idDae);
                p.setCpfDae(cpfDae);
                p.setApuracaoDae(apuracaoDae);
                p.setVencimentoDae(vencimentoDae);
                p.setValorDae(valorDae);
                
                pdfsdae.add(p);

            }
        } catch (SQLException e) {
            System.out.println("Erro correrTabela pdfsdae " + e.getMessage());
        } finally {
            TratamentoConexao.fecharConexaoEResultSet(con, rs);
        }
        return pdfsdae;
    }//correr_tabela

    public boolean inserirPdfDae(PdfDae pdfdae) {
        boolean itsOK = false;
        try {
            String sql = "insert into pdf_dae ( cpfDae, apuracaoDae, "
                    + "vencimentoDae,valorDae) "
                    + "values (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pdfdae.getCpfDae());
            ps.setString(2, pdfdae.getApuracaoDae());
            ps.setString(3, pdfdae.getVencimentoDae());
            ps.setString(4, pdfdae.getValorDae());
            ps.execute();
            itsOK = true;
        } catch (SQLException e) {
            System.out.println("Erro salvar inserirPdfDae " + e.getMessage());
        } finally {
            TratamentoConexao.fecharConexao(con);
        }
        return itsOK;
    }
}
