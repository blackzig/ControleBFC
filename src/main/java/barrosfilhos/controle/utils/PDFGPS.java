/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.utils;




import barrosfilhos.controle.model.PdfGps;
import barrosfilhos.controle.process.ProcessarGps;
import static barrosfilhos.controle.utils.ArquivoGps.PATHSFOLDERS;
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
public class PDFGPS {

    public static List<PdfGps> LerGps() throws IOException {
 List<PdfGps> lista = new ArrayList<>();
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
                        if (i == 72) {
                            System.out.println("72");                            
                            boolean itsOK = ProcessarGps.pdfGps72Linhas(conteudoPDF);
                            document.close();
                            if (itsOK) {
                                ArquivoGps.moveFile(f);
                            }
                        } else if (i == 68) {
                          boolean itsOK = ProcessarGps.pdfGps70Linhas(conteudoPDF);
                          document.close();
                          if(itsOK) {
                              ArquivoGps.moveFile(f);
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