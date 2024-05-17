import axios from "axios";

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employees";
const DEPARTMENT_API_BASE_URL = "http://localhost:8080/api/v1/departments";

class EmployeeService {

  saveDepartment(department) {
    return axios.post(DEPARTMENT_API_BASE_URL, department);
  }

  saveEmployee(employee) {
    return axios.post(EMPLOYEE_API_BASE_URL, employee);
  }

  getEmployees() {
    return axios.get(EMPLOYEE_API_BASE_URL);
  }

  getEmployeeBySalary(salary, flag) {
    return axios.get(EMPLOYEE_API_BASE_URL + "/salary/" + salary +"/" + flag);
  }

  getEmployeeByDepartment(departmentId) {
    return axios.get(EMPLOYEE_API_BASE_URL + "/department/" + departmentId );
  }

  getDepartments() {
    return axios.get(DEPARTMENT_API_BASE_URL);
  }

  deleteEmployee(id) {
    return axios.delete(EMPLOYEE_API_BASE_URL + "/" + id);
  }
  
  getEmployeeById(id) {
    return axios.get(EMPLOYEE_API_BASE_URL + "/" + id);
  }

  updateEmployee(employee, id) {
    return axios.put(EMPLOYEE_API_BASE_URL + "/" + id, employee);
  }
}

export default new EmployeeService();