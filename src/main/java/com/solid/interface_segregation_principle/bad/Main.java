package com.solid.interface_segregation_principle.bad;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<OfficeDevice> devices = List.of(
                new SimplePrinter("Home printer"),
                new MultiFunctionPrinter("Office printer")
        );

        ReportService reportService = new ReportService();
        reportService.printReports(devices, "Monthly report");
        reportService.scanReports(devices, "Signed contract");
    }
}

class ReportService {
    void printReports(List<OfficeDevice> devices, String document) {
        for (OfficeDevice device : devices) {
            device.print(document);
        }
    }

    void scanReports(List<OfficeDevice> devices, String document) {
        for (OfficeDevice device : devices) {
            device.scan(document);
        }
    }
}

interface OfficeDevice {
    void print(String document);

    void scan(String document);

    void fax(String document);
}

class SimplePrinter implements OfficeDevice {
    private final String name;

    SimplePrinter(String name) {
        this.name = name;
    }

    @Override
    public void print(String document) {
        System.out.println(name + " prints: " + document);
    }

    @Override
    public void scan(String document) {
        throw new UnsupportedOperationException(name + " cannot scan");
    }

    @Override
    public void fax(String document) {
        throw new UnsupportedOperationException(name + " cannot send fax");
    }
}

class MultiFunctionPrinter implements OfficeDevice {
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
