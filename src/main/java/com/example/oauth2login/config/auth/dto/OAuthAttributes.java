package com.example.oauth2login.config.auth.dto;

import com.example.oauth2login.domain.Role;
import com.example.oauth2login.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String name,
                           String email,
                           String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    /**
     * OAuth2User 에서 사용자 정보를 맵 형태로 반환하기 떄문에 dto 형태로 둔다.
     * @param registrationId
     * @param userNameAttributeName
     * @param attributes
     * @return
     */
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
              .attributes(attributes)
              .nameAttributeKey(userNameAttributeName)
              .name(attributes.get("name").toString())
              .email(attributes.get("email").toString())
              .picture(attributes.get("picture").toString())
              .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
