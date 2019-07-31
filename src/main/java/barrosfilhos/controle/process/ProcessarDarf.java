/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.process;

import barrosfilhos.controle.model.PdfDarf;
import barrosfilhos.controle.dao.PdfDarfDAO;
import java.util.List;

/**
 *
 * @author Michel
 */
public class ProcessarDarf {

    public static boolean pdfDarfDominio82(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    3
        Apuracao_Darf           30
        Inscricao_Darf          31
        Receita_Darf            32
        Vencimento_Darf         33
        ValorPrinc_Darf         34
        ValorMulta_Darf         35
        ValorJuros_Darf         36
        ValorTotal_Darf         37
         */
        PdfDarf p = new PdfDarf();
        p.setApuracaoDarf(conteudoPDF.get(30));
        p.setCnpjDarf(conteudoPDF.get(31));
        p.setReceitaDarf(conteudoPDF.get(32));
        p.setVencimentoDarf(conteudoPDF.get(33));
        p.setValorPrincDarf(conteudoPDF.get(34));
        p.setValorMultaDarf(conteudoPDF.get(35));
        p.setValorJurosDarf(conteudoPDF.get(36));
        p.setValorTotalDarf(conteudoPDF.get(37));

        boolean itsOK = verificarTabelaPdfDarf(p);

        if (itsOK) {
            PdfDarfDAO dao = new PdfDarfDAO();
            dao.inserirPdfDARF(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    }

    public static boolean pdfDarfSicalc(List<String> conteudoPDF) {
        PdfDarf p = new PdfDarf();
        p.setCompararLinha(conteudoPDF.get(12));
         /*
        linhas para ler
        Nome da documentação    12
        Apuracao_Darf           2
        Inscricao_Darf          3
        Receita_Darf            4
        Vencimento_Darf         5
        ValorPrinc_Darf         6
        ValorMulta_Darf         7
        ValorJuros_Darf         8
        ValorTotal_Darf         9
         */
        if (p.getCompararLinha().contains("Documento de Arrecadação de Receitas Federais")) {
            p.setApuracaoDarf(conteudoPDF.get(2));
            p.setCnpjDarf(conteudoPDF.get(3));
            p.setReceitaDarf(conteudoPDF.get(4));
            p.setVencimentoDarf(conteudoPDF.get(5));
            p.setValorPrincDarf(conteudoPDF.get(6));
            p.setValorMultaDarf(conteudoPDF.get(7));
            p.setValorJurosDarf(conteudoPDF.get(8));
            p.setValorTotalDarf(conteudoPDF.get(9));
            boolean itsOK = verificarTabelaPdfDarf(p);
            if (itsOK) {
                PdfDarfDAO dao = new PdfDarfDAO();
                dao.inserirPdfDARF(p);
            } else {
                System.out.println("Já tem.");
            }
            return itsOK;
       } else {
             /*
        linhas para ler
        Nome da documentação    13
        Apuracao_Darf           3
        Inscricao_Darf          4
        Receita_Darf            5
        Vencimento_Darf         6
        ValorPrinc_Darf         7
        ValorMulta_Darf         8
        ValorJuros_Darf         9
        ValorTotal_Darf         10
         */
            p.setCompararLinha(conteudoPDF.get(13));
            if (p.getCompararLinha().contains("Documento de Arrecadação de Receitas Federais"))
            p.setApuracaoDarf(conteudoPDF.get(3));
            p.setCnpjDarf(conteudoPDF.get(4));
            p.setReceitaDarf(conteudoPDF.get(5));
            p.setVencimentoDarf(conteudoPDF.get(6));
            p.setValorPrincDarf(conteudoPDF.get(7));
            p.setValorMultaDarf(conteudoPDF.get(8));
            p.setValorJurosDarf(conteudoPDF.get(9));
            p.setValorTotalDarf(conteudoPDF.get(10));

            boolean itsOK = verificarTabelaPdfDarf(p);

            if (itsOK) {
                PdfDarfDAO dao = new PdfDarfDAO();
                dao.inserirPdfDARF(p);
            } else {
                System.out.println("Já tem.");
            }
            return itsOK;
        }
    }
    public static boolean pdfDarfComCod68(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    12
        Apuracao_Darf           2
        Inscricao_Darf          3
        Receita_Darf            4
        Vencimento_Darf         5
        ValorPrinc_Darf         6
        ValorMulta_Darf         7
        ValorJuros_Darf         8
        ValorTotal_Darf         9
         */
        PdfDarf p = new PdfDarf();
        p.setCompararLinha(conteudoPDF.get(12));
        if (p.getCompararLinha().contains("Documento de Arrecadação de Receitas Federais"))
        p.setApuracaoDarf(conteudoPDF.get(2));
        p.setCnpjDarf(conteudoPDF.get(3));
        p.setReceitaDarf(conteudoPDF.get(4));
        p.setVencimentoDarf(conteudoPDF.get(5));
        p.setValorPrincDarf(conteudoPDF.get(6));
        p.setValorMultaDarf(conteudoPDF.get(7));
        p.setValorJurosDarf(conteudoPDF.get(8));
        p.setValorTotalDarf(conteudoPDF.get(9));

        boolean itsOK = verificarTabelaPdfDarf(p);

        if (itsOK) {
            PdfDarfDAO dao = new PdfDarfDAO();
            dao.inserirPdfDARF(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    }

    private static boolean verificarTabelaPdfDarf(PdfDarf p) {
        boolean itsOK = false;
        PdfDarfDAO dao = new PdfDarfDAO();
        List<PdfDarf> pdfsdarf = dao.verificarTabelaPdfDarf(p);
        if (pdfsdarf.isEmpty()) {
            itsOK = true;
        }
        return itsOK;
    }

   
}
