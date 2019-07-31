/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.dao;

import barrosfilhos.controle.conf.Conexao;
import barrosfilhos.controle.conf.TratamentoConexao;
import barrosfilhos.controle.model.PdfGps;
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
public class PdfGpsDAO {

    Connection con = null;

    public PdfGpsDAO() {
        con = Conexao.conectar();
    }

    public List<PdfGps> verificarTabelaPdfGps(PdfGps p) {
        List<PdfGps> pdfsgps = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `pdf_gps` "
                    + "where vencimentoGps = ? "
                    + "and codigoGps = ? "
                    + "and competenciaGps = ? "
                    + "and inscricaoGps = ? "
                    + "and valorTotalGps = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getVencimentoGps());
            ps.setString(2, p.getCodigoGps());
            ps.setString(3, p.getCompetenciaGps());
            ps.setString(4, p.getInscricaoGps());
            ps.setString(5, p.getValortotalGps());
            ResultSet rs = ps.executeQuery();
            pdfsgps = correrTabela(rs);
        } catch (SQLException e) {
            System.out.println("Erro loadEmpresas " + e.getMessage());
        }
        return pdfsgps;
    }

    private List<PdfGps> correrTabela(ResultSet rs) {
        List<PdfGps> pdfsgps = new ArrayList<>();
        try {
            while (rs.next()) {

                int idGps = rs.getInt("idGps");
                String vencimentoGps = rs.getString("vencimentoGps");
                String codigoGps = rs.getString("codigoGps");
                String competenciaGps = rs.getString("competenciaGps");
                String inscricaoGps = rs.getString("inscricaoGps");
                String valorInssGps = rs.getString("valorInssGps");
                String valorOutrasGps = rs.getString("valorOutrasGps");
                String valorJurosGps = rs.getString("valorJurosGps");
                String valorTotalGps = rs.getString("valorTotalGps");

                PdfGps p = new PdfGps();
                p.setIdGps(idGps);
                p.setVencimentoGps(vencimentoGps);
                p.setCodigoGps(codigoGps);
                p.setCompetenciaGps(competenciaGps);
                p.setInscricaoGps(inscricaoGps);
                p.setValorInssGPS(valorInssGps);
                p.setValorOutrasGps(valorOutrasGps);
                p.setValorJurosGps(valorJurosGps);
                p.setValortotalGps(valorTotalGps);
                pdfsgps.add(p);

            }
        } catch (SQLException e) {
            System.out.println("Erro correrTabela pdfsgps " + e.getMessage());
        } finally {
            TratamentoConexao.fecharConexaoEResultSet(con, rs);
        }
        return pdfsgps;
    }//correr_tabela

    public boolean inserirPdfGps(PdfGps pdfgps) {
        boolean itsOK = false;
        try {
            String sql = "insert into pdf_gps ( vencimentoGps, codigoGps, "
                    + "competenciaGps, inscricaoGps, valorInssGps, valorOutrasGps,"
                    + "valorJurosGps,valorTotalGps) "
                    + "values (?, ?, ?, ?,?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pdfgps.getVencimentoGps());
            ps.setString(2, pdfgps.getCodigoGps());
            ps.setString(3, pdfgps.getCompetenciaGps());
            ps.setString(4, pdfgps.getInscricaoGps());
            ps.setString(5, pdfgps.getValorInssGPS());
            if(pdfgps.getValorOutrasGps()!=null){
            ps.setString(6, pdfgps.getValorOutrasGps());    
            }else{
             ps.setString(6,null);
            }
            if(pdfgps.getValorOutrasGps()!=null){
            ps.setString(7, pdfgps.getValorJurosGps());    
            }else{
             ps.setString(7,null);
            }
            ps.setString(8, pdfgps.getValortotalGps());
            ps.execute();
            itsOK = true;
        } catch (SQLException e) {
            System.out.println("Erro salvar inserirPdfDARF " + e.getMessage());
        } finally {
            TratamentoConexao.fecharConexao(con);
        }
        return itsOK;
    }
}
