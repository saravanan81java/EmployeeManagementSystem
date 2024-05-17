import React from "react";

const Department = ({ department }) => {
  return (
    <tr key={department.departmentNameId}>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{department.departmentName}</div>
      </td>
     
    </tr>
  );
};

export default Department;