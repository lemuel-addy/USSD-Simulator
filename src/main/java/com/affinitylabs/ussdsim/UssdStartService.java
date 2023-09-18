package com.affinitylabs.ussdsim;


import javafx.concurrent.Service;
import javafx.concurrent.Task;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class UssdStartService extends Service<String> {

    private String serverUrl;

    public UssdStartService(String url) {
        this.serverUrl = url;
    }

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                System.out.println("Task called!!");
                UssdRequestInfoBip request = new UssdRequestInfoBip();
                request.setMsisdn("+233553007243");
                request.setShortCode("*229#");
                request.setUssdNodeId("live1");
                request.setNetworkName("vodafone");
                request.setCountryName("Ghana");
                HttpResponse<JsonNode> response = Unirest.post(serverUrl)
                        .header("Content-Type", "application/json")
                        .body(request).asJson();
                if (response.getStatus() == 200) {
                    return (response.getBody().getObject().getString("ussdMenu"));
                } else {
                    return response.getStatusText();
                }
            }
        };
    }
}
