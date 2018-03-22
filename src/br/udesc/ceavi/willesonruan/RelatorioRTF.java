/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.willesonruan;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.field.RtfPageNumber;
import com.lowagie.text.rtf.field.RtfTotalPageNumber;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;
import com.lowagie.text.rtf.table.RtfBorder;
import com.lowagie.text.rtf.table.RtfBorderGroup;
import com.lowagie.text.rtf.table.RtfCell;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author willt
 */
public class RelatorioRTF extends TipoRelatorio {

    private Relatorio rel;
    Document document = new Document();
    private boolean verificaGrafico;
    private String grafico;
    private String tipo;

    public RelatorioRTF(Relatorio rel) {
        this.rel = rel;
    }
    
    public boolean isVerificaGrafico() {
        return verificaGrafico;
    }

    public void setVerificaGrafico(boolean verificaGrafico) {
        this.verificaGrafico = verificaGrafico;
    }

    public String getGrafico() {
        return grafico;
    }

    public void setGrafico(String grafico) {
        this.grafico = grafico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void gerarRelatorio() {
        try {
            RtfWriter2 writer = RtfWriter2.getInstance(document, new FileOutputStream(rel.getNomeArquivo() + ".rtf"));
            document.open();
            //Tamanho da pagina
            if (rel.getTamanhoPagina() != null) {
                if (rel.getTamanhoPagina().equalsIgnoreCase("carta")) {
                    document.setPageSize(PageSize.LETTER);
                } else {
                    document.setPageSize(PageSize.A4);
                }
            } else {
                document.setPageSize(PageSize.A4);
            }
            headerFooter();
            if (isVerificaGrafico() == true) {
                Grafico graf = new Grafico(); 
                for (int i = 0; i < graf.getImgRTF().size(); i++) {                 
                    Image img1 = Image.getInstance(graf.getImgRTF().get(i).toString());
                    img1.setAlignment(Element.ALIGN_CENTER);
                    document.add(img1);
                }
                graf.limparListRTF();
            }
            document.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Font styleTabelaColuna() {
        Font fonte = FontFactory.getFont(rel.getFontTabelaCabecalho(), Float.parseFloat(rel.getFontSizeTabelaCabecalho()), rel.getoNegritoSublinhadoTabelaCabecalho(), rel.getoColorTabelaCabecalho());
        return fonte;
    }

    public Font styleTabelaLinha() {
        Font fonte = FontFactory.getFont(rel.getFontTabelaLinha(), Float.parseFloat(rel.getFontSizeTabelaLinha()), rel.getoNegritoSublinhadoTabelaLinha(), rel.getoColorTabelaLinha());
        return fonte;
    }

    public Font styleCabecalho() {
        Font fonte = FontFactory.getFont(rel.getFontCabecalho(), Float.parseFloat(rel.getFontSizeCabecalho()), rel.getoNegritoSublinhadoCabecalho(), rel.getoColorCabecalho());
        return fonte;

    }

    public Font styleRodape() {
        Font fonte = FontFactory.getFont(rel.getFontRodape(), Float.parseFloat(rel.getFontSizeRodape()), rel.getoNegritoSublinhadoRodape(), rel.getoColorRodape());
        return fonte;

    }

    public void addTabela() {
        try {
            //cria a tabela
            Table table = new Table(rel.getListacolunas().size());
            //cria as colunas
            String c = "";
            for (int i = 0; i < rel.getListacolunas().size(); i++) {
                c = rel.getListacolunas().get(i).toString();
                RtfCell cell1 = new RtfCell(new Paragraph(c));
                table.addCell(new Paragraph(c, styleTabelaColuna()));
            }
            String l = "";
            int numeroLinhas = rel.getListatlinhas().size() / rel.getListacolunas().size();
            for (int i = 0; i < rel.getListatlinhas().size(); i++) {
                l = rel.getListatlinhas().get(i).toString();
                table.addCell(new Paragraph(l, styleTabelaLinha()));
            }
            document.add(table);
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void addGrafico(ArrayList list, String x, String y, String tipo, String nomeGrafico, String tituloGrafico) {
        String imagem = "";
        try {
            Grafico grafico = new Grafico();
            String classe = this.getClass().getName();
            if (tipo != null) {
                grafico.criarGrafico(list, x, y, tipo, nomeGrafico, tituloGrafico,classe);
            } else {
                grafico.criarGrafico(list, x, y, "", nomeGrafico, tituloGrafico,classe);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setVerificaGrafico(true);
        setGrafico(nomeGrafico);
        setTipo(tipo);

    }

    public void headerFooter() throws IOException, DocumentException {
        heater();
        footer();
    }

    public void heater() throws BadElementException, IOException, DocumentException {
        Table tableHead = null;
        if (rel.getImagemCabecalho() == null) {
            tableHead = new Table(1);
            tableHead.setWidths(new int[]{24});
            RtfCell text = new RtfCell();

            text.setBorders(new RtfBorderGroup(Rectangle.BOTTOM, RtfBorder.BORDER_EMBOSS, 1, rel.getBorderCabecalho()));
            text.setBackgroundColor(rel.getoBackgroundColorCabecalho());

            text.add(new Paragraph(rel.getCabecalho(), styleCabecalho()));
            text.add(new Paragraph(rel.getData(), styleCabecalho()));
            tableHead.addCell(text);
        } else {
            tableHead = new Table(2);
            tableHead.setWidths(new int[]{20, 24});
            Image img = Image.getInstance(rel.getImagemCabecalho());
            img.scalePercent(1.3f, 1.3f);

            RtfCell imagem = new RtfCell(img);
            RtfCell text = new RtfCell();

            text.setBorders(new RtfBorderGroup(Rectangle.BOTTOM, RtfBorder.BORDER_EMBOSS, 1, rel.getBorderCabecalho()));
            text.setBackgroundColor(rel.getoBackgroundColorCabecalho());
            imagem.setBorders(new RtfBorderGroup(Rectangle.BOTTOM, RtfBorder.BORDER_EMBOSS, 1, rel.getBorderCabecalho()));
            imagem.setBackgroundColor(rel.getoBackgroundColorCabecalho());

            text.add(new Paragraph("   " + rel.getCabecalho(), styleCabecalho()));
            text.add(new Paragraph("   " + rel.getData(), styleCabecalho()));
            tableHead.addCell(imagem);
            tableHead.addCell(text);

        }
        HeaderFooter header = new RtfHeaderFooter(tableHead);
        document.setHeader(header);

        for (int i = 0; i < 3; i++) {
            document.add(new Paragraph("      "));
        }
        if (rel.addTabela() == true) {
                addTabela();
            }
    }

    public void footer() throws BadElementException, DocumentException, IOException {
        Table tablefooter = null;
        if (rel.getImagemRodape() == null) {

            tablefooter = new Table(2);
            tablefooter.setWidths(new int[]{24, 10});

            RtfCell text = new RtfCell();
            RtfCell pageNumber = new RtfCell();

            text.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_EMBOSS, 1, rel.getBorderRodape()));
            text.setBackgroundColor(rel.getoBackgroundColorRodape());
            text.add(new Paragraph(rel.getRodape(), styleCabecalho()));

            pageNumber.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_EMBOSS, 1, rel.getBorderRodape()));
            pageNumber.setBackgroundColor(rel.getoBackgroundColorRodape());
            Paragraph page = new Paragraph("Page ");
            page.add(new RtfPageNumber());
            page.add(" of ");
            page.add(new RtfTotalPageNumber());
            pageNumber.add(page);

            tablefooter.addCell(text);
            tablefooter.addCell(pageNumber);
        } else {
            tablefooter = new Table(3);
            tablefooter.setWidths(new int[]{10, 24, 10});

            Image img = Image.getInstance(rel.getImagemRodape());
            img.scalePercent(1f, 1f);

            RtfCell imagem = new RtfCell(img);
            RtfCell text = new RtfCell();
            RtfCell pageNumber = new RtfCell();

            text.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_EMBOSS, 1, rel.getBorderRodape()));
            text.setBackgroundColor(rel.getoBackgroundColorRodape());
            text.add(new Paragraph("    " + rel.getRodape(), styleCabecalho()));
            imagem.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_EMBOSS, 1, rel.getBorderRodape()));
            imagem.setBackgroundColor(rel.getoBackgroundColorRodape());

            pageNumber.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_EMBOSS, 1, rel.getBorderRodape()));
            pageNumber.setBackgroundColor(rel.getoBackgroundColorRodape());
            Paragraph page = new Paragraph("Page ");
            page.add(new RtfPageNumber());
            page.add(" of ");
            page.add(new RtfTotalPageNumber());
            pageNumber.add(page);

            tablefooter.addCell(imagem);
            tablefooter.addCell(text);
            tablefooter.addCell(pageNumber);
        }
        HeaderFooter rodape = new RtfHeaderFooter(tablefooter);
        document.setFooter(rodape);

        for (int i = 0; i < 2; i++) {
            document.add(new Paragraph("      "));
        }

    }

    @Override
    public <T> T getRel() {
        return (T) rel;
    }

}
