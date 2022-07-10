# 企业ERP系统测试文档

## 0.目录

[TOC]

## 1.简介

### 1.1 项目背景

- 本项目是一个企业ERP系统，详细内容请参照企业ERP系统用例文档、企业ERP系统需求规格说明文档、企业ERP系统体系结构设计文档、企业ERP系统详细设计文档

- 本项目集成了前端Vue框架+后端SpringBoot框架，因此本项目的测试从前端和后端两个方面进行

### 1.2 测试目的

- 前端页面渲染

- 后端数据库交互

- 后端接口完成度

- 后端接口正确性

- 后端接口之间的交互

- 前端与后端的交互

### 1.3 测试环境

- 后端运行于SpringBoot框架上，故后端测试采用Junit与Mockito和SpringbootTest进行单元测试与集成测试。
  - SpringBoot版本：v2.4.1
  - Mybatis版本：v2.1.4
  - Junit版本：v4.13.2
  - Mockito版本：v2.23.4

- 前端运行于Vue框架上，故前端测试采用postman进行测试。

### 1.4 测试范围

- 后端
  - Controller层
    - 集成测试
    - 单元测试
    - 接口测试
  - Service层
    - 单元测试
    - 桩测试
  - Dao层
    - 单元测试
    - 集成测试
    - 接口测试

- 前端
  - 页面渲染测试
  - postman接口测试

### 1.5 参考资料

- 网络上的Junit教程、Mockito教程

- postman的使用方法

## 2.测试计划

### 2.1 后端测试

1. 单元测试
   1. 后端根据前端的API调用进行响应，所以主要测试后端的Controller包中的各个接口，对每一个方法进行调用，并进入数据库查找传入的资料或输出的资料并进行一一比对验证正确性。

1. 集成测试
   1. 调用后端Controller包中的各个方法，对各个模块(Service、Dao)进行集成，测试各个代码模块之间的交互性与正确性。

1. 接口测试
   1. 主要使用postman工具测试后端Controller包中定义的各个接口。

### 2.2 前端测试

1. UI测试
   1. 主要测试每个页面的渲染效果

1. 接口测试
   1. 主要使用postman测试前端的各个接口

## 3.后端集成测试

### 3.1 测试方向

- 测试后端集成了Controller、Service、Dao之后各个模块之间的交互情况以及正确性。

### 3.2 测试准备

- 重新运行sql文件确保数据库已经被初始化，不容易受到外部数据的干扰

- 关闭正在运行的后端服务避免测试中途从外部传入新的数据而影响测试的结果

### 3.3 测试用例

#### 3.3.1 MockCollectionControllerTest

- 测试前的准备
  - 在测试类上添加@RunWith(SpringRunner.class)、@SpringBootTest、@AutoConfigureMockMvc注解，用于集成测试和使用MockMvc进行测试
  - 注入MockMvc类的对象mockMvc和ObjectMapper类的对象objMapper

- 测试集成的模块
  - CollectionController
  - CollectionService
  - CollectionDao

##### 3.3.1.1 testCreateCollectionSheet

- 测试编号 B000001
  - 目标方法
    - CollectionController.makeCollectionSheet

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NDc5MGQwYzE5ZTBmNjRmYjRjMmZjOWUyNjQ5YzFhODBfd2xoWlVPbVp2WXA3ZWlHZ05hcnc0N0x0eUo0T0MzUEpfVG9rZW46Ym94Y24xeHNmekc5SDAyRndYcXI5ZGVqMWlZXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YmRhMDE4NDFlNmY3MzNhOWY1NTFjM2Q3Nzk5MmUyZGFfMlFCNGliRUhrdWhjWEJleFZQYjhqN2FHSzY2eHpsa0ZfVG9rZW46Ym94Y25USmhyN3FvcmJSalJHVWk1dng1TzJiXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.1.2 testApproveCollectionSheet

- 测试编号 B000002
  - 目标方法
    - approveCollectionSheet

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NTIxNzliNGE2ZjdlZWNkMzFhNWNmYTIyOGJmZGJhMzhfdXBFeHduZHdnYnZDR0pwSTZzZm9LNXlrSDV3SE1GdTBfVG9rZW46Ym94Y25FUEg3RDNjQXdzUmYwdXJ5RGsyWGd1XzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MGIzYTBkN2Y0ZDhmMWJjNjgxMjdkN2E3YTFkOWFiNWJfRkZ0RDRpZHVkWHVXRHR4VkRTdlFZZ0FhZEprcWtrU05fVG9rZW46Ym94Y25zeEd5ZzN6eG1BcjJWRFJ3dTV3SVNkXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.1.3 testFindAllCollectionSheet

- 测试编号 B000003
  - 目标方法
    - findAllCollectionSheetByState

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmNiMDZhZmMxOWQxY2ZlODZjODUwYTkwMDA0Y2E2YzNfVkhTbnhDQzNYakhCNmp3M2hRZEg0aXg3NjFGQXA1eGFfVG9rZW46Ym94Y255b2YxR0xjWnU4TEhjZFhjMGswNUh0XzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OGI1ZGNiNDlmMWJhZTc0NzFhOGQ1NDFmOWY1YmJmYjRfU28ySTlubWxKaWVRYjh1V0tqQTZJQVZIT3ZVSWxHVm9fVG9rZW46Ym94Y243VEwzbkNlczdPU09YczVyRFdKNlpKXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 3.3.2 MockCompanyAccountControllerTest

- 测试前的准备
  - 在测试类上添加@RunWith(SpringRunner.class)、@SpringBootTest、@AutoConfigureMockMvc注解，用于集成测试和使用MockMvc进行测试
  - 注入MockMvc类的对象mockMvc和ObjectMapper类的对象objMapper

- 测试集成的模块
  - CompanyAccountController
  - CompanyAccountService
  - CompanyAccountDao

##### 3.3.2.1 testCreateCompanyAccount

- 测试编号 B000004
  - 目标方法
    - CompanyAccountController.createAccount

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NGMxODcxOTk2MzQzY2NhOGJkNzkyNzBmYzU1ODYzYzFfdFFBY21FM3hJazJMUUt0aDZ2NmxVbnRHancyMldlYzJfVG9rZW46Ym94Y25yMlRHSW9XMUxVUjJRUnRZeG9ET0ViXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YmVhM2U5NzZmYTcxMDQyNzBkYTExNWJhNWYyMTgwN2Jfa3R5MHd3NXBxVmVoZXhtRFpKSTJQcG9QNkNSMVNtQlBfVG9rZW46Ym94Y25Za2cydkkwOHJRbnVYTFF2VmpPWVVoXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.2.2 testFindCompanyAccountByName

- 测试编号 B000005
  - 目标方法
    - CompanyAccountController.findCompanyAccountByName

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NjU4ZmEwYTY5OWI2ZjgwNTcwNTIwNTg4MTIwOWVhZDlfWjBqRGN4WFM5cE9vR2dZZnlaRHFRZWJjZzZDU0YxWDZfVG9rZW46Ym94Y25keXE1RjRiWW9iaXV4R1VtbFFhcThjXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MTQxMzdiZmU5NjA1YzBhZDQ5OTZjNmY2MzI2ODkzMWZfNGkxWU9EbGZMSVp0dFRlWGFrMmtrZTVrT0NFVHJBNDlfVG9rZW46Ym94Y244ekkwZ2ZmUHRoUGdFa3kzTW9wQVdkXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.2.3 testUpdateCompanyAccount

- 测试编号 B000006
  - 目标方法
    - CompanyAccountController.updateCompanyAccount

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=M2Q3YzViYWUyM2NlNTRkMmIyMjc3M2JmM2EwYTkxNzZfQVN1WDZDS3BoZGcwQ1JISU5sc0dKRlZFbEdIS2xJeWZfVG9rZW46Ym94Y244Y3hRdEV0bklsaGZHdHBsTnRDbGhlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MzI3ZjZmM2Q5NmM1NGY5YWNlM2EyZDU0YjMxMDNlODBfMzVjbmM5bmFrZzhtUnpJUnNQcnoxRnRXZzlXRDB0WFVfVG9rZW46Ym94Y25GdXlnNDBWY1ZWcDhYZ2JjeEdidTBtXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.2.4 testDeleteCompanyAccountById

- 测试编号 B000007
  - 目标方法
    - CompanyAccountController.deleteCompanyAccountById

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YmQwZTkxYzJkZGQ4MjFkNzhiZjcyN2YzNzk1Nzg4OTRfVVMydU1kYmgyUktteG1GQkEzRFIycWJoV0FDWGk5dnlfVG9rZW46Ym94Y25qWGx4Z0ZVclRBYjYxZUt5VHVZYjhkXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NTlhNzAxYTZhODhkOTY3OWI4NTAzZDZiMWQ5ZWQ0MGFfV0x0eEg1VkYxR0IyY3RGdXhEYWlFeHJtQVpGbDN5MjlfVG9rZW46Ym94Y251cU1GcUVaV0t6MTNOWTlCbGtGWjhnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 3.3.3 MockPaymentControllerTest

- 测试前的准备
  - 在测试类上添加@RunWith(SpringRunner.class)、@SpringBootTest、@AutoConfigureMockMvc注解，用于集成测试和使用MockMvc进行测试
  - 注入MockMvc类的对象mockMvc和ObjectMapper类的对象objMapper

- 测试集成的模块
  - PaymentController
  - PaymentService
  - PaymentDao

##### 3.3.3.1  testMakePaymentSheet

- 测试编号 B000008
  - 目标方法
    - PaymentController.makePaymentSheet

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjgxYTZjZWYxZjg4ZjczY2IyNzk4NGY1ZWM5NzliMTZfSzlMbTBPYU15M2haV1M3YXQyR1dEbU5NOUhqUFVEalRfVG9rZW46Ym94Y25JMGtuemJZQWlwV3I3MTN4elBNVk9mXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTVmNWZmMmU1MjdjM2I1ZmFlYWJjNTczYjkyMjJkNTFfZHllVjA1TmIyWDBFbFdSbVFwZTVkWVp3dHNGdjZJVDBfVG9rZW46Ym94Y254YzRyeWlZbEphbUF2OWROdUxCOG1jXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.3.2 testApprovePaymentSheet

- 测试编号 B000009
  - 目标方法
    - PaymentController.approvePaymentSheet

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NDRiZjc1Y2ZlMmY2MmVlNWNjZjFhZGZmY2JhZWZlMmZfTDlmVVhFWmZOakFVelFJTmJ0VUVIeEl1cGQwWlRhV0dfVG9rZW46Ym94Y25OZnZwa3JqaVRqUXNORnNENzAzRThnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWRkYjIwMTIwYjI3MWU4OTBjNjc3NTZhNGZiMjEwN2NfQmRCa0lYUVpqVDlLTEw2NTBYdXF5Mk1hRzZreEdJaUVfVG9rZW46Ym94Y242VkRtOHUxNzRGTzgyVEJ2VWd1QVpmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.3.3 testFindPaymentSheet

- 测试编号 B000010
  - 目标方法
    - PaymentController.findAllPaymentSheetByState

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDdlZGJiNjQ0MmYxOWNlOGE2MjRhZjVlYjlhY2UwMjBfQ1Q4MHllRFYzZEEwMFRIcm1ESTY4aVdQYUs4MzJXZ1hfVG9rZW46Ym94Y25SS0plTENuN1ZDZUg3aDlHTDFiR2xlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmI4ZDg2YTg4Y2RhZWZlZWNlZGVhMWZiZWI3YWM4YjNfTjZMaFB5RXVOUjdaVlkyVzIxZTJLaTVBZUE4RDhtbmRfVG9rZW46Ym94Y25sR2F6NHdxbUhiUzBaUXJ5dVdyTVdlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 3.3.4 MockFinanceControllerTest

##### 3.3.4.1 testGetFinancialReport

- 测试编号 B000011
  - 目标方法
    - FinanceController.getFinancialReport

- 测试代码

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OTRiZDJkODcwYjkyYTQ2YTcwNDYyNGZkOWFiNjY2MjFfRlJKU3hvV29PZ3l4bTJHR2pWU2hESGQ4ZkFEUDdTTDhfVG9rZW46Ym94Y25tNlhwRDVqTjVLZ3JLc2NZd1UwdGliXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2EzNjE2ZTUwNTk2MDk0ZjcwZDg3MGY4Mjg0MGQzYjVfRGNYeHFuSVh3alIyN3NSdVl3eUxvMkdzTnVqUU0xSENfVG9rZW46Ym94Y25iSzZ3Q0NPTUpka2FIUm5ZUnB5NU1jXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 3.3.5 MockJobControllerTest

##### 3.3.5.1 testCreateDepartmentSalaryRule

- 测试编号 B000012
  - 目标方法
    - JobController.createDepartmentSalaryRule

- 测试代码

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MGQ1ZjRlMjYyYWZmOGZhZjFiOTRlYzk1ODBiMjM3ZWRfNlA3Mk53dXdlR2VzVGtzemJiUjV0WmgxSW9ISlFoMVRfVG9rZW46Ym94Y25xdDdiYWtKTXB3d2Frck5qWlprODRnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OWI4NjVkODhiYTBlOWRjNjMwM2VhNmI5MGQzYjc0ODdfa0p6Z29SYzZpWHphQUdBeHVkWjcyMk1CNTJFeFBhY1JfVG9rZW46Ym94Y25rWmZiMnU2ZHFycUpISHhEaTl0bmdmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 3.3.6 MockSalaryControllerTest

##### 3.3.6.1 testQueryAllSalaryRules

- 测试编号 B000013
  - 目标方法
    - SalaryController.queryAllSalaryRules

- 测试代码

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NGFhNzc4YTlmYzIwZjMxZDY4YzcwYWVhYjM1ZjU4NTZfZFUzZmV4WHRidzhTNHBJYVNXT3dkVFk5N0ZHaFV0R0dfVG9rZW46Ym94Y25ibTJDOGlZZEZJQnhyeHVjRzFQbVViXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MDdlYzBlYTM5OTQ5MmY5N2M3ZWNjZTlhMzg1NmI0ZDRfU1pJcjQ0S1VYTXNBSU1kRE1MNlFWS0RBRzZnNGdFUHBfVG9rZW46Ym94Y242ckYwa3llS3JNU2g4ZW9zelB3VXJlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.6.2 testUpdateDepartmentSalaryRule

- 测试编号 B000014
  - 目标方法
    - SalaryController.updateDepartmentSalaryRule

- 测试代码

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTk5YzIyMmM4N2RhOWQ3ZjI4ZWVhYmFkN2NkODhlZTZfRHR3R3cxZ05SZVJGc3FCR29GNXU0amJib0g3Uk9BTzdfVG9rZW46Ym94Y25PcGdxQlhxYU54SDNzajVVOGlza3RnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YTNjZTk1MmE0MjdlYzEyY2RmYjcyYTM1ZDI2NDZhZjlfZWszZ3BqeWtnOVY3TnNGeXVSanBDWnJpSUUxTjhCUVNfVG9rZW46Ym94Y25Lb2liMGxEbnpEbzRkWWpIbmlydjVnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 3.3.6.3 testGenerateSalarySheet

- 测试编号 B000015
  - 目标方法
    - SalaryController.generateSalarySheet

- 测试代码

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OTQyZThiNjY4MDRlMDZlNzRkYjg1NzE2YTU1NGVmM2RfaDFiREpSdDZYY0RRODd6TGpHWXNUOTdrSWhOaVAzQURfVG9rZW46Ym94Y25ZdTJPNzVaNHpTczZJR1dycVZyMzJjXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NmM2NTE4ZjY0MTVlZDMyOTBhZTBlZWE3NjExNmRkYjNfSXdmZ1B4MEtWVUNIckpWMEJGZDI0SzdPTzRKZUFlTFFfVG9rZW46Ym94Y25yc0xURHFqbW1JZUJaeFcyc3QxcXpmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 3.3.7 MockSheetControllerTest

##### 3.3.7.1 testFindAllSheet

- 测试编号 B00016
  - 目标方法
    - SheetController.findAllSheet

- 测试代码

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MjIzNzM3YTY0ZWI3YWNhOGU5ODBiYjg0NmE2MjliODRfSllVVVcwbEluanF5QVFUNTVaYVZTU2NiZ0N1emR4M1hfVG9rZW46Ym94Y24xNVpLRDJJQjhxcDE3cXBxMmRDVWllXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YzY5MTE5NzY2YTU4YjAyNGI3ZTZkYzE1MDg1YWUyNjVfU3JEbGhDTVJ3MHBRSGdJZWFBRjh6S016NEcyVGNxNFRfVG9rZW46Ym94Y25aNGZrTnN1TlkzNlZOR1pibzF6bFhlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

## 4.后端单元测试

### 4.1 测试方向

- 测试Service包中具体类的方法，单元测试、集成测试和桩测试同时进行
  - 使用桩测试的类
    - CategoryService
    - CollectionService
    - CompanyAccountService
    - PaymentService
  - 使用集成测试的类
    - CustomerService
    - ProductService
    - SaleService
    - WareHouseService

### 4.2 测试准备

- 重新运行sql文件确保数据库已经被初始化，不容易受外部数据干扰。

- 关闭正在运行的后端避免测试途中外部传入新数据影响测试结果。

### 4.3 测试用例

#### 4.3.1 MockPaymentServiceTest

- 测试前的准备
  - 在测试类上添加@RunWith(MockitoJUnitRunner.class)，用于使用Mockito进行桩测试
  - 使用@InjectMocks注解表示注入需要调用真实方法的对象，使用@Mock注解表示注入需要调用虚拟方法的对象(MockObject)

- MockObject
  - PaymentSheetDao paymentSheetDao

##### 4.3.1.1 testFindAllPaymentSheetByState

- 测试编号 B000011
  - 目标方法
    - paymentService.findAllPaymentSheetByState
  - mock的方法(stub)
    - paymentSheetDao.findAllPaymentSheetByState
    - paymentSheetDao.findAllPaymentSheetContentById

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjE2NjZhMjRmNTkwOTliY2MyYmYwY2JlYzg5MGVjY2NfWG1LSmRvejR2QW80bHhiVmR4Y3NIV2RnUkN3Sk5PdXVfVG9rZW46Ym94Y25HeEZtSnBLZVljaFJqaWNhVXRGb3NkXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NzIxNDFkMDM4NmI2MWJmYWE1NjU4ZmU4MTM2MTk5MzZfTXVCZ2R6cmpVdnMzOHN2eDJhM3ZCMzBMVVk3Q2pnUDJfVG9rZW46Ym94Y25DS2xhTFNqbUg5ZkVPYWN1bkJ6Y1hnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 4.3.2 MockCompanyAccountServiceTest

- 测试前的准备
  - 在测试类上添加@RunWith(MockitoJUnitRunner.class)，用于使用Mockito进行桩测试
  - 使用@InjectMocks注解表示注入需要调用真实方法的对象，使用@Mock注解表示注入需要调用虚拟方法的对象(MockObject)

- MockObject
  - PaymentSheetDao paymentSheetDao

##### 4.3.2.1 testCreateAccount

- 测试编号 B000012
  - 目标方法
    - collectionService.findAllCollectionSheetByState
  - mock方法(stub)
    - collectionDao.findAllCollectionSheetByState

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=Yjc5NmJmYjA3ZmJlZTViNGRmMzIzNTVlZGJkMWE4YmRfM1ZxZ1ZyQWgzb3dFR0tFNXNNSE1VbGtLT2dGbGZaWDJfVG9rZW46Ym94Y25ZTUFlaHliZEd0U0RhdGhtNVlrdDRjXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NDAzYWUxNWMwZTZmNDNjNjAxMDZjYzk2Njc0YjBmMzNfQlplM2FLd2ZGcUoyVWQ3WkFjT2h4M3pySmdRZGVlaUxfVG9rZW46Ym94Y25yNnR4aVJacTZGc3M0eXdJM21PSDZlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.2.2 testFindCompanyAccountByName

- 测试编号 B000013
  - 目标方法
    - companyAccountService.findCompanyAccountByName
  - Mock方法
    - companyAccountDao.findCompanyAccountByName

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MGQ1NmMxYmIyNTM1Nzc4NTc2OTM4MTNjMjk2NmIxZDJfTzNabGxLZFpYa0Jwck1UbXM3SUNXd2gxRW41V1VFQ1RfVG9rZW46Ym94Y25jUFlPc1QyNmRZWEY0UVhGc092Y2RjXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YjQ4MzMzYzk3NmNkMGJjM2NhMzJiOGQ0NzdkN2Q5OWNfc1J4eW9sYWtzMkRqNXczZ0ZEQndFOGNBUUxjNDV3aU1fVG9rZW46Ym94Y24wSk53TmptdWUzb3E3RTF4VHlHdGhmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.2.3 testDeleteCompanyAccountById

- 测试编号 B000014
  - 目标方法
    - companyAccountService.deleteCompanyAccountById
  - Mock方法
    - companyAccountDao.deleteCompanyAccountById

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MWJhODY0NTE5ZGFkNTk5MWQzMzljYWJhOTlhNWQ0MDBfSVZkSGxHeW9FTUJOUHE1UVZ3M0Z0aG56Vjg1U21WTzBfVG9rZW46Ym94Y25Qb0lSOHpsd2FLalMzVjZ0dVlsZHdmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YTkzNzI5NjdjNTk3ODgzNDk5Y2Y3M2Y0ODAxNTUyZTBfQk1vWm5BTUhuallGQmNxVFJxUkNmOHFDOGxuMDhiUnNfVG9rZW46Ym94Y253MkZjMGI4RkVmeHVWZ1NUZGh1eFljXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.2.4 testUpdateCompanyAccount

- 测试编号 B000015
  - 目标方法
    - companyAccountService.updateCompanyAccount
  - Mock方法
    - companyAccountDao.updateCompanyAccount

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NTYxNDRhMGY3YTY2ZWE0YTg2OTIyN2ExYmRhNmVhZjJfbE9IbUtBbGN3a05zZ2ZTWHY1MHQ5VWp0aElPVFJ4RVVfVG9rZW46Ym94Y25PbmkyREtPWnpYWHlXMWxGcEdkb3FiXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmQ3NDVjZDg4ZWI4ZjJlN2U4OGUzZTNlMjMwZDIyMTRfd2IzWG8zeVdnQVVtTTRERzJXenZkOUdWVDR3WFN2aHRfVG9rZW46Ym94Y240bW93RkhDODVIVVRINnBhcURqOVZiXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 4.3.3 MockCollectionServiceTest

- 测试前的准备
  - 在测试类上添加@RunWith(MockitoJUnitRunner.class)，用于使用Mockito进行桩测试
  - 使用@InjectMocks注解表示注入需要调用真实方法的对象，使用@Mock注解表示注入需要调用虚拟方法的对象(MockObject)

- MockObject
  - CollectionDao collectionDao

##### 4.3.3.1 testFindAllCollectionSheetByState

- 测试编号 B000016
  - 目标方法
    - collectionService.findAllCollectionSheetByState
  - Mock方法
    - collectionDao.findAllCollectionSheetContent
    - collectionDao.findAllCollectionSheetByState

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTMyODc4ZDI2YzNkODViYzk0NTVjZGIxOGE2MTM2ZDNfcHJPbWNoZ3ZQc0JkVlNyWk9MbU9SakhodTNnZ2xCQmZfVG9rZW46Ym94Y25xa3V4S0E1NlhSSzM5NXVndFZVUGpkXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDE2MWZmNDE2ZGQ0NGFkMDAyYTYwODgwYjBiYzg4NTNfZ2JRZHhodnZFWGlFbGs0bnZ4QVhUUzhFNFJRd3Q5aDFfVG9rZW46Ym94Y24yaWZHTFhBbGt5ZEZLUEFJQXBZbndoXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 4.3.4 MockCategoryServiceTest

- 测试前的准备
  - 在测试类上添加@RunWith(MockitoJUnitRunner.class)，用于使用Mockito进行桩测试
  - 使用@InjectMocks注解表示注入需要调用真实方法的对象，使用@Mock注解表示注入需要调用虚拟方法的对象(MockObject)

- MockObject
  - CategoryDao categoryDao1

##### 4.3.4.1 testFindAllCategory

- 测试标号 B000017
  - 目标方法
    - categoryService1.queryAllCategory
  - Mock方法
    - categoryDao1.findAll

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MTQ5NGIzNGE5MmVjYWQ3NmJkMzYzYzc3NDcwZWVmNTFfMXp0M1BYNWNRQ2t5UExWVHRrd2RZNFpVUnRjMjdOSWRfVG9rZW46Ym94Y25QV1FYS1pwdmpOOTRnWHZWalRIVHhmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MDdiZTE5OGRlOTk1MzQ0NDY4M2U1N2RjNmJjZGExZjRfTWs3Y01DaVVIUURtT0o1MEU2ZVFYb2JPMHZSMHFxZ2lfVG9rZW46Ym94Y25pd3lIQjlyZnhCcHB0amRUeEdsRmFmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 4.3.5 CustomerServiceTest

##### 4.3.5.1 testUpdateCustomer

- 测试编号 B000018
  - 目标方法
    - customerService.updateCustomer

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ODZkNTVjMDlkODhhMDgyYmI5OWRkMDBkMjY3ODgwMWNfQ0tYcmhPTFNNQXJvZDNWb2JTTG1Xd3BWd0JYYmJBYXVfVG9rZW46Ym94Y252anFhTmNya0ZlbDcwdlB4d0xGeFVjXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YzY5YzkyNzEyODYwNWRlYzFkMzJiYWUxNGY5ZDI4MzBfYkZBcHdxMW1IYktESmF6T3QxNHJlMkpMaGtkOXdMajRfVG9rZW46Ym94Y25PNDJCb1hGUkhMWDRHUjVPM3RadmJlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.5.2 testGetCustomersByType

- 测试编号 B000019
  - 目标方法
    - customerService.getCustomersByType

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWNkZTgzYTJmMDBjMjBlYmQ0ZGYwNTQ3M2FiYTllMGJfdzVteUZqeFpKT3lBdGxYNHNjTVQ4UEozblZnblNyckFfVG9rZW46Ym94Y245VVFQWjAzTFpPODhnQ1dTSk9mMUNoXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2ZmMzUxNzA1NDNmMTY3Y2NhMGQ1NmUyNWM4ZGFiNDRfZEVMNE02aXJJeDBUcmxTd3FyR0xJSUJ1bk1tYnNZbWxfVG9rZW46Ym94Y255dkNSUDg2RGcwcTR5M1dvUnBIenBnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.5.3 testFindCustomerById

- 测试编号 B000020
  - 目标方法
    - customerService.findCustomerById

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmUwZGE3NjRhYjZkNDE0MmY5ZWVjY2ZmOTQ3NGQ3MDlfRGFKOVZDWHBSSHN3VlpFY2NWWDNjTkJZWVFZcFpKUmhfVG9rZW46Ym94Y25ha0ZvR0dsWlVMZWRMdjJwbHZCcmNjXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NTAwMTE1OTRhNjI3ODA3ODI4ZDBkOTk1ZjAwZTc0MzlfcENyVnBKanJTQXl1SzNlYjhSR3RMQmZObUE4M1hYNUxfVG9rZW46Ym94Y25GUzlPdEpUZ3JPb1pPVU5hS0xlU1djXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.5.4 testCreateCustomer

- 测试编号 B000020
  - 目标方法
    - customerService.createCustomer

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OWM5MzFhYzI5NGZlYzg3NWRkMTY5Y2ViYTA3ODY5ZWZfT2l1SnJ5NVJnc0dYMTJWY1ZkTk5TRllmM1Z2akwwRFBfVG9rZW46Ym94Y25UbU9sMDg3U2ZtQkNpYjF0NVh6d1FlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmE1YWI4MGNkYjE0Mzc4M2E3NzA0N2IwODVhNjA2NmFfQVVPaWlTNE9idHpKWkxycThreUUzaHM3NEdPSk8zUXJfVG9rZW46Ym94Y25YWVVyN2NLRUF2RzJITkRnMVZ3OFFjXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.5.5 testDeleteCustomer

- 测试编号 B000021
  - 目标方法
    - customerService.deleteCustomer

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NTUyOGMxZDQ1YjA5MGU5NmQ4MTgwOGZjNGFmNzc5MmFfdDY1MFlqUzIycE9vOEZXSUNCTVRWVUF1UVR0VzQySDlfVG9rZW46Ym94Y25obk5waFRGYWpYTGY0Wkt4STgxandmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NGU4NDYwZTYwN2NlNTJiMDAyMTRiNjRmNDVlMWNmNDhfSzd2WE1RNXNIV2dreTNvaEh2Z3lBNGhISUsycjh4bURfVG9rZW46Ym94Y25SWTVZVmYzelZLbWQ5RUV5NmRybnRlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

#### 4.3.6 SaleServiceTest

##### 4.3.6.1 makeSaleSheet

- 测试编号 B000022
  - 目标方法
    - saleService.makeSaleSheet
    - saleSheetDao.getLatestSheet

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MzUxNzMxYmI2MjQ1YmM2YTJmMWRjNTZhNGFhM2M4ZDJfTm5HSFM1c3dZVHhOOXJyczNVdzluTEx2a2lSd2FGbk1fVG9rZW46Ym94Y25xVURPTXA2NWN4aWNjV09BUWtWbE5iXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YmU0OTBiMzZhYTg3ODNiYTM2NjM2YTUzNDNhMjZiMDVfNnJkV0V6UjBIMUJQNDVPUDB0R2ZVSHAzbGpWMkpoRzFfVG9rZW46Ym94Y25Vc0l3VThXTkRrVThKNlNXazduMHNlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.6.2 getSaleSheetByState

- 测试编号 B000023
  - 目标方法
    - saleService.getSaleSheetByState

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NzJiOGFlOWZjMjY4YWU4YzA3MWNkZWYxMWE1MjU3MWJfdVNFV21pNXNneXo5azE5elU4cFdhVTlRWnJZOFRDOFpfVG9rZW46Ym94Y245VGUxOUFWT1I1UjlzMEducHJZTXhTXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NDQyNTU4OTYzMTI3ZTRlYTAzOTY3ZGJkZWNmOThlZDNfb0h4cmJPZDVVZ2ZJMkJ5SUN1UUQzNzZ1WTcyUkszZE1fVG9rZW46Ym94Y25qaFFjb2pnQ0JvcFVCY0hsUHJkU1pjXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.6.3 approval_exceptions_1

- 测试编号 B000024
  - 目标方法
    - saleService.approval

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjM2ODZiZjZhMDBkNDY0NDhjN2RmZTExMmE5NGRkNDBfck5FRDBlRW5yT1JDM1cwSklQTUJZYkpqM211WG1qaUhfVG9rZW46Ym94Y25Vc1dCcEtqQ3JFYW9GVkZWVGVBcW1mXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDBiMTZmNWVkNDI2OThhMWI5YmVlMTVhODdjMzIxMzdfWkRoZHdaTmxmTHRYR25kd3JhRWlVQjlrYW9aVFN3QmZfVG9rZW46Ym94Y25qMGdzZjRKZ1ZiRFhRU25OM2R1MUljXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.6.4 approval_exceptions_2

- 测试编号 B000025
  - 目标方法
    - saleService.approval

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MGI0ODc0NjI3ZTlkZTUwMTY5NGM4ZGE1ZGU3YmJmM2VfN1Nodzd0aDZYOVkwN2tyNFVkM29pYmhpSmJNTjZIbDZfVG9rZW46Ym94Y25CZ2VQTVBjNGN4MUdtZnBmanFVMlZmXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGEwMmZhNDIwMmE0ZTliNDA5MTMwMzk5MjE3YmQzMWZfbDc5V2tqY2g0RzVwdFk0UVVodHBRM016NEtRMXFIWmJfVG9rZW46Ym94Y25tTHZVVDYxdWpWSFRHWUdGblpORVBlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.6.5 approval_failed

- 测试编号 B000026
  - 目标方法
    - saleService.approval

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MGIyYjhmZjVjY2MyZjY5ZTM1ZmM1ZTAyYjliMzdiNmFfbkhiV0VvUzRWWXpYVGJFbWtpRHp3RjJLMlBGbnpUSzBfVG9rZW46Ym94Y25OMk54MEY0MUhjdUF5WDBaRFZTNkVlXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NjVlNTk5YzIzOGRmZWY3NThmNjYwMjRhYzVkZmU1OGFfRG5TdWVUcFd1YmNaRTNkdmQ1UGtiNDFRNm1YUkJqd05fVG9rZW46Ym94Y25LNGtFSkRUYTlRYzNzZnBRSExwWktnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.6.6 approval_1

- 测试编号 B000027
  - 目标方法
    - saleService.approval

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MDEwOGUyYjBjNzgxMjI5OTRlMjI5ZTliZWQ1N2IzMjFfWWhoZk8xQmdaT2JoelQ3R1JvVGtvRFdIVDZGSW4ycU1fVG9rZW46Ym94Y25jU2Rkd0xJRGZWZGlWU0o4T04zWFVkXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWM5YjY3ZDdlNzljNzYwZDdhNzk4YjYyYmI3NWQzYzNfZE9sMlgwaElnMG1kMHRQUG94dWYzVDFrbzlyV2Vjc0VfVG9rZW46Ym94Y25QeFRKUFZsaVcxMVlIbUhtQW5ORVFoXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

##### 4.3.6.7 approval_2

- 测试编号 B000028
  - 目标方法
    - saleService.approval

- 测试代码
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ODY5ZGYzMjVkODFiZTRiNjFiNmQ1MWIzZmE4OWU3M2FfZDhkMGx4bnozY2VhNm1LTjZ2Y1VuaG5SeW55YUdRODNfVG9rZW46Ym94Y25laGdjNjd1V2FhbG13MnZWbXd3TmpnXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

- 测试结果
  - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OGFiZDdkYmM5ZjQyMTNkZmIyNjBkMzk0ZjRmNTg3MDdfM0xUU09Gcm9JNDVFVUtZaGE0R2dUU2xEdzVYWkV1b3hfVG9rZW46Ym94Y242M1FBQk5BOXlQSGo1b1FQRmR4SVNiXzE2NTc0NTA3Mzk6MTY1NzQ1NDMzOV9WNA)

## 5.前端接口测试

使用Postman进行接口测试