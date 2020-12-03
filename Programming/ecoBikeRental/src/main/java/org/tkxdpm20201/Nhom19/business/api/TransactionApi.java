package org.tkxdpm20201.Nhom19.business.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.tkxdpm20201.Nhom19.persistence.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.persistence.model.TransactionResponse;

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
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }

        //Now pull back the response object
        HttpEntity httpEntity = httpResponse.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);

        System.out.println(apiOutput);

        ObjectMapper om = new ObjectMapper();
        TransactionResponse transactionResponse = om.readValue(apiOutput, TransactionResponse.class);

        System.out.println(transactionResponse.getErrorCode());

        // TODO: continue something
        return null;
    }

    private String getHashCode(String jsonString){
        return DigestUtils.md5Hex(jsonString);
    }

    private String getBodyRequest(TransactionRequest transactionRequest){
        JSONObject transaction = getJSONTransaction(transactionRequest, Config.PROCESS_TRANS);
        String stringToHash = getJsonToHashCode(transaction).toString();
        String hashCode = getHashCode(stringToHash);
        JSONObject bodyRequest = new JSONObject();

        bodyRequest.put("version", Config.API_VERSION);
        bodyRequest.put("transaction", transaction);
//        bodyRequest.put("appCode", Config.APP_CODE);
        bodyRequest.put("appCode", "CWr2Fgjdclc=");
        bodyRequest.put("hashCode", hashCode);
        return bodyRequest.toString();
    }

    private JSONObject getJSONTransaction(TransactionRequest transactionRequest, String command) {
        JSONObject obj = new JSONObject();
        obj.put("cardCode", transactionRequest.getCardCode());
        obj.put("owner", transactionRequest.getOwner());
        obj.put("cvvCode", transactionRequest.getCvvCode());
        obj.put("dateExpired", transactionRequest.getDateExpired());
        obj.put("command", command);
        obj.put("transactionContent", transactionRequest.getTransactionContent());
        obj.put("amount", transactionRequest.getAmount());
//        obj.put("createdAt", DateUtil.format(transactionRequest.getCreatedAt()));
        obj.put("createdAt", "2020-11-30 10:55:00");
        return obj;
    }

    private JSONObject getJsonToHashCode(JSONObject jsonTransaction){
        JSONObject obj = new JSONObject();
//        obj.put("secretKey", Config.SECRET_KEY);
        obj.put("secretKey", "BLSSBlwOmxo=");
        obj.put("transaction", jsonTransaction);

        return obj;
    }

    public Object resetBalance() {
        return null;
    }

}
