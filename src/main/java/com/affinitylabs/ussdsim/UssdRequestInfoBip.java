package com.affinitylabs.ussdsim;

import com.google.gson.annotations.Expose;

public class UssdRequestInfoBip {
    @Expose(serialize = true, deserialize = true)
    private String msisdn;
    @Expose(serialize = true, deserialize = true)
    private String imsi;
    @Expose(serialize = true, deserialize = true)
    private String shortCode;
    @Expose(serialize = true, deserialize = true)
    private String optional;
    @Expose(serialize = true, deserialize = true)
    private String ussdNodeId;
    @Expose(serialize = true, deserialize = true)
    private String text;
    @Expose(serialize = true, deserialize = true)
    private String networkName;
    @Expose(serialize = true, deserialize = true)
    private String countryName;

    public UssdRequestInfoBip() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getUssdNodeId() {
        return ussdNodeId;
    }

    public void setUssdNodeId(String ussdNodeId) {
        this.ussdNodeId = ussdNodeId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "UssdRequestInfoBip{" +
                "msisdn='" + msisdn + '\'' +
                ", imsi='" + imsi + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", optional='" + optional + '\'' +
                ", ussdNodeId='" + ussdNodeId + '\'' +
                ", text='" + text + '\'' +
                ", networkName='" + networkName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
