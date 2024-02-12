package com.examination.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * 新しい従業員を作成するために送信するリクエストを表すクラスです.
 *
 * @param firstName 従業員の名前
 * @param lastName  従業員の名字
 */
public record CreateEmployeeRequest(
    @NotBlank
    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("firstName")
    String firstName,

    @NotBlank
    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("lastName")
    String lastName
) {
}
