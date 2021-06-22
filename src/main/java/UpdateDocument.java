
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UpdateDocument {

    public static void main(String[] args) throws IOException {

        UpdateDocument obj = new UpdateDocument();

        obj.updateDocument(
                  "c:\\test\\contrato.docx",
                  "c:\\test\\output.docx",
                  "Francisco");
    }

    private void updateDocument(String input, String output, String name)
        throws IOException {
    	
    	
    	Resource resource = new ClassPathResource("/contrato.docx");
    	
        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(resource.getFile().getAbsolutePath())));
        ) {

            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
            //Iterate over paragraph list and check for the replaceable text in each paragraph
            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);
                    //replacement and setting position
                    if (docText == null) continue;
                    docText = docText.replace("${name}", name);
                    xwpfRun.setText(docText, 0);
                }
            }

            // save the docs
            try (FileOutputStream out = new FileOutputStream(output)) {
                doc.write(out);
            }

        }

    }

}