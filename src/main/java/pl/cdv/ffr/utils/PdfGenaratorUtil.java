package pl.cdv.ffr.utils;

import com.itextpdf.text.pdf.BaseFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.cdv.ffr.service.BaseService;
import pl.cdv.ffr.service.InvoiceService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

@Component
public class PdfGenaratorUtil {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private BaseService baseService;

    @Autowired
    ResourceLoader resourceLoader;

    Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    public InputStream createPdf(String templateName, Map<String, Object> map) throws Exception {
        Context ctx = new Context();
        map.forEach((k, v) -> {
            ctx.setVariable(k, v);
        });

        ctx.setVariable("baseUrl", baseService.getCurrentBaseUrl());

        String processedHtml = templateEngine.process(templateName, ctx);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        URL font = Thread.currentThread().getContextClassLoader().getResource("static/fonts/Lato-Regular.ttf");
        String fontPath = font.getPath();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont(fontPath,
                    BaseFont.IDENTITY_H,
                    BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) { /*ignore*/ }
            }
        }

        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;

    }
}
