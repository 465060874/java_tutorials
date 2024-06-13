package com.javatutorials.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.javatutorials.model.CDTData;
import com.javatutorials.model.CTEventRecord;
import com.javatutorials.model.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelParser {
    @Test
    public void test_excel_parser(){
        List<CTEventRecord> ctEventRecords = new ArrayList<>();
        EasyExcel.read("D:\\Support\\CT\\moc_ct.xlsx", CTEventRecord.class, new PageReadListener<CTEventRecord>(dataList -> {
            ctEventRecords.addAll(dataList);
        })).sheet().doRead();

        List<CTEventRecord> sortedCTEvents = ctEventRecords.stream()
                .sorted(Comparator.comparing(CTEventRecord::getBkgNum)
                        .thenComparing(CTEventRecord::getCntrNum)
                        .thenComparing(CTEventRecord::getEvent))
                .collect(Collectors.toList());

        System.out.println(ctEventRecords.size());

        List<CDTData> cdtDataList = new ArrayList<>();
        EasyExcel.read("D:\\Support\\CT\\moc_ct.xlsx", CDTData.class, new PageReadListener<CDTData>(dataList -> {
            cdtDataList.addAll(dataList);
        })).sheet().doRead();

        List<CTEventRecord> sortedCdtDataList = ctEventRecords.stream()
                .sorted(Comparator.comparing(CTEventRecord::getBkgNum)
                        .thenComparing(CTEventRecord::getCntrNum)
                        .thenComparing(CTEventRecord::getEvent))
                .collect(Collectors.toList());
    }
}
