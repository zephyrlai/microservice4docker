FROM ccr.ccs.tencentyun.com/zephyrlai/centos-jdk8-tomcat8:0.0.1
MAINTAINER zephyrlai
COPY course-edge-service-1.0-SNAPSHOT.jar /myJarPacks/course-edge-service-1.0-SNAPSHOT.jar
RUN yum -y install nc
ENTRYPOINT ["nc","-l","8091"]
# for win-env
# ENTRYPOINT ["java","-jar","/myJarPacks/course-edge-service-1.0-SNAPSHOT.jar","--dubbo.address=192.168.163.128"]
# for mac-env
ENTRYPOINT ["java","-jar","/myJarPacks/course-edge-service-1.0-SNAPSHOT.jar","--dubbo.address=192.168.2.139"]