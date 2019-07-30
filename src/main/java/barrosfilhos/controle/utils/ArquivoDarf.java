/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barrosfilhos.controle.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zigui
 */
public class ArquivoDarf {

    private static final String PASTADARF = "J:\\TesteJava\\OneDrive-2019-07-26";
    public static final List<String> PATHSFOLDERS = new ArrayList<>();

    public static void createFolder() {
        PATHSFOLDERS.add(PASTADARF);
        for (String pasta : PATHSFOLDERS) {
            File file = new File(pasta + "\\DarfVerificados");
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Pasta Criada!");
                } else {
                    System.out.println("Erro ao criar a pasta!");
                }
            }
        }
    }

    public static void listFilesAndFolders() {
        File file = new File(PASTADARF);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            System.out.println(arquivos.getName());
        }
    }

    public static List<String> justPDFFilesName(String pathFolder) {
        List<String> listFilesPDF = new ArrayList<>();

        File file = new File(pathFolder);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            if (arquivos.getName().endsWith("pdf")) {
                listFilesPDF.add(arquivos.getName());
            }
        }

        listFilesPDF.forEach((s) -> {
            System.out.println(s);
        });

        return listFilesPDF;
    }

    public static List<File> justPDFFiles(String pathFolder) {
        List<File> listFilesPDF = new ArrayList<>();

        File file = new File(pathFolder);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            if (arquivos.getName().endsWith("pdf")) {
                listFilesPDF.add(arquivos);
            }
        }

        listFilesPDF.forEach((s) -> {
            System.out.println(s);
        });

        return listFilesPDF;
    }

    public static void moveFile(File file) {

        File arquivo = new File(PASTADARF + file.getName());
        //File arquivo = new File(PASTADARF + "\\DARF IRRF 0561 - DOMÍNIO.pdf");
        // diretorio de destino

        File dir = new File(PASTADARF + "\\ArquivosScaneados\\");

        // move o arquivo para o novo diretorio
        boolean ok = arquivo.renameTo(new File(dir, arquivo.getName().concat("OK")));
        if (ok) {
            System.out.println("Arquivo foi movido com sucesso");
        } else {
            System.out.println("Nao foi possivel mover o arquivo");
            arquivo.renameTo(new File(dir, arquivo.getName().concat("Erro")));
        }

    }

    public static void moveFile(List<File> listaPDFS) {
        for (File f : listaPDFS) {
            File arquivo = new File(PASTADARF + f.getName());
            //File arquivo = new File(PASTADARF + "\\DARF IRRF 0561 - DOMÍNIO.pdf");
            // diretorio de destino

            File dir = new File(PASTADARF + "\\ArquivosScaneados\\");

            // move o arquivo para o novo diretorio
            boolean ok = arquivo.renameTo(new File(dir, arquivo.getName().concat("OK")));
            if (ok) {
                System.out.println("Arquivo foi movido com sucesso");
            } else {
                System.out.println("Nao foi possivel mover o arquivo");
                arquivo.renameTo(new File(dir, arquivo.getName().concat("Erro")));
            }
        }
    }

    public static void copyFile() {
        try {
            // arquivos que vamos copiar
            File toFile = new File(PASTADARF + "\\DARF IRRF 0561 - DOMÍNIO.pdf");
            // destino para onde vamos mover o arquivo
            File fromFile = new File(PASTADARF
                    + "\\ArquivosScaneados\\DARF IRRF 0561 - DOMÍNIO.pdf");

            OutputStream out;
            try (InputStream in = new FileInputStream(toFile)) {
                out = new FileOutputStream(fromFile);
                // buffer para transportar os dados
                byte[] buffer = new byte[1024];
                int length;
                // enquanto tiver dados para ler..
                while ((length = in.read(buffer)) > 0) {
                    // escreve no novo arquivo
                    out.write(buffer, 0, length);
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println("ERRO copyFile " + e.getMessage());
        }

    }

}
