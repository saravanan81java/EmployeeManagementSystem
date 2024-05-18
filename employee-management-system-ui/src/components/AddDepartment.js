import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";

const AddDepartment = () => {
  const [department, setDepartment] = useState({
    departmentId: "",
    departmentName: ""
  });

  const navigaye = useNavigate();

  const handleChange = (e) => {
    const value = e.target.value;
    setDepartment({ ...department, [e.target.name]: value });
  };

  const saveDepartment = (e) => {
    e.preventDefault();
     
    if(department.departmentName == '') {
      alert("Department name field is required");
      return;
    }
    
    EmployeeService.saveDepartment(department)
      .then((response) => {
        console.log(response);
        navigaye("/departmentList");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setDepartment({
      departmentId: "",
      departmentName: ""
    });
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add New Department</h1>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Department Name
          </label>
          <input
            type="text"
            name="departmentName"
            value={department.departmentName}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"></input>
        </div>
       
       

        <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
          <button
            onClick={saveDepartment}
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

export default AddDepartment;