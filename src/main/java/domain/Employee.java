package domain;

/**
 * 従業員を表すオブジェクト。
 *
 * @param id        従業員を識別する個別のID
 * @param firstName 従業員の名前
 * @param lastName  従業員の名字
 */
public record Employee(String id, String firstName, String lastName) {
}
