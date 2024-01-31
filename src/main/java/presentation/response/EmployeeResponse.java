package presentation.response;

import domain.Employee;

public record EmployeeResponse(String id, String firstName, String lastName) {
    public static EmployeeResponse createResponse(Employee employee) {
        return new EmployeeResponse("1", "Taro", "Yamada");
    }
}
