package pl.cdv.ffr.utils;

import com.itextpdf.text.pdf.BaseFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.cdv.ffr.service.BaseService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
public class PdfGenaratorUtil {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private BaseService baseService;

    @Autowired
    ResourceLoader resourceLoader;

    public InputStream createPdf(String templateName, Map<String, Object> map) throws Exception {
        Context ctx = new Context();
        map.forEach((k, v) -> {
            ctx.setVariable(k, v);
        });

        ctx.setVariable("baseUrl", baseService.getCurrentBaseUrl());

        String processedHtml = templateEngine.process(templateName, ctx);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont("http://wieczorekmarcin.usermd.net/ffr/fonts/Lato-Regular.ttf",
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
