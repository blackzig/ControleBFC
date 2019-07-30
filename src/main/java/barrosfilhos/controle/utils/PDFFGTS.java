/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.utils;

import barrosfilhos.controle.process.ProcessarDarf;
import barrosfilhos.controle.model.PdfDarf;
import static barrosfilhos.controle.utils.ArquivoDarf.PATHSFOLDERS;
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

    public static List<PdfDarf> LerDarfs() {
        List<PdfDarf> lista = new ArrayList<>();
        try {
            for (String pasta : PATHSFOLDERS) {
                List<File> listFiles = ArquivoDarf.justPDFFiles(pasta);

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
                        if (i == 82) {
                            System.out.println("82");
                            ProcessarDarf.pdfDarfDominio82(conteudoPDF);
                        } else if (i == 70) {
                            ProcessarDarf.pdfDarfSicalc(conteudoPDF);
                        }
                    }
                    document.close();
                    System.out.println("-----------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO readRows " + e.getMessage());
        }
        return lista;
    }

}
