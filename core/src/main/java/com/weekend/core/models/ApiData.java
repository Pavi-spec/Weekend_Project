package com.weekend.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.client.fluent.Request;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ApiData {

    /* =========================
       Dialog Field
       ========================= */
    @ValueMapValue
    private String sectionTitle;

    /* =========================
       API Data List
       ========================= */
    private List<UserPojo> users = new ArrayList<>();

    /* =========================
       INIT METHOD
       ========================= */
    @PostConstruct
    protected void init() {

        System.out.println(">>> MODEL INIT STARTED");

        try {

            /* =========================
               STEP 1: CALL API
               ========================= */
            String json = Request.Get("http://localhost:4503/bin/mockapi")
                    .connectTimeout(5000)
                    .socketTimeout(5000)
                    .execute()
                    .returnContent()
                    .asString();

            System.out.println("RAW JSON RESPONSE: " + json);

            /* =========================
               STEP 2: PARSE JSON
               (YOUR API RETURNS ARRAY)
               ========================= */
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            /* =========================
               STEP 3: VALIDATE ARRAY
               ========================= */
            if (root != null && root.isArray()) {

                System.out.println("ARRAY SIZE: " + root.size());

                for (JsonNode node : root) {

                    String name = node.has("name") ? node.get("name").asText() : "";
                    String email = node.has("email") ? node.get("email").asText() : "";
                    String phone = node.has("phone") ? node.get("phone").asText() : "";

                    users.add(new UserPojo(name, email, phone));
                }
            } else {
                System.out.println("JSON is NOT an array");
            }

            System.out.println("FINAL USERS SIZE: " + users.size());

        } catch (Exception e) {

            System.out.println("❌ ERROR IN MODEL:");
            e.printStackTrace();
        }

        System.out.println(">>> MODEL INIT END");
    }

    /* =========================
       GETTERS FOR HTL
       ========================= */
    public String getSectionTitle() {
        return sectionTitle;
    }

    public List<UserPojo> getUsers() {
        return users;
    }
}