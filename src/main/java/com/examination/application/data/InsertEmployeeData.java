package com.examination.application.data;

/**
 * 従業員を新規登録するためのデータを保持します.
 *
 * @param firstName 追加したい従業員の名前
 * @param lastName  追加したい従業員の名字
 */
public record InsertEmployeeData(String firstName, String lastName) {
}
