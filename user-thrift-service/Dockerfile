FROM ccr.ccs.tencentyun.com/zephyrlai/centos-jdk8-tomcat8:0.0.1
MAINTAINER zephyrlai
COPY user-thrift-service-1.0-SNAPSHOT.jar /myJarPacks/user-thrift-service-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/myJarPacks/user-thrift-service-1.0-SNAPSHOT.jar","--mysql.server=192.168.3.181"]