package com.javatutorials.model;

import java.util.Map;

public class VesselEventInfo {
    private String carrier;
    private String interchangeMessageID;
    private String subscriptionID;
    private String ssmTransactionRefNo;
    private String ssmEventGMTDateTimeString;
    private String service;

    private String vessel;
    private String vesselName;
    private String voyage;
    private String direction;
    private String unLocationCode;
    private String callNumber;
    private String facilityCode;
    private String facilityName;

    private String estimatedArrivalLocalDTString;
    private String actualArrivalLocalDTString;
    private String estimatedDepartureDTLocalDTString;
    private String actualDepartureDTLocalDTString;
    private static final String ARRIVALDT_A = "ARRIVALDT_A";
    private static final String ARRIVALDT_E = "ARRIVALDT_E";
    private static final String DEPARTUREDT_A = "DEPARTUREDT_A";
    private static final String DEPARTUREDT_E = "DEPARTUREDT_E";

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getInterchangeMessageID() {
        return interchangeMessageID;
    }

    public void setInterchangeMessageID(String interchangeMessageID) {
        this.interchangeMessageID = interchangeMessageID;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(String subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getSsmTransactionRefNo() {
        return ssmTransactionRefNo;
    }

    public void setSsmTransactionRefNo(String ssmTransactionRefNo) {
        this.ssmTransactionRefNo = ssmTransactionRefNo;
    }

    public String getSsmEventGMTDateTimeString() {
        return ssmEventGMTDateTimeString;
    }

    public void setSsmEventGMTDateTimeString(String ssmEventGMTDateTimeString) {
        this.ssmEventGMTDateTimeString = ssmEventGMTDateTimeString;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getUnLocationCode() {
        return unLocationCode;
    }

    public void setUnLocationCode(String unLocationCode) {
        this.unLocationCode = unLocationCode;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getEstimatedArrivalLocalDTString() {
        return estimatedArrivalLocalDTString;
    }

    public void setEstimatedArrivalLocalDTString(String estimatedArrivalLocalDTString) {
        this.estimatedArrivalLocalDTString = estimatedArrivalLocalDTString;
    }

    public String getActualArrivalLocalDTString() {
        return actualArrivalLocalDTString;
    }

    public void setActualArrivalLocalDTString(String actualArrivalLocalDTString) {
        this.actualArrivalLocalDTString = actualArrivalLocalDTString;
    }

    public String getEstimatedDepartureDTLocalDTString() {
        return estimatedDepartureDTLocalDTString;
    }

    public void setEstimatedDepartureDTLocalDTString(String estimatedDepartureDTLocalDTString) {
        this.estimatedDepartureDTLocalDTString = estimatedDepartureDTLocalDTString;
    }

    public String getActualDepartureDTLocalDTString() {
        return actualDepartureDTLocalDTString;
    }

    public void setActualDepartureDTLocalDTString(String actualDepartureDTLocalDTString) {
        this.actualDepartureDTLocalDTString = actualDepartureDTLocalDTString;
    }

    public void updateEventDate(Map<String, String> eventDateMap) {
        for (String key : eventDateMap.keySet()) {
            if (ARRIVALDT_A.equalsIgnoreCase(key)) {
                this.actualArrivalLocalDTString = eventDateMap.get(key);
                continue;
            }

            if (ARRIVALDT_E.equalsIgnoreCase(key)) {
                this.estimatedArrivalLocalDTString = eventDateMap.get(key);
                continue;
            }

            if (DEPARTUREDT_A.equalsIgnoreCase(key)) {
                this.actualDepartureDTLocalDTString = eventDateMap.get(key);
                continue;
            }

            if (DEPARTUREDT_E.equalsIgnoreCase(key)) {
                this.estimatedDepartureDTLocalDTString = eventDateMap.get(key);
                continue;
            }
        }
    }

    @Override
    public String toString() {
        return "VesselEventInfo{" +
                "interchangeMessageID='" + interchangeMessageID + '\'' +
                ", subscriptionID='" + subscriptionID + '\'' +
                ", ssmTransactionRefNo='" + ssmTransactionRefNo + '\'' +
                ", ssmEventGMTDateTimeString='" + ssmEventGMTDateTimeString + '\'' +
                ", service='" + service + '\'' +
                ", vessel='" + vessel + '\'' +
                ", vesselName='" + vesselName + '\'' +
                ", voyage='" + voyage + '\'' +
                ", direction='" + direction + '\'' +
                ", unLocationCode='" + unLocationCode + '\'' +
                ", callNumber='" + callNumber + '\'' +
                ", facilityCode='" + facilityCode + '\'' +
                ", facilityName='" + facilityName + '\'' +
                ", estimatedArrivalLocalDTString='" + estimatedArrivalLocalDTString + '\'' +
                ", actualArrivalLocalDTString='" + actualArrivalLocalDTString + '\'' +
                ", estimatedDepartureDTLocalDTString='" + estimatedDepartureDTLocalDTString + '\'' +
                ", actualDepartureDTLocalDTString='" + actualDepartureDTLocalDTString + '\'' +
                '}';
    }
}
