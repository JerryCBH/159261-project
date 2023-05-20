import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class ChatGPTConnector {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";

    public static String SendRequestToChatGPT(String prompt) {
        try {
            System.out.println("Before HTTP Client Chatgpt...");

            OkHttpClient client = new OkHttpClient();

            System.out.println("After HTTP Client Chatgpt...");

            // Prepare the request body
            JSONObject json = new JSONObject();
            json.put("model", "text-davinci-002");
            json.put("prompt", prompt);
            json.put("max_tokens", 50); // Adjust as needed

            RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));

            System.out.println("Create request for Chatgpt...");

            // Prepare the request
            Request request = new Request.Builder()
                    .url(API_ENDPOINT)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .post(body)
                    .build();

            System.out.println("Calling Chatgpt...");

            // Send the request and get the response
            Response response = client.newCall(request).execute();

            System.out.println("Receving response from Chatgpt...");

            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String ExtractGeneratedMessage(String response) {
        JSONObject json = new JSONObject(response);
        return json.getJSONArray("choices").getJSONObject(0).getString("text");
    }
}
