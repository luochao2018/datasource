# datasource
管理多数据库切换


2020.09.05更新springcloud模块,测试完成后将新增内容注释掉了,使用时需要注意以下:
1.application启动项释放eureka注解,释放import
2.pom文件释放spring-cloud依赖,eureka依赖,内部tomcat禁用依赖(这一步必须,因为eureka不能在内部tomcat使用,即不能application方式启动,所以必须在外部tomcat部署)
