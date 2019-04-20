FROM ccr.ccs.tencentyun.com/zephyrlai/centos-jdk8-tomcat8:0.0.1
MAINTAINER zephyrlai
COPY user-thrift-service-1.0-SNAPSHOT.jar /myJarPacks/user-thrift-service-1.0-SNAPSHOT.jar
RUN yum -y install nc
ENTRYPOINT["nc","-l","8081"]
# for win-env
# ENTRYPOINT ["java","-jar","/myJarPacks/user-thrift-service-1.0-SNAPSHOT.jar","--mysql.server=192.168.3.181"]
# for mac-env
ENTRYPOINT ["java","-jar","/myJarPacks/user-thrift-service-1.0-SNAPSHOT.jar","--mysql.server=192.168.0.110"]