/*
 * To change this license titulo, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.willesonruan;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willt
 */
public class RelatorioPDF extends TipoRelatorio {

    private Relatorio rel;
    Document document = new Document();
    private boolean verificaGrafico;
    private String grafico;
    private String tipo;

    public RelatorioPDF(Relatorio rel) {
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
            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
            if (rel.getTamanhoPagina() != null) {
                if (rel.getTamanhoPagina().equalsIgnoreCase("carta")) {
                    document = new Document(PageSize.LETTER, 36, 36, 90, 36);
                } else {
                    document = new Document(PageSize.A4, 36, 36, 90, 36);
                }
            } else {
                document = new Document(PageSize.A4, 36, 36, 90, 36);

            }
            document.setMargins(90, 36, 90, 36);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rel.getNomeArquivo() + ".pdf"));
            writer.setPageEvent(event);
            document.open();
            for (int i = 0; i < 3; i++) {
                document.add(new Paragraph("      "));
            }

            if (rel.addTabela() == true) {
                addTabela();
            }
            if (isVerificaGrafico() == true) {
                Grafico graf = new Grafico(); 
                for (int i = 0; i < graf.getImgPDF().size(); i++) {                 
                    Image img1 = Image.getInstance(graf.getImgPDF().get(i).toString());
                    img1.setAlignment(Element.ALIGN_CENTER);
                    document.add(img1);
                }
                graf.limparListPDF();
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
            PdfPTable table = new PdfPTable(rel.getListacolunas().size());
            String c = "";
            for (int i = 0; i < rel.getListacolunas().size(); i++) {
                c = rel.getListacolunas().get(i).toString();
                PdfPCell cell1 = new PdfPCell(new Paragraph(c, styleTabelaColuna()));
                table.addCell(cell1);

            }
            String l = "";
            int numeroLinhas = rel.getListatlinhas().size() / rel.getListacolunas().size();
            for (int i = 0; i < rel.getListatlinhas().size(); i++) {
                l = rel.getListatlinhas().get(i).toString();
                PdfPCell cell2 = new PdfPCell(new Paragraph(l, styleTabelaLinha()));
                table.addCell(cell2);
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
            String classe = this.getClass().getName();
            Grafico grafico = new Grafico();
            grafico.criarGrafico(list, x, y, tipo, nomeGrafico, tituloGrafico, classe);
        } catch (Exception e) {
            System.out.println("Não foi possível gerar o Grafico");
        }
        setVerificaGrafico(true);

    }

    @Override
    public <T> T getRel() {
        return  (T) rel;
    }

    public class HeaderFooterPageEvent extends PdfPageEventHelper {

        private PdfTemplate t;
        private Image total;

        public void onOpenDocument(PdfWriter writer, Document document) {
            t = writer.getDirectContent().createTemplate(30, 16);
            try {
                total = Image.getInstance(t);
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            addHeader(writer);
            addFooter(writer);
        }

        private void addHeader(PdfWriter writer) {
            PdfPTable header;
            try {
                // set defaults
                if (rel.getImagemCabecalho() == null) {
                    header = new PdfPTable(1);
                    header.setWidths(new int[]{24});
                    header.setTotalWidth(527);
                    header.setLockedWidth(true);
                    header.getDefaultCell().setFixedHeight(40);
                    header.getDefaultCell().setBorder(Rectangle.BOTTOM);
                    header.getDefaultCell().setBorderColor(rel.getBorderCabecalho());

                } else {
                    header = new PdfPTable(2);
                    header.setWidths(new int[]{10, 24});
                    header.setTotalWidth(527);
                    header.setLockedWidth(true);
                    header.getDefaultCell().setFixedHeight(40);
                    header.getDefaultCell().setBorder(Rectangle.BOTTOM);
                    header.getDefaultCell().setBorderColor(rel.getBorderCabecalho());
                    Image img = null;
                    try {
                        img = Image.getInstance(rel.getImagemCabecalho());
                    } catch (BadElementException ex) {
                        Logger.getLogger(RelatorioPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(RelatorioPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    header.getDefaultCell().setBackgroundColor(rel.getoBackgroundColorCabecalho());
                    header.addCell(img);
                }

                // add text
                PdfPCell text = new PdfPCell();
                text.setPaddingBottom(15);
                text.setPaddingLeft(10);
                text.setBorder(Rectangle.BOTTOM);
                text.setBorderColor(rel.getBorderCabecalho());
                text.setBackgroundColor(rel.getoBackgroundColorCabecalho());
                text.addElement(new Phrase(rel.getCabecalho(), styleCabecalho()));
                text.addElement(new Phrase(rel.getData(), styleCabecalho()));
                header.addCell(text);

                // write content
                header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        private void addFooter(PdfWriter writer) {
            PdfPTable footer;
            try {
                // set defaults
                if (rel.getImagemRodape() == null) {
                    footer = new PdfPTable(3);
                    footer.setWidths(new int[]{24, 2, 1});
                    footer.setTotalWidth(527);
                    footer.setLockedWidth(true);
                    footer.getDefaultCell().setFixedHeight(40);
                    footer.getDefaultCell().setBorder(Rectangle.TOP);
                    footer.getDefaultCell().setBorderColor(rel.getBorderRodape());
                    footer.getDefaultCell().setBackgroundColor(rel.getoBackgroundColorRodape());
                } else {
                    footer = new PdfPTable(4);
                    footer.setWidths(new int[]{10, 24, 4, 4});
                    footer.setTotalWidth(527);
                    footer.setLockedWidth(true);
                    footer.getDefaultCell().setFixedHeight(40);
                    footer.getDefaultCell().setBorder(Rectangle.TOP);
                    footer.getDefaultCell().setBorderColor(rel.getBorderRodape());
                    footer.getDefaultCell().setBackgroundColor(rel.getoBackgroundColorRodape());
                    footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    Image img = null;
                    try {
                        img = Image.getInstance(rel.getImagemRodape());
                    } catch (BadElementException ex) {
                        Logger.getLogger(RelatorioPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(RelatorioPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    footer.addCell(img);
                }

                // add copyright
                footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                footer.addCell(new Phrase(rel.getRodape(), styleRodape()));

                // add current page count
                footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.HELVETICA, 8)));

                // add placeholder for total page count
                PdfPCell totalPageCount = new PdfPCell(total);
                totalPageCount.setBorder(Rectangle.TOP);
                totalPageCount.setBorderColor(rel.getBorderRodape());
                footer.addCell(totalPageCount);

                // write page
                PdfContentByte canvas = writer.getDirectContent();
                canvas.beginMarkedContentSequence(PdfName.ALLPAGES);
                footer.writeSelectedRows(0, -1, 34, 50, canvas);
                canvas.endMarkedContentSequence();
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }

        public void onCloseDocument(PdfWriter writer, Document document) {
            int totalLength = String.valueOf(writer.getPageNumber()).length();
            int totalWidth = totalLength * 5;
            ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1), new Font(Font.HELVETICA, 8)),
                    totalWidth, 6, 0);
        }
    }

}
