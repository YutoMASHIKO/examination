package com.examination.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * 従業員情報を更新するためのリクエストを表します.
 *
 * @param firstName 更新する授業員の名前
 * @param lastName  更新する授業員の名字
 */
public record UpdateEmployeeRequest(
    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("firstName")
    String firstName,

    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("lastName")
    String lastName
) {

}
