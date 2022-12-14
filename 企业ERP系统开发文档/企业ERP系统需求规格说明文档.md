# 企业ERP系统需求规格说明文档

## 小组分工

| 姓名   | 学号      | 分工                                                         |
| ------ | --------- | ------------------------------------------------------------ |
| 韩陈旭 | 201250037 | 管理用户、制定进货单、制定进货退货单、制定付款单、制定收款单、期初建账功能需求描述 |
| 万沛沛 | 201250038 | 制定销售单、制定销售退货单、登记员工信息、管理员工打卡功能需求描述 |
| 邓尤亮 | 201250035 | 查询成交金额最大客户、查看销售明细表、查看经营情况表、查看经营历程表功能需求描述 |
| 华广松 | 201840309 | 查询日志、账户管理、制定促销规则功能需求描述                 |

## 文档修改历史

| 修改日期  | 修改人员                       | 修改原因                                                     | 版本号 |
| --------- | ------------------------------ | ------------------------------------------------------------ | ------ |
| 2022.4.12 | 韩陈旭、万沛沛、邓尤亮、华广松 | 创建企业ERP系统需求规格说明文档                              | v.1.0  |
| 2022.4.12 | 韩陈旭                         | 添加管理用户、制定进货单、制定进货退货单功能需求描述         | v.1.1  |
| 2022.4.13 | 万沛沛                         | 添加制定销售单、制定销售退货单功能需求描述                   | v.1.2  |
| 2022.4.14 | 邓尤亮                         | 添加查询成交金额最大客户功能需求描述                         | v.1.3  |
| 2022.4.15 | 华广松                         | 添加查询日志功能需求描述                                     | v.1.4  |
| 2022.7.5  | 韩陈旭                         | 添加制定付款单、制定收款单、期初建账功能需求描述             | v.2.0  |
| 2022.7.6  | 邓尤亮                         | 添加查看销售明细表、查看经营情况表、查看经营历程表功能需求描述 | v.2.1  |
| 2022.7.7  | 万沛沛                         | 添加登记员工信息、管理员工打卡功能需求描述                   | v.2.2  |
| 2022.7.8  | 华广松                         | 添加账户管理、制定促销规则功能需求描述                       | v.2.3  |
| 2022.7.9  | 韩陈旭                         | 添加制定促销规则功能需求描述                                 | v3.0   |

## 0.目录

[TOC]

## 1.引言

### 1.1 目的

本文档描述了企业ERP系统销售模块的功能需求和非功能需求。开发小组的软件系统实现和验证工作都以此文档为依据。

除特殊说明外，本文档所包含的需求都是高优先级需求。

### 1.2 范围

- 产品名称：企业ERP系统
- 预期功能：
  - 库存管理
  - 销售管理
  - 财务管理
  - 人事管理
  - 企业经营管理
- 应用：企业ERP系统是为某公司开发的业务系统，开发的目标是帮助该公司处理日常的重点业务，包括库存管理、销售管理、财务管理、人事管理和企业经营管理。系统上线运行6个月后，能够减少积压的库存，增加销售额，提高财务人员和人力资源人员工作效率，为经理的决策做支持。

### 1.3 参考文献

[1] IEEE标准。

[2] 骆斌，丁二玉，刘钦. 软件工程与计算.卷二,软件开发的技术基础.volume Ⅱ, Fundamentals of software development technology[M]. 机械工业出版社, 2012.

[3] 企业ERP系统用例文档 V1.0.

## 2.总体描述

### 2.1 项目前景

#### 2.1.1 背景与机遇

某公司规模扩大，企业业务量、办公场所、员工数都发生增长。

该企业为了适应新的环境，提高工作效率和用户满意度，聘请南鲸软件技术公司开发一套企业ERP系统。

#### 2.1.2 业务需求

系统上线运行6个月之后，能够

- 减少积压的库存
- 增加销售额
- 提高财务人员和人力资源人员工作效率
- 为经理的决策做支持

### 2.2 项目功能

#### 2.2.1 库存管理人员

- 商品管理
- 库存管理

#### 2.2.2 进货销售人员

- 客户管理
- 销售管理

#### 2.2.3 财务人员

- 账户管理
- 财务管理

#### 2.2.4 人力资源人员

- 人事管理

#### 2.2.5 总经理

- 企业经营管理

### 2.3 用户特征

| 库存管理人员 | 1到2名。负责对商品分类和商品的信息的管理。能够简单使用办公信息化系统。对新系统持支持态度，不希望增加现有的工作量。 |
| ------------ | ------------------------------------------------------------ |
| 进货销售人员 | 4到8名。负责完成进货单和销售单，对新系统基本持积极态度，不希望增加现有工作量。 |
| 财务人员     | 1到2名。办公信息化系统较强。对新系统持支持态度，不希望增加现有的工作量。 |
| 人力资源人员 | 1到2名。办公信息化系统较强。对新系统持支持态度，不希望增加现有的工作量。 |
| 总经理       | 2名。能够熟练使用办公信息化系统。对新系统持支持态度。        |

### 2.4 约束

- 开发语言：Java + CSS + JavaScript + HTML + MySQL

- 开发框架：SpringBoot + MyBatis + Vue
- 运行界面：系统将使用Web界面。
- 硬件需求：支持Windows 97以上的操作系统。
- 开发方式：项目使用持续集成方式进行开发。
- 审计功能：开发项目时，需定期提交需求规格文档与用例文档。

### 2.5 假设与依赖

- 系统需提供新建账户功能，以保证用户的使用权限。

## 3.详细需求描述

### 3.1 对外接口需求

#### 3.1.1 用户界面

用户使用图形化界面进行操作。

#### 3.1.2 硬件接口

无特殊需求

#### 3.1.3 软件接口

数据库：MySQL

操作系统：无特殊需求

工具：无特殊需求

程序库：暂无

集成的商业组件：暂无

#### 3.1.4 通信接口

无特殊需求

### 3.2 功能需求

#### 3.2.1 管理客户

##### 3.2.1.1 特征描述

经过系统认证和授权的销售人员可以对客户进行增删改查操作，包括增加客户、删除客户、修改客户属性和查询客户。

客户的应收应付属性不可以被修改；应收额度属性只可以被总经理修改；查询客户信息可以通过输入关键字、客户编号等进行模糊查找。

##### 3.2.1.2 刺激/响应序列

刺激：销售人员点击“客户管理”按钮。

响应：系统显示所有已经存储的客户的信息。

刺激：销售人员在搜索框中输入关键字(姓名、电话等)、客户编号等信息，点击查询按钮。

响应：系统显示查询得到的客户的信息。

刺激：销售人员点击所有客户信息模表格右上角的“+”号，填写客户信息表单，点击创建按钮。

响应：系统在页面上显示添加成功，并且在1s之内在所有用户信息表格中显示新添加的客户信息。

刺激：销售人员点击对应客户信息条目后的“删除”按钮。

响应：系统弹出提示"你确认要删除这条客户信息吗?"。

刺激：销售人员点击“确认”。

响应：系统删除对应的客户信息，1s内用户信息表格中的对应信息别删除。

刺激：销售人员点击“取消”。

响应：系统取消删除操作。

刺激：销售人员点击客户信息后的“修改”按钮，更改原来的客户信息表单，点击"提交"按钮。

响应：系统更新对应客户的信息，1s之内客户信息表格中对应客户的信息发生变更。

刺激：总经理点击点击"修改应收额度"按钮，修改对应用户的应收额度。

响应：系统更改对应客户的应收额度信息，1s之内客户信息表格中对应客户的应收额度信息发生变更。

刺激：销售人员关闭客户管理页面。

响应：系统退出客户管理页面，回到主页面。

##### 3.2.1.3 相关功能需求

| Sale.CustomerManagement.Input         | 系统应该允许销售人员在管理客户时进行键盘输入                 |
| ------------------------------------- | ------------------------------------------------------------ |
| Sale.CustomerManagement.Input.Search  | 当销售人员在客户信息搜索框中输入关键字、客户编号时，系统要展示对应的用户信息，参见Sale.Customer.Display |
| Sale.CustomerManagement.Input.Add     | 销售人员填写完需要创建的客户信息表单，点击“创建”按钮，系统执行增加客户操作， 参见Sale.Customer.Add |
| Sale.CustomerManagement.Input.Update  | 销售人员填写完需要更新的客户信息表单，点击“更新”按钮，系统执行更新用户属性操作， 参见Sale.Customer.Update |
| Sale.CustomerManagement.Input.Del     | 销售人员删除已经填写还未提交的信息，系统继续等待用户输入     |
| Sale.CustomerManagement.Input.Cancel  | 当销售人员点击“取消”按钮的时候，系统关闭当前的客户管理任务，返回主界面 |
| Sale.CustomerManagement.Input.InValid | 销售人员在管理客户时的输入不合法或者无效时，系统要显示输入无效，参见Sale.Customer.Invalid |
| Sale.Customer.Display                 | 当销售人员进行了合法的增删改查之后，系统应该显示指定/全部客户的全部信息 |
| Sale.Customer.Add                     | 在销售人员填写完客户信息表单并且点击“创建”按钮时，系统要检查并添加对应的客户信息，显示是否添加成功，如果添加成功，将添加完的结果在页面显示 |
| Sale.Customer.Delete                  | 在销售人员点击“删除"按钮时，系统应该删除对应的客户信息，并显示是否删除成功 |
| Sale.Customer.Update                  | 1.在销售人员点击“更新”按钮时，系统应该检查并更新对应的用户信息，显示是否更新成功，如果成功，将更新的结果在页面显示； 2.总经理点击“更新应收额度”按钮时，系统应该检查并更新对应用户信息的“应收额度”属性，显示是否更新成功，如果成功，将更新的结果在页面显示； |
| Sale.Customer.Invalid                 | 当销售人员在查询、修改、增加客户信息时的输入不合法时，系统应该发出警报，提示并等待销售人员进行合法的输入 |

#### 3.2.2 制定进货单

##### 3.2.2.1 特征描述

经过系统认证和授权的进货销售人员可以制定和生成一个进货单，用于处理进货情况。进货单需要销售经理的一级审批和总经理的二级审批。

##### 3.2.2.2 刺激/响应序列

刺激：进货销售人员进入制定进货单的页面。

响应：系统显示进货单的输入界面。

刺激：进货销售人员输入供应商，仓库，备注，商品名称，数量，单价，金额，商品备注，确认并提交。

响应：系统生成进货单，并向销售经理发出审批请求。

刺激：销售经理审批通过。

响应：系统记录审批人，并向总经理发出审批请求。

刺激：销售经理驳回。

响应：系统记录审批人，将进货单标记为驳回状态。

刺激：总经理审批通过。

响应：系统记录审批人，锁定进货单，更新数据库中的库存数据和客户的应收应付数据。

刺激：总经理驳回。

响应：系统记录审批人，将进货单标记为驳回状态。

##### 3.2.2.3 相关功能需求

| Sale.Stock.Replenish               | 系统显示制定进货单的页面                                     |
| ---------------------------------- | ------------------------------------------------------------ |
| Sale.Stock.Replenish.Input         | 系统允许进货销售人员在制定进货单时进行输入                   |
| Sale.Stock.Replenish.Input.Del     | 系统允许进货销售人员删除已输入的进货单信息                   |
| Sale.Stock.Replenish.Input.Invalid | 进货销售人员输入无效信息时，系统拒绝接受并提示信息无效       |
| Sale.Stock.Replenish.Submit        | 进货销售人员完成进货单的填写，系统生成此进货单并进入提交状态 |
| Sale.Stock.Replenish.Audit         | 系统应该允许销售经理和总经理审批进货单，参见Sale.Stock.Audit |
| Sale.Stock.Audit                   | 系统应该允许销售经理和总经理审批单据                         |
| Sale.Stock.Audit.Approve           | 单据被审批通过后，系统将单据修改为审批通过状态               |
| Sale.Stock.Audit.Reject            | 单据审批不通过，系统将单据修改为草稿状态                     |
| Sale.Stock.Replenish.Update        | 在进货单审批全部通过后，系统更新重要数据，参见Sale.Stock.Update |
| Sale.Stock.Update                  | 系统更新库存相关数据                                         |
| Sale.Stock.Update.Inventory        | 系统更改库存数据                                             |
| Sale.Stock.Update.Customer         | 系统更改客户的应收应付数据                                   |
| Sale.Stock.Replenish.End           | 在进货单审批全部通过后，系统锁定进货单，结束进货单任务       |
| Sale.Stock.Replenish.Close         | 销售人员退出创建进货单的界面                                 |

#### 3.2.3 制定进货退货单

##### 3.2.3.1 特征描述

经过系统认证和授权的进货销售人员可以制定和生成一个进货退货单，用于处理进货退货情况。进货退货单需要销售经理的一级审批和总经理的二级审批。

##### 3.2.3.2 刺激/响应序列

刺激：进货销售人员进入制定进货退货单的页面。

响应：系统显示进货退货单的输入界面。

刺激：进货销售人员输入供应商，仓库，备注，商品名称，数量，单价，金额，商品备注，确认并提交。

响应：系统生成进货退货单，并向销售经理发出审批请求。

刺激：销售经理审批通过。

响应：系统记录审批人，并向总经理发出审批请求。

刺激：销售经理驳回。

响应：系统记录审批人，将进货退货单标记为驳回状态。

刺激：总经理审批通过。

响应：系统记录审批人，锁定进货退货单，更新数据库中的库存数据和客户的应收应付数据。

刺激：总经理驳回。

响应：系统记录审批人，将进货退货单标记为驳回状态。

##### 3.2.3.3 相关功能需求

| Sale.Stock.Return               | 系统显示制定进货退货单的页面                                 |
| ------------------------------- | ------------------------------------------------------------ |
| Sale.Stock.Return.Input         | 系统允许进货销售人员在制定进货退货单时进行输入               |
| Sale.Stock.Return.Input.Del     | 系统允许进货销售人员删除已输入的销售退货单信息               |
| Sale.Stock.Return.Input.Invalid | 进货销售人员输入无效信息时，系统拒绝接受并提示信息无效       |
| Sale.Stock.Return.Submit        | 进货销售人员完成进货退货单的填写，系统生成此进货退货单并进入提交状态 |
| Sale.Stock.Return.Audit         | 系统应该允许销售经理和总经理审批进货退货单，参见Sale.Stock.Audit |
| Sale.Stock.Return.Update        | 在进货退货单审批全部通过后，系统更新重要数据，参见Sale.Stock.Update |
| Sale.Stock.Return.End           | 在进货退货单审批全部通过后，系统锁定进货退货单，结束进货退货单任务 |
| Sale.Stock.Return.Close         | 销售人员退出创建进货退货单的界面                             |

#### 3.2.4 制定销售单

##### 3.2.4.1 特征描述

经过系统认证和授权的进货销售人员可以制定和生成一个销售单用于处理将要被销售的商品。销售单需要销售经理的一级审批和总经理的二级审批。

##### 3.2.4.2 刺激/响应序列

刺激： 销售人员点击“生成销售单”按钮

响应： 系统显示销售单的输入表单。

刺激： 销售人员输入销售单的详细信息

响应： 系统生成销售单，并向销售经理发出审批请求

刺激： 销售经理审批通过

响应： 系统向总经理发出审批请求

刺激： 总经理审批通过

响应： 系统更新库存数据和客户的应收应付数据

刺激： 销售经理审批不通过

响应： 系统记录审批人，将销售单标记为驳回状态

刺激： 总经理审不通过

响应： 系统记录审批人，将销售单标记为驳回状态

##### 3.2.4.3 相关功能需求

| Sale.SellOrder.Create           | 系统允许销售人员创建新的销售单，销售单进入草稿状态           |
| ------------------------------- | ------------------------------------------------------------ |
| Sale.SellOrder.Input            | 系统允许销售人员在制定销售单时进行输入                       |
| Sale.SellOrder.Input.Del        | 系统允许销售人员删除已输入的销售单信息                       |
| Sale.SellOrder.Input.Invalid    | 销售人员输入无效信息时，系统拒绝接受并提示信息无效           |
| Sale.SellOrder.Complete         | 销售人员完成销售单的填写，系统生成此销售单，销售单进入待审核状态 |
| Sale.SellOrder.Approve          | 销售单被审批通过后，系统将销售单修改为审批通过状态           |
| Sale.SellOrder.Reject           | 销售单审批不通过，系统将销售单修改为驳回状态                 |
| Sale.SellOrder.Update           | 系统更新重要数据                                             |
| Sale.SellOrder.Update.Inventory | 系统更改库存数据                                             |
| Sale.SellOrder.Update.Customer  | 系统更改客户的应收应付数据                                   |
| Sale.SellOrder.End              | 系统允许销售人员结束创建销售单                               |
| Sale.SellOrder.End.Timeout      | 若长时间未收到销售人员指定销售单的操作，系统取消该销售单的生成 |
| Sale.SellOrder.Close            | 销售人员退出创建销售单的界面                                 |

#### 3.2.5 制定销售退货单

##### 3.2.5.1 特征描述

经过系统认证和授权的进货销售人员可以制定和生成一个销售退货单用于处理已经销售的商品被退货的情况。销售退货单需要销售经理的一级审批和总经理的二级审批。

##### 3.2.5.2 刺激/响应序列

刺激：销售人员进入制定销售退货单的页面

响应：系统显示销售退货单的输入界面

刺激：销售人员输入销售退货单的信息

响应：系统生成销售退货单，并向销售经理发出审批请求

刺激：销售经理审批通过

响应：系统向总经理发出审批请求

刺激：总经理审批通过

响应：系统更新数据库中的库存数据和客户的应收应付数据

刺激： 销售经理审批不通过

响应： 系统记录审批人，将销售退货单标记为驳回状态

刺激： 总经理审不通过

响应： 系统记录审批人，将销售退货单标记为驳回状态

##### 3.2.5.3 相关功能需求

| Sale.Return.Create           | 系统允许销售人员创建新的销售退货单，销售退货单进入草稿状态   |
| ---------------------------- | ------------------------------------------------------------ |
| Sale.Return.Input            | 系统允许销售人员在制定销售退货单时进行输入                   |
| Sale.Return.Input.Del        | 系统允许销售人员删除已输入的销售退货单信息                   |
| Sale.Return.Input.Invalid    | 销售人员输入无效信息时，系统拒绝接受并提示信息无效           |
| Sale.Return.Complete         | 销售人员完成销售退货单的填写系统生成此销售退货单并进入提交状态 |
| Sale.Return.Approve          | 销售退货单被审批通过后，系统将销售退货单修改为审批通过状态   |
| Sale.Return.Approve.Reject   | 销售退货单审批不通过，系统将销售退货单修改为驳回状态         |
| Sale.Return.Update           | 系统更新重要数据                                             |
| Sale.Return.Update.Inventory | 系统更改库存数据                                             |
| Sale.Return.Update.Customer  | 系统更改客户的应收应付数据                                   |
| Sale.Return.End              | 系统允许销售人员结束创建销售退货单                           |
| Sale.Return.End.Timeout      | 若长时间未收到销售人员指定销售退货单的操作，系统取消该销售退货单的生成 |
| Sale.Return.Close            | 销售人员退出创建销售退货单的界面                             |

#### 3.2.6 查询成交金额最大的客户

##### 3.2.6.1 特征描述

经过系统认证和授权的销售管理人员可以查看某个时间段成交金额或者订单数最大的客户

##### 3.2.6.2 刺激/响应序列

刺激：进货销售人员查看所有客户的所有订单信息

响应：系统显示所有客户的所有订单信息

刺激：销售人员按照成交金额对客户进行排序

响应：系统由金额高到低显示客户

刺激：销售人员按照订单数对客户进行排序

响应：系统由订单数高到低显示客户

##### 3.2.6.3 相关功能需求

| Sale.Check                    | 系统显示所有订单信息                               |
| ----------------------------- | -------------------------------------------------- |
| Sale.Check.Customer           | 系统以用户为单位合并每个客户的订单信息并显示       |
| Sale.Check.Sort.Volume        | 系统以成交金额由高至低对所有客户进行排序           |
| Sale.Check.Sort.Order         | 系统以订单数由高至低对所有客户进行排序             |
| Sale.Check.Time.Input         | 销售人员输入某个时间段，系统显示此时间段以内的订单 |
| Sale.Check.Time.Input.Invalid | 销售人员输入无效时间段，系统拒绝该请求并报错       |
| Sale.Check.Close              | 销售人员退出查看成交金额界面，系统关闭界面         |

#### 3.2.7 进货销售相关的单据先由销售经理进行一级审批，再由总经理进行二级审批。

##### 3.2.7.1 特征描述

经过系统认证和授权的进货销售人员制定进货销售相关的单据后，需要先提交给销售经理进行一级审批，再提交至总经理进行二级审批

##### 3.2.7.2 刺激/响应序列

刺激： 销售经理点击“审批”按钮

响应： 系统显示相关单据信息

刺激： 销售经理审批通过

响应： 系统向总经理发出审批请求

刺激： 总经理审批通过

响应： 系统更新库存数据和客户的应收应付数据

刺激： 销售经理审批不通过

响应： 系统记录审批人，将销售单标记为驳回状态

刺激： 总经理审不通过

响应： 系统记录审批人，将销售单标记为驳回状态

##### 3.2.7.3 相关功能需求

| Sale.Check.Voucher.Button             | 在销售经理输入审批请求时，系统在桌面显示单据信息             |
| ------------------------------------- | ------------------------------------------------------------ |
| Sale.Check.Voucher.Pre_approve        | 在进货销售相关单据被销售经理审批通过后，系统将单据修改为预审批通过状态,并将单据提交至总经理账户 |
| Sale.Check.Voucher.Button             | 在总经理输入审批请求时，系统在桌面显示进货销售单据信息       |
| Sale.Check.Voucher.Approve            | 在总经理审批通过进货销售相关单据后，系统将单据修改为审批通过状态，并更新库存数据 |
| Sale.Check.Voucher.Pre_approve.Reject | 销售经理审批不通过进货销售相关单据，系统将单据修改为驳回状态 |
| Sale.Check.Voucher.Approve.Reject     | 总经理审批不通过进货销售相关单据时，系统将单据修改为驳回状态 |

#### 3.2.8 查询日志(销售经理）

##### 3.2.8.1 特征描述

经过系统认证和授权的销售经理可以查看系统进货和销售的日志，系统保存了半年内的进货销售日志

##### 3.2.8.2 刺激/响应序列

刺激：销售经理点击“日志”按钮

响应：系统在桌面上显示日志时间段视图

刺激：销售经理选择日志时间段并选择具体操作日志

响应：系统在桌面上显示具体操作日志

刺激：销售经理点击“导出按钮”并选择导出格式以及导出位置

相应：系统按照相应格式和位置将日志数据拷贝至对应存储位置

##### 3.2.8.3 相关功能需求

| Sale.Check.Log.Permission | 系统允许销售经理查看销售日志                                 |
| ------------------------- | ------------------------------------------------------------ |
| Sale.Check.Log.Detail     | 在销售经理选择具体时间操作日志时，系统显示该操作日志         |
| Sale.Check.Log.OutPut     | 在销售经理选择导出操作日志数据时，系统选择相应路径并将数据拷贝 |

#### 3.2.9 账户管理

##### 3.2.9.1 特征描述

经过系统认证和授权的超级管理员可以管理公司的银行账户

##### 3.2.9.2 刺激/响应序列

刺激：超级管理员点击“账户管理”按钮

响应：系统在显示账户管理模块的界面

刺激：超级管理员点击“新增账户”按钮

响应：系统显示新建账户对应的会话框

刺激：超级管理员填写表单，点击确认按钮

响应：系统提示“创建表单成功”

刺激：超级管理员点击“取消”按钮

响应：系统提示“创建取消”

刺激：超级管理员在对应的银行账户之后点击“编辑”按钮

响应：系统显示对应的银行账户的信息供超级管理员更改

刺激：超级管理员编辑对应的银行账户信息，点击确认“按钮”

响应：系统提示“修改成功”

刺激：超级管理员在对应的账户之后点击“删除”按钮

响应：系统弹出会话框

刺激：超级管理员点击“确定删除”按钮

响应：系统删除对应账户，显示“删除成功”

刺激：超级管理员点击“取消删除”按钮

响应：系统提示“取消删除”

##### 3.2.9.3 相关功能需求

| Finance.AccountManagement.Input         | 系统应该允许超级管理员在管理银行账户时进行键盘输入           |
| --------------------------------------- | ------------------------------------------------------------ |
| Finance.AccountManagement.Input.Search  | 当超级管理员在账户信息搜索框中输入关键字、客户编号时，系统要展示对应的账户信息，参见Finance.Account.Display |
| Finance.AccountManagement.Input.Add     | 超级管理员填写完需要创建的账户信息表单，点击“创建”按钮，系统执行增加账户操作， 参见Finance.Account.Add |
| Finance.AccountManagement.Input.Update  | 超级管理员填写完需要更新的账户信息表单，点击“更新”按钮，系统执行更新账户属性操作， 参见Finance.Account.Update |
| Finance.AccountManagement.Input.Del     | 超级管理员删除已经填写还未提交的信息，系统继续等待用户输入   |
| Finance.AccountManagement.Input.Cancel  | 当超级管理员点击“取消”按钮的时候，系统关闭当前的账户管理任务，返回主界面 |
| Finance.AccountManagement.Input.InValid | 超级管理员在管理账户时的输入不合法或者无效时，系统要显示输入无效，参见Finance.Account.Invalid |
| Finance.Account.Display                 | 当超级管理员进行了合法的增删改查之后，系统应该显示指定/全部账户的全部信息 |
| Finance.Account.Add                     | 在超级管理员填写完账户信息表单并且点击“创建”按钮时，系统要检查并添加对应的账户信息，显示是否添加成功，如果添加成功，将添加完的结果在页面显示 |
| Finance.Account.Delete                  | 在超级管理员点击“删除"按钮时，系统应该删除对应的账户信息，并显示是否删除成功 |
| Finance.Account.Update                  | 在超级管理员点击“更新”按钮时，系统应该检查并更新对应的账户信息，显示是否更新成功，如果成功，将更新的结果在页面显示； |
| Finance.Account.Invalid                 | 当超级管理员在查询、修改、增加账户信息时的输入不合法时，系统应该发出警报，提示并等待超级管理员进行合法的输入 |

#### 3.2.10 制定付款单

##### 3.2.10.1 特征描述

经过系统认证和授权的财务人员可以制定一个付款单用于向对应的客户付款，付款单需要经过总经理的审批

##### 3.2.10.2 刺激/响应序列

刺激：财务人员点击“制定付款单”按钮

响应：系统显示“制定付款单”页面内容

刺激：财务人员点击“新增付款单”按钮

响应：系统显示包含付款单信息的表单会话框

刺激：财务人员选择关联的账户名称、客户编号，填写付款金额，点击“确认”按钮

响应：系统提示“制定付款单成功”，在数据库中添加新的付款单信息，更新“制定付款单”页面信息

刺激：财务人员点击“取消”按钮

响应：系统提示“创建付款单已取消”

刺激：系统向总经理发出审批收款单通知

响应：总经理进行审批

刺激：总经理点击审批通过按钮

响应：系统更改公司银行账户中的余额，减少客户的应收款，将更新内容写入数据库，将对应的收款单标记为审批通过

刺激：总经理点击审批不通过按钮

响应：系统将对应的收款单标记为审批不通过

##### 3.2.10.3 相关功能需求

| Finance.PaymentSheet.Create          | 系统允许财务人员创建新的付款单，付款单进入草稿状态           |
| ------------------------------------ | ------------------------------------------------------------ |
| Finance.PaymentSheet.Input           | 系统允许财务人员在制定付款单时进行输入                       |
| Finance.PaymentSheet.Input.Del       | 系统允许财务人员删除已输入的付款单信息                       |
| Finance.PaymentSheet.Input.Invalid   | 财务人员输入无效信息时，系统拒绝接受并提示信息无效           |
| Finance.PaymentSheet.Complete        | 财务人员完成收款单的填写，系统生成此付款单，付款单进入待审核状态 |
| Finance.PaymentSheet.Approve         | 付款单被审批通过后，系统将付款单修改为审批通过状态           |
| Finance.PaymentSheet.Reject          | 付款单审批不通过，系统将付款单修改为驳回状态                 |
| Finance.PaymentSheet.Update          | 系统更新付款单上的重要数据                                   |
| Finance.PaymentSheet.Update.Account  | 系统更改银行账户数据                                         |
| Finance.PaymentSheet.Update.Customer | 系统更改客户的应收数据                                       |
| Finance.PaymentSheet.End             | 系统允许财务人员结束创建付款单                               |
| Finance.PaymentSheet.End.Timeout     | 若长时间未收到财务人员指定付款单的操作，系统取消该付款单的生成 |
| Finance.PaymentSheet.Close           | 财务人员退出创建付款单的界面                                 |

#### 3.2.11 制定收款单

##### 3.2.10.1 特征描述

经过系统认证和授权的财务人员可以制定一个收款单用于向对应的客户收款，收款单需要经过总经理的审批

##### 3.2.10.2 刺激/响应序列

刺激：财务人员点击“制定收款单”按钮

响应：系统显示“制定收款单”页面内容

刺激：财务人员点击“新增收款单”按钮

响应：系统显示包含收款单信息的表单会话框

刺激：财务人员选择关联的账户名称、客户编号，填写收款金额，点击“确认”按钮

响应：系统提示“制定收款单成功”，在数据库中添加新的收款单信息，更新“制定收款单”页面信息

刺激：财务人员点击“取消”按钮

响应：系统提示“创建收款单已取消”

刺激：系统向总经理发出审批收款单通知

响应：总经理进行审批

刺激：总经理点击审批通过按钮

响应：系统更改公司银行账户中的余额，减少客户的应付款，将更新内容写入数据库，将对应的收款单标记为审批通过

刺激：总经理点击审批不通过按钮

响应：系统将对应的收款单标记为审批不通过

##### 3.2.10.3 相关功能需求

| Finance.CollectionSheet.Create          | 系统允许财务人员创建新的收款单，收款单进入草稿状态           |
| --------------------------------------- | ------------------------------------------------------------ |
| Finance.CollectionSheet.Input           | 系统允许财务人员在制定收款单时进行输入                       |
| Finance.CollectionSheet.Input.Del       | 系统允许财务人员删除已输入的收款单信息                       |
| Finance.CollectionSheet.Input.Invalid   | 财务人员输入无效信息时，系统拒绝接受并提示信息无效           |
| Finance.CollectionSheet.Complete        | 财务人员完成收款单的填写，系统生成此收款单，收款单进入待审核状态 |
| Finance.CollectionSheet.Approve         | 收款单被审批通过后，系统将收款单修改为审批通过状态           |
| Finance.CollectionSheet.Reject          | 收款单审批不通过，系统将收款单修改为驳回状态                 |
| Finance.CollectionSheet.Update          | 系统更新收款单上的重要数据                                   |
| Finance.CollectionSheet.Update.Account  | 系统更改银行账户数据                                         |
| Finance.CollectionSheet.Update.Customer | 系统更改客户的应付数据                                       |
| Finance.CollectionSheet.End             | 系统允许财务人员结束创建收款单                               |
| Finance.CollectionSheet.End.Timeout     | 若长时间未收到财务人员指定收款单的操作，系统取消该收款单的生成 |
| Finance.CollectionSheet.Close           | 财务人员退出创建收款单的界面                                 |

#### 3.2.12 查看销售明细表

##### 3.2.12.1 特征描述

 当需要了解商品销售情况或者查询出货单据记录的时候，财务人员选择查看销售明细表。  

##### 3.2.12.2 刺激/响应序列

刺激：财务人员选择查看销售明细表  

响应：系统要求输入时间区间，商品名，客户，业务员，仓库  

刺激：财务人员或总经理输入筛选条件  

响应：系统以列表形式显示匹配的结果，列表中包含如下信息：时间（精确到天），商品名，型号，数量，单价，总额  

刺激：财务人员选择某一单据查看具体信息

响应：系统显示该单据的具体信息

##### 3.2.12.3 相关功能需求

| Finance.SaleDetail.Show           | 系统允许财务人员查看销售出货记录，以列表形式显示             |
| --------------------------------- | ------------------------------------------------------------ |
| Finance.SaleDetail.Show.Search    | 系统允许财务管理人员对销售记录进行关键字搜索，通过输入时间区间，商品名，客户，业务员进行搜索。输入完成后，点击“查找”按钮进行查找，系统将显示符合条件的销售记录。 |
| Finance.SaleDetail.Search.Failure | 系统查询无匹配结果，系统将提示无匹配结果                     |
| Finance.SaleDetail.Search.Null    | 财务人员未输入筛选条件，系统提示“请输入筛选条件”             |
| Finance.SaleDetail.CheckDetail    | 系统允许财务人员查看某一单据的具体信息                       |
| Finance.SaleDetail.Close          | 财务人员退出查看销售明细表的界面                             |

#### 3.2.13 查看经营情况表

##### 3.2.13.1 特征描述

当需要查看经营情况时，财务人员选择查看经营历程表

##### 3.2.13.2 刺激/响应序列

刺激：财务人员选择查看经营情况表  

响应：系统要求输入时间  

刺激：财务人员输入时间段  

响应：系统显示收入类、支出类、利润等信息

刺激：财务人员选择导出

响应：系统提供经营情况的 EXCEL 表下载

##### 3.2.13.3 相关功能需求

| Finance.Situation.Show           | 系统允许财务人员查看经营情况页面                             |
| -------------------------------- | ------------------------------------------------------------ |
| Finance.Situation.Show.Search    | 系统允许财务管理人员对经营情况进行时间范围的指定，通过输入时间区间进行搜索。输入完成后，点击“查找”按钮进行查找，系统将显示符合条件的经营情况。 |
| Finance.Situation.Search.Failure | 系统查询无匹配结果，系统将提示无匹配结果                     |
| Finance.Situation.Search.Null    | 财务人员未输入筛选条件，系统提示“请输入筛选条件”             |
| Finance.Situation.Close          | 财务人员退出查看经营情况的界面                               |

#### 3.2.14 查看经营历程表

##### 3.2.14.1 特征描述

当需要查看一段时间内的所有单据，财务人员选择查看经营历程表

##### 3.2.14.2 刺激/响应序列

刺激：财务人员选择查看经营历程表  

响应：系统要求输入时间区间，单据类型，客户，业务员

刺激：财务人员输入筛选条件  

响应：系统显示符合条件的单据

刺激：财务人员点击单据查看详细信息

响应：返回详细信息

刺激：财务人员选择导出

响应：系统提供经营历程的 EXCEL 表下载

##### 3.2.14.3 相关功能需求

| Finance.SaleProgress.Show                       | 系统允许财务人员查看经营历程表界面                           |
| ----------------------------------------------- | ------------------------------------------------------------ |
| Finance.SaleProgress.Show.Search                | 系统允许财务管理人员对销售记录进行关键字搜索，通过输入时间区间，商品名，客户，业务员进行搜索。输入完成后，点击“查找”按钮进行查找，系统将显示符合条件的单据。 |
| Finance.SaleProgress.Search.Failure             | 系统查询无匹配结果，系统将提示无匹配结果                     |
| Finance.SaleProgress.Search.Null                | 财务人员未输入筛选条件，系统提示“请输入筛选条件”             |
| Finance.SaleProgress.CheckDetail                | 系统允许财务人员查看某一单据的具体信息                       |
| Finance.SaleProgress.CheckDetail.CreateRedFlush | 系统允许财务人员创建某一单据对应的红冲单据                   |
| Finance.SaleProgress.CheckDetail.Complete       | 财务人员完成红冲单的填写，系统生成此红冲单                   |
| Finance.SaleProgress.CheckDetail.End            | 系统允许财务人员结束创建红冲单                               |
| Finance.SaleProgress.CheckDetail.End.Timeout    | 若长时间未收到财务人员指定红冲单的操作，系统取消该红冲单的生成 |
| Finance.SaleProgress.CheckDetail.Close          | 财务人员退出创建红冲单的界面                                 |
| Finance.SaleProgress.Close                      | 财务人员退出查看经营历程表的界面                             |

#### 3.2.15 期初建账

##### 3.2.15.1 特征描述

经过系统授权与认证的财务人员可以在期初建帐，可以创建期初客户信息，期初商品信息，期初银行账户信息。

##### 3.2.15.2 刺激/响应序列

刺激：财务人员点击“期初创建客户”按钮

响应：系统弹出创建客户的表单

刺激：财务人员填写客户表单，点击创建按钮

响应：系统提示创建成功，并且更新期初客户列表数据

刺激：财务人员点击“期初创建商品”按钮

响应：系统弹出创建客户表单

刺激：财务人员填写客户表单，点击创建按钮

响应：系统提示创建成功，并且更新期初商品列表数据

刺激：财务人员点击“期初创建账户”按钮

响应：系统弹出创建账户表单

刺激：财务人员填写账户表单，点击创建按钮

响应：系统提示创建成功，并且更新期初账户列表

刺激：财务人员选择期初信息类型

响应：系统展示所选择的期初信息类型

##### 3.2.15.3 相关功能需求

| Finance.InitAccount.Create                    | 系统允许财务人员期初建账                               |
| --------------------------------------------- | ------------------------------------------------------ |
| Finance.InitAccount.Create.CreateInitProduct  | 系统允许财务人员填写期初商品表单，创建期初商品信息     |
| Finance.InitAccount.Create.CreateInitAccount  | 系统允许财务人员填写期初银行账户表单，创建期初账户信息 |
| Finance.InitAccount.Create.CreateInitCustomer | 系统允许财务人员填写期初客户信息表单，创建期初客户信息 |
| Finance.InitAccount.Create.Complete           | 财务人员完成期初建账                                   |
| Finance.InitAccount.Create.Cancel             | 财务人员取消期初建账                                   |
| Finance.InitAccount.Create.Close              | 系统允许财务人员关闭期初建账界面                       |
| Finance.InitAccount.Show                      | 系统允许财务人员查看期初建账信息                       |
| Finance.InitAccount.Show.ShowInitProduct      | 系统允许财务人员查看期初商品信息                       |
| Finance.InitAccount.Show.ShowInitAccount      | 系统允许财务人员查看期初账户信息                       |
| Finance.InitAccount.Show.ShowInitCustomer     | 系统允许财务人员查看期初客户信息                       |
| Finance.InitAccount.Show.Cancel               | 系统允许财务人员取消期初信息查看                       |
| Finance.InitAccount.Show.Close                | 系统允许财务人员关闭期初信息查看界面                   |

#### 3.2.17 制定促销策略

##### 3.2.17.1 特征描述

经过系统授权和认证的总经理可以制定促销策略

##### 3.2.17.1 刺激/响应序列

刺激：总经理点击“制定促销策略”按钮

响应：系统显示“制定促销策略”界面

刺激：总经理点击“按照用户等级制定促销策略”按钮

响应：系统显示对应表单

刺激：总经理填写表单，点击提交按钮

响应：系统提示创建成功

刺激：总经理点击“按照特价包制定促销策略”按钮

响应：系统显示对应表单

刺激：总经理填写表单，点击提交按钮

响应：系统提示创建成功

刺激：总经理点击“按照总价制定促销策略”按钮

响应：系统显示对应表单

刺激：总经理填写表单，点击提交按钮

响应：系统显示创建成功

##### 3.2.17.2 相关功能需求

| Promotion.Create                | 系统允许总经理建立促销策略             |
| ------------------------------- | -------------------------------------- |
| Promotion.Create.CustomerLevel  | 系统允许总经理根据用户等级创建促销策略 |
| Promotion.Create.ProductPackage | 系统允许总经理使用特价包创建促销策略   |
| Promotion.Create.TotalAmount    | 系统允许总经理根据总价创建促销策略     |
| Promotion.Create.Input          | 系统允许总经理在创建促销策略时进行输入 |
| Promotion.Create.Cancel         | 系统允许总经理取消创建促销策略         |
| Promotion.Create.Close          | 系统允许总经理关闭创建促销策略         |

#### 3.2.18 制定薪酬规则

##### 3.2.18.1 特征描述

经过系统授权与认证的人力资源人员可以制定各个岗位的薪酬规则，用于生成工资单

##### 3.2.18.2 刺激/响应序列

刺激：人力资源人员点击“制定部门薪资规则”按钮

响应：系统显示各部门薪酬规则列表

刺激：人力资源人员点击对应部门薪酬规则的“编辑”按钮

响应：系统显示“修改部门薪资规则”对话框

刺激：人力资源人员填写该部门薪资规则表单，点击“确定”按钮

响应：系统更新该部门的薪资规则，显示“修改成功”

##### 3.2.18.3 相关功能需求

| HumanResource.SalaryRule.ShowAllSalaryRules | 系统允许人力资源人员查看所有部门薪酬规则信息 |
| ------------------------------------------- | -------------------------------------------- |
| HumanResource.SalaryRule.Update             | 系统允许人力资源人员修改部门薪酬规则         |
| HumanResource.SalaryRule.Cancel             | 系统允许人力资源人员取消修改部门薪酬规则     |
| HumanResource.SalaryRule.Close              | 系统允许人力资源人员关闭制定部门薪酬规则页面 |

#### 3.2.19 管理员工信息

##### 3.2.19.1 特征描述

经过系统授权与认证的人力资源人员可以管理员工信息

##### 3.2.19.2 刺激/响应序列

刺激：人力资源人员点击“管理员工信息”按钮

响应：系统显示“管理员工信息”界面

刺激：人力资源人员点击“添加员工”按钮

响应：系统显示“添加员工”对话框

刺激：人力资源人员填写员工信息，点击“确定”按钮

响应：系统添加该员工信息，并创建对应的ERP系统账号，显示“添加成功”和对应的ERP系统账号密码信息

刺激：人力资源人员点击员工信息对应的“编辑”按钮

响应：系统显示“修改员工信息”对话框

刺激：人力资源人员修改员工信息，点击“确定”按钮

响应：系统修改该员工信息，显示“修改成功”

刺激：人力资源人员点击员工信息对应的“删除”按钮

响应：系统显示“是否要删除该员工？”提示

刺激：人力资源人员点击“确定”按钮

响应：系统删除该员工信息，并删除对应的ERP系统账号，显示“删除成功”

##### 3.2.19.3 相关功能需求

| HumanResource.Employee.ShowAllEmployees | 系统允许人力资源人员查看所有员工信息       |
| --------------------------------------- | ------------------------------------------ |
| HumanResource.Employee.Add              | 系统允许人力资源人员添加员工信息           |
| HumanResource.Employee.Update           | 系统允许人力资源人员修改员工信息           |
| HumanResource.Employee.Delete           | 系统允许人力资源人员删除员工信息           |
| HumanResource.Employee.Add.Cancel       | 系统允许人力资源人员取消添加员工信息       |
| HumanResource.Employee.Update.Cancel    | 系统允许人力资源人员取消修改员工信息       |
| HumanResource.Employee.Delete.Cancel    | 系统允许人力资源人员取消删除员工信息       |
| HumanResource.Employee.Close            | 系统允许人力资源人员关闭管理员工信息的页面 |

#### 3.2.20 员工打卡

##### 3.2.20.1 特征描述

经过系统授权与认证的库存管理人员、进货销售人员、财务人员、人力资源人员可以进行每日打卡

##### 3.2.20.2 刺激/响应序列

刺激：系统用户点击“打卡”按钮

响应：系统记录该日打卡，显示“打卡成功”提示

##### 3.2.20.3 相关功能需求

| HumanResource.SignIn | 系统允许库存管理人员、进货销售人员、财务人员、人力资源人员每日打卡 |
| -------------------- | ------------------------------------------------------------ |
|                      |                                                              |

#### 3.2.21 制定工资单

##### 3.2.21.1 特征描述

经过系统认证和授权的人力资源人员可以制定工资单向员工发放工资，工资单需要经过总经理的审批

##### 3.2.21.2 刺激/响应序列

刺激：人力资源人员点击“制定工资单”按钮

响应：系统显示“制定工资单”页面

刺激：人力资源人员选择对应的时间段，点击创建工资单

响应：系统自动创建所有员工的工资单，显示“创建成功”

##### 3.2.22.3 相关功能需求 

| HumanResource.Salary.Select | 系统允许人力资源人员选择时间           |
| --------------------------- | -------------------------------------- |
| HumanResource.Salary.Create | 系统允许人力资源人员创建工资单         |
| HumanResource.Salary.Cancel | 系统允许人力资源人员取消创建工资单     |
| HumanResource.Salary.Close  | 系统允许人力资源人员关闭创建工资单界面 |

### 3.3 非功能性需求

#### 3.3.1 安全性

- 系统应该只允许经过验证和授权的用户访问
- 系统应该按照用户的身份验证用户的访问权限
- 系统中有一个默认的超级管理员账户，该账户只允许超级管理员用户修改口令

#### 3.3.2 可维护性

无特殊需求

#### 3.3.3 易用性

所有类型的人员都不需要专门的培训，就可以在10分钟之内完成一个操作任务

#### 3.3.4 可靠性

无特殊需求

#### 3.3.5 业务规则

无特殊需求

### 3.4 数据需求

#### 3.4.1 数据定义

| Sale.warehouse.id                                  | int(11)        |
| -------------------------------------------------- | -------------- |
| Sale.warehouse.pid                                 | varchar(16)    |
| Sale.warehouse.quantity                            | int(11)        |
| Sale.warehouse.purchase_price                      | decimal(10, 2) |
| Sale.warehouse.batch_id                            | int(11)        |
| Sale.warehouse.production_date                     | datetime(0)    |
|                                                    |                |
| Sale.warehouse_input_sheet.id                      | varchar(32)    |
| Sale.warehouse_input_sheet.batch_id                | int(11)        |
| Sale.warehouse_input_sheet.operator                | varchar(255)   |
| Sale.warehouse_input_sheet.update_time             | datetime(0)    |
|                                                    |                |
| Sale.warehouse_input_sheet_content.id              | int(11)        |
| Sale.warehouse_input_sheet_content.wi_id           | varchar(32)    |
| Sale.warehouse_input_sheet_content.pid             | varchar(16)    |
| Sale.warehouse_input_sheet_content.quantity        | int(11)        |
| Sale.warehouse_input_sheet_content.purchase_price  | decimal(10, 2) |
| Sale.warehouse_input_sheet_content.production_date | datetime(0)    |
| Sale.warehouse_input_sheet_content.remark          | varchar(255)   |
|                                                    |                |
| Sale.warehouse_output_sheet.id                     | varchar(32)    |
| Sale.warehouse_output_sheet.operator               | varchar(255)   |
| Sale.warehouse_output_sheet.updatetime             | datetime(0)    |
|                                                    |                |
| Sale.warehouse_output_sheet.id                     | int(11)        |
| Sale.warehouse_output_sheet.pid                    | varchar(16)    |
| Sale.warehouse_output_sheet.wo_id                  | varchar(32)    |
| Sale.warehouse_output_sheet.batch_id               | int(11)        |
| Sale.warehouse_output_sheet.quantity               | int(11)        |
| Sale.warehouse_output_sheet.purchase_price         | decimal(10, 2) |
| Sale.warehouse_output_sheet.remark                 | varchar(255)   |
|                                                    |                |
| Sale.user.id                                       | int(11)        |
| Sale.user.name                                     | varchar(255)   |
| Sale.user.password                                 | varchar(255)   |
| Sale.user.role                                     | varchar(255)   |
|                                                    |                |
| Sale.product.id                                    | varchar(16)    |
| Sale.product.name                                  | varchar(255)   |
| Sale.product.category_id                           | int(11)        |
| Sale.product.type                                  | varchar(255)   |
| Sale.product.quantity                              | int(11)        |
| Sale.product.purchase_price                        | decimal(10, 2) |
| Sale.product.retail_price                          | decimal(10, 2) |
| Sale.product.recent_pp                             | decimal(10, 2) |
| Sale.product.rencet_rp                             | decimal(10, 2) |
|                                                    |                |
| Sale.category.id                                   | int(11)        |
| Sale.category.name                                 | varchar(255)   |
| Sale.category.parent_id                            | int(11)        |
| Sale.category.is_leaf                              | tinyint(4)     |
| Sale.category.item_count                           | int(11)        |
| Sale.category.item_index                           | int(11)        |
|                                                    |                |
| Finance.company_account.id                         | int(11)        |
| Finance.company_account.name                       | varchar(255)   |
| Finance.company_account.amount                     | bigint(20)     |
|                                                    |                |
| Finance.collection_sheet.id                        | varchar(31)    |
| Finance.collection_sheet.customer                  | int(11)        |
| Finance.collection_sheet.operator                  | bigint(20)     |
| Finance.collection_sheet.total_amount              | varchar(31)    |
| Finance.collection_sheet.state                     | varchar(31)    |
| Finance.collection_sheet.create_time               | datetime       |
|                                                    |                |
| Finance.transfer_list_sheet.id                     | int(11)        |
| Finance.transfer_list_sheet.company_account_id     | int(11)        |
| Finance.transfer_list_sheet.transfer_amount        | bigint(20)     |
| Finance.transfer_list_sheet.collection_sheet_id    | varchar(31)    |
| Finance.transfer_list_sheet.remark                 | varchar(255)   |
|                                                    |                |
| Finance.payment_sheet.id                           | varchar(31)    |
| Finance.payment_sheet.customer                     | int(11)        |
| Finance.payment_sheet.operator                     | bigint(20)     |
| Finance.payment_sheet.total_amount                 | varchar(31)    |
| Finance.payment_sheet.state                        | varchar(31)    |
| Finance.payment_sheet.create_time                  | datetime       |
|                                                    |                |
| Finance.payment_sheet_content.id                   | int(11)        |
| Finance.payment_sheet_content.company_account_id   | int(11)        |
| Finance.payment_sheet_content.transfer_amount      | bigint(20)     |
| Finance.payment_sheet_content.collection_sheet_id  | varchar(31)    |
| Finance.payment_sheet_content.remark               | varchar(255)   |

#### 3.4.2 默认数据

默认数据用于以下两种情况：

1. 系统中新增加数据时。
2. 编辑数据时不小心将相关内容清空时。

- Default1：商品的数量默认为1.
- Default2：因为商品在实际中进价和售价一直都在变化，所以规定商品的进价和售价默认为该商品最近的进价和售价。
- Default3：入库商品列表中商品单价默认为商品信息里的进价 。
- Default4：出货商品清单中商品单价默认为商品信息里的销售价。 
- Default5：期初建账时，商品信息中的进价、售价默认为上年平均。 

#### 3.4.3 数据格式要求

- Format1：进货单的单据编号必须是JHD-yyyyMMdd-xxxxx，后五位每天从 1 开始编号；进货退货单的单据编号必须是  JHTHD-yyyyMMdd-xxxxx ，后五位每天从 1 开始编号。
- Format2：销售出货单的单据编号必须是 XSD-yyyyMMdd-xxxxx，后五位每天从 1 开始编号；销售退货单的单据编号必须是  XSTHD-yyyyMMdd-xxxxx ，后五位每天从 1 开始编号。
- Format3：商品编号格式必须是xxxxxxxxxxx-yyyyy，其中xxxxxxxxxxx为其所在的分类编号，yyyyy为其添加次序。 

其余编号以此类推

### 3.5 其他需求

- 系统主要操作都有日志进行记录，并对经理和财务人员提供查询功能。 
- 采用 Vue+Springboot+Mybatis 框架进行开发。
- 用户远程使用系统。 