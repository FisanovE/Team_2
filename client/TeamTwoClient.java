package client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class  TeamTwoClient{
	private final String url;
	private final String token;

	public TeamTwoClient(String url) throws IOException, InterruptedException {
		this.url = url;
		this.token = "verySecretToken";
	}

	public void register(String key, String json) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		URI uriSave = URI.create(url + "/register");

		HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
		HttpRequest requestSave = HttpRequest.newBuilder()
											 .uri(uriSave)
											 .header("Content-type", "application/json")
											 .header("MAIN_ANSWER", "ответ на главный вопрос Жизни, Вселенной и Всего Остального")
											 .POST(body)
											 .build();

		HttpResponse<String> responseSave = client.send(requestSave, HttpResponse.BodyHandlers.ofString());
	}

	public void put(String key, String json) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		URI uriSave = URI.create(url + "/save/" + key + "?API_TOKEN=" + token);

		HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(json);
		HttpRequest requestSave = HttpRequest.newBuilder().uri(uriSave).header("Content-type", "application/json")
											 .POST(body).build();

		HttpResponse<String> responseSave = client.send(requestSave, HttpResponse.BodyHandlers.ofString());
	}

	public String load(String key) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		URI uriLoad = URI.create(url + "/load/" + key + "?API_TOKEN=" + token);
		HttpRequest requestLoad = HttpRequest.newBuilder().uri(uriLoad).GET().build();

		HttpResponse<String> responseLoad = client.send(requestLoad, HttpResponse.BodyHandlers.ofString());
		return responseLoad.body();
	}
}