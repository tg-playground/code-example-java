package com.taogen.example.jsonparser.gson.entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author Taogen
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForGson {
    private Long id;

    private String name;

    private Integer age;

    /**
     * Change the field name: Using Gson @SerializedName.
     * Format date:
     * 1) Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
     * 2)registerTypeAdapter(Date.class, MyDateJsonSerializer);
     */
    @SerializedName(value = "birth_date")
    private Date birthDate;

    private List<RoleForGson> roles;
}
