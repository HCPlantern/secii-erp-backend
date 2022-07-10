# 企业ERP系统详细设计描述文档

## 小组分工

| 姓名   | 学号      | 分工                         |
| ------ | --------- | ---------------------------- |
| 韩陈旭 | 201250037 | 财务模块的业务逻辑层分解     |
| 万沛沛 | 201250038 | 人力资源模块业务逻辑层的分解 |
| 邓尤亮 | 201250035 | 财务模块业务逻辑层的分解     |
| 华广松 | 201840309 | 总经理模块业务逻辑层的分解   |

## 文档修改历史

| 修改日期 | 修改人员 | 修改原因                             | 版本号 |
| -------- | -------- | ------------------------------------ | ------ |
| 2022.7.1 | 韩陈旭   | 创建企业ERP系统详细设计描述文档      | v.1.0  |
| 2022.7.2 | 韩陈旭   | 添加财务模块的模块概述和整体结构     | v.1.0  |
| 2022.7.2 | 邓尤亮   | 添加财务模块的模块概述和整体结构     | v.1.1  |
| 2022.7.3 | 万沛沛   | 添加人力资源模块的模块概述和整体结构 | v.1.2  |
| 2022.7.5 | 华广松   | 添加总经理模块的模块概述和整体结构   | v.1.3  |
| 2022.7.8 | 韩陈旭   | 添加模块对应的系统顺序图             | v.1.4  |
| 2022.7.9 | 万沛沛   | 修改人力资源模块整体结构             | v.1.5  |

## 0. 目录

[TOC]

## 1. 引言

### 1.1 编制目的

本文档详细完成了对企业ERP系统的详细设计，达到了指导后续软件构造的目的，同时实现开发人员、测试人员以及用户的沟通。

本文档面向开发人员、测试人员及最终用户而编写，是了解系统的导航。

### 1.2 词汇表

| 词汇名称 | 词汇含义    | 备注 |
| -------- | ----------- | ---- |
| ERP      | 企业ERP系统 |      |

### 1.3 参考资料

[1]. 企业ERP系统用例文档

[2]. 企业ERP系统需求规格说明

[3]. 企业ERP系统软件体系结构文档

[4]. 骆斌，丁二玉，刘钦. 软件工程与计算.卷二,软件开发的技术基础.volume Ⅱ, Fundamentals of software development technology[M]. 机械工业出版社, 2012.

## 2.产品概述

参考企业ERP系统用例文档和企业ERP系统软件需求规格说明文档中对于产品的概括描述。

## 3.体系结构设计概述

参考企业ERP系统体系结构设计文档对于体系结构设计的概述。

## 4.结构视角

### 4.1 业务逻辑层的分解

业务逻辑层的开发包图参见企业ERP系统体系结构设计文档中的图3。

#### 4.1.1 财务模块

(1) 模块概述

- 财务模块模块承担的需求参见需求规格说明文档功能需求以及相关的非功能需求。

- 财务模块模块的职责以及接口参见软件体系结构描述文档表

(2) 整体结构

根据体系结构的设计，我们将系统分成展示层、业务逻辑层、数据层。每一层之间为了增加灵活性、可修改性，我们都添加了接口，按照接口编程，使得设计更加符合接口隔离、依赖倒置等设计模式。

在展示层和业务逻辑层中(financeservice)，我们设计了CommonSheetOperation、CompanyAccountService、CollectionService、PaymentService、FinanceService等接口，提供了账户管理、制定收款单、制定付款单、查看销售明细表等方法的接口，展示层和业务逻辑层之间我们设计了对应的VO对象，用于前后端的数据传递。

在业务逻辑层和数据层之间，我们定义了Dao接口提供查询所有单据信息的的方法接口，设计了CollectionSheetDao、PaymentSheetDao等接口提供对单据的增删改查方法接口，业务逻辑层和数据层之间我们设计了对应的PO对象，用于将数据保存到数据库。

为了隔离业务逻辑职责和逻辑控制职责，我们增添了一些Controller类，这样对于具体业务逻辑的处理会委托给对应的service类对象，Controller类起到了分派器的作用。

财务模块的设计如下图所示：

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YzJiZjUwMWNkNGVlZDZjZWI0MmFmMjNiNWJjYjg1MGRfa1E4aXRidlV5NmVNa09lWURtcDBidEdKWklueFlMYW9fVG9rZW46Ym94Y24yZzBwSDBxNlQ2b3dWejBnQzg0b0FoXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

financebl模块各个类的职责如下表所示

| 模块                     | 职责                                                         |
| ------------------------ | ------------------------------------------------------------ |
| CollectionController     | 负责通过CollectionSheetService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| CompanyAccountController | 负责通过CompanyAccountService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| FinanceController        | 负责通过FinanceService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| InitAccountController    | 负责通过InitAccountService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| PaymentController        | 负责通过PaymentSheetService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| SheetController          | 负责通过SheetService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| CommonSheetOperation     | 将所有单据的审批功能抽象成一个统一的接口                     |
| PaymentService           | 提供付款单增删改查的接口                                     |
| CollectionSheetService   | 提供收款单增删改查的接口                                     |
| FinanceService           | 提供查询经营历程表的接口                                     |
| SheetService             | 提供查询销售明细表的接口                                     |
| CompanyAccountService    | 提供公司账户增删改查的接口                                   |
| InitAccountService       | 提供期初建账创建和查询的接口                                 |
| CollectionDao            | 负责与数据库交互，实现收款单的增删改查                       |
| CompanyAccountDao        | 负责与数据库交互，实现银行账户的增删改查                     |
| FinanceDao               | 负责与数据库交互，实现财务报告的查询                         |
| InitAccountDao           | 负责与数据库交互，实现期初建账的创建与查询                   |
| PaymentDao               | 负责与数据库交互，实现付款单的增删改查                       |
| SheetDao                 | 负责与数据库交互，实现销售明细的查询                         |
| CollectionPO             | 收款单的持久层对象，与数据库表一一映射                       |
| CompanyAccountPO         | 公司银行账户的持久层对象，与数据库表一一映射                 |
| FinancePO                | 财务报告的持久层对象，与数据库表一一映射                     |
| InitAccountPO            | 期初信息的持久层对象，与数据库表一一映射                     |
| PaymentPO                | 付款单的持久层对象，与数据库表一一映射                       |
| SheetPO                  | 销售明细的持久层对象，与数据库表一一映射                     |
| CollectionVO             | 收款单的展示层对象，用于前后端传递数据                       |
| CompanyAccountVO         | 公司银行账户的展示层对象，用于前后端传递数据                 |
| FinanceVO                | 财务报告的展示层对象，用于前后端传递数据                     |
| InitAccountVO            | 财务报告的展示层对象，用于前后端传递数据                     |
| PaymentVO                | 付款单的展示层对象，用于前后端传递数据                       |
| SheetVO                  | 销售明细的展示层对象，用于前后端传递数据                     |

(3) 模块内部类的接口规范

展示层与逻辑层之间的接口：

1. CollectionController的接口规范

| 提供的服务(供接口)                                 |                                                          |                                                              |
| -------------------------------------------------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| CollectionController.makeCollectionSheet           | 语法                                                     | public Response makeCollectionSheet(@RequestBody CollectionSheetVO collectionSheetVO) |
| 前置条件                                           | 用户请求创建收款单的API                                  |                                                              |
| 后置条件                                           | 调用CollectionService的makeCollectionSheet方法           |                                                              |
| CollectionController.approveCollectionSheet        | 语法                                                     | public List<CompanyAccountVO> findCompanyAccountByName(String name); |
| 前置条件                                           | 用户请求审批收款单的API                                  |                                                              |
| 后置条件                                           | 调用CollectionService的approveCollectionSheet方法        |                                                              |
| CollectionController.findAllCollectionSheetByState | 语法                                                     | public void deleteCompanyAccountById(Integer id);            |
| 前置条件                                           | 用户请求根据状态查询收款单的API                          |                                                              |
| 后置条件                                           | 调用CollectionService的findAllCollectionSheetByState方法 |                                                              |
| CollectionController.findBySheetId                 | 语法                                                     | public void updateCompanyAccount(CompanyAccountVO companyAccountVO); |
| 前置条件                                           | 用户请求更具编号查询收款单的API                          |                                                              |
| 后置条件                                           | 调用CollectionService的findBySheetId方法                 |                                                              |

1. CompanyAccountController的接口规范

| 提供的服务(供接口)                                |                                                        |                                                              |
| ------------------------------------------------- | ------------------------------------------------------ | ------------------------------------------------------------ |
| CompanyAccountController.createAccount            | 语法                                                   | public Response createAccount(@RequestBody CompanyAccountVO companyAccountVO) |
| 前置条件                                          | 用户请求创建公司银行账户的API                          |                                                              |
| 后置条件                                          | 调用CompanyAccountSevice的createAccount方法            |                                                              |
| CompanyAccountController.findCompanyAccountByName | 语法                                                   | public Response findCompanyAccountByName(@RequestParam(required = false) String name) |
| 前置条件                                          | 用户请求根据名称查询公司银行账户的API                  |                                                              |
| 后置条件                                          | 调用CompanyAccountSevice的findCompanyAccountByName方法 |                                                              |
| CompanyAccountController.deleteCompanyAccountById | 语法                                                   | public Response deleteCompanyAccountById(@RequestParam Integer id) |
| 前置条件                                          | 用户请求删除指定id的银行账户的API                      |                                                              |
| 后置条件                                          | 调用CompanyAccountSevice的deleteCompanyAccountById方法 |                                                              |
| CompanyAccountController.updateCompanyAccount     | 语法                                                   | public Response updateCompanyAccount(@RequestBody CompanyAccountVO companyAccountVO) |
| 前置条件                                          | 用户请求更新公司银行账户的API                          |                                                              |
| 后置条件                                          | 调用CompanyAccountSevice的updateCompanyAccount方法     |                                                              |

1. InitAccountController的接口规范

| 提供的服务(供接口)                             |                                                      |                                                              |
| ---------------------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------ |
| InitAccountController.createCustomer           | 语法                                                 | public Response createCustomer(@RequestBody InitCustomerVO initCustomerVO) |
| 前置条件                                       | 用户请求期初创建客户的API                            |                                                              |
| 后置条件                                       | 调用InitAccountService的createCustomer方法           |                                                              |
| InitAccountController.createProduct            | 语法                                                 | public Response createProduct(@RequestBody InitProductVO initProductVO) |
| 前置条件                                       | 用户请求期初创建商品的API                            |                                                              |
| 后置条件                                       | 调用InitAccountService的createProduct方法            |                                                              |
| InitAccountController.createAccount            | 语法                                                 | public Response createAccount(@RequestBody InitCompanyAccountVO initCompanyAccountVO) |
| 前置条件                                       | 用户请求期初创建银行账户的API                        |                                                              |
| 后置条件                                       | 调用InitAccountService的createAccount方法            |                                                              |
| InitAccountController.getAllInitCustomer       | 语法                                                 | public Response getAllInitCustomer()                         |
| 前置条件                                       | 用户请求查询期初客户信息的API                        |                                                              |
| 后置条件                                       | 调用InitAccountService的getAllInitCustomer方法       |                                                              |
| InitAccountController.getAllInitProduct        | 语法                                                 | public Response getAllInitProduct()                          |
| 前置条件                                       | 用户请求查询期初商品信息的API                        |                                                              |
| 后置条件                                       | 调用InitAccountService的getAllInitProduct方法        |                                                              |
| InitAccountController.getAllInitCompanyAccount | 语法                                                 | public Response getAllInitCompanyAccount()                   |
| 前置条件                                       | 用户请求查询期初银行账户信息的API                    |                                                              |
| 后置条件                                       | 调用InitAccountService的getAllInitCompanyAccount方法 |                                                              |

1. FinanceController的接口规范

| 提供的服务(供接口)                   |                                              |                                                              |
| ------------------------------------ | -------------------------------------------- | ------------------------------------------------------------ |
| FinanceController.getFinancialReport | 语法                                         | public Response getFinancialReport(@Param("beginDateStr") String beginDateStr, @Param("endDateStr") String endDateStr) |
| 前置条件                             | 用户请求查询财务报告的API                    |                                                              |
| 后置条件                             | 调用FinanceService的getFinancialReport的方法 |                                                              |

1. PaymentController的接口规范

| 提供的服务(供接口)                           |                                                      |                                                              |
| -------------------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------ |
| PaymentController.makePaymentSheet           | 语法                                                 | public Response makePaymentSheet(UserVO userVO, @RequestBody PaymentSheetVO paymentSheetVO) |
| 前置条件                                     | 用户请求创建付款单的API                              |                                                              |
| 后置条件                                     | 调用PaymentService的makePaymentSheet的方法           |                                                              |
| PaymentController.approvePaymentSheet        | 语法                                                 | public Response approvePaymentSheet(@RequestParam String paymentSheetId, @RequestParam PaymentSheetState state) |
| 前置条件                                     | 用户请求审批付款单的API                              |                                                              |
| 后置条件                                     | 调用PaymentService的approvePaymentSheet的方法        |                                                              |
| PaymentController.findAllPaymentSheetByState | 语法                                                 | public Response findAllPaymentSheetByState(@RequestParam(required = false) PaymentSheetState state) |
| 前置条件                                     | 用户请求根据状态查询付款单的API                      |                                                              |
| 后置条件                                     | 调用PaymentService的findAllPaymentSheetByState的方法 |                                                              |
| PaymentController.findBySheetId              | 语法                                                 | public Response findBySheetId(@RequestParam("id") String id) |
| 前置条件                                     | 用户请求根据id查询付款单的API                        |                                                              |
| 后置条件                                     | 调用PaymentService的findBySheetId的方法              |                                                              |

1. SheetController的接口规范

| 提供的服务(供接口)           |                                    |                                                              |
| ---------------------------- | ---------------------------------- | ------------------------------------------------------------ |
| SheetController.findAllSheet | 语法                               | public Response findAllSheet(@RequestParam String beginDateStr, @RequestParam String endDateStr) |
| 前置条件                     | 用户请求查询所有单据的API          |                                                              |
| 后置条件                     | 调用SheetService的findAllSheet方法 |                                                              |

1. CompanyAccountService的接口规范

| 提供的服务(供接口)                             |                                                        |                                                              |
| ---------------------------------------------- | ------------------------------------------------------ | ------------------------------------------------------------ |
| CompanyAccountService.createAccount            | 语法                                                   | public void createAccount(CompanyAccountVO companyAccountVO); |
| 前置条件                                       | 正在进行创建公司账户                                   |                                                              |
| 后置条件                                       | 添加一个公司银行账户，更新公司银行账户信息列表         |                                                              |
| CompanyAccountService.findCompanyAccountByName | 语法                                                   | public List<CompanyAccountVO> findCompanyAccountByName(String name); |
| 前置条件                                       | 正在进行按照账户名称查询账户                           |                                                              |
| 后置条件                                       | 按照名字查询对应的账户，在公司银行账户信息列表列表     |                                                              |
| CompanyAccountService.deleteCompanyAccountById | 语法                                                   | public void deleteCompanyAccountById(Integer id);            |
| 前置条件                                       | 正在进行按照id删除指定的账户                           |                                                              |
| 后置条件                                       | 按照银行账户编号删除银行账户，更新公司银行账户信息列表 |                                                              |
| CompanyAccountService.updateCompanyAccount     | 语法                                                   | public void updateCompanyAccount(CompanyAccountVO companyAccountVO); |
| 前置条件                                       | 正在更新公司银行账户                                   |                                                              |
| 后置条件                                       | 更新银行账户信息，在公司银行信息列表显示               |                                                              |

1. PaymentService的接口规范

| 提供的服务(供接口)                        |                                              |                                                              |
| ----------------------------------------- | -------------------------------------------- | ------------------------------------------------------------ |
| PaymentService.makePaymentSheet           | 语法                                         | void makePaymentSheet(UserVO userVO, PaymentSheetVO paymentSheetVO); |
| 前置条件                                  | 正在制定付款单                               |                                                              |
| 后置条件                                  | 添加一个付款单，更新付款单信息列表           |                                                              |
| PaymentService.approval                   | 语法                                         | void approval(String paymentSheetId, BaseEnum state);        |
| 前置条件                                  | 正在审批付款单                               |                                                              |
| 后置条件                                  | 审批一个付款单，标注对应付款单审批之后的状态 |                                                              |
| PaymentService.findAllPaymentSheetByState | 语法                                         | List<PaymentSheetVO> findAllPaymentSheetByState(PaymentSheetState paymentSheetState); |
| 前置条件                                  | 正在根据单据的状态查询付款单                 |                                                              |
| 后置条件                                  | 按照状态查询所有的付款单，更新付款单信息列表 |                                                              |
| PaymentService.findPaymentSheetById       | 语法                                         | PaymentSheetVO findPaymentSheetById(String id);              |
| 前置条件                                  | 正在根据id查询付款单                         |                                                              |
| 后置条件                                  | 按照id查询所有的付款单，在付款单信息列表显示 |                                                              |

1. CollectionService的接口规范

| 提供的服务(供接口)                              |                                                    |                                                              |
| ----------------------------------------------- | -------------------------------------------------- | ------------------------------------------------------------ |
| CollectionService.makeCollectionSheet           | 语法                                               | void makeCollectionSheet(CollectionSheetVO collectionSheetVO); |
| 前置条件                                        | 正在制定收款单                                     |                                                              |
| 后置条件                                        | 添加一个收款单，更新收款单信息列表                 |                                                              |
| CollectionService.approval                      | 语法                                               | void approval(String collectionSheetId, BaseEnum state);     |
| 前置条件                                        | 正在审批收款单                                     |                                                              |
| 后置条件                                        | 审批完成一个收款单，标记收款单收款完成的状态       |                                                              |
| CollectionService.findAllCollectionSheetByState | 语法                                               | List<CollectionSheetVO> findAllCollectionSheetByState(CollectionSheetState state); |
| 前置条件                                        | 正在根据单据状态查询收款单                         |                                                              |
| 后置条件                                        | 按照单据状态查询所有的收款单，在收款单信息列表显示 |                                                              |
| CollectionService.findCollectionSheetById       | 语法                                               | CollectionSheetVO findCollectionSheetById(String id);        |
| 前置条件                                        | 正在根据id查询收款单                               |                                                              |
| 后置条件                                        | 按照单据编号查询所有的收款单，在收款单信息列表显示 |                                                              |

1. SalaryService

| 提供的服务(供接口)                  |                                            |                                                              |
| ----------------------------------- | ------------------------------------------ | ------------------------------------------------------------ |
| SalaryService.queryAllJobs          | 语法                                       | List<JobPO> queryAllJobs();                                  |
| 前置条件                            | 正在查询岗位信息                           |                                                              |
| 后置条件                            | 返回所有的岗位信息                         |                                                              |
| SalaryService.updateJobById         | 语法                                       | void updateJobById(JobVO jobVO);                             |
| 前置条件                            | 正在更新对应编号的岗位信息                 |                                                              |
| 后置条件                            | 更新对应编号的岗位信息，将修改更新到数据库 |                                                              |
| SalaryService.generateSalarySheet   | 语法                                       | void generateSalarySheet(String beginDateStr, String endDateStr); |
| 前置条件                            | 系统正在自动制定工资单                     |                                                              |
| 后置条件                            | 将指定完成的工资单信息保存到数据库         |                                                              |
| SalaryService.getSalarySheetByTime  | 语法                                       | List<SalarySheetPO> getSalarySheetByTime(String beginTime, String endTime); |
| 前置条件                            | 正在根据时间查询工资单                     |                                                              |
| 后置条件                            | 查询对应时间的工资单，在工资单列表显示     |                                                              |
| SalaryService.getSalarySheetById    | 语法                                       | SalarySheetPO getSalarySheetById(Integer id);                |
| 前置条件                            | 正在根据工资单编号查询工资单               |                                                              |
| 后置条件                            | 查询对应编号的工资单，在工资单列表显示     |                                                              |
| SalaryService.getSalarySheetByState | 语法                                       | List<SalarySheetPO> getSalarySheetByState(SalarySheetState state); |
| 前置条件                            | 正在根据工资单状态查询工资单               |                                                              |
| 后置条件                            | 查询对应状态的工资单，在工资单列表显示     |                                                              |

1. SheetService

| 提供的服务(供接口)        |                                        |                                                              |
| ------------------------- | -------------------------------------- | ------------------------------------------------------------ |
| SheetService.findAllSheet | 语法                                   | List<SheetVO> findAllSheet(String beginDateStr, String endDateStr); |
| 前置条件                  | 正在查询经营历程表                     |                                                              |
| 后置条件                  | 查询出系统中所有的单据，返回单据的集合 |                                                              |

1. FinanceService

| 提供的服务(供接口)                |                                          |                                                              |
| --------------------------------- | ---------------------------------------- | ------------------------------------------------------------ |
| FinanceService.getFinancialReport | 语法                                     | FinanceVO getFinancialReport(String beginDateStr, String endDateStr); |
| 前置条件                          | 正在查询经营情况表                       |                                                              |
| 后置条件                          | 查询所有的经营情况，在经营情况表列表显示 |                                                              |

1. SaleService

| 提供的服务(供接口)                  |                                                              |                                                              |
| ----------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| SaleService.findAllSaleDetailByTime | 语法                                                         | List<SaleDetailVO> findAllSaleDetailByTime(String beginDateStr, String endDateStr); |
| 前置条件                            | 正在查询销售明细表                                           |                                                              |
| 后置条件                            | 根据单据创建的时间查询出所有单据的信息，在销售明细表列表显示 |                                                              |

1. InitAccountService

| 提供的服务(供接口)                          |                                  |                                                              |
| ------------------------------------------- | -------------------------------- | ------------------------------------------------------------ |
| InitAccountService.createCustomer           | 语法                             | int createCustomer(InitCustomerVO initCustomerVO)            |
| 前置条件                                    | 正在期初创建客户信息             |                                                              |
| 后置条件                                    | 创建客户，将信息保存到数据库     |                                                              |
| InitAccountService.createAccount            | 语法                             | public void createAccount(InitCompanyAccountVO initCompanyAccountVO) |
| 前置条件                                    | 正在期初创建账户信息             |                                                              |
| 后置条件                                    | 创建账户，将信息保存到数据库     |                                                              |
| InitAccountService.createProduct            | 语法                             | void createProduct(InitProductVO initProductVO);             |
| 前置条件                                    | 正在期初创建商品                 |                                                              |
| 后置条件                                    | 创建商品，将信息保存到数据库     |                                                              |
| InitAccountService.getAllInitCustomer       | 语法                             | List<InitCustomerVO> getAllInitCustomer();                   |
| 前置条件                                    | 正在查询所有期初创建的客户       |                                                              |
| 后置条件                                    | 在期初客户列表中显示所有客户信息 |                                                              |
| InitAccountService.getAllInitProduct        | 语法                             | List<InitProductVO> getAllInitProduct();                     |
| 前置条件                                    | 正在查询所有起初创建的商品       |                                                              |
| 后置条件                                    | 在期初商品列表中显示所有商品信息 |                                                              |
| InitAccountService.getAllInitCompanyAccount | 语法                             | List<InitCompanyAccountVO> getAllInitCompanyAccount();       |
| 前置条件                                    | 正在查询所有起初创建的账户       |                                                              |
| 后置条件                                    | 在期初账户列表中显示所有账户信息 |                                                              |

逻辑层与数据层之间的接口

1. CompanyAccountDao

| 提供的服务(供接口)                                         |                                                        |                                                              |
| ---------------------------------------------------------- | ------------------------------------------------------ | ------------------------------------------------------------ |
| CompanyAccountDao.createAccount                            | 语法                                                   | void createAccount(CompanyAccountPO companyAccountPO);       |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 创建银行账户，保存到数据库                             |                                                              |
| CompanyAccountDao.findCompanyAccountByName                 | 语法                                                   | List<CompanyAccountPO> findCompanyAccountByName(String name); |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 查询出指定名称的银行账户                               |                                                              |
| CompanyAccountDao.findAllCompanyAccounts                   | 语法                                                   | List<CompanyAccountPO> findAllCompanyAccounts();             |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 查询所有银行账户                                       |                                                              |
| CompanyAccountDao.deleteCompanyAccountById                 | 语法                                                   | void deleteCompanyAccountById(Integer id);                   |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 删除指定编号的银行账户，更新数据库信息                 |                                                              |
| CompanyAccountDao.updateCompanyAccount                     | 语法                                                   | void updateCompanyAccount(CompanyAccountPO companyAccountPO); |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 更新对应的公司银行账户信息，更新数据库信息             |                                                              |
| CompanyAccountDao.collectionUpdateCompanyAccountAmountById | 语法                                                   | void collectionUpdateCompanyAccountAmountById(Integer id, BigDecimal amount); |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 收款单审批之后更新对应的银行账户，更新对应的数据库信息 |                                                              |
| CompanyAccountDao.paymentUpdateCompanyAccountAmountById    | 语法                                                   | void paymentUpdateCompanyAccountAmountById(Integer id, BigDecimal amount); |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 付款单审批之后更新对应的银行账户，更新对应的数据库信息 |                                                              |
| CompanyAccountDao.deleteAll                                | 语法                                                   | int deleteAll();                                             |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 删除所有银行账户信息，更新数据库                       |                                                              |
| CompanyAccountDao.insertFromInit                           | 语法                                                   | int insertFromInit();                                        |
| 前置条件                                                   | 无                                                     |                                                              |
| 后置条件                                                   | 从期初建账的表中插入数据                               |                                                              |

1. PaymentSheetDao

| 提供的服务(供接口)                        |                                      |                                                              |
| ----------------------------------------- | ------------------------------------ | ------------------------------------------------------------ |
| PaymentDao.savePaymentSheet               | 语法                                 | int savePaymentSheet(PaymentSheetPO paymentSheetPO);         |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 创建付款单并保存到数据库             |                                                              |
| PaymentDao.savePaymentSheetContent        | 语法                                 | int savePaymentSheetContent(List<PaymentSheetContentPO> paymentSheetContentPOS); |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 保存付款单内容到数据库               |                                                              |
| PaymentDao.findPaymentSheetById           | 语法                                 | PaymentSheetPO findPaymentSheetById(String id);              |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 根据编号查找对应的付款单信息         |                                                              |
| PaymentDao.findAllPaymentSheet            | 语法                                 | PaymentSheetVO findPaymentSheetById(String id);              |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 查询所有的付款单                     |                                                              |
| PaymentDao.updateStateById                | 语法                                 | int updateStateById(String id, BaseEnum state);              |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 根据指定的编号更新状态，更新数据库   |                                                              |
| PaymentDao.findAllPaymentSheetContentById | 语法                                 | List<PaymentSheetContentPO> findAllPaymentSheetContentById(String id); |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 根据付款单编号查询所有的付款单内容   |                                                              |
| PaymentDao.findLatest                     | 语法                                 | PaymentSheetPO findLatest();                                 |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 查询目前最新的付款单                 |                                                              |
| PaymentDao.findAllPaymentSheetByState     | 语法                                 | List<PaymentSheetPO> findAllPaymentSheetByState(BaseEnum state); |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 根据付款单的状态查询所有的付款单内容 |                                                              |
| PaymentDao.findAllBasicSheetInfo          | 语法                                 | List<SheetPO> findAllBasicSheetInfo(@Param("beginTime") String beginTime, @Param("endTime") String endTime); |
| 前置条件                                  | 无                                   |                                                              |
| 后置条件                                  | 查询单据的基本内容                   |                                                              |

1. CollectionDao

| 提供的服务(供接口)                          |                                                    |                                                              |
| ------------------------------------------- | -------------------------------------------------- | ------------------------------------------------------------ |
| CollectionDao.saveCollectionSheetSheet      | 语法                                               | int saveCollectionSheetSheet(CollectionSheetPO collectionSheetPO); |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 将收款单保存到数据库中                             |                                                              |
| CollectionDao.saveTransferList              | 语法                                               | int saveTransferList(List<TransferListSheetPO> transferListSheetPOS); |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 将收款单内容保存到数据库                           |                                                              |
| CollectionDao.findCollectionSheetById       | 语法                                               | CollectionSheetPO findCollectionSheetById(String id);        |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 按照单据编号查询所有的收款单，在收款单信息列表显示 |                                                              |
| CollectionDao.findAllCollectionSheet        | 语法                                               | List<CollectionSheetPO> findAllCollectionSheet();            |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 查询所有的收款单，在收款单列表显示                 |                                                              |
| CollectionDao.findAllCollectionSheetByState | 语法                                               | List<CollectionSheetPO> findAllCollectionSheetByState(CollectionSheetState state); |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 根据状态查询所有的收款单                           |                                                              |
| CollectionDao.findAllCollectionSheetContent | 语法                                               | List<TransferListSheetPO> findAllCollectionSheetContent(String collectionSheetId); |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 查询所有的收款单内容                               |                                                              |
| CollectionDao.updateState                   | 语法                                               | int updateState(String id, BaseEnum state);                  |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 更新收款单状态                                     |                                                              |
| CollectionDao.findLatest                    | 语法                                               | CollectionSheetPO findLatest();                              |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 查询最新的收款单                                   |                                                              |
| CollectionDao.findAllBasicSheetInfo         | 语法                                               | List<SheetPO> findAllBasicSheetInfo(@Param("beginTime") String beginTime, @Param("endTime") String endTime); |
| 前置条件                                    | 无                                                 |                                                              |
| 后置条件                                    | 查询收款单基本信息                                 |                                                              |

1. SalarySheetDao

| 提供的服务(供接口)                   |                                        |                                                              |
| ------------------------------------ | -------------------------------------- | ------------------------------------------------------------ |
| SalarySheetDao.createSalarySheet     | 语法                                   | void createSalarySheet(SalarySheetPO salarySheetPO);         |
| 前置条件                             | 无                                     |                                                              |
| 后置条件                             | 系统自动生成工资单，将数据保存到数据库 |                                                              |
| SalarySheetDao.getSalarySheetByTime  | 语法                                   | List<SalarySheetPO> getSalarySheetByTime(@Param("startTime") String startTime, @Param("endTime") String endTime); |
| 前置条件                             | 无                                     |                                                              |
| 后置条件                             | 根据创建时间查询工资单                 |                                                              |
| SalarySheetDao.getSalarySheetById    | 语法                                   | SalarySheetPO getSalarySheetById(Integer id);                |
| 前置条件                             | 无                                     |                                                              |
| 后置条件                             | 更具工资单编号查询工资单               |                                                              |
| SalarySheetDao.findAllBasicSheetInfo | 语法                                   | List<SheetPO> findAllBasicSheetInfo(@Param("startTime") String startTime, @Param("endTime") String endTime); |
| 前置条件                             | 无                                     |                                                              |
| 后置条件                             | 查询所有工资单的基本信息               |                                                              |
| SalarySheetDao.getSalarySheetByState | 语法                                   | List<SalarySheetPO> getSalarySheetByState(SalarySheetState state); |
| 前置条件                             | 无                                     |                                                              |
| 后置条件                             | 根据工资单的状态查询工资单             |                                                              |
| SalarySheetDao.getAllSalarySheet     | 语法                                   | List<SalarySheetPO> getAllSalarySheet();                     |
| 前置条件                             | 无                                     |                                                              |
| 后置条件                             | 查询所有的工资单                       |                                                              |

1. InitAccountDao

| 提供的服务(供接口)                      |                                      |                                                              |
| --------------------------------------- | ------------------------------------ | ------------------------------------------------------------ |
| InitAccountDao.createProduct            | 语法                                 | int createProduct(InitProductPO initProductPO);              |
| 前置条件                                | 无                                   |                                                              |
| 后置条件                                | 期初创建商品，将商品信息保存到数据库 |                                                              |
| InitAccountDao.createAccount            | 语法                                 | public void createAccount(InitCompanyAccountVO initCompanyAccountVO) |
| 前置条件                                | 无                                   |                                                              |
| 后置条件                                | chaunchaung                          |                                                              |
| InitAccountDao.createCustomer           | 语法                                 | int createCustomer(InitCustomerPO initCustomerPO);           |
| 前置条件                                | 无                                   |                                                              |
| 后置条件                                | 创建商品，将信息保存到数据库         |                                                              |
| InitAccountDao.getAllInitCustomer       | 语法                                 | List<InitCustomerPO> getAllInitCustomer();                   |
| 前置条件                                | 无                                   |                                                              |
| 后置条件                                | 在期初客户列表中显示所有客户信息     |                                                              |
| InitAccountDao.getAllInitProduct        | 语法                                 | List<InitProductPO> getAllInitProduct();                     |
| 前置条件                                | 无                                   |                                                              |
| 后置条件                                | 在期初商品列表中显示所有商品信息     |                                                              |
| InitAccountDao.getAllInitCompanyAccount | 语法                                 | List<InitCompanyAccountPO> getAllInitCompanyAccount();       |
| 前置条件                                | 无                                   |                                                              |
| 后置条件                                | 在期初账户列表中显示所有账户信息     |                                                              |

(4) 业务逻辑层的动态模型

1. 账户管理
   1. 创建公司银行账户系统顺序图
      - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=Yzg5YzEyNjhmODdmMTRkZTQxYTg0N2VmODRhOTFlZGRfM25md3ZmVEFaTFU1eWpNcWxxdFJWWndKTHdEdkZOSU9fVG9rZW46Ym94Y25GdnZubkZCd0oyOVdNRTVwRXo3ZkZnXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)
   2. 更新公司银行账户系统顺序图
      - ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjFjZjY0ZDJhOWJjMzkzOGQ4MTZkMzlkOGZlNTM1ZDhfOWZLc0VnV2NBR3JTUDhER3BjYXZWRmVPN1ZvSEN4Y0NfVG9rZW46Ym94Y25aNzdzRUU4VkxmQzVRSjRMNWlaQ25iXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 制定收款单系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OGY3NDU3ZjBjNjM0ZmYyYWZjNzU3OTNhNDI2ZDIzNGJfWm9YSVJWOTlZMEY3bXNCUlh4Z0p6REJZaEVPNHdSazJfVG9rZW46Ym94Y25IVHlHcElmZWtDTTkxNkRSVmdlNjlSXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 制定付款单系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YmVhYTQ5MjAzYTkwMDNjMmRlYjAzMDkyNWFmZThhNjVfQmtraDV0Mm9SVnpqWTVqcHFxbVlQUUhqUEpadUdvaW5fVG9rZW46Ym94Y25Va2ZSM3hxdFNoNHk1czFKRFJpeU1oXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 制定工资单系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjcyNDBhMTA3NGQ2MjQzMGE2M2JmZjhlOGQ4MTdiYmRfZVNIM0VaOHhSQWtkRWdjRDhrVWJ4SHdkQkFNNkE1YVJfVG9rZW46Ym94Y25rWDl6OFk5Q29OQ3YxZEM1SEJyb1FoXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 查看销售明细表系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=M2E0MjZiNzE5NTBiNzZlZjY5MzA5MzA4OWM0YjU0NTFfemhLM0NhcXFEV1lVWlppRUFlM1BqWk1lcDFZTEpWc1lfVG9rZW46Ym94Y244TkMyRDdBZ1dhTVhVUlFwajgzQlFkXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 查看经营历程表系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NTg5ZGViNWVhZDViZjcyNGU3OTYwOTE3ZGZjZTViOWZfYXRkNFIxbmlpc3Y3QkJGY29GZkJJSFZJcFdpYTJpVldfVG9rZW46Ym94Y242czY5aFQ4eWRSdW9Kc0dHSk9NUkJwXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 查看经营情况表系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=Mjg5YjU2NWU2NzRmYzgzYmY4NjEyZjYzNGZmMTZlYzBfUzhiRm9QdEpSVDFvamhHTFY3UmNJTzhPRE54cXFRUmtfVG9rZW46Ym94Y240UnhGSG9KV1FUUXBxQWVrZDdLbVBkXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 期初建账系统顺序图
   1. 期初建立客户信息
   2. ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OWQzNDU2YzRmZDJmNjFmN2E0N2QzYTdiNTgwZGRhNzFfUGxnVEZrTTd4RkRSWjQ5Wm5tRzFKYTRpM1FiZDVZbXlfVG9rZW46Ym94Y24zVHhnOGRwYWVNWDRZckhTMFJjRTZlXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

   3. 期初建立商品信息
   4. ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmNhYTkxMWFlOTAwYzFhOGU1MDRkNmQ5N2VlN2E2OGVfUDFlRkNCUW9TNDlnQ2Z1dnlMMHZackNPUUVyYzJxRkhfVG9rZW46Ym94Y240a2l5VVBYZU5TMk0xMnVFMDdyemhmXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

   5. 期初建立公司银行账户信息
   6. ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=ODFjMTk4Nzg0NGExYjYwNTY2ZWZmNTQ3N2U1NmMwYWRfd0JTVW1Qb2R5TGtKcHBUNTIwU1ZFWUNJSmVFUFlHb3RfVG9rZW46Ym94Y25rRnBocGtiNVVKQjFDZzhsWW1zd0xoXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

(5) 业务逻辑层的设计原理

利用委托式控制风格，每个界面需要访问的业务逻辑有各自的控制器委托给不同的业务逻辑对象。

#### 4.1.2 人力资源模块

(1) 模块概述

- humanresourcebl模块承担的需求参见企业ERP需求规格说明文档功能需求以及相关非功能需求

- humanresourcebl模块的职责以及接口参见软件体系结构描述文档

(2) 整体结构

根据体系结构的设计，我们将系统分成展示层、业务逻辑层、数据层。每一层之间为了增加灵活性、可修改性，我们都添加了接口，按照接口编程，使得设计更加符合接口隔离、依赖倒置等设计模式。

在展示层和业务逻辑层中(humanresourceservice)，我们设计了JobService、SalaryService、

EmployeeService等接口，提供了员工管理、员工打卡、薪酬规则制定等方法的接口，展示层和业务逻辑层之间我们设计了对应的VO对象，用于前后端的数据传递。

在业务逻辑层和数据层之间，我们定义了Dao接口提供查询所有单据信息的的方法接口，设计了

JobDao、SalaryDao、EmployeeDao等接口提供增删改查方法接口，业务逻辑层和数据层之间我们设计了对应的PO对象，用于将数据保存到数据库。

为了隔离业务逻辑职责和逻辑控制职责，我们增添了一些Controller类，这样对于具体业务逻辑的处理会委托给对应的service类对象，Controller类起到了分派器的作用。

人力资源模块的设计如下:

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MzljMDYwODY2NjUwMTlmZjRjOTQ2NTYyMTA5NDA5YzVfYWlKTDJ1ZWUyWUI3dTF1bURVN1M3NDNKT2d3TnFoV0VfVG9rZW46Ym94Y25rTTFkRE9kZzJBcFgySnI0eFM3aXhlXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

人力资源模块各个类的职责如下

| 模块               | 职责                                                         |
| ------------------ | ------------------------------------------------------------ |
| EmployeeController | 负责通过EmployeeService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| JobController      | 负责通过JobService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| SalaryController   | 负责通过SalaryService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| EmployeeService    | 提供员工信息增删改查的接口                                   |
| JobService         | 提供岗位信息增删改查的接口                                   |
| SalaryService      | 提供工资单信息增删改查的接口                                 |
| EmployeeDao        | 负责与数据库的交互，实现员工信息的增删改查                   |
| JobDao             | 负责与数据库的交互，实现岗位信息的增删改查                   |
| SalaryDao          | 负责与数据库的交互，实现工资单信息的增删改查                 |
| EmployeePO         | 员工信息的持久层对象，与数据库表一一对应                     |
| JobPO              | 岗位信息的持久层对象，与数据库表一一对应                     |
| SalarySheetPO      | 工资单的持久层对象，与数据库表一一对应                       |
| EmployeeVO         | 员工信息的展示层对象，用于前后端传递数据                     |
| JobVO              | 岗位信息的展示层对象，用于前后端传递数据                     |
| SalarySheetVO      | 工资单的展示层对象，用于前后端传递数据                       |

(3) 模块内部类的接口规范

1. EmployeeController

| 提供的服务(供接口)                    |                                             |                                                              |
| ------------------------------------- | ------------------------------------------- | ------------------------------------------------------------ |
| EmployeeController.addEmployee        | 语法                                        | public Response addEmployee(@RequestBody EmployeeVO employeeVO) |
| 前置条件                              | 用户请求添加员工的API                       |                                                              |
| 后置条件                              | 调用EmployeeService的addEmployee方法        |                                                              |
| EmployeeController.queryAllEmployees  | 语法                                        | public Response queryAllEmployees()                          |
| 前置条件                              | 用户请求查询所有员工信息的API               |                                                              |
| 后置条件                              | 调用EmployeeService的queryAllEmployees方法  |                                                              |
| EmployeeController.updateEmployeeInfo | 语法                                        | public Response updateEmployeeInfo(@RequestBody EmployeeVO employeeVO) |
| 前置条件                              | 用户请求更新员工信息的API                   |                                                              |
| 后置条件                              | 调用EmployeeService的updateEmployeeInfo方法 |                                                              |
| EmployeeController.deleteEmployeeById | 语法                                        | public Response deleteEmployeeById(@RequestParam(value = "id") int id) |
| 前置条件                              | 用户请求删除员工信息的API                   |                                                              |
| 后置条件                              | 调用EmployeeService的deleteEmployeeById方法 |                                                              |

1. JobController

| 提供的服务(供接口)                       |                                                |                                                              |
| ---------------------------------------- | ---------------------------------------------- | ------------------------------------------------------------ |
| JobController.createDepartmentSalaryRule | 语法                                           | public Response createDepartmentSalaryRule(@RequestBody JobVO jobVO) |
| 前置条件                                 | 用户请求创建部门薪资规则的API                  |                                                              |
| 后置条件                                 | 调用JobService的createDepartmentSalaryRule方法 |                                                              |
| JobController.queryAllSalaryRules        | 语法                                           | public Response queryAllSalaryRules()                        |
| 前置条件                                 | 用户请求查询所有薪资规则的API                  |                                                              |
| 后置条件                                 | 调用JobService的queryAllSalaryRules方法        |                                                              |
| JobController.updateDepartmentSalaryRule | 语法                                           | public Response updateDepartmentSalaryRule(@RequestBody JobVO jobVO) |
| 前置条件                                 | 用户请求更新部门薪资规则的API                  |                                                              |
| 后置条件                                 | 调用JobService的updateDepartmentSalaryRule方法 |                                                              |

1. SalaryController

| 提供的服务(供接口)                          |                                                   |                                                              |
| ------------------------------------------- | ------------------------------------------------- | ------------------------------------------------------------ |
| SalaryController.queryAllSalaryRules        | 语法                                              | public Response queryAllSalaryRules()                        |
| 前置条件                                    | 用户请求查询所有薪资规则的API                     |                                                              |
| 后置条件                                    | 调用SalaryService的queryAllSalaryRules方法        |                                                              |
| SalaryController.updateDepartmentSalaryRule | 语法                                              | public Response updateDepartmentSalaryRule(@RequestBody JobVO salaryRuleEditForm) |
| 前置条件                                    | 用户请求更新部门薪酬规则的API                     |                                                              |
| 后置条件                                    | 调用SalaryService的updateDepartmentSalaryRule方法 |                                                              |
| SalaryController.getSalarySheetByTime       | 语法                                              | public Response getSalarySheetByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime) |
| 前置条件                                    | 用户请求根据时间查询工资单的API                   |                                                              |
| 后置条件                                    | 调用SalaryService的getSalarySheetByTime方法       |                                                              |
| SalaryController.getSalarySheetById         | 语法                                              | public Response getSalarySheetById(@Param("id") String id)   |
| 前置条件                                    | 用户请求根据编号查询工资单的API                   |                                                              |
| 后置条件                                    | 调用SalaryService的getSalarySheetById方法         |                                                              |
| SalaryController.getSalarySheetByState      | 语法                                              | public Response getSalarySheetByState(@RequestParam(value = "state", required = false) SalarySheetState state) |
| 前置条件                                    | 用户请求根据状态查询工资单的API                   |                                                              |
| 后置条件                                    | 调用SalaryService的getSalarySheetByState方法      |                                                              |
| SalaryController.generateSalarySheet        | 语法                                              | public Response generateSalarySheet(@RequestParam(value = "beginTime") String beginTimeStr, @RequestParam(value = "endTime") String endTimeStr) |
| 前置条件                                    | 用户请求生成工资单的API                           |                                                              |
| 后置条件                                    | 调用SalaryService的generateSalarySheet方法        |                                                              |
| SalaryController.fistApproval               | 语法                                              | public Response fistApproval(@RequestParam("salarySheetId") String salarySheetId,@RequestParam("state") SalarySheetState state) |
| 前置条件                                    | 用户请求一级审批工资单的API                       |                                                              |
| 后置条件                                    | 调用SalaryService的fistApproval方法               |                                                              |
| SalaryController.secondApproval             | 语法                                              | public Response secondApproval(@RequestParam("salarySheetId") String salarySheetId,@RequestParam("state") SalarySheetState state) |
| 前置条件                                    | 用户请求二级审批工资单的API                       |                                                              |
| 后置条件                                    | 调用SalaryService的secondApproval方法             |                                                              |

展示层与逻辑层之间的接口

1. EmployeeService

| 提供的服务(供接口)                 |                                          |                                                 |
| ---------------------------------- | ---------------------------------------- | ----------------------------------------------- |
| EmployeeService.addEmployee        | 语法                                     | UserVO addEmployee(EmployeeVO employeeVO);      |
| 前置条件                           | 正在添加员工                             |                                                 |
| 后置条件                           | 新增员工，更新数据库信息                 |                                                 |
| EmployeeService.queryAllEmployees  | 语法                                     | List<EmployeePO> queryAllEmployees();           |
| 前置条件                           | 正在查询所有员工                         |                                                 |
| 后置条件                           | 查询所有员工，在员工列表显示             |                                                 |
| EmployeeService.updateEmployeeInfo | 语法                                     | void updateEmployeeInfo(EmployeeVO employeeVO); |
| 前置条件                           | 正在更新员工信息                         |                                                 |
| 后置条件                           | 更新员工信息，更新对应的数据库信息       |                                                 |
| EmployeeService.deleteEmployeeById | 语法                                     | void deleteEmployeeById(int id);                |
| 前置条件                           | 正在删除对应的员工信息                   |                                                 |
| 后置条件                           | 删除对应的员工信息，更新对应的数据库信息 |                                                 |

1. SalaryService

| 提供的服务(供接口)                       |                                            |                                                              |
| ---------------------------------------- | ------------------------------------------ | ------------------------------------------------------------ |
| SalaryService.queryAllSalaryRules        | 语法                                       | List<JobPO> queryAllSalaryRules();                           |
| 前置条件                                 | 正在查询所有的薪资规则                     |                                                              |
| 后置条件                                 | 查询薪资规则，在薪资规则列表显示           |                                                              |
| SalaryService.updateDepartmentSalaryRule | 语法                                       | void updateSalaryRuleById(JobVO jobVO);                      |
| 前置条件                                 | 正在更新对应的部门薪酬规则                 |                                                              |
| 后置条件                                 | 更新薪酬规则，更新数据库                   |                                                              |
| SalaryService.getSalarySheetByTime       | 语法                                       | List<SalarySheetPO> getSalarySheetByTime(String beginTime, String endTime); |
| 前置条件                                 | 根据时间查询工资单                         |                                                              |
| 后置条件                                 | 查询工资单，在工资单列表显示               |                                                              |
| SalaryService.getSalarySheetById         | 语法                                       | SalarySheetPO getSalarySheetById(String id);                 |
| 前置条件                                 | 正在根据编号查询对应的工资单               |                                                              |
| 后置条件                                 | 查询对应的工资单，在工资单列表显示         |                                                              |
| SalaryService.getSalarySheetByState      | 语法                                       | List<SalarySheetPO> getSalarySheetByState(SalarySheetState state); |
| 前置条件                                 | 正在根据状态查询工资单                     |                                                              |
| 后置条件                                 | 根据状态查询对应的工资单，在工资单列表显示 |                                                              |
| SalaryService.generateSalarySheet        | 语法                                       | void generateSalarySheet(String beginDateStr, String endDateStr); |
| 前置条件                                 | 正在制定工资单                             |                                                              |
| 后置条件                                 | 制定工资单，将工资单信息保存到数据库       |                                                              |
| SalaryService.approval                   | 语法                                       | void approval(String salarySheetId, BaseEnum state);         |
| 前置条件                                 | 正在审批工资单                             |                                                              |
| 后置条件                                 | 更新工资单状态，更新数据库信息             |                                                              |

1. JobService

| 提供的服务(供接口)                    |                                                |                                               |
| ------------------------------------- | ---------------------------------------------- | --------------------------------------------- |
| JobService.createDepartmentSalaryRule | 语法                                           | void createDepartmentSalaryRule(JobVO jobVO); |
| 前置条件                              | 正在创建部门薪资规则                           |                                               |
| 后置条件                              | 创建部门薪资规则，更新数据库信息               |                                               |
| JobService.queryAllSalaryRules        | 语法                                           | List<JobVO> queryAllSalaryRules();            |
| 前置条件                              | 正在查询所有薪资规则                           |                                               |
| 后置条件                              | 查询所有部门薪资规则，在部门薪资规则列表中显示 |                                               |
| JobService.updateDepartmentSalaryRule | 语法                                           | void updateJobById(JobVO jobVO);              |
| 前置条件                              | 正在更新部门薪资规则                           |                                               |
| 后置条件                              | 更新部门薪资规则，更新数据库                   |                                               |

逻辑层与数据层接口

1. EmployeeDao

| 提供的服务(供接口)             |                            |                                                 |
| ------------------------------ | -------------------------- | ----------------------------------------------- |
| EmployeeDao.addEmployee        | 语法                       | int addEmployee(EmployeePO employeePO);         |
| 前置条件                       | 无                         |                                                 |
| 后置条件                       | 创建员工信息，添加到数据库 |                                                 |
| EmployeeDao.findAllEmployees   | 语法                       | List<EmployeePO> findAllEmployees();            |
| 前置条件                       | 无                         |                                                 |
| 后置条件                       | 查询所有的员工信息         |                                                 |
| EmployeeDao.updateEmployeeInfo | 语法                       | void updateEmployeeInfo(EmployeePO employeePO); |
| 前置条件                       | 无                         |                                                 |
| 后置条件                       | 更新指定的员工信息         |                                                 |
| EmployeeDao.deleteEmployeeById | 语法                       | void deleteEmployeeById(int id);                |
| 前置条件                       | 无                         |                                                 |
| 后置条件                       | 删除指定的员工             |                                                 |
| EmployeeDao.getGradeById       | 语法                       | int getGradeById(int id);                       |
| 前置条件                       | 无                         |                                                 |
| 后置条件                       | 得到员工的级别             |                                                 |

1. SalaryDao

| 提供的服务(供接口)               |                                |                                                              |
| -------------------------------- | ------------------------------ | ------------------------------------------------------------ |
| SalaryDao.createSalarySheet      | 语法                           | void createSalarySheet(SalarySheetPO salarySheetPO);         |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 生成工资单，将数据保存到数据库 |                                                              |
| SalaryDao.getSalarySheetByTime   | 语法                           | List<SalarySheetPO> getSalarySheetByTime(@Param("beginTime") String beginTime, @Param("endTime") String endTime); |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 根据创建时间查询               |                                                              |
| SalaryDao.getSalarySheetById     | 语法                           | SalarySheetPO getSalarySheetById(String id);                 |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 根据工资单编号查询工资单       |                                                              |
| SalaryDao.findAllBasicSheetInfo  | 语法                           | List<SheetPO> findAllBasicSheetInfo(@Param("beginTime") String beginTime, @Param("endTime") String endTime); |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 查询所有工资单的基本信息       |                                                              |
| SalaryDao.getSalarySheetByState  | 语法                           | List<SalarySheetPO> getSalarySheetByState(SalarySheetState state); |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 根据工资单的状态查询工资单     |                                                              |
| SalaryDao.getAllSalarySheet      | 语法                           | List<SalarySheetPO> getAllSalarySheet();                     |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 查询所有的工资单               |                                                              |
| SalaryDao.getLatestSheet         | 语法                           | SalarySheetPO getLatestSheet(String idPrefix);               |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 查询最新的工资单               |                                                              |
| SalaryDao.updateSalaryStateById  | 语法                           | int updateSalaryStateById(String sheetId, BaseEnum state);   |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 更新指定工资单的状态           |                                                              |
| SalaryDao.updateSheetStateOnPrev | 语法                           | int updateSheetStateOnPrev(String sheetId, BaseEnum prev, BaseEnum state); |
| 前置条件                         | 无                             |                                                              |
| 后置条件                         | 根据之前的状态更新工资单状态   |                                                              |

1. JobDao

| 提供的服务(供接口)                |                              |                                               |
| --------------------------------- | ---------------------------- | --------------------------------------------- |
| JobDao.findJobByName              | 语法                         | JobPO findJobByName(Role name);               |
| 前置条件                          | 无                           |                                               |
| 后置条件                          | 根据名称查询部门信息         |                                               |
| JobDao.updateJobById              | 语法                         | void updateJobById(JobPO jobPO);              |
| 前置条件                          | 无                           |                                               |
| 后置条件                          | 根据部门编号更新部门信息     |                                               |
| JobDao.queryAllJobs               | 语法                         | List<JobPO> queryAllJobs();                   |
| 前置条件                          | 无                           |                                               |
| 后置条件                          | 查询所有部门信息             |                                               |
| JobDao.createDepartmentSalaryRule | 语法                         | void createDepartmentSalaryRule(JobPO jobPO); |
| 前置条件                          | 无                           |                                               |
| 后置条件                          | 创建部门薪资规则，更新数据库 |                                               |

(4) 业务逻辑层的动态模型

1. 员工管理
   1. 新增员工
   2. ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MDljZTRlYmZiYWVhZGUwYWU2ZjIyNDQ3NjUwOTFmYmZfc1FYMFF4ckt2eXlMTVBOZ1VGQjAyM0pvemVYNlhHUVNfVG9rZW46Ym94Y24zc1c1SEI5NnJvVUJMemNKZGR1bmRjXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

   3. 更新员工信息
   4. ![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=Nzg1MDU3N2UxZjk1NmQxZThlYTE1OWU5NzY4YzE1YzJfZ1FBSzh2N216UWVjZjF2UDhGZFFxdWxxUUswbnZISlpfVG9rZW46Ym94Y254bThhekZtejlFTUVtOU1MMm51OWlkXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 员工打卡

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OGZkNTZlYmQxYTgzYWNlYWEzMzBmM2E4ZDZlNTVkZDNfd2FHQlFEeTE3MUZNS2FuZFNTeTNHTzNTR3JDN2VkWlJfVG9rZW46Ym94Y25Rc21tT2ROOWRyUEFnNUo0allHVVpjXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 薪酬规则制定

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NmVhYjQwMzU3OTdiOTIzNjJhOWZmY2M3YzhhOTA3OWRfQXZES2psWnphajlJSUNyVTRGbjdWNm0xendDNGJWZ0hfVG9rZW46Ym94Y25pZmtuNWM3QUM1Y2h6YmhWM1NDUVFmXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

(5) 业务逻辑层的设计原理

利用委托式控制风格，每个界面需要访问的业务逻辑有各自的控制器委托给不同的业务逻辑对象。

#### 4.1.3 总经理模块

(1) 模块概述

- 总经理模块模块承担的需求参见需求规格说明文档功能需求以及相关的非功能需求。

- 总经理模块模块的职责以及接口参见软件体系结构描述文档表

(2) 整体结构

根据体系结构的设计，我们将系统分成展示层、业务逻辑层、数据层。每一层之间为了增加灵活性、可修改性，我们都添加了接口，按照接口编程，使得设计更加符合接口隔离、依赖倒置等设计模式。

在展示层和业务逻辑层中(generalmanagerservice)，我们设计了PromotionService、SalaryService、

EmployeeService等接口，提供了审批单据、查看经营情况表、制定促销策略等方法的接口，展示层和业务逻辑层之间我们设计了对应的VO对象，用于前后端的数据传递。

在业务逻辑层和数据层之间，我们定义了Dao接口提供查询所有单据信息的的方法接口，设计了

PromotionDao、GiftDao、EmployeeDao等接口提供增删改查方法接口，业务逻辑层和数据层之间我们设计了对应的PO对象，用于将数据保存到数据库。

为了隔离业务逻辑职责和逻辑控制职责，我们增添了一些Controller类，这样对于具体业务逻辑的处理会委托给对应的service类对象，Controller类起到了分派器的作用。

总经理模块的设计如下:

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MmFiNmNmZmM1MTBjYzlmNzc5NjRiZjFjNTAxNzJjMDBfRkxDdjNRZDZpek5ha1NkUzV1UHFFb2ZFT0J3RkNROVNfVG9rZW46Ym94Y25SS3dxMUVucnQ1ZEsxbTJJV2szblRkXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

总经理模块各个类的职责如下:

| 模块                     | 职责                                                         |
| ------------------------ | ------------------------------------------------------------ |
| PromotionController      | 负责通过PromotionService接口控制业务逻辑方法的调用，隔离业务逻辑职责和逻辑控制职责 |
| PromotionStrategy        | 抽象的促销策略类                                             |
| PromotionStrategyFactory | 创建具体促销策略的工厂                                       |
| PromotionContext         | 选择创建具体的策略                                           |
| CustomerStrategy         | 具体的促销策略类(用户等级促销策略)                           |
| PacketStrategy           | 具体的促销策略类(特价包促销策略)                             |
| PriceStrategy            | 具体的促销策略类(总价促销策略)                               |
| PromotionService         | 提供促销策略查询和创建的接口                                 |
| PromotionVO              | 策略信息的展示层对象，用于前后端传递数据                     |
| GiftVO                   | 赠品信息的展示层对象，用于前后端传递数据                     |
| PromotionDao             | 负责与数据库的交互，实现促销策略的创建与查询                 |
| GiftDao                  | 负责与数据库的交互，实现赠品的创建与查询                     |
| GiftPO                   | 赠品的持久层对象，与数据库表一一对应                         |
| PacketPromotionPO        | 特价包的持久层对象，与数据库表一一对应                       |
| PromotionPO              | 策略的持久层对象，与数据库表一一对应                         |

(3) 模块内部类的接口规范

1. PromotionController

   1. | 提供的服务(供接口)                              |                                                      |                                                              |
      | ----------------------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------ |
      | PromotionController.createPromotionStrategy     | 语法                                                 | public Response createPromotionStrategy(@RequestBody PromotionVO promotionStrategyForm) |
      | 前置条件                                        | 用户请求添加促销策略                                 |                                                              |
      | 后置条件                                        | 调用PromotionService的addPromotionStrategy方法       |                                                              |
      | PromotionController.findPromotionStrategyByTime | 语法                                                 | public Response findPromotionStrategyByTime(@Param("beginTime") String beginTime**,** @Param("endTime") String endTime) |
      | 前置条件                                        | 用户请求根据日期查询促销手段                         |                                                              |
      | 后置条件                                        | 调用PromotionService的getPromotionStrategyByTime方法 |                                                              |
      | PromotionController.updatePromotionInfo         | 语法                                                 | public Response updatePromotionInfo(@RequestBody PromotionVO promotionVO) |
      | 前置条件                                        | 用户请求更新促销信息的API                            |                                                              |
      | 后置条件                                        | 调用PromotionService的updatePromotionInfo方法        |                                                              |
      | PromotionController.deletePromotionById         | 语法                                                 | public Response deletePromotionById(@RequestParam(value = "id") int id) |
      | 前置条件                                        | 用户请求删除促销信息的API                            |                                                              |
      | 后置条件                                        | 调用PromotionService的deletePromotionById方法        |                                                              |

​    

展示层与逻辑层之间的接口

1. PromotionService

   1. | 提供的服务(供接口)                          |                                          |                                                              |
      | ------------------------------------------- | ---------------------------------------- | ------------------------------------------------------------ |
      | PromotionService.addPromotionStrategy       | 语法                                     | void addPromotionStrategy(PromotionVO promotionVO)**;**      |
      | 前置条件                                    | 正在添加促销策略                         |                                                              |
      | 后置条件                                    | 新增促销策略，更新数据库信息             |                                                              |
      | PromotionService.getPromotionStrategyByTime | 语法                                     | List<PromotionPO> getPromotionStrategyByTime(String beginDateStr**,** String endDateStr) |
      | 前置条件                                    | 正在根据时间段查询促销策略               |                                                              |
      | 后置条件                                    | 查询促销方式，在促销列表显示             |                                                              |
      | EmployeeService.deleteEmployeeById          | 语法                                     | void deleteEmployeeById(int id);                             |
      | 前置条件                                    | 正在删除对应的员工信息                   |                                                              |
      | 后置条件                                    | 删除对应的员工信息，更新对应的数据库信息 |                                                              |

逻辑层和数据层之间的接口

1. PromotionDao

| 提供的服务(供接口)                          |                                          |                                                              |
| ------------------------------------------- | ---------------------------------------- | ------------------------------------------------------------ |
| PromotionDao.getLatestSheet                 | 语法                                     | PromotionPO getLatestSheet()**;**                            |
| 前置条件                                    | 正在动态生成促销ID                       |                                                              |
| 后置条件                                    | 生成动态ID，更新PromotionPO信息          |                                                              |
| PromotionService.getPromotionStrategyByTime | 语法                                     | List<PromotionPO> getPromotionStrategyByTime(String beginDateStr**,** String endDateStr) |
| 前置条件                                    | 正在根据时间段查询促销策略               |                                                              |
| 后置条件                                    | 查询促销方式，在促销列表显示             |                                                              |
| PromotionService.deletePromotionById        | 语法                                     | void deletePromotionById(int id);                            |
| 前置条件                                    | 正在删除对应的促销信息                   |                                                              |
| 后置条件                                    | 删除对应的促销信息，更新对应的数据库信息 |                                                              |

1. GiftDao

   1. | 提供的服务(供接口)  |                                          |                                                        |
      | ------------------- | ---------------------------------------- | ------------------------------------------------------ |
      | GiftDao.addGift     | 语法                                     | int addGift(GiftPO giftPO)**;**                        |
      | 前置条件            | 正在添加赠品                             |                                                        |
      | 后置条件            | 生成曾品清单，更新GiftPO信息             |                                                        |
      | GiftDao.getGiftById | 语法                                     | List<GiftPO> getGiftById(String promotionSheetId)**;** |
      | 前置条件            | 正在根据促销策略的ID查询赠品             |                                                        |
      | 后置条件            | 返回赠品清单，供PromotionServiceImpl使用 |                                                        |

(4) 业务逻辑层的动态模型

1. 制定促销策略顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NmFkNWU4MzJkNmU1ODVhNjBmNTBmMjlhMjE3YjFmYTNfWGJVelBZSXJMbHRsRmVraktpSGNTM3RVTjlIY1djcjRfVG9rZW46Ym94Y25CbjlUYkhWdXZUeXdwNlVkem40OGxlXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 查找促销策略顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YmViMmMxYWE2MzIyZjJkMTcyNjlmZDFmM2E0NTYwYTNfc053REl6ckZiTzBpSTF6TmRLWVkzN2hHTWttZFpDMW9fVG9rZW46Ym94Y25VMGhUMGJYZzE5VGU5eDBTVlJRbEViXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 查看销售明细表系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=N2ExOWI4NDI4OTAzNDhkMGVmZmM3NzEyNGMwNGM4ZDZfcmhBdXNIR1BoanZhanRJZVQ1ZlNNc0N1amtvRG5CYWpfVG9rZW46Ym94Y256YkZWMExqRUNVYmtCR1QwQ0trVEJoXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 查看经营历程表系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NzNmMjAwYzBiYzFlNmFmODUyZGRlMDBkNjE1NTNiNmVfaEZFNGRDQXQ5blpyZWkyMU1aSW5wVHoxczFQVHpRTHlfVG9rZW46Ym94Y241NHY2c2FIam5sUmJoWTR2NUxuRUZkXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

1. 查看经营情况表系统顺序图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MjNiZmE0NGZkYmY0YjNhZmE2OTE2NDYzMWMzOGRiMGNfbUpGOVhwVXBYTkxaOWJGQXA5bjJscVpyWmFLUHVMYWxfVG9rZW46Ym94Y245NnZCZjNsTXpiSHhoNUNscmdsRzRlXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

(5) 业务逻辑层的设计原理

利用委托式控制风格，每个界面需要访问的业务逻辑有各自的控制器委托给不同的业务逻辑对象。

## 5.依赖视角 

#### 5.1 前端开发包图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=YzczNTZhYjI1MWE2MGRmYWU0NmY2ZTdkODcwYjUxYWRfSEg3eE03SXFCS1NGNldhT1FxS3UydFdwTUlyVzg5M3FfVG9rZW46Ym94Y25qcXhiZG9MYk9yZFAyMm9DUXl2TWtkXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/team9_企业ERP系统前端开发包图.svg)

图3 企业ERP系统前端开发包图

#### 5.2 后端开发包图

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OWFhZDdlMGQ1ZjViMjA3MGM1MTc5ODc3MGUzMjkzNjhfRVdudU1zNW9VTjMxOGF0YkxQa2twRkZ1SkZuM0Nxb1lfVG9rZW46Ym94Y25OWVNUSFhiTkN2eURISEhhM0lmRlJoXzE2NTc0NTA1Mzk6MTY1NzQ1NDEzOV9WNA)

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/team9_企业ERP系统后端开发包图.svg)

图4 企业ERP系统后端开发包图