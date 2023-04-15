package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Ganado;
import modelo.GanadoCrud;
import vista.Formulario2;

/**
 *
 * @author Bayro
 */
public class Ganadero {

    private Ganado cont;
    private GanadoCrud contG;
    private Formulario2 frm;
    Ganado c;

    public void informe() {

        try {
            FileOutputStream archivo;
            File file = new File("E:\\Estructuras\\TrabajoLaboratorio\\LABORATORIO\\src\\PDF\\Reporte.pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("E:\\Estructuras\\TrabajoLaboratorio\\LABORATORIO\\src\\Imagenes\\Login.png");
            img.setAlignment(Element.ALIGN_CENTER);
            img.scaleToFit(100, 100);
            doc.add(img);
            Paragraph p = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            p.add(Chunk.NEWLINE);
            p.add("CLASIFICACION DE BOVINOS");
            p.add(Chunk.NEWLINE);
            p.add(Chunk.NEWLINE);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            PdfPCell c1 = new PdfPCell(new Phrase("CODIGO", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("TIPO", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("PESO", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("EDAD(SEMANAS)", negrita));

            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            c4.setHorizontalAlignment(Element.ALIGN_CENTER);

            c1.setBackgroundColor(BaseColor.DARK_GRAY);
            c2.setBackgroundColor(BaseColor.DARK_GRAY);
            c3.setBackgroundColor(BaseColor.DARK_GRAY);
            c4.setBackgroundColor(BaseColor.DARK_GRAY);

            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            tabla.addCell(c4);

            DefaultTableModel modeloT = new DefaultTableModel();
            JTable tablaD = new JTable();
            tablaD.setModel(modeloT);
            Object[] columna = new Object[6];
            int numRegistros = contG.listGanados().size();
            for (int i = 0; i < numRegistros; i++) {
                tabla.addCell("" + contG.listGanados().get(i).getCcodigo());
                tabla.addCell("" + contG.listGanados().get(i).getTipo());
                tabla.addCell("" + contG.listGanados().get(i).getPeso());
                tabla.addCell("" + contG.listGanados().get(i).getEdad());

                modeloT.addRow(columna);
            }

            doc.add(tabla);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ganadero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Ganadero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ganadero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

