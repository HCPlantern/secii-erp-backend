package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.EmployeeDao;
import com.nju.edu.erp.dao.UserDao;
import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.po.User;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.humanResource.EmployeeVO;
import com.nju.edu.erp.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDAO;
    private final UserDao userDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDAO, UserDao userDAO) {
        this.employeeDAO = employeeDAO;
        this.userDAO = userDAO;
    }

    /**
     * 添加员工，同时自动创建一个user账号
     *
     * @param employeeVO
     * @return 自动创建的账号, 目前用户名等同于员工姓名,默认密码123456
     */
    public UserVO addEmployee(EmployeeVO employeeVO) {
        EmployeePO employeePO = new EmployeePO();
        BeanUtils.copyProperties(employeeVO, employeePO);
        employeeDAO.addEmployee(employeePO);
        User user = new User(null, employeeVO.getName(), "123456", employeeVO.getJob(), 0, null, employeePO.getId());
        userDAO.createUser(user);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    public List<EmployeeVO> queryAllEmployees() {
        List<EmployeeVO> employeeVOList = employeeDAO.findAllEmployees();
        return employeeVOList;
    }

    /**
     * 更新员工信息，必须有id，其中“角色”信息在数据库使用触发器级联更新user表
     *
     * @param employeeVO
     */
    public void updateEmployeeInfo(EmployeeVO employeeVO) {
        EmployeePO employeePO = new EmployeePO();
        BeanUtils.copyProperties(employeeVO, employeePO);
        employeeDAO.updateEmployeeInfo(employeePO);
    }

    /**
     * 根据员工id删除employee表中员工，在数据库使用触发器级联删除user表中账号
     *
     * @param id
     */
    public void deleteEmployeeById(int id) {
        employeeDAO.deleteEmployeeById(id);
    }

}
