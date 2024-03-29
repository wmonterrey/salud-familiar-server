
/*
 Quodlibet.be
 */
package ni.gob.minsa.hsf.utils.pdf;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class pdfParagraph {


    
    private int width = 500;

    
    private String text;
    private PDFont font = PDType1Font.HELVETICA;
    private int fontSize = 10;

    private int color = 0;

    public pdfParagraph( String text, PDFont font, int fontSize, int width)
    {
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
        this.width = width;
    }


    public List<String> getLines() throws IOException
    {
        List<String> result = new ArrayList<String>();

        String[] split = text.split("(?<=\\W)");
        int[] possibleWrapPoints = new int[split.length];
        possibleWrapPoints[0] = split[0].length();
        for ( int i = 1 ; i < split.length ; i++ ) {
            possibleWrapPoints[i] = possibleWrapPoints[i-1] + split[i].length();
        }

        int start = 0;
        int end = 0;
        for ( int i : possibleWrapPoints ) {
            float width = font.getStringWidth(text.substring(start,i)) / 1000 * fontSize;
            if ( start < end && width > this.width ) {
                result.add(text.substring(start,end));
                start = end;
            }
            end = i;
        }
        // Last piece of text
        result.add(text.substring(start));
        return result;
    }

    public float getFontHeight() {
        return font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
    }
    public float getFontWidth() {
        return font.getFontDescriptor().getFontBoundingBox().getWidth() / 1000 * fontSize;
    }

    public pdfParagraph withWidth(int width) {
        this.width = width;
        return this;
    }

    public pdfParagraph withFont(PDFont font, int fontSize) {
        this.font = font;
        this.fontSize = fontSize;
        return this;
    }

    public pdfParagraph withColor(int color) {
        this.color = color;
        return this;
    }

    public int getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public String getText() {
        return text;
    }

    public PDFont getFont() {
        return font;
    }

    public int getFontSize() {
        return fontSize;
    }
}
