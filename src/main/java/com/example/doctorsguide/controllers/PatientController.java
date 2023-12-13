package com.example.doctorsguide.controllers;

import com.example.doctorsguide.data.PatientExaminationForm;
import com.example.doctorsguide.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PatientController {

    private final SymptomService symptomService;
    private final DiseaseService diseaseService;
    private final DiagnosticProcedureService diagnosticProcedureService;
    private final MedicineService medicineService;
    private final PatientService patientService;

    @GetMapping("/patient_examination_form")
    public String fillPatientForm(Model model){
        model.addAttribute("symptoms", symptomService.getSymptoms());
        model.addAttribute("diseases", diseaseService.getDiseases());
        model.addAttribute("procedures", diagnosticProcedureService.getProcedures());
        model.addAttribute("medicines", medicineService.getMedicines());
        model.addAttribute("patientForm", new PatientExaminationForm());
        return "patient_examination_form";
    }

    @PostMapping("/fill_patient_exam_form")
    public String formReport(@ModelAttribute("patientForm") PatientExaminationForm patientForm, Model model) {
        model.addAttribute("report", patientService.composeForm(patientForm));
        return "doctors_report";
    }

/*    @RequestMapping("/download-pdf")
    @ResponseBody
    public ResponseEntity<byte[]> downloadPDF(@RequestParam("report") String report) throws IOException {
        // Generate PDF content based on the 'report'
        String pdfContent = generatePdfContentFromReport(report);

        byte[] contentBytes = pdfContent.getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "output.pdf");

        return ResponseEntity.ok().headers(headers).body(contentBytes);
    }

    private String generatePdfContentFromReport(String report) throws IOException {
        // Assuming 'report' is a JSON string, you may need to parse it and extract relevant information
        // For example, you can use a JSON library like Jackson to deserialize the JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        PatientExaminationForm reportObject = objectMapper.readValue(report, PatientExaminationForm.class);

        // Generate PDF content based on the extracted information
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Patient Information:");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Name: " + reportObject.getName());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Age: " + reportObject.getAge());
            // Add more information as needed
            contentStream.endText();
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();

        return baos.toString(StandardCharsets.UTF_8);
    }*/



}
