package presentation.response;

import domain.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeResponseTest {
    @Test
    void 任意のEmployeeに対してEmployeeResponseが作成できる() {
        Employee employee = new Employee("1", "Taro", "Yamada");
        EmployeeResponse actual =  EmployeeResponse.createResponse(employee);

        EmployeeResponse expected = new EmployeeResponse("1", "Taro", "Yamada");

        assertEquals(expected, actual);
    }
}
