package cn.com.sendmessage.service.impl;


import cn.com.sendmessage.common.FileTransformObjectUtils;
import cn.com.sendmessage.entity.Emp;
import cn.com.sendmessage.service.EmpService;

import java.util.List;

/**
 * @Auther X .
 */
public class EmpTxtServiceImpl implements EmpService {

    // 通过txt获取emp
    @Override
    @SuppressWarnings("unchecked")
    public List<Emp> getEmp() {

        return (List<Emp>) FileTransformObjectUtils.fileTransformObject("classpath:templates/emp.txt", Emp.class);
    }
}
