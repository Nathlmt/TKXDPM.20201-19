package org.tkxdpm20201.Nhom19.subsystem.interbank;

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
import org.tkxdpm20201.Nhom19.data.entities.Card;
import org.tkxdpm20201.Nhom19.data.model.TransactionRequest;
import org.tkxdpm20201.Nhom19.data.model.TransactionResponse;
import org.tkxdpm20201.Nhom19.utils.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;

public class InterbankBoundary {
    /**
     * request api through HttpClient
     * @param transactionRequest : body content request of transaction
     * @return response from http
     * @throws IOException
     */
    public HttpResponse requestHTTP(TransactionRequest transactionRequest) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch(org.tkxdpm20201.Nhom19.subsystem.interbank.Config.PROCESS_TRANSACTION_URL);
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
     *
     * @param transactionRequest
     * @return
     */
    private String getBodyRequest(TransactionRequest transactionRequest){
        JSONObject transaction = getJSONTransaction(transactionRequest);
        String stringToHash = getJsonToHashCode(transaction).toString();
        String hashCode = getHashCode(stringToHash);
        JSONObject bodyRequest = new JSONObject();

        bodyRequest.put("version", org.tkxdpm20201.Nhom19.subsystem.interbank.Config.API_VERSION);
        bodyRequest.put("transaction", transaction);
        bodyRequest.put("appCode", org.tkxdpm20201.Nhom19.subsystem.interbank.Config.APP_CODE);
        bodyRequest.put("hashCode", hashCode);
        return bodyRequest.toString();
    }

    /**
     *
     * @param transactionRequest
     * @return
     */
    private JSONObject getJSONTransaction(TransactionRequest transactionRequest) {
        JSONObject obj = new JSONObject();
        Card card = transactionRequest.getCard();
        obj.put("cardCode", card.getCardCode());
        obj.put("owner", card.getOwner());
        obj.put("cvvCode", card.getCvvCode());
        obj.put("dateExpired", card.getDateExpired());
        obj.put("command", transactionRequest.getCommand());
        obj.put("transactionContent", transactionRequest.getTransactionContent());
        obj.put("amount", transactionRequest.getAmount());
        obj.put("createdAt", DateUtil.format(LocalDateTime.now()));
        return obj;
    }

    /**
     *
     * @param jsonTransaction
     * @return
     */
    private JSONObject getJsonToHashCode(JSONObject jsonTransaction){
        JSONObject obj = new JSONObject();
        obj.put("secretKey", Config.SECRET_KEY);
        obj.put("transaction", jsonTransaction);

        return obj;
    }

    /**
     *
     * @param jsonString
     * @return
     */
    private String getHashCode(String jsonString){
        return DigestUtils.md5Hex(jsonString);
    }

    /**
     *  get object transactionResponse
     * @param httpResponse
     * @return
     * @throws IOException
     */
    protected TransactionResponse getTransactionResponse(HttpResponse httpResponse) throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(apiOutput, TransactionResponse.class);
    }
}
