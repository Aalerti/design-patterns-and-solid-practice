package com.solid.interface_segregation_principle.good;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Printable> printers = List.of(
                new SimplePrinter("Home printer"),
                new MultiFunctionPrinter("Office printer")
        );

        List<Scannable> scanners = List.of(
                new MultiFunctionPrinter("Office printer")
        );

        ReportService reportService = new ReportService();
        reportService.printReports(printers, "Monthly report");
        reportService.scanReports(scanners, "Signed contract");
    }
}

class ReportService {
    void printReports(List<Printable> devices, String document) {
        for (Printable device : devices) {
            device.print(document);
        }
    }

    void scanReports(List<Scannable> devices, String document) {
        for (Scannable device : devices) {
            device.scan(document);
        }
    }
}


interface Printable {
    void print(String document);
}

interface Scannable {
    void scan(String document);
}

interface Faxable {
    void fax(String document);
}


class SimplePrinter implements Printable {
    private final String name;

    SimplePrinter(String name) {
        this.name = name;
    }

    @Override
    public void print(String document) {
        System.out.println(name + " prints: " + document);
    }

}

class MultiFunctionPrinter implements Printable, Scannable, Faxable {
    private final String name;

    MultiFunctionPrinter(String name) {
        this.name = name;
    }

    @Override
    public void print(String document) {
        System.out.println(name + " prints: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println(name + " scans: " + document);
    }

    @Override
    public void fax(String document) {
        System.out.println(name + " sends fax: " + document);
    }
}
