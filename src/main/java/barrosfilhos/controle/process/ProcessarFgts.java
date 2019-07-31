/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.process;

import barrosfilhos.controle.model.PdfFgts;
import barrosfilhos.controle.dao.PdfFgtsDAO;
import java.util.List;

/**
 *
 * @author Michel
 */
public class ProcessarFgts {

    public static boolean pdfFGTS2Folhas(List<String> conteudoPDF) {
        /*
        linhas para ler
     Nome da documentação    73
     fpasFgts	             107
     simplesFgts	     110
     remuneracaoFgts	     114
     trabalhadoresFgts	     118
     aliquotaFgts	     124
     codRecolhiFgts	     99
     idRecolhiFgts	     112
     inscricaoFgts	     116
     competenciaFgts	     120
     validadeFgts	     126
     depositoFgts	     132
     encargosFgts	     94
     totalFgts	             128
     dtGeracao	             140

         */
        PdfFgts p = new PdfFgts();
         p.setCompararLinha(conteudoPDF.get(73));
       if (p.getCompararLinha().contains("GRF - GUIA DE RECOLHIMENTO DO FGTS")) {  
        p.setFpasFgts(conteudoPDF.get(107));
        p.setSimplesFgts(conteudoPDF.get(110));
        p.setRemuneracaoFgts(conteudoPDF.get(114));
        p.setTrabalhadoresFgts(conteudoPDF.get(118));
        p.setAliquotafgts(conteudoPDF.get(124));
        p.setCodRecolhiFgts(conteudoPDF.get(99));
        p.setIdRecolhiFgts(conteudoPDF.get(112));
        p.setInscricaoFgts(conteudoPDF.get(116));
        p.setCompetenciaFgts(conteudoPDF.get(120));
        p.setValidadeFgts(conteudoPDF.get(126));
        p.setDepositoFgts(conteudoPDF.get(132));
        p.setEncargosFgts(conteudoPDF.get(94));
        p.setTotalFgts(conteudoPDF.get(128));
        p.setDtGeracaoFgts(conteudoPDF.get(140));

        boolean itsOK = verificarTabelaPdfDarf(p);

        if (itsOK) {
            PdfFgtsDAO dao = new PdfFgtsDAO();
            dao.inserirPdfFgts(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    } 
        return false;
    
    }
    
    
    

    public static boolean pdfFGTS1Folhas(List<String> conteudoPDF) {
     /*
        linhas para ler
     Nome da documentação    2
     fpasFgts	             36
     simplesFgts	     39
     remuneracaoFgts	     43
     trabalhadoresFgts	     47
     aliquotaFgts	     53
     codRecolhiFgts	     37
     idRecolhiFgts	     41
     inscricaoFgts	     45
     competenciaFgts	     49
     validadeFgts	     55
     depositoFgts	     61
     encargosFgts	     59
     totalFgts	             57
     dtGeracao	             67

         */
        PdfFgts p = new PdfFgts();
        p.setCompararLinha(conteudoPDF.get(2));
       if (p.getCompararLinha().contains("GRF - GUIA DE RECOLHIMENTO DO FGTS")) {  
        p.setFpasFgts(conteudoPDF.get(36));
        p.setSimplesFgts(conteudoPDF.get(39));
        p.setRemuneracaoFgts(conteudoPDF.get(43));
        p.setTrabalhadoresFgts(conteudoPDF.get(47));
        p.setAliquotafgts(conteudoPDF.get(53));
        p.setCodRecolhiFgts(conteudoPDF.get(37));
        p.setIdRecolhiFgts(conteudoPDF.get(41));
        p.setInscricaoFgts(conteudoPDF.get(45));
        p.setCompetenciaFgts(conteudoPDF.get(49));
        p.setValidadeFgts(conteudoPDF.get(55));
        p.setDepositoFgts(conteudoPDF.get(61));
        p.setEncargosFgts(conteudoPDF.get(59));
        p.setTotalFgts(conteudoPDF.get(57));
        p.setDtGeracaoFgts(conteudoPDF.get(67));

        boolean itsOK = verificarTabelaPdfDarf(p);

        if (itsOK) {
            PdfFgtsDAO dao = new PdfFgtsDAO();
            dao.inserirPdfFgts(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    }
        return false;
       
    }
    
    

    private static boolean verificarTabelaPdfDarf(PdfFgts p) {
        boolean itsOK = false;
        PdfFgtsDAO dao = new PdfFgtsDAO();
        List<PdfFgts> pdfsfgts = dao.verificarTabelaFgts(p);
        if (pdfsfgts.isEmpty()) {
            itsOK = true;
        }
        return itsOK;
    }

}
