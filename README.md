# sendmessage
启动步骤：<br>
1.确保jar包全部下载好；<br>
2.在单元测试 EmailServiceImplTest 类中的 sendBatchEmail方法运行，可进行测试；<br>
3.SendMessageApplication类启动  会用定时器的方式于每天7:00定时执行方法并发送当天生日的邮件；<br>
4.可在resources/templates下的emp.txt增加或修改人员信息（姓名、邮件地址）。
