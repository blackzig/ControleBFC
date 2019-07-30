///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package barrosfilhos.controle.dao;
//
//import barrosfilhos.controle.conf.Conexao;
//import barrosfilhos.controle.conf.TratamentoConexao;
//import barrosfilhos.controle.model.PdfFgts;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author Michel
// */
//public class PdfFgtsDAO {
//
//    Connection con = null;
//
//    public PdfFgtsDAO() {
//        con = Conexao.conectar();
//    }
//
//    public List<PdfFgts> verificarTabelaFgts(PdfFgts p) {
//        List<PdfFgts> pdfsfgts = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM `pdf_fgts` "
//                    + "where remuneracaoFgts = ? "
//                    + "and trabalhadoresFgts = ? "
//                    + "and inscricaoFgts = ? "
//                    + "and competenciaFgts = ?"
//                    + "and validadeFgts = ? "
//                    + "and totalFgts = ? "
//                    + "and dtGeracaoFgts = ? ";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, p.getRemuneracaoFgts());
//            ps.setString(2, p.getTrabalhadoresFgts());
//            ps.setString(3, p.getInscricaoFgts());
//            ps.setString(4, p.getCompetenciaFgts());
//            ps.setString(5, p.getValidadeFgts());
//            ps.setString(6, p.getTotalFgts());
//            ps.setString(7, p.getDtGeracaoFgts());
//            ResultSet rs = ps.executeQuery();
//            pdfsfgts = correrTabela(rs);
//        } catch (SQLException e) {
//            System.out.println("Erro loadEmpresas " + e.getMessage());
//        }
//        return pdfsfgts;
//    }
//
//    private List<PdfFgts> correrTabela(ResultSet rs) {
//        List<PdfFgts> pdfsfgts = new ArrayList<>();
//        try {
//            while (rs.next()) {
//
//                int idFgts = rs.getInt("idFgts");
//                String fpasFgts = rs.getString("fpasFgts");
//                String simplesFgts = rs.getString("simplesFgts");
//                String remuneracaoFgts = rs.getString("remuneracaoFgts");
//                String trabalhadoresFgts = rs.getString("trabalhadoresFgts");
//                String aliquotafgts = rs.getString("aliquotafgts");
//                String codRecolhiFgts = rs.getString("codRecolhiFgts");
//                String idRecolhiFgts = rs.getString("idRecolhiFgts");
//                String inscricaoFgts = rs.getString("inscricaoFgts");
//                String competenciaFgts = rs.getString("competenciaFgts");
//                String validadeFgts = rs.getString("validadeFgts");
//                String depositoFgts = rs.getString("depositoFgts");
//                String encargosFgts = rs.getString("encargosFgts");
//                String totalFgts = rs.getString("totalFgts");
//                String dtGeracaoFgts = rs.getString("dtGeracaoFgts");
//                
//
//                PdfFgts p = new PdfFgts();
//                p.setIdFgts(idFgts);
//                p.setFpasFgts(fpasFgts);
//                p.setSimplesFgts(simplesFgts);
//                p.setRemuneracaoFgts(remuneracaoFgts);
//                p.setTrabalhadoresFgts(trabalhadoresFgts);
//                p.setAliquotafgts(aliquotafgts);
//                p.setCodRecolhiFgts(codRecolhiFgts);
//                p.setIdRecolhiFgts(idRecolhiFgts);
//                p.setInscricaoFgts(inscricaoFgts);
//                p.setCompetenciaFgts(competenciaFgts);
//                p.setValidadeFgts(validadeFgts);
//                p.setDepositoFgts(depositoFgts);
//                p.setEncargosFgts(encargosFgts);
//                p.setTotalFgts(totalFgts);
//                p.setDtGeracaoFgts(dtGeracaoFgts);
//
//                pdfsfgts.add(p);
//
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro correrTabela pdfsfgts " + e.getMessage());
//        } finally {
//            TratamentoConexao.fecharConexaoEResultSet(con, rs);
//        }
//        return pdfsfgts;
//    }//correr_tabela
//
//    public boolean inserirPdfFgts(PdfFgts pdffgts) {
//        boolean itsOK = false;
//        try {
//            String sql = "insert into pdf_fgts ( fpasFgts, simplesFgts, "
//                    + "remuneracaoFgts, trabalhadoresFgts,aliquotaFgts,codRecolhiFgts,"
//                    + "idRecolhiFgts,inscricaoFgts,competenciaFgts,validadeFgts,"
//                    + "depositoFgts,encargosFgts,totalFgts,dtGeracao) "
//                    + "values (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?)";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, pdffgts.getFpasFgts());
//            ps.setString(2, pdffgts.getSimplesFgts());
//            ps.setString(3, pdffgts.getRemuneracaoFgts());
//            ps.setString(4, pdffgts.getTrabalhadoresFgts());
//            ps.setString(5, pdffgts.getAliquotafgts());
//            ps.setString(6, pdffgts.getCodRecolhiFgts());
//            ps.setString(7, pdffgts.getIdRecolhiFgts());
//            ps.setString(8, pdffgts.getInscricaoFgts());
//            ps.setString(9, pdffgts.getCompetenciaFgts());
//            ps.setString(10, pdffgts.getValidadeFgts());
//            ps.setString(11, pdffgts.getDepositoFgts());
//            ps.setString(12, pdffgts.getEncargosFgts());
//            ps.setString(13, pdffgts.getTotalFgts());
//            ps.setString(14, pdffgts.getDtGeracaoFgts());
//            ps.execute();
//            itsOK = true;
//        } catch (SQLException e) {
//            System.out.println("Erro salvar inserirPdfFgts " + e.getMessage());
//        } finally {
//            TratamentoConexao.fecharConexao(con);
//        }
//        return itsOK;
//    }
//}
