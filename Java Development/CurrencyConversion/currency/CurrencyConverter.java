package currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrencyConverter {
    private static final String API_BASE_URL = "https://api.exchangerate.host/latest?base=";

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter the base currency: ");
            String baseCurrency = sc.nextLine().toUpperCase();
            System.out.println("Enter the target currency: ");
            String targetCurrency = sc.nextLine().toUpperCase();
            System.out.println("Enter the amount to convert: ");
            BigDecimal amount = sc.nextBigDecimal();


            String urlString = API_BASE_URL + baseCurrency;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(urlString).get().build();

            try (Response response = client.newCall(request).execute()) {
                String strResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(strResponse);

                if (jsonObject.has("rates")) {
                    JSONObject rateObject = jsonObject.getJSONObject("rates");
                    if (rateObject.has(targetCurrency)) {
                        BigDecimal rate = rateObject.getBigDecimal(targetCurrency);
                        BigDecimal result = rate.multiply(amount);

                        System.out.println("Converted amount: " + result.setScale(2, BigDecimal.ROUND_HALF_UP)
                                + " " + targetCurrency);
                    } 
                    else {
                        System.out.println("Error: Target currency not found in the rates");
                    }
                } 
                else {
                    System.out.println("Error: Unable to fetch exchange rates");
                }
            }
        } 
        catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
            e.printStackTrace();
        } 
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } 
        finally {
            sc.close();
        }
    }

}
