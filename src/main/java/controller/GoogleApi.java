package controller;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Lists;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.SneakyThrows;
import model.WhiskeyBottle;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import repository.BaseRepository;
import repository.BaseRepositoryFactory;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.ApplicationProperties.getProperties;

public class GoogleApi {
    private static final String CREDENTIALS_FILEPATH = "/credentials.json";


    @SneakyThrows
    private static HttpRequestInitializer getCredentials() {
        InputStream in = GoogleApi.class.getResourceAsStream(CREDENTIALS_FILEPATH);
        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(Lists.newArrayList(Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)));

        return new HttpCredentialsAdapter(credentials);

    }

    @SneakyThrows
    public static void reed() {
        final String spreadSheetsId = getProperties().getProperty("spreadSheetsId");
        final String spreadSheetsRange = getProperties().getProperty("spreadSheetsRange");
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Sheets service = new Sheets.Builder(httpTransport, JacksonFactory.getDefaultInstance(), getCredentials())
                .setApplicationName("Viscarnya_bot")
                .build();
//        Spreadsheet spreadsheetMetadata = service.spreadsheets().get(spreadSheetsId).execute();
//        List<Sheet> sheets = spreadsheetMetadata.getSheets();
//        sheets.forEach(sheet -> System.out.println(((SheetProperties) (sheet.get("util"))).get("title")));
        ValueRange valueRange = service.spreadsheets().values()
                .get(spreadSheetsId, spreadSheetsRange)
                .execute();
        List<List<Object>> values = valueRange.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("EMPTY SHEETS!!!");
        }
        BaseRepository whiskeyBase = BaseRepositoryFactory.of(WhiskeyBottle.class);
        for (List row : values) {
            if (row.isEmpty()) {
                continue;
            }
            WhiskeyBottle whiskeyBottle = new WhiskeyBottle();
            whiskeyBottle.setNameBottle(String.valueOf(row.get(0)));
            whiskeyBottle.setIcon(new InputFile().setMedia(loadIcon((String) row.get(1)), whiskeyBottle.getNameBottle()));
            whiskeyBottle.setDescription(String.valueOf(row.get(2)));
        }

    }

    public static InputStream loadIcon(String url) throws Exception {
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                                            JacksonFactory.getDefaultInstance(),
                                            getCredentials())
                .setApplicationName("viskarnya-bot")
                .build();

        HttpResponse resp = service.getRequestFactory().buildGetRequest(new GenericUrl(new URI(url))).execute();
        return resp.getContent();
    }


}
