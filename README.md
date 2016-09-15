# Jenkins和Selenium Grid多设备平行测试


目录及介绍

1、jsonfiles:目录里包含着appium用于注册到Selenium Grid的配置文件(每个配置文件对应一个安卓设备)

2、src:用于存放测试代码，注意：如果用maven进行构建测试，需要满足maven的约定，及测试代码的目录应该为src\test\java,否则maven执行测试任务无法识别到代码

3、build.xml:ant构建文件，编写的目的是便于Jenkins集成，目前在本地运行没问题，但是在Jenkins中会存在问题(暂时可以考虑不适用)

4：pom.xml:maven构建文件，本例子中主要用于管理appium测试工程中的库依赖及测试的参数配置

5、selenium-server-standalone-2.48.2.jar：用来启动Selenium 服务，使用方法 java -jar selenium-server-standalone-2.48.2.jar -role hub http://127.0.0.1:4444/grid/console,前提是本机有安装，由于这个jar包文件过大，请自行去Selenium官网下载：http://www.seleniumhq.org/download/
   Java JDK 环境
   
6、Testng.xml：用于配置testng参数化测试，本例子中主要是用来做多机并行测试的


使用方法：

1、先启动Selenium Grid server，使用如下命令： java -jar selenium-server-standalone-2.48.2.jar -role hub http://127.0.0.1:4444/grid/console，启动成功后可以在浏览器中打开注册的地址，正常打开则注册成功

2、注册appium server 到 Gird，要测试几个设备就注册几个，如：appium -a 127.0.0.1 -p 4723  --bootstrap-port 4730 -U 7N2RDQ1496009372 --nodeconfig  %~dp0jsonFiles\nodeconfig_1.json

3、当Selenium 控制窗口显示appium 的相关参数注册成功时，进入到步骤1的地址会显示注册设备的相关参数

4、执行构建，命令行使用mvn test 即可完成测试，测试完成maven会自动生成target目录，surefire-reports目录下会显示测试的结果


注意：一定要保证jsonfiles里面的配置文件中使用的参数与appium 注册时的参数一致，否则会出现无法新建会话的情况
