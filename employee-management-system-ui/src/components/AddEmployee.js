import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";

const AddEmployee = () => {
  const [employee, setEmployee] = useState({
    id: "",
    empName: "",
    salary: "",
    department: {"departmentId":null, "departmentName":""}
  });
  const [loading, setLoading] = useState(true);
  const [departments, setDepartments] = useState([]);
  const navigaye = useNavigate();


  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await EmployeeService.getDepartments();
        setDepartments(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };
    fetchData();
  }, []);

  const handleSelect = (e) => {
    const { options } = e.target;
    const selectedOption = options[e.target.selectedIndex];
    const innerHTML = selectedOption.innerHTML;
    employee.department.departmentId = e.target.value;
    employee.department.departmentName = innerHTML;
  };

 
  const handleChange = (e) => {
    const value = e.target.value;
    setEmployee({ ...employee, [e.target.name]: value });
  };

  const saveEmployee = (e) => {
    e.preventDefault();
    
    if(employee.empName == '') {
      alert("Employee name field is required");
      return;
    }

    if(employee.salary == '') {
      alert("Salary field is required");
      return;
    }

    if(employee.department.departmentName == '') {
      alert("Department name field is required");
      return;
    }

    EmployeeService.saveEmployee(employee)
      .then((response) => {
        console.log(response);
        navigaye("/employeeList");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setEmployee({
      id: "",
      empName: "",
      salary: "",
      department: {"departmentId":null, "departmentName":""}
    });
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add New Employee</h1>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Employee Name
          </label>
          <input
            type="text"
            name="empName"
            value={employee.empName}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"></input>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Salary
          </label>
          <input
            type="text"
            name="salary"
            value={employee.salary}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"></input>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Department
          </label>
          <select onChange={(e) => handleSelect(e)}>
            <option value="">Select...</option>
            {departments.map((option, index) => (
              <option key={index} value={option.departmentId}>
                {option.departmentName}
              </option>
            ))}
          </select>
        </div>

        <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
          <button
            onClick={saveEmployee}
            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6">
            Save
          </button>
          
          <button
            onClick={reset}
            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6">
            Clear
          </button>

          <button
            onClick={() => navigaye("/employeeList")}
            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6">
            Cancel
          </button>

        </div>
      </div>
    </div>
  );
};

export default AddEmployee;