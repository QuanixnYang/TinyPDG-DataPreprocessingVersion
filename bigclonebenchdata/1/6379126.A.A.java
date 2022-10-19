public class A{
    public boolean gerarTutorialPage() {
        try {
            File indexDir = criarDiretorioSite();
            File cssDir = criarDiretorioCss();
            File capDir = criarDiretorioCapitulos();
            File licDir = criarDiretorioLicoes();
            File midDir = criarDiretorioMidias();
            File filesDir = criarDiretorioArquivos();
            File videosDir = new File(filesDir + "/videos");
            videosDir.mkdir();
            File imagensDir = new File(filesDir + "/imagens");
            imagensDir.mkdir();
            String local = System.getProperty("user.dir");
            FileChannel srcCss1 = new FileInputStream(local + "/bin/style/layout.css").getChannel();
            FileChannel destCss1 = new FileOutputStream(cssDir + "/layout.css").getChannel();
            destCss1.transferFrom(srcCss1, 0, srcCss1.size());
            srcCss1.close();
            destCss1.close();
            FileChannel srcCss2 = new FileInputStream(local + "/bin/style/elementos.css").getChannel();
            FileChannel destCss2 = new FileOutputStream(cssDir + "/elementos.css").getChannel();
            destCss2.transferFrom(srcCss2, 0, srcCss2.size());
            srcCss2.close();
            destCss2.close();
            FileChannel srcCss3 = new FileInputStream(local + "/bin/style/estilo-cap-lic-mid.css").getChannel();
            FileChannel destCss3 = new FileOutputStream(cssDir + "/estilo-cap-lic-mid.css").getChannel();
            destCss3.transferFrom(srcCss3, 0, srcCss3.size());
            srcCss3.close();
            destCss3.close();
            FileChannel srcCss4 = new FileInputStream(local + "/bin/style/layout_ie.css").getChannel();
            FileChannel destCss4 = new FileOutputStream(cssDir + "/layout_ie.css").getChannel();
            destCss4.transferFrom(srcCss4, 0, srcCss4.size());
            srcCss4.close();
            destCss4.close();
            FileChannel srcCss5 = new FileInputStream(local + "/bin/style/elementos_ie.css").getChannel();
            FileChannel destCss5 = new FileOutputStream(cssDir + "/elementos_ie.css").getChannel();
            destCss5.transferFrom(srcCss5, 0, srcCss5.size());
            srcCss5.close();
            destCss5.close();
            FileChannel srcCss6 = new FileInputStream(local + "/bin/style/estilo-cap-lic-mid_ie.css").getChannel();
            FileChannel destCss6 = new FileOutputStream(cssDir + "/estilo-cap-lic-mid_ie.css").getChannel();
            destCss6.transferFrom(srcCss6, 0, srcCss6.size());
            srcCss6.close();
            destCss6.close();
            copiarMidias(videosDir, imagensDir);
            escreverMidiasPage(midDir);
            escreverLicoesPage(licDir);
            escreverCapitulosPages(capDir);
            FileWriter indexHtml = new FileWriter(indexDir + "/index.html");
            indexHtml.write(escreverIndexHead() + escreverBodyHeader() + escreverIndexBodyContent() + escreverFooter());
            indexHtml.close();
            System.out.println("Site gerado com sucesso");
            JOptionPane.showMessageDialog(null, "Web Site gerado com sucesso", "\\o/", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Site não gerado");
            JOptionPane.showMessageDialog(null, "Web Site não gerado corretamente", "Ops...", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}