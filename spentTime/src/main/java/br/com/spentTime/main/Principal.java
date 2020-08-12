package br.com.spentTime.main;

import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.spentTime.model.WorkLog;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Principal {
	public static String login;
	public static String tokenApi;
	public static void main(String[] args) {
		if (StringUtils.isAllBlank(args)) {
			System.out.println("Preencher parametros");
			return;
		}
		if (args.length < 2) {
			System.out.println("Preencher email e tokenApi");
			return;
		}
		
		login = args[0];
		tokenApi = args[1];
		if (args.length < 3) {
			System.out.println("Necessario informar planilha xls");
			return;
		}
		login = login+":"+tokenApi;
		login = Base64.getEncoder().encodeToString(login.getBytes());
		List<WorkLog> listW = ReadSpreadSheet.setWorkLog(args[2]);
		
		listW.forEach(wl -> {
			Response response = Principal.request(wl, login);
			System.out.println(response != null ? response : "Nao foi possivel apontar o tempo.");
		});
	}

	private static Response request(WorkLog wl, String login) {
		if (wl != null && !StringUtils.isBlank(wl.getIssue())) {
			
			try {
				Gson gson = new GsonBuilder()
						.setExclusionStrategies(new ExclusionGson(wl.getClass(), "issue"))
						.create();
				String json = gson.toJson(wl);
				OkHttpClient client = new OkHttpClient().newBuilder()
						.build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, json);
				Request request = new Request.Builder()
						.url("https://your-domain.atlassian.com/rest/api/3/issue/" + wl.getIssue() + "/worklog")
						.method("POST", body)
						.addHeader("Authorization", "Basic " + login)
						.addHeader("Content-Type", "application/json")
						.build();
				Response response = client.newCall(request).execute();
				if (response.code() != 201) {
					System.out.println(json);
				}
				return response;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
