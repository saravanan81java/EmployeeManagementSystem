import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import Department from "./Department";

const DepartmentList = () => {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [departments, setDepartments] = useState(null);

  const [isToggled, setIsToggled] = useState(false);

  const handleToggle = () => {
      setIsToggled(!isToggled);
  };

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
       
      </div>
      <div className="flex shadow border-b">
        <table className="min-w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Department Name
              </th>
            </tr>
          </thead>
          {!loading && (
              <tbody className="bg-white">
                  {departments.map((department) => (
                      <Department 
                      department={department}
                      
                      key={department.departmentId}></Department>
                  ))}
              </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default DepartmentList;