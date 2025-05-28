package org.vitorAlves.infra.webapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.vitorAlves.domain.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookWebAPI {
    private final String BASE_URL = "https://openlibrary.org";
    private final int successCode = 200;
    private final ObjectMapper mapper = new ObjectMapper();

    public BookWebAPI() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public Book fetchBookByIsbn(String isbn){
        try {
            String json = getResponseJson(BASE_URL + "/isbn/" + isbn + ".json");
            JsonNode root = mapper.readTree(json);

            JsonNode node = root.get("authors");
            List<JsonNode> keysNode = new ArrayList<>();
            if (node != null) node.forEach(keysNode::add);

            List<String> authors = new ArrayList<>();
            for (JsonNode authorKey : keysNode) {
                String author = fetchAuthor(authorKey);
                authors.add(author);
            }

            Book book = mapper.readValue(json, Book.class);

            book.setAuthors(authors);
            book.setIsbn(isbn);

            return book;
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching book data: " + e.getMessage());
        }
    }

    @NotNull
    private String getResponseJson(String requestURI) throws IOException {
        URL url = new URL(requestURI);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if(conn.getResponseCode() != successCode) {
            throw new RuntimeException("HTTP error code: " + conn.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while((inputLine = reader.readLine()) != null) content.append(inputLine);
        reader.close();

        return content.toString();
    }

    private String fetchAuthor(JsonNode node) {
        try {
            String json = getResponseJson(BASE_URL + node.get("key").asText() + ".json");
            JsonNode root = mapper.readTree(json);

            String personalName = root.get("personal_name").toString();
            
            if (personalName.contains(",")) {
                String[] name = root.get("personal_name")
                        .toString().replace('"', ' ').split(",");

                String firstName = name[1].replaceAll("\\.", "");
                String lastName = name[0].trim();

                return (firstName + lastName).trim();
            }
            
            return personalName;
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching author data: " + e.getMessage());
        }
    }
}
