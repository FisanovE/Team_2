import client.TeamTwoClient;
import com.google.gson.Gson;
import java.io.IOException;

public class Main {
	private static TeamTwoClient client;
	static Gson gson = new Gson();

	public static void main(String[] args) throws IOException, InterruptedException {
		client = new TeamTwoClient("ya.praktikum.fvds.ru:8080/dev-day");
		String json = gson.toJson("mock");
		client.register("key", json);
	}
}