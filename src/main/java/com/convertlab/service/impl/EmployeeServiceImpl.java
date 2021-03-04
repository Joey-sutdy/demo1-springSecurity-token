package com.convertlab.service.impl;

import com.convertlab.entity.Employee;
import com.convertlab.mapper.EmployeeMapper;
import com.convertlab.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author limei
 * @since 2021-03-02
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
