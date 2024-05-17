import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import Employee from "./Employee";

const EmployeeList = () => {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [employees, setEmployees] = useState([]);
  const [isToggled, setIsToggled] = useState(false);
  const [departments, setDepartments] = useState([]); 

  const fetchData = async () => {
    setLoading(true);
    try {
      const response = await EmployeeService.getEmployeeBySalary(10000, isToggled);
      setEmployees(response.data);
      setIsToggled(!isToggled);
    } catch (error) {
      console.log(error);
    }
    setLoading(false);
  };

  const fetchAllData = async () => {
    setLoading(true);
    try {
      const response = await EmployeeService.getEmployees();
      setEmployees(response.data);
      setIsToggled(false);
    } catch (error) {
      console.log(error);
    }
    setLoading(false);
  };

  const fetchByDepartData = async (departmentId) => {
    setLoading(true);
    try {
      const response = await EmployeeService.getEmployeeByDepartment(departmentId);
      setEmployees(response.data);
      //setIsToggled(!isToggled);
    } catch (error) {
      console.log(error);
    }
    setLoading(false);
  };

  const getDepartmentData = async () => {
      setLoading(true);
      try {
        const response = await EmployeeService.getDepartments();
        setDepartments(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    }; 

  useEffect(() => { 
    getDepartmentData(); 
    fetchData();
  }, []);


  const handleSelect = (e) => { 
    fetchByDepartData(e.target.value);
  }

  const deleteEmployee = (e, id) => {
      e.preventDefault();
      EmployeeService.deleteEmployee(id).then((res) => {
          if(employees) {
              setEmployees((prevElement) => {
                  return prevElement.filter((employee) => employee.id !== id);
              })
          }
      });
  }

  return (
    <div className="container mx-auto my-8">
      <div className="h-12">
        <button
          onClick={() => navigate("/addDepartment")}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold">
          Add Department
        </button>

      &nbsp;
        <button
          onClick={() => navigate("/addEmployee")}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold">
          Add Employee
        </button>

      &nbsp;
        <button className={`rounded bg-slate-600 text-white px-6 py-2 font-semibold`} onClick={fetchAllData}>
            ALL
        </button> 

      &nbsp;
        <select className={`rounded bg-slate-600 text-white px-6 py-2 font-semibold`} onChange={(e) => handleSelect(e)}>
          <option value="">Select...</option>
          {departments.map((option, index) => (
            <option key={index} value={option.departmentId}>
              {option.departmentName}
            </option>
          ))}
        </select>
      &nbsp;

        <button className={`toggle-button ${isToggled ? 'active' : ''}`} onClick={fetchData}>
            {isToggled ? '<10K' : '>10K'}
        </button>    
      </div>
      <div className="flex shadow border-b">
        <table className="min-w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Employee Id
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Employee Name
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
              Salary
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
              Department
              </th>
              <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Actions
              </th>
            </tr>
          </thead>
          {!loading && (
              <tbody className="bg-white">
                  {employees.map((employee) => (
                      <Employee 
                      employee={employee}
                      deleteEmployee={deleteEmployee} 
                      key={employee.id}></Employee>
                  ))}
              </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default EmployeeList;