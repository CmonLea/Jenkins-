# Jenkins和Selenium Grid多设备平行测试


目录及介绍

1、jsonfiles:目录里包含着appium用于注册到Selenium Grid的配置文件(每个配置文件对应一个安卓设备)

2、src:用于存放测试代码，注意：如果用maven进行构建测试，需要满足maven的约定，及测试代码的目录应该为src\test\java,否则maven执行测试任务无法识别到代码

3、build.xml:ant构建文件，编写的目的是便于Jenkins集成，目前在本地运行没问题，但是在Jenkins中会存在问题(暂时可以考虑不适用)

4：pom.xml:maven构建文件，本例子中主要用于管理appium测试工程中的库依赖及测试的参数配置

5、selenium-server-standalone-2.48.2.jar：用来启动Selenium 服务，使用方法 java -jar selenium-server-standalone-2.48.2.jar -role hub http://127.0.0.1:4444/grid/console,前提是本机有安装
   Java JDK 环境
   
6、Testng.xml：用于配置testng参数化测试，本例子中主要是用来做多机并行测试的


注意：一定要保证jsonfiles里面的配置文件中使用的参数与appium 注册时的参数一致，否则会出现无法新建会话的情况
