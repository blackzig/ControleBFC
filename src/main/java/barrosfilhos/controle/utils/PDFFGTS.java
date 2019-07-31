/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.utils;


import barrosfilhos.controle.model.PdfFgts;
import barrosfilhos.controle.process.ProcessarFgts;
import static barrosfilhos.controle.utils.ArquivoFgts.PATHSFOLDERS;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *
 * @author Michel
 */
public class PDFFGTS {

    public static List<PdfFgts> LerFGTSs() throws IOException {
 List<PdfFgts> lista = new ArrayList<>();
        try {
            List<File> listFiles = null;
            for (String pasta : PATHSFOLDERS) {
                listFiles = ArquivoDarf.justPDFFiles(pasta);

                for (File f : listFiles) {
                    PDDocument document = PDDocument.load(f);

                    document.getClass();

                    if (!document.isEncrypted()) {

                        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                        stripper.setSortByPosition(true);

                        PDFTextStripper tStripper = new PDFTextStripper();

                        String pdfFileInText = tStripper.getText(document);
                        //System.out.println("Text:" + st);

                        // split by whitespace
                        String lines[] = pdfFileInText.split("\\r?\\n");
                        int i = 0;
                        List<String> conteudoPDF = new ArrayList<>();
                        System.out.println("---------------" + f.getName() + "--------------------");
                        for (String line : lines) {
                            System.out.println(i + " - " + line);
                            conteudoPDF.add(line);
                            i++;
                        }
                        i--;
                        if (i == 141) {
                            System.out.println("141");
                            boolean itsOK = ProcessarFgts.pdfFGTS2Folhas(conteudoPDF);
                            document.close();
                            if (itsOK) {
                                ArquivoFgts.moveFile(f);
                            }
                        } else if (i == 68) {
                          boolean itsOK = ProcessarFgts.pdfFGTS1Folhas(conteudoPDF);
                          document.close();
                          if(itsOK) {
                              ArquivoFgts.moveFile(f);
                          }
                        }
                    }
                    System.out.println("-----------------------------------");
                }
            }
            // Arquivo.moveFile(listFiles);
        } catch (IOException e) {
            System.out.println("ERRO readRows " + e.getMessage());
        }
        return lista;
    }

}