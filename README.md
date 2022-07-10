# DubboDemo
第一次提交：
Zookeeper=3.7.1<br>
使用步骤<br>
1.启动Zookeeper注册中心<br>
2.启动provider port 8001<br>
3.启动consumer port 8002<br>
4.浏览器输入localhost:8082/register 进入注册页面<br>
5.输入注册信息即可，不使用数据库，注册信息符合要求返回success<br><br>
第二次提交：<br>
1.前端页面不应该放在provider中<br>
&nbsp;前端页面放在各自的consumer中<br>
2.provider中的各种校验逻辑能否优化，比如使用hibernate validator
&nbsp;使用validator去校验表单传过来的逻辑，但是在所定义的全局异常处理没有发挥作用，目前的异常处理写在controller里<br>
3.provider中判断用户名，手机号，邮箱是否重复，多次访问数据库效率太低，怎么优化<br>
  &nbsp;①采用redis做缓存，缓解数据库压力<br>
  &nbsp;②采用RabbitMQ消息队列，分散数据库压力<br>
  &nbsp;以上两种只是对于注册用户校验通过后，将用户信息插入数据库时有用，对校验逻辑时查询数据库无用（redis不缓存用用户信息）<br>
  &nbsp;将三次访问数据库改为一次访问数据库，通过or来判断三者是否存在数据库中，但是这样无法准确告知用户那个字段有问题<br>
4.common中依赖的包是多余的<br>
  &nbsp;只需要将某个模块需要的引入对应模块即可<br>
5.consumer中UserController第45行判断map是否为空可能会出现npe<br>
6.consumer中返回信息的写法是否过于繁琐，可以怎么优化<br>
  
