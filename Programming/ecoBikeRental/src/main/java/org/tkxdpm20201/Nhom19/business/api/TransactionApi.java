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
import org.tkxdpm20201.Nhom19.utils.DateUtil;

import java.io.IOException;

public class TransactionApi {

    /**
     * run process transaction
     * @param transactionRequest
     * @return response transaction
     * @throws IOException
     */
    public TransactionResponse processTransaction(TransactionRequest transactionRequest) throws IOException {
        HttpResponse httpResponse = requestHTTP(transactionRequest);
        return getTransactionResponse(httpResponse);
    }

    /**
     * request api through HttpClient
     * @param transactionRequest : body content request of transaction
     * @return response from http
     * @throws IOException
     */
    private HttpResponse requestHTTP(TransactionRequest transactionRequest) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPatch httpPatch = new HttpPatch(Config.PROCESS_TRANSACTION_URL);
        String bodyRequest = getBodyRequest(transactionRequest);
        httpPatch.setEntity(new StringEntity(bodyRequest, ContentType.APPLICATION_JSON));

        HttpResponse httpResponse = httpClient.execute(httpPatch);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
        return httpResponse;
    }

    /**
     *  get object transactionResponse
     * @param httpResponse
     * @return
     * @throws IOException
     */
    private TransactionResponse getTransactionResponse(HttpResponse httpResponse) throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(apiOutput, TransactionResponse.class);
    }

    private String getHashCode(String jsonString){
        return DigestUtils.md5Hex(jsonString);
    }


    private String getBodyRequest(TransactionRequest transactionRequest){
        JSONObject transaction = getJSONTransaction(transactionRequest);
        String stringToHash = getJsonToHashCode(transaction).toString();
        String hashCode = getHashCode(stringToHash);
        JSONObject bodyRequest = new JSONObject();

        bodyRequest.put("version", Config.API_VERSION);
        bodyRequest.put("transaction", transaction);
        bodyRequest.put("appCode", Config.APP_CODE);
        bodyRequest.put("hashCode", hashCode);
        return bodyRequest.toString();
    }


    private JSONObject getJSONTransaction(TransactionRequest transactionRequest) {
        JSONObject obj = new JSONObject();
        obj.put("cardCode", transactionRequest.getCardCode());
        obj.put("owner", transactionRequest.getOwner());
        obj.put("cvvCode", transactionRequest.getCvvCode());
        obj.put("dateExpired", transactionRequest.getDateExpired());
        obj.put("command", Config.PROCESS_TRANS);
        obj.put("transactionContent", transactionRequest.getTransactionContent());
        obj.put("amount", transactionRequest.getAmount());
        obj.put("createdAt", DateUtil.format(transactionRequest.getCreatedAt()));
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
