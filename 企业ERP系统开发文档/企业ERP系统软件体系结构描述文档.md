# 企业ERP系统软件体系结构描述文档

## 小组分工

| 姓名   | 学号      | 分工                                                       |
| ------ | --------- | ---------------------------------------------------------- |
| 韩陈旭 | 201250037 | 系统逻辑视角、系统组合视角、业务逻辑层的分解、数据层的分解 |
| 万沛沛 | 201250038 | 模块职责、业务逻辑层的分解、数据层的分解                   |
| 邓尤亮 | 201250035 | 用户界面层的分解、业务逻辑层的分解、数据层的分解           |
| 华广松 | 201840309 | 信息视角、业务逻辑层的分解、数据层的分解                   |

## 更新版本记录

| 修改日期  | 修改人                         | 备注                                                         | 版本号 |
| --------- | ------------------------------ | ------------------------------------------------------------ | ------ |
| 2022.4.25 | 韩陈旭、万沛沛、邓尤亮、华广松 | 创建了企业ERP系统软件体系结构描述文档                        | v.1.0  |
| 2022.4.26 | 韩陈旭                         | 创建逻辑视角、组合视角                                       | v.1.1  |
| 2022.4.27 | 万沛沛                         | 创建模块职责                                                 | v.1.2  |
| 2022.4.28 | 邓尤亮                         | 创建用户界面层分解                                           | v.1.3  |
| 2022.4.29 | 华广松                         | 创建信息视角                                                 | v.1.4  |
| 2022.5.1  | 韩陈旭                         | 添加CategoryService、CommodityService业务逻辑层接口规范和对应的数据层接口规范 | v.1.5  |
| 2022.5.2  | 邓尤亮                         | 添加PurchaseService、ApprovalService、SaleService业务逻辑层接口规范和对应的数据层接口规范 | v.1.6  |
| 2022.5.4  | 万沛沛                         | 添加WarehouseService业务逻辑层接口规范和对应的数据层接口规范 | v.1.7  |
| 2022.5.4  | 华广松                         | 添加ClientService业务逻辑层接口规范和对应的数据层接口规范    | v.1.8  |
| 2022.7.7  | 韩陈旭                         | 添加CompanyAccountService业务逻辑层接口规范和对应的数据层接口规范 | v.2.0  |
| 2022.7.8  | 邓尤亮                         | 添加CollectionService、PaymentService、SalaryService业务逻辑层接口规范和对应的数据层接口规范 | v.2.1  |

## 1.引言

### 1.1 编制目的

本报告详细完成企业ERP系统的概要设计，达到指导详细设计和开发的目的，同时实现和测试人员及用户的沟通。

本报告面向开发人员、测试人员及最终用户而编写，是了解系统的导航。

### 1.2 词汇表

| **词汇名称** | **词汇含义**   | **备注**                                   |
| ------------ | -------------- | ------------------------------------------ |
| HTTP REST    | 表述性状态传递 | 是一种针对网络应用的设计和开发方式         |
| UI           | 用户接口       | 即User Interface，用户界面，人机交互的媒介 |
| BL           | 业务逻辑       | 即Business Logic，是层次体系中的中间层     |

### 1.3 参考资料

[1] 骆斌，丁二玉，刘钦. 软件工程与计算.卷二,软件开发的技术基础.volume Ⅱ, Fundamentals of software development technology[M]. 机械工业出版社, 2012.

[2] 企业ERP系统需求规格说明文档 V1.0.

## 2.产品概述

参考企业ERP系统用例文档和企业ERP系统需求规格说明文档中对产品的概括描述。

## 3.逻辑视角@韩陈旭 

在企业ERP系统中，选择了分层体系结构风格，将系统分为3层(展示层、业务逻辑层、数据层)能够很好地示意各个高层抽象。展示层包含GUI页面的实现，业务逻辑层包含业务逻辑处理的实现，数据层负责数据的持久化和访问。分层体系结构的逻辑视角和逻辑设计方案如图1和图2所示。

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=NDE2NzQxZGRjNWYxZDE2ZDMxOGQ5MWQzZTc3NTkxOGJfT01hbGFEWHhYd1VMYnlXZkFOS2hVNkF5OEpPdWh6WFFfVG9rZW46Ym94Y25kZ1ROeXRtVTdRV2lSWXZYdFlzeEhmXzE2NTc0NTA2MTE6MTY1NzQ1NDIxMV9WNA)

图1 参照体系结构风格的包图表达逻辑视角

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=MzkzMDM3YzM2ODk0Njg0MWMyZWEyZGE4MWNiNzNhZmVfYUxva2hSdVlaUjBKUUNwRm1QZFE5RE1LbUpWUklBMkpfVG9rZW46Ym94Y25kdzV4RjEzQzBjTk1yWjZZT3Z5RkFjXzE2NTc0NTA2MTE6MTY1NzQ1NDIxMV9WNA)

  图2 软件体系结构逻辑设计方案(草稿)

## 4.组合视角@韩陈旭 

### 4.1 开发包图

企业ERP系统的最终开发包设计如表1所示。

表1 企业ERP系统的最终开发包设计

| 开发(物理包)           | 依赖的其他开发包                                             |
| ---------------------- | ------------------------------------------------------------ |
| mainui                 | Loginui, categoryui, commodityui, inventoryui, clientui, purchaseui, saleui, regulationsui, logui, accountui, financeui, initialui, employeeui, attendanceui, bonusui, promotionui, approvalui, userui, vo |
| loginui                | vueframework, vo, loginservice                               |
| loginservice           | springframework                                              |
| loginbl                | loginservice, logindataservice, userbl, springframework, vo, po |
| logindataservice       | databaseutility, po                                          |
| logindata              | databaseutility, po, logindataservice, http rest             |
| categoryui             | vueframework, vo, categoryservice                            |
| categoryservice        | springframework                                              |
| categorybl             | categoryservice, commodityservice, springframework, categorydataservice, vo, po |
| categorydataservice    | databaseutility, po                                          |
| categorydata           | databaseutility, po, categorydataservice, http rest          |
| commodityui            | vueframework, vo, commodityservice                           |
| commodityservice       | springframework                                              |
| commoditybl            | categoryservice, commodityservice, inventoryservice,promotionservice, commoditydataservice, springframework, vo, po |
| commoditydataservice   | databaseutility, po                                          |
| commoditydata          | databaseutility, po, categorydataservice, http rest          |
| inventoryui            | vueframework, vo, inventoryservice                           |
| inventoryservice       | springframework                                              |
| inventorybl            | commoditybl,  inventoryservice, inventorydataservice, springframework, vo, po |
| inventorydataservice   | databaseutility, po                                          |
| inventorydata          | databaseutility, po, inventorydataservice, http rest         |
| clientui               | vueframework, vo, clientservice                              |
| clientservice          | springframework                                              |
| clientbl               | clientservice, purchaseservice, saleservice, clientdataservice, springframework, vo, po |
| clientdataservice      | databaseutility, po                                          |
| clientdata             | databaseutility, po, clientdataservice, http rest            |
| purchaseui             | vueframework, vo, purchaseservice                            |
| purchaseservice        | springframework                                              |
| purchasebl             | categoryservice, commodityservice, inventoryservice, clientservice, purchaseservice, purchasedataservice, springframework, vo, po |
| purchasedataservice    | databaseutility, po                                          |
| purchasedata           | databaseutility, po, purchasedataservice, http rest          |
| saleui                 | vueframework, vo, saleservice                                |
| saleservice            | springframework                                              |
| salebl                 | saleservice, categoryservice, commodityservice, inventoryservice, clientservice, userservice, springframework, promotionservice, vo, po, saledataservice |
| saledataservice        | databaseutility, po                                          |
| saledata               | databaseutility, po, saledataservice, http rest              |
| regulationsui          | vueframework, vo, regulationsservice                         |
| regulationsservice     | springframework                                              |
| regulationsbl          | employeeservice, regulationsservice, springframework, regulationsdataservice, vo, po |
| regulationsdataservice | databaseutility, po                                          |
| regulationsdata        | databaseutility, po, regulationsdataservice, http rest       |
| logui                  | vueframework, vo, logservice                                 |
| logservice             | springframework                                              |
| logbl                  | logservice,  userservice, springframework, logdataservice, vo, po |
| logdataservice         | databaseutility, po                                          |
| logdata                | databaseutility, po, logdataservice, http rest               |
| accountui              | vueframework, vo, accountservice                             |
| accountservice         | springframework                                              |
| accountbl              | springframework, accountservice, vo, po, accountdataservice  |
| accountdataservice     | databaseutility, po                                          |
| accountdata            | databaseutility, po, accountdataservice, http rest           |
| financeui              | vueframework, vo, financeservice                             |
| financeservice         | springframework                                              |
| financebl              | clientservice, userservice, accountdataservice, employeeservice, categoryservice, commodityservice, inventoryservice, financeservice, financedataservice, springframework, vo, po |
| financedataservice     | databaseutility, po                                          |
| financedata            | databaseutility, po, financedataservice, http rest           |
| initialui              | vueframework, vo, financeservice                             |
| initialservice         | springframework                                              |
| initialbl              | commodityservice, clientservice, accountservice, purchasebl, saleservice, initialservice, initialdataservice, categoryservice, springframework, vo, po |
| initialdataservice     | databaseutility, po                                          |
| initialdata            | databaseutility, po, initialdata, http rest                  |
| employeeui             | vueframework, vo, employeeservice                            |
| employeeservice        | springframework                                              |
| employeebl             | employeeservice, attendanceservice, springframework, vo, po  |
| employeedataservice    | databaseutility, po                                          |
| employeedata           | databaseutility, po, employeedataservice, http rest          |
| attendanceui           | vueframework, vo, attendanceservice                          |
| attendanceservice      | springframework                                              |
| attendancebl           | employeeservice, attendanceservice, bonusservice, attendancedataservice springframework, vo, po |
| attendancedataservice  | databaseutility, po                                          |
| attendancedata         | databaseutility, po, attendancedataservice, http rest        |
| bonusui                | vueframework, vo, bonusservice                               |
| bonusservice           | springframework                                              |
| bonusbl                | employeeservice, bonusservice, bonusdataservice, springframework, vo, po |
| bonusdataservice       | databaseutility, po                                          |
| bonusdata              | databaseutility, po, bonusdataservice, http rest             |
| promotionui            | vueframework, vo, promotionservice                           |
| promotionservice       | springframework                                              |
| promotionbl            | commodityservice, promotionservice, springframework, vo, po  |
| promotiondataservice   | databaseutility, po                                          |
| promotionuidata        | databaseutility, po, promotiondataservice, http rest         |
| approvalui             | vueframework, vo, approvalservice                            |
| approvalservice        | springframework                                              |
| approvalbl             | Inventoryservice, purchaseservice, saleservice, financeservice,bonusdataservice approvalservice, springframework, vo, po |
| approvaldataservice    | databaseutility, po                                          |
| approvaldata           | databaseutility, po, approvaldataservice, http rest          |
| userui                 | vueframework, vo, userservice                                |
| userservice            | springframework                                              |
| userbl                 | userservice, purchaseservice, logservice, approvalbl, userdataservice  springframework, vo, po |
| userdataservice        | databaseutility, po                                          |
| userdata               | databaseutility, po, userdataservice, http rest              |
| vo                     |                                                              |
| po                     |                                                              |
| databaseutility        | Mybatis                                                      |
| http rest              | RESTful API                                                  |
| vueframework           | Vue.js                                                       |
| springframework        | SpringBoot                                                   |

#### 4.1.2 前端开发包图

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/team9_企业ERP系统前端开发包图.svg)

图3 企业ERP系统前端开发包图

#### 4.1.3 后端开发包图

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/team9_企业ERP系统后端开发包图.svg)

图4 企业ERP系统后端开发包图

### 4.2 物理部署



![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/team9_物理部署图.svg)

## 5.架构设计

### 5.1 模块职责@万沛沛 ✅

由于Web应用的特殊性，企业ERP系统的客户端不需要进行开发，只需用户自行安装浏览器即可，故略过。服务器端模块视图如下所示。

#### 5.1.1 模块视图

![svg](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250038_服务端模块视图.svg)

#### 5.1.2 各层职责

**表2** **服务端各层的职责**

| **层**     | **职责**                                                     |
| ---------- | ------------------------------------------------------------ |
| 启动模块   | 负责初始化网络通信机制，启动用户界面                         |
| 用户界面层 | 负责存储用户页面的数据，并根据用户触发的事件以及服务端返回的信息更改相应的状态 |
| 业务逻辑层 | 负责执行业务处理逻辑                                         |
| 数据层     | 负责数据的持久化和访问                                       |

每一层只是使用下方直接接触的层，层与层之间仅仅是通过接口的调用来完成的，层之间调用的接口如表3所示。

**表3** **层之间调用接口**

| **接口**                                                     | **服务调用方** | **服务提供方** |
| ------------------------------------------------------------ | -------------- | -------------- |
| loginBLService accountBLService categoryBLService financeBLService commodityBLService initialBLService inventoryBLService employeeBLService clientBLService attendanceBLService purchaseBLService bonusBLService saleBLService promotionBLService regulationBLService approvalBLService logBLService userBLService | 用户界面层     | 业务逻辑层     |
| loginDataService accountDataService categoryDataService financeDataService commodityDataService initialDataService inventoryDataService employeeDataService clientDataService attendanceDataService purchaseDataService bonusDataService saleDataService promotionDataService regulationDataService approvalDataService logDataService userDataService | 业务逻辑层     | 数据层         |

### 5.2 用户界面层分解@邓尤亮 

根据需求，系统共存在24类用户界面，界面跳转如图所示：



![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/201250035-用户界面跳转.svg)



#### 5.2.1 用户界面层模块的职责

| 模块      | 职责                                 |
| --------- | ------------------------------------ |
| MainFrame | 界面 MainFrame：负责界面的显示和跳转 |

#### 5.2.2 用户界面层模块的接口规范

用户界面层模块的接口规范：

| MainFrame | 语法                   | init(args:String[]) |
| --------- | ---------------------- | ------------------- |
| 前置条件  | 初始化数据库           |                     |
| 后置条件  | 运行前端，显示登陆界面 |                     |

用户界面层模块需要的服务接口：

| 服务名                              | 服务                               |
| ----------------------------------- | ---------------------------------- |
| businesslogicservice.LoginBLService | 登陆界面的业务逻辑接口             |
| businesslogicservice.*BLService     | 每个界面都有一个相应的业务逻辑接口 |



### 5.3 业务逻辑层的分解@韩陈旭 @万沛沛 @邓尤亮 @华广松 

业务逻辑层包括多个针对界面的业务逻辑处理对象。业务逻辑层的设计如下图所示。

![img](https://wntz9g285u.feishu.cn/space/api/box/stream/download/asynccode/?code=OGE1YmI1OGI2YjE5ZDQ3YTZjZGY0OTA1MjExNzBmMzhfREp0OGhHSk52N1E4OWpKUnhmbFZlYW4xazd4YTgzczNfVG9rZW46Ym94Y24zN3dZRFlxSFRqVmFlejk1bXg1eEljXzE2NTc0NTA2MTE6MTY1NzQ1NDIxMV9WNA)

![](https://seec-homework.oss-cn-shanghai.aliyuncs.com/team9_业务逻辑层的设计.svg)

#### 5.3.1 业务逻辑层模块的职责

业务逻辑层模块的职责如下标所示

| 模块             | 职责                                           | 分工(写接口) |
| ---------------- | ---------------------------------------------- | ------------ |
| loginbl          | 负责实现登录界面所需要的服务                   |              |
| categorybl       | 负责实现商品分类与商品分类管理界面所需要的服务 | ✔            |
| commoditybl      | 负责实现商品与商品管理界面所需要的服务         | ✔            |
| clientbl         | 负责实现客户管理界面所需要的服务               | @华广松 ✅    |
| purchasebl       | 负责实现进货界面所需要的服务                   | @万沛沛 ✅    |
| salebl           | 负责实现销售界面所需要的服务                   | @万沛沛 ✅    |
| regulationsbl    | 负责实现制度信息界面所需要的服务               |              |
| logbl            | 负责实现查看日志界面所需要的服务               |              |
| accountbl        | 负责实现公司银行账户管理界面所需要的服务       |              |
| financebl        | 负责实现财务单据创建与管理界面所需要的服务     |              |
| initialbl        | 负责实现期初建账界面所需要的服务               |              |
| employeebl       | 负责实现员工管理界面所需要的服务               |              |
| attendancebl     | 负责实现考勤管理界面所需要的服务               |              |
| bonusbl          | 负责实现年终奖管理界面所需要的服务             |              |
| promotionbl      | 负责实现促销策略制定界面所需要的服务           | @邓尤亮      |
| approvalbl       | 负责实现审批界面所需要的服务                   | @华广松 ✅    |
| userbl           | 负责实现用户管理界面所需要的服务               |              |
| WarehouseService | 负责实现仓库管理界面所需要的服务               | @邓尤亮      |

#### 5.3.2 业务逻辑层模块的接口规范

库存模块和销售模块的接口规范分别如下所示。

##### CategoryService

| **categorybl模块提供的服务(供接口)**     |                                          |                                                              |
| ---------------------------------------- | ---------------------------------------- | ------------------------------------------------------------ |
| `Category``BLService``.createCategory`   | 语法                                     | `CategoryVO createCategory``*(*``Integer parentId, String name``*);*` |
| 前置条件                                 | 正在进行商品分类管理                     |                                                              |
| 后置条件                                 | 添加一个商品分类，更新商品分类列表       |                                                              |
| `Category``BLService``.queryAllCategory` | 语法                                     | `List``*<*``CategoryVO``*>* ``queryAllCategory``*()*``;`     |
| 前置条件                                 | 正在进行商品分类管理                     |                                                              |
| 后置条件                                 | 查询所有的商品分类信息，展示商品分类列表 |                                                              |
| `Category``BLService``.updateCategory`   | 语法                                     | `CategoryVO updateCategory``*(*``Integer id, String name``*)*``;` |
| 前置条件                                 | 正在进行商品分类管理                     |                                                              |
| 后置条件                                 | 更新一个商品分类信息，更新商品分类列表   |                                                              |
| `Category``BLService``.deleteCategory`   | 语法                                     | `void deleteCategory``*(*``Integer id``*)*``;`               |
| 前置条件                                 | 正在进行商品分类                         |                                                              |
| 后置条件                                 | 删除一个商品分类信息，更新商品分类列表   |                                                              |

| **categorybl模块需要的服务(需接口)**                         |                              |
| ------------------------------------------------------------ | ---------------------------- |
| 服务名                                                       | 服务                         |
| `CategoryDataService.createCategory``*(*``CategoryPO categoryPO``*)*` | 添加商品分类                 |
| `CategoryDataService.findByCategoryId``*(*``Integer categoryId``*)*` | 根据商品分类的ID获取商品分类 |
| `CategoryDataService.findAl``l``*()*`                        | 获取所有商品分类             |
| `CategoryDataService.updateById``*(*``CategoryPO categoryPO``*)*` | 更新商品分类                 |
| `CategoryDataService.deleteById``*(*``Integer id``*)*`       | 删除指定ID的商品分类         |

##### CommodityService

| **commoditybl模块提供的服务(供接口)**    |                              |                                                              |
| ---------------------------------------- | ---------------------------- | ------------------------------------------------------------ |
| `Commodity``BLService``.createProduct`   | 语法                         | `ProductInfoVO createProduct``*(*``CreateProductVO inputVO``*)*``;` |
| 前置条件                                 | 正在进行商品管理             |                                                              |
| 后置条件                                 | 添加一个商品，更新商品列表   |                                                              |
| `Commodity``BLService``.updateProduct`   | 语法                         | `ProductInfoVO updateProduct``*(*``ProductInfoVO productInfoVO``*)*``;` |
| 前置条件                                 | 正在进行商品管理             |                                                              |
| 后置条件                                 | 更新一个商品，更新商品列表   |                                                              |
| `Commodity``BLService``.queryAllProduct` | 语法                         | `List``*<*``ProductInfoVO``*>* ``queryAllProduct``*()*``;`   |
| 前置条件                                 | 正在进行商品管理             |                                                              |
| 后置条件                                 | 查询所有的商品，显示商品列表 |                                                              |
| `Commodity``BLService``.deleteById`      | 语法                         | `void deleteById``*(*``String id``*)*``;`                    |
| 前置条件                                 | 正在进行商品分类             |                                                              |
| 后置条件                                 | 删除一个商品，更新商品列表   |                                                              |

| **commoditybl模块需要的服务(需接口)**                        |                    |
| ------------------------------------------------------------ | ------------------ |
| 服务名                                                       | 服务               |
| `CommodityDataService.createProduct``*(*``ProductPO productPO``*)*` | 创建一个商品       |
| `CommodityDataService.updateById``*(*``ProductPO productPO``*)*` | 根据商品ID更新商品 |
| `CommodityDataService.findById``*(*``String id``*)*`         | 根据商品ID查询商品 |
| `CommodityDataService.findAll``*()*`                         | 查询所有商品       |
| `CommodityDataService.deleteById``*(*``String id``*)*`       | 根据商品ID删除商品 |

##### ClientService

| **clientbl模块提供的服务（供接口）** |                                          |                                                              |
| ------------------------------------ | ---------------------------------------- | ------------------------------------------------------------ |
| `ClientBlService.getClientList`      | 语法                                     | `public ArrayList<ClientVo> getClientList(ClientQueryVO query)` |
| 前置条件                             | 正在进行客户管理                         |                                                              |
| 后置条件                             | 返回客户列表                             |                                                              |
| `ClientBlService.addClient`          | 语法                                     | `public String addClient(ClientVO client)`                   |
| 前置条件                             | 正在进行客户管理                         |                                                              |
| 后置条件                             | 添加一个客户，返回客户ID                 |                                                              |
| `ClientBlService.editClient`         | 语法                                     | `public void editClient(ClientVO client)`                    |
| 前置条件                             | 正在进行客户管理                         |                                                              |
| 后置条件                             | 编辑一个客户，更新客户列表               |                                                              |
| `ClientBlService.deleteClient`       | 语法                                     | `public void deleteClient(String clientID)`                  |
| 前置条件                             | 正在进行客户管理                         |                                                              |
| 后置条件                             | 删除一个客户，更新客户列表               |                                                              |
| `ClientBlService.getUserList`        | 语法                                     | `public ArrayList<UserVO> getUserList(UserQueryVO query)`    |
| 前置条件                             | 正在进行客户管理                         |                                                              |
| 后置条件                             | 取得符合条件的用户列表，作为业务员的备选 |                                                              |

| **clientbl模块需要的服务（需接口）**          |                          |
| --------------------------------------------- | ------------------------ |
| 服务名                                        | 服务                     |
| `ClientDataService.find(ClientQueryPO query)` | 根据筛选条件进行查找客户 |
| `ClientDataService.insert(ClientPO po)`       | 添加客户                 |
| `ClientDataService.delete(String clientID)`   | 删除客户                 |
| `ClientDataService.update(ClientPO po)`       | 更改客户信息             |
| `ClientDataService.getID()`                   | 得到新的客户ID           |

##### PurchaseService

| **purchasebl提供的服务（供接口）**            |                           |                                                              |
| --------------------------------------------- | ------------------------- | ------------------------------------------------------------ |
| `PurchaseBLService.getSupplierList`           | 语法                      | `List<ClientVO> getSupplierList()`                           |
| 前置条件                                      | 开始制定进货/进货退货单据 |                                                              |
| 后置条件                                      | 返回供应商列表            |                                                              |
| `PurchaseBLService.getProductList`            | 语法                      | `List<ProductInfoVO> getProductList()`                       |
| 前置条件                                      | 开始制定进货/进货退货单据 |                                                              |
| 后置条件                                      | 返回商品列表              |                                                              |
| `PurchaseBLService.submit`                    | 语法                      | `String submit(PurchaseTradeBillVO purchaseTradeBillVO)`     |
| 前置条件                                      | 开始制定进货单            |                                                              |
| 后置条件                                      | 提交单据，返回单据ID      |                                                              |
| `PurchaseBLService.editPurchaseTradeBill`     | 语法                      | `void editPurchaseTradeBill(PurchaseTradeBillVO purchaseTradeBillVO)` |
| 前置条件                                      | 制定进货单据完成          |                                                              |
| 后置条件                                      | 更新单据信息              |                                                              |
| `PurchaseBLService.getPurchaseTradeBillList`  | 语法                      | `List<PurchaseTradeBillVO> getPurchaseTradeBillList()`       |
| 前置条件                                      | 无                        |                                                              |
| 后置条件                                      | 取得进货单列表            |                                                              |
| `PurchaseBLService.submit`                    | 语法                      | `String submit(PurchaseRefundBillVO purchaseRefundBillVO)`   |
| 前置条件                                      | 开始制定进货退货单        |                                                              |
| 后置条件                                      | 提交单据，返回单据ID      |                                                              |
| `PurchaseBLService.editPurchaseRefundBill`    | 语法                      | `void editPurchaseRefundBill(PurchaseRefundBillVO purchaseRefundBillVO)` |
| 前置条件                                      | 制定进货退化单据完成      |                                                              |
| 后置条件                                      | 更新单据信息              |                                                              |
| `PurchaseBLService.getPurchaseRefudnBillList` | 语法                      | `List<PurchaseRefundBillVO> getPurchaseRefundBillList()`     |
| 前置条件                                      | 无                        |                                                              |
| 后置条件                                      | 取得进货退货单列表        |                                                              |

| **purchasebl模块需要的服务（需接口）**                |                    |
| ----------------------------------------------------- | ------------------ |
| 服务名                                                | 服务               |
| `PurchaseDataService.findAll``TradeBill``()`          | 取得进货单列表     |
| `PurchaseDataService.insert(PurchaseTradeBillPO po)`  | 添加进货单         |
| `PurchaseDataService.update(PurchaseTradeBillPO po)`  | 更新进货单         |
| `PurchaseDataService.findAllRefundBill()`             | 取得进货退货单列表 |
| `PurchaseDataService.insert(PurchaseRefundBillPO po)` | 添加进货退货单     |
| `PurchaseDataService.update(PurchaseRefundBillPO po)` | 更新进货退货单     |

##### ApprovalService

| **approvalbl提供的服务（供接口）** |                                            |                                                              |
| ---------------------------------- | ------------------------------------------ | ------------------------------------------------------------ |
| `ApprovalBlService.getBillList`    | 语法                                       | `public ArrayList<BillVO> getBillList(BillQueryVO query)`    |
| 前置条件                           | 无                                         |                                                              |
| 后置条件                           | 系统显示该类型的提交单据                   |                                                              |
| `ApprovalBlService.pass`           | 语法                                       | `public void pass(BillVO vo, UserVO senderVO)`               |
| 前置条件                           | 无                                         |                                                              |
| 后置条件                           | 系统修改单据的数据，状态以及单据涉及的数据 |                                                              |
| `ApprovalBlService.reject`         | 语法                                       | `public void reject(BillVO vo, String reason, UserVO senderVO)` |
| 前置条件                           | 无                                         |                                                              |
| 后置条件                           | 系统修改单据的状态                         |                                                              |

| **approvalbl需要的服务（需接口）** |      |
| ---------------------------------- | ---- |
| 服务名                             | 服务 |
| 无                                 |      |

##### SaleService

| SaleService提供的服务(供接口)                    |                                                              |                                                              |
| ------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| SaleService.findAllSaleDetailByTime              | 语法                                                         | List<SaleDetailVO> findAllSaleDetailByTime(String beginDateStr, String endDateStr); |
| 前置条件                                         | 正在查询销售明细表                                           |                                                              |
| 后置条件                                         | 根据单据创建的时间查询出所有单据的信息，在销售明细表列表显示 |                                                              |
| SaleService.makeSaleSheet                        | 语法                                                         | **void** makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO); |
| 前置条件                                         | 正在制定销售单                                               |                                                              |
| 后置条件                                         | 更新销售单信息                                               |                                                              |
| SaleService.getSaleSheetByState                  | 语法                                                         | **List<SaleSheetVO> getSaleSheetByState(SaleSheetState state);** |
| 前置条件                                         | 正在获取销售单                                               |                                                              |
| 后置条件                                         | 根据状态获取销售单信息                                       |                                                              |
| SaleService.approval                             | 语法                                                         | **void approval(String saleSheetId, BaseEnum state);**       |
| 前置条件                                         | 正在审批销售单                                               |                                                              |
| 后置条件                                         | 更新销售单审批状态                                           |                                                              |
| SaleService.getMaxAmountCustomerOfSalesmanByTime | 语法                                                         | **CustomerPurchaseAmountVO getMaxAmountCustomerOfSalesmanByTime(String salesman, String beginDateStr, String endDateStr);** |
| 前置条件                                         | 无                                                           |                                                              |
| 后置条件                                         | 获取某时间段内某销售人员消费金额最大的客户                   |                                                              |
| SaleService.getSaleSheetById                     | 语法                                                         | **SaleSheetVO getSaleSheetById(String saleSheetId);**        |
| 前置条件                                         | 正在搜索销售单                                               |                                                              |
| 后置条件                                         | 根据销售单Id搜索销售单信息                                   |                                                              |
| SaleService.findAllSaleDetailByTime              | 语法                                                         | List<SaleDetailVO> findAllSaleDetailByTime(String beginDateStr, String endDateStr); |
| 前置条件                                         | 正在搜索销售单详细信息                                       |                                                              |
| 后置条件                                         | 根据时间段搜索销售详细信息                                   |                                                              |



| SaleService模块需要的服务(需接口)                            |                                              |
| ------------------------------------------------------------ | -------------------------------------------- |
| 服务名                                                       | 服务                                         |
| saleSheetDao.getLatestSheet()                                | 查找最近的销售单                             |
| saleSheetDao.saveBatchSheetContent(List<SaleSheetContent>)   | 创建销售单内容                               |
| saleSheetDao.saveSheet(SaleSheetPO)                          | 创建销售单                                   |
| saleSheetDao.findAllSheet(SaleSheetQuery)                    | 查找所有满足查询条件的销售单                 |
| saleSheetDao.updateSheetState(String saleSheetId, SaleSheetState state) | 更新指定销售单的状态                         |
| saleSheetDao.updateSheetStateOnPrev(String saleSheetId, BaseEnum prevState, BaseEnum state) | 根据当前状态更新销售单状态                   |
| saleSheetDao.findContentBySheetId(String saleSheetId)        | 根据销售单Id寻找销售单内容列表               |
| saleSheetDao.findSheetById(String saleSheetId)               | 根据销售单Id寻找销售单列表                   |
| saleSheetDao.getMaxAmountCustomerOfSalesmanByTime(salesman, beginTime, endTime) | 根据业务员和时间段寻找消费金额最大的客户信息 |
| saleSheetDao.findAllSaleDetailByTime(beginDateStr, endDateStr) | 根据时间查找所有销售单的基本信息             |



##### WarehouseService

| **WarehouseService模块提供的服务(供接口)**                  |                                                              |                                                              |
| ----------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `WarehouseService.productWarehousing`                       | 语法                                                         | `void productWarehousing(WarehouseInputFormVO warehouseInputFormVO);` |
| 前置条件                                                    | 无                                                           |                                                              |
| 后置条件                                                    | 商品入库                                                     |                                                              |
| `WarehouseService.productOutOfWarehouse`                    | 语法                                                         | `void productOutOfWarehouse(WarehouseOutputFormVO warehouseOutputFormListVO);` |
| 前置条件                                                    | 无                                                           |                                                              |
| 后置条件                                                    | 商品出库                                                     |                                                              |
| `WarehouseService.approvalInputSheet`                       | 语法                                                         | `void approvalInputSheet(UserVO user, String warehouseInputSheetId, WarehouseInputSheetState state);` |
| 前置条件                                                    | 用户、进货单Id、目标状态                                     |                                                              |
| 后置条件                                                    | 审批入库单                                                   |                                                              |
| `WarehouseService.getWareProductInfo`                       | 语法                                                         | `List<WarehouseOneProductInfoVO> getWareProductInfo(GetWareProductInfoParamsVO params);` |
| 前置条件                                                    | 商品id、批次和数量                                           |                                                              |
| 后置条件                                                    | 来从库存中取物品                                             |                                                              |
| `WarehouseService.getWareHouseInputSheetByState`            | 语法                                                         | `List<WarehouseInputSheetPO> getWareHouseInputSheetByState(WarehouseInputSheetState state);` |
| 前置条件                                                    | 通过状态                                                     |                                                              |
| 后置条件                                                    | 获取入库单                                                   |                                                              |
| `WarehouseService.getWareHouseOutSheetByState`              | 语法                                                         | `List<WarehouseOutputSheetPO> getWareHouseOutSheetByState(WarehouseOutputSheetState state);` |
| 前置条件                                                    | 状态                                                         |                                                              |
| 后置条件                                                    | 获取出库单                                                   |                                                              |
| `WarehouseService.approvalOutputSheet`                      | 语法                                                         | `void approvalOutputSheet(UserVO user, String sheetId, WarehouseOutputSheetState state);` |
| 前置条件                                                    | 无                                                           |                                                              |
| 后置条件                                                    | 审批出库单                                                   |                                                              |
| `WarehouseService.getWHISheetContentById`                   | 语法                                                         | `List<WarehouseInputSheetContentPO> getWHISheetContentById(String sheetId);` |
| 前置条件                                                    | 单据ID                                                       |                                                              |
| 后置条件                                                    | 获取具体内容                                                 |                                                              |
| `WarehouseService. getWarehouseIODetailByTime`              | 语法                                                         | `List<WarehouseIODetailPO> getWarehouseIODetailByTime(String beginDateStr,String endDateStr) ``**throws** ``ParseException;` |
| 前置条件                                                    | 库存查看                                                     |                                                              |
| 后置条件                                                    | 设定一个时间段，查看此时间段内的出/入库数量/金额/商品信息/分类信息 |                                                              |
| `WarehouseService. getWarehouseInputProductQuantityByTime`  | 语法                                                         | `**int** ``getWarehouseInputProductQuantityByTime(String beginDateStr,String endDateStr);` |
| 前置条件                                                    | 无                                                           |                                                              |
| 后置条件                                                    | 一个时间段内的入库数量合计                                   |                                                              |
| `WarehouseService. getWarehouseOutputProductQuantityByTime` | 语法                                                         | `int getWarehouseOutProductQuantityByTime(String beginDateStr,String endDateStr);` |
| 前置条件                                                    | 无                                                           |                                                              |
| 后置条件                                                    | 一个时间段内的出库数量合计                                   |                                                              |
| `WarehouseService.warehouseCounting`                        | 语法                                                         | `List<WarehouseCountingVO> warehouseCounting();`             |
| 前置条件                                                    | 无                                                           |                                                              |
| 后置条件                                                    | 库存盘点                                                     |                                                              |
| `WarehouseService.findOutputSheetById`                      | 语法                                                         | `WarehouseOutputSheetVO findOutputSheetById(String id);`     |
| 前置条件                                                    | 无                                                           |                                                              |
| 后置条件                                                    | 通过单据ID查找出库单                                         |                                                              |
| `WarehouseService.findInputSheetById`                       | 语法                                                         | `WarehouseInputSheetVO findInputSheetById(String id);`       |
| 前置条件                                                    | 无                                                           |                                                              |
| 后置条件                                                    | 通过单据查找入库单                                           |                                                              |

| **WarehouseService模块需要的服务(需接口)**        |                                        |
| ------------------------------------------------- | -------------------------------------- |
| 服务名                                            | 服务                                   |
| `WarehouseDao.findAllNotZeroByPidSortedByBatchId` | 通过商品ID按照批次获取库存信息         |
| `WarehouseDao.saveBatch`                          | 将商品存入库存                         |
| `WarehouseDao.findByPidOrderByPurchasePricePos`   | 通过商品ID按照购买价格排序获取库存信息 |
| `warehouseDao.deductQuantity`                     | 减去库存数量                           |
| `warehouseDao.findAll`                            | 查找所有库存信息                       |

##### CompanyAccountService

| **CompanyAccountService**提供的服务(供接口)    |                                                        |                                                              |
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

| **CompanyAccountService模块需要的服务(需接口)** |                      |
| ----------------------------------------------- | -------------------- |
| 服务名                                          | 服务                 |
| `CompanyAccountDao.createAccount`               | 创建账户             |
| `CompanyAccountDao.findAllCompanyAccounts`      | 查找所有账户信息     |
| `CompanyAccountDao.findCompanyAccountByName`    | 根据姓名查找账户信息 |
| `CompanyAccountDao.deleteCompanyAccountById`    | 根据Id删除账户       |
| `CompanyAccountDao.updateCompanyAccount`        | 根据账户信息更新账户 |



##### CollectionService

| **CollectionService**提供的服务(供接口)         |                                                    |                                                              |
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

| **CollectionService模块需要的服务(需接口)**                  |                      |
| ------------------------------------------------------------ | -------------------- |
| 服务名                                                       | 服务                 |
| `CollectionDao.findLatest()`                                 | 查找最新的收款单     |
| `CollectionDao.saveCollectionSheetSheet(CollectionSheetPO)`  | 保存收款单           |
| `CollectionDao.saveTransferList(List<TransferListSheetPO>)`  | 保存收款单内容列表   |
| `CollectionDao.findCollectionSheetById(String)`              | 根据Id查询收款单     |
| `CollectionDao.updateState(String id, BaseEnum state)`       | 更新id收款单状态     |
| `CollectionDao.findAllCollectionSheetContent(String collectionSheetId)` | 查询所有收款单的内容 |
| `CollectionDao.findAllCollectionSheet()`                     | 查询所有的收款单     |
| `CollectionDao.findAllCollectionSheetByState(` `CollectionSheetState state)` | 根据状态查询收款单   |



##### PaymentService

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



| **PaymentService模块需要的服务(需接口)**                     |                                  |
| ------------------------------------------------------------ | -------------------------------- |
| 服务名                                                       | 服务                             |
| paymentSheetDao.findLatest()                                 | 找到最近的一个付款单             |
| paymentSheetDao.savePaymentSheetContent(List<PaymentSheetContentPO> paymentSheetContentPOS ) | 保存付款单内容                   |
| paymentSheetDao.savePaymentSheet(PaymentSheetPO paymentSheetPO ) | 保存付款单                       |
| paymentSheetDao.findPaymentSheetById(String paymentSheetId)  | 通过Id查找付款单                 |
| paymentSheetDao.updateStateById(String paymentSheetId, PaymentSheetState state) | 更新付款单信息                   |
| paymentSheetDao.findAllPaymentSheetContentById(String paymentSheetId); | 通过付款单Id查找对应的付款单内容 |
| paymentSheetDao.findAllPaymentSheet()                        | 查找所有的付款单                 |
| paymentSheetDao.findAllPaymentSheetByState(PaymentSheetState  paymentSheetState) | 通过状态查找所有的付款单         |



##### SalaryService

| SalaryService提供的服务(供接口)          |                                            |                                                              |
| ---------------------------------------- | ------------------------------------------ | ------------------------------------------------------------ |
| SalaryService.queryAllJobs               | 语法                                       | List<JobPO> queryAllJobs();                                  |
| 前置条件                                 | 正在查询岗位信息                           |                                                              |
| 后置条件                                 | 返回所有的岗位信息                         |                                                              |
| SalaryService.updateJobById              | 语法                                       | void updateJobById(JobVO jobVO);                             |
| 前置条件                                 | 正在更新对应编号的岗位信息                 |                                                              |
| 后置条件                                 | 更新对应编号的岗位信息，将修改更新到数据库 |                                                              |
| SalaryService.generateSalarySheet        | 语法                                       | void generateSalarySheet(String beginDateStr, String endDateStr); |
| 前置条件                                 | 系统正在自动制定工资单                     |                                                              |
| 后置条件                                 | 将指定完成的工资单信息保存到数据库         |                                                              |
| SalaryService.getSalarySheetByTime       | 语法                                       | List<SalarySheetPO> getSalarySheetByTime(String beginTime, String endTime); |
| 前置条件                                 | 正在根据时间查询工资单                     |                                                              |
| 后置条件                                 | 查询对应时间的工资单，在工资单列表显示     |                                                              |
| SalaryService.getSalarySheetById         | 语法                                       | SalarySheetPO getSalarySheetById(Integer id);                |
| 前置条件                                 | 正在根据工资单编号查询工资单               |                                                              |
| 后置条件                                 | 查询对应编号的工资单，在工资单列表显示     |                                                              |
| SalaryService.getSalarySheetByState      | 语法                                       | List<SalarySheetPO> getSalarySheetByState(SalarySheetState state); |
| 前置条件                                 | 正在根据工资单状态查询工资单               |                                                              |
| 后置条件                                 | 查询对应状态的工资单，在工资单列表显示     |                                                              |
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

| **SalaryService模块需要的服务(需接口)**                      |                      |
| ------------------------------------------------------------ | -------------------- |
| 服务名                                                       | 服务                 |
| salarySheetDao.getLatestSheet()                              | 查找最新的工资单     |
| salarySheetDao.createSalarySheet(SalarySheetPO po)           | 创建工资单           |
| salarySheetDao.getSalarySheetByTime(String beginTime, String endTime); | 根据时间段查找工资单 |
| salarySheetDao.getAllSalarySheet();                          | 查找所有工资单       |
| salarySheetDao.getSalarySheetByState(SalarySheetState state); | 通过状态查找工资单   |

##### SheetService

| SheetService提供的服务(供接口) |                                        |                                                              |
| ------------------------------ | -------------------------------------- | ------------------------------------------------------------ |
| SheetService.findAllSheet      | 语法                                   | List<SheetVO> findAllSheet(String beginDateStr, String endDateStr); |
| 前置条件                       | 正在查询经营历程表                     |                                                              |
| 后置条件                       | 查询出系统中所有的单据，返回单据的集合 |                                                              |

| **SheetService模块需要的服务(需接口)**                       |                        |
| ------------------------------------------------------------ | ---------------------- |
| 服务名                                                       | 服务                   |
| saleSheetDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找销售单基本信息     |
| saleReturnsSheetDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找销售退货单基本信息 |
| purchaseSheetDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找进货单基本信息     |
| purchaseReturnsSheetDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找进货退货单基本信息 |
| paymentSheetDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找付款单基本信息     |
| collectionDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找收款单基本信息     |
| salarySheetDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找工资单基本信息     |
| warehouseInputSheetDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找入库单基本信息     |
| warehouseOutputSheetDao.findAllBasicSheetInfo(String beginDateStr, String endDateStr) | 查找出库单基本信息     |

##### FinanceService

| FinanceService提供的服务(供接口)  |                                          |                                                              |
| --------------------------------- | ---------------------------------------- | ------------------------------------------------------------ |
| FinanceService.getFinancialReport | 语法                                     | FinanceVO getFinancialReport(String beginDateStr, String endDateStr); |
| 前置条件                          | 正在查询经营情况表                       |                                                              |
| 后置条件                          | 查询所有的经营情况，在经营情况表列表显示 |                                                              |

| **FinanceService模块需要的服务(需接口)**                   |                              |
| ---------------------------------------------------------- | ---------------------------- |
| 服务名                                                     | 服务                         |
| saleSheetDao.findAllSheet(SaleSheetQuery query)            | 查找所有审批通过的销售单     |
| saleReturnsSheetDao.findAllSheet(SaleSheetQuery query)     | 查找所有审批通过的销售退货单 |
| purchaseSheetDao.findAllSheet(SaleSheetQuery query)        | 查找所有审批通过的进货单     |
| purchaseReturnsSheetDao.findAllSheet(SaleSheetQuery query) | 查找所有审批通过的进货退货单 |
| salarySheetDao.find(SaleSheetQuery query)                  | 查找所有审批通过的工资单     |

##### InitAccountService

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

| InitAccountService模块需要的服务(需接口)           |                      |
| -------------------------------------------------- | -------------------- |
| 服务名                                             | 服务                 |
| initAccountDao.createCustomer(initCustomerPO)      | 期初创建客户         |
| initAccountDao.createAccount(initCompanyAccountPO) | 期初创建公司账户     |
| initAccountDao.createProduct(savePO)               | 期初建立商品         |
| initAccountDao.getAllInitCustomer()                | 查找所有的初始客户   |
| initAccountDao.getAllInitProduct()                 | 查找所有的初始商品   |
| initAccountDao.getAllInitCompanyAccount()          | 查找所有初始公司账户 |

##### JobService

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

| JobService模块需要的服务(需接口)          |                      |
| ----------------------------------------- | -------------------- |
| 服务名                                    | 服务                 |
| jobDao.createDepartmentSalaryRule(jobPO); | 创建部门薪酬规则     |
| jobDao.queryAllJobs()                     | 查找所有部门         |
| initAccountDao.createProduct(savePO)      | 期初建立商品         |
| initAccountDao.getAllInitCustomer()       | 查找所有的初始客户   |
| initAccountDao.getAllInitProduct()        | 查找所有的初始商品   |
| initAccountDao.getAllInitCompanyAccount() | 查找所有初始公司账户 |





### 5.4 数据层的分解@韩陈旭 @邓尤亮 @万沛沛 @华广松 

数据层主要给业务逻辑层提供数据访问服务，包括对于持久化数据的增、删、改、查。在企业ERP系统中，持久化数据的保存使用的是数据库。

#### 5.4.1 数据层模块的职责

| 模块                    | 职责                                                         | 分工(写接口) |
| ----------------------- | ------------------------------------------------------------ | ------------ |
| logindataservice        | 提供登陆服务                                                 |              |
| categorydataservice     | 提供商品分类数据集体载入、集体保存、增、删、改、查服务       | ✔            |
| commoditydataservice    | 提供商品数据集体载入、集体保存、增、删、改、查服务           | ✔            |
| clientdataservice       | 提供客户数据集体载入、集体保存、增、删、改、查服务           | @华广松  ✔   |
| purchasedataservice     | 提供进货(退货)单数据集体载入、集体保存、增、删、改、查服务   | @万沛沛 ✔    |
| saledataservice         | 提供销售（退货）单数据集体载入、集体保存、增、删、改、查服务 | @万沛沛 ✔    |
| regulationsdataservice  | 提供制度信息数据集体载入、集体保存、增、删、改、查服务       |              |
| logdataservice          | 提供日志数据集体载入、集体保存、增、删、改、查服务           |              |
| accountdataservice      | 提供公司银行账户数据集体载入、集体保存、增、删、改、查服务   |              |
| financedataservice      | 提供财务单据数据集体载入、集体保存、增、删、改、查服务       |              |
| initialdataservice      | 提供期初建账数据集体载入、集体保存、增、删、改、查服务       |              |
| employeedataservice     | 提供员工数据集体载入、集体保存、增、删、改、查服务           |              |
| attendancedataservice   | 提供考勤数据集体载入、集体保存、增、删、改、查服务           |              |
| bonusdataservice        | 提供年终奖数据集体载入、集体保存、增、删、改、查服务         |              |
| promotiondataservice    | 提供促销策略数据集体载入、集体保存、增、删、改、查服务       | @邓尤亮      |
| approvaldataservice     | 提供审批数据数据集体载入、集体保存、增、删、改、查服务       | @华广松  ✔   |
| userdataservice         | 提供系统用户数据集体载入、集体保存、增、删、改、查服务       |              |
| WarehouseDao            | 提供库存的增删改查服务                                       | @邓尤亮      |
| WarehouseInputSheetDao  | 提供入库单的增删改查服务                                     |              |
| WarehouseOutputSheetDao | 提供出库单的增删改查服务                                     |              |

#### 5.4.2 数据层模块的接口规范

##### CategoryDao

| **categorydata模块提供的服务(供接口)** |                                            |                                                              |
| -------------------------------------- | ------------------------------------------ | ------------------------------------------------------------ |
| `CategoryDataService.createCategory`   | 语法                                       | `int createCategory``*(*``CategoryPO categoryPO``*);*`       |
| 前置条件                               | 无                                         |                                                              |
| 后置条件                               | 在数据库中增加一个商品分类`categoryPO`记录 |                                                              |
| `CategoryDataService.findByCategoryId` | 语法                                       | `CategoryPO findByCategoryId``*(*``Integer categoryId``*);*` |
| 前置条件                               | 无                                         |                                                              |
| 后置条件                               | 按照ID进行查找返回相应的`CategoryPO`       |                                                              |
| `CategoryDataService.updateById`       | 语法                                       | `int updateById``*(*``CategoryPO categoryPO``*);*`           |
| 前置条件                               | 在数据库中存在同样ID的`categoryPO`         |                                                              |
| 后置条件                               | 更新一个`categoryPO`                       |                                                              |
| `CategoryDataService.deleteById`       | 语法                                       | `int deleteById``*(*``Integer id``*);*`                      |
| 前置条件                               | 在数据库中存在同样ID的`categoryPO`         |                                                              |
| 后置条件                               | 删除一个`categoryPO`                       |                                                              |
| `CategoryDataService.findAll`          | 语法                                       | `List``*<*``CategoryPO``*>* ``findAll``*()*``;`              |
| 前置条件                               | 无                                         |                                                              |
| 后置条件                               | 返回所有`CategoryPO`的列表                 |                                                              |

| **commoditydata模块提供的服务(供接口)** |                                     |                                                              |
| --------------------------------------- | ----------------------------------- | ------------------------------------------------------------ |
| `CommodityDataService.createProduct`    | 语法                                | `int createProduct``***(***``**ProductPO** ``productPO``***)***``;` |
| 前置条件                                | 无                                  |                                                              |
| 后置条件                                | 在数据库中增加一个`productPO`记录   |                                                              |
| `CommodityDataService.updateById`       | 语法                                | `int updateById``***(***``**ProductPO** ``productPO``***)***``;` |
| 前置条件                                | 在数据库中存在同样ID的`productPO`   |                                                              |
| 后置条件                                | 更新一个`productPO`                 |                                                              |
| `CommodityDataService.findById`         | 语法                                | `**ProductPO** ``findById``***(***``**String** ``id``***)***``;` |
| 前置条件                                | 在数据库中存在同样ID的`productPO`   |                                                              |
| 后置条件                                | 按照ID进行查找返回相应的`productPO` |                                                              |
| `CommodityDataService.findAll`          | 语法                                | `**List**``***<***``**ProductPO**``***>*** ``findAll``***()***``;` |
| 前置条件                                | 无                                  |                                                              |
| 后置条件                                | 返回所有`productPO`的列表           |                                                              |
| `CommodityDataService.deleteById`       | 语法                                | `int deleteById``***(***``**String** ``id``***)***``;`       |
| 前置条件                                | 在数据库中存在同样ID的`productPO`   |                                                              |
| 后置条件                                | 删除一个`productPO`                 |                                                              |

##### ClientDao

| **clientdata模块提供的服务（供接口）** |                            |                                             |
| -------------------------------------- | -------------------------- | ------------------------------------------- |
| `ClientDataService.find`               | 语法                       | `public ClientPO find(String clientID)`     |
| 前置条件                               | 无                         |                                             |
| 后置条件                               | 根据ID进行查找客户         |                                             |
| `ClientDataService.finds`              | 语法                       | `public ClientPO find(ClientQueryPO query)` |
| 前置条件                               | 无                         |                                             |
| 后置条件                               | 根据筛选条件进行查找客户   |                                             |
| `ClientDataService.insert`             | 语法                       | `public void insert(ClientPO po)`           |
| 前置条件                               | 无                         |                                             |
| 后置条件                               | 添加新客户，返回提示信息   |                                             |
| `ClientDataService.delete`             | 语法                       | `public void delete(String clientID)`       |
| 前置条件                               | 无                         |                                             |
| 后置条件                               | 删除客户，返回提示信息     |                                             |
| `ClientDataService.update`             | 语法                       | `public void update(ClientPO po)`           |
| 前置条件                               | 无                         |                                             |
| 后置条件                               | 更新客户信息，返回提示信息 |                                             |

##### PruchaseDao

| **purchasedata模块提供的服务（供接口）** |                          |                                                  |
| ---------------------------------------- | ------------------------ | ------------------------------------------------ |
| `PurchaseDataService.findAllTradeBill`   | 语法                     | `List<PurchaseTradeBillPO> findAllTradeBill()`   |
| 前置条件                                 | 无                       |                                                  |
| 后置条件                                 | 获取进货单列表           |                                                  |
| `PurchaseDataService.insert`             | 语法                     | `void insert(PurchaseTradeBillPO po)`            |
| 前置条件                                 | 制定单据完成             |                                                  |
| 后置条件                                 | 添加持久化进货单数据     |                                                  |
| `PurchaseDataService.update`             | 语法                     | `void update(PurchaseTradeBillPO po)`            |
| 前置条件                                 | 修改单据完成             |                                                  |
| 后置条件                                 | 更新持久化进货单数据     |                                                  |
| `PurchaseDataService.findAllRefundBill`  | 语法                     | `List<PurchaseRefundBillPO> findAllRefundBill()` |
| 前置条件                                 | 无                       |                                                  |
| 后置条件                                 | 获取进货退货单列表       |                                                  |
| `PurchaseDataService.insert`             | 语法                     | `void insert(PurchaseRefundBillPO po)`           |
| 前置条件                                 | 制定单据完成             |                                                  |
| 后置条件                                 | 添加持久化进货退货单数据 |                                                  |
| `PurchaseDataService.update`             | 语法                     | `void update(PurchaseRefundBillPO po)`           |
| 前置条件                                 | 修改单据完成             |                                                  |
| 后置条件                                 | 更新持久化进货退货单数据 |                                                  |

##### SaleDao

| **saledata模块提供的服务（供接口）** |                          |                                              |
| ------------------------------------ | ------------------------ | -------------------------------------------- |
| `SaleDataService.findAllTradeBill`   | 语法                     | `List<SaleTradeBillPO> findAllTradeBill()`   |
| 前置条件                             | 无                       |                                              |
| 后置条件                             | 获取销售单列表           |                                              |
| `SaleDataService.insert`             | 语法                     | `void insert(SaleTradeBillPO po)`            |
| 前置条件                             | 制定单据完成             |                                              |
| 后置条件                             | 添加持久化销售单数据     |                                              |
| `SaleDataService.update`             | 语法                     | `void update(SaleTradeBillPO po)`            |
| 前置条件                             | 修改单据完成             |                                              |
| 后置条件                             | 更新持久化销售单数据     |                                              |
| `SaleDataService.findAllRefundBill`  | 语法                     | `List<SaleRefundBillPO> findAllRefundBill()` |
| 前置条件                             | 无                       |                                              |
| 后置条件                             | 获取销售退货单列表       |                                              |
| `SaleDataService.insert`             | 语法                     | `void insert(SaleRefundBillPO po)`           |
| 前置条件                             | 制定单据完成             |                                              |
| 后置条件                             | 添加持久化销售退货单数据 |                                              |
| `SaleDataService.update`             | 语法                     | `void update(SaleRefundBillPO po)`           |
| 前置条件                             | 修改单据完成             |                                              |
| 后置条件                             | 更新持久化销售退货单数据 |                                              |

##### WarehouseDao

| **inventorydata模块提供的服务(供接口)**           |                                              |                                                              |
| ------------------------------------------------- | -------------------------------------------- | ------------------------------------------------------------ |
| `WarehouseDao.saveBatch`                          | 语法                                         | `void saveBatch(List<WarehousePO> warehousePOList) `         |
| 前置条件                                          | 无                                           |                                                              |
| 后置条件                                          | 存入库存                                     |                                                              |
| `WarehouseDao.deductQuantity`                     | 语法                                         | `void deductQuantity(WarehousePO warehousePO);`              |
| 前置条件                                          | 无                                           |                                                              |
| 后置条件                                          | 减去库存商品数量                             |                                                              |
| `WarehouseDao.addQuantity`                        | 语法                                         | `void addQuantity(WarehousePO warehousePO);`                 |
| 前置条件                                          | 无                                           |                                                              |
| 后置条件                                          | 增加库存商品数量                             |                                                              |
| `WarehouseDao.findAllNotZeroByPidSortedByBatchId` | 语法                                         | `List<WarehousePO> findAllNotZeroByPidSortedByBatchId(String pid);` |
| 前置条件                                          | 无                                           |                                                              |
| 后置条件                                          | 根据商品 id 获取商品信息并按照批次排序       |                                                              |
| `WarehouseDao.findByPidOrderByPurchasePricePos`   | 语法                                         | `List<WarehousePO> findByPidOrderByPurchasePricePos(String pid);` |
| 前置条件                                          | 无                                           |                                                              |
| 后置条件                                          | 按照商品id获取现存商品（存量>0）并按价格排序 |                                                              |
| `WarehouseDao.findOneByPidAndBatchId`             | 语法                                         | `WarehousePO findOneByPidAndBatchId(String pid, Integer batchId);` |
| 前置条件                                          | 无                                           |                                                              |
| 后置条件                                          | 按照商品id和批次获取现存商品                 |                                                              |
| `WarehouseDao.findAll`                            | 语法                                         | `List<WarehousePO> findAll()`                                |
| 前置条件                                          | 无                                           |                                                              |
| 后置条件                                          | 查看所有库存                                 |                                                              |

##### CompanyAccountDao

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



##### CollectionDao

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



##### PaymentSheetDao

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

##### SalarySheetDao

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

##### InitAccountDao

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

##### EmployeeDao

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

##### SalaryDao

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

##### JobDao

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



## 6.信息视角@华广松 

### 6.1 数据持久化对象

系统的PO类就是对应的相关的实体类，如下所示: 

- CategoryPO商品分类类：编号、名称、父分类、子分类、商品列表。
- ProductPO商品类：包括编号、名称、型号、数量、进价、零售价、最近进价、最近零售价、报警数量、备注。
- InventoryAlarmBillPO库存报警单类：包括时间、单据ID、状态、仓库、客户、报警商品列表（包括商品编号、数量）。
- InventoryOverLossBillPO库存溢损单类：包括时间、单据ID、状态、仓库、操作员、溢损商品列表（包括商品编号、实际数量）。
- InventoryGiftBillPO库存报警单类：包括时间、单据ID、状态、仓库、客户、赠品列表（包括商品编号、数量）。
- CustomerPO客户类：包括编号、类别（进货商、销售商）、等级（五级，一级普通用户，五级VIP客户）、姓名、电话、地址、邮编、电子邮箱、应收额度、应收、应付、默认业务员。
- PurchaseSheetPO进货单类：包括时间、状态、单据编号（JHD-yyyyMMdd-xxxxx），客户，仓库，操作员，商品列表，备注，总额。
- PurchaseReturnSheetPO进货退货单类：包括时间、状态、单据编号（JHD-yyyyMMdd-xxxxx），客户，仓库，操作员，商品列表，备注，总额。
- SaleSheetPO销售单类：包括时间、状态、单据编号（XSD-yyyyMMdd-xxxxx），客户，业务员，操作员（当前登录系统的用户），仓库，商品列表，折让前总额，折让，使用代金卷金额，折让后总额，备注。
- SaleReturnSheetPO销售单类：包括时间、状态、单据编号（XSTHD-yyyyMMdd-xxxxx），客户，业务员，操作员（当前登录系统的用户），仓库，商品列表，总额，备注。
- CompanyAccountPO账户类：包括银行账户、账户名称、金额、可见性。
- PaymentSheetPO收款单类：包括时间、状态，单据编号（SKD-yyyyMMdd-xxxxx），客户（同时包含供应商和销售商），操作员（当前登录用户），转账列表，总额，备注。
- CashBillPO现金费用单类：包括时间、状态、单据编号、操作员、备注、条目清单（包括条目名和金额）、总额。
- InitialPO期初建账类：包括年份、商品信息（商品分类，某一商品的名称，类别，型号，进价/售价(默认为上年平均)，最近进价和最近售价留空），客户信息（客户分类，某一个客户的名称，联系方式等，应收应付(之前遗留)），银行账户信息（名称，余额）。
- PromotionPO客户促销策略类：包括编号、类型、起始时间、结束时间、客户等级、代金卷、折让金额、赠品列表（编号、数量）。
- PromotionGoodsPO组合包促销策略类：包括编号、类型、起始时间、结束时间、组合商品列表（编号、数量）、总价。
- PromotionTotalPO总价促销策略类：包括编号、类型、起始时间、结束时间、总价、代金卷、赠品列表（编号、数量）。
- EmployeePO员工类：包括员工编号、姓名、性别、生日、手机号码、工作岗位、岗位级别、工资卡账户。
- JobPO岗位薪资规则类：包括岗位编号、岗位名称、基本工资、薪资计算方法、岗位工资、年终奖、提成率、岗位级别加薪率、失业保险金、住房公积金。
- UserPO用户类：包括用户名、密码、身份属性。
- LogPO日志类：包括执行者列表，行为列表，时间列表
- MessagePO系统信息类：包括发送者、接收者、信息列表

### 6.2 数据库表

用MySQL数据库保存，导出文件格式为*.sql。