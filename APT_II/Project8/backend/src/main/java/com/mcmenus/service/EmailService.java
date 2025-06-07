package com.mcmenus.service;

import com.mcmenus.model.CalculationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.core.io.ByteArrayResource;
import jakarta.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInvoiceEmail(String to, CalculationResult result) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Tu factura McMENUS #" + result.getInvoiceNumber());
        message.setText(result.getResultText());
        
        mailSender.send(message);
    }

    public void sendInvoicePdfEmail(String to, String subject, String body) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph(body));
            document.close();

            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setTo(to);
                messageHelper.setSubject(subject);
                messageHelper.setText("Adjunto encontrarás la factura en PDF.");
                messageHelper.addAttachment("factura.pdf", new ByteArrayResource(baos.toByteArray()));
            };

            mailSender.send(messagePreparator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 