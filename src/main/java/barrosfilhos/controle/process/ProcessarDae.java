/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.process;


import barrosfilhos.controle.dao.PdfDaeDAO;
import barrosfilhos.controle.model.PdfDae;
import java.util.List;

/**
 *
 * @author Michel
 */
public class ProcessarDae {

    public static boolean pdfDae36Linhas(List<String> conteudoPDF) {
        /*
        linhas para ler
        Nome da documentação    31
        cpfDae                  3    
        apuracaoDae             7
        vencimentoDae           9
        valorDae                15
      
         */
        PdfDae p = new PdfDae();
        p.setCompararLinha(conteudoPDF.get(31));
        if (p.getCompararLinha().contains("Documento de Arrecadação do eSocial")) {
        p.setCpfDae(conteudoPDF.get(3));
        p.setApuracaoDae(conteudoPDF.get(7));
        p.setVencimentoDae(conteudoPDF.get(9));
        p.setValorDae(conteudoPDF.get(15));
        
        boolean itsOK = verificarTabelaPdfDae(p);

        if (itsOK) {
            PdfDaeDAO dao = new PdfDaeDAO();
            dao.inserirPdfDae(p);
        } else {
            System.out.println("Já tem.");
        }
        return itsOK;
    }
        return false;
        
    }
    
    
    private static boolean verificarTabelaPdfDae(PdfDae p) {
        boolean itsOK = false;
        PdfDaeDAO dao = new PdfDaeDAO();
        List<PdfDae> pdfsdae = dao.verificarTabelaPdfDae(p);
        if (pdfsdae.isEmpty()) {
            itsOK = true;
        }
        return itsOK;
    }

   
}
