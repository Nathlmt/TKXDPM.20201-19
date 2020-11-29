package org.tkxdpm20201.Nhom19.business.api;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.tkxdpm20201.Nhom19.persistence.model.TransactionRequest;

import java.io.IOException;

public class TransactionApi {

    public TransactionApi() {
    }

    private CloseableHttpClient httpClient;

    public Object processTransaction(TransactionRequest transactionRequest) throws IOException {
        httpClient = HttpClients.createDefault();

        HttpPatch httpPatch = new HttpPatch(Config.BASE_URL + "/api/card/processTransaction");
        String bodyRequest = getBodyRequest(transactionRequest);
        httpPatch.setEntity(new StringEntity(bodyRequest, ContentType.APPLICATION_JSON));

        HttpResponse httpResponse = httpClient.execute(httpPatch);
        System.out.println("pokkk");
        // TODO: continue something
        return null;
    }

    private String getHashCode(String jsonString){
        return DigestUtils.md5Hex(jsonString);
    }

    private String getBodyRequest(TransactionRequest transactionRequest){
        JSONObject transaction = getJSONTransaction(transactionRequest, Config.PROCESS_TRANS);
        String hashCode = getHashCode(getJsonToHashCode(transaction).toString());
        JSONObject bodyRequest = new JSONObject();

        bodyRequest.put("version", Config.API_VERSION);
        bodyRequest.put("transaction", transaction);
        bodyRequest.put("cardCode", transactionRequest.getCardCode());
        bodyRequest.put("owner", transactionRequest.getOwner());
        bodyRequest.put("cvvCode", transactionRequest.getCvvCode());
        bodyRequest.put("dateExpired", transactionRequest.getDateExpired());
        bodyRequest.put("command", Config.PROCESS_TRANS);
        bodyRequest.put("transactionContent", transactionRequest.getTransactionContent());
        bodyRequest.put("amount", transactionRequest.getAmount());
        bodyRequest.put("createAt", transactionRequest.getCreateAt());
        bodyRequest.put("appCode", Config.APP_CODE);
        bodyRequest.put("hashCode", hashCode);
        return bodyRequest.toString();
    }

    private JSONObject getJSONTransaction(TransactionRequest transactionRequest, String command) {
        JSONObject obj = new JSONObject();
        obj.put("command", command);
        obj.put("cardCode", transactionRequest.getCardCode());
        obj.put("owner", transactionRequest.getOwner());
        obj.put("cvvCode", transactionRequest.getCvvCode());
        obj.put("dateExpired", transactionRequest.getDateExpired());
        obj.put("transactionContent", transactionRequest.getTransactionContent());
        obj.put("amount", transactionRequest.getAmount());

        return obj;
    }

    private JSONObject getJsonToHashCode(JSONObject jsonTransaction){
        JSONObject obj = new JSONObject();
        obj.put("secretKey", Config.SECRET_KEY);
        obj.put("transaction", jsonTransaction);
        return obj;
    }

    public Object resetBalance() {
        return null;
    }

}
