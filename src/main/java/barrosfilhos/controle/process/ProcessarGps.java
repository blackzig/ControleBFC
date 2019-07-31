/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.process;

import barrosfilhos.controle.model.PdfGps;
import barrosfilhos.controle.dao.PdfGpsDAO;
import java.util.List;

/**
 *
 * @author Michel
 */
public class ProcessarGps {

    public static boolean pdfGps72Linhas(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    15
        vencimentoGps           30
        codigoGps               1
        competenciaGps          31
        inscricaoGps            32
        valorInssGps            33
        valorOutrasGps          34
        valorJurosGps           36
        valorTotalGps           38
         */
        PdfGps p = new PdfGps();
         p.setCompararLinha(conteudoPDF.get(15));
        if (p.getCompararLinha().contains("GUIA DA PREVIDENCIA SOCIAL - GPS")) {
              p.setVencimentoGps(conteudoPDF.get(30));
        p.setCodigoGps(conteudoPDF.get(1));
        p.setCompetenciaGps(conteudoPDF.get(31));
        p.setInscricaoGps(conteudoPDF.get(32));
        p.setValorInssGPS(conteudoPDF.get(33));
        p.setValorOutrasGps(conteudoPDF.get(34));
        p.setValorJurosGps(conteudoPDF.get(36));
        p.setValortotalGps(conteudoPDF.get(38));

        boolean itsOK = verificarTabelaPdfGps(p);

        if (itsOK) {
            PdfGpsDAO dao = new PdfGpsDAO();
            dao.inserirPdfGps(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    }
        return false;
    }
    public static boolean pdfGps70Linhas(List<String> conteudoPDF) {
        /* Gps Empresas Em Geral - Lucro Real e Presumido
        linhas para ler
        Nome da documentação    15
        vencimentoGps           30
        codigoGps               1
        competenciaGps          31
        inscricaoGps            32
        valorInssGps            33
        valorOutrasGps          35
        valorJurosGps           34
        valorTotalGps           37
         */
        PdfGps p = new PdfGps();
        p.setCompararLinha(conteudoPDF.get(15));
        if (p.getCompararLinha().contains("GUIA DA PREVIDENCIA SOCIAL - GPS")) {
            p.setVencimentoGps(conteudoPDF.get(30));
            p.setCodigoGps(conteudoPDF.get(1));
            p.setCompetenciaGps(conteudoPDF.get(31));
            p.setInscricaoGps(conteudoPDF.get(32));
            p.setValorInssGPS(conteudoPDF.get(33));
            p.setValorOutrasGps(conteudoPDF.get(35));
            p.setValorJurosGps(conteudoPDF.get(34));
            p.setValortotalGps(conteudoPDF.get(37));
            boolean itsOK = verificarTabelaPdfGps(p);
            if (itsOK) {
                PdfGpsDAO dao = new PdfGpsDAO();
                dao.inserirPdfGps(p);
            } else {
                System.out.println("Já tem.");
            }
            return itsOK;
        } else {
            /* GPS - Empresas Simples Nacional
        linhas para ler
        Nome da documentação    15
        vencimentoGps           30
        codigoGps               1
        competenciaGps          31
        inscricaoGps            30
        valorInssGps            32
        valorOutrasGps          Não tem 69
        valorJurosGps           Não Tem 70 
        valorTotalGps           35
             */
            p.setCompararLinha(conteudoPDF.get(14));
            if (p.getCompararLinha().contains("GUIA DA PREVIDENCIA SOCIAL - GPS")) {
                p.setVencimentoGps(conteudoPDF.get(29));
                p.setCodigoGps(conteudoPDF.get(0));
                p.setCompetenciaGps(conteudoPDF.get(31));
                p.setInscricaoGps(conteudoPDF.get(30));
                p.setValorInssGPS(conteudoPDF.get(32));
                p.setValortotalGps(conteudoPDF.get(35));

                boolean itsOK = verificarTabelaPdfGps(p);

                if (itsOK) {
                    PdfGpsDAO dao = new PdfGpsDAO();
                    dao.inserirPdfGps(p);
                } else {
                    System.out.println("Já tem.");
                }
                return itsOK;
            }
        }
        return false;
    }

    private static boolean verificarTabelaPdfGps(PdfGps p) {
        boolean itsOK = false;
        PdfGpsDAO dao = new PdfGpsDAO();
        List<PdfGps> pdfsgps = dao.verificarTabelaPdfGps(p);
        if (pdfsgps.isEmpty()) {
            itsOK = true;
        }
        return itsOK;
    }

}
