package com.affinitylabs.ussdsim;

import com.google.gson.annotations.Expose;

public class UssdResponseInfoBip {

    @Expose(serialize = true, deserialize = true)
    private boolean shouldClose;
    @Expose(serialize = true, deserialize = true)
    private String ussdMenu;
    @Expose(serialize = true, deserialize = true)
    private int responseExitCode;
    @Expose(serialize = true, deserialize = true)
    private String responseMessage;

    public UssdResponseInfoBip() {
    }

    public boolean getShouldClose() {
        return shouldClose;
    }

    public void setShouldClose(boolean shouldClose) {
        this.shouldClose = shouldClose;
    }

    public String getUssdMenu() {
        return ussdMenu;
    }

    public void setUssdMenu(String ussdMenu) {
        this.ussdMenu = ussdMenu;
    }

    public int getResponseExitCode() {
        return responseExitCode;
    }

    public void setResponseExitCode(int responseExitCode) {
        this.responseExitCode = responseExitCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "UssdResponseInfoBip{" +
                "shouldClose='" + shouldClose + '\'' +
                ", ussdMenu='" + ussdMenu + '\'' +
                ", responseExitCode=" + responseExitCode +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
