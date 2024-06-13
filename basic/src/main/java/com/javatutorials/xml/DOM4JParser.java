package com.javatutorials.xml;

import com.google.gson.Gson;
import com.javatutorials.java.io.FileLoaderWithoutSpring;
import com.javatutorials.model.SystemLogException;
import com.javatutorials.model.VesselEventInfo;
import org.dom4j.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DOM4JParser {
    Gson gson = new Gson();

    private static final List<String> ignoredLogKeyWordList = Arrays.asList("exceptions.OptimisticLockException;exception.OutOfServiceException".split(";"));

    @Test
    public void test_ignore_exception() throws IOException {
        String content = FileLoaderWithoutSpring.readFileFromResources("com/javatutorials/xml/", "exceptionDetails.txt");
        if (ignoredLogKeyWordList.stream().anyMatch(content::contains)) {
            System.out.println("ignore");
            return;
        }
        System.out.println("not ignore");
    }

    @Test
    public void parse_pkl() throws IOException, DocumentException {
        String xmlContent = FileLoaderWithoutSpring.readFileFromResources("com/javatutorials/xml/", "system_log.xml");
        if (!containExceptionInfo(xmlContent)) {
            System.out.println("no exception info");
            return;
        }

        Document document = DocumentHelper.parseText(xmlContent);
        Element rootElement = document.getRootElement();

        SystemLogException SystemLogException = new SystemLogException();
        SystemLogException.setMsgType(getSingleFieldValue(rootElement, Arrays.asList("Header", "MsgType")));
        SystemLogException.setSenderID(getSingleFieldValue(rootElement, Arrays.asList("Header", "SenderID")));
        SystemLogException.setInterchangeMessageID(getSingleFieldValue(rootElement, Arrays.asList("Header", "InterchangeMessageID")));
        SystemLogException.setReceiveGMT(getSingleFieldValue(rootElement, Arrays.asList("Header", "ReceiveGMT")));
        SystemLogException.setTransactionID(getSingleFieldValue(rootElement, Arrays.asList("Transaction", "TransactionID")));

        List<Element> transactions = rootElement.elements("Transaction");
        List<Element> exceptionTransactions = transactions.stream().filter(t -> t.element("Exception") != null).collect(Collectors.toList());
        List<Element> exceptions = exceptionTransactions.stream().map(t -> t.element("Exception")).collect(Collectors.toList());
        exceptionTransactions.stream().map(t -> t.element("Exception")).forEach(e -> {
            System.out.println(e.element("Level").getText());
        });

        for (Element transaction : transactions) {
            //System.out.println("done");
        }
        SystemLogException.setMessageID(getSingleFieldValue(rootElement, Arrays.asList("Transaction", "MessageID")));
        SystemLogException.setMessagePath(getSingleFieldValue(rootElement, Arrays.asList("Transaction", "MsgPath")));
        SystemLogException.setExceptionCode(getSingleFieldValue(rootElement, Arrays.asList("Transaction", "Exception", "ExceptionCode")));
        SystemLogException.setExceptionLevel(getSingleFieldValue(rootElement, Arrays.asList("Transaction", "Exception", "Level")));
//        SystemLogException.setRawMessage(xmlContent);
//        System.out.println(gson.toJson(SystemLogException));

    }

    private boolean containExceptionInfo(String messageContent) {
        return messageContent.contains(":Exception>");
    }

    @Test
    public void parse_xml() throws IOException, DocumentException {
        String xmlContent = FileLoaderWithoutSpring.readFileFromResources("com/javatutorials/xml/", "vesselEvent.xml");
//        xmlContent = xmlContent.replaceAll("ns2:","");
        System.out.println(xmlContent);
        xmlContent = xmlContent.replaceAll("ns2:", "");
        Document document = DocumentHelper.parseText(xmlContent);
        Element rootElement = document.getRootElement();
//        treeWalk(rootElement);
//        getNodes(rootElement);

        VesselEventInfo vesselEventInfo = new VesselEventInfo();
        vesselEventInfo.setInterchangeMessageID(getSingleFieldValue(rootElement, Arrays.asList("Header", "InterchangeMessageID")));
        vesselEventInfo.setSubscriptionID(getSingleFieldValue(rootElement, Arrays.asList("Body", "TransactionInformation", "SubscriptionID")));
        vesselEventInfo.setSsmTransactionRefNo(getSingleFieldValue(rootElement, Arrays.asList("Body", "TransactionInformation", "SSMTransactionRefNo")));

        vesselEventInfo.setSsmEventGMTDateTimeString(getSingleFieldValue(rootElement, Arrays.asList("Body", "Event", "EventDT", "GMT")));
        vesselEventInfo.setCarrier(getSingleFieldValue(rootElement, Arrays.asList("Body", "GeneralInformation", "SCAC")));

        Element scheduleInformation = rootElement.element("Body").element("ScheduleInformation");

        vesselEventInfo.setService(getSingleFieldValue(scheduleInformation, Arrays.asList("SVVD", "Service")));
        vesselEventInfo.setVessel(getSingleFieldValue(scheduleInformation, Arrays.asList("SVVD", "Vessel")));
        vesselEventInfo.setVesselName(getSingleFieldValue(scheduleInformation, Arrays.asList("SVVD", "VesselName")));
        vesselEventInfo.setVoyage(getSingleFieldValue(scheduleInformation, Arrays.asList("SVVD", "Voyage")));
        vesselEventInfo.setDirection(getSingleFieldValue(scheduleInformation, Arrays.asList("SVVD", "Direction")));

        vesselEventInfo.setUnLocationCode(getSingleFieldValue(scheduleInformation, Arrays.asList("Port", "LocationCode", "UNLocationCode")));
        vesselEventInfo.setCallNumber(getSingleFieldValue(scheduleInformation, Arrays.asList("ScheduleDetails", "CallNumber")));
        vesselEventInfo.setFacilityCode(getSingleFieldValue(scheduleInformation, Arrays.asList("ScheduleDetails", "Facility", "FacilityCode")));
        vesselEventInfo.setFacilityName(getSingleFieldValue(scheduleInformation, Arrays.asList("ScheduleDetails", "Facility", "FacilityName")));
        vesselEventInfo.updateEventDate(getVesselEventDates(scheduleInformation, Arrays.asList("ScheduleDetails")));
        System.out.println(new Gson().toJson(vesselEventInfo));
//
//        nodes = Arrays.asList("Header","InterchangeMessageID");
//        value = get_field_value(rootElement,nodes);
//        System.out.println(value);
//
//        nodes = Arrays.asList("Body","TransactionInformation","SubscriptionID");
//        value = get_field_value(rootElement,nodes);
//        System.out.println(value);
//
//        nodes = Arrays.asList("Body","TransactionInformation","SSMTransactionRefNo");
//        value = get_field_value(rootElement,nodes);
//        System.out.println(value);
//
//        nodes = Arrays.asList("Body","ScheduleInformation","ScheduleDetails");
//        value = get_field_value(rootElement,nodes);
//        System.out.println(value);
//
//        List<Node> list = document.selectNodes("/UpdateSailingSchedule/Body/ScheduleInformation/ScheduleDetails");
//
//        xmlContent = xmlContent.replaceAll("ns2:","");

//        XStream xstream2 = new XStream();
//        XStream.setupDefaultSecurity(xstream2);
//        Class<?>[] classes = new Class[] { UpdateSailingSchedule.class };
//        xstream2.allowTypes(classes);
//        UpdateSailingSchedule student1 = (UpdateSailingSchedule) xstream2.fromXML(xmlContent);
//        System.out.println(student1);
//        Element gmt = null;
//        for(String node:nodes){
//            if(gmt==null){
//                gmt = rootElement.element(node);
//            }else{
//                gmt = gmt.element(node);
//            }
//        }
//        String dateTimeString = gmt.getText().trim();
//        System.out.println(gmt.getText().trim());
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
//        System.out.println(dateTime);
    }

    private String getSingleFieldValue(Element rootElement, List<String> nodes) {
        Element element = null;
        for (String node : nodes) {
            if (element == null) {
                element = rootElement.element(node);
            } else {
                element = element.element(node);
            }
        }
        return element != null && element.getText() != null ? element.getText().trim() : "";
    }

    private Map<String, String> getVesselEventDates(Element rootElement, List<String> nodes) {
        Map<String, String> vesselEvents = new HashMap<>();
        Element element = null;
        for (String node : nodes) {
            if (element == null) {
                element = rootElement.element(node);
            } else {
                element = element.element(node);
            }
        }
        for (Element e : element.elements()) {
            if ("CallNumber".equalsIgnoreCase(e.getName())) {
                vesselEvents.put(e.getName().toUpperCase(), e.getText());
                continue;
            }

            if ("ArrivalDT".equalsIgnoreCase(e.getName())) {
                vesselEvents.put(e.getName().toUpperCase() + "_" + e.attribute("Indicator").getValue().toUpperCase(), e.element("LocDT").getText());
                continue;
            }

            if ("DepartureDT".equalsIgnoreCase(e.getName())) {
                vesselEvents.put(e.getName().toUpperCase() + "_" + e.attribute("Indicator").getValue().toUpperCase(), e.element("LocDT").getText());
                continue;
            }

        }

        return vesselEvents;
    }

    public void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node);
            } else {
                System.out.println(node.getName() + "=" + node.getText());
            }
        }
    }

    public void getNodes(Element node) {
        System.out.println("--------------------");

        //当前节点的名称、文本内容和属性
        System.out.println("当前节点名称：" + node.getName());//当前节点名称
        System.out.println("当前节点的内容：" + node.getTextTrim());//当前节点名称
        List<Attribute> listAttr = node.attributes();//当前节点的所有属性的list
        for (Attribute attr : listAttr) {//遍历当前节点的所有属性
            String name = attr.getName();//属性名称
            String value = attr.getValue();//属性的值
            System.out.println("属性名称：" + name + "属性值：" + value);
        }

        //递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();//所有一级子节点的list
        for (Element e : listElement) {//遍历所有一级子节点
            this.getNodes(e);//递归
        }
    }
}
